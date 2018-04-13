package qsbk.app.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PagerSlidingTabStrip;

public abstract class BaseTabActivity extends BaseActionBarActivity {
    protected Fragment[] a;
    protected int b = 0;
    private PagerSlidingTabStrip c;
    private ViewPager d;
    private MyPagerAdapter e;

    public interface ILoadingState {
        boolean isLoading();
    }

    public static final class MyPagerAdapter extends FragmentPagerAdapter {
        private final String[] a;
        private Fragment[] b;

        public MyPagerAdapter(FragmentManager fragmentManager, String[] strArr, Fragment[] fragmentArr) {
            super(fragmentManager);
            this.b = fragmentArr;
            this.a = strArr;
        }

        public CharSequence getPageTitle(int i) {
            return this.a[i];
        }

        public Fragment getItem(int i) {
            return this.b[i];
        }

        public int getCount() {
            return this.a.length;
        }
    }

    protected abstract String[] c();

    protected abstract Fragment[] e();

    protected boolean d() {
        return true;
    }

    protected int a() {
        return R.layout.activity_tab_pager;
    }

    public void showLoadingIfFocus(Fragment fragment) {
        if (fragment == this.a[this.b]) {
            showLoading();
        }
    }

    public void hideLoadingIfFocus(Fragment fragment) {
        if (fragment == this.a[this.b]) {
            hideLoading();
        }
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.c = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        this.d = (ViewPager) findViewById(R.id.pager);
        this.a = e();
        this.e = new MyPagerAdapter(getSupportFragmentManager(), c(), this.a);
        this.d.setAdapter(this.e);
        this.c.setViewPager(this.d, new dh(this));
        this.c.setSelectedTabTextColor(UIHelper.getSelectedTabTextColor());
        this.c.setOnPageChangeListener(new di(this));
    }
}
