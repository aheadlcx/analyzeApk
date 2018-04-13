package qsbk.app.activity.dialog;

import android.view.View;
import android.view.View.OnClickListener;

class a implements OnClickListener {
    final /* synthetic */ FirstInRemindDailog a;

    a(FirstInRemindDailog firstInRemindDailog) {
        this.a = firstInRemindDailog;
    }

    public void onClick(View view) {
        this.a.setResult(-1);
        this.a.finish();
    }
}
