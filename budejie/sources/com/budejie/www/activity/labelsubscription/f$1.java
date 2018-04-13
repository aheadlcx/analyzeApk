package com.budejie.www.activity.labelsubscription;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.util.an;

class f$1 implements OnClickListener {
    final /* synthetic */ f a;

    f$1(f fVar) {
        this.a = fVar;
    }

    public void onClick(View view) {
        if (an.a(f.a(this.a))) {
            RecommendSubscription recommendSubscription = (RecommendSubscription) view.getTag();
            Intent intent = new Intent(f.a(this.a), CommonLabelActivity.class);
            intent.putExtra("theme_name", recommendSubscription.getTheme_name());
            intent.putExtra("theme_id", recommendSubscription.getTheme_id());
            f.a(this.a).startActivity(intent);
            return;
        }
        an.a(f.a(this.a), f.a(this.a).getString(R.string.nonet), -1).show();
    }
}
