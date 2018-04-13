package qsbk.app.share;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class i implements OnItemClickListener {
    final /* synthetic */ NewShareActivity a;

    i(NewShareActivity newShareActivity) {
        this.a = newShareActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.a((a) this.a.h.get(i));
    }
}
