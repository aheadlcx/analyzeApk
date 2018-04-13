package cn.v6.sixrooms.surfaceanim.giftframe;

import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimSceneFrame;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimFrameAddListener;
import cn.v6.sixrooms.surfaceanim.animinterface.IFrameRailManager;
import cn.v6.sixrooms.surfaceanim.animinterface.IRoomParameter;
import cn.v6.sixrooms.surfaceanim.animinterface.ISurfaceChangedListener;
import cn.v6.sixrooms.surfaceanim.util.RenderRect;

public class GiftSceneFrame extends AnimSceneFrame implements IAnimFrameAddListener, ISurfaceChangedListener {
    public GiftSceneFrame(IRoomParameter iRoomParameter) {
        super(iRoomParameter);
    }

    protected IFrameRailManager initRailManager(IRoomParameter iRoomParameter) {
        return new GiftFrameRailManager(iRoomParameter);
    }

    protected int initVisibleSceneCounts() {
        return 2;
    }

    public void addAnimScene(AnimScene animScene) {
        super.addAnimScene(animScene);
    }

    public void sceneRenderPre(AnimScene animScene) {
        animScene.getSceneParameter().setPoint(this.mRailManager.getValidRail());
    }

    public void sceneRenderFinish(AnimScene animScene) {
        this.mRailManager.setAvailRail(animScene.getSceneParameter().getPoint());
    }

    public void setRailManager(IFrameRailManager iFrameRailManager) {
        this.mRailManager = iFrameRailManager;
    }

    public IFrameRailManager getRailManager() {
        return this.mRailManager;
    }

    public void surfaceChanged(RenderRect renderRect) {
        this.mRailManager.setRenderRect(renderRect);
    }

    public void onAnimFrameAdd(RenderRect renderRect) {
        this.mRailManager.setRenderRect(renderRect);
    }
}
