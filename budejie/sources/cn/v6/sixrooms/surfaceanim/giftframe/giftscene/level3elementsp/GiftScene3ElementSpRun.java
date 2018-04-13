package cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3elementsp;

import android.graphics.Bitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.level3element.GiftScene3ElementRun;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class GiftScene3ElementSpRun extends GiftScene3ElementRun {
    public GiftScene3ElementSpRun(AnimScene animScene) {
        super(animScene);
    }

    public Bitmap elementRun() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "pink_run.png");
    }

    public Bitmap elementBg() {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mGiftScene.getSceneParameter().getResPath() + File.separator + "bg_pink.png");
    }
}
