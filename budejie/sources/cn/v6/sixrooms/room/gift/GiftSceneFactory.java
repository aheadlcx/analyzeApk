package cn.v6.sixrooms.room.gift;

import cn.v6.sixrooms.constants.CommonInts;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimSceneFactory;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftScene.GiftSceneParameter;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneBuilder;
import cn.v6.sixrooms.surfaceanim.util.SceneType;
import java.util.Arrays;

public class GiftSceneFactory extends AnimSceneFactory {
    public final String IGNORE_ID = "463";
    private int[] specialNum = new int[]{50, 99, 100, 300, 520, 999, 1314, 2345, 3344, 5200, CommonInts.USER_MANAGER_REQUEST_CODE};

    public AnimScene[] generateAnimScene(Object obj) {
        Gift gift = (Gift) obj;
        if ("463".equals(gift.getId())) {
            return null;
        }
        SceneType sceneType;
        GiftSceneParameter giftSceneParameter = new GiftSceneParameter();
        giftSceneParameter.setFromUserName(gift.getFrom());
        giftSceneParameter.setGiftName(gift.getTitle());
        giftSceneParameter.setIconUrl(gift.getMpic().getImg3x());
        giftSceneParameter.setGiftNum(gift.getNum());
        giftSceneParameter.setResPath(gift.getLocalResPath());
        int parseInt = Integer.parseInt(gift.getPrice()) * gift.getNum();
        if (parseInt < 1000) {
            sceneType = SceneType.SCENE_LEVEL1;
        } else if (parseInt < 5000) {
            if (Arrays.binarySearch(this.specialNum, gift.getNum()) > 0) {
                sceneType = SceneType.SCENE_LEVEL2_SP;
            } else {
                sceneType = SceneType.SCENE_LEVEL2;
            }
        } else if (Arrays.binarySearch(this.specialNum, gift.getNum()) > 0) {
            sceneType = SceneType.SCENE_LEVEL3_SP;
        } else {
            sceneType = SceneType.SCENE_LEVEL3;
        }
        if (sceneType == SceneType.NONE) {
            return null;
        }
        return new AnimScene[]{GiftSceneBuilder.createScene(sceneType, giftSceneParameter)};
    }
}
