package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.im.IMChatMsgSource;

class be implements OnClickListener {
    final /* synthetic */ CircleVideoCell a;

    be(CircleVideoCell circleVideoCell) {
        this.a = circleVideoCell;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser == null || !QsbkApp.currentUser.userId.equals(this.a.article.user.userId)) {
            MyInfoActivity.launch(view.getContext(), this.a.article.user.userId, MyInfoActivity.FANS_ORIGINS[1], new IMChatMsgSource(8, this.a.article.user.userId, "来自糗友圈"));
            return;
        }
        MyInfoActivity.launch(view.getContext());
    }
}
