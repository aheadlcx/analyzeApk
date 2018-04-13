package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class b implements OnClickListener {
    final /* synthetic */ BaseFragmentActivity a;

    b(BaseFragmentActivity baseFragmentActivity) {
        this.a = baseFragmentActivity;
    }

    public final void onClick(View view) {
        this.a.finish();
    }
}
