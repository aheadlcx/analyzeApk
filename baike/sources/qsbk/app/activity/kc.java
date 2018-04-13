package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.LogUtil;

class kc implements OnClickListener {
    final /* synthetic */ EditInfoBaseActivity a;

    kc(EditInfoBaseActivity editInfoBaseActivity) {
        this.a = editInfoBaseActivity;
    }

    public void onClick(View view) {
        boolean onSure = this.a.onSure(view);
        LogUtil.d("on sure:" + onSure);
        if (this.a.e && onSure) {
            this.a.a(this.a.getPostParams(), this.a.getPostUrl());
        }
        if (onSure) {
            LogUtil.d("result data:" + this.a.getResultData());
            this.a.setResult(-1, this.a.getResultData());
        } else {
            this.a.setResult(0);
        }
        this.a.finish();
    }
}
