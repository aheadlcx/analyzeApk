package qsbk.app.activity;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.TextView;
import java.util.List;
import pl.droidsonroids.gif.GifImageView;
import qsbk.app.utils.DebugUtil;

class adz implements OnPageChangeListener {
    final /* synthetic */ GifImageView a;
    final /* synthetic */ List b;
    final /* synthetic */ TextView c;
    final /* synthetic */ UserGuideActivity d;

    adz(UserGuideActivity userGuideActivity, GifImageView gifImageView, List list, TextView textView) {
        this.d = userGuideActivity;
        this.a = gifImageView;
        this.b = list;
        this.c = textView;
    }

    public void onPageSelected(int i) {
        if (i < 4) {
            int i2;
            if (i == 3) {
                this.a.setVisibility(8);
            } else {
                this.a.setVisibility(0);
            }
            ((UserGuideActivity$a) this.b.get(i + 1)).invisibleViews();
            if (i > UserGuideActivity.a(this.d)) {
                ((UserGuideActivity$a) this.b.get(i)).moveForwardIn();
            } else if (i < UserGuideActivity.a(this.d)) {
                ((UserGuideActivity$a) this.b.get(i)).moveBackIn();
            }
            List list = this.b;
            if (i > 0) {
                i2 = i - 1;
            } else {
                i2 = 0;
            }
            ((UserGuideActivity$a) list.get(i2)).setMoveOutFlag(false);
        }
        if (4 == i) {
            this.c.setVisibility(8);
            this.a.setVisibility(8);
            this.a.setBackgroundDrawable(null);
            UserGuideActivity.a(this.d, false);
            UserGuideActivity.c(this.d).postDelayed(new aea(this), 800);
        }
        UserGuideActivity.a(this.d, UserGuideActivity.a(this.d));
        UserGuideActivity.b(this.d, i);
    }

    public void onPageScrolled(int i, float f, int i2) {
        DebugUtil.debug(UserGuideActivity.a(), "arg0:" + i + " arg1:" + f + " arg2:" + i2);
        if (i != 4 && UserGuideActivity.c(this.d, i2) && ((double) f) > 0.7d && ((double) f) < 0.8d && !((UserGuideActivity$a) this.b.get(i)).getMoveOutFlag()) {
            ((UserGuideActivity$a) this.b.get(i)).setMoveOutFlag(true);
            ((UserGuideActivity$a) this.b.get(i)).moveForwardOut();
        }
    }

    public void onPageScrollStateChanged(int i) {
        DebugUtil.debug(UserGuideActivity.a(), "onPageScrollStateChanged arg0:" + i);
        if (i == 0 && UserGuideActivity.d(this.d) == UserGuideActivity.a(this.d)) {
            ((UserGuideActivity$a) this.b.get(UserGuideActivity.a(this.d))).moveBackIn();
        }
    }
}
