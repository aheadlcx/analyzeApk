package cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level2elementsp;

import android.graphics.Bitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level2element.GiftScene2ElementHit;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class GiftScene2ElementSpHit extends GiftScene2ElementHit {
    public GiftScene2ElementSpHit(AnimScene animScene) {
        super(animScene);
    }

    public Bitmap elementHit() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "pink_hit.png");
    }

    public Bitmap elementBg() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "bg_pink.png");
    }
}
