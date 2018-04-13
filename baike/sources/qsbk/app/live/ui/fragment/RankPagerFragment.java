package qsbk.app.live.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import java.util.ArrayList;
import qsbk.app.core.model.User;
import qsbk.app.core.ui.base.BaseFragment;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.core.widget.PagerSlidingTabStrip;
import qsbk.app.live.R;
import qsbk.app.live.ui.GiftRankActivity;

public class RankPagerFragment extends BaseFragment {
    public static final int LIVE_RANK_ANCHOR = 1;
    public static final int LIVE_RANK_WEEK = 2;
    private PagerSlidingTabStrip a;
    private ViewPager b;
    private b c;
    private ArrayList<a> d = new ArrayList();
    private boolean e;
    private User f;
    private int g;

    private class a {
        final /* synthetic */ RankPagerFragment a;
        public int id;
        public String name;

        public a(RankPagerFragment rankPagerFragment, int i, String str) {
            this.a = rankPagerFragment;
            this.id = i;
            this.name = str;
        }
    }

    private class b extends FragmentStatePagerAdapter implements OnPageChangeListener {
        ArrayList<a> a;
        final /* synthetic */ RankPagerFragment b;

        public b(RankPagerFragment rankPagerFragment, FragmentManager fragmentManager, ArrayList<a> arrayList) {
            this.b = rankPagerFragment;
            super(fragmentManager);
            this.a = arrayList;
        }

        public CharSequence getPageTitle(int i) {
            return ((a) this.a.get(i)).name;
        }

        public Fragment getItem(int i) {
            return GiftRankFragment.newInstance(((a) this.a.get(i)).id, this.b.f, this.b.e, this.b.g, null);
        }

        public int getCount() {
            return this.a.size();
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
        }

        public void onPageScrollStateChanged(int i) {
        }
    }

    public static RankPagerFragment newInstance(User user, boolean z) {
        return newInstance(user, z, 1);
    }

    public static RankPagerFragment newInstance(User user, boolean z, int i) {
        RankPagerFragment rankPagerFragment = new RankPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        bundle.putBoolean("is_anchor", z);
        bundle.putInt("type", i);
        rankPagerFragment.setArguments(bundle);
        return rankPagerFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f = (User) arguments.getSerializable("user");
            this.e = arguments.getBoolean("is_anchor");
            this.g = arguments.getInt("type");
        }
    }

    protected int getLayoutId() {
        return R.layout.fragment_rank_pager;
    }

    protected void initView() {
        this.a = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        this.b = (ViewPager) findViewById(R.id.pager);
        if (getActivity() instanceof GiftRankActivity) {
            findViewById(R.id.pager_container).setBackgroundResource(R.color.yellow_fddb2e);
            this.a.setIndicatorColorResource(R.color.color_41364F);
            this.a.setIndicatorHeight(WindowUtils.dp2Px(3));
            this.a.setIndicatorWidthPadding(WindowUtils.dp2Px(12));
            this.a.setUnderlineColorResource(R.color.transparent);
            this.a.setDividerColorResource(R.color.transparent);
            this.a.setTextSize(WindowUtils.dp2Px(13));
            this.a.setTabBackground(R.color.transparent);
            this.a.setTextColorResource(R.color.color_9941364F);
            this.a.setSelectedTabTextColorResource(R.color.color_41364F);
            return;
        }
        findViewById(R.id.pager_container).setBackgroundResource(R.color.transparent);
        this.a.setVisibility(8);
    }

    protected void initData() {
        if (getActivity() instanceof GiftRankActivity) {
            this.d.add(new a(this, 1, AppUtils.getInstance().getAppContext().getString(R.string.rank_week)));
            this.d.add(new a(this, 2, AppUtils.getInstance().getAppContext().getString(R.string.rank_total)));
        } else {
            this.d.add(new a(this, 1, AppUtils.getInstance().getAppContext().getString(R.string.live_rank_gift_sended)));
            this.d.add(new a(this, 2, AppUtils.getInstance().getAppContext().getString(R.string.live_rank_gift_received)));
        }
        this.c = new b(this, getChildFragmentManager(), this.d);
        this.b.setAdapter(this.c);
        this.b.setOffscreenPageLimit(this.d.size());
        this.b.addOnPageChangeListener(this.c);
        this.a.setViewPager(this.b, null);
    }
}
