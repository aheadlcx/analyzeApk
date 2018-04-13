package qsbk.app.activity;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import qsbk.app.activity.BaseTabActivity.ILoadingState;

class di implements OnPageChangeListener {
    final /* synthetic */ BaseTabActivity a;

    di(BaseTabActivity baseTabActivity) {
        this.a = baseTabActivity;
    }

    public void onPageSelected(int i) {
        if (this.a.b != i) {
            this.a.b = i;
            if (!(this.a.a[this.a.b] instanceof ILoadingState)) {
                return;
            }
            if (((ILoadingState) this.a.a[this.a.b]).isLoading()) {
                this.a.showLoading();
            } else {
                this.a.hideLoading();
            }
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageScrollStateChanged(int i) {
    }
}
