package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;

class ak implements OnClickListener {
    final /* synthetic */ CircleCommentAdapter a;

    ak(CircleCommentAdapter circleCommentAdapter) {
        this.a = circleCommentAdapter;
    }

    public void onClick(View view) {
        if (this.a.d == this.a.c) {
            this.a.d = this.a.b;
            if (this.a.j != null) {
                this.a.j.onCommentTabSelect();
            }
            this.a.notifyDataSetChanged();
        }
    }
}
