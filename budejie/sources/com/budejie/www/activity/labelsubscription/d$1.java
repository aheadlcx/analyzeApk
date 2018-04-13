package com.budejie.www.activity.labelsubscription;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;

class d$1 implements OnClickListener {
    final /* synthetic */ d a;

    d$1(d dVar) {
        this.a = dVar;
    }

    public void onClick(View view) {
        if (!an.a(d.a(this.a))) {
            an.a(d.a(this.a), d.a(this.a).getString(R.string.nonet), -1).show();
        } else if (TextUtils.isEmpty(ai.b(d.a(this.a)))) {
            an.a(d.a(this.a), 0, null, null, 0);
        } else {
            RecommendSubscription recommendSubscription = (RecommendSubscription) view.getTag();
            String is_sub = recommendSubscription.getIs_sub();
            if (is_sub.equals("1")) {
                d.a(this.a, recommendSubscription);
            } else if (is_sub.equals("0")) {
                d.b(this.a, recommendSubscription);
            }
        }
    }
}
