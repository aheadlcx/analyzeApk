package android.support.v7.widget;

import android.view.View;
import android.view.View.OnClickListener;

class cx implements OnClickListener {
    final /* synthetic */ Toolbar a;

    cx(Toolbar toolbar) {
        this.a = toolbar;
    }

    public void onClick(View view) {
        this.a.collapseActionView();
    }
}
