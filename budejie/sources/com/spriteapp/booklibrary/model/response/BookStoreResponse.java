package com.spriteapp.booklibrary.model.response;

import com.spriteapp.booklibrary.model.store.BookTypeResponse;
import com.spriteapp.booklibrary.model.store.HotSellResponse;
import java.io.Serializable;
import java.util.List;

public class BookStoreResponse implements Serializable {
    private List<HotSellResponse> classes;
    private int code;
    private List<BookTypeResponse> lists;
    private String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public List<HotSellResponse> getClasses() {
        return this.classes;
    }

    public void setClasses(List<HotSellResponse> list) {
        this.classes = list;
    }

    public void setLists(List<BookTypeResponse> list) {
        this.lists = list;
    }

    public List<BookTypeResponse> getLists() {
        return this.lists;
    }
}
