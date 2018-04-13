package qsbk.app.nearby.ui;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;
import qsbk.app.utils.ToastAndDialog;

class j implements OnClickListener {
    final /* synthetic */ InfoCompleteActivity a;

    j(InfoCompleteActivity infoCompleteActivity) {
        this.a = infoCompleteActivity;
    }

    public void onClick(View view) {
        if (this.a.A()) {
            this.a.r();
        } else {
            ToastAndDialog.makeNegativeToast(this.a, this.a.getResources().getString(R.string.edit_gender)).show();
        }
    }
}
