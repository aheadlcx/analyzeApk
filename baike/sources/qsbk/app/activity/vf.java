package qsbk.app.activity;

import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.DebugUtil;
import qsbk.app.widget.BlackReportDialog;
import qsbk.app.widget.BlackReportDialog.IBlackReportSuccessListener;

class vf implements IBlackReportSuccessListener {
    final /* synthetic */ BlackReportDialog a;
    final /* synthetic */ MyInfoActivity b;

    vf(MyInfoActivity myInfoActivity, BlackReportDialog blackReportDialog) {
        this.b = myInfoActivity;
        this.a = blackReportDialog;
    }

    public void onBlackReportSuccess() {
        DebugUtil.debug(MyInfoActivity.bB, "onBlackReportSuccess");
        this.b.bD = Relationship.BLACK;
        this.b.a(this.b.bD);
        this.b.a(this.b.bD, this.b.bF.userId);
        this.a.unregisterListener();
    }
}
