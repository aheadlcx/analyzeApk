package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.LoginPermissionClickDelegate;

class hn implements OnClickListener {
    final /* synthetic */ BaseUserInfo a;
    final /* synthetic */ CircleTopicActivity b;

    hn(CircleTopicActivity circleTopicActivity, BaseUserInfo baseUserInfo) {
        this.b = circleTopicActivity;
        this.a = baseUserInfo;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser != null) {
            MyInfoActivity.launch(view.getContext(), this.a.userId, MyInfoActivity.FANS_ORIGINS[0], new IMChatMsgSource(8, this.a.userId, "来自糗友圈"));
            return;
        }
        LoginPermissionClickDelegate.startLoginActivity(this.b);
    }
}
