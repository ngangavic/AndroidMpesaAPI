package com.example.mpesaapi.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.mpesaapi.R;
import com.example.mpesaapi.utils.Network;

import org.json.JSONException;

import java.io.IOException;

import static com.example.mpesaapi.mpesa.Mpesa.lipaNaMpesaOnline;
import static com.example.mpesaapi.settings.SandBox.getBusiness_shortcode;
import static com.example.mpesaapi.settings.SandBox.getCallBack_url;
import static com.example.mpesaapi.settings.SandBox.getMSISDN;
import static com.example.mpesaapi.settings.SandBox.getQueue_timeout_url;
import static com.example.mpesaapi.utils.GenerateValues.date;
import static com.example.mpesaapi.utils.GenerateValues.generatePassword;

public class STKPushDialog extends DialogFragment {
private Button buttonPay,buttonCancel;
private EditText editTextAmount,editTextPhone;
private Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_stk_push, container, false);
        editTextAmount=view.findViewById(R.id.editTextAmount);
        editTextPhone=view.findViewById(R.id.editTextPhone);
        buttonCancel=view.findViewById(R.id.buttonCancel);
        buttonPay=view.findViewById(R.id.buttonPay);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        buttonPay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextPhone.getText().toString())){
                    editTextPhone.requestFocus();
                    editTextPhone.setError("Cannot be empty");
                }else if (TextUtils.isEmpty(editTextAmount.getText().toString())){
                    editTextAmount.requestFocus();
                    editTextAmount.setError("Cannot be empty");
                }else if (phoneVerify(editTextPhone.getText().toString())){
                    Toast.makeText(getActivity(),"Correct",Toast.LENGTH_LONG).show();
                    try {
                    Network.requests="stk";
                    lipaNaMpesaOnline(getBusiness_shortcode(),generatePassword(),date,"CustomerPayBillOnline","100",editTextPhone.getText().toString(),editTextPhone.getText().toString(),getBusiness_shortcode(),getCallBack_url(),getQueue_timeout_url(),"KAR423A","Car hire payment");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                }else {
                    editTextPhone.requestFocus();
                    editTextPhone.setError("Invalid phone number");
                }

            }
        });



        return view;
    }

    private boolean phoneVerify(String phone){
        return phone.startsWith("+254");
    }

    private String editPhone(String phone){
        return phone.replace("+","");
    }

    private void showProgress(boolean show){
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        //dialog.setContentView(R.layout.custom_progress);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (show){
            dialog.show();
        }else{
            dialog.cancel();
        }
    }




}
