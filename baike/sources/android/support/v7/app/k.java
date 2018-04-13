package android.support.v7.app;

import android.support.v7.app.AlertController.AlertParams;
import android.support.v7.app.AlertController.RecycleListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class k implements OnItemClickListener {
    final /* synthetic */ RecycleListView a;
    final /* synthetic */ AlertController b;
    final /* synthetic */ AlertParams c;

    k(AlertParams alertParams, RecycleListView recycleListView, AlertController alertController) {
        this.c = alertParams;
        this.a = recycleListView;
        this.b = alertController;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.c.mCheckedItems != null) {
            this.c.mCheckedItems[i] = this.a.isItemChecked(i);
        }
        this.c.mOnCheckboxClickListener.onClick(this.b.a, i, this.a.isItemChecked(i));
    }
}
