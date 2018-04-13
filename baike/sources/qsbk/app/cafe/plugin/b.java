package qsbk.app.cafe.plugin;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.activity.BindPhoneActivity;

class b implements OnClickListener {
    final /* synthetic */ QbSignPlugin a;

    b(QbSignPlugin qbSignPlugin) {
        this.a = qbSignPlugin;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        BindPhoneActivity.launchForResult(this.a.getContext().getCurActivity(), QbSignPlugin.REQUEST_BIND_PHONE);
    }
}
