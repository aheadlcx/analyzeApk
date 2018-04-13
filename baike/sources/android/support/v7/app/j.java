package android.support.v7.app;

import android.support.v7.app.AlertController.AlertParams;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class j implements OnItemClickListener {
    final /* synthetic */ AlertController a;
    final /* synthetic */ AlertParams b;

    j(AlertParams alertParams, AlertController alertController) {
        this.b = alertParams;
        this.a = alertController;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.b.mOnClickListener.onClick(this.a.a, i);
        if (!this.b.mIsSingleChoice) {
            this.a.a.dismiss();
        }
    }
}
