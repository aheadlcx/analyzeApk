package cn.v6.sixrooms.surfaceanim.flybanner.confession;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.flybanner.FlyBannerScene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneUtil;
import com.budejie.www.R$styleable;

public class ConfessionScene extends FlyBannerScene {
    public ConfessionScene(SceneParameter sceneParameter) {
        super(sceneParameter);
    }

    protected int initMaxFrames() {
        return R$styleable.Theme_Custom_label_subscribe_bg_gd_color;
    }

    protected void initResources() {
        GiftSceneUtil.getOriginIconBitmap(((ConfessionSceneParameter) this.mSceneParameter).getBgUrl(), null);
    }

    protected void initSceneElement() {
        addElement(new ConfessionElement(this));
    }
}
