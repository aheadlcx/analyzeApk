package qsbk.app.live.ui;

import android.view.View;
import android.view.View.OnClickListener;

class fl implements OnClickListener {
    final /* synthetic */ NetworkDiagnosisActivity a;

    fl(NetworkDiagnosisActivity networkDiagnosisActivity) {
        this.a = networkDiagnosisActivity;
    }

    public void onClick(View view) {
        this.a.a();
    }
}
