package com.example.matthew.mgreen_listwithratings_s18;

public class ListItem {

    String title;
    float rating = 0.0f;
    String comment = "";

    ListItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
