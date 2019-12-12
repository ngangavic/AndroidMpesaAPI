package com.ngangavictor.mpesa.stkpush;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static com.ngangavictor.mpesa.stkpush.Network.sendRequest;
import static com.ngangavictor.mpesa.stkpush.Settings.getAccount_reference;
import static com.ngangavictor.mpesa.stkpush.Settings.getAmount;
import static com.ngangavictor.mpesa.stkpush.Settings.getBusiness_short_code;
import static com.ngangavictor.mpesa.stkpush.Settings.getCallback_url;
import static com.ngangavictor.mpesa.stkpush.Settings.getDate;
import static com.ngangavictor.mpesa.stkpush.Settings.getPassword;
import static com.ngangavictor.mpesa.stkpush.Settings.getPhone;
import static com.ngangavictor.mpesa.stkpush.Settings.getStk_push_url;
import static com.ngangavictor.mpesa.stkpush.Settings.getTimeout_url;
import static com.ngangavictor.mpesa.stkpush.Settings.getTransaction_desc;
import static com.ngangavictor.mpesa.stkpush.Settings.getTransaction_type;

public class Mpesa {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sktPush()throws JSONException,IOException{
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("BusinessShortCode", getBusiness_short_code());
        jsonObject.put("Password", getPassword());
        jsonObject.put("Timestamp", getDate());
        jsonObject.put("TransactionType", getTransaction_type());
        jsonObject.put("Amount", getAmount());
        jsonObject.put("PhoneNumber", getPhone());
        jsonObject.put("PartyA", getPhone());
        jsonObject.put("PartyB", getBusiness_short_code());
        jsonObject.put("CallBackURL", getCallback_url());
        jsonObject.put("AccountReference", getAccount_reference());
        jsonObject.put("QueueTimeOutURL", getTimeout_url());
        jsonObject.put("TransactionDesc", getTransaction_desc());

        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
        return sendRequest(requestJson, getStk_push_url());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String c2bSimulation()throws JSONException,IOException{
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("ShortCode", "602994");
        jsonObject.put("CommandID", "CustomerPayBillOnline");
        jsonObject.put("Amount", "10");
        jsonObject.put("Msisdn", "254708374149");
        jsonObject.put("BillRefNumber", "vic2020");

        jsonArray.put(jsonObject);

        String requestJson=jsonArray.toString().replaceAll("[\\[\\]]","");
        return sendRequest(requestJson, "https://sandbox.safaricom.co.ke/mpesa/c2b/v1/simulate");
    }


}
