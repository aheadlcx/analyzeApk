package qsbk.app.activity;

import qsbk.app.activity.HighLightQiushiActivity.QiushiListFragment;
import qsbk.app.model.EditorMsg;

class oh implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ QiushiListFragment b;

    oh(QiushiListFragment qiushiListFragment, boolean z) {
        this.b = qiushiListFragment;
        this.a = z;
    }

    public void run() {
        if (this.b.B != null) {
            ((HighLightQiushiActivity) this.b.B).j();
            if (!this.a) {
                if (this.b.j == null || this.b.j.size() == 0 || !(this.b.j.get(this.b.j.size() - 1) instanceof EditorMsg)) {
                    ((HighLightQiushiActivity) this.b.B).f();
                }
            }
        }
    }
}
