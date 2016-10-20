package me.cosmin.storemypassword.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.cosmin.storemypassword.R;
import me.cosmin.storemypassword.Views.CircleImageView;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ViewHolder> {

    private String[] mColors;
    private ColorClickListener colorListener;

    public void setColorSelectedListener(ColorClickListener listener) {
        colorListener = listener;
    }

    public interface ColorClickListener {
        void onColorSelected(String color);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView circle;
        public ViewHolder(View v) {
            super(v);
            circle = (CircleImageView) v.findViewById(R.id.circle);
        }
    }

    public ColorsAdapter(String[] colors) {
        mColors = colors;
    }

    @Override
    public ColorsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_edit_note_color_item, parent, false);
        ViewHolder vh = new ViewHolder(v);


        return vh;
    }

    @Override
    public void onBindViewHolder(ColorsAdapter.ViewHolder holder, int position) {
        final String color = mColors[position];
        holder.circle.changeBackgroundColor(color);
        holder.circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( colorListener != null ) {
                    colorListener.onColorSelected(color);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mColors.length;
    }
}
