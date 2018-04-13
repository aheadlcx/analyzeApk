package com.spriteapp.booklibrary.model.store;

import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import java.util.List;

public class BookTypeResponse {
    private int count;
    private List<BookDetailResponse> lists;
    private String name;
    private String url;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public List<BookDetailResponse> getLists() {
        return this.lists;
    }

    public void setLists(List<BookDetailResponse> list) {
        this.lists = list;
    }
}
