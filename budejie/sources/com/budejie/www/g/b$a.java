package com.budejie.www.g;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.a;
import com.budejie.www.util.an;
import com.umeng.analytics.MobclickAgent;

public class b$a implements OnClickListener {
    final /* synthetic */ b a;
    private long b;

    public b$a(b bVar) {
        this.a = bVar;
    }

    public void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.b >= 500) {
            this.b = currentTimeMillis;
            MobclickAgent.onEvent(b.a(this.a), "comment_label");
            if (an.a(b.a(this.a))) {
                ListItemObject listItemObject = (ListItemObject) view.getTag();
                if (listItemObject != null) {
                    an.a(listItemObject, b.c(this.a), b.d(this.a));
                    if (view.getId() == R.id.comment_layout || view.getId() == R.id.hot_cmt_content_textview) {
                        a.a(b.a(this.a), listItemObject, "", false, true);
                        return;
                    } else {
                        a.a(b.a(this.a), listItemObject, "", false);
                        return;
                    }
                }
                return;
            }
            b.a(this.a, an.a(b.a(this.a), b.a(this.a).getString(R.string.nonet), -1));
            b.b(this.a).show();
        }
    }
}
