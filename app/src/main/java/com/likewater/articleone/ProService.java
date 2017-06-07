package com.likewater.articleone;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.likewater.articleone.Constants;
import com.likewater.articleone.Rep;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProService {
    public static void findReps(String location, Callback callback){

        //https://api.propublica.org/congress/v1/115/senate/members.json
        //endpoint example

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.ENDPOINT).newBuilder();
        urlBuilder.addPathSegment(location);
        urlBuilder.addPathSegment(Constants.URL_END);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header(Constants.HEADER, Constants.PRO_PUBLICA_KEY)
                .build();
        Log.d(url, url);
        Log.d(Constants.HEADER, Constants.HEADER );

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Rep> processResults(Response response) {
        ArrayList<Rep> reps = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            if(response.isSuccessful()) {
                JSONObject responseJSON = new JSONObject(jsonData);
                JSONArray repListJSON = responseJSON.getJSONArray("results");

                for(int i = 0; i < repListJSON.length(); i++){
                   JSONObject repJSON = repListJSON.getJSONObject(i);
                    String lastName = repJSON.getString("last_name");
                    String firstName = repJSON.getString("first_name");
                    String state = repJSON.getString("state");
                    String party = repJSON.getString("party");

                    Rep rep = new Rep(firstName, lastName, state, party);
                    reps.add(rep);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reps;

    }
}
