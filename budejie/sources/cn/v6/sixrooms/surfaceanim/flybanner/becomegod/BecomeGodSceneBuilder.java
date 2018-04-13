package cn.v6.sixrooms.surfaceanim.flybanner.becomegod;

import cn.v6.sixrooms.surfaceanim.flybanner.becomegod.creator.CreatorGodScene;
import cn.v6.sixrooms.surfaceanim.flybanner.becomegod.god.GodScene;
import cn.v6.sixrooms.surfaceanim.flybanner.becomegod.gods.GodOfGodsScene;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;

public class BecomeGodSceneBuilder {
    public static SpecialScene createScene(BecomeGodSceneParameter becomeGodSceneParameter) {
        switch (b.a[becomeGodSceneParameter.getGodType().ordinal()]) {
            case 1:
                return new GodScene(becomeGodSceneParameter);
            case 2:
                return new GodOfGodsScene(becomeGodSceneParameter);
            case 3:
                return new CreatorGodScene(becomeGodSceneParameter);
            default:
                return null;
        }
    }
}
