package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class yl implements OnClickListener {
    final /* synthetic */ OtherInfoActivity a;

    yl(OtherInfoActivity otherInfoActivity) {
        this.a = otherInfoActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
