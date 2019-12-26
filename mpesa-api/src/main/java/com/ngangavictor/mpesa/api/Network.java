package com.ngangavictor.mpesa.api;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.ngangavictor.mpesa.api.LNMPSettings.getAccess_token;
import static com.ngangavictor.mpesa.api.LNMPSettings.getAccess_token_url;
import static com.ngangavictor.mpesa.api.LNMPSettings.getConsumer_key;
import static com.ngangavictor.mpesa.api.LNMPSettings.getConsumer_secret;

public class Network {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String accessToken() throws IOException, JSONException {

        String appKeySecret = getConsumer_key() + ":" + getConsumer_secret();
        byte[] bytes = appKeySecret.getBytes("ISO-8859-1");
        String encoded = Base64.getEncoder().encodeToString(bytes);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(getAccess_token_url())
                .get()
                .addHeader("authorization", "Basic " + encoded)
                .addHeader("cache-control", "no-cache")

                .build();

        Response response = client.newCall(request).execute();
        JSONObject jsonObject = new JSONObject(response.body().string());
        Log.d("ACCESS TOKEN: ", jsonObject.getString("access_token"));
        return jsonObject.getString("access_token");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sendRequest(String requestJson, String url) throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, requestJson);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer " + getAccess_token())
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();



        //return response.body().toString();
        JSONObject res = new JSONObject(response.body().string());
        if(res.has("ResponseCode")) {
//            Log.d("RESPONSE: ", response.body().string());
            Log.d("ResponseCode: ", res.getString("ResponseCode"));
            return res.getString("ResponseCode");
        }else{
            Log.d("RESPONSE: ", response.body().string());
            return "1";
        }

    }

}
