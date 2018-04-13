package cn.v6.sixrooms.surfaceanim.animinterface;

import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.util.RenderRect;

public interface IFrameRailManager {
    RenderRect getRenderRect();

    PointI getValidRail();

    void resetRail();

    void setAvailRail(PointI pointI);

    void setRenderRect(RenderRect renderRect);
}
