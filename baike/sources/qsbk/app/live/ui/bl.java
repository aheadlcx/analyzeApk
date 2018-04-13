package qsbk.app.live.ui;

import android.view.ViewStub;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveEnterMessage;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.widget.SuperUserEnterAnimLayout;

class bl implements Runnable {
    final /* synthetic */ LiveMessage a;
    final /* synthetic */ LiveBaseActivity b;

    bl(LiveBaseActivity liveBaseActivity, LiveMessage liveMessage) {
        this.b = liveBaseActivity;
        this.a = liveMessage;
    }

    public void run() {
        LiveEnterMessage liveEnterMessage = (LiveEnterMessage) this.a;
        if (this.b.isSuperUserComing(liveEnterMessage) && !liveEnterMessage.isSmallFrameAnim()) {
            if (this.b.al == null) {
                ViewStub viewStub = (ViewStub) this.b.findViewById(R.id.special_user_enter_view_stub);
                if (viewStub != null) {
                    this.b.al = (SuperUserEnterAnimLayout) viewStub.inflate();
                    this.b.al.setLiveMessageListener(this.b);
                }
            }
            if (this.b.al != null) {
                this.b.al.addEnterMessage(liveEnterMessage);
            }
        } else if (ConfigInfoUtil.instance().getEnterConfigByLevel(liveEnterMessage.getActivityLevel()) != null) {
            this.b.af.setVisibility(0);
            this.b.af.addEnterMessage(liveEnterMessage);
        } else if (liveEnterMessage.getUserLevel() > ConfigInfoUtil.instance().getEnterMarqueeMinLevel() || liveEnterMessage.isSmallFrameAnim()) {
            this.b.af.setVisibility(0);
            this.b.af.addEnterMessage(liveEnterMessage);
        } else if (liveEnterMessage.getUser() != null && liveEnterMessage.getUser().showFamilyEnterEffect()) {
            this.b.af.setVisibility(0);
            this.b.af.addEnterMessage(liveEnterMessage);
        }
    }
}
