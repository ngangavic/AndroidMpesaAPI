package com.example.mpesaapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonB2C,buttonB2B, buttonC2B,buttonTransStatus,buttonReversal,buttonLNMP,buttonLNMPQuery,buttonAccountBal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonB2C = findViewById(R.id.btn_b2c);
        buttonB2B = findViewById(R.id.btn_b2b);
        buttonC2B = findViewById(R.id.btn_c2b);
        buttonTransStatus = findViewById(R.id.btn_trans_status);
        buttonReversal = findViewById(R.id.btn_reversal);
        buttonLNMP = findViewById(R.id.btn_lipa);
        buttonLNMPQuery = findViewById(R.id.btn_lipa_query);
        buttonAccountBal = findViewById(R.id.btn_acc_bal);
    }
}
