package qsbk.app.core.widget;

import android.view.View;
import android.view.View.OnClickListener;

class aa implements OnClickListener {
    final /* synthetic */ UserPicSelectDialog a;

    aa(UserPicSelectDialog userPicSelectDialog) {
        this.a = userPicSelectDialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
        this.a.c.getPicFromContent();
    }
}
