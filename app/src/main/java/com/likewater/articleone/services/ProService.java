package com.likewater.articleone.services;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.likewater.articleone.Constants;
import com.likewater.articleone.models.Rep;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProService {

    public static void findReps(String congress, String state, Callback callback){

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.ENDPOINT).newBuilder();
        urlBuilder.addPathSegment(congress);
        urlBuilder.addPathSegment(state);
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
                    String name = repJSON.getString("name");
                    String role = repJSON.getString("role");
                    String party = repJSON.getString("party");
                    String apiUri = repJSON.getString("api_uri");

                    Rep rep = new Rep(name, role, party, apiUri);
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
