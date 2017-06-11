package com.minnehmugo.spotter.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

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
    @Override
    protected void populateViewHolder(final FirebaseGymViewHolder viewHolder, Gym model, int position) {
        viewHolder.bindGym(model);
        viewHolder.mGymImageView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}