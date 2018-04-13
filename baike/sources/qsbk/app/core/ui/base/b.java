package qsbk.app.core.ui.base;

import android.view.View;
import android.view.View.OnClickListener;

class b implements OnClickListener {
    final /* synthetic */ BaseActivity a;

    b(BaseActivity baseActivity) {
        this.a = baseActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
