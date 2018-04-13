package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;

class ig implements OnClickListener {
    final /* synthetic */ OfficialInfoActivity a;

    ig(OfficialInfoActivity officialInfoActivity) {
        this.a = officialInfoActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
