package com.budejie.www.activity.label.response;

import com.budejie.www.type.SearchItem;
import java.util.List;

public class UserBanResponse {
    private List<SearchItem> list;

    public List<SearchItem> getList() {
        return this.list;
    }

    public void setList(List<SearchItem> list) {
        this.list = list;
    }
}
