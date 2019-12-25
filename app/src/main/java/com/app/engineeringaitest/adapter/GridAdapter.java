package com.app.engineeringaitest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.engineeringaitest.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GridAdapter extends RecyclerView.Adapter {

    private ArrayList<String> mainList;

    GridAdapter() {
    }

    public void setList(ArrayList<String> list) {
        this.mainList = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemImages(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_images, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemImages userLisViewHolder = (ItemImages) holder;
        Glide.with(userLisViewHolder.userImage.getRootView().getContext()).load(
                mainList.get(position)
        ).into(userLisViewHolder.userImage);
    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }

    private class ItemImages extends RecyclerView.ViewHolder {
        private ImageView userImage;

        ItemImages(View view) {
            super(view);
            userImage = view.findViewById(R.id.ivImage);
        }
    }
}
