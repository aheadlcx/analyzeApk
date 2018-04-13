package cn.v6.sixrooms.room.gift;

import cn.v6.sixrooms.room.gift.PoseConfig.PoseBean;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.AnimSceneFactory;
import cn.v6.sixrooms.surfaceanim.specialframe.poseframe.PoseScene;
import cn.v6.sixrooms.surfaceanim.specialframe.poseframe.PoseScene.PoseSceneParameter;

public class GiftPoseFactory extends AnimSceneFactory {
    public AnimScene[] generateAnimScene(Object obj) {
        Gift gift = (Gift) obj;
        PoseBean pose = PoseConfig.getInstance().getPose(gift.getNum());
        if (pose == null) {
            return null;
        }
        SceneParameter poseSceneParameter = new PoseSceneParameter();
        poseSceneParameter.setTotalTime((float) pose.getTotalt());
        poseSceneParameter.setCompoundTime((float) pose.getCompoundt());
        poseSceneParameter.setIconUrl(gift.getMpic().getImg());
        poseSceneParameter.setPointConfigP(pose.getY());
        poseSceneParameter.setPointConfigL(pose.getX());
        return new AnimScene[]{new PoseScene(poseSceneParameter)};
    }
}
