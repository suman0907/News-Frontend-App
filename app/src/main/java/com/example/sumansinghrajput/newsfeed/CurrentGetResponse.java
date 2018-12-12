package com.example.sumansinghrajput.newsfeed;

import java.util.List;

/**
 * Created by Suman Singh Rajput on 27-04-2018.
 */

public class CurrentGetResponse {
    private List<TopNewsModel> data;

    public List<TopNewsModel> getData() {
        return data;
    }

    public void setData(List<TopNewsModel> data) {
        this.data = data;
    }
}
