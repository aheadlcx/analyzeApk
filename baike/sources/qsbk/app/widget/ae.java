package qsbk.app.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ae implements OnClickListener {
    final /* synthetic */ BlackReportDialog a;

    ae(BlackReportDialog blackReportDialog) {
        this.a = blackReportDialog;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                this.a.showBlackConfirmDialog();
                return;
            case 1:
                this.a.showReportDialog();
                return;
            default:
                return;
        }
    }
}
