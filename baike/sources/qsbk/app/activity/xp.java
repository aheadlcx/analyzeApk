package qsbk.app.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import qsbk.app.model.NewFan;
import qsbk.app.widget.PersonalInfoView;

class xp implements OnItemSelectedListener {
    final /* synthetic */ NewFansActivity a;

    xp(NewFansActivity newFansActivity) {
        this.a = newFansActivity;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (i > NewFansActivity.b(this.a)) {
            NewFansActivity.e(this.a).onKeyDown(22, null);
        } else if (i < NewFansActivity.b(this.a)) {
            NewFansActivity.e(this.a).onKeyDown(21, null);
        }
        NewFansActivity.a(this.a, i);
        NewFansActivity.f(this.a).setSelection(i);
        NewFansActivity.h(this.a).setText(String.format("我的新粉丝 (%1$s/%2$s)", new Object[]{Integer.valueOf(i + 1), Integer.valueOf(NewFansActivity.g(this.a))}));
        NewFansActivity.i(this.a).setText("");
        NewFansActivity.a(this.a, (NewFan) NewFansActivity.c(this.a).get(i));
        NewFansActivity.j(this.a);
        NewFansActivity.l(this.a).removeCallbacks(NewFansActivity.k(this.a));
        NewFansActivity.a(this.a, new xq(this, i));
        NewFansActivity.l(this.a).postDelayed(NewFansActivity.k(this.a), 500);
        if (i < NewFansActivity.n(this.a).size()) {
            NewFansActivity.o(this.a).setVisibility(0);
            return;
        }
        NewFansActivity.o(this.a).setVisibility(8);
        ((PersonalInfoView) NewFansActivity.m(this.a).getSelectedView()).setLoadingView();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
