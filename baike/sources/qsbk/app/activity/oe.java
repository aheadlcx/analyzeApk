package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class oe implements OnClickListener {
    final /* synthetic */ GuideActivity a;

    oe(GuideActivity guideActivity) {
        this.a = guideActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
