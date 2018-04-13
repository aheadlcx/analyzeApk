package qsbk.app.activity;

import qsbk.app.widget.OverScrollView.LoadMoreListener;

class cl implements LoadMoreListener {
    final /* synthetic */ AuditNativeActivity2 a;

    cl(AuditNativeActivity2 auditNativeActivity2) {
        this.a = auditNativeActivity2;
    }

    public void onLoadMore() {
        if (this.a.D) {
            this.a.D = false;
            this.a.d(0);
            this.a.b(2);
        }
    }
}
