package cn.v6.sixrooms.surfaceanim.protocolframe;

import android.graphics.Bitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.annotation.SceneFrameOwner;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.FrameType;

@SceneFrameOwner("cn.v6.sixrooms.surfaceanim.protocolframe.ProtocolSceneFrame")
public abstract class ProtocolScene extends AnimScene {
    protected int mLayerId;

    public ProtocolScene() {
        super(new SceneParameter());
    }

    public ProtocolScene(SceneParameter sceneParameter) {
        super(sceneParameter);
    }

    public FrameType getFrameType() {
        return FrameType.NONE;
    }

    protected void releaseResources() {
        AnimSceneResManager.getInstance().clearSceneBitmaps(getSceneType());
    }

    public Bitmap getBitmap(String str) {
        return AnimSceneResManager.getInstance().getBitmap(getSceneType(), str);
    }

    public void setLayerId(int i) {
        this.mLayerId = i;
    }

    public int getLayerId() {
        return this.mLayerId;
    }
}
