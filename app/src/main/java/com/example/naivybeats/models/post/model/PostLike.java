package com.example.naivybeats.models.post.model;

public class PostLike extends Post {
    int like;
    int chat;
    int follow;

    public PostLike() {
    }

    public PostLike(int postId, int userId, String postDate, String description, String multimedia, String title, int like, int chat, int follow) {
        super(postId, userId, postDate, description, multimedia, title);
        this.like = like;
        this.chat = chat;
        this.follow = follow;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getChat() {
        return chat;
    }

    public void setChat(int chat) {
        this.chat = chat;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }
}
