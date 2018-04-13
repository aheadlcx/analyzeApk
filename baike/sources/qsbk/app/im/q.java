package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.MyInfoActivity;

class q implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ c b;

    q(c cVar, String str) {
        this.b = cVar;
        this.a = str;
    }

    public void onClick(View view) {
        MyInfoActivity.launch(view.getContext(), this.a, MyInfoActivity.FANS_ORIGINS[4], new IMChatMsgSource(9, this.a, "来自直播"));
    }
}
