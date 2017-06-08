package com.minnehmugo.spotter.ui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.minnehmugo.spotter.R;

import butterknife.Bind;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.loginTextView)
    TextView mLoginTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Typeface kaushanScript = Typeface.createFromAsset(getAssets(), "fonts/KaushanScript-Regular.otf");
        mLoginTextView.setTypeface(kaushanScript);

    }
}
