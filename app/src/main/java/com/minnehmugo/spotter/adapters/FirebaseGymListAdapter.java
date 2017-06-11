package com.minnehmugo.spotter.adapters;

import android.content.Context;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.minnehmugo.spotter.models.Gym;
import com.minnehmugo.spotter.util.ItemTouchHelperAdapter;
import com.minnehmugo.spotter.util.OnStartDragListener;

/**
 * Created by minnehmugo on 11/06/2017.
 */

public class FirebaseGymListAdapter extends FirebaseRecyclerAdapter<Gym, FirebaseGymViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseGymListAdapter(Class<Gym> modelClass, int modelLayout,
                                  Class<FirebaseGymViewHolder> viewHolderClass,
                                  Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }
}