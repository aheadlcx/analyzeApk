package qsbk.app.live.widget;

import qsbk.app.core.model.LevelData;
import qsbk.app.core.utils.WindowUtils;

class z implements Runnable {
    final /* synthetic */ LevelData a;
    final /* synthetic */ FamilyCreatorEnterView b;

    z(FamilyCreatorEnterView familyCreatorEnterView, LevelData levelData) {
        this.b = familyCreatorEnterView;
        this.a = levelData;
    }

    public void run() {
        this.b.q = ((WindowUtils.getScreenWidth() - this.b.l.getWidth()) / 2) - this.b.s;
        this.b.startEnterAnim(this.a);
    }
}
