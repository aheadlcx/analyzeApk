package qsbk.app.activity.publish;

import android.view.View;
import android.view.View.OnClickListener;

class bf implements OnClickListener {
    final /* synthetic */ PublishActivity a;

    bf(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onClick(View view) {
        this.a.B = true;
        this.a.c(true);
        this.a.F();
        this.a.b("on");
    }
}
