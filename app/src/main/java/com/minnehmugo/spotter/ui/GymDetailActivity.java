package com.minnehmugo.spotter.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.minnehmugo.spotter.R;
import com.minnehmugo.spotter.models.Gym;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GymDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    private GymPagerAdapter adapterViewPager;
    ArrayList<Gym> mGyms = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_detail);
        ButterKnife.bind(this);

        mGyms = Parcels.unwrap(getIntent().getParcelableExtra("gyms"));

        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));

        adapterViewPager = new GymPagerAdapter(getSupportFragmentManager(), mGyms);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
