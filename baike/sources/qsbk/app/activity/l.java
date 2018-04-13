package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.Constants;
import qsbk.app.activity.base.BaseWebviewActivity;

class l implements OnClickListener {
    final /* synthetic */ ActionBarLoginActivity a;

    l(ActionBarLoginActivity actionBarLoginActivity) {
        this.a = actionBarLoginActivity;
    }

    public void onClick(View view) {
        Intent intent = new Intent(this.a, BaseWebviewActivity.class);
        intent.putExtra(BaseWebviewActivity.KEY_CUSTOMER_TITLE, "用户协议");
        intent.putExtra(BaseWebviewActivity.KEY_CUSTOMER_URL, Constants.AGREEMENT);
        this.a.startActivity(intent);
    }
}
