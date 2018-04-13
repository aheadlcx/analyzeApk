package qsbk.app.activity;

import qsbk.app.QsbkApp;
import qsbk.app.widget.NotificationSettingItem;
import qsbk.app.widget.NotificationSettingItem.OnCheckedChange;

class jl implements OnCheckedChange {
    final /* synthetic */ CommonSettingActivity a;

    jl(CommonSettingActivity commonSettingActivity) {
        this.a = commonSettingActivity;
    }

    public void onCheckedChanged(NotificationSettingItem notificationSettingItem, boolean z) {
        QsbkApp.setHttpsEnable(z);
    }
}
