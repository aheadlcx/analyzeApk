package cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3element;

import android.graphics.Bitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftSceneElementRun;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class GiftScene3ElementRun extends GiftSceneElementRun {
    public GiftScene3ElementRun(AnimScene animScene) {
        super(animScene);
    }

    public boolean frameControl(int i) {
        if (i < 10) {
            return true;
        }
        if (i < 22) {
            this.transXFrom += this.transXFrame;
            return false;
        } else if (i == 22) {
            this.transXFrom = this.mRunBitmapLeft;
            return false;
        } else if (i < 30) {
            this.alpha -= this.alphaFrame;
            this.scaleX -= this.scaleXFrame;
            this.transXFrom = this.mRunBitmapLeft;
            return false;
        } else if (i == 30) {
            this.alpha = 0;
            this.scaleX = this.scaleXDest;
            this.transXFrom = this.mRunBitmapLeft;
            return false;
        } else if (i <= 37) {
            this.alpha = 0;
            this.scaleX = this.scaleXDest;
            this.transXFrom = this.mRunBitmapLeft;
            return false;
        } else if (i < 43) {
            this.alpha += 42;
            this.scaleX += (1.0f - this.scaleXDest) / 6.0f;
            this.transXFrom = this.mRunBitmapLeft;
            return false;
        } else if (i == 43) {
            this.alpha = 255;
            this.scaleX = 1.0f;
            this.transXFrom = this.mRunBitmapLeft;
            return false;
        } else if (i < 57) {
            this.alpha = 255;
            this.scaleX = 1.0f;
            this.transXFrom -= this.transXFrame;
            return false;
        } else if (i == 57) {
            this.alpha = 255;
            this.scaleX = 1.0f;
            this.transXFrom = -this.mRunBitmap.getWidth();
            return false;
        } else if (i < 62) {
            this.alpha = 255;
            this.scaleX = 1.0f;
            this.transXFrom = -this.mRunBitmap.getWidth();
            return false;
        } else if (i < 75) {
            this.alpha = 255;
            this.scaleX = 1.0f;
            this.transXFrom += this.transXFrame;
            return false;
        } else if (i == 75) {
            this.alpha = 255;
            this.scaleX = 1.0f;
            this.transXFrom = this.mRunBitmapLeft;
            return false;
        } else if (i < 83) {
            this.alpha -= this.alphaFrame;
            this.scaleX -= this.scaleXFrame;
            this.transXFrom = this.mRunBitmapLeft;
            return false;
        } else if (i < 83) {
            return false;
        } else {
            this.alpha = 0;
            this.scaleX = this.scaleXDest;
            this.transXFrom = this.mRunBitmapLeft;
            return false;
        }
    }

    public Bitmap elementRun() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "blue_run.png");
    }

    public Bitmap elementBg() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "bg_blue.png");
    }
}
