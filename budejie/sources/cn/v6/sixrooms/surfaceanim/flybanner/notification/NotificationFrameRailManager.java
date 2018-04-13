package cn.v6.sixrooms.surfaceanim.flybanner.notification;

import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.animinterface.IFrameRailManager;
import cn.v6.sixrooms.surfaceanim.animinterface.IRoomParameter;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.RenderRect;

public class NotificationFrameRailManager implements IFrameRailManager {
    private PointI a = new PointI();
    private IRoomParameter b;

    public NotificationFrameRailManager(IRoomParameter iRoomParameter) {
        this.b = iRoomParameter;
    }

    public synchronized PointI getValidRail() {
        return this.a;
    }

    public synchronized void setAvailRail(PointI pointI) {
    }

    public synchronized void resetRail() {
    }

    public synchronized void setRenderRect(RenderRect renderRect) {
        this.a.x = renderRect.getWidth();
        if (renderRect.getWidth() > renderRect.getHeight()) {
            this.a.y = renderRect.getHeight() - this.b.getChatHeightL();
        } else {
            this.a.y = ((renderRect.getHeight() - this.b.getChatHeightP()) - AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.notification_height)) - AnimSceneResManager.getInstance().dp2px(17.0f);
        }
    }

    public RenderRect getRenderRect() {
        return null;
    }
}
