package qsbk.app.activity;

import android.support.v4.view.ViewPager.OnPageChangeListener;

class fe implements OnPageChangeListener {
    final /* synthetic */ ChooseQiuyouActivity a;

    fe(ChooseQiuyouActivity chooseQiuyouActivity) {
        this.a = chooseQiuyouActivity;
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        if (i == 0) {
            this.a.g.setTextColor(-17899);
            this.a.h.setTextColor(-3355444);
            this.a.i.setTextColor(-3355444);
        } else if (i == 1) {
            this.a.g.setTextColor(-3355444);
            this.a.h.setTextColor(-17899);
            this.a.i.setTextColor(-3355444);
        } else if (i == 2) {
            this.a.g.setTextColor(-3355444);
            this.a.h.setTextColor(-3355444);
            this.a.i.setTextColor(-17899);
        }
    }

    public void onPageScrollStateChanged(int i) {
    }
}
