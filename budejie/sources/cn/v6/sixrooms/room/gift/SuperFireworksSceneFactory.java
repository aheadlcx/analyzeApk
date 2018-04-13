package cn.v6.sixrooms.room.gift;

import cn.v6.sixrooms.room.SuperFireworksBean;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimSceneFactory;
import cn.v6.sixrooms.surfaceanim.flybanner.superfireworks.SuperFireworksScene;
import cn.v6.sixrooms.surfaceanim.flybanner.superfireworks.SuperFireworksSceneParameter;

public class SuperFireworksSceneFactory implements IAnimSceneFactory {
    public AnimScene[] generateAnimScene(Object obj) {
        if (!(obj instanceof SuperFireworksBean)) {
            return null;
        }
        SuperFireworksBean superFireworksBean = (SuperFireworksBean) obj;
        SceneParameter superFireworksSceneParameter = new SuperFireworksSceneParameter();
        String localname = superFireworksBean.getLocalname();
        String localrid = superFireworksBean.getLocalrid();
        String localuid = superFireworksBean.getLocaluid();
        String uname = superFireworksBean.getUname();
        String bg = superFireworksBean.getBg();
        superFireworksSceneParameter.setFromUser(uname);
        superFireworksSceneParameter.setToUser(localname);
        superFireworksSceneParameter.setToRoomUid(localuid);
        superFireworksSceneParameter.setToRoomId(localrid);
        superFireworksSceneParameter.setBgUrl(bg);
        return new AnimScene[]{new SuperFireworksScene(superFireworksSceneParameter)};
    }
}
