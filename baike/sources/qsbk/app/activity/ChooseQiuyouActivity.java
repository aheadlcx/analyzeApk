package qsbk.app.activity;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collection;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.fragments.FansFragment;
import qsbk.app.fragments.FriendsFragment;
import qsbk.app.fragments.MyGroupFragment;
import qsbk.app.fragments.QiuYouFragment;
import qsbk.app.fragments.QiuYouFragment.QiuYouListener;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PagerSlidingTabStrip;

public class ChooseQiuyouActivity extends BaseActionBarActivity implements QiuYouListener {
    private static final String a = ChooseQiuyouActivity.class.getSimpleName();
    private ArrayList<Fragment> b = new ArrayList();
    private PagerSlidingTabStrip c;
    private ViewPager d;
    private QiushiPagerAdapter e;
    private Bundle f;
    private TextView g;
    private TextView h;
    private TextView i;

    public final class QiushiPagerAdapter extends FragmentPagerAdapter {
        final /* synthetic */ ChooseQiuyouActivity a;
        private final String[] b = new String[]{this.a.j(), this.a.getFollowerFromResource(), "群"};
        private ArrayList<Fragment> c = new ArrayList();

        public QiushiPagerAdapter(ChooseQiuyouActivity chooseQiuyouActivity, FragmentManager fragmentManager, ArrayList<Fragment> arrayList) {
            this.a = chooseQiuyouActivity;
            super(fragmentManager);
            this.c = arrayList;
        }

        public CharSequence getPageTitle(int i) {
            return this.b[i];
        }

        public Fragment getItem(int i) {
            return (Fragment) this.c.get(i);
        }

        public int getCount() {
            return this.b.length;
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return c();
    }

    @TargetApi(14)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DebugUtil.debug(a, "onCreate");
    }

    protected String c() {
        return getResources().getString(R.string.choose_contacts);
    }

    protected int a() {
        return R.layout.activity_choose_qiuyou;
    }

    protected void a(Bundle bundle) {
        this.f = getIntent().getExtras();
        setActionbarBackable();
        f();
        i();
    }

    private void f() {
        this.b.add(new FriendsFragment(this.f));
        this.b.add(new FansFragment(this.f));
        this.b.add(MyGroupFragment.newInstance(1));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_qiuyou, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (UIHelper.isNightTheme()) {
            menu.findItem(R.id.action_search).setIcon(R.drawable.add_qiuyou_ic_search_dark);
        } else {
            menu.findItem(R.id.action_search).setIcon(R.drawable.add_qiuyou_ic_search);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_search) {
            return super.onOptionsItemSelected(menuItem);
        }
        g();
        return true;
    }

    private void g() {
        int size = this.b.size();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            Fragment fragment = (Fragment) this.b.get(i);
            if (fragment instanceof QiuYouFragment) {
                Collection cacheData = ((QiuYouFragment) fragment).getCacheData();
                if (cacheData != null && cacheData.size() > 0) {
                    arrayList.addAll(cacheData);
                }
            }
        }
        SearchQiuYouActivity.launch(this, arrayList, true);
    }

    private void i() {
        this.c = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        this.g = (TextView) findViewById(R.id.friend_num);
        this.h = (TextView) findViewById(R.id.fans_num);
        this.i = (TextView) findViewById(R.id.group_num);
        this.d = (ViewPager) findViewById(R.id.pager);
        this.e = new QiushiPagerAdapter(this, getSupportFragmentManager(), this.b);
        this.d.setAdapter(this.e);
        this.d.setOffscreenPageLimit(2);
        this.c.setViewPager(this.d, new fd(this));
        this.c.setOnPageChangeListener(new fe(this));
        this.c.setSelectedTabTextColor(UIHelper.getSelectedTabTextColor());
        this.d.setCurrentItem(0);
        this.g.setTextColor(-17899);
        this.h.setTextColor(-3355444);
        this.i.setTextColor(-3355444);
    }

    private String j() {
        return getResources().getString(R.string.friends);
    }

    public String getFollowerFromResource() {
        return getResources().getString(R.string.follower);
    }

    public void qiuYouNum(int i, String str) {
        if (FriendsFragment.class.getSimpleName().equals(str)) {
            if (i >= 0) {
                this.g.setVisibility(0);
                this.g.setText(String.valueOf(i));
            }
        } else if (FansFragment.class.getSimpleName().equals(str)) {
            if (i >= 0) {
                this.h.setVisibility(0);
                this.h.setText(String.valueOf(i));
            }
        } else if (MyGroupFragment.class.getSimpleName().equals(str) && i >= 0) {
            this.i.setVisibility(0);
            this.i.setText(String.valueOf(i));
        }
    }
}
