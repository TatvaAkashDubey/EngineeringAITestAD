
package com.app.engineeringaitest.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("has_more")
    private Boolean mHasMore;
    @SerializedName("users")
    private List<User> mUsers;

    public Boolean getHasMore() {
        return mHasMore;
    }

    public void setHasMore(Boolean hasMore) {
        mHasMore = hasMore;
    }

    public List<User> getUsers() {
        return mUsers;
    }

    public void setUsers(List<User> users) {
        mUsers = users;
    }

}
