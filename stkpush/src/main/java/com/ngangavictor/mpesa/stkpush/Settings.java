package com.ngangavictor.mpesa.stkpush;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static com.ngangavictor.mpesa.stkpush.GenerateValues.*;
import static com.ngangavictor.mpesa.stkpush.GenerateValues.generatePassword;
import static com.ngangavictor.mpesa.stkpush.Network.accessToken;

public class Settings {
    public static String business_short_code;
    public static String transaction_type;
    public static String callback_url;
    public static String timeout_url;
    public static String password;
    public static final String date=generateDate();
    public static String account_reference;
    public static String transaction_desc;
    public static String stk_push_url;
    public static String access_token_url;
    public static String consumer_key;
    public static String consumer_secret;
    public static String access_token;
    public static String amount;
    public static String phone;
    public static String passkey;

    public static String getBusiness_short_code() {
        return business_short_code;
    }

    public static void setBusiness_short_code(String business_short_code) {
        Settings.business_short_code = business_short_code;
    }

    public static String getTransaction_type() {
        return transaction_type;
    }

    public static void setTransaction_type(String transaction_type) {
        Settings.transaction_type = transaction_type;
    }

    public static String getCallback_url() {
        return callback_url;
    }

    public static void setCallback_url(String callback_url) {
        Settings.callback_url = callback_url;
    }

    public static String getTimeout_url() {
        return timeout_url;
    }

    public static void setTimeout_url(String timeout_url) {
        Settings.timeout_url = timeout_url;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getPassword() throws UnsupportedEncodingException {
        return password=generatePassword();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void setPassword() throws UnsupportedEncodingException {
        Settings.password = generatePassword();
    }

    public static String getDate() {
        return date;
    }

    public static String getAccount_reference() {
        return account_reference;
    }

    public static void setAccount_reference(String account_reference) {
        Settings.account_reference = account_reference;
    }

    public static String getTransaction_desc() {
        return transaction_desc;
    }

    public static void setTransaction_desc(String transaction_desc) {
        Settings.transaction_desc = transaction_desc;
    }

    public static String getStk_push_url() {
        return stk_push_url;
    }

    public static void setStk_push_url(String stk_push_url) {
        Settings.stk_push_url = stk_push_url;
    }

    public static String getAccess_token_url() {
        return access_token_url;
    }

    public static void setAccess_token_url(String access_token_url) {
        Settings.access_token_url = access_token_url;
    }

    public static String getConsumer_key() {
        return consumer_key;
    }

    public static void setConsumer_key(String consumer_key) {
        Settings.consumer_key = consumer_key;
    }

    public static String getConsumer_secret() {
        return consumer_secret;
    }

    public static void setConsumer_secret(String consumer_secret) {
        Settings.consumer_secret = consumer_secret;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getAccess_token() throws IOException, JSONException {
        return access_token=accessToken();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void setAccess_token() throws IOException, JSONException {
        Settings.access_token = accessToken();
    }

    public static String getAmount() {
        return amount;
    }

    public static void setAmount(String amount) {
        Settings.amount = amount;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        Settings.phone = phone;
    }

    public static String getPasskey() {
        return passkey;
    }

    public static void setPasskey(String passkey) {
        Settings.passkey = passkey;
    }
}
