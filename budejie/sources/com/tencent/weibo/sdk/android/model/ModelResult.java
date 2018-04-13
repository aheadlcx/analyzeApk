package com.tencent.weibo.sdk.android.model;

import java.util.List;

public class ModelResult {
    private String error_message = "success";
    private boolean isExpires = false;
    private boolean isLastPage;
    private String lat;
    private List<BaseVO> list;
    private String lon;
    private Object obj;
    private int p;
    private int ps;
    private boolean success = true;
    private int total;

    public boolean isExpires() {
        return this.isExpires;
    }

    public void setExpires(boolean z) {
        this.isExpires = z;
    }

    public boolean isLastPage() {
        return this.isLastPage;
    }

    public void setLastPage(boolean z) {
        this.isLastPage = z;
    }

    public Object getObj() {
        return this.obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public void add(BaseVO baseVO) {
        this.list.add(baseVO);
    }

    public BaseVO get() {
        return (BaseVO) this.list.get(0);
    }

    public String getLon() {
        return this.lon;
    }

    public void setLon(String str) {
        this.lon = str;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String str) {
        this.lat = str;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    public int getP() {
        return this.p;
    }

    public void setP(int i) {
        this.p = i;
    }

    public int getPs() {
        return this.ps;
    }

    public void setPs(int i) {
        this.ps = i;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }

    public String getError_message() {
        return this.error_message;
    }

    public void setError_message(String str) {
        this.error_message = str;
    }

    public List<BaseVO> getList() {
        return this.list;
    }

    public void setList(List<BaseVO> list) {
        this.list = list;
    }
}
