package cn.v6.sixrooms.surfaceanim.giftframe.giftscene;

import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftScene.GiftSceneParameter;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level1element.GiftScene1ElementBg;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level1element.GiftScene1ElementNum;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneUtil;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.NumType;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.SceneType;

public class GiftLevel1Scene extends GiftScene {
    public GiftLevel1Scene(GiftSceneParameter giftSceneParameter) {
        super(giftSceneParameter);
        this.mSceneType = SceneType.SCENE_LEVEL1;
    }

    protected int initMaxFrames() {
        return 82;
    }

    protected void initResources() {
        addBitmap(R.drawable.gift_default_icon);
        GiftSceneUtil.getIconBitmap(((GiftSceneParameter) getSceneParameter()).getIconUrl(), null);
        int giftNum = ((GiftSceneParameter) this.mSceneParameter).getGiftNum();
        int ceil = (int) Math.ceil((double) (((float) giftNum) / 27.0f));
        ((GiftSceneParameter) this.mSceneParameter).setStep(ceil);
        ((GiftSceneParameter) this.mSceneParameter).setNumStartFrame(23);
        int i = 23;
        int i2 = 1;
        while (i2 < giftNum) {
            AnimSceneResManager instance = AnimSceneResManager.getInstance();
            StringBuilder append = new StringBuilder().append(GiftSceneUtil.generateNumBitmapKey(NumType.WHITE, ((GiftSceneParameter) this.mSceneParameter).getGiftNum(), hashCode()));
            int i3 = i + 1;
            instance.saveBitmap(append.append(i).toString(), GiftSceneUtil.processImage(this.mSceneParameter.getResPath(), i2, NumType.WHITE));
            i2 += ceil;
            i = i3;
        }
        ((GiftSceneParameter) this.mSceneParameter).setNumEndFrame(i - 1);
        AnimSceneResManager.getInstance().saveBitmap(GiftSceneUtil.generateNumBitmapKey(NumType.WHITE, ((GiftSceneParameter) this.mSceneParameter).getGiftNum(), hashCode()), GiftSceneUtil.processImage(this.mSceneParameter.getResPath(), ((GiftSceneParameter) this.mSceneParameter).getGiftNum(), NumType.WHITE));
    }

    protected void releaseResources() {
        AnimSceneResManager.getInstance().clearSceneBitmaps(getSceneType());
    }

    protected void initSceneElement() {
        addElement(new GiftScene1ElementBg(this));
        addElement(new GiftScene1ElementNum(this));
    }
}
