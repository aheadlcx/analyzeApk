package qsbk.app.video;

import qsbk.app.video.VideoRecordActivity.VideoSnippet;
import qsbk.app.ye.videotools.recorder.MediaRecorder;
import qsbk.app.ye.videotools.recorder.MediaRecorder.OnCompletionListener;

class bs implements OnCompletionListener {
    final /* synthetic */ VideoRecordActivity a;

    bs(VideoRecordActivity videoRecordActivity) {
        this.a = videoRecordActivity;
    }

    public void onCompletion(MediaRecorder mediaRecorder) {
        this.a.s.add(new VideoSnippet(this.a, this.a.q, (int) this.a.m()));
        this.a.h.setEnabled(true);
        this.a.h.setAlpha(1.0f);
        this.a.e.update();
        this.a.a(false);
    }
}
