package qsbk.app.core.utils;

import android.os.Build.VERSION;

class c implements Runnable {
    final /* synthetic */ AppUtils a;

    c(AppUtils appUtils) {
        this.a = appUtils;
    }

    public void run() {
        boolean z = true;
        AppUtils.a(this.a).removeCallbacks(this);
        this.a.loadConfigInfo();
        GiftResSync.checkUpdate(true);
        if (!AppUtils.a()) {
            long cpuNumCores = (long) DeviceUtils.getCpuNumCores();
            if ((DeviceUtils.getTotalMemorySize(this.a.getAppContext()) >= k.MAX_AGE || cpuNumCores > 4) && VERSION.SDK_INT >= 16) {
                z = false;
            }
            AppUtils.a(z);
        }
    }
}
