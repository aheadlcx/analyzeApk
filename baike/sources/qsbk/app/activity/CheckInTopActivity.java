package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.fragments.CheckInListFragment;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.LoadingLayout;
import qsbk.app.widget.PagerSlidingTabStrip;

public class CheckInTopActivity extends BaseActionBarActivity {
    String[] a = new String[]{"总榜", "已粉榜", "周榜"};
    PagerSlidingTabStrip b;
    UserInfo c;
    ViewPager d;
    LoadingLayout e;

    class a extends FragmentPagerAdapter {
        List<Fragment> a;
        final /* synthetic */ CheckInTopActivity b;

        public a(CheckInTopActivity checkInTopActivity, List<Fragment> list, FragmentManager fragmentManager) {
            this.b = checkInTopActivity;
            super(fragmentManager);
            this.a = list;
        }

        public Fragment getItem(int i) {
            return (Fragment) this.a.get(i);
        }

        public int getCount() {
            return this.a.size();
        }

        public CharSequence getPageTitle(int i) {
            return this.b.a[i];
        }
    }

    public static void launch(Context context, UserInfo userInfo) {
        Intent intent = new Intent(context, CheckInTopActivity.class);
        intent.putExtra("info", userInfo);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    protected CharSequence getCustomTitle() {
        return "签到排行榜";
    }

    protected int a() {
        return R.layout.activity_check_in_top;
    }

    protected void a(Bundle bundle) {
        Intent intent = getIntent();
        if (intent != null) {
            this.c = (UserInfo) intent.getSerializableExtra("info");
        }
        setActionbarBackable();
        this.e = (LoadingLayout) findViewById(R.id.loading_layout);
        this.b = (PagerSlidingTabStrip) findViewById(R.id.tab);
        this.b.setShouldExpand(true);
        this.b.setTextSize(UIHelper.dip2px(this, 12.0f));
        this.b.setIndicatorHeight(0);
        this.b.setSelectedTabTextColor(UIHelper.isNightTheme() ? -4359412 : -17664);
        this.d = (ViewPager) findViewById(R.id.viewpager);
        this.d.setOffscreenPageLimit(3);
        f();
    }

    private void f() {
        if (!(QsbkApp.currentUser == null || TextUtils.equals(QsbkApp.currentUser.userId, this.c.userId))) {
            this.a = new String[]{"总榜", "周榜"};
        }
        this.e.hide();
        List arrayList = new ArrayList();
        for (int i = 0; i < this.a.length; i++) {
            if (this.a.length <= 2) {
                switch (i) {
                    case 0:
                        arrayList.add(CheckInListFragment.newInstance(this.c.userId, 1));
                        break;
                    case 1:
                        arrayList.add(CheckInListFragment.newInstance(this.c.userId, 2));
                        break;
                    default:
                        break;
                }
            }
            switch (i) {
                case 0:
                    arrayList.add(CheckInListFragment.newInstance(this.c.userId, 1));
                    break;
                case 1:
                    arrayList.add(CheckInListFragment.newInstance(this.c.userId, 0));
                    break;
                case 2:
                    arrayList.add(CheckInListFragment.newInstance(this.c.userId, 2));
                    break;
                default:
                    break;
            }
        }
        this.d.setAdapter(new a(this, arrayList, getSupportFragmentManager()));
        this.b.setViewPager(this.d, new fc(this));
    }
}
