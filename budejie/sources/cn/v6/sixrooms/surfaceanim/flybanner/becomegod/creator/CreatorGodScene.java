package cn.v6.sixrooms.surfaceanim.flybanner.becomegod.creator;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.flybanner.FlyBannerScene;
import cn.v6.sixrooms.surfaceanim.flybanner.becomegod.BecomeGodSceneParameter;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneUtil;
import com.budejie.www.R$styleable;

public class CreatorGodScene extends FlyBannerScene {
    public CreatorGodScene(SceneParameter sceneParameter) {
        super(sceneParameter);
    }

    protected int initMaxFrames() {
        return R$styleable.Theme_Custom_last_refresh_item_text_theme;
    }

    protected void initResources() {
        GiftSceneUtil.getOriginIconBitmap(((BecomeGodSceneParameter) this.mSceneParameter).getBgUrl(), null);
    }

    protected void initSceneElement() {
        addElement(new CreatorGodElement(this));
    }
}
