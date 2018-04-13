package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class y implements OnClickListener {
    final /* synthetic */ FamilyCreatorEnterView a;

    y(FamilyCreatorEnterView familyCreatorEnterView) {
        this.a = familyCreatorEnterView;
    }

    public void onClick(View view) {
        if (this.a.o != null && this.a.n != null && this.a.n.getUserId() > 0) {
            this.a.o.onAnimAvatarClick(this.a.n.getConvertedUser());
        }
    }
}
