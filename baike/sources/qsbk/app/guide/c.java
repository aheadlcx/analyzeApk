package qsbk.app.guide;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.DebugUtil;

class c implements OnClickListener {
    final /* synthetic */ GuideUtil a;

    c(GuideUtil guideUtil) {
        this.a = guideUtil;
    }

    public void onClick(View view) {
        DebugUtil.debug("luolong", "Guide, mImgView, remove View");
        this.a.g.removeView(this.a.f);
        this.a.g.removeViewImmediate(this.a.f);
    }
}
