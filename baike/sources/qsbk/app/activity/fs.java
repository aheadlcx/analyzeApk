package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class fs implements OnClickListener {
    final /* synthetic */ CircleArticleActivity a;

    fs(CircleArticleActivity circleArticleActivity) {
        this.a = circleArticleActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
