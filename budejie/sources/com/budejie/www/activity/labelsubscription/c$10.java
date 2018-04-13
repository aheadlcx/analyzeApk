package com.budejie.www.activity.labelsubscription;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.util.ao;

class c$10 implements OnItemClickListener {
    final /* synthetic */ c a;

    c$10(c cVar) {
        this.a = cVar;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        try {
            if (!(c.j(this.a).getItemAtPosition(i) instanceof Integer)) {
                RecommendSubscription recommendSubscription = (RecommendSubscription) c.j(this.a).getItemAtPosition(i);
                Intent intent = new Intent(c.c(this.a), CommonLabelActivity.class);
                intent.putExtra("theme_name", recommendSubscription.getTheme_name());
                String theme_id = recommendSubscription.getTheme_id();
                if (theme_id.matches("[0-9]+")) {
                    intent.putExtra("theme_id", Integer.parseInt(theme_id));
                    c.c(this.a).startActivityForResult(intent, 0);
                }
            } else if (TextUtils.isEmpty(c.h(this.a).getText().toString())) {
                Toast.makeText(c.c(this.a), "请输入要搜索的标签", 0).show();
            } else {
                this.a.c();
                ao.a(c.c(this.a));
                c.a(this.a, c.h(this.a).getText().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
