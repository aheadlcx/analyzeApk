package android.support.v7.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class ci implements OnItemSelectedListener {
    final /* synthetic */ SearchView a;

    ci(SearchView searchView) {
        this.a = searchView;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.a(i);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
