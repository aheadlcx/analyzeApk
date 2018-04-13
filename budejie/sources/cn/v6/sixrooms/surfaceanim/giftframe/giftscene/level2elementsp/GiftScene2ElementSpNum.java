package cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level2elementsp;

import android.graphics.Bitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level2element.GiftScene2ElementNum;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.NumType;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class GiftScene2ElementSpNum extends GiftScene2ElementNum {
    public GiftScene2ElementSpNum(AnimScene animScene) {
        super(animScene);
    }

    public NumType elementNum() {
        return NumType.PINK;
    }

    public Bitmap elementBg() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "bg_pink.png");
    }
}
