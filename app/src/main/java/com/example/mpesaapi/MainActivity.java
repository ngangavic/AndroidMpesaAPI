package com.example.mpesaapi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mpesaapi.database.DatabaseHelper;
import com.example.mpesaapi.utils.Network;

import org.json.JSONException;

import java.io.IOException;

import static com.example.mpesaapi.mpesa.Mpesa.accountBalance;
import static com.example.mpesaapi.mpesa.Mpesa.businessBusiness;
import static com.example.mpesaapi.mpesa.Mpesa.businessCustomer;
import static com.example.mpesaapi.mpesa.Mpesa.customerBusiness;
import static com.example.mpesaapi.mpesa.Mpesa.lipaNaMpesaOnline;
import static com.example.mpesaapi.mpesa.Mpesa.lipaNaMpesaOnlineQuery;
import static com.example.mpesaapi.mpesa.Mpesa.reversal;
import static com.example.mpesaapi.mpesa.Mpesa.transactionStatus;
import static com.example.mpesaapi.settings.SandBox.getBusiness_shortcode;
import static com.example.mpesaapi.settings.SandBox.getCallBack_url;
import static com.example.mpesaapi.settings.SandBox.getInitiator_name;
import static com.example.mpesaapi.settings.SandBox.getMSISDN;
import static com.example.mpesaapi.settings.SandBox.getQueue_timeout_url;
import static com.example.mpesaapi.settings.SandBox.getResult_url;
import static com.example.mpesaapi.settings.SandBox.getSecurity_credential;
import static com.example.mpesaapi.settings.SandBox.getShort_code;
import static com.example.mpesaapi.utils.GenerateValues.date;
import static com.example.mpesaapi.utils.GenerateValues.generatePassword;

public class MainActivity extends AppCompatActivity {
    Button buttonB2C,buttonB2B, buttonC2B,buttonTransStatus,buttonReversal,buttonLNMP,buttonLNMPQuery,buttonAccountBal;
    public static DatabaseHelper databaseHelper;
    SQLiteDatabase database;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=new DatabaseHelper(this).getWritableDatabase();
        //databaseHelper = new DatabaseHelper(this);
//        int SDK_INT = android.os.Build.VERSION.SDK_INT;
//        if (SDK_INT > 8) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//                    .permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
//
//        try {
//            registerURL(getShort_code(),"Confirmed",getConfirmation_url(),getValidation_url());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
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
                    businessBusiness(getInitiator_name(),"August loan",getSecurity_credential(),"BusinessPayBill","4","4","10000",getShort_code(),"600000","Loan payment",getQueue_timeout_url(),getResult_url());
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
                    customerBusiness(getShort_code(),"CustomerPayBillOnline","1000",getMSISDN(),"KAR345A");
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
                    Network.request="stk";
                    lipaNaMpesaOnline(getBusiness_shortcode(),generatePassword(),date,"CustomerPayBillOnline","100",getMSISDN(),getMSISDN(),getBusiness_shortcode(),getCallBack_url(),getQueue_timeout_url(),"KAR423A","Car hire payment");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //lipa na mpesa online query
        buttonLNMPQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    lipaNaMpesaOnlineQuery(getBusiness_shortcode(),"","","");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
