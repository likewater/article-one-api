package com.likewater.articleone.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.likewater.articleone.Constants;
import com.likewater.articleone.models.RepDetail;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetailService {

    public static void findRepDetail(String member, Callback callback){

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(member).newBuilder();
        urlBuilder.addPathSegment(Constants.URL_END);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header(Constants.HEADER, Constants.PRO_PUBLICA_KEY)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<RepDetail> processResults(Response response) {
        ArrayList<RepDetail> repDetails = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            if(response.isSuccessful()) {
                JSONObject responseJSON = new JSONObject(jsonData);
                JSONArray repListJSON = responseJSON.getJSONArray("results");

                for(int i = 0; i < repListJSON.length(); i++){
                    JSONObject repJSON = repListJSON.getJSONObject(i);
                    String lastName = repJSON.getString("lastName");
                    String firstName = repJSON.getString("firstName");
                    String title = repJSON.getString("title");
                    String state = repJSON.getString("state");
                    String district = repJSON.getString("district");
                    String currentParty = repJSON.getString("currentParty");
                    String url = repJSON.getString("url");
                    String twitterAccount = repJSON.getString("twitterAccount");
                    String facebookAccount = repJSON.getString("facebookAccount");
                    String youtubeAccount = repJSON.getString("youtubeAccount");
                    String phone = repJSON.getString("phone");
                    String apiUri = repJSON.getString("api_uri");

                    RepDetail repDetail = new RepDetail("115", lastName, firstName, title, state,
                            district, currentParty, url, twitterAccount, facebookAccount,
                            youtubeAccount, phone);
                    repDetails.add(repDetail);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return repDetails;
    }
}


