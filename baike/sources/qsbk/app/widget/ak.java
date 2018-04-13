package qsbk.app.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ak implements OnClickListener {
    final /* synthetic */ BlackReportDialog a;

    ak(BlackReportDialog blackReportDialog) {
        this.a = blackReportDialog;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if (BlackReportDialog.a(this.a) != null) {
            BlackReportDialog.a(this.a).onBlackReportSuccess();
        }
    }
}
