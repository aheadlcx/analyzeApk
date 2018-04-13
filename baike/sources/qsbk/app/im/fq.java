package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.UIHelper;

class fq implements OnClickListener {
    final /* synthetic */ IMChatBaseActivityEx a;

    fq(IMChatBaseActivityEx iMChatBaseActivityEx) {
        this.a = iMChatBaseActivityEx;
    }

    public void onClick(View view) {
        this.a.r.setBackgroundColor(UIHelper.isNightTheme() ? -15724269 : -1973791);
        this.a.q.setBackgroundColor(UIHelper.isNightTheme() ? -15263461 : -328966);
        this.a.y();
    }
}
