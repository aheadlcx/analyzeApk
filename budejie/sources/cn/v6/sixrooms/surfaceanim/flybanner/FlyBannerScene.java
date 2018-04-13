package cn.v6.sixrooms.surfaceanim.flybanner;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;
import cn.v6.sixrooms.surfaceanim.util.FrameType;

public abstract class FlyBannerScene extends SpecialScene {
    public FlyBannerScene(SceneParameter sceneParameter) {
        super(sceneParameter);
    }

    public FrameType getFrameType() {
        return FrameType.FLY_BANNER_FRAME;
    }
}
