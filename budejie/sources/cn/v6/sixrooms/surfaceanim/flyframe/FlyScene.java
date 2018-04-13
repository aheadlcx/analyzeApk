package cn.v6.sixrooms.surfaceanim.flyframe;

import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.util.FrameType;

public class FlyScene extends AnimScene {
    public FlyScene(SceneParameter sceneParameter) {
        super(sceneParameter);
    }

    protected int initMaxFrames() {
        return Integer.MAX_VALUE;
    }

    protected void initResources() {
    }

    protected void releaseResources() {
    }

    public FrameType getFrameType() {
        return FrameType.FLY_FRAME;
    }

    protected void initSceneElement() {
        addElement(new FlyElement(this));
    }
}
