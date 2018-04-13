package cn.v6.sixrooms.room.gift;

import cn.v6.sixrooms.room.SmallFlyTextBean;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimSceneFactory;
import cn.v6.sixrooms.surfaceanim.smaillfly.SmaillFlyScene;
import cn.v6.sixrooms.surfaceanim.smaillfly.SmaillFlySceneParameter;

public class SmaillFlyScreenSceneFactory implements IAnimSceneFactory {
    public AnimScene[] generateAnimScene(Object obj) {
        if (!(obj instanceof SmallFlyTextBean)) {
            return null;
        }
        SceneParameter smaillFlySceneParameter = new SmaillFlySceneParameter();
        SmallFlyTextBean smallFlyTextBean = (SmallFlyTextBean) obj;
        smaillFlySceneParameter.setText(smallFlyTextBean.content);
        smaillFlySceneParameter.setFromUser(smallFlyTextBean.from);
        smaillFlySceneParameter.setPhotoUrl(smallFlyTextBean.pic);
        return new AnimScene[]{new SmaillFlyScene(smaillFlySceneParameter)};
    }
}
