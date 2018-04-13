package qsbk.app.core.widget;

import android.view.View;
import android.view.View.OnClickListener;

class z implements OnClickListener {
    final /* synthetic */ UserPicSelectDialog a;

    z(UserPicSelectDialog userPicSelectDialog) {
        this.a = userPicSelectDialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
        this.a.c.getPicFromCapture();
    }
}
