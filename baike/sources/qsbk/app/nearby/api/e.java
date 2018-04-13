package qsbk.app.nearby.api;

import android.app.AlertDialog.Builder;
import qsbk.app.nearby.api.LocationHelper.LocationWarnUIProvider;

class e implements LocationWarnUIProvider {
    final /* synthetic */ LocationHelper a;

    e(LocationHelper locationHelper) {
        this.a = locationHelper;
    }

    public void onShowServiceDisableWarn() {
        new Builder(this.a.k).setTitle("提示").setMessage("无法获取您的位置信息，您可以通过以下操作提高附近的定位精度：在位置设置中打开GPS和无线网络").setPositiveButton("打开定位服务", new g(this)).setNegativeButton("取消", new f(this)).create().show();
    }

    public void onHideServiceDisableWarn() {
    }
}
