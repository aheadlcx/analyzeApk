package qsbk.app.activity.security;

import android.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.tauth.IUiListener;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;

class h implements OnClickListener {
    final /* synthetic */ ActionBarSecurityBindActivity a;

    h(ActionBarSecurityBindActivity actionBarSecurityBindActivity) {
        this.a = actionBarSecurityBindActivity;
    }

    public void onClick(View view) {
        if (TextUtils.isEmpty(this.a.f.getText().toString())) {
            this.a.n = ThirdPartyConstants.THIRDPARTY_TYLE_QQ;
            IUiListener bVar = new b();
            this.a.p = ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, this.a.getApplicationContext());
            this.a.p.login(this.a, "get_user_info,get_simple_userinfo", bVar);
            this.a.w = true;
            return;
        }
        new Builder(this.a).setTitle("操作").setItems(new String[]{"换一个", "取消"}, new i(this)).show();
    }
}
