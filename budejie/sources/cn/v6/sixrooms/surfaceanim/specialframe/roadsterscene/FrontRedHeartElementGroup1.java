package cn.v6.sixrooms.surfaceanim.specialframe.roadsterscene;

import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;

public class FrontRedHeartElementGroup1 extends FrontRedHeartElementGroup {
    public FrontRedHeartElementGroup1(AnimScene animScene) {
        super(animScene);
    }

    public PointI elementLocation() {
        return new PointI(0, 0);
    }

    public int elementFirstFrame() {
        return 11;
    }
}
