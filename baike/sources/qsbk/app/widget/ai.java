package qsbk.app.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ai implements OnClickListener {
    final /* synthetic */ BlackReportDialog a;

    ai(BlackReportDialog blackReportDialog) {
        this.a = blackReportDialog;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        BlackReportDialog.b(this.a, "");
        dialogInterface.dismiss();
    }
}
