package com.budejie.www.activity.posts;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.budejie.www.activity.auditpost.AuditPostsActivity;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;

class a$12 implements OnClickListener {
    final /* synthetic */ a a;

    a$12(a aVar) {
        this.a = aVar;
    }

    public void onClick(View view) {
        if (!a.b(this.a).getString(R.string.not_new_data_click).equals(a.k(this.a).getText().toString())) {
            return;
        }
        if (an.a(a.b(this.a).g)) {
            aa.a("PostsFeaturesFragment", "点击了暂无数据");
            this.a.startActivity(new Intent(a.b(this.a), AuditPostsActivity.class));
            return;
        }
        an.a(a.b(this.a), 1, "new", "shenhe", 125);
    }
}
