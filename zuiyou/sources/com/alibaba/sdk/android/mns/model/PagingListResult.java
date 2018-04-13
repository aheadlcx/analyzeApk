package com.alibaba.sdk.android.mns.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PagingListResult<T> implements Serializable {
    private String marker;
    private List<T> result = new ArrayList();

    public String getMarker() {
        return this.marker;
    }

    public void setMarker(String str) {
        this.marker = str;
    }

    public List<T> getResult() {
        return this.result;
    }

    public void setResult(List<T> list) {
        this.result = list;
    }
}
