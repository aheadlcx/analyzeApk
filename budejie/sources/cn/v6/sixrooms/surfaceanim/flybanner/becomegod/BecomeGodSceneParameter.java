package cn.v6.sixrooms.surfaceanim.flybanner.becomegod;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;

public class BecomeGodSceneParameter extends SceneParameter {
    private String a;
    private String b;
    private String c;
    private GodType d;
    private String e;
    private String f;
    private String g;

    public String getBgUrl() {
        return this.e;
    }

    public void setBgUrl(String str) {
        this.e = str;
    }

    public String getText() {
        return this.a;
    }

    public void setText(String str) {
        this.a = str;
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

    public GodType getGodType() {
        return this.d;
    }

    public void setGodType(GodType godType) {
        this.d = godType;
    }

    public String getToRoomId() {
        return this.f;
    }

    public void setToRoomId(String str) {
        this.f = str;
    }

    public String getToRoomUid() {
        return this.g;
    }

    public void setToRoomUid(String str) {
        this.g = str;
    }
}
