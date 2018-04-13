package com.spriteapp.booklibrary.model.response;

import java.io.Serializable;

public class CommentResponse implements Serializable {
    private int book_id;
    private int chapter_id;
    private String comment_content;
    private long comment_datetime;
    private int comment_id;
    private int comment_parent_id;
    private String ip_address;
    private String user_avatar;
    private String user_id;
    private String user_nickname;
    private int volume_id;

    public int getComment_id() {
        return this.comment_id;
    }

    public void setComment_id(int i) {
        this.comment_id = i;
    }

    public String getComment_content() {
        return this.comment_content;
    }

    public void setComment_content(String str) {
        this.comment_content = str;
    }

    public long getComment_datetime() {
        return this.comment_datetime;
    }

    public void setComment_datetime(long j) {
        this.comment_datetime = j;
    }

    public int getComment_parent_id() {
        return this.comment_parent_id;
    }

    public void setComment_parent_id(int i) {
        this.comment_parent_id = i;
    }

    public String getUser_avatar() {
        return this.user_avatar;
    }

    public void setUser_avatar(String str) {
        this.user_avatar = str;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String str) {
        this.user_id = str;
    }

    public String getUser_nickname() {
        return this.user_nickname;
    }

    public void setUser_nickname(String str) {
        this.user_nickname = str;
    }

    public int getBook_id() {
        return this.book_id;
    }

    public void setBook_id(int i) {
        this.book_id = i;
    }

    public int getVolume_id() {
        return this.volume_id;
    }

    public void setVolume_id(int i) {
        this.volume_id = i;
    }

    public int getChapter_id() {
        return this.chapter_id;
    }

    public void setChapter_id(int i) {
        this.chapter_id = i;
    }

    public String getIp_address() {
        return this.ip_address;
    }

    public void setIp_address(String str) {
        this.ip_address = str;
    }
}
