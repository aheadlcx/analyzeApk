package qsbk.app.core.widget;

import android.view.View;
import android.view.View.OnClickListener;

class a implements OnClickListener {
    final /* synthetic */ BaseDialog a;

    a(BaseDialog baseDialog) {
        this.a = baseDialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
    }
}
