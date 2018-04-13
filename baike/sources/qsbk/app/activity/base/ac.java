package qsbk.app.activity.base;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class ac implements OnItemClickListener {
    final /* synthetic */ CommDialogActivity a;

    ac(CommDialogActivity commDialogActivity) {
        this.a = commDialogActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.confirmOption(i);
    }
}
