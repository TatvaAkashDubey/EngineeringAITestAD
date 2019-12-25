package com.app.engineeringaitest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.app.engineeringaitest.adapter.UserListAdapter;
import com.app.engineeringaitest.network.RetrofitApiInterface;
import com.app.engineeringaitest.response.User;
import com.app.engineeringaitest.response.UserResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.alexbykov.nopaginate.callback.OnLoadMoreListener;
import ru.alexbykov.nopaginate.paginate.NoPaginate;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipToRefresh;
    private UserListAdapter userListAdapter;
    private ArrayList<User> userArrayList = new ArrayList<>();
    private int offset = 1;
    private NoPaginate noPaginate;
    private RecyclerView recyclerView;
    private FrameLayout errorItem;
    private Button btnRepeat;
    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        getUserList();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.rvUsers);
        errorItem = findViewById(R.id.errorItem);
        btnRepeat = findViewById(R.id.btnRepeat);
        tvError = findViewById(R.id.tvError);
        swipToRefresh = findViewById(R.id.swipToRefresh);
        userListAdapter = new UserListAdapter(userArrayList);
        recyclerView.setAdapter(userListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        swipToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doRefresh();
            }
        });
        btnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRefresh();
            }
        });

        noPaginate = NoPaginate.with(recyclerView)
                .setOnLoadMoreListener(new OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        getUserList();
                    }
                })
                .build();
    }

    private void doRefresh() {
        offset = 1;
        swipToRefresh.setRefreshing(true);
        getUserList();
    }

    private void getUserList() {
        noPaginate.showLoading(true);
        Call<UserResponse> call = RetrofitApiInterface.getRestApiMethods().getUserData(offset, 10);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NotNull Call<UserResponse> call, @NotNull Response<UserResponse> response) {
                noPaginate.showLoading(false);
                if (swipToRefresh.isRefreshing()) {
                    swipToRefresh.setRefreshing(false);
                }
                if (response.isSuccessful()) {
                    if (offset == 1) {
                        userArrayList.clear();
                        userArrayList = new ArrayList<>();
                    }
                    if (response.body() != null) {
                        userArrayList.addAll(response.body().getData().getUsers());
                    }
                    userListAdapter.setUserList(userArrayList);
                    offset++;
                } else {
                    tvError.setText(response.message());
                    noPaginate.showLoading(false);
                    if (swipToRefresh.isRefreshing()) {
                        swipToRefresh.setRefreshing(false);
                    }
                }
                if (userArrayList.size() == 0) {
                    errorItem.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    errorItem.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserResponse> call, @NotNull Throwable t) {
                errorItem.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                noPaginate.showLoading(false);
                tvError.setText(t.getLocalizedMessage());
                if (swipToRefresh.isRefreshing()) {
                    swipToRefresh.setRefreshing(false);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noPaginate.unbind();
    }
}
