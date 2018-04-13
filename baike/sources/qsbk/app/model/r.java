package qsbk.app.model;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.UserLoginGuideCard.ViewHolder;

final class r implements OnClickListener {
    final /* synthetic */ ViewHolder a;

    r(ViewHolder viewHolder) {
        this.a = viewHolder;
    }

    public void onClick(View view) {
        this.a.qiubaiLogin.performClick();
    }
}
