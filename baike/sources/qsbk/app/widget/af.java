package qsbk.app.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class af implements OnClickListener {
    final /* synthetic */ BlackReportDialog a;

    af(BlackReportDialog blackReportDialog) {
        this.a = blackReportDialog;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        BlackReportDialog.a(this.a, "");
        dialogInterface.dismiss();
    }
}
