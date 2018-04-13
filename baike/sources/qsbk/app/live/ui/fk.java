package qsbk.app.live.ui;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.utils.AppUtils;

class fk implements OnClickListener {
    final /* synthetic */ NetworkDiagnosisActivity a;

    fk(NetworkDiagnosisActivity networkDiagnosisActivity) {
        this.a = networkDiagnosisActivity;
    }

    public void onClick(View view) {
        Object obj = this.a.a.getText().toString();
        if (!TextUtils.isEmpty(obj)) {
            AppUtils.copyToClipboard(this.a.getActivity(), obj);
        }
    }
}
