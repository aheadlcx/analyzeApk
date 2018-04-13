package cn.v6.sixrooms.room.gift;

import cn.v6.sixrooms.room.PublicNoticeBean;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimSceneFactory;
import cn.v6.sixrooms.surfaceanim.flybanner.confession.ConfessionScene;
import cn.v6.sixrooms.surfaceanim.flybanner.confession.ConfessionSceneParameter;

public class ConfessionSceneFactory implements IAnimSceneFactory {
    public AnimScene[] generateAnimScene(Object obj) {
        if (!(obj instanceof PublicNoticeBean)) {
            return null;
        }
        SceneParameter confessionSceneParameter = new ConfessionSceneParameter();
        PublicNoticeBean publicNoticeBean = (PublicNoticeBean) obj;
        String bg = publicNoticeBean.getBg();
        String msg = publicNoticeBean.getMsg();
        String uname = publicNoticeBean.getUname();
        String tname = publicNoticeBean.getTname();
        String trid = publicNoticeBean.getTrid();
        String tuid = publicNoticeBean.getTuid();
        confessionSceneParameter.setToUser(tname);
        confessionSceneParameter.setFromUser(uname);
        confessionSceneParameter.setText(msg);
        confessionSceneParameter.setBgUrl(bg);
        confessionSceneParameter.setRid(trid);
        confessionSceneParameter.setUid(tuid);
        return new AnimScene[]{new ConfessionScene(confessionSceneParameter)};
    }
}
