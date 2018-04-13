package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;
import qsbk.app.model.EventWindow;
import qsbk.app.utils.SharePreferenceUtils;

class gx implements OnClickListener {
    final /* synthetic */ c a;
    final /* synthetic */ b b;
    final /* synthetic */ e c;

    gx(e eVar, c cVar, b bVar) {
        this.c = eVar;
        this.a = cVar;
        this.b = bVar;
    }

    public void onClick(View view) {
        MyProfileFragment.b(this.c.a.b.getActivity(), this.a.duobao.link, this.a.duobao.title, this.a.duobao.userToken);
        StatSDK.onEvent(this.c.a.b.getActivity(), "found_click", EventWindow.JUMP_DUOBAO);
        StatService.onEvent(this.c.a.b.getActivity(), "found_click", EventWindow.JUMP_DUOBAO);
        MyProfileFragment.duobaoHasTips = true;
        SharePreferenceUtils.setSharePreferencesValue(MyProfileFragment.DUOBAO_HAS_TIPS, MyProfileFragment.duobaoHasTips);
        this.b.k.setVisibility(8);
    }
}
