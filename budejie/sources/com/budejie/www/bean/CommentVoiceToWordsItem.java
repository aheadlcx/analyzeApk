package com.budejie.www.bean;

import java.io.Serializable;

public class CommentVoiceToWordsItem implements Serializable {
    private static final long serialVersionUID = 1;
    private String code;
    private String info;
    private String msg;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
