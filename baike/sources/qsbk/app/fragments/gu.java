package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.StatService;
import qsbk.app.QsbkApp;
import qsbk.app.model.RssArticle.Type;

class gu implements OnClickListener {
    final /* synthetic */ e a;

    gu(e eVar) {
        this.a = eVar;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser != null) {
            MyProfileFragment.d(this.a.a.b.getActivity());
        } else {
            MyProfileFragment.f(this.a.a.b.getActivity());
        }
        StatService.onEvent(this.a.a.b.getActivity(), "found_click", Type.NEARBY);
    }
}
