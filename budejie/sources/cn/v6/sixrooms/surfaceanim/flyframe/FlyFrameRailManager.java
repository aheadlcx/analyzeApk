package cn.v6.sixrooms.surfaceanim.flyframe;

import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.animinterface.IFrameRailManager;
import cn.v6.sixrooms.surfaceanim.animinterface.IRoomParameter;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.RenderRect;

public class FlyFrameRailManager implements IFrameRailManager {
    private PointI a = new PointI();
    private int b = AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.fly_frame_point_margin_bottom_l);
    private int c = AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.fly_frame_point_margin_bottom_p);

    public FlyFrameRailManager(IRoomParameter iRoomParameter) {
    }

    public synchronized PointI getValidRail() {
        return this.a;
    }

    public synchronized void setAvailRail(PointI pointI) {
    }

    public synchronized void resetRail() {
    }

    public synchronized void setRenderRect(RenderRect renderRect) {
        if (renderRect.getWidth() > renderRect.getHeight()) {
            this.a.y = renderRect.getHeight() - this.b;
        } else {
            this.a.y = renderRect.getHeight() - this.c;
        }
        this.a.x = renderRect.getWidth();
    }

    public RenderRect getRenderRect() {
        return null;
    }
}
