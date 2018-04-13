package qsbk.app.slide;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;

class r implements OnClickListener {
    final /* synthetic */ SingleArticleFragment a;

    r(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser.hasPhone()) {
            this.a.C();
            return;
        }
        AlertDialog create = new Builder(this.a.getActivity()).setTitle("绑定手机后，可以使用图片评论哦").setPositiveButton("绑定手机", new s(this)).create();
        create.setCanceledOnTouchOutside(true);
        create.show();
    }
}
