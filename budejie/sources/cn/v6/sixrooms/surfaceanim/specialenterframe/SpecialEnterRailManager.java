package cn.v6.sixrooms.surfaceanim.specialenterframe;

import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.animinterface.IFrameRailManager;
import cn.v6.sixrooms.surfaceanim.animinterface.IRoomParameter;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.RenderRect;

public class SpecialEnterRailManager implements IFrameRailManager {
    private PointI a = new PointI();
    private IRoomParameter b;

    public SpecialEnterRailManager(IRoomParameter iRoomParameter) {
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
        int width = renderRect.getWidth();
        int height = renderRect.getHeight();
        if (width > height) {
            this.a.y = (height - this.b.getChatHeightL()) - AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.special_enter_base_on_gift_margin_p);
        } else {
            this.a.y = (height - this.b.getChatHeightP()) - AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.special_enter_base_on_gift_margin_p);
        }
        this.a.x = renderRect.getWidth();
    }

    public RenderRect getRenderRect() {
        return null;
    }
}
