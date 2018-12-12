package com.example.sumansinghrajput.newsfeed;

/**
 * Created by Suman Singh Rajput on 27-04-2018.
 */

public class TopNewsModel {

    private String title;
    private String description;
    private String image_url;
    private String url;

    public TopNewsModel() {

        this.title = null;
        this.description = null;
        this.image_url = null;

         this.url = null;

    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
