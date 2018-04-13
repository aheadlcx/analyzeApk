package android.support.v7.widget;

import android.view.View;
import android.view.View.OnClickListener;

class ce implements OnClickListener {
    final /* synthetic */ SearchView a;

    ce(SearchView searchView) {
        this.a = searchView;
    }

    public void onClick(View view) {
        if (view == this.a.b) {
            this.a.e();
        } else if (view == this.a.d) {
            this.a.d();
        } else if (view == this.a.c) {
            this.a.c();
        } else if (view == this.a.e) {
            this.a.f();
        } else if (view == this.a.a) {
            this.a.i();
        }
    }
}
