package cn.v6.sixrooms.surfaceanim.protocolframe;

import cn.v6.sixrooms.surfaceanim.AnimSceneElement;
import cn.v6.sixrooms.surfaceanim.protocol.SceneBean;

final class a extends ProtocolScene {
    final /* synthetic */ SceneBean a;

    a(SceneBean sceneBean) {
        this.a = sceneBean;
    }

    protected final int initMaxFrames() {
        return this.a.getFrames();
    }

    protected final void initResources() {
    }

    protected final void initSceneElement() {
        if (this.mIsSceneElementLoaded) {
            for (AnimSceneElement resetAnimEntities : getAnimSceneElements()) {
                resetAnimEntities.resetAnimEntities();
            }
            return;
        }
        for (int i = 0; i < this.a.getEleCount(); i++) {
            addElement(ProtocolElementBuilder.createElement(this, this.a.getElements()[i]));
        }
    }
}
