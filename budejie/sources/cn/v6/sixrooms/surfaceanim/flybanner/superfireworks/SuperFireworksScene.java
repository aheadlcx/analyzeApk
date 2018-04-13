package cn.v6.sixrooms.surfaceanim.flybanner.superfireworks;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.flybanner.FlyBannerScene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneUtil;
import com.budejie.www.R$styleable;

public class SuperFireworksScene extends FlyBannerScene {
    public SuperFireworksScene(SceneParameter sceneParameter) {
        super(sceneParameter);
    }

    protected int initMaxFrames() {
        return R$styleable.Theme_Custom_forward_icon;
    }

    protected void initResources() {
        GiftSceneUtil.getOriginIconBitmap(((SuperFireworksSceneParameter) this.mSceneParameter).getBgUrl(), null);
    }

    protected void initSceneElement() {
        addElement(new SuperFireworksElement(this));
    }
}
