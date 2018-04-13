package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.MedalListActivity;

class ai implements OnClickListener {
    final /* synthetic */ v a;

    ai(v vVar) {
        this.a = vVar;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser != null) {
            MedalListActivity.launch(this.a.a.h, QsbkApp.currentUser.userId, true);
        }
    }
}
