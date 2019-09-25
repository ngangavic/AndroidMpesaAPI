package com.example.mpesaapi.network;

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

import static com.example.mpesaapi.settings.SandBox.consumer_key;
import static com.example.mpesaapi.settings.SandBox.consumer_secret;

public class Network {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sendRequest(String requestJson) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, requestJson);
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/safaricom/c2b/v1/simulate")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer "+accesToken())
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        Log.d("RESPONSE: ",response.body().string());
        return response.body().toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String accesToken() throws IOException, JSONException {

        String appKeySecret = consumer_key + ":" + consumer_secret;
        byte[] bytes = appKeySecret.getBytes("ISO-8859-1");
        String encoded = Base64.getEncoder().encodeToString(bytes);


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
                .get()
                .addHeader("authorization", "Basic "+encoded)
                .addHeader("cache-control", "no-cache")

                .build();

        Response response = client.newCall(request).execute();
        JSONObject jsonObject=new JSONObject(response.body().string());
        //System.out.println(jsonObject.getString("access_token"));
        Log.d("ACCESS TOKEN: ",jsonObject.getString("access_token"));
        return jsonObject.getString("access_token");
    }


}
