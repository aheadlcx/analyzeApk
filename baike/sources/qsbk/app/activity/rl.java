package qsbk.app.activity;

import qsbk.app.R;
import qsbk.app.ad.feedsad.FeedsAd;
import qsbk.app.utils.UIHelper;

class rl implements Runnable {
    final /* synthetic */ MainActivity a;

    rl(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        FeedsAd.getInstance().init(this.a);
        FeedsAd.getQiuyouCircleInstance().init(this.a);
        UIHelper.getDrawable(R.drawable.not_funny_shake);
        UIHelper.getDrawable(R.drawable.funny_shake);
    }
}
