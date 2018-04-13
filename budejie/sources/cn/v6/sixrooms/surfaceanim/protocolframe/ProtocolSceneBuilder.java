package cn.v6.sixrooms.surfaceanim.protocolframe;

import cn.v6.sixrooms.surfaceanim.protocol.SceneBean;

public class ProtocolSceneBuilder {
    public static ProtocolScene createScene(SceneBean sceneBean) {
        ProtocolScene specialScene = ProtocolSceneConfig.getSpecialScene(sceneBean.getId());
        specialScene.setSceneBean(sceneBean);
        return specialScene;
    }
}
