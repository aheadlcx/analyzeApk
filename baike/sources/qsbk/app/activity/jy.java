package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.AsyncTask;

class jy implements OnClickListener {
    final /* synthetic */ EditIMImageActivity a;

    jy(EditIMImageActivity editIMImageActivity) {
        this.a = editIMImageActivity;
    }

    public void onClick(View view) {
        this.a.a();
        new jz(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
