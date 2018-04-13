package qsbk.app.activity;

import android.content.Intent;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.DebugUtil;
import qsbk.app.widget.BlackReportDialog;
import qsbk.app.widget.BlackReportDialog.IBlackReportSuccessListener;

class yn implements IBlackReportSuccessListener {
    final /* synthetic */ BlackReportDialog a;
    final /* synthetic */ ym b;

    yn(ym ymVar, BlackReportDialog blackReportDialog) {
        this.b = ymVar;
        this.a = blackReportDialog;
    }

    public void onBlackReportSuccess() {
        DebugUtil.debug(OtherInfoActivity.a(), "onBlackReportSuccess");
        OtherInfoActivity.a(this.b.a, Relationship.BLACK);
        OtherInfoActivity.b(this.b.a, OtherInfoActivity.b(this.b.a));
        OtherInfoActivity.c(this.b.a).sendBroadcast(new Intent("QIU_YOU_RELATION_CHANGED"));
        this.a.unregisterListener();
    }
}
