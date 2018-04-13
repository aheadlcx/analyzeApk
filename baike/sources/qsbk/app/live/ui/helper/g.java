package qsbk.app.live.ui.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.widget.EditText;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.ToastUtil;

final class g implements OnClickListener {
    final /* synthetic */ EditText a;
    final /* synthetic */ User b;

    g(EditText editText, User user) {
        this.a = editText;
        this.b = user;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        String trim = this.a.getText().toString().trim();
        if (TextUtils.isEmpty(trim) || !InspectHelper.isInteger(trim)) {
            ToastUtil.Short("请输入数字");
        } else {
            NetRequest.getInstance().post(UrlConstants.ADMIN_WEIGHT, new h(this, trim));
        }
    }
}
