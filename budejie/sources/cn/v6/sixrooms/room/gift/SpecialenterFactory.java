package cn.v6.sixrooms.room.gift;

import cn.v6.sixrooms.bean.WelcomeBean;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimSceneFactory;
import cn.v6.sixrooms.surfaceanim.specialenterframe.SpecialEnterScene;
import cn.v6.sixrooms.surfaceanim.specialenterframe.SpecialEnterScene.SpecialEnterParameter;
import cn.v6.sixrooms.utils.DrawableResourceUtils;

public class SpecialenterFactory implements IAnimSceneFactory {
    public AnimScene[] generateAnimScene(Object obj) {
        if (!(obj instanceof WelcomeBean)) {
            return null;
        }
        SceneParameter specialEnterParameter = new SpecialEnterParameter();
        WelcomeBean welcomeBean = (WelcomeBean) obj;
        specialEnterParameter.setContent(welcomeBean.getAlias() + " 进入房间");
        if (Integer.parseInt(welcomeBean.getRich()) >= 27) {
            specialEnterParameter.setRichUrl(DrawableResourceUtils.getCustomWealthRankImg(welcomeBean.getUid()));
        } else {
            specialEnterParameter.setRich(welcomeBean.getRich());
        }
        return new AnimScene[]{new SpecialEnterScene(specialEnterParameter)};
    }
}
