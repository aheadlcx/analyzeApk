package cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3elementsp;

import android.graphics.Bitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3element.GiftScene3ElementNum;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.NumType;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class GiftScene3ElementSpNum extends GiftScene3ElementNum {
    public GiftScene3ElementSpNum(AnimScene animScene) {
        super(animScene);
    }

    public NumType elementNum() {
        return NumType.PINK;
    }

    public Bitmap elementBg() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "bg_pink.png");
    }
}
