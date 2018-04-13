package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.model.CircleComment;

class am implements OnClickListener {
    final /* synthetic */ CircleComment a;
    final /* synthetic */ CircleCommentAdapter b;

    am(CircleCommentAdapter circleCommentAdapter, CircleComment circleComment) {
        this.b = circleCommentAdapter;
        this.a = circleComment;
    }

    public void onClick(View view) {
        String str = this.a.uid;
        if (!"63443".equals(str)) {
            if (QsbkApp.currentUser == null || !QsbkApp.currentUser.userId.equals(str)) {
                MyInfoActivity.launch(view.getContext(), str, MyInfoActivity.FANS_ORIGINS[1]);
            } else {
                MyInfoActivity.launch(view.getContext());
            }
        }
    }
}
