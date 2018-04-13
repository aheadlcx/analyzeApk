package qsbk.app.activity.publish;

import android.view.View;
import android.view.View.OnClickListener;

class az implements OnClickListener {
    final /* synthetic */ PublishActivity a;

    az(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onClick(View view) {
        if (this.a.j.size() > 0) {
            this.a.a(2).show();
        } else {
            this.a.startVideo();
        }
    }
}
