package cn.v6.sixrooms.surfaceanim.giftframe.giftscene;

import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftScene.GiftSceneParameter;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3element.GiftScene3ElementBg;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3element.GiftScene3ElementFu;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3element.GiftScene3ElementHit;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3element.GiftScene3ElementNum;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3element.GiftScene3ElementRun;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneUtil;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.NumType;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.SceneType;
import com.budejie.www.R$styleable;

public class GiftLevel3Scene extends GiftScene {
    public GiftLevel3Scene(GiftSceneParameter giftSceneParameter) {
        super(giftSceneParameter);
        this.mSceneType = SceneType.SCENE_LEVEL3;
    }

    protected int initMaxFrames() {
        return R$styleable.Theme_Custom_top_label_btn_bg;
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
            StringBuilder append = new StringBuilder().append(GiftSceneUtil.generateNumBitmapKey(NumType.BLUE, ((GiftSceneParameter) this.mSceneParameter).getGiftNum(), hashCode()));
            int i3 = i + 1;
            instance.saveBitmap(append.append(i).toString(), GiftSceneUtil.processImage(this.mSceneParameter.getResPath(), i2, NumType.BLUE));
            i2 += ceil;
            i = i3;
        }
        ((GiftSceneParameter) this.mSceneParameter).setNumEndFrame(i - 73);
        AnimSceneResManager.getInstance().saveBitmap(GiftSceneUtil.generateNumBitmapKey(NumType.BLUE, ((GiftSceneParameter) this.mSceneParameter).getGiftNum(), hashCode()), GiftSceneUtil.processImage(this.mSceneParameter.getResPath(), ((GiftSceneParameter) this.mSceneParameter).getGiftNum(), NumType.BLUE));
        addBitmap(R.drawable.run_shade);
        addBitmap(R.drawable.gift_default_icon);
    }

    protected void releaseResources() {
        AnimSceneResManager.getInstance().clearSceneBitmaps(getSceneType());
    }

    protected void initSceneElement() {
        addElement(new GiftScene3ElementBg(this));
        addElement(new GiftScene3ElementRun(this));
        addElement(new GiftScene3ElementHit(this));
        addElement(new GiftScene3ElementNum(this));
        addElement(new GiftScene3ElementFu(this));
    }
}
