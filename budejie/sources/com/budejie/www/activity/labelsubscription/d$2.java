package com.budejie.www.activity.labelsubscription;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.util.an;

class d$2 implements OnClickListener {
    final /* synthetic */ d a;

    d$2(d dVar) {
        this.a = dVar;
    }

    public void onClick(View view) {
        if (an.a(d.a(this.a))) {
            RecommendSubscription recommendSubscription = (RecommendSubscription) view.getTag();
            Intent intent = new Intent(d.a(this.a), CommonLabelActivity.class);
            intent.putExtra("theme_name", recommendSubscription.getTheme_name());
            intent.putExtra("theme_id", recommendSubscription.getTheme_id());
            d.a(this.a).startActivity(intent);
            return;
        }
        an.a(d.a(this.a), d.a(this.a).getString(R.string.nonet), -1).show();
    }
}
