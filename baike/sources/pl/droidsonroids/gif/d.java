package pl.droidsonroids.gif;

import java.util.Iterator;

class d implements Runnable {
    final /* synthetic */ GifDrawable a;

    d(GifDrawable gifDrawable) {
        this.a = gifDrawable;
    }

    public void run() {
        Iterator it = this.a.i.iterator();
        while (it.hasNext()) {
            ((AnimationListener) it.next()).onAnimationCompleted();
        }
    }
}
