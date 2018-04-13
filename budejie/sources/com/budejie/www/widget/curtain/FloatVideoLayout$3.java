package com.budejie.www.widget.curtain;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.budejie.www.http.i;
import com.budejie.www.http.j;
import com.budejie.www.util.an;
import com.budejie.www.util.d;

class FloatVideoLayout$3 implements OnClickListener {
    final /* synthetic */ FloatVideoLayout a;

    FloatVideoLayout$3(FloatVideoLayout floatVideoLayout) {
        this.a = floatVideoLayout;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recommend_post_item_pause:
                FloatVideoLayout.b(this.a);
                return;
            case R.id.ll_user_view:
                if (FloatVideoLayout.c(this.a) != null) {
                    FloatVideoLayout.c(this.a).a(view, FloatVideoLayout.d(this.a), "");
                    return;
                }
                return;
            case R.id.recommend_post_comment:
                if (FloatVideoLayout.c(this.a) != null) {
                    FloatVideoLayout.c(this.a).e(view, FloatVideoLayout.d(this.a));
                    return;
                }
                return;
            case R.id.recommend_post_item_pause_forword:
                if (FloatVideoLayout.c(this.a) != null) {
                    FloatVideoLayout.c(this.a).a(view, FloatVideoLayout.d(this.a), FloatVideoLayout.e(this.a));
                    return;
                }
                return;
            case R.id.recommend_post_item_pause_like:
                if (!an.a(FloatVideoLayout.f(this.a))) {
                    an.a((Activity) FloatVideoLayout.f(this.a), FloatVideoLayout.f(this.a).getString(R.string.nonet), -1).show();
                } else if (!view.isSelected() && !"cai".equals(FloatVideoLayout.d(this.a).getCai_flag())) {
                    i.a(FloatVideoLayout.f(this.a).getString(R.string.track_event_top_post), j.a(FloatVideoLayout.d(this.a)), j.b(FloatVideoLayout.f(this.a), FloatVideoLayout.d(this.a)));
                    d.a(FloatVideoLayout.f(this.a), view, "1");
                    view.setSelected(true);
                    FloatVideoLayout.d(this.a).setLove(FloatVideoLayout.d(this.a).getLove() + 1);
                    FloatVideoLayout.d(this.a).setFlag("ding");
                } else {
                    return;
                }
                if (FloatVideoLayout.c(this.a) != null) {
                    FloatVideoLayout.c(this.a).a(view, FloatVideoLayout.d(this.a));
                    return;
                }
                return;
            case R.id.recommend_post_item_pause_comment:
                if (FloatVideoLayout.c(this.a) != null) {
                    FloatVideoLayout.c(this.a).d(view, FloatVideoLayout.d(this.a));
                    return;
                }
                return;
            case R.id.recommend_post_item_pause_shit:
                if (!an.a(FloatVideoLayout.f(this.a))) {
                    an.a((Activity) FloatVideoLayout.f(this.a), FloatVideoLayout.f(this.a).getString(R.string.nonet), -1).show();
                } else if (!view.isSelected() && !"ding".equals(FloatVideoLayout.d(this.a).getFlag())) {
                    i.a(FloatVideoLayout.f(this.a).getString(R.string.track_event_down_post), j.a(FloatVideoLayout.d(this.a)), j.b(FloatVideoLayout.f(this.a), FloatVideoLayout.d(this.a)));
                    d.a(FloatVideoLayout.f(this.a), view, "1");
                    view.setSelected(true);
                    FloatVideoLayout.d(this.a).setCai(FloatVideoLayout.d(this.a).getCai() + 1);
                    FloatVideoLayout.d(this.a).setCai_flag("cai");
                } else {
                    return;
                }
                if (FloatVideoLayout.c(this.a) != null) {
                    FloatVideoLayout.c(this.a).b(view, FloatVideoLayout.d(this.a));
                    return;
                }
                return;
            default:
                return;
        }
    }
}
