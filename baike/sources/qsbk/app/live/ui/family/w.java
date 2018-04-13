package qsbk.app.live.ui.family;

import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import qsbk.app.core.utils.WindowUtils;

class w implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ FamilyDetailActivity b;

    w(FamilyDetailActivity familyDetailActivity, int i) {
        this.b = familyDetailActivity;
        this.a = i;
    }

    public void run() {
        int lineCount = this.b.g.getLineCount() < this.a ? this.b.g.getLineCount() : this.a;
        LayoutParams layoutParams = (LayoutParams) this.b.u.getLayoutParams();
        layoutParams.height = WindowUtils.dp2Px(209) + (this.b.g.getLineHeight() * lineCount);
        this.b.u.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.b.g.getLayoutParams();
        layoutParams2.height = lineCount * this.b.g.getLineHeight();
        this.b.g.setLayoutParams(layoutParams2);
        this.b.g.invalidate();
    }
}
