package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.im.IMChatMsgSource;

class hg implements OnClickListener {
    final /* synthetic */ CircleTopicActivity a;

    hg(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser == null || !QsbkApp.currentUser.userId.equals(this.a.g.user.userId)) {
            MyInfoActivity.launch(view.getContext(), this.a.g.user.userId, MyInfoActivity.FANS_ORIGINS[1], new IMChatMsgSource(8, this.a.g.user.userId, "来自糗友圈"));
            return;
        }
        MyInfoActivity.launch(view.getContext());
    }
}
