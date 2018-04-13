package qsbk.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.fragments.CircleTopicListFragment;
import qsbk.app.http.HttpTask;
import qsbk.app.model.CircleTopicCategory;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.LoadingLayout;
import qsbk.app.widget.PagerSlidingTabStrip;

public class CircleTopicCategoriesActivity extends BaseActionBarActivity {
    PagerSlidingTabStrip a;
    ViewPager b;
    LoadingLayout c;
    private ArrayList<CircleTopicCategory> d;
    private CircleTopicCategory e;
    private boolean f;

    class a extends FragmentPagerAdapter {
        List<Fragment> a;
        final /* synthetic */ CircleTopicCategoriesActivity b;

        public a(CircleTopicCategoriesActivity circleTopicCategoriesActivity, List<Fragment> list, FragmentManager fragmentManager) {
            this.b = circleTopicCategoriesActivity;
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
            CircleTopicCategory circleTopicCategory = (CircleTopicCategory) this.b.d.get(i);
            return circleTopicCategory == null ? "" : circleTopicCategory.name;
        }
    }

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, CircleTopicCategoriesActivity.class));
    }

    public static void launch(Activity activity, CircleTopicCategory circleTopicCategory) {
        Intent intent = new Intent(activity, CircleTopicCategoriesActivity.class);
        intent.putExtra("category", circleTopicCategory);
        activity.startActivity(intent);
    }

    protected CharSequence getCustomTitle() {
        return "话题分类";
    }

    protected int a() {
        return R.layout.activity_circle_topic_list;
    }

    protected void a(Bundle bundle) {
        Intent intent = getIntent();
        if (intent != null) {
            this.e = (CircleTopicCategory) intent.getSerializableExtra("category");
        }
        setActionbarBackable();
        this.c = (LoadingLayout) findViewById(R.id.loading_layout);
        this.a = (PagerSlidingTabStrip) findViewById(R.id.tab);
        this.a.setTextSize(UIHelper.dip2px(this, 12.0f));
        this.a.setIndicatorHeight(0);
        this.a.setSelectedTabTextColor(UIHelper.isNightTheme() ? -4359412 : -17664);
        this.b = (ViewPager) findViewById(R.id.viewpager);
        g();
    }

    private void f() {
        this.c.hide();
        String[] strArr = new String[this.d.size()];
        List arrayList = new ArrayList();
        for (int i = 0; i < this.d.size(); i++) {
            CircleTopicCategory circleTopicCategory = (CircleTopicCategory) this.d.get(i);
            strArr[i] = circleTopicCategory.name;
            arrayList.add(CircleTopicListFragment.newInstance(circleTopicCategory.id));
        }
        this.b.setAdapter(new a(this, arrayList, getSupportFragmentManager()));
        this.a.setViewPager(this.b, new jb(this));
        if (this.d.contains(this.e)) {
            this.b.setCurrentItem(this.d.indexOf(this.e));
        }
        this.a.post(new jc(this));
    }

    private void g() {
        this.c.onLoading();
        new HttpTask(null, Constants.CIRCLE_TOPIC_CATEGORY_ALL, new je(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
