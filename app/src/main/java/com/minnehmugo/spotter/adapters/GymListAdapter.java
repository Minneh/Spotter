package com.minnehmugo.spotter.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minnehmugo.spotter.R;
import com.minnehmugo.spotter.models.Gym;
import com.minnehmugo.spotter.ui.GymDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by minnehmugo on 04/06/2017.
 */

public class GymListAdapter extends RecyclerView.Adapter<GymListAdapter.GymViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    private ArrayList<Gym> mGyms = new ArrayList<>();
    private Context mContext;

    public GymListAdapter(Context context, ArrayList<Gym> gyms) {
        mContext = context;
        mGyms = gyms;
    }

    @Override
    public GymListAdapter.GymViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gym_list_item, parent, false);
        GymViewHolder viewHolder = new GymViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GymListAdapter.GymViewHolder holder, int position) {
        holder.bindGym(mGyms.get(position));
    }

    @Override
    public int getItemCount() {
        return mGyms.size();
    }

    public class GymViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.gymImageView)
        ImageView mGymImageView;
        @Bind(R.id.gymNameTextView)
        TextView mNameTextView;
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;

        public GymViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindGym(Gym gym) {
            Picasso.with(mContext)
                    .load(gym.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mGymImageView);
            mNameTextView.setText(gym.getName());
            mCategoryTextView.setText(gym.getCategories().get(0));
            mRatingTextView.setText("Rating: " + gym.getRating() + "/5");
        }

        @Override
        public void onClick(View v) {
            Log.d("click listener", "working!");
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, GymDetailActivity.class);
            intent.putExtra("position", itemPosition + "");
            intent.putExtra("gyms", Parcels.wrap(mGyms));
            mContext.startActivity(intent);
        }
    }

}
