package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.live.ui.GiftRankActivity;
import qsbk.app.utils.LiveUtil;

class wc implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ MyInfoActivity b;

    wc(MyInfoActivity myInfoActivity, int i) {
        this.b = myInfoActivity;
        this.a = i;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser != null && this.b.bF != null) {
            Intent intent = new Intent(this.b, GiftRankActivity.class);
            intent.putExtra("user", LiveUtil.convert2User(this.b.bF));
            intent.putExtra("total", this.a);
            this.b.startActivity(intent);
        }
    }
}
