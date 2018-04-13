package qsbk.app.activity;

import qsbk.app.im.IMNotifyManager;
import qsbk.app.widget.NotificationSettingItem;
import qsbk.app.widget.NotificationSettingItem.OnCheckedChange;

class zx implements OnCheckedChange {
    final /* synthetic */ PrivacyActivity a;

    zx(PrivacyActivity privacyActivity) {
        this.a = privacyActivity;
    }

    public void onCheckedChanged(NotificationSettingItem notificationSettingItem, boolean z) {
        IMNotifyManager.canInvisUserCenter(this.a, z, true);
    }
}
