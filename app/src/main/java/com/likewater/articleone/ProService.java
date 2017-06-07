package com.likewater.articleone;

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

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
