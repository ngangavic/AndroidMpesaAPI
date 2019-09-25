package com.example.mpesaapi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;

import java.io.IOException;

import static com.example.mpesaapi.mpesa.Mpesa.businessCustomer;
import static com.example.mpesaapi.mpesa.Mpesa.registerURL;
import static com.example.mpesaapi.settings.SandBox.getConfirmation_url;
import static com.example.mpesaapi.settings.SandBox.getInitiator_name;
import static com.example.mpesaapi.settings.SandBox.getQueue_timeout_url;
import static com.example.mpesaapi.settings.SandBox.getResult_url;
import static com.example.mpesaapi.settings.SandBox.getSecurity_credential;
import static com.example.mpesaapi.settings.SandBox.getShort_code;
import static com.example.mpesaapi.settings.SandBox.getValidation_url;

public class MainActivity extends AppCompatActivity {
    Button buttonB2C,buttonB2B, buttonC2B,buttonTransStatus,buttonReversal,buttonLNMP,buttonLNMPQuery,buttonAccountBal;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            registerURL(getShort_code(),"Confirmed",getConfirmation_url(),getValidation_url());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        buttonB2C = findViewById(R.id.btn_b2c);
        buttonB2B = findViewById(R.id.btn_b2b);
        buttonC2B = findViewById(R.id.btn_c2b);
        buttonTransStatus = findViewById(R.id.btn_trans_status);
        buttonReversal = findViewById(R.id.btn_reversal);
        buttonLNMP = findViewById(R.id.btn_lipa);
        buttonLNMPQuery = findViewById(R.id.btn_lipa_query);
        buttonAccountBal = findViewById(R.id.btn_acc_bal);

        buttonB2C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    businessCustomer(getInitiator_name(),getSecurity_credential(),"SalaryPayment","10000",getShort_code(),"254700352822","Your salary",getQueue_timeout_url(),getResult_url());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
