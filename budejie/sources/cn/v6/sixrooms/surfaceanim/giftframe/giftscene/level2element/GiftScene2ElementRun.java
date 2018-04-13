package cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level2element;

import android.graphics.Bitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftSceneElementRun;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class GiftScene2ElementRun extends GiftSceneElementRun {
    public GiftScene2ElementRun(AnimScene animScene) {
        super(animScene);
        this.transXFrame = (this.mRunBitmapLeft - this.transXFrom) / 8;
    }

    public boolean frameControl(int i) {
        if (i < 10) {
            return true;
        }
        if (i < 18) {
            this.transXFrom += this.transXFrame;
            return false;
        } else if (i == 18) {
            this.transXFrom = this.mRunBitmapLeft;
            return false;
        } else if (i < 26) {
            this.alpha -= this.alphaFrame;
            this.scaleX -= this.scaleXFrame;
            this.transXFrom = this.mRunBitmapLeft;
            return false;
        } else if (i < 26) {
            return false;
        } else {
            this.alpha = 0;
            this.scaleX = this.scaleXDest;
            this.transXFrom = this.mRunBitmapLeft;
            return false;
        }
    }

    public Bitmap elementRun() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "gold_run.png");
    }

    public Bitmap elementBg() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "bg_gold.png");
    }
}
