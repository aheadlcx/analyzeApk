package cn.v6.sixrooms.surfaceanim.flybanner.superfireworks;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;

public class SuperFireworksSceneParameter extends SceneParameter {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;

    public String getBgUrl() {
        return this.c;
    }

    public void setBgUrl(String str) {
        this.c = str;
    }

    public String getFromUser() {
        return this.a;
    }

    public void setFromUser(String str) {
        if (str == null || str.length() <= 6) {
            this.a = str;
        } else {
            this.a = str.substring(0, 6) + "...";
        }
    }

    public String getToUser() {
        return this.b;
    }

    public void setToUser(String str) {
        if (str == null || str.length() <= 6) {
            this.b = str;
        } else {
            this.b = str.substring(0, 6) + "...";
        }
    }

    public String getToRoomId() {
        return this.d;
    }

    public void setToRoomId(String str) {
        this.d = str;
    }

    public String getToRoomUid() {
        return this.e;
    }

    public void setToRoomUid(String str) {
        this.e = str;
    }
}
