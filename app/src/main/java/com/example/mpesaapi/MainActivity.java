package com.example.mpesaapi;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mpesaapi.database.DatabaseHelper;
import com.ngangavictor.mpesa.api.B2BSettings;
import com.ngangavictor.mpesa.api.C2BSettings;
import com.ngangavictor.mpesa.api.LNMPSettings;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

import static com.example.mpesaapi.mpesa.Mpesa.accountBalance;
import static com.example.mpesaapi.mpesa.Mpesa.businessBusiness;
import static com.example.mpesaapi.mpesa.Mpesa.businessCustomer;
import static com.example.mpesaapi.mpesa.Mpesa.registerURL;
import static com.example.mpesaapi.mpesa.Mpesa.reversal;
import static com.example.mpesaapi.mpesa.Mpesa.transactionStatus;
import static com.example.mpesaapi.settings.SandBox.getConfirmation_url;
import static com.example.mpesaapi.settings.SandBox.getInitiator_name;
import static com.example.mpesaapi.settings.SandBox.getQueue_timeout_url;
import static com.example.mpesaapi.settings.SandBox.getResult_url;
import static com.example.mpesaapi.settings.SandBox.getSecurity_credential;
import static com.example.mpesaapi.settings.SandBox.getShort_code;
import static com.example.mpesaapi.settings.SandBox.getValidation_url;
import static com.ngangavictor.mpesa.api.Mpesa.b2bSimulation;
import static com.ngangavictor.mpesa.api.Mpesa.c2bSimulation;
import static com.ngangavictor.mpesa.api.Mpesa.sktPush;

public class MainActivity extends AppCompatActivity {
    Button buttonB2C,buttonB2B, buttonC2B,buttonTransStatus,buttonReversal,buttonLNMP,buttonLNMPQuery,buttonAccountBal;
    public static SQLiteDatabase database;
    public static DatabaseHelper databaseHelper;
    public static String checkoutRequestID;
    String url ="http://smartforex.co.ke/android/mpesa/listener.php";
    private UserLoginTask mAuthTask = null;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=new DatabaseHelper(this).getWritableDatabase();
        databaseHelper = new DatabaseHelper(this);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        LNMPSettings.setBusiness_short_code("174379");
        LNMPSettings.setCallback_url("http://smartforex.co.ke/android/mpesa/listener.php");
        LNMPSettings.setConsumer_key("AL4cs1jYio03B97Bvri5SWaPsQ1upawY");
        LNMPSettings.setConsumer_secret("tIO5wyY43Gobzt6C");
        //LNMPSettings.setPhone("254708374149");
        LNMPSettings.setPhone("254798467446");
        LNMPSettings.setStk_push_url("https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest");
        LNMPSettings.setTimeout_url("http://smartforex.co.ke/android/mpesa/timeout.php");
        LNMPSettings.setTransaction_desc("testing my api");
        LNMPSettings.setTransaction_type("CustomerPayBillOnline");
        LNMPSettings.setAmount("1");
        LNMPSettings.setAccess_token_url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials");
        LNMPSettings.setAccount_reference("vic10020");
        LNMPSettings.setPasskey("bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919");

        //c2b settings
        C2BSettings.setShortCode("602994");
        C2BSettings.setAmount("1");
        C2BSettings.setBillRef("vic2020");
        C2BSettings.setC2bUrl("https://sandbox.safaricom.co.ke/mpesa/c2b/v1/simulate");
        C2BSettings.setCommandId("CustomerPayBillOnline");
        C2BSettings.setMSISDN("254708374149");

        //b2b settings
        B2BSettings.setInitiator("testapi");
        B2BSettings.setAccountReference("Loan Payment");
        B2BSettings.setSecurityCredential("");
        B2BSettings.setCommandID("BusinessPayBill");
        B2BSettings.setSenderIdentifierType("4");
        B2BSettings.setReceiverIdentifierType("4");
        B2BSettings.setAmount("1");
        B2BSettings.setPartyA("600130");
        B2BSettings.setPartyB("60000");
        B2BSettings.setRemarks("Car loan payment for August");
        B2BSettings.setResultURL("");
        B2BSettings.setQueueTimeOutURL("");
        B2BSettings.setB2bUrl("https://sandbox.safaricom.co.ke/mpesa/b2b/v1/paymentrequest");


