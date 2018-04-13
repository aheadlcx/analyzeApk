package com.spriteapp.booklibrary.model.response;

import java.io.Serializable;

public class BookChapterResponse implements Serializable {
    private int bookId;
    private int chapterReadState;
    private long chapter_content_byte;
    private int chapter_id;
    private int chapter_is_sub;
    private int chapter_is_vip;
    private int chapter_order;
    private int chapter_price;
    private String chapter_title;

    public int getChapter_id() {
        return this.chapter_id;
    }

    public void setChapter_id(int i) {
        this.chapter_id = i;
    }

    public String getChapter_title() {
        return this.chapter_title;
    }

    public void setChapter_title(String str) {
        this.chapter_title = str;
    }

    public int getChapter_order() {
        return this.chapter_order;
    }

    public void setChapter_order(int i) {
        this.chapter_order = i;
    }

    public int getChapter_is_vip() {
        return this.chapter_is_vip;
    }

    public void setChapter_is_vip(int i) {
        this.chapter_is_vip = i;
    }

    public long getChapter_content_byte() {
        return this.chapter_content_byte;
    }

    public void setChapter_content_byte(long j) {
        this.chapter_content_byte = j;
    }

    public int getChapter_is_sub() {
        return this.chapter_is_sub;
    }

    public void setChapter_is_sub(int i) {
        this.chapter_is_sub = i;
    }

    public int getChapter_price() {
        return this.chapter_price;
    }

    public void setChapter_price(int i) {
        this.chapter_price = i;
    }

    public int getBookId() {
        return this.bookId;
    }

    public void setBookId(int i) {
        this.bookId = i;
    }

    public int getChapterReadState() {
        return this.chapterReadState;
    }

    public void setChapterReadState(int i) {
        this.chapterReadState = i;
    }
}
