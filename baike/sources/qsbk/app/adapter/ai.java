package qsbk.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.model.CircleComment;

class ai implements OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ CircleComment b;
    final /* synthetic */ CircleCommentAdapter c;

    ai(CircleCommentAdapter circleCommentAdapter, Context context, CircleComment circleComment) {
        this.c = circleCommentAdapter;
        this.a = context;
        this.b = circleComment;
    }

    public void onClick(View view) {
        if (view.getTag() != null) {
            view.setTag(null);
        } else {
            ((CircleArticleActivity) this.a).reply(this.b);
        }
    }
}
