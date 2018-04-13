package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;

class al implements OnClickListener {
    final /* synthetic */ CircleCommentAdapter a;

    al(CircleCommentAdapter circleCommentAdapter) {
        this.a = circleCommentAdapter;
    }

    public void onClick(View view) {
        if (this.a.d == this.a.b) {
            this.a.d = this.a.c;
            if (this.a.j != null) {
                this.a.j.onCommentTabSelect();
            }
            this.a.notifyDataSetChanged();
        }
    }
}
