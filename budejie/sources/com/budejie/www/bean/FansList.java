package com.budejie.www.bean;

import java.util.ArrayList;

public class FansList {
    String code;
    String count;
    ArrayList<Fans> data;
    String follow_id;
    boolean has_data;
    String msg;

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

    public ArrayList<Fans> getData() {
        return this.data;
    }

    public void setData(ArrayList<Fans> arrayList) {
        this.data = arrayList;
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String str) {
        this.count = str;
    }

    public String getFollow_id() {
        return this.follow_id;
    }

    public void setFollow_id(String str) {
        this.follow_id = str;
    }

    public boolean getHas_data() {
        return this.has_data;
    }

    public void setHas_data(boolean z) {
        this.has_data = z;
    }
}
