package qsbk.app.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.fragments.TopicsTopFragment;

public class TopicTopActivity extends BaseActionBarActivity {
    private List<Fragment> a;
    private ViewPager b;
    private FragmentPagerAdapter c;

    private static class a extends FragmentPagerAdapter {
        private List<Fragment> a;

        a(FragmentManager fragmentManager, List<Fragment> list) {
            super(fragmentManager);
            this.a = list;
        }

        public Fragment getItem(int i) {
            return (Fragment) this.a.get(i);
        }

        public int getCount() {
            return this.a.size();
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return "话题天梯";
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.a = new ArrayList();
        this.a.add(TopicsTopFragment.newInstance(false));
        this.b = (ViewPager) findViewById(R.id.rankViewPager);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager == null) {
            finish();
        }
        this.c = new a(supportFragmentManager, this.a);
        this.b.setAdapter(this.c);
        this.b.setCurrentItem(0);
    }

    protected int a() {
        return R.layout.topic_rank_activity;
    }
}
