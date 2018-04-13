package com.budejie.www.activity.detail;

import android.content.Intent;
import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.activity.HotCommentShareActivity;
import com.budejie.www.util.al.b;

class c$16 implements b {
    final /* synthetic */ c a;

    c$16(c cVar) {
        this.a = cVar;
    }

    public void a(String str) {
        this.a.b = null;
        if (c.aj(this.a) != null) {
            c.aj(this.a).setVisibility(8);
        }
        if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent(c.c(this.a), HotCommentShareActivity.class);
            intent.putExtra("hot_comment_share_post", c.d(this.a));
            intent.putExtra("hot_comment_share_image", str);
            c.c(this.a).startActivity(intent);
        }
    }

    public void a() {
        if (c.af(this.a) != null) {
            c.af(this.a).setText(R.string.screen_shot_processing);
            c.af(this.a).setClickable(false);
        }
    }

    public void b() {
        if (c.aj(this.a) != null) {
            c.aj(this.a).setVisibility(0);
            if (c.af(this.a) != null) {
                c.af(this.a).setText(R.string.screen_shot_stop);
                c.af(this.a).setClickable(true);
            }
        }
    }
}
