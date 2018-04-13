package cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3elementsp;

import android.graphics.Bitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3element.GiftScene3ElementBg;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class GiftScene3ElementSpBg extends GiftScene3ElementBg {
    public GiftScene3ElementSpBg(AnimScene animScene) {
        super(animScene);
    }

    public Bitmap elementBg() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "bg_pink.png");
    }
}
