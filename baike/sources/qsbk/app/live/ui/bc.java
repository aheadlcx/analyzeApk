package qsbk.app.live.ui;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.widget.GameGuessHelpDialog;

class bc implements OnClickListener {
    final /* synthetic */ LiveMessage a;
    final /* synthetic */ LiveBaseActivity b;

    bc(LiveBaseActivity liveBaseActivity, LiveMessage liveMessage) {
        this.b = liveBaseActivity;
        this.a = liveMessage;
    }

    public void onClick(View view) {
        if (this.b.mDialog != null && this.b.mDialog.isShowing()) {
            this.b.mDialog.dismiss();
        }
        this.b.mDialog = new GameGuessHelpDialog(this.b, 1999, "", new bd(this), this.a);
        this.b.mDialog.show();
    }
}
