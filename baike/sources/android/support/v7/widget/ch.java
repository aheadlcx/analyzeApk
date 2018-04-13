package android.support.v7.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class ch implements OnItemClickListener {
    final /* synthetic */ SearchView a;

    ch(SearchView searchView) {
        this.a = searchView;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.a(i, 0, null);
    }
}
