package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.Article;

class ck implements OnClickListener {
    final /* synthetic */ AuditNativeActivity2 a;

    ck(AuditNativeActivity2 auditNativeActivity2) {
        this.a = auditNativeActivity2;
    }

    public void onClick(View view) {
        this.a.a((Article) this.a.K.get(this.a.J - 1));
    }
}
