package com.example.mpesaapi;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.ngangavictor.mpesa.api.AccountBalance;
import com.ngangavictor.mpesa.api.B2BSettings;
import com.ngangavictor.mpesa.api.B2CSettings;
import com.ngangavictor.mpesa.api.C2BSettings;
import com.ngangavictor.mpesa.api.LNMPQuerySettings;
import com.ngangavictor.mpesa.api.LNMPSettings;
import com.ngangavictor.mpesa.api.Mpesa;

import org.json.JSONException;

import java.io.IOException;

import static com.ngangavictor.mpesa.api.Mpesa.b2bSimulation;
import static com.ngangavictor.mpesa.api.Mpesa.b2cSimulation;
import static com.ngangavictor.mpesa.api.Mpesa.c2bSimulation;
import static com.ngangavictor.mpesa.api.Mpesa.sktPush;
import static com.ngangavictor.mpesa.api.Mpesa.stkPushQuery;

public class MainActivity extends AppCompatActivity {
    Button buttonB2C, buttonB2B, buttonC2B, buttonTransStatus, buttonReversal, buttonLNMP, buttonLNMPQuery, buttonAccountBal;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        B2BSettings.setInitiator("apiop50");
        B2BSettings.setAccountReference("Loan Payment");
        B2BSettings.setSecurityCredential("EgTdE5XjHYBbNMPGIZKrpQq99hEKTE/P4EfC/SmNIjvfttGV/jf7/JBVto4DfU5CWOhXOPJVGr5W9QIiO8aXLmIbYzbnKX0VvdTvzWcZLLZTXi/qbRCADyHYIKb2yIDu+zIPJ28RFtzYtFLLyySTBXvIrOYLExuLLcsJNuJpGPJ2GZ++dEtuZxQGf+OVfzi5AGAmfzwasIS9G+fhYQE1aAPjVkzBJKelP4jO2rTxQa/AU9oN859gcGqViaW70dEhSIeh73rDCKGz2zirF5hqNc76KcxKpt/rO2Q+pgEJsIAdfjDbCCZ6hTvjhQm5nPQgqI5WViH6jiPRTeC6dt2pgA==");
        B2BSettings.setCommandID("BusinessPayBill");
        B2BSettings.setSenderIdentifierType("4");
        B2BSettings.setReceiverIdentifierType("3");
        B2BSettings.setAmount("1");
        B2BSettings.setPartyA("602994");
        B2BSettings.setPartyB("600000");
        B2BSettings.setRemarks("Car loan payment for August");
        B2BSettings.setResultURL("https://mytut.com");
        B2BSettings.setQueueTimeOutURL("https://mytut.com");
        B2BSettings.setB2bUrl("https://sandbox.safaricom.co.ke/mpesa/b2b/v1/paymentrequest");

        //b2c settings
        B2CSettings.setAmount("1");
        B2CSettings.setB2cUrl("https://sandbox.safaricom.co.ke/mpesa/b2c/v1/paymentrequest");
        B2CSettings.setCommandId("SalaryPayment");
        B2CSettings.setInitiatorName("apiop50");
        B2CSettings.setPartyA("602994");
        B2CSettings.setPartyB("254708374149");
        B2CSettings.setRemarks("December 2019 salary");
        B2CSettings.setResultUrl("https://www.result.com");
        B2CSettings.setSecurityCredential("EgTdE5XjHYBbNMPGIZKrpQq99hEKTE/P4EfC/SmNIjvfttGV/jf7/JBVto4DfU5CWOhXOPJVGr5W9QIiO8aXLmIbYzbnKX0VvdTvzWcZLLZTXi/qbRCADyHYIKb2yIDu+zIPJ28RFtzYtFLLyySTBXvIrOYLExuLLcsJNuJpGPJ2GZ++dEtuZxQGf+OVfzi5AGAmfzwasIS9G+fhYQE1aAPjVkzBJKelP4jO2rTxQa/AU9oN859gcGqViaW70dEhSIeh73rDCKGz2zirF5hqNc76KcxKpt/rO2Q+pgEJsIAdfjDbCCZ6hTvjhQm5nPQgqI5WViH6jiPRTeC6dt2pgA==");
        B2CSettings.setTimeOutUrl("https://www.timeout.com");

        //account balance settings
        AccountBalance.setAccountBalanceUrl("https://sandbox.safaricom.co.ke/mpesa/accountbalance/v1/query");
        AccountBalance.setAmount("1");
        AccountBalance.setCommandId("AccountBalance");
        AccountBalance.setIdentifierType("4");
        AccountBalance.setInitiatorName("apiop50");
        AccountBalance.setPartyA("602994");
        AccountBalance.setRemarks("Check account balance");
        AccountBalance.setResultUrl("https://wwww.result.com");
        AccountBalance.setSecurityCredential("EgTdE5XjHYBbNMPGIZKrpQq99hEKTE/P4EfC/SmNIjvfttGV/jf7/JBVto4DfU5CWOhXOPJVGr5W9QIiO8aXLmIbYzbnKX0VvdTvzWcZLLZTXi/qbRCADyHYIKb2yIDu+zIPJ28RFtzYtFLLyySTBXvIrOYLExuLLcsJNuJpGPJ2GZ++dEtuZxQGf+OVfzi5AGAmfzwasIS9G+fhYQE1aAPjVkzBJKelP4jO2rTxQa/AU9oN859gcGqViaW70dEhSIeh73rDCKGz2zirF5hqNc76KcxKpt/rO2Q+pgEJsIAdfjDbCCZ6hTvjhQm5nPQgqI5WViH6jiPRTeC6dt2pgA==");
        AccountBalance.setTimeOutUrl("https://www.timeout.com");

        //lipa na mpesa query
        LNMPQuerySettings.setBusiness_short_code("");
        LNMPQuerySettings.setCheckout_request_id("");
        LNMPQuerySettings.setPassword("");
        LNMPQuerySettings.setStk_push_query_url("");
        LNMPQuerySettings.setTimestamp("");


        buttonB2C = findViewById(R.id.btn_b2c);
        buttonB2B = findViewById(R.id.btn_b2b);
        buttonC2B = findViewById(R.id.btn_c2b);
        buttonTransStatus = findViewById(R.id.btn_trans_status);
        buttonReversal = findViewById(R.id.btn_reversal);
        buttonLNMP = findViewById(R.id.btn_lipa);
        buttonLNMPQuery = findViewById(R.id.btn_lipa_query);
        buttonAccountBal = findViewById(R.id.btn_acc_bal);

        //b2c
        buttonB2C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    b2cSimulation();
                    // businessCustomer(getInitiator_name(),getSecurity_credential(),"SalaryPayment","10000",getShort_code(),"254708374149","Your salary",getQueue_timeout_url(),getResult_url());
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
                    Mpesa.accountBalance();
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

            }
        });

        //transaction status
        buttonTransStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //lina na mpesa online
        buttonLNMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (sktPush().equals("0")) {
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //lipa na mpesa online query
        buttonLNMPQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    stkPushQuery();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }


}
