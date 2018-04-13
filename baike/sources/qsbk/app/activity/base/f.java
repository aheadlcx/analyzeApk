package qsbk.app.activity.base;

import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.loader.AsyncDataLoader;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ToastAndDialog;

class f implements Runnable {
    final /* synthetic */ BaseArticleListViewFragment a;

    f(BaseArticleListViewFragment baseArticleListViewFragment) {
        this.a = baseArticleListViewFragment;
    }

    public void run() {
        if (this.a.n != null) {
            this.a.n.hide();
        }
        if (!this.a.r) {
            this.a.w();
        }
        if (HttpUtils.netIsAvailable()) {
            this.a.o = 1;
            if (this.a.L) {
                this.a.L = false;
                this.a.c("tip");
            } else {
                this.a.c("pull");
            }
            try {
                if (this.a.lastRefreshFirstPageTime == null) {
                    this.a.x = new AsyncDataLoader(this.a.b("load"), "qsbk-AT-BGA-01");
                    this.a.x.execute(new Void[0]);
                    return;
                }
                Long valueOf = Long.valueOf(System.currentTimeMillis());
                if (this.a.ae || valueOf.longValue() - this.a.lastRefreshFirstPageTime.longValue() > ((long) BaseArticleListViewFragment.b)) {
                    this.a.x = new AsyncDataLoader(this.a.b("load"), "qsbk-AT-BGA-02");
                    this.a.x.execute(new Void[0]);
                    return;
                }
                this.a.a(600, true);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.a.a(1000, false);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, this.a.getResources().getString(R.string.network_not_connected), Integer.valueOf(0)).show();
    }
}
