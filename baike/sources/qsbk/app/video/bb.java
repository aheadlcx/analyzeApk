package qsbk.app.video;

import android.content.Intent;
import qsbk.app.utils.ResultActivityListener;

class bb implements ResultActivityListener {
    final /* synthetic */ VideoPickerActivity a;

    bb(VideoPickerActivity videoPickerActivity) {
        this.a = videoPickerActivity;
    }

    public void onResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            this.a.setResult(-1, intent);
            this.a.finish();
        }
    }
}
