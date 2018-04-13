package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.User;

class jg implements OnClickListener {
    final /* synthetic */ User a;
    final /* synthetic */ int b;
    final /* synthetic */ UserCardDialog c;

    jg(UserCardDialog userCardDialog, User user, int i) {
        this.c = userCardDialog;
        this.a = user;
        this.b = i;
    }

    public void onClick(View view) {
        this.c.dismiss();
        if (this.c.z != null) {
            this.c.z.doSilent(this.a, this.b);
        }
    }
}
