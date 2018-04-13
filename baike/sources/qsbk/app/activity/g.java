package qsbk.app.activity;

import android.content.ActivityNotFoundException;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

class g implements OnClickListener {
    final /* synthetic */ AboutSettingActivity a;

    g(AboutSettingActivity aboutSettingActivity) {
        this.a = aboutSettingActivity;
    }

    public void onClick(View view) {
        try {
            this.a.a(this.a);
        } catch (ActivityNotFoundException e) {
            ToastAndDialog.makePositiveToast(this.a, "感谢您的支持, 我们会更加努力.", Integer.valueOf(0)).show();
        } finally {
            SharePreferenceUtils.setSharePreferencesValue("isRated", "true");
        }
    }
}
