package cn.v6.sixrooms.surfaceanim.flybanner.notification;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;

public class NotificationSceneParameter extends SceneParameter {
    private String a;
    private String b;
    private String c;
    private String d;

    public String getUserAliasName() {
        return this.a;
    }

    public void setUserAliasName(String str) {
        this.a = str;
    }

    public String getRid() {
        return this.b;
    }

    public void setRid(String str) {
        this.b = str;
    }

    public String getUid() {
        return this.c;
    }

    public void setUid(String str) {
        this.c = str;
    }

    public String getPicuser() {
        return this.d;
    }

    public void setPicuser(String str) {
        this.d = str;
    }
}
