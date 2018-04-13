package cn.v6.sixrooms.surfaceanim.giftframe;

import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.animinterface.IFrameRailManager;
import cn.v6.sixrooms.surfaceanim.animinterface.IRoomParameter;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.RenderRect;

public class GiftFrameRailManager implements IFrameRailManager {
    private PointI[] a = new PointI[2];
    private boolean[] b = new boolean[2];
    private IRoomParameter c;
    private int d;

    public GiftFrameRailManager(IRoomParameter iRoomParameter) {
        this.c = iRoomParameter;
        initRail();
    }

    public synchronized void setRenderRect(RenderRect renderRect) {
        int width = renderRect.getWidth();
        int height = renderRect.getHeight();
        if (width > height) {
            width = this.c.getChatHeightL() + AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.gift_base_on_chat_margin_land);
        } else {
            width = this.c.getChatHeightP() + AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.gift_base_on_chat_margin_portrait);
        }
        this.a[0].y = height - width;
        this.a[1].y = this.d + this.a[0].y;
    }

    public RenderRect getRenderRect() {
        return null;
    }

    public synchronized PointI getValidRail() {
        PointI pointI;
        synchronized (this) {
            for (int i = 0; i < this.b.length; i++) {
                if (this.b[i]) {
                    this.b[i] = false;
                    pointI = this.a[i];
                    break;
                }
            }
            pointI = null;
        }
        return pointI;
    }

    public synchronized void setAvailRail(PointI pointI) {
        if (pointI.equals(this.a[0])) {
            this.b[0] = true;
        } else {
            this.b[1] = true;
        }
    }

    public synchronized void resetRail() {
        this.b[0] = true;
        this.b[1] = true;
    }

    public synchronized void initRail() {
        this.d = AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.gift_frame_point_y2_margin);
        this.a[0] = new PointI();
        this.a[1] = new PointI();
        this.b[0] = true;
        this.b[1] = true;
    }
}
