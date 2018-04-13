package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.StatService;
import qsbk.app.QsbkApp;
import qsbk.app.utils.SubscribeReportHelper;

class gv implements OnClickListener {
    final /* synthetic */ e a;

    gv(e eVar) {
        this.a = eVar;
    }

    public void onClick(View view) {
        StatService.onEvent(this.a.a.b.getActivity(), "found_click", SubscribeReportHelper.TYPE_GROUP);
        if (QsbkApp.currentUser != null) {
            MyProfileFragment.e(this.a.a.b.getActivity());
        } else {
            MyProfileFragment.f(this.a.a.b.getActivity());
        }
    }
}
