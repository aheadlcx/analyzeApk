package qsbk.app.activity;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

class AuditNativeActivity$a extends PagerAdapter implements OnPageChangeListener {
    List<View> a = null;
    ViewPager b = null;
    ViewGroup c = null;
    final /* synthetic */ AuditNativeActivity d;

    AuditNativeActivity$a(AuditNativeActivity auditNativeActivity, List<View> list, ViewPager viewPager, TextView textView, TextView textView2, ViewGroup viewGroup) {
        this.d = auditNativeActivity;
        this.a = list;
        this.b = viewPager;
        this.c = viewGroup;
    }

    public int getCount() {
        return this.a == null ? 0 : this.a.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) this.a.get(i));
    }

    public int getItemPosition(Object obj) {
        return -2;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        viewGroup.addView((View) this.a.get(i));
        return this.a.get(i);
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        if (i != 0) {
            new Handler(Looper.getMainLooper()).postDelayed(new cf(this, i), 100);
        }
    }
}
