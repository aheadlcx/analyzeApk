package qsbk.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MenuItem;
import java.util.ArrayList;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.fragments.IArticleList;
import qsbk.app.fragments.IPageableFragment;
import qsbk.app.fragments.MyCollectionsFragment;
import qsbk.app.fragments.MyParticipatesFragment;
import qsbk.app.utils.LogUtil;

public class ManageQiuShiActivity extends BaseActionBarActivity {
    private static final String a = ManageQiuShiActivity.class.getSimpleName();
    private ArrayList<Fragment> b = new ArrayList();
    private ViewPager c;
    private QiushiPagerAdapter d;
    private int e;
    private boolean f;

    public static final class QiushiPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> a = new ArrayList();
        private String[] b;

        public QiushiPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> arrayList) {
            super(fragmentManager);
            this.a = arrayList;
        }

        public QiushiPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> arrayList, String[] strArr) {
            super(fragmentManager);
            this.a = arrayList;
            this.b = strArr;
        }

        public CharSequence getPageTitle(int i) {
            return this.b[i];
        }

        public Fragment getItem(int i) {
            return (Fragment) this.a.get(i);
        }

        public int getCount() {
            return this.b.length;
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return getIntent().getBooleanExtra("isFromCollect", false) ? "我的收藏" : "我的参与";
    }

    protected int a() {
        return R.layout.activity_manage_qiushi_new;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.f = getIntent().getBooleanExtra("isFromCollect", false);
        g();
        i();
    }

    private void g() {
        if (this.f) {
            this.b.add(new MyCollectionsFragment());
        } else {
            this.b.add(new MyParticipatesFragment());
        }
    }

    protected void onStop() {
        super.onStop();
        Fragment fragment = (Fragment) this.b.get(this.e);
        if (fragment instanceof IPageableFragment) {
            ((IPageableFragment) fragment).doStop();
        }
    }

    protected void onResume() {
        super.onResume();
        Fragment fragment = (Fragment) this.b.get(this.e);
        if (fragment instanceof IPageableFragment) {
            ((IPageableFragment) fragment).doResume();
        }
    }

    private void i() {
        this.c = (ViewPager) findViewById(R.id.pager);
        this.d = new QiushiPagerAdapter(getSupportFragmentManager(), this.b, this.f ? new String[]{"我的参与"} : new String[]{"我的收藏"});
        this.c.setAdapter(this.d);
        this.e = 0;
        this.c.setCurrentItem(this.e);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = false;
        if (this.c != null && (this.d.getItem(this.c.getCurrentItem()) instanceof IArticleList)) {
            z = ((IArticleList) this.d.getItem(this.c.getCurrentItem())).onKeyDown(i, keyEvent);
        }
        if (z) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                LogUtil.d("on home click");
                finish();
                break;
            case R.id.qiushi_notification:
                QiushiNotificationListActivity.gotoQiushi(this, 0);
                break;
        }
        return false;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            switch (i2) {
                case 0:
                    LogUtil.d("申诉失败");
                    return;
                case 1:
                    ((Fragment) this.d.a.get(0)).onActivityResult(i, i2, intent);
                    return;
                case 100:
                    ((Fragment) this.d.a.get(0)).onActivityResult(i, i2, intent);
                    return;
                case 101:
                    ((Fragment) this.d.a.get(0)).onActivityResult(i, i2, intent);
                    return;
                case 102:
                    ((Fragment) this.d.a.get(0)).onActivityResult(i, i2, intent);
                    return;
                case 9999:
                    ((Fragment) this.d.a.get(0)).onActivityResult(i, i2, intent);
                    return;
                case 10000:
                    ((Fragment) this.d.a.get(0)).onActivityResult(i, i2, intent);
                    return;
                default:
                    return;
            }
        }
    }
}
