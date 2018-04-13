package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.widget.BlackReportDialog;

class ym implements OnClickListener {
    final /* synthetic */ OtherInfoActivity a;

    ym(OtherInfoActivity otherInfoActivity) {
        this.a = otherInfoActivity;
    }

    public void onClick(View view) {
        BlackReportDialog blackReportDialog = new BlackReportDialog(this.a, OtherInfoActivity.a(this.a).userId);
        blackReportDialog.registerListener(new yn(this, blackReportDialog));
        blackReportDialog.createDialog();
        blackReportDialog.show();
    }
}
