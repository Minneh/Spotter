package com.minnehmugo.spotter.util;

/**
 * Created by minnehmugo on 11/06/2017.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
