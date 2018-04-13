package android.support.v7.widget;

import android.view.View;
import android.view.View.OnFocusChangeListener;

class cc implements OnFocusChangeListener {
    final /* synthetic */ SearchView a;

    cc(SearchView searchView) {
        this.a = searchView;
    }

    public void onFocusChange(View view, boolean z) {
        if (this.a.f != null) {
            this.a.f.onFocusChange(this.a, z);
        }
    }
}
