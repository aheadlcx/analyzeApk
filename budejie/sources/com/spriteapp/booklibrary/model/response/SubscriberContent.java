package com.spriteapp.booklibrary.model.response;

import java.io.Serializable;

public class SubscriberContent implements Serializable {
    public int SQLiteId;
    private int auto_sub;
    private int book_id;
    private String chapter_content;
    private long chapter_content_byte;
    private String chapter_content_key;
    private int chapter_id;
    private String chapter_intro;
    private int chapter_is_vip;
    private int chapter_need_buy;
    private int chapter_pay_type;
    private int chapter_price;
    private String chapter_title;
    private int used_false_point;
    private int used_real_point;

    public int getAuto_sub() {
        return this.auto_sub;
    }

    public void setAuto_sub(int i) {
        this.auto_sub = i;
    }

    public int getBook_id() {
        return this.book_id;
    }

    public void setBook_id(int i) {
        this.book_id = i;
    }

    public String getChapter_title() {
        return this.chapter_title;
    }

    public void setChapter_title(String str) {
        this.chapter_title = str;
    }

    public String getChapter_intro() {
        return this.chapter_intro;
    }

    public void setChapter_intro(String str) {
        this.chapter_intro = str;
    }

    public int getChapter_id() {
        return this.chapter_id;
    }

    public void setChapter_id(int i) {
        this.chapter_id = i;
    }

    public int getChapter_price() {
        return this.chapter_price;
    }

    public void setChapter_price(int i) {
        this.chapter_price = i;
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

    public String getChapter_content_key() {
        return this.chapter_content_key;
    }

    public void setChapter_content_key(String str) {
        this.chapter_content_key = str;
    }

    public String getChapter_content() {
        return this.chapter_content;
    }

    public void setChapter_content(String str) {
        this.chapter_content = str;
    }

    public int getUsed_real_point() {
        return this.used_real_point;
    }

    public void setUsed_real_point(int i) {
        this.used_real_point = i;
    }

    public int getUsed_false_point() {
        return this.used_false_point;
    }

    public void setUsed_false_point(int i) {
        this.used_false_point = i;
    }

    public int getChapter_need_buy() {
        return this.chapter_need_buy;
    }

    public void setChapter_need_buy(int i) {
        this.chapter_need_buy = i;
    }

    public int getChapter_pay_type() {
        return this.chapter_pay_type;
    }

    public void setChapter_pay_type(int i) {
        this.chapter_pay_type = i;
    }
}
