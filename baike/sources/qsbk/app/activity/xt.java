package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;
import qsbk.app.model.NewFan;
import qsbk.app.model.UserInfo;

class xt implements OnClickListener {
    final /* synthetic */ NewFansActivity a;

    xt(NewFansActivity newFansActivity) {
        this.a = newFansActivity;
    }

    public void onClick(View view) {
        NewFansActivity.a(this.a, true);
        NewFansActivity.a(this.a, (UserInfo) NewFansActivity.c(this.a).get(NewFansActivity.b(this.a)));
        NewFansActivity.a(this.a, ((NewFan) NewFansActivity.c(this.a).get(NewFansActivity.b(this.a))).userId);
        NewFansActivity.c(this.a).remove(NewFansActivity.b(this.a));
        if (NewFansActivity.n(this.a).size() - 1 > NewFansActivity.b(this.a) && NewFansActivity.b(this.a) >= 0) {
            NewFansActivity.n(this.a).remove(NewFansActivity.b(this.a));
        }
        NewFansActivity.b(this.a, NewFansActivity.g(this.a) - 1);
        if (NewFansActivity.g(this.a) <= 0) {
            NewFansActivity.o(this.a).setVisibility(8);
            NewFansActivity.i(this.a).setText("");
            NewFansActivity.h(this.a).setText("我的新粉丝");
            this.a.findViewById(R.id.empty_tips).setVisibility(0);
            return;
        }
        NewFansActivity.h(this.a).setText(String.format("我的新粉丝 (%1$s/%2$s)", new Object[]{Integer.valueOf(NewFansActivity.b(this.a) + 1), Integer.valueOf(NewFansActivity.g(this.a))}));
    }
}
