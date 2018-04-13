package qsbk.app.activity.publish;

import android.view.View;
import android.view.View.OnClickListener;

class bg implements OnClickListener {
    final /* synthetic */ PublishActivity a;

    bg(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onClick(View view) {
        this.a.B = false;
        this.a.c(false);
        this.a.H();
        this.a.b("off");
    }
}
