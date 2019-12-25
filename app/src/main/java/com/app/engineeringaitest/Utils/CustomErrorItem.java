package com.app.engineeringaitest.Utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.app.engineeringaitest.R;

import ru.alexbykov.nopaginate.callback.OnRepeatListener;
import ru.alexbykov.nopaginate.item.ErrorItem;

public class CustomErrorItem implements ErrorItem {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_error, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, final OnRepeatListener repeatListener) {
        Button btnRepeat = (Button) holder.itemView.findViewById(R.id.btnRepeat);
        btnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeatListener != null) {
                    repeatListener.onClickRepeat(); //call onLoadMore
                }
            }
        });
    }
}