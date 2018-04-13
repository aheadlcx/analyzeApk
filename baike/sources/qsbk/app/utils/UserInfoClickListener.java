package qsbk.app.utils;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.im.IMChatMsgSource;

public class UserInfoClickListener implements OnClickListener {
    String a = null;
    String b = null;
    String c = null;
    String d = null;

    public UserInfoClickListener(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public UserInfoClickListener(String str, String str2, String str3, String str4) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    public void setIMChatMsgSource(String str) {
        this.d = str;
    }

    public void onClick(View view) {
        Context context = view.getContext();
        if (QsbkApp.currentUser == null) {
            MyInfoActivity.launch(context, this.a, true);
        } else if (QsbkApp.currentUser.userId.equals(this.a)) {
            MyInfoActivity.launch(context);
        } else {
            MyInfoActivity.launch(context, this.a, MyInfoActivity.FANS_ORIGINS[0], new IMChatMsgSource(2, this.a, this.d));
        }
    }
}
