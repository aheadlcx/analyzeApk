package com.budejie.www.activity.labelsubscription;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import cn.v6.sixrooms.room.RoomActivity;
import com.budejie.www.R;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;

class e$2 implements OnClickListener {
    final /* synthetic */ e a;

    e$2(e eVar) {
        this.a = eVar;
    }

    public void onClick(View view) {
        if (!an.a(e.a(this.a))) {
            an.a(e.a(this.a), e.a(this.a).getString(R.string.nonet), -1).show();
        } else if (TextUtils.isEmpty(ai.b(e.a(this.a)))) {
            an.a(e.a(this.a), 0, null, null, 0);
        } else {
            RecommendSubscription recommendSubscription = (RecommendSubscription) view.getTag();
            String is_sub = recommendSubscription.getIs_sub();
            if (is_sub.equals("1")) {
                e.a(this.a, recommendSubscription);
            } else if (is_sub.equals(RoomActivity.VIDEOTYPE_UNKNOWN)) {
                e.b(this.a, recommendSubscription);
            }
        }
    }
}
