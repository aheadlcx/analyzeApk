package com.budejie.www.activity.posts;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.budejie.www.activity.auditpost.AuditPostsActivity;
import com.budejie.www.util.an;

class b$6 implements OnClickListener {
    final /* synthetic */ b a;

    b$6(b bVar) {
        this.a = bVar;
    }

    public void onClick(View view) {
        if (!b.b(this.a).getString(R.string.not_new_data_click).equals(b.c(this.a).getText().toString())) {
            return;
        }
        if (an.a(b.b(this.a).g)) {
            this.a.startActivity(new Intent(b.b(this.a), AuditPostsActivity.class));
            return;
        }
        an.a(b.b(this.a), 1, "new", "shenhe", 125);
    }
}
