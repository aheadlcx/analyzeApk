package qsbk.app.activity;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.Constants;

class ql implements OnClickListener {
    final /* synthetic */ LaiseeDetailActivity a;

    ql(LaiseeDetailActivity laiseeDetailActivity) {
        this.a = laiseeDetailActivity;
    }

    public void onClick(View view) {
        LocalBroadcastManager.getInstance(this.a).sendBroadcast(new Intent(Constants.ACTION_SEND_VOICE_LAISEE_TOO));
        this.a.finish();
    }
}
