package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.BaseUserInfo;

class wb implements OnClickListener {
    final /* synthetic */ BaseUserInfo a;
    final /* synthetic */ MyInfoActivity b;

    wb(MyInfoActivity myInfoActivity, BaseUserInfo baseUserInfo) {
        this.b = myInfoActivity;
        this.a = baseUserInfo;
    }

    public void onClick(View view) {
        MyInfoActivity.launch(this.b, this.a.userId);
    }
}
