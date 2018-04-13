package com.spriteapp.booklibrary.model.response;

import java.io.Serializable;
import java.util.List;

public class BookDetailResponse implements Serializable {
    private String author_avatar;
    private int author_id;
    private String author_name;
    private int book_add_shelf;
    private int book_chapter_total;
    private long book_content_byte;
    private int book_finish_flag;
    private int book_id;
    private String book_image;
    private String book_intro;
    private int book_is_vip;
    private List<String> book_keywords;
    private String book_name;
    private int book_price;
    private String book_share_url;
    private int book_total_reads;
    private String book_total_score;
    private long book_updatetime;
    private int chapter_id;
    private int chapter_index;
    private int is_recommend_book;
    private int lastReadTime;
    private int last_update_book_datetime;
    private int last_update_chapter_datetime;
    private int last_update_chapter_id;
    private String last_update_chapter_intro;
    private String last_update_chapter_title;

    public int getBook_id() {
        return this.book_id;
    }

    public void setBook_id(int i) {
        this.book_id = i;
    }

    public String getBook_name() {
        return this.book_name;
    }

    public void setBook_name(String str) {
        this.book_name = str;
    }

    public String getBook_image() {
        return this.book_image;
    }

    public void setBook_image(String str) {
        this.book_image = str;
    }

    public String getBook_intro() {
        return this.book_intro;
    }

    public void setBook_intro(String str) {
        this.book_intro = str;
    }

    public String getBook_share_url() {
        return this.book_share_url;
    }

    public void setBook_share_url(String str) {
        this.book_share_url = str;
    }

    public String getBook_total_score() {
        return this.book_total_score;
    }

    public void setBook_total_score(String str) {
        this.book_total_score = str;
    }

    public int getBook_total_reads() {
        return this.book_total_reads;
    }

    public void setBook_total_reads(int i) {
        this.book_total_reads = i;
    }

    public int getBook_finish_flag() {
        return this.book_finish_flag;
    }

    public void setBook_finish_flag(int i) {
        this.book_finish_flag = i;
    }

    public int getBook_is_vip() {
        return this.book_is_vip;
    }

    public void setBook_is_vip(int i) {
        this.book_is_vip = i;
    }

    public long getBook_content_byte() {
        return this.book_content_byte;
    }

    public void setBook_content_byte(long j) {
        this.book_content_byte = j;
    }

    public int getBook_chapter_total() {
        return this.book_chapter_total;
    }

    public void setBook_chapter_total(int i) {
        this.book_chapter_total = i;
    }

    public List<String> getBook_keywords() {
        return this.book_keywords;
    }

    public void setBook_keywords(List<String> list) {
        this.book_keywords = list;
    }

    public int getBook_price() {
        return this.book_price;
    }

    public void setBook_price(int i) {
        this.book_price = i;
    }

    public long getBook_updatetime() {
        return this.book_updatetime;
    }

    public void setBook_updatetime(long j) {
        this.book_updatetime = j;
    }

    public int getAuthor_id() {
        return this.author_id;
    }

    public void setAuthor_id(int i) {
        this.author_id = i;
    }

    public String getAuthor_name() {
        return this.author_name;
    }

    public void setAuthor_name(String str) {
        this.author_name = str;
    }

    public String getAuthor_avatar() {
        return this.author_avatar;
    }

    public void setAuthor_avatar(String str) {
        this.author_avatar = str;
    }

    public int getLast_update_chapter_id() {
        return this.last_update_chapter_id;
    }

    public void setLast_update_chapter_id(int i) {
        this.last_update_chapter_id = i;
    }

    public int getLast_update_chapter_datetime() {
        return this.last_update_chapter_datetime;
    }

    public void setLast_update_chapter_datetime(int i) {
        this.last_update_chapter_datetime = i;
    }

    public String getLast_update_chapter_title() {
        return this.last_update_chapter_title;
    }

    public void setLast_update_chapter_title(String str) {
        this.last_update_chapter_title = str;
    }

    public String getLast_update_chapter_intro() {
        return this.last_update_chapter_intro;
    }

    public void setLast_update_chapter_intro(String str) {
        this.last_update_chapter_intro = str;
    }

    public int getChapter_id() {
        return this.chapter_id;
    }

    public void setChapter_id(int i) {
        this.chapter_id = i;
    }

    public int getLastReadTime() {
        return this.lastReadTime;
    }

    public void setLastReadTime(int i) {
        this.lastReadTime = i;
    }

    public int getLast_chapter_index() {
        return this.chapter_index;
    }

    public void setLast_chapter_index(int i) {
        this.chapter_index = i;
    }

    public int getLast_update_book_datetime() {
        return this.last_update_book_datetime;
    }

    public void setLast_update_book_datetime(int i) {
        this.last_update_book_datetime = i;
    }

    public int getBook_add_shelf() {
        return this.book_add_shelf;
    }

    public void setBook_add_shelf(int i) {
        this.book_add_shelf = i;
    }

    public int getIs_recommend_book() {
        return this.is_recommend_book;
    }

    public void setIs_recommend_book(int i) {
        this.is_recommend_book = i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BookDetailResponse)) {
            return super.equals(obj);
        }
        return this.book_id == ((BookDetailResponse) obj).getBook_id();
    }

    public int hashCode() {
        return String.valueOf(this.book_id).hashCode();
    }
}
