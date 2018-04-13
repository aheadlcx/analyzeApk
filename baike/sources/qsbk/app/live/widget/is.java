package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class is implements OnClickListener {
    final /* synthetic */ SuperUserEnterAnimLayout a;

    is(SuperUserEnterAnimLayout superUserEnterAnimLayout) {
        this.a = superUserEnterAnimLayout;
    }

    public void onClick(View view) {
        if (this.a.r != null && this.a.g != null && this.a.g.getUserId() > 0) {
            this.a.r.onAnimAvatarClick(this.a.g.getConvertedUser());
        }
    }
}
