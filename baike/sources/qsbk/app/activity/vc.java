package qsbk.app.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import qsbk.app.model.Remark;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

class vc implements OnClickListener {
    final /* synthetic */ EditText a;
    final /* synthetic */ uz b;

    vc(uz uzVar, EditText editText) {
        this.b = uzVar;
        this.a = editText;
    }

    public void onClick(View view) {
        Object trim = this.a.getText().toString().trim();
        if (trim.length() > 8) {
            ToastAndDialog.makeNegativeToast(this.b.c, "最多只能8个字").show();
            return;
        }
        this.b.dismiss();
        UIHelper.hideKeyboard(this.b.c);
        if (!TextUtils.equals(trim, this.b.a)) {
            Remark remark = new Remark();
            remark.uid = this.b.c.b;
            remark.state = 1;
            remark.remark = trim;
            this.b.c.bW.uploadedRemark(remark, new vd(this, remark));
        }
    }
}
