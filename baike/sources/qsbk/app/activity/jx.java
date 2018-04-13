package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class jx implements OnClickListener {
    final /* synthetic */ EditIMImageActivity a;

    jx(EditIMImageActivity editIMImageActivity) {
        this.a = editIMImageActivity;
    }

    public void onClick(View view) {
        this.a.setResult(0);
        this.a.finish();
    }
}
