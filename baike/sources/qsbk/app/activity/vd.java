package qsbk.app.activity;

import android.text.TextUtils;
import qsbk.app.model.Remark;
import qsbk.app.utils.RemarkManager$UploadCallBack;
import qsbk.app.utils.ToastAndDialog;

class vd implements RemarkManager$UploadCallBack {
    final /* synthetic */ Remark a;
    final /* synthetic */ vc b;

    vd(vc vcVar, Remark remark) {
        this.b = vcVar;
        this.a = remark;
    }

    public void uploadSucc() {
        this.b.b.c.bW.insertOrUpdate(this.a, true);
        ToastAndDialog.makePositiveToast(this.b.b.b, "修改备注成功!").show();
        this.b.b.c.a(this.b.b.c.b);
        this.b.b.c.d();
    }

    public void uploadFail(String str) {
        if (TextUtils.isEmpty(str)) {
            ToastAndDialog.makeNegativeToast(this.b.b.b, "修改备注失败，请重试!").show();
        } else {
            ToastAndDialog.makeNegativeToast(this.b.b.b, str).show();
        }
    }
}
