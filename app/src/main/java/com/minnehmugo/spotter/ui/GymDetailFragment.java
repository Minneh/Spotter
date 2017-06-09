package com.minnehmugo.spotter.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minnehmugo.spotter.Constants;
import com.minnehmugo.spotter.R;
import com.minnehmugo.spotter.models.Gym;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GymDetailFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.gymImageView) ImageView mImageLabel;
    @Bind(R.id.gymNameTextView) TextView mNameLabel;
    @Bind(R.id.categoryTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveGymButton) TextView mSaveGymButton;

    private Gym mGym;
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

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

        Picasso.with(view.getContext())
                .load(mGym.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        mNameLabel.setText(mGym.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mGym.getCategories()));
        mRatingLabel.setText(Double.toString(mGym.getRating()) + "/5");
        mPhoneLabel.setText(mGym.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mGym.getAddress()));

        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);
        mSaveGymButton.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mGym.getWebsite()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mGym.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mGym.getLatitude()
                            + "," + mGym.getLongitude()
                            + "?q=(" + mGym.getName() + ")"));
            startActivity(mapIntent);
        }
        if (v == mSaveGymButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference restaurantRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_GYMS)
                    .child(uid);
            DatabaseReference pushRef = restaurantRef.push();
            String pushId = pushRef.getKey();
            mGym.setPushId(pushId);
            pushRef.setValue(mGym);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
