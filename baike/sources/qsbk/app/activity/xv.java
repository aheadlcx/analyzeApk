package qsbk.app.activity;

import qsbk.app.R;

class xv implements Runnable {
    final /* synthetic */ NewFansActivity a;

    xv(NewFansActivity newFansActivity) {
        this.a = newFansActivity;
    }

    public void run() {
        NewFansActivity.r(this.a).setVisibility(8);
        int selectedItemPosition = NewFansActivity.m(this.a).getSelectedItemPosition();
        if (NewFansActivity.c(this.a).size() - 1 > selectedItemPosition && selectedItemPosition >= 0) {
            NewFansActivity.c(this.a).remove(selectedItemPosition);
        }
        if (NewFansActivity.n(this.a).size() - 1 > selectedItemPosition && selectedItemPosition >= 0) {
            NewFansActivity.n(this.a).remove(selectedItemPosition);
        }
        NewFansActivity.b(this.a, NewFansActivity.g(this.a) - 1);
        if (NewFansActivity.g(this.a) <= 0) {
            NewFansActivity.o(this.a).setVisibility(8);
            NewFansActivity.i(this.a).setText("");
            NewFansActivity.h(this.a).setText("我的新粉丝");
            this.a.findViewById(R.id.empty_tips).setVisibility(0);
        } else {
            NewFansActivity.h(this.a).setText(String.format("我的新粉丝 (%1$s/%2$s)", new Object[]{Integer.valueOf(NewFansActivity.b(this.a) + 1), Integer.valueOf(NewFansActivity.g(this.a))}));
        }
        NewFansActivity.d(this.a).notifyDataSetChanged();
        NewFansActivity.f(this.a).notifyDataSetChanged();
        NewFansActivity.q(this.a).setClickable(true);
    }
}
