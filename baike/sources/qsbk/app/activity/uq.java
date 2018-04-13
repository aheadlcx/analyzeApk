package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.UserInfo;

class uq implements OnClickListener {
    final /* synthetic */ MyInfoActivity a;

    uq(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onClick(View view) {
        if (this.a.bF == null) {
            UserInfo userInfo = new UserInfo();
            userInfo.userId = this.a.b;
            CheckInTopActivity.launch(this.a, userInfo);
            return;
        }
        CheckInTopActivity.launch(this.a, this.a.bF);
    }
}
