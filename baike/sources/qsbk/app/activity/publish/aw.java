package qsbk.app.activity.publish;

import android.widget.PopupWindow.OnDismissListener;
import qsbk.app.utils.SharePreferenceUtils;

class aw implements OnDismissListener {
    final /* synthetic */ au a;

    aw(au auVar) {
        this.a = auVar;
    }

    public void onDismiss() {
        SharePreferenceUtils.setSharePreferencesValue("showed_qiushi_topic_bubble", true);
    }
}
