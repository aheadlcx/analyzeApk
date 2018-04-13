package qsbk.app.activity.publish;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

class ay implements OnClickListener {
    final /* synthetic */ PublishActivity a;

    ay(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onClick(View view) {
        if (TextUtils.isEmpty(this.a.ai)) {
            this.a.startAlbum();
        } else {
            this.a.a(0).show();
        }
    }
}
