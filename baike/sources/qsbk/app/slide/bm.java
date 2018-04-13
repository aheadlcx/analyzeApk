package qsbk.app.slide;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import qsbk.app.model.Article;
import qsbk.app.utils.ReadQiushi;

class bm implements OnPageChangeListener {
    final /* synthetic */ SlideActivity a;

    bm(SlideActivity slideActivity) {
        this.a = slideActivity;
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        this.a.g = i;
        this.a.setTitle(null);
        this.a.setTitle(this.a.i());
        if (this.a.g > 0 && this.a.g < this.a.c.size()) {
            Object obj = this.a.c.get(i);
            if (obj instanceof Article) {
                ReadQiushi.setRead((Article) obj);
            }
        }
        this.a.a(false);
    }

    public void onPageScrollStateChanged(int i) {
        if (1 == i && this.a.r) {
            this.a.g();
        }
    }
}
