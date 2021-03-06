package cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level2element;

import android.graphics.Bitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftSceneElementHit;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class GiftScene2ElementHit extends GiftSceneElementHit {
    public GiftScene2ElementHit(AnimScene animScene) {
        super(animScene);
    }

    public boolean frameControl(int i) {
        if (i < 18) {
            return true;
        }
        if (i <= 18) {
            this.alpha = 255;
            this.scale = 1.0f;
            return false;
        } else if (i <= 28) {
            this.alpha = 255;
            this.scale += this.scaleFrame;
            return false;
        } else if (i < 33) {
            this.alpha -= this.alphaFrame;
            this.scale = 0.5f;
            return false;
        } else if (i < 33) {
            return false;
        } else {
            this.alpha = 0;
            this.scale = 0.5f;
            return false;
        }
    }

    public Bitmap elementHit() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "gold_hit.png");
    }

    public Bitmap elementBg() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "bg_gold.png");
    }
}
