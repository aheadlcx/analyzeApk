package cn.v6.sixrooms.surfaceanim.smaillfly;

import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.animinterface.IFrameRailManager;
import cn.v6.sixrooms.surfaceanim.animinterface.IRoomParameter;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.RenderRect;

public class SmaillFlyFrameRailManager implements IFrameRailManager {
    private PointI a = new PointI();
    private IRoomParameter b;

    public SmaillFlyFrameRailManager(IRoomParameter iRoomParameter) {
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
            this.a.y = renderRect.getHeight() - (this.b.getChatHeightL() + AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.gift_base_on_chat_margin_land));
        } else {
            int height = (renderRect.getHeight() - (this.b.getChatHeightP() + AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.gift_base_on_chat_margin_portrait))) + AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.gift_frame_point_y2_margin);
            this.a.y = height + AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.gift_frame_point_y2_margin);
        }
    }

    public RenderRect getRenderRect() {
        return null;
    }
}
