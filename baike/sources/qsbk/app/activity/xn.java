package qsbk.app.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class xn implements OnItemSelectedListener {
    final /* synthetic */ NewFansActivity a;

    xn(NewFansActivity newFansActivity) {
        this.a = newFansActivity;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        NewFansActivity.d(this.a).setSelection(i);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
