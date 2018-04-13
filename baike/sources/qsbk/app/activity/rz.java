package qsbk.app.activity;

import com.baidu.mobstat.Config;
import qsbk.app.QsbkApp;
import qsbk.app.utils.HttpUtils;

class rz extends Thread {
    final /* synthetic */ MainActivity a;

    rz(MainActivity mainActivity, String str) {
        this.a = mainActivity;
        super(str);
    }

    public void run() {
        try {
            Thread.sleep(Config.BPLUS_DELAY_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!(!HttpUtils.netIsAvailable() || QsbkApp.hasVerify || QsbkApp.currentUser == null)) {
            QsbkApp.hasVerify = true;
            this.a.startVerifyService();
        }
        this.a.startVersionService();
    }
}
