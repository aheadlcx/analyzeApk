package qsbk.app.video;

import android.view.ViewTreeObserver.OnPreDrawListener;

class v implements OnPreDrawListener {
    final /* synthetic */ u a;

    v(u uVar) {
        this.a = uVar;
    }

    public boolean onPreDraw() {
        this.a.d.g.getViewTreeObserver().removeOnPreDrawListener(this);
        this.a.d.p.setText(String.format("%d", new Object[]{Integer.valueOf(Math.round(((float) this.a.d.e()) / 1000.0f))}) + "s");
        return true;
    }
}
