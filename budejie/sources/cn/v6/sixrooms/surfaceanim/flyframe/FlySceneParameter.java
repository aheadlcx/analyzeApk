package cn.v6.sixrooms.surfaceanim.flyframe;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;

public class FlySceneParameter extends SceneParameter {
    private String a;

    public void setText(String str) {
        this.a = str;
    }

    public String getText() {
        return this.a;
    }
}
