package cn.v6.sixrooms.room.gift;

import cn.v6.sixrooms.constants.GiftIdStrs;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimSceneFactory;
import cn.v6.sixrooms.surfaceanim.specialframe.brachildscene.BraChildScene;
import cn.v6.sixrooms.surfaceanim.specialframe.fireworks.FireWorksLevel1Scene;
import cn.v6.sixrooms.surfaceanim.specialframe.fireworks.FireWorksLevel2Scene;
import cn.v6.sixrooms.surfaceanim.specialframe.fireworks.FireWorksLevel3Scene;
import cn.v6.sixrooms.surfaceanim.specialframe.roadsterscene.RoadsterScene;
import cn.v6.sixrooms.surfaceanim.specialframe.shakecucumberscene.CucumberScene;
import cn.v6.sixrooms.surfaceanim.specialframe.util.SpecialSceneBuilder;
import cn.v6.sixrooms.surfaceanim.specialframe.util.SpecialSceneConfig;
import cn.v6.sixrooms.surfaceanim.specialframe.weddingscene.WeddingScene;
import cn.v6.sixrooms.surfaceanim.specialframe.yachtscene.YachtScene;

public class SpecialSceneFactory extends AnimSceneFactory {
    static {
        SpecialSceneConfig.registerSpecialScene(7, RoadsterScene.class);
        SpecialSceneConfig.registerSpecialScene(40, YachtScene.class);
        SpecialSceneConfig.registerSpecialScene(674, CucumberScene.class);
        SpecialSceneConfig.registerSpecialScene(109, WeddingScene.class);
        SpecialSceneConfig.registerSpecialScene(215, BraChildScene.class);
        SpecialSceneConfig.registerSpecialScene(Integer.valueOf(GiftIdStrs.SMALL_FIREWORKS).intValue(), FireWorksLevel1Scene.class);
        SpecialSceneConfig.registerSpecialScene(Integer.valueOf("99").intValue(), FireWorksLevel2Scene.class);
        SpecialSceneConfig.registerSpecialScene(Integer.valueOf(GiftIdStrs.SUPER_FIREWORKS).intValue(), FireWorksLevel3Scene.class);
    }

    public AnimScene[] generateAnimScene(Object obj) {
        int i = 20;
        Gift gift = (Gift) obj;
        int parseInt = Integer.parseInt(gift.getId());
        if (gift.getNum() <= 20) {
            i = gift.getNum();
        }
        AnimScene[] animSceneArr = new AnimScene[i];
        for (int i2 = 0; i2 < i; i2++) {
            animSceneArr[i2] = SpecialSceneBuilder.createScene(parseInt, gift.getLocalResPath());
        }
        return animSceneArr;
    }
}
