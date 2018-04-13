package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleArticleActivity;

class av implements OnClickListener {
    final /* synthetic */ ChatMsg a;
    final /* synthetic */ String b;
    final /* synthetic */ an c;

    av(an anVar, ChatMsg chatMsg, String str) {
        this.c = anVar;
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
