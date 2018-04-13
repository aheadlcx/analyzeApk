package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;

class ck implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ MemberAdapter b;

    ck(MemberAdapter memberAdapter, a aVar) {
        this.b = memberAdapter;
        this.a = aVar;
    }

    public void onClick(View view) {
        this.b.e.onAction(this.a.a);
    }
}
