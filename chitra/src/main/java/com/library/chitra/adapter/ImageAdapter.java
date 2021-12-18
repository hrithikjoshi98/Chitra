package com.library.chitra.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.library.chitra.Bekommen;
import com.library.chitra.R;
import com.library.chitra.model.ImageSelected;
import com.library.chitra.bildbekommen.Bildbekommen;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private Context context;
    public ArrayList<ImageSelected> previewActivities;
    public int final_limit;

    public ImageAdapter() {
    }

    public ImageAdapter(Context context, ArrayList<ImageSelected> previewActivities, int final_limit) {
        this.context = context;
        this.previewActivities = previewActivities;
        this.final_limit = final_limit;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_images_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final ImageSelected model = previewActivities.get(holder.getAdapterPosition());
        initState(Bildbekommen.main_list.contains(previewActivities.get(position).getPath()), holder);

        holder.iv_preview_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context, R.style.Alt_AppTheme);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().clearFlags(
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                dialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent);
                dialog.setContentView(R.layout.image_preview_dialoage);
                PhotoView photoView = dialog.findViewById(R.id.iv_preview_image);
                Glide.with(context).load(previewActivities.get(position).getPath()).into(photoView);
                //photoView.setImageURI(Uri.parse(previewActivities.get(position).getPath()));
                dialog.show();
            }
        });
        holder.ll_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCheckStateChange(holder, previewActivities.get(position).getPath());
            }
        });
        Glide.with(context).load(model.path).into(holder.iv_preview_image);
    }

    @Override
    public int getItemCount() {
        return previewActivities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //public TextView tv_title;
        public ImageView iv_preview_image, thumbnail;
        public LinearLayout ll_checked;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //tv_title = itemView.findViewById(R.id.tv_title);
            iv_preview_image = itemView.findViewById(R.id.iv_album_preview_image);
            ll_checked = itemView.findViewById(R.id.ll_album_checked);
            thumbnail = itemView.findViewById(R.id.album_thumbnail);
        }
    }


    private void onCheckStateChange(ViewHolder holder, String image) {
        boolean isContained = Bildbekommen.main_list.contains(image);
        if (isContained) {
            Bildbekommen.main_list.remove(image);
            updateRadioButton(false, holder);
            ((Bekommen) context).unSelect();
        } else {
            if (Bildbekommen.limitselected == true) {
                if (final_limit == Bildbekommen.main_list.size()) {
                    Toast.makeText(context, "Limit is " + final_limit, Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Bildbekommen.main_list.add(image);
                    updateRadioButton(true, holder);
                    ((Bekommen) context).selecte();
                }
            }else{
                Bildbekommen.main_list.add(image);
                updateRadioButton(true, holder);
                ((Bekommen)context).selecte();
            }
        }
    }

    private void initState(boolean selectedIndex, ViewHolder holder) {
        updateRadioButton(selectedIndex == true, holder);
    }

    public void updateRadioButton(boolean selectedIndex, ViewHolder holder) {
        if (selectedIndex == false) {
            holder.thumbnail.setImageResource(R.drawable.ic_radio_button_unchecked);
        } else {
            holder.thumbnail.setImageResource(R.drawable.ic_correct);
        }
    }
}
