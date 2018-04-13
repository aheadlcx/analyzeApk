package qsbk.app.live.ui;

import qsbk.app.core.utils.DeviceUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.ui.mic.MicConnectDialog.MicConnectClickListener;

class bs implements MicConnectClickListener {
    final /* synthetic */ LiveBaseActivity a;

    bs(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void micConnectClick(int i) {
        int i2;
        if (DeviceUtils.getSystemSDKInt() < 16) {
            ToastUtil.Long(R.string.live_mic_system_low_tips);
            i2 = 4;
        } else {
            i2 = i;
        }
        this.a.sendLiveMessageAndRefreshUI(LiveMessage.createMicMessage(this.a.ax.getOriginId(), i2, this.a.aq, this.a.ax.getOrigin(), this.a.ax.getOriginId()));
    }
}
