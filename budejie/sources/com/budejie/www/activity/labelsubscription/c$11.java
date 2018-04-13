package com.budejie.www.activity.labelsubscription;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.bean.RecommendSubscription;

class c$11 implements OnItemClickListener {
    final /* synthetic */ c a;

    c$11(c cVar) {
        this.a = cVar;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        try {
            RecommendSubscription recommendSubscription = (RecommendSubscription) c.l(this.a).getItemAtPosition(i);
            if (recommendSubscription.getType().equals("add")) {
                Intent intent = new Intent(c.c(this.a), RecommendLabelActivity.class);
                intent.putExtra("only_seach", true);
                c.c(this.a).startActivity(intent);
                return;
            }
            Intent intent2 = new Intent(c.c(this.a), CommonLabelActivity.class);
            intent2.putExtra("theme_name", recommendSubscription.getTheme_name());
            String theme_id = recommendSubscription.getTheme_id();
            if (theme_id != null && !theme_id.equals("")) {
                intent2.putExtra("theme_id", theme_id);
                c.c(this.a).startActivityForResult(intent2, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
