package cn.v6.sixrooms.room.verify;

import cn.v6.sixrooms.bean.MessageBean;

public class CaptchaBean extends MessageBean {
    private static final long serialVersionUID = 1;
    private String challenge;
    private String gt;
    private String success;

    public String getSuccess() {
        return this.success;
    }

    public void setSuccess(String str) {
        this.success = str;
    }

    public String getGt() {
        return this.gt;
    }

    public void setGt(String str) {
        this.gt = str;
    }

    public String getChallenge() {
        return this.challenge;
    }

    public void setChallenge(String str) {
        this.challenge = str;
    }
}
