package cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level2elementsp;

import android.graphics.Bitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level2element.GiftScene2ElementBg;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class GiftScene2ElementSpBg extends GiftScene2ElementBg {
    public GiftScene2ElementSpBg(AnimScene animScene) {
        super(animScene);
    }

    public Bitmap elementBg() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "bg_pink.png");
    }
}
