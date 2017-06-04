package com.minnehmugo.spotter.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minnehmugo.spotter.R;
import com.minnehmugo.spotter.models.Gym;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GymDetailFragment extends Fragment {
    @Bind(R.id.gymImageView) ImageView mImageLabel;
    @Bind(R.id.gymNameTextView) TextView mNameLabel;
    @Bind(R.id.categoryTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveGymButton) TextView mSaveGymButton;

    private Gym mGym;

    public static GymDetailFragment newInstance(Gym gym) {
        GymDetailFragment gymDetailFragment = new GymDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("gym", Parcels.wrap(gym));
        gymDetailFragment.setArguments(args);
        return gymDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGym = Parcels.unwrap(getArguments().getParcelable("gym"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gym_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mGym.getImageUrl()).into(mImageLabel);

        mNameLabel.setText(mGym.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mGym.getCategories()));
        mRatingLabel.setText(Double.toString(mGym.getRating()) + "/5");
        mPhoneLabel.setText(mGym.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mGym.getAddress()));

        return view;
    }
}
