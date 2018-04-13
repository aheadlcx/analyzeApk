package qsbk.app.activity;

import qsbk.app.widget.OverScrollView.LoadMoreListener;

class de implements LoadMoreListener {
    final /* synthetic */ AuditNativeActivity2 a;

    de(AuditNativeActivity2 auditNativeActivity2) {
        this.a = auditNativeActivity2;
    }

    public void onLoadMore() {
        if (this.a.B) {
            this.a.B = false;
            this.a.U.postDelayed(new df(this), 100);
        }
    }
}
