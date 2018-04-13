package cn.v6.sixrooms.surfaceanim.protocolframe;

import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimSceneFrame;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimFrameAddListener;
import cn.v6.sixrooms.surfaceanim.animinterface.IFrameRailManager;
import cn.v6.sixrooms.surfaceanim.animinterface.IRoomParameter;
import cn.v6.sixrooms.surfaceanim.animinterface.ISurfaceChangedListener;
import cn.v6.sixrooms.surfaceanim.util.RenderRect;

public class ProtocolSceneFrame extends AnimSceneFrame implements IAnimFrameAddListener, ISurfaceChangedListener {
    public ProtocolSceneFrame(IRoomParameter iRoomParameter) {
        super(iRoomParameter);
    }

    protected IFrameRailManager initRailManager(IRoomParameter iRoomParameter) {
        return new ProtocolFrameRailManager();
    }

    protected int initVisibleSceneCounts() {
        return 1;
    }

    public void sceneRenderPre(AnimScene animScene) {
        if (animScene instanceof ProtocolScene) {
            ((ProtocolFrameRailManager) this.mRailManager).setSceneBean(animScene.getSceneBean());
            animScene.getSceneParameter().setPoint(this.mRailManager.getValidRail().transformScalePoint());
            return;
        }
        animScene.getSceneParameter().setPoint(((ProtocolFrameRailManager) this.mRailManager).getSpecialPoint());
    }

    public void sceneRenderFinish(AnimScene animScene) {
    }

    public void onAnimFrameAdd(RenderRect renderRect) {
        this.mRailManager.setRenderRect(renderRect);
    }

    public void surfaceChanged(RenderRect renderRect) {
        this.mRailManager.setRenderRect(renderRect);
    }
}
