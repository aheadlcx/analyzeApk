package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class jf implements OnClickListener {
    final /* synthetic */ UserCardDialog a;

    jf(UserCardDialog userCardDialog) {
        this.a = userCardDialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
    }
}
