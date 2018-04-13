package cn.v6.sixrooms.surfaceanim.giftframe.giftscene;

import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftScene.GiftSceneParameter;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3elementsp.GiftScene3ElementSpBg;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3elementsp.GiftScene3ElementSpFu;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3elementsp.GiftScene3ElementSpHit;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3elementsp.GiftScene3ElementSpNum;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3elementsp.GiftScene3ElementSpRun;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneUtil;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.NumType;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.SceneType;

public class GiftLevel3SceneSp extends GiftLevel3Scene {
    public GiftLevel3SceneSp(GiftSceneParameter giftSceneParameter) {
        super(giftSceneParameter);
        this.mSceneType = SceneType.SCENE_LEVEL3_SP;
    }

    protected void initResources() {
        GiftSceneUtil.getIconBitmap(((GiftSceneParameter) getSceneParameter()).getIconUrl(), null);
        int giftNum = ((GiftSceneParameter) this.mSceneParameter).getGiftNum();
        int ceil = (int) Math.ceil((double) (((float) giftNum) / 144.0f));
        ((GiftSceneParameter) this.mSceneParameter).setStep(ceil);
        ((GiftSceneParameter) this.mSceneParameter).setNumStartFrame(23);
        int i = 23;
        int i2 = 1;
        while (i2 < giftNum) {
            AnimSceneResManager instance = AnimSceneResManager.getInstance();
            StringBuilder append = new StringBuilder().append(GiftSceneUtil.generateNumBitmapKey(NumType.PINK, ((GiftSceneParameter) this.mSceneParameter).getGiftNum(), hashCode()));
            int i3 = i + 1;
            instance.saveBitmap(append.append(i).toString(), GiftSceneUtil.processImage(this.mSceneParameter.getResPath(), i2, NumType.PINK));
            i2 += ceil;
            i = i3;
        }
        ((GiftSceneParameter) this.mSceneParameter).setNumEndFrame(i - 73);
        AnimSceneResManager.getInstance().saveBitmap(GiftSceneUtil.generateNumBitmapKey(NumType.PINK, ((GiftSceneParameter) this.mSceneParameter).getGiftNum(), hashCode()), GiftSceneUtil.processImage(this.mSceneParameter.getResPath(), ((GiftSceneParameter) this.mSceneParameter).getGiftNum(), NumType.PINK));
        addBitmap(R.drawable.run_shade);
        addBitmap(R.drawable.gift_default_icon);
    }

    protected void releaseResources() {
        AnimSceneResManager.getInstance().clearSceneBitmaps(getSceneType());
    }

    protected void initSceneElement() {
        addElement(new GiftScene3ElementSpBg(this));
        addElement(new GiftScene3ElementSpRun(this));
        addElement(new GiftScene3ElementSpHit(this));
        addElement(new GiftScene3ElementSpNum(this));
        addElement(new GiftScene3ElementSpFu(this));
    }
}
