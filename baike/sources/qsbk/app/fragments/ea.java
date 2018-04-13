package qsbk.app.fragments;

import java.io.File;
import qsbk.app.im.VoiceHelper.VoiceListener;
import qsbk.app.utils.ToastAndDialog;

class ea implements VoiceListener {
    final /* synthetic */ LaiseeVoiceGetFragment a;

    ea(LaiseeVoiceGetFragment laiseeVoiceGetFragment) {
        this.a = laiseeVoiceGetFragment;
    }

    public void onRecordComplete(File file, long j) {
        this.a.b.setEnabled(false);
        this.a.g.setImageResource(0);
        this.a.g.setVisibility(8);
        this.a.a(file, j);
    }

    public void onRecordError(String str) {
        ToastAndDialog.makeText(this.a.getActivity(), str).show();
        this.a.g.setImageResource(0);
        this.a.g.setVisibility(8);
        this.a.b.setEnabled(true);
    }

    public void onRecordStart() {
    }

    public void onRecording(double d) {
        int i;
        if (d < 50.0d) {
            i = this.a.a[0];
        } else if (d > 50.0d && d < 60.0d) {
            i = this.a.a[1];
        } else if (d > 60.0d && d < 70.0d) {
            i = this.a.a[2];
        } else if (d > 70.0d && d < 80.0d) {
            i = this.a.a[3];
        } else if (d <= 80.0d || d >= 90.0d) {
            i = this.a.a[5];
        } else {
            i = this.a.a[4];
        }
        this.a.g.setImageResource(i);
        this.a.g.setVisibility(0);
    }
}
