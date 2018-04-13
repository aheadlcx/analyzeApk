package cn.v6.sixrooms.ui.phone;

import android.app.Dialog;
import android.os.Bundle;
import cn.v6.sixrooms.utils.DialogUtils;
import com.alibaba.wireless.security.SecExceptionCode;

public class DialogSupportCustomizeActivity extends BaseFragmentActivity {
    private DialogUtils a;
    private Dialog b;

    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        logout();
        if (this.a == null) {
            this.a = new DialogUtils(this);
        }
        if (this.b == null) {
            this.b = this.a.createConfirmDialogs(SecExceptionCode.SEC_ERROR_OPENSDK, "密码修改成功", "请使用新密码重新登录", "现在登录", new y(this));
            this.b.setOnDismissListener(new z(this));
        }
        this.b.show();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.a = null;
        if (this.b != null && this.b.isShowing()) {
            this.b.dismiss();
        }
        this.b = null;
    }
}
