package cn.v6.sixrooms.surfaceanim.specialframe.fireworks;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;

public class FireWorksLevel2Scene extends FireWorksScene {
    public FireWorksLevel2Scene(SceneParameter sceneParameter) {
        super(sceneParameter);
    }

    protected int initMaxFrames() {
        return 360;
    }

    public String initImgHeader() {
        return "fileworks_m_";
    }

    public int initRealFrames() {
        return 60;
    }

    protected void initSceneElement() {
        addElement(new FireWorksElement(this));
    }
}
