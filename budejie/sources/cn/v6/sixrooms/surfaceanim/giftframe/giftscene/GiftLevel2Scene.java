package cn.v6.sixrooms.surfaceanim.giftframe.giftscene;

import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftScene.GiftSceneParameter;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level2element.GiftScene2ElementBg;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level2element.GiftScene2ElementHit;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level2element.GiftScene2ElementNum;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level2element.GiftScene2ElementRun;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneUtil;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.NumType;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.SceneType;

public class GiftLevel2Scene extends GiftScene {
    public GiftLevel2Scene(GiftSceneParameter giftSceneParameter) {
        super(giftSceneParameter);
        this.mSceneType = SceneType.SCENE_LEVEL2;
    }

    protected int initMaxFrames() {
        return 121;
    }

    protected void initResources() {
        GiftSceneUtil.getIconBitmap(((GiftSceneParameter) getSceneParameter()).getIconUrl(), null);
        int giftNum = ((GiftSceneParameter) this.mSceneParameter).getGiftNum();
        int ceil = (int) Math.ceil((double) (((float) giftNum) / 48.0f));
        ((GiftSceneParameter) this.mSceneParameter).setStep(ceil);
        ((GiftSceneParameter) this.mSceneParameter).setNumStartFrame(23);
        int i = 23;
        int i2 = 1;
        while (i2 < giftNum) {
            AnimSceneResManager instance = AnimSceneResManager.getInstance();
            StringBuilder append = new StringBuilder().append(GiftSceneUtil.generateNumBitmapKey(NumType.GOLD, ((GiftSceneParameter) this.mSceneParameter).getGiftNum(), hashCode()));
            int i3 = i + 1;
            instance.saveBitmap(append.append(i).toString(), GiftSceneUtil.processImage(this.mSceneParameter.getResPath(), i2, NumType.GOLD));
            i2 += ceil;
            i = i3;
        }
        ((GiftSceneParameter) this.mSceneParameter).setNumEndFrame(i - 25);
        AnimSceneResManager.getInstance().saveBitmap(GiftSceneUtil.generateNumBitmapKey(NumType.GOLD, ((GiftSceneParameter) this.mSceneParameter).getGiftNum(), hashCode()), GiftSceneUtil.processImage(this.mSceneParameter.getResPath(), ((GiftSceneParameter) this.mSceneParameter).getGiftNum(), NumType.GOLD));
        addBitmap(R.drawable.run_shade);
        addBitmap(R.drawable.gift_default_icon);
    }

    protected void releaseResources() {
        AnimSceneResManager.getInstance().clearSceneBitmaps(getSceneType());
    }

    protected void initSceneElement() {
        addElement(new GiftScene2ElementBg(this));
        addElement(new GiftScene2ElementRun(this));
        addElement(new GiftScene2ElementHit(this));
        addElement(new GiftScene2ElementNum(this));
    }
}
