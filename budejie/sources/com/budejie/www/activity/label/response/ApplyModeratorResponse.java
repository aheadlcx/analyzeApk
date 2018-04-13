package com.budejie.www.activity.label.response;

public class ApplyModeratorResponse {
    private ApplyBean info;

    public static class ApplyBean {
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

    public ApplyBean getInfo() {
        return this.info;
    }

    public void setInfo(ApplyBean applyBean) {
        this.info = applyBean;
    }
}
