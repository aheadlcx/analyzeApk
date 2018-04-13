package cn.v6.sixrooms.surfaceanim;

import cn.v6.sixrooms.surfaceanim.animinterface.IAnimSceneFactory;

class AnimViewControl$a implements Runnable {
    final /* synthetic */ AnimViewControl a;
    private Object b;
    private IAnimSceneFactory c;

    public AnimViewControl$a(AnimViewControl animViewControl, Object obj, IAnimSceneFactory iAnimSceneFactory) {
        this.a = animViewControl;
        this.b = obj;
        this.c = iAnimSceneFactory;
    }

    public AnimViewControl$a(AnimViewControl animViewControl, Object obj) {
        this.a = animViewControl;
        this.b = obj;
    }

    public final void run() {
        try {
            if (AnimViewControl.a(this.a) != null && AnimViewControl.a(this.a).getAnimSceneFrames() != null) {
                if (this.c != null || AnimViewControl.b(this.a) != null) {
                    AnimScene[] generateAnimScene;
                    if (this.c != null) {
                        generateAnimScene = this.c.generateAnimScene(this.b);
                    } else {
                        generateAnimScene = AnimViewControl.b(this.a).generateAnimScene(this.b);
                    }
                    if (generateAnimScene != null && generateAnimScene.length != 0) {
                        AnimViewControl.a(this.a).addAnimScenes(generateAnimScene);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
