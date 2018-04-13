package cn.v6.sixrooms.surfaceanim.flybanner.becomegod.god;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.flybanner.FlyBannerScene;
import cn.v6.sixrooms.surfaceanim.flybanner.becomegod.BecomeGodSceneParameter;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneUtil;

public class GodScene extends FlyBannerScene {
    public GodScene(SceneParameter sceneParameter) {
        super(sceneParameter);
    }

    protected int initMaxFrames() {
        return 200;
    }

    protected void initResources() {
        GiftSceneUtil.getOriginIconBitmap(((BecomeGodSceneParameter) this.mSceneParameter).getBgUrl(), null);
    }

    protected void releaseResources() {
    }

    protected void initSceneElement() {
        addElement(new GodElement(this));
    }
}
