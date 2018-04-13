package qsbk.app.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.utils.UIHelper;

class abo implements OnItemClickListener {
    final /* synthetic */ SearchGroupActivity a;

    abo(SearchGroupActivity searchGroupActivity) {
        this.a = searchGroupActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        String str = (String) this.a.c.get(i);
        this.a.a.setText(str);
        this.a.a.setSelection(str.length());
        UIHelper.hideKeyboard(this.a);
        this.a.a(str);
    }
}
