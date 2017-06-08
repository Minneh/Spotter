package com.minnehmugo.spotter.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minnehmugo.spotter.Constants;
import com.minnehmugo.spotter.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedLocationReference;

    @Bind(R.id.findGymsButton) Button mFindGymsButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.findGymTextView) TextView mFindGymTextView;
    @Bind(R.id.savedGymsButton) Button mSavedGymsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        Typeface kaushanScript = Typeface.createFromAsset(getAssets(), "fonts/KaushanScript-Regular.otf");
        mAppNameTextView.setTypeface(kaushanScript);
        mFindGymTextView.setTypeface(kaushanScript);

        mFindGymsButton.setOnClickListener(this);
        mSavedGymsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mFindGymsButton) {
            String location = mLocationEditText.getText().toString();
            saveLocationToFirebase(location);

            Intent intent = new Intent(MainActivity.this, GymListActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
        if (v == mSavedGymsButton) {
            Intent intent = new Intent(MainActivity.this, SavedGymListActivity.class);
            startActivity(intent);
        }
    }

    public void saveLocationToFirebase(String location) {
        mSearchedLocationReference.setValue(location);
    }
}

