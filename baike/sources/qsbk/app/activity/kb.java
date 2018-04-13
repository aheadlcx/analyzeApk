package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class kb implements OnClickListener {
    final /* synthetic */ EditInfoBaseActivity a;

    kb(EditInfoBaseActivity editInfoBaseActivity) {
        this.a = editInfoBaseActivity;
    }

    public void onClick(View view) {
        this.a.onCancel(view);
    }
}
