package android.support.v7.widget;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class v implements OnGlobalLayoutListener {
    final /* synthetic */ b a;

    v(b bVar) {
        this.a = bVar;
    }

    public void onGlobalLayout() {
        if (this.a.a(this.a.b)) {
            this.a.a();
            super.show();
            return;
        }
        this.a.dismiss();
    }
}
