package com.minnehmugo.spotter.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minnehmugo.spotter.Constants;
import com.minnehmugo.spotter.R;
import com.minnehmugo.spotter.adapters.FirebaseGymListAdapter;
import com.minnehmugo.spotter.adapters.FirebaseGymViewHolder;
import com.minnehmugo.spotter.models.Gym;
import com.minnehmugo.spotter.util.OnStartDragListener;
import com.minnehmugo.spotter.util.SimpleItemTouchHelperCallback;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedGymListActivity extends AppCompatActivity implements OnStartDragListener {

    private DatabaseReference mGymReference;
    private FirebaseGymListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gyms);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mGymReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_GYMS)
                .child(uid);

        mFirebaseAdapter = new FirebaseGymListAdapter(Gym.class,
                R.layout.gym_list_item_drag, FirebaseGymViewHolder.class,
                mGymReference, this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}