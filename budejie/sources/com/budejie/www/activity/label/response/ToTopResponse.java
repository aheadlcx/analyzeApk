package com.budejie.www.activity.label.response;

public class ToTopResponse {
    private InfoBean info;

    public static class InfoBean {
        private int code;
        private String result;

        public int getCode() {
            return this.code;
        }

        public void setCode(int i) {
            this.code = i;
        }

        public String getResult() {
            return this.result;
        }

        public void setResult(String str) {
            this.result = str;
        }
    }

    public InfoBean getInfo() {
        return this.info;
    }

    public void setInfo(InfoBean infoBean) {
        this.info = infoBean;
    }
}
