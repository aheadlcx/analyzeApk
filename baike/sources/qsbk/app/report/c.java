package qsbk.app.report;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class c implements OnCancelListener {
    final /* synthetic */ ReportActivity a;

    c(ReportActivity reportActivity) {
        this.a = reportActivity;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.a.finish();
    }
}
