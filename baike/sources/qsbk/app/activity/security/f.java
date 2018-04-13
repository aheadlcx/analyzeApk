package qsbk.app.activity.security;

import android.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;

class f implements OnClickListener {
    final /* synthetic */ ActionBarSecurityBindActivity a;

    f(ActionBarSecurityBindActivity actionBarSecurityBindActivity) {
        this.a = actionBarSecurityBindActivity;
    }

    public void onClick(View view) {
        if (TextUtils.isEmpty(this.a.e.getText().toString())) {
            this.a.n = ThirdPartyConstants.THIRDPARTY_TYLE_SINA;
            this.a.m = ThirdParty.getInstance(ThirdPartyConstants.SINA_CONSUMER_KEY, ThirdPartyConstants.SINA_REDIRECT_URL, ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
            this.a.o = new SsoHandler(this.a);
            this.a.o.authorize(new a(this.a));
            this.a.x = true;
            return;
        }
        new Builder(this.a).setTitle("操作").setItems(new String[]{"换一个", "取消"}, new g(this)).show();
    }
}
