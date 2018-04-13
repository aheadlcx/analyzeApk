package qsbk.app.activity.security;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ToastAndDialog;

class n implements OnClickListener {
    final /* synthetic */ EmailBindActivity a;

    n(EmailBindActivity emailBindActivity) {
        this.a = emailBindActivity;
    }

    public void onClick(View view) {
        if (!HttpUtils.isNetworkConnected(this.a)) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, this.a.getResources().getString(R.string.network_not_connected), Integer.valueOf(0)).show();
        } else if (this.a.n()) {
            this.a.submit();
        }
    }
}
