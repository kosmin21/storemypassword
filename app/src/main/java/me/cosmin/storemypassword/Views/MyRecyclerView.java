package me.cosmin.storemypassword.Views;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import me.cosmin.storemypassword.R;

public class MyRecyclerView extends RecyclerView {

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addGridLayout(GridLayoutManager gridLayoutManager) {
        this.setLayoutManager(gridLayoutManager);
    }

    public void addItemSpacing() {
        int space = (int) getResources().getDimension(R.dimen.recycler_view_spacing);
        this.addItemDecoration(new VerticalSpaceItemDecoration(space));
    }
}
