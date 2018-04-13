package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.model.BaseUserInfo;

class adx implements OnClickListener {
    final /* synthetic */ b a;
    final /* synthetic */ a b;

    adx(a aVar, b bVar) {
        this.b = aVar;
        this.a = bVar;
    }

    public void onClick(View view) {
        String str = ((BaseUserInfo) this.b.getItem(this.a.pos)).userId;
        if (QsbkApp.currentUser.userId.equals(str)) {
            MyInfoActivity.launch(view.getContext());
            return;
        }
        MyInfoActivity.launch(view.getContext(), str, MyInfoActivity.FANS_ORIGINS[1], new IMChatMsgSource(8, str, "来自糗友圈"));
    }
}
