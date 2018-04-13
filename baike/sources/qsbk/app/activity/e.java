package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.ToastAndDialog;

class e implements OnClickListener {
    final /* synthetic */ AboutSettingActivity a;

    e(AboutSettingActivity aboutSettingActivity) {
        this.a = aboutSettingActivity;
    }

    public void onClick(View view) {
        ToastAndDialog.makeNeutralToast(this.a, "检测新版本，请稍候...", Integer.valueOf(0)).show();
        this.a.b.postDelayed(new f(this), 3000);
    }
}
