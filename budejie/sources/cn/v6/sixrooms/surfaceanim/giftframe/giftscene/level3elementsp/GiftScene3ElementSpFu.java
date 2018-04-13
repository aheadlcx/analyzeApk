package cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3elementsp;

import android.graphics.Bitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3element.GiftScene3ElementFu;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class GiftScene3ElementSpFu extends GiftScene3ElementFu {
    public GiftScene3ElementSpFu(AnimScene animScene) {
        super(animScene);
    }

    public Bitmap elementFu() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "pink_fu.png");
    }

    public Bitmap elementFu2() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "pink_fu2.png");
    }

    public Bitmap elementBg() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "bg_pink.png");
    }
}
