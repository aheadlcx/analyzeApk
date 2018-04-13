package cn.v6.sixrooms.surfaceanim.protocolframe;

import cn.v6.sixrooms.surfaceanim.animinterface.IFrameRailManager;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.protocol.SceneBean;
import cn.v6.sixrooms.surfaceanim.util.RenderRect;

public class ProtocolFrameRailManager implements IFrameRailManager {
    private PointI a;
    private RenderRect b;
    private int c;
    private SceneBean d;
    private PointI e = new PointI();

    public void setSceneBean(SceneBean sceneBean) {
        this.d = sceneBean;
    }

    public PointI getSpecialPoint() {
        return this.e;
    }

    public PointI getValidRail() {
        switch (this.d.getSupportScreen()) {
            case 0:
            case 1:
                this.a = this.d.getPosition()[0];
                break;
            case 2:
                switch (this.c) {
                    case 1:
                        this.a = this.d.getPosition()[0];
                        break;
                    case 2:
                        this.a = this.d.getPosition()[1];
                        break;
                    default:
                        break;
                }
        }
        return this.a;
    }

    public RenderRect getRenderRect() {
        return this.b;
    }

    public int getOrientation() {
        return this.c;
    }

    public void setAvailRail(PointI pointI) {
    }

    public void resetRail() {
    }

    public void setRenderRect(RenderRect renderRect) {
        this.e.set(renderRect.getWidth(), renderRect.getHeight());
        this.b = renderRect;
        if (renderRect.getWidth() > renderRect.getHeight()) {
            this.c = 2;
        } else {
            this.c = 1;
        }
    }
}
