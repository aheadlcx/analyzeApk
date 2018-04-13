package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class qu implements OnCancelListener {
    final /* synthetic */ LaiseeGetActivity a;

    qu(LaiseeGetActivity laiseeGetActivity) {
        this.a = laiseeGetActivity;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.a.finish();
    }
}
