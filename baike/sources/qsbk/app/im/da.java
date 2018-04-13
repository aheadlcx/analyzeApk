package qsbk.app.im;

import qsbk.app.widget.BlackReportDialog;
import qsbk.app.widget.BlackReportDialog.IBlackReportSuccessListener;

class da implements IBlackReportSuccessListener {
    final /* synthetic */ BlackReportDialog a;
    final /* synthetic */ cz b;

    da(cz czVar, BlackReportDialog blackReportDialog) {
        this.b = czVar;
        this.a = blackReportDialog;
    }

    public void onBlackReportSuccess() {
        this.b.a.finish();
        this.a.unregisterListener();
    }
}
