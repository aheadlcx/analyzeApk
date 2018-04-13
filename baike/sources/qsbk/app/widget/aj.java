package qsbk.app.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class aj implements OnClickListener {
    final /* synthetic */ BlackReportDialog a;

    aj(BlackReportDialog blackReportDialog) {
        this.a = blackReportDialog;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                BlackReportDialog.a(this.a, "disturb");
                break;
            case 1:
                BlackReportDialog.a(this.a, "cheat");
                break;
            case 2:
                BlackReportDialog.a(this.a, "politics");
                break;
            case 3:
                BlackReportDialog.a(this.a, "porn");
                break;
            case 4:
                BlackReportDialog.a(this.a, "other");
                break;
        }
        dialogInterface.dismiss();
    }
}
