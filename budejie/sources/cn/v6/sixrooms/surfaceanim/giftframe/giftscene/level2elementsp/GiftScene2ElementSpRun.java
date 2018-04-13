package cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level2elementsp;

import android.graphics.Bitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level2element.GiftScene2ElementRun;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class GiftScene2ElementSpRun extends GiftScene2ElementRun {
    public GiftScene2ElementSpRun(AnimScene animScene) {
        super(animScene);
    }

    public Bitmap elementRun() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "pink_run.png");
    }

    public Bitmap elementBg() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "bg_pink.png");
    }
}
