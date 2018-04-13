package cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level1element;

import android.graphics.Bitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftSceneElementNum;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.NumType;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class GiftScene1ElementNum extends GiftSceneElementNum {
    public GiftScene1ElementNum(AnimScene animScene) {
        super(animScene);
    }

    public NumType elementNum() {
        return NumType.WHITE;
    }

    public Bitmap elementBg() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "bg_black.png");
    }
}
