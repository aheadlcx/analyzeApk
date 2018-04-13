package com.budejie.www.activity.labelsubscription;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.umeng.analytics.MobclickAgent;
import java.util.HashMap;
import java.util.Map;

class f$2 implements OnClickListener {
    final /* synthetic */ f a;

    f$2(f fVar) {
        this.a = fVar;
    }

    public void onClick(View view) {
        if (!an.a(f.a(this.a))) {
            an.a(f.a(this.a), f.a(this.a).getString(R.string.nonet), -1).show();
        } else if (TextUtils.isEmpty(ai.b(f.a(this.a)))) {
            an.a(f.a(this.a), 0, null, null, 0);
        } else {
            RecommendSubscription recommendSubscription = (RecommendSubscription) view.getTag();
            String is_sub = recommendSubscription.getIs_sub();
            if ("1".equals(recommendSubscription.getIs_default())) {
                an.a(f.a(this.a), f.a(this.a).getString(R.string.no_cancel_label), -1).show();
            } else if (is_sub.equals("1")) {
                MobclickAgent.onEvent(f.a(this.a), "E02-A02", "点击取消订阅");
                f.a(this.a, recommendSubscription);
            } else if (is_sub.equals("0")) {
                Map hashMap = new HashMap();
                hashMap.put("label", recommendSubscription.getTheme_name());
                an.a(f.a(this.a), hashMap, "E01_A03");
                MobclickAgent.onEvent(f.a(this.a), "E02-A02", "点击订阅");
                f.a(this.a, recommendSubscription, view);
            }
        }
    }
}
