package com.budejie.www.bean;

public class DOCObject {
    private int cai;
    private String cai_flag;
    private String data_id;
    private int ding;
    private String flag;

    public int getDing() {
        return this.ding;
    }

    public void setDing(int i) {
        this.ding = i;
    }

    public int getCai() {
        return this.cai;
    }

    public void setCai(int i) {
        this.cai = i;
    }

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String str) {
        this.flag = str;
    }

    public String getData_id() {
        return this.data_id;
    }

    public void setData_id(String str) {
        this.data_id = str;
    }

    public String getCai_flag() {
        return this.cai_flag;
    }

    public void setCai_flag(String str) {
        this.cai_flag = str;
    }
}
