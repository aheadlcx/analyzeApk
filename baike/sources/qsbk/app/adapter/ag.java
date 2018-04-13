package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.fragments.CheckInListFragment.CheckInInfo;

class ag implements OnClickListener {
    final /* synthetic */ CheckInInfo a;
    final /* synthetic */ a b;

    ag(a aVar, CheckInInfo checkInInfo) {
        this.b = aVar;
        this.a = checkInInfo;
    }

    public void onClick(View view) {
        MyInfoActivity.launch(this.b.a.k, this.a.user.userId);
    }
}
