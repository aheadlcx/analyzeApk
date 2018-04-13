package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.StatService;
import qsbk.app.utils.SharePreferenceUtils;

class gw implements OnClickListener {
    final /* synthetic */ c a;
    final /* synthetic */ b b;
    final /* synthetic */ e c;

    gw(e eVar, c cVar, b bVar) {
        this.c = eVar;
        this.a = cVar;
        this.b = bVar;
    }

    public void onClick(View view) {
        StatService.onEvent(this.c.a.b.getActivity(), "found_click", "found_item_chicken");
        MyProfileFragment.c(this.c.a.b.getActivity(), this.a.chicken.link, this.a.chicken.name);
        StatService.onEvent(this.c.a.b.getActivity(), "found_click", "cross");
        FoundFragment.foundChickenHasTips = false;
        SharePreferenceUtils.setSharePreferencesValue(FoundFragment.FOUND_CHICKEN_HAS_TIPS, false);
        this.b.j.setVisibility(4);
    }
}
