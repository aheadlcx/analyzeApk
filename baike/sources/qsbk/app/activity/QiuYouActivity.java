package qsbk.app.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import java.util.ArrayList;
import java.util.Collection;
import qsbk.app.R;
import qsbk.app.fragments.ContactFansFragment;
import qsbk.app.fragments.ContactFollowsFragment;
import qsbk.app.fragments.ContactFriendsFragment;
import qsbk.app.fragments.ContactMyGroupFragment;
import qsbk.app.fragments.ContactQiuYouFragment.QiuYouListener;
import qsbk.app.fragments.QiuYouFragment;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;

public class QiuYouActivity extends StatFragmentActivity implements QiuYouListener {
    private static final String a = QiuYouActivity.class.getSimpleName();
    private static final String[] o = new String[]{"互粉", "粉", "粉丝", "群"};
    private ArrayList<Fragment> b = new ArrayList();
    private TabLayout c;
    private ViewPager d;
    private QiushiPagerAdapter e;
    private View f;
    private ImageView g;
    private View h;
    private ImageView i;
    private boolean j = false;
    private boolean k = false;
    private boolean l = false;
    private View m;
    private View n;

    public final class QiushiPagerAdapter extends FragmentPagerAdapter {
        final /* synthetic */ QiuYouActivity a;
        private ArrayList<Fragment> b;

        public QiushiPagerAdapter(QiuYouActivity qiuYouActivity, FragmentManager fragmentManager, ArrayList<Fragment> arrayList) {
            this.a = qiuYouActivity;
            super(fragmentManager);
            this.b = arrayList;
        }

        public CharSequence getPageTitle(int i) {
            return QiuYouActivity.o[i];
        }

        public Fragment getItem(int i) {
            LogUtil.e("arg0====" + i);
            return (Fragment) this.b.get(i);
        }

        public int getCount() {
            return QiuYouActivity.o.length;
        }

        public View getTabView(int i) {
            View inflate = LayoutInflater.from(this.a).inflate(R.layout.item_qiuyou_tab, this.a.c, false);
            TextView textView = (TextView) inflate.findViewById(16908308);
            if (textView != null && QiuYouActivity.o.length > i) {
                textView.setText(QiuYouActivity.o[i]);
                textView.setTextColor(this.a.getResources().getColor(R.color.col_voice_tab_text));
                ((TextView) inflate.findViewById(R.id.num)).setTextColor(-1761625579);
            }
            return inflate;
        }
    }

    protected int a() {
        return R.layout.activity_qiuyou_contact;
    }

    protected void onCreate(Bundle bundle) {
        setTheme(UIHelper.isNightTheme() ? R.style.Night.QiuyouActivity : R.style.Day.QiuyouActivity);
        super.onCreate(bundle);
        setContentView(a());
        a(bundle);
    }

    protected void a(Bundle bundle) {
        findViewById(R.id.contact_container).setBackgroundColor(UIHelper.isNightTheme() ? -15329253 : -1184275);
        this.m = findViewById(R.id.input_name);
        this.m.setOnClickListener(new zz(this));
        this.n = findViewById(R.id.title);
        this.n.setOnClickListener(new aaa(this));
        d();
        f();
    }

    public int getCurrentItem() {
        return this.d.getCurrentItem();
    }

    private void d() {
        this.b.add(new ContactFriendsFragment());
        this.b.add(new ContactFollowsFragment());
        this.b.add(new ContactFansFragment());
        this.b.add(ContactMyGroupFragment.newInstance(0));
    }

    private void e() {
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
        SearchQiuYouActivity.launch(this, arrayList);
    }

    private void f() {
        this.j = getIntent().getBooleanExtra("has_new_fans", false);
        this.k = getIntent().getBooleanExtra("from_group", false);
        this.l = getIntent().getBooleanExtra("manage_my_group", false);
        this.c = (TabLayout) findViewById(R.id.pager_tab);
        this.d = (ViewPager) findViewById(R.id.pager);
        this.f = findViewById(R.id.nearby_group);
        this.g = (ImageView) findViewById(R.id.nearby_group_image);
        this.h = findViewById(R.id.nearby_people);
        this.i = (ImageView) findViewById(R.id.nearby_people_image);
        this.i.setImageResource(UIHelper.isNightTheme() ? R.drawable.found_ic_nearby_night : R.drawable.found_ic_nearby);
        this.g.setImageResource(UIHelper.isNightTheme() ? R.drawable.found_ic_group_night : R.drawable.found_ic_group);
        this.f.setOnClickListener(new aab(this));
        this.h.setOnClickListener(new aac(this));
        this.e = new QiushiPagerAdapter(this, getSupportFragmentManager(), this.b);
        this.d.setAdapter(this.e);
        this.d.setOffscreenPageLimit(4);
        this.c.setupWithViewPager(this.d);
        for (int i = 0; i < this.c.getTabCount(); i++) {
            Tab tabAt = this.c.getTabAt(i);
            if (tabAt != null) {
                tabAt.setCustomView(this.e.getTabView(i));
            }
        }
        this.c.setTabMode(1);
        this.c.addOnTabSelectedListener(new aad(this));
        if (this.j) {
            this.d.setCurrentItem(2);
        } else if (this.k || this.l) {
            this.d.setCurrentItem(3);
        } else {
            this.d.setCurrentItem(0);
        }
        if (!SharePreferenceUtils.getSharePreferencesBoolValue("_show_overflow_menu")) {
            new Handler().postDelayed(new aae(this), 1000);
            SharePreferenceUtils.setSharePreferencesValue("_show_overflow_menu", true);
        }
    }

    public void openOptionsMenu() {
        Configuration configuration = getResources().getConfiguration();
        if ((configuration.screenLayout & 15) > 3) {
            int i = configuration.screenLayout;
            configuration.screenLayout = 3;
            super.openOptionsMenu();
            configuration.screenLayout = i;
            return;
        }
        super.openOptionsMenu();
    }

    public void qiuYouNum(int i, String str) {
        CharSequence valueOf = String.valueOf(i > 1000 ? (i / 1000) + Config.APP_KEY : Integer.valueOf(i));
        if (i >= 0) {
            Tab tab = null;
            if (ContactFriendsFragment.class.getSimpleName().equals(str)) {
                tab = this.c.getTabAt(0);
            } else if (ContactFollowsFragment.class.getSimpleName().equals(str)) {
                tab = this.c.getTabAt(1);
            } else if (ContactFansFragment.class.getSimpleName().equals(str)) {
                tab = this.c.getTabAt(2);
            } else if (ContactMyGroupFragment.class.getSimpleName().equals(str)) {
                tab = this.c.getTabAt(3);
            }
            if (tab != null) {
                View customView = tab.getCustomView();
                if (customView != null) {
                    ((TextView) customView.findViewById(R.id.num)).setText(valueOf);
                }
            }
        }
    }
}
