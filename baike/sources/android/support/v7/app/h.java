package android.support.v7.app;

import android.content.Context;
import android.support.v7.app.AlertController.AlertParams;
import android.support.v7.app.AlertController.RecycleListView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

class h extends ArrayAdapter<CharSequence> {
    final /* synthetic */ RecycleListView a;
    final /* synthetic */ AlertParams b;

    h(AlertParams alertParams, Context context, int i, int i2, CharSequence[] charSequenceArr, RecycleListView recycleListView) {
        this.b = alertParams;
        this.a = recycleListView;
        super(context, i, i2, charSequenceArr);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        if (this.b.mCheckedItems != null && this.b.mCheckedItems[i]) {
            this.a.setItemChecked(i, true);
        }
        return view2;
    }
}
