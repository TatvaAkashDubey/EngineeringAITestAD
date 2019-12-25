package com.app.engineeringaitest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.engineeringaitest.R;
import com.app.engineeringaitest.response.User;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter {

    private ArrayList<User> mainList;
    private GridLayoutManager gridLayoutManager;

    public UserListAdapter(ArrayList<User> list) {
        mainList = list;
    }

    public void setUserList(ArrayList<User> list) {
        mainList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserLisViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserLisViewHolder userLisViewHolder = (UserLisViewHolder) holder;
        User user = mainList.get(position);
        userLisViewHolder.textView.setText(user.getName());
        Glide.with(userLisViewHolder.userImage.getRootView().getContext()).load(user.getImage()).into(userLisViewHolder.userImage);
        setUserImages(user.getItems(), userLisViewHolder.recyclerViewInner);
    }

    private void setUserImages(final ArrayList<String> items, RecyclerView recyclerViewInner) {
        if (items != null && items.size() != 0) {
            gridLayoutManager = new GridLayoutManager(recyclerViewInner.getContext(), 2);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (items.size() % 2 == 0) {
                        return 1;
                    } else if (position == 0) {
                        return 2;
                    } else return 1;
                }
            });
            GridAdapter gridAdapter = new GridAdapter();
            recyclerViewInner.setLayoutManager(gridLayoutManager);
            recyclerViewInner.setAdapter(gridAdapter);
            gridAdapter.setList(items);
        }
    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }

    private class UserLisViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView userImage;
        private RecyclerView recyclerViewInner;

        UserLisViewHolder(View view) {
            super(view);
            recyclerViewInner = view.findViewById(R.id.rvInnerImages);
            textView = view.findViewById(R.id.tvUserName);
            userImage = view.findViewById(R.id.ivUserImage);
        }
    }
}
