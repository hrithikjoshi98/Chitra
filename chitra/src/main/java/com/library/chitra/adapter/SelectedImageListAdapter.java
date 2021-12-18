package com.library.chitra.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.library.chitra.Bekommen;
import com.library.chitra.R;
import com.library.chitra.bildbekommen.Bildbekommen;

import java.util.ArrayList;

public class SelectedImageListAdapter extends RecyclerView.Adapter<SelectedImageListAdapter.ViewHolder>{
    private Context context;
    private ArrayList<String> list;

    public SelectedImageListAdapter() {
    }

    public SelectedImageListAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.selected_image_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Glide.with(context).load(list.get(position)).into(holder.iv_artwork);
        holder.checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Bildbekommen.main_list.size() != 0) {
                    if (Bildbekommen.main_list.contains(list.get(position))) {
                        Bildbekommen.main_list.remove(list.get(position));
                        ((Bekommen)context).deleteImage(position);
                        notifyDataSetChanged();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout checked;
        private final ImageView iv_artwork;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_artwork = itemView.findViewById(R.id.iv_select_artwork);
            checked = itemView.findViewById(R.id.select_checked);
        }
    }
}