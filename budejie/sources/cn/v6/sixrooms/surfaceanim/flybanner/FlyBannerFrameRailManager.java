package cn.v6.sixrooms.surfaceanim.flybanner;

import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.animinterface.IFrameRailManager;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.RenderRect;

public class FlyBannerFrameRailManager implements IFrameRailManager {
    private PointI a = new PointI();
    private int b = AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.anim_upgrade_margin_top_l);
    private int c = AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.anim_upgrade_margin_top_p);

    public synchronized PointI getValidRail() {
        return this.a;
    }

    public synchronized void setAvailRail(PointI pointI) {
    }

    public synchronized void resetRail() {
    }

    public synchronized void setRenderRect(RenderRect renderRect) {
        if (renderRect.getWidth() > renderRect.getHeight()) {
            this.a.y = this.b;
        } else {
            this.a.y = this.c;
        }
        this.a.x = renderRect.getWidth();
    }

    public RenderRect getRenderRect() {
        return null;
    }
}
