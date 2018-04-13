package cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util;

import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftLevel1Scene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftLevel2Scene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftLevel2SceneSp;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftLevel3Scene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftLevel3SceneSp;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftScene;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftScene.GiftSceneParameter;
import cn.v6.sixrooms.surfaceanim.util.SceneType;

public abstract class GiftSceneBuilder {
    public static GiftScene createScene(SceneType sceneType, GiftSceneParameter giftSceneParameter) {
        switch (a.a[sceneType.ordinal()]) {
            case 1:
                return new GiftLevel1Scene(giftSceneParameter);
            case 2:
                return new GiftLevel2Scene(giftSceneParameter);
            case 3:
                return new GiftLevel3Scene(giftSceneParameter);
            case 4:
                return new GiftLevel2SceneSp(giftSceneParameter);
            case 5:
                return new GiftLevel3SceneSp(giftSceneParameter);
            default:
                return null;
        }
    }
}
