package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.User;
import qsbk.app.core.utils.DeviceUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.live.R;

class jm implements OnClickListener {
    final /* synthetic */ User a;
    final /* synthetic */ UserCardDialog b;

    jm(UserCardDialog userCardDialog, User user) {
        this.b = userCardDialog;
        this.a = user;
    }

    public void onClick(View view) {
        this.b.dismiss();
        if (DeviceUtils.getSystemSDKInt() < 16) {
            ToastUtil.Long(R.string.live_mic_system_low_tips);
        } else if (this.b.z != null) {
            this.b.z.doMicConnect(this.a);
        }
    }
}
