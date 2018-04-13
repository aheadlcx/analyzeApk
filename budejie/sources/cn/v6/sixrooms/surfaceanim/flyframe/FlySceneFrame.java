package cn.v6.sixrooms.surfaceanim.flyframe;

import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimSceneFrame;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimFrameAddListener;
import cn.v6.sixrooms.surfaceanim.animinterface.IFrameRailManager;
import cn.v6.sixrooms.surfaceanim.animinterface.IRoomParameter;
import cn.v6.sixrooms.surfaceanim.animinterface.ISurfaceChangedListener;
import cn.v6.sixrooms.surfaceanim.util.RenderRect;

public class FlySceneFrame extends AnimSceneFrame implements IAnimFrameAddListener, ISurfaceChangedListener {
    public FlySceneFrame(IRoomParameter iRoomParameter) {
        super(iRoomParameter);
    }

    protected IFrameRailManager initRailManager(IRoomParameter iRoomParameter) {
        return new FlyFrameRailManager(iRoomParameter);
    }

    protected int initVisibleSceneCounts() {
        return 1;
    }

    public void sceneRenderPre(AnimScene animScene) {
        animScene.getSceneParameter().setPoint(this.mRailManager.getValidRail());
    }

    public void sceneRenderFinish(AnimScene animScene) {
        this.mRailManager.setAvailRail(animScene.getSceneParameter().getPoint());
    }

    public void onAnimFrameAdd(RenderRect renderRect) {
        this.mRailManager.setRenderRect(renderRect);
    }

    public void surfaceChanged(RenderRect renderRect) {
        this.mRailManager.setRenderRect(renderRect);
    }
}
