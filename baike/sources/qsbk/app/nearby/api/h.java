package qsbk.app.nearby.api;

import qsbk.app.utils.LogUtil;

final class h implements ILocationCallback {
    final /* synthetic */ String a;
    final /* synthetic */ ILocationManager b;

    h(String str, ILocationManager iLocationManager) {
        this.a = str;
        this.b = iLocationManager;
    }

    public void onLocation(int i, double d, double d2, String str, String str2) {
        LogUtil.d("========== test " + this.a + " location================");
        LogUtil.d("ret:" + i);
        LogUtil.d("latitude:" + d);
        LogUtil.d("longtitude:" + d2);
        LogUtil.d("district:" + str);
        LogUtil.d("city:" + str2);
        this.b.stop();
    }
}
