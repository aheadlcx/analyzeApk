package android.support.v4.app;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

class ak implements OnItemClickListener {
    final /* synthetic */ ListFragment a;

    ak(ListFragment listFragment) {
        this.a = listFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.onListItemClick((ListView) adapterView, view, i, j);
    }
}
