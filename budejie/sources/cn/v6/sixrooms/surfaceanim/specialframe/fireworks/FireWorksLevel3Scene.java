package cn.v6.sixrooms.surfaceanim.specialframe.fireworks;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;

public class FireWorksLevel3Scene extends FireWorksScene {
    public FireWorksLevel3Scene(SceneParameter sceneParameter) {
        super(sceneParameter);
    }

    protected int initMaxFrames() {
        return 418;
    }

    public String initImgHeader() {
        return "fileworks_b_";
    }

    public int initRealFrames() {
        return 126;
    }

    protected void initSceneElement() {
        addElement(new SuperFireWorksElement(this));
    }
}
