package qsbk.app.activity.publish;

import android.content.Intent;
import android.text.TextUtils;
import qsbk.app.utils.ResultActivityListener;

class bo implements ResultActivityListener {
    final /* synthetic */ PublishActivity a;

    bo(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            this.a.m = true;
            this.a.ai = intent.getStringExtra("video_path");
            this.a.ak = intent.getIntExtra("video_width", 0);
            this.a.al = intent.getIntExtra("video_height", 0);
            if (!TextUtils.isEmpty(this.a.ai)) {
                this.a.j.clear();
                this.a.w();
            }
            this.a.a(this.a.ai, this.a.ak, this.a.al);
        }
        this.a.ao = 0;
    }
}
