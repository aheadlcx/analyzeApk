package qsbk.app.ad.feedsad.qbad;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.ad.feedsad.FeedsAdUtils;
import qsbk.app.utils.HttpUtils;

class n implements OnClickListener {
    final /* synthetic */ QbAdItem a;

    n(QbAdItem qbAdItem) {
        this.a = qbAdItem;
    }

    public void onClick(View view) {
        if (QbAdItem.QB_APP.equals(this.a.action_type)) {
            if (this.a.getApkStatus(this.a.getUrlAndPackageNameFromArsg(this.a.action_args)) == 1) {
                Context context = view.getContext();
                String network = HttpUtils.getNetwork(context);
                if (FeedsAdUtils.needShowConfirm(network)) {
                    new Builder(context).setTitle("温馨提示").setCancelable(true).setMessage("当前为" + network + "网络，开始下载应用？").setNegativeButton("取消", new p(this)).setPositiveButton("确认", new o(this, view)).create().show();
                    return;
                } else {
                    this.a.onAppClick(view);
                    return;
                }
            }
            this.a.onAppClick(view);
            return;
        }
        this.a.onAppClick(view);
    }
}
