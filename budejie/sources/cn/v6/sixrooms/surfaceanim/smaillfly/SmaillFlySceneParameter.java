package cn.v6.sixrooms.surfaceanim.smaillfly;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;

public class SmaillFlySceneParameter extends SceneParameter {
    private String a;
    private String b;
    private String c;

    public String getPhotoUrl() {
        return this.a;
    }

    public void setPhotoUrl(String str) {
        this.a = str;
    }

    public String getText() {
        return this.b;
    }

    public void setText(String str) {
        this.b = str;
    }

    public String getFromUser() {
        return this.c;
    }

    public void setFromUser(String str) {
        this.c = str + "说";
    }
}
