package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.User;
import qsbk.app.live.ui.LiveBaseActivity;

class jq implements OnClickListener {
    final /* synthetic */ User a;
    final /* synthetic */ UserCardDialog b;

    jq(UserCardDialog userCardDialog, User user) {
        this.b = userCardDialog;
        this.a = user;
    }

    public void onClick(View view) {
        this.b.dismiss();
        if (this.b.a instanceof LiveBaseActivity) {
            ((LiveBaseActivity) this.b.a).onAtTa(this.a.name);
        }
    }
}
