package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.User;

class jn implements OnClickListener {
    final /* synthetic */ User a;
    final /* synthetic */ UserCardDialog b;

    jn(UserCardDialog userCardDialog, User user) {
        this.b = userCardDialog;
        this.a = user;
    }

    public void onClick(View view) {
        this.b.dismiss();
        this.b.b(this.a);
    }
}
