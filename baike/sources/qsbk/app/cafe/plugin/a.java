package qsbk.app.cafe.plugin;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.activity.BindPhoneActivity;

class a implements OnClickListener {
    final /* synthetic */ PayUniversalPlugin a;

    a(PayUniversalPlugin payUniversalPlugin) {
        this.a = payUniversalPlugin;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        BindPhoneActivity.launch(this.a.getContext().getCurActivity());
    }
}
