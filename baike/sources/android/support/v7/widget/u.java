package android.support.v7.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class u implements OnItemClickListener {
    final /* synthetic */ AppCompatSpinner a;
    final /* synthetic */ b b;

    u(b bVar, AppCompatSpinner appCompatSpinner) {
        this.b = bVar;
        this.a = appCompatSpinner;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.b.b.setSelection(i);
        if (this.b.b.getOnItemClickListener() != null) {
            this.b.b.performItemClick(view, i, this.b.a.getItemId(i));
        }
        this.b.dismiss();
    }
}
