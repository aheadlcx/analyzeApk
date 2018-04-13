package qsbk.app.live.widget;

import qsbk.app.core.model.LevelData;

class fj implements Runnable {
    final /* synthetic */ LevelData a;
    final /* synthetic */ HighLevelUserEnterView b;

    fj(HighLevelUserEnterView highLevelUserEnterView, LevelData levelData) {
        this.b = highLevelUserEnterView;
        this.a = levelData;
    }

    public void run() {
        this.b.startEnterAnim(this.a);
    }
}
