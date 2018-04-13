package cn.v6.sixrooms.surfaceanim.specialframe;

import android.graphics.Bitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.annotation.SceneFrameOwner;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.FrameType;

@SceneFrameOwner("cn.v6.sixrooms.surfaceanim.protocolframe.ProtocolSceneFrame")
public abstract class SpecialScene extends AnimScene {
    protected int offsetX = 0;
    protected int offsetY = 0;

    public SpecialScene() {
        super(new SceneParameter());
    }

    public SpecialScene(SceneParameter sceneParameter) {
        super(sceneParameter);
    }

    public FrameType getFrameType() {
        return FrameType.NONE;
    }

    protected void releaseResources() {
        AnimSceneResManager.getInstance().clearSceneBitmaps(getSceneType());
    }

    public int getOffsetX() {
        return this.offsetX;
    }

    public int getOffsetY() {
        return this.offsetY;
    }

    public Bitmap getBitmap(String str) {
        return AnimSceneResManager.getInstance().getBitmap(getSceneType(), str);
    }
}
