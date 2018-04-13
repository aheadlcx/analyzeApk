package qsbk.app.widget;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.model.CircleArticle;

final class q implements OnClickListener {
    final /* synthetic */ Activity a;
    final /* synthetic */ String[] b;
    final /* synthetic */ CircleArticle c;

    q(Activity activity, String[] strArr, CircleArticle circleArticle) {
        this.a = activity;
        this.b = strArr;
        this.c = circleArticle;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        new Builder(this.a).setTitle("举报话题").setMessage("确定举报话题后，将看不到此话题").setPositiveButton(17039370, new r(this, i)).setNegativeButton(17039360, null).create().show();
    }
}
