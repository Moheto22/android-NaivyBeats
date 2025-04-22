package com.example.naivybeats.models.offer.models;

public class PostOffer {
    public int user_id;
    public int post_id;

    public PostOffer() {
    }

    public PostOffer(int user_id, int post_id) {
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }
}
