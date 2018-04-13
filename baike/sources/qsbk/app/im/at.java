package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleArticleActivity;

class at implements OnClickListener {
    final /* synthetic */ ChatMsg a;
    final /* synthetic */ String b;
    final /* synthetic */ am c;

    at(am amVar, ChatMsg chatMsg, String str) {
        this.c = amVar;
        this.a = chatMsg;
        this.b = str;
    }

    public void onClick(View view) {
        if (this.a.type == 27) {
            CircleArticleActivity.launch(this.c.a.h, this.b, false);
        } else {
            this.c.a.c(this.b);
        }
    }
}
