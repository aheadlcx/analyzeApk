package android.support.v7.widget;

import android.view.View;
import android.view.View.OnLayoutChangeListener;

class cd implements OnLayoutChangeListener {
    final /* synthetic */ SearchView a;

    cd(SearchView searchView) {
        this.a = searchView;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.a.h();
    }
}
