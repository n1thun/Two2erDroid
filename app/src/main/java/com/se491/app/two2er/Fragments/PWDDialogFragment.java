package com.se491.app.two2er.Fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.se491.app.two2er.R;
import com.stormpath.sdk.Stormpath;
import com.stormpath.sdk.utils.StringUtils;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by eoliv on 3/9/2017.
 */

public class PWDDialogFragment extends DialogFragment {

    private Button changePassword;
    private EditText passwordInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_changewd_dialog, container, false);
        getDialog().setTitle("Change User Password");


        changePassword = (Button)rootView.findViewById(R.id.changeMyPwdDialog_btn);
        passwordInput = (EditText) rootView.findViewById(R.id.changeMY_passwordDialog);

        changePassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String newPassword = passwordInput.getText().toString();
                if(!Objects.equals(newPassword, "") & !newPassword.isEmpty()) {
                    changePassword();
                    dismiss();
                }
            }
        });

        return rootView;
    }

    private void changePassword(){
    String newPassword = passwordInput.getText().toString();

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .build();

    RequestBody requestBody = new FormBody.Builder()
            .add("password", newPassword)
            .build();

    Request request = new Request.Builder()
            .url("http://lowcost-env.niuk5squp9.us-east-2.elasticbeanstalk.com/apiauth/users/changepassword")
            .headers(buildStandardHeaders(Stormpath.getAccessToken()))
            .post(requestBody)
            .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
        @Override public
        void onFailure(Call call, IOException e) {
            Log.d("Android : ", e.getMessage());
        }

        @Override public void onResponse(Call call, Response response)
                    throws IOException {
            final String responseStr = response.body().string();
            Log.d("Android : ", responseStr);
        }
    });
}

    private Headers buildStandardHeaders(String accessToken) {
        Headers.Builder builder = new Headers.Builder();
        builder.add("Accept", "application/json");

        if (StringUtils.isNotBlank(accessToken)) {
            builder.add("Authorization", "Bearer " + accessToken);
        }

        return builder.build();
    }
}