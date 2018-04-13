package qsbk.app.live.ui;

import android.content.Intent;
import qsbk.app.core.widget.DialogFragment;
import qsbk.app.core.widget.SimpleDialog.Builder;
import qsbk.app.live.R;

class ef extends Builder {
    final /* synthetic */ LivePushActivity a;

    ef(LivePushActivity livePushActivity, int i) {
        this.a = livePushActivity;
        super(i);
    }

    public void onPositiveActionClicked(DialogFragment dialogFragment) {
        super.onPositiveActionClicked(dialogFragment);
        try {
            this.a.startActivity(new Intent("android.settings.SETTINGS"));
        } catch (Exception e) {
            this.a.f(this.a.getString(R.string.video_record_turn_on_recorder_permission));
        }
    }

    public void onNegativeActionClicked(DialogFragment dialogFragment) {
        super.onNegativeActionClicked(dialogFragment);
    }
}
