package com.ngangavictor.mpesa.api;

public class LNMPQuerySettings {
    public static String business_short_code;
    public static String password;
    public static String timestamp;
    public static String checkout_request_id;
    public static String stk_push_query_url;

    public static String getBusiness_short_code() {
        return business_short_code;
    }

    public static void setBusiness_short_code(String business_short_code) {
        LNMPQuerySettings.business_short_code = business_short_code;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        LNMPQuerySettings.password = password;
    }

    public static String getTimestamp() {
        return timestamp;
    }

    public static void setTimestamp(String timestamp) {
        LNMPQuerySettings.timestamp = timestamp;
    }

    public static String getCheckout_request_id() {
        return checkout_request_id;
    }

    public static void setCheckout_request_id(String checkout_request_id) {
        LNMPQuerySettings.checkout_request_id = checkout_request_id;
    }

    public static String getStk_push_query_url() {
        return stk_push_query_url;
    }

    public static void setStk_push_query_url(String stk_push_query_url) {
        LNMPQuerySettings.stk_push_query_url = stk_push_query_url;
    }
}
