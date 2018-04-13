package cn.v6.sixrooms.surfaceanim.specialframe.fireworks;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;

public abstract class FireWorksScene extends SpecialScene {
    protected String mImgHeader;
    protected int mRealFrames;

    public abstract String initImgHeader();

    public abstract int initRealFrames();

    public FireWorksScene(SceneParameter sceneParameter) {
        super(sceneParameter);
    }

    protected int initMaxFrames() {
        return 180;
    }

    protected void initResources() {
        this.mImgHeader = initImgHeader();
        this.mRealFrames = initRealFrames();
    }

    protected void initSceneElement() {
        addElement(new FireWorksElement(this));
    }
}
