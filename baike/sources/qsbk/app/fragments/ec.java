package qsbk.app.fragments;

import android.view.View;
import qsbk.app.widget.RecordButton.OnRecordListener;

class ec implements OnRecordListener {
    final /* synthetic */ LaiseeVoiceGetFragment a;

    ec(LaiseeVoiceGetFragment laiseeVoiceGetFragment) {
        this.a = laiseeVoiceGetFragment;
    }

    public void onStart(View view) {
        this.a.n.startRecord();
    }

    public void onRecording(View view) {
    }

    public void onStop(View view) {
        this.a.n.stopRecord();
    }
}
