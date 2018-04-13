package qsbk.app.live.ui.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.widget.EditText;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.ToastUtil;

final class i implements OnClickListener {
    final /* synthetic */ EditText a;
    final /* synthetic */ User b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;

    i(EditText editText, User user, String str, String str2) {
        this.a = editText;
        this.b = user;
        this.c = str;
        this.d = str2;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Object trim = this.a.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            ToastUtil.Short("请输入警告内容");
            return;
        }
        NetRequest.getInstance().post(String.format(UrlConstants.ADMIN_WARN, new Object[]{Long.valueOf(this.b.getOriginId()), Long.valueOf(this.b.getOrigin())}), new j(this, trim));
    }
}
