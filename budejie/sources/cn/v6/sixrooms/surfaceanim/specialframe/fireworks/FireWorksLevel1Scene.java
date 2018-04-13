package cn.v6.sixrooms.surfaceanim.specialframe.fireworks;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;

public class FireWorksLevel1Scene extends FireWorksScene {
    public FireWorksLevel1Scene(SceneParameter sceneParameter) {
        super(sceneParameter);
    }

    protected int initMaxFrames() {
        return 300;
    }

    public String initImgHeader() {
        return "fileworks_s_";
    }

    public int initRealFrames() {
        return 40;
    }

    protected void initSceneElement() {
        addElement(new FireWorksElement(this));
    }
}