        buttonB2C = findViewById(R.id.btn_b2c);
        buttonB2B = findViewById(R.id.btn_b2b);
        buttonC2B = findViewById(R.id.btn_c2b);
        buttonTransStatus = findViewById(R.id.btn_trans_status);
        buttonReversal = findViewById(R.id.btn_reversal);
        buttonLNMP = findViewById(R.id.btn_lipa);
        buttonLNMPQuery = findViewById(R.id.btn_lipa_query);
        buttonAccountBal = findViewById(R.id.btn_acc_bal);

        try {
            registerURL(getShort_code(),"Confirmed",getConfirmation_url(),getValidation_url());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //b2c
        buttonB2C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    businessCustomer(getInitiator_name(),getSecurity_credential(),"SalaryPayment","10000",getShort_code(),"254708374149","Your salary",getQueue_timeout_url(),getResult_url());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //b2b
        buttonB2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    b2bSimulation();
                    //businessBusiness(getInitiator_name(),"August loan",getSecurity_credential(),"BusinessPayBill","4","4","10000",getShort_code(),"600000","Loan payment",getQueue_timeout_url(),getResult_url());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //c2b
        buttonC2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    c2bSimulation();
                    //customerBusiness(getShort_code(),"CustomerPayBillOnline","1000",getMSISDN(),"KAR345A");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //account balance
        buttonAccountBal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    accountBalance(getInitiator_name(),"AccountBalance",getSecurity_credential(),getShort_code(),"4","My balance",getQueue_timeout_url(),getResult_url());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //reversal
        buttonReversal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    reversal(getInitiator_name(),getSecurity_credential(),"","","","","",getResult_url(),getQueue_timeout_url(),"reverse past transaction");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //transaction status
        buttonTransStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    transactionStatus(getInitiator_name(),getSecurity_credential(),"","","","",getResult_url(),getQueue_timeout_url(),"Current transaction status");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //lina na mpesa online
        buttonLNMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (sktPush().equals("0")){
                        Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
                    }
//                    send();
//                    HashMap<String,String> params = new HashMap<>();
//                    params.put("phone", "254798467446");
//                    params.put("amount","1");
//
//                    mAuthTask=new UserLoginTask(url,params,200);
//                    mAuthTask.execute();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                try {
//                    Network.requests="stk";
//                    lipaNaMpesaOnline(getBusiness_shortcode(),generatePassword(),date,"CustomerPayBillOnline","100",getMSISDN(),getMSISDN(),getBusiness_shortcode(),getCallBack_url(),getQueue_timeout_url(),"KAR423A","Car hire payment");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        });

        //lipa na mpesa online query
        buttonLNMPQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,STKQueryActivity.class);
                startActivity(intent);
//                try {
//                    lipaNaMpesaOnlineQuery(getBusiness_shortcode(),"","","");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                // databaseHelper.insertSTKPush(password, date, checkoutRequestID);
            }
        });


    }

    private void send(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://smartforex.co.ke/android/mpesa/listener.php";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("MPESA RESPONSE",response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               error.printStackTrace();
            }
       }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String>  params = new HashMap<String, String>();
//                params.put("phone", "254798467446");
//                params.put("amount", "1");
//
//                return params;
//            }
        };

// Add the request to the RequestQueue.\
        //new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        0,
                        -1,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
        queue.start();
    }

    private class UserLoginTask extends AsyncTask<Void, Void, String> {
        String url;
        HashMap<String,String> params;
        int requestCode;
        UserLoginTask(String url,HashMap<String,String> params,int requestCode){
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(final String s) {
            super.onPostExecute(s);
            Log.d("ASYNC TASK",s);
        }

        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();
            if (requestCode == 200)
                return requestHandler.sendPostRequest(url,params);
            return null;
        }
    }


}
