package cn.v6.sixrooms.surfaceanim.smaillfly;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneUtil;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.FrameType;

public class SmaillFlyScene extends SpecialScene {
    public SmaillFlyScene(SceneParameter sceneParameter) {
        super(sceneParameter);
    }

    protected int initMaxFrames() {
        return Integer.MAX_VALUE;
    }

    protected void initResources() {
        AnimSceneResManager.getInstance().addBitmap(getSceneType(), R.drawable.default_photo, true);
        GiftSceneUtil.getOriginIconBitmap(((SmaillFlySceneParameter) getSceneParameter()).getPhotoUrl(), null);
    }

    public FrameType getFrameType() {
        return FrameType.SMAILL_FLY_FRAME;
    }

    protected void initSceneElement() {
        addElement(new SmaillFlyElement(this));
    }
}
