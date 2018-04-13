package cn.v6.sixrooms.surfaceanim.protocolframe;

import cn.v6.sixrooms.surfaceanim.protocol.ProtocolBean;
import cn.v6.sixrooms.surfaceanim.protocol.SceneBean;

public class ProtocolAnimInitiator {
    public static void loadAnim(ProtocolBean protocolBean) {
        if (AnimProtocolFilter.checkAuthor(protocolBean.getAuthor()) && AnimProtocolFilter.checkVersion(protocolBean.getVersion())) {
            loadScene(protocolBean.getScene());
        }
    }

    public static void loadScene(SceneBean sceneBean) {
        ProtocolSceneConfig.registerSpecialScene(sceneBean.getId(), new a(sceneBean));
    }
}
