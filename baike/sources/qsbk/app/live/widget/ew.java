package qsbk.app.live.widget;

import com.baidu.mobstat.Config;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.widget.GuessBetView.ClickListenner;

class ew implements ClickListenner {
    final /* synthetic */ GuessBigOrSmallGameView a;

    ew(GuessBigOrSmallGameView guessBigOrSmallGameView) {
        this.a = guessBigOrSmallGameView;
    }

    public void clickListenner(boolean z, long j) {
        if ((!AppUtils.getInstance().getUserInfoProvider().isLogin() ? 1 : 0) != 0) {
            AppUtils.getInstance().getUserInfoProvider().toLogin(this.a.mLiveBaseActivity, 1001);
        } else if (z) {
            this.a.mLiveBaseActivity.sendLiveMessageAndRefreshUI(LiveMessage.createGameBetMessage(AppUtils.getInstance().getUserInfoProvider().getUserId(), this.a.a.getGameId(), this.a.a.getGameRoundId(), j, 0));
        } else {
            if (this.a.L == -100) {
                this.a.ad.setText("您已参与过游戏，无法参与");
            } else if (this.a.L == -200) {
                this.a.ad.setText("游戏已开始，无法参与");
            }
            this.a.ad.setVisibility(0);
            this.a.m.postDelayed(new ex(this), Config.BPLUS_DELAY_TIME);
        }
    }
}
