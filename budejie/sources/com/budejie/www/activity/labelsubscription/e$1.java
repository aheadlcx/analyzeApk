package com.budejie.www.activity.labelsubscription;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.util.an;

class e$1 implements OnClickListener {
    final /* synthetic */ e a;

    e$1(e eVar) {
        this.a = eVar;
    }

    public void onClick(View view) {
        if (an.a(e.a(this.a))) {
            RecommendSubscription recommendSubscription = (RecommendSubscription) view.getTag();
            Intent intent = new Intent(e.a(this.a), CommonLabelActivity.class);
            intent.putExtra("theme_name", recommendSubscription.getTheme_name());
            intent.putExtra("theme_id", recommendSubscription.getTheme_id());
            e.a(this.a).startActivity(intent);
            return;
        }
        an.a(e.a(this.a), e.a(this.a).getString(R.string.nonet), -1).show();
    }
}
