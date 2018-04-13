package qsbk.app.activity;

import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.DebugUtil;
import qsbk.app.widget.BlackReportDialog;
import qsbk.app.widget.BlackReportDialog.IBlackReportSuccessListener;

class vh implements IBlackReportSuccessListener {
    final /* synthetic */ BlackReportDialog a;
    final /* synthetic */ MyInfoActivity b;

    vh(MyInfoActivity myInfoActivity, BlackReportDialog blackReportDialog) {
        this.b = myInfoActivity;
        this.a = blackReportDialog;
    }

    public void onBlackReportSuccess() {
        DebugUtil.debug(MyInfoActivity.bB, "onBlackReportSuccess");
        if (Relationship.FRIEND == this.b.bD) {
            this.b.bD = Relationship.FOLLOW_UNREPLIED;
            this.b.a(this.b.bD);
        } else if (Relationship.FAN == this.b.bD) {
            this.b.bD = Relationship.NO_REL;
            this.b.a(this.b.bD);
        }
        this.b.a(this.b.bD, this.b.bF.userId);
        this.a.unregisterListener();
    }
}
