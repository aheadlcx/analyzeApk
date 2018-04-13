package cn.v6.sixrooms.surfaceanim.specialframe.roadsterscene;

import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;

public class FrontRedHeartElementGroup2 extends FrontRedHeartElementGroup {
    public FrontRedHeartElementGroup2(AnimScene animScene) {
        super(animScene);
    }

    public PointI elementLocation() {
        return new PointI(360, 10);
    }

    public int elementFirstFrame() {
        return 11;
    }
}
