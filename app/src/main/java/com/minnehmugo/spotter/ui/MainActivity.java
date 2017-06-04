package com.minnehmugo.spotter.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.minnehmugo.spotter.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findGymsButton) Button mFindGymsButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface kaushanScript = Typeface.createFromAsset(getAssets(), "fonts/KaushanScript-Regular.otf");
        mAppNameTextView.setTypeface(kaushanScript);

        mFindGymsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mFindGymsButton) {
            String location = mLocationEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, GymListActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
    }
}
