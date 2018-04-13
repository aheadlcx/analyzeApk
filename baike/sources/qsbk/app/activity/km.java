package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class km implements OnClickListener {
    final /* synthetic */ EventWindowActivity a;

    km(EventWindowActivity eventWindowActivity) {
        this.a = eventWindowActivity;
    }

    public void onClick(View view) {
        this.a.d();
    }
}
