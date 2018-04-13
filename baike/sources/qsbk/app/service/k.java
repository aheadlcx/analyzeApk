package qsbk.app.service;

import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.VersionUtil;

class k extends Thread {
    final /* synthetic */ VersionCheckService a;

    k(VersionCheckService versionCheckService, String str) {
        this.a = versionCheckService;
        super(str);
    }

    public void run() {
        try {
            boolean isNeedUpdate = VersionUtil.isNeedUpdate(this.a);
            DebugUtil.debug("是否有新版本存在：" + isNeedUpdate);
            if (isNeedUpdate && VersionCheckService.b()) {
                this.a.b.obtainMessage().sendToTarget();
            } else {
                this.a.stopSelf();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
