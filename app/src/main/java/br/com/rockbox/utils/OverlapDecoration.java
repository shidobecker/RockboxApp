package br.com.rockbox.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class OverlapDecoration extends RecyclerView.ItemDecoration {
    private final static int vertOverlap = -150;

    @Override
    public void getItemOffsets (Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, vertOverlap, 0, 0);
    }
}
