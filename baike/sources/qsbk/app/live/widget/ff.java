package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class ff implements OnClickListener {
    final /* synthetic */ HighLevelUserEnterView a;

    ff(HighLevelUserEnterView highLevelUserEnterView) {
        this.a = highLevelUserEnterView;
    }

    public void onClick(View view) {
        if (this.a.z != null && this.a.v != null && this.a.v.getUserId() > 0) {
            this.a.z.onAnimAvatarClick(this.a.v.getConvertedUser());
        }
    }
}
