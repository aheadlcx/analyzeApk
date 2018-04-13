package com.budejie.www.adapter.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.budejie.www.bean.RecommendSubscription;

class j$1 implements OnClickListener {
    final /* synthetic */ RecommendSubscription a;
    final /* synthetic */ j$c b;
    final /* synthetic */ j c;

    j$1(j jVar, RecommendSubscription recommendSubscription, j$c j_c) {
        this.c = jVar;
        this.a = recommendSubscription;
        this.b = j_c;
    }

    public void onClick(View view) {
        if (j.a.contains(this.a.getTheme_id())) {
            j.a.remove(this.a.getTheme_id());
            j.b.remove(this.a.getTheme_id());
            this.b.b.setImageResource(R.drawable.label_no_selector_icon);
            return;
        }
        j.a.add(this.a.getTheme_id());
        j.b.add(this.a.getTheme_id());
        this.b.b.setImageResource(R.drawable.label_selector_icon);
    }
}
