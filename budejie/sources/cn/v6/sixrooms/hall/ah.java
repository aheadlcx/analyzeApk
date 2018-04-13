package cn.v6.sixrooms.hall;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final class ah implements OnItemClickListener {
    final /* synthetic */ ag a;

    ah(ag agVar) {
        this.a = agVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.a.c != null && this.a.b != null && this.a.b.size() > 0) {
            a a = this.a.c;
            this.a.b.get(i);
            a.a(i);
        }
    }
}
