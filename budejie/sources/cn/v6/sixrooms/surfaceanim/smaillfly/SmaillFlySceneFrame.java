package cn.v6.sixrooms.surfaceanim.smaillfly;

import cn.v6.sixrooms.surfaceanim.animinterface.IFrameRailManager;
import cn.v6.sixrooms.surfaceanim.animinterface.IRoomParameter;
import cn.v6.sixrooms.surfaceanim.flyframe.FlySceneFrame;

public class SmaillFlySceneFrame extends FlySceneFrame {
    public SmaillFlySceneFrame(IRoomParameter iRoomParameter) {
        super(iRoomParameter);
    }

    protected IFrameRailManager initRailManager(IRoomParameter iRoomParameter) {
        return new SmaillFlyFrameRailManager(iRoomParameter);
    }
}
