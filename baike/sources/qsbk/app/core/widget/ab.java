package qsbk.app.core.widget;

import android.view.View;
import android.view.View.OnClickListener;

class ab implements OnClickListener {
    final /* synthetic */ UserPicSelectDialog a;

    ab(UserPicSelectDialog userPicSelectDialog) {
        this.a = userPicSelectDialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
    }
}
