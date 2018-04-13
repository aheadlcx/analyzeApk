package qsbk.app.activity;

import android.graphics.Point;
import android.view.View;
import qsbk.app.R;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.ShowcaseView.Builder;

class fb implements Runnable {
    final /* synthetic */ fa a;

    fb(fa faVar) {
        this.a = faVar;
    }

    public void run() {
        if (!this.a.a.g.isFinishing()) {
            Point point = new Point(UIHelper.dip2px(this.a.a.g, 180.0f), UIHelper.dip2px(this.a.a.g, 200.0f));
            if (this.a.a.a.getChildCount() > 0) {
                View leftView = this.a.a.a.getLeftView();
                View rightView = this.a.a.a.getRightView();
                if (!(leftView == null || rightView == null)) {
                    int[] iArr = new int[2];
                    leftView.getLocationInWindow(iArr);
                    point.x = ((rightView.getLeft() - leftView.getLeft()) / 2) + iArr[0];
                    point.y = iArr[1] + UIHelper.dip2px(this.a.a.getContext(), 100.0f);
                }
            }
            this.a.a.g.I = new Builder().addShowcase(this.a.a.a.getLeftView(), this.a.a.g.getResources().getDrawable(R.drawable.ic_showcase_checkin_1)).addShowcase(this.a.a.a.getRightView(), this.a.a.g.getResources().getDrawable(R.drawable.ic_showcase_checkin_3)).addShowcase(this.a.a.a.getTitleView(), this.a.a.g.getResources().getDrawable(R.drawable.ic_showcase_checkin_2)).addShowcase(point, this.a.a.g.getResources().getDrawable(R.drawable.ic_showcase_checkin_4)).addShowcase(new Point(UIHelper.dip2px(this.a.a.g, 180.0f), point.y + UIHelper.dip2px(this.a.a.g, 80.0f)), this.a.a.g.getResources().getDrawable(R.drawable.ic_showcase_checkin_5)).setBackgroundColor(this.a.a.g.getResources().getColor(R.color.black_50_percent_transparent)).build(this.a.a.g);
            this.a.a.g.I.show(this.a.a.g);
            SharePreferenceUtils.setSharePreferencesValue("_first_check_in", true);
        }
    }
}
