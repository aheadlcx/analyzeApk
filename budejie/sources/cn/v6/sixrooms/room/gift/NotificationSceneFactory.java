package cn.v6.sixrooms.room.gift;

import cn.v6.sixrooms.bean.BroadcastBean;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimSceneFactory;
import cn.v6.sixrooms.surfaceanim.flybanner.notification.NotificationScene;
import cn.v6.sixrooms.surfaceanim.flybanner.notification.NotificationSceneParameter;

public class NotificationSceneFactory implements IAnimSceneFactory {
    public AnimScene[] generateAnimScene(Object obj) {
        if (!(obj instanceof BroadcastBean)) {
            return null;
        }
        SceneParameter notificationSceneParameter = new NotificationSceneParameter();
        BroadcastBean broadcastBean = (BroadcastBean) obj;
        String alias = broadcastBean.getAlias();
        String picuser = broadcastBean.getPicuser();
        String rid = broadcastBean.getRid();
        String uid = broadcastBean.getUid();
        notificationSceneParameter.setRid(rid);
        notificationSceneParameter.setUid(uid);
        notificationSceneParameter.setPicuser(picuser);
        notificationSceneParameter.setUserAliasName(alias);
        return new AnimScene[]{new NotificationScene(notificationSceneParameter)};
    }
}
