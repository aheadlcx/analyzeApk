package cn.v6.sixrooms.surfaceanim.flybanner.confession;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;

public class ConfessionSceneParameter extends SceneParameter {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;

    public String getRid() {
        return this.e;
    }

    public void setRid(String str) {
        this.e = str;
    }

    public String getUid() {
        return this.f;
    }

    public void setUid(String str) {
        this.f = str;
    }

    public String getBgUrl() {
        return this.d;
    }

    public void setBgUrl(String str) {
        this.d = str;
    }

    public String getText() {
        return this.a;
    }

    public void setText(String str) {
        if (str == null || str.length() <= 12) {
            this.a = str;
        } else {
            this.a = str.substring(0, 12) + "...";
        }
    }

    public String getFromUser() {
        return this.b;
    }

    public void setFromUser(String str) {
        if (str == null || str.length() <= 6) {
            this.b = str;
        } else {
            this.b = str.substring(0, 6) + "...";
        }
    }

    public String getToUser() {
        return this.c;
    }

    public void setToUser(String str) {
        if (str == null || str.length() <= 6) {
            this.c = str;
        } else {
            this.c = str.substring(0, 6) + "...";
        }
    }
}
