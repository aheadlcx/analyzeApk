package com.budejie.www.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.budejie.www.bean.Fans;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;

class a$2 implements OnClickListener {
    final /* synthetic */ a a;

    a$2(a aVar) {
        this.a = aVar;
    }

    public void onClick(View view) {
        if (!an.a(a.a(this.a))) {
            an.a(a.a(this.a), a.a(this.a).getString(R.string.nonet), -1).show();
        } else if (TextUtils.isEmpty(ai.b(a.a(this.a)))) {
            an.a(a.a(this.a), 0, null, null, 0);
        } else {
            Fans fans = (Fans) view.getTag();
            String relationship = fans.getRelationship();
            if (relationship.equals("2") || relationship.equals("4")) {
                a.a(this.a, fans);
            } else if (relationship.equals("0") || relationship.equals("3")) {
                a.b(this.a, fans);
            }
        }
    }
}
