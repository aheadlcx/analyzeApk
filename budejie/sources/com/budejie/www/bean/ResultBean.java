package com.budejie.www.bean;

public class ResultBean {
    private String code;
    private ImageBean imageBean;
    private String msg;
    private String result;
    private String result_msg;

    public String getResult() {
        return this.result;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public String getResult_msg() {
        return this.result_msg;
    }

    public void setResult_msg(String str) {
        this.result_msg = str;
    }

    public ImageBean getImageBean() {
        return this.imageBean;
    }

    public void setImageBean(ImageBean imageBean) {
        this.imageBean = imageBean;
    }

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
}
