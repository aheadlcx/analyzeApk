package qsbk.app.live.widget;

import qsbk.app.live.model.LiveEnterMessage;

class iu implements Runnable {
    final /* synthetic */ SuperUserEnterAnimLayout a;

    iu(SuperUserEnterAnimLayout superUserEnterAnimLayout) {
        this.a = superUserEnterAnimLayout;
    }

    public void run() {
        this.a.removeCallbacks(this);
        if (this.a.e.size() > 0) {
            this.a.setViewContentAndStartAnim((LiveEnterMessage) this.a.e.remove(0));
        }
    }
}
