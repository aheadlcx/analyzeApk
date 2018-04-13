package qsbk.app.activity;

import qsbk.app.widget.BlackReportDialog.IBlackReportSuccessListener;

class xs implements IBlackReportSuccessListener {
    final /* synthetic */ xr a;

    xs(xr xrVar) {
        this.a = xrVar;
    }

    public void onBlackReportSuccess() {
        NewFansActivity.p(this.a.a);
    }
}
