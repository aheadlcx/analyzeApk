package com.budejie.www.type;

import java.util.List;

public class SearchResult {
    private String code;
    private List<SearchItem> list;
    private String more;
    private String msg;
    private long total;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long j) {
        this.total = j;
    }

    public String getMore() {
        return this.more;
    }

    public void setMore(String str) {
        this.more = str;
    }

    public List<SearchItem> getList() {
        return this.list;
    }

    public void setList(List<SearchItem> list) {
        this.list = list;
    }
}
