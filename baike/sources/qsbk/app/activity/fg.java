package qsbk.app.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;

class fg implements OnClickListener {
    final /* synthetic */ CircleArticleActivity a;

    fg(CircleArticleActivity circleArticleActivity) {
        this.a = circleArticleActivity;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser.hasPhone()) {
            this.a.u();
            return;
        }
        AlertDialog create = new Builder(this.a).setTitle("绑定手机后，可以使用图片评论哦").setPositiveButton("绑定手机", new fh(this)).create();
        create.setCanceledOnTouchOutside(true);
        create.show();
    }
}
