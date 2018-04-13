package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.widget.BlackReportDialog;

class cz implements OnClickListener {
    final /* synthetic */ ConversationActivity a;

    cz(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public void onClick(View view) {
        BlackReportDialog blackReportDialog = new BlackReportDialog(this.a, this.a.getToId());
        blackReportDialog.registerListener(new da(this, blackReportDialog));
        blackReportDialog.createDialog();
        blackReportDialog.show();
    }
}
