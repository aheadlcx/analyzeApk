package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;
import com.baidu.mobstat.Config;
import com.baidu.mobstat.StatService;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.activity.MainActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.PreferenceUtils;
import qsbk.app.core.web.ui.WebActivity;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.ChatMsg;
import qsbk.app.im.IMTimer;
import qsbk.app.im.datastore.ChatMsgStore;
import qsbk.app.live.dload.LiveDownloadWebviewActivity;
import qsbk.app.live.ui.bag.BagFragment;
import qsbk.app.live.ui.bag.MarketFragment;
import qsbk.app.live.ui.family.FamilyBlankActivity;
import qsbk.app.live.ui.family.FamilyRankFragment;
import qsbk.app.live.widget.LiveStartUpDialog;
import qsbk.app.model.LiveRoom;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.RemixUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.SplashAdManager;
import qsbk.app.utils.SplashAdManager.SplashAdGroup;
import qsbk.app.utils.SplashAdManager.SplashAdItem;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.LiveFollowClickedFlip;
import qsbk.app.widget.OnNavigationListener;
import qsbk.app.widget.QiuyouCircleTab;
import qsbk.app.widget.QiuyouCircleTab.ITabOnClickListener;
import qsbk.app.widget.TipsView;

public class LiveTabsFragment extends StatisticFragment implements OnNavigationListener, ITabOnClickListener {
    public static final String SP_LAST_LIVE_CLICK = "_last_live_tab_click";
    public static final int TAB_INDEX_ALL = 0;
    public static final int TAB_INDEX_FAMILY = 3;
    public static final int TAB_INDEX_FOLLOW = 1;
    public static final int TAB_INDEX_FOUND = 2;
    private static final String[] c = new String[]{"热门", "发现", "家族", "背包", "商城"};
    private ImageView A;
    private ImageView B;
    private SplashAdItem C;
    private DataSource<CloseableReference<CloseableImage>> D;
    protected BroadcastReceiver a;
    BroadcastReceiver b = new ew(this);
    private View d;
    private ViewPager e;
    private TipsView f;
    private FragmentPagerAdapter g;
    private ArrayList<Fragment> h = new ArrayList();
    private int i = 0;
    private QiuyouCircleTab j;
    private Toolbar k;
    private Runnable l = new ep(this);
    private View m;
    private View n;
    private View o;
    private View p;
    private ViewFlipper q;
    private View r;
    private RelativeLayout s;
    private LiveFollowClickedFlip t;
    private SimpleHttpTask u;
    private boolean v;
    private List<LiveRoom> w = new ArrayList();
    private int x;
    private LiveStartUpDialog y;
    private ImageView z;

    private static class a extends FragmentPagerAdapter {
        private final String[] a;
        private final List<? extends Fragment> b;

        a(FragmentManager fragmentManager, List<? extends Fragment> list, String[] strArr) {
            super(fragmentManager);
            this.b = list;
            this.a = strArr;
        }

        public Fragment getItem(int i) {
            return (Fragment) this.b.get(i);
        }

        public int getCount() {
            return this.b.size();
        }

        public CharSequence getPageTitle(int i) {
            return this.a[i];
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            bundle.remove("android:support:fragments");
        }
        setHasOptionsMenu(true);
        this.a = new ex(this);
        getActivity().registerReceiver(this.a, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.b, new IntentFilter(Constants.ACTION_LIVE_BEGIN));
        b();
    }

    private void b() {
        NetRequest.getInstance().get(UrlConstants.LIVE_START_UP_WINDOW, new ey(this), "start_up_window", true);
    }

    public void onResume() {
        int i = 0;
        super.onResume();
        if (this.d != null) {
            this.d.postDelayed(this.l, Config.BPLUS_DELAY_TIME);
        }
        SharePreferenceUtils.setSharePreferencesValue(SP_LAST_LIVE_CLICK, System.currentTimeMillis());
        this.x = QsbkApp.currentUser == null ? 0 : ChatMsgStore.getChatMsgStore(QsbkApp.currentUser.userId).getTotalLiveBeginUnreadCount();
        View view = this.o;
        if (QsbkApp.currentUser == null || this.i >= 2) {
            i = 8;
        }
        view.setVisibility(i);
        g();
    }

    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(this.a);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.b);
    }

    public void onStop() {
        super.onStop();
        if (this.d != null) {
            this.d.removeCallbacks(this.l);
        }
    }

    private void c() {
        WebActivity.launch(getActivity(), FamilyRankFragment.family_help_url, getString(R.string.family_help));
    }

    private void d() {
        if (QsbkApp.currentUser == null) {
            startActivity(new Intent(getActivity(), ActionBarLoginActivity.class));
        } else {
            startActivity(new Intent(getActivity(), FamilyBlankActivity.class));
        }
    }

    private void e() {
        AppUtils.getInstance().getUserInfoProvider().toLiveRank(getActivity());
    }

    public void addLive() {
        if (QsbkApp.currentUser == null) {
            startActivity(new Intent(getActivity(), ActionBarLoginActivity.class));
        } else if (RemixUtil.isRemixInstalled()) {
            RemixUtil.openRemix();
        } else {
            startActivity(new Intent(QsbkApp.mContext, LiveDownloadWebviewActivity.class));
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.d == null) {
            a(layoutInflater, viewGroup);
        } else {
            ViewParent parent = this.d.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(this.d);
            }
        }
        a(0, true);
        return this.d;
    }

    private void a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.d = layoutInflater.inflate(R.layout.fragment_live_tab, viewGroup, false);
        this.e = (ViewPager) this.d.findViewById(R.id.pager);
        this.f = (TipsView) this.d.findViewById(R.id.tipsView);
        this.B = (ImageView) this.d.findViewById(R.id.activity_notification);
        this.B.setOnClickListener(new ez(this));
        if (!i()) {
            SplashAdManager instance = SplashAdManager.instance();
            instance.doTaskOnLoaded(new fa(this));
            instance.loadSplashAd();
        }
        this.m = this.d.findViewById(R.id.declaration);
        this.n = this.d.findViewById(R.id.declaration_close);
        this.n.setOnClickListener(new fc(this));
        this.o = this.d.findViewById(R.id.follow_container);
        this.s = (RelativeLayout) this.d.findViewById(R.id.follow_feed);
        this.p = this.d.findViewById(R.id.follow_flip_container);
        this.p.setVisibility(8);
        this.t = (LiveFollowClickedFlip) this.d.findViewById(R.id.liv_clicked_flip);
        this.t.setVisibility(8);
        this.q = (ViewFlipper) this.d.findViewById(R.id.follow_flip);
        this.o.setOnClickListener(new fd(this));
        this.q.setFlipInterval(5000);
        Animation loadAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up_in);
        loadAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        Animation loadAnimation2 = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up_out);
        loadAnimation2.setInterpolator(new AccelerateDecelerateInterpolator());
        this.q.setInAnimation(loadAnimation);
        this.q.setOutAnimation(loadAnimation2);
        this.r = this.d.findViewById(R.id.flip_close);
        this.r.setOnClickListener(new fe(this));
        h();
        this.e.setAdapter(this.g);
        this.e.setOnPageChangeListener(new eq(this));
        getAndInitPagerSlidingTabStrip(this.d);
    }

    private void f() {
        int i = 8;
        this.v = true;
        this.q.stopFlipping();
        this.p.setVisibility(8);
        this.t.setLiveRooms(this.w);
        this.t.setVisibility(this.w.size() > 0 ? 0 : 8);
        RelativeLayout relativeLayout = this.s;
        if (this.w.size() <= 0) {
            i = 0;
        }
        relativeLayout.setVisibility(i);
        if (QsbkApp.currentUser != null) {
            ChatMsgStore.getChatMsgStore(QsbkApp.currentUser.userId).markMessagesToReadWith(Integer.parseInt(ChatMsg.UID_LIVE));
            FragmentActivity activity = getActivity();
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).hideSmallTips(MainActivity.TAB_LIVE_ID);
            }
            this.x = 0;
        }
    }

    private synchronized void g() {
        if (QsbkApp.currentUser != null) {
            this.u = new SimpleHttpTask(String.format(Constants.LIVE_FOLLOW_ALL, new Object[]{"20", "1"}), new er(this));
            this.u.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private void a(int i, boolean z) {
        String str = c[i];
        if (!TextUtils.isEmpty(str)) {
            DebugUtil.debug(LiveTabsFragment.class.getSimpleName(), "baiduStatistics " + str + Config.TRACE_TODAY_VISIT_SPLIT + z);
            if (z) {
                StatService.onPageStart(getActivity(), str);
            } else {
                StatService.onPageEnd(getActivity(), str);
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001 && i2 == -1 && intent != null) {
            String stringExtra = intent.getStringExtra("liveStreamId");
            long intExtra = (long) intent.getIntExtra("liveStatus", -1);
            if (!TextUtils.isEmpty(stringExtra)) {
                Fragment fragment;
                if (intExtra == 0) {
                    Iterator it = this.h.iterator();
                    while (it.hasNext()) {
                        fragment = (Fragment) it.next();
                        if (fragment instanceof BaseLiveTabFragment) {
                            ((BaseLiveTabFragment) fragment).onRoomClosed(stringExtra);
                        }
                    }
                } else if (intent.hasExtra("isFollowLiveUser")) {
                    boolean booleanExtra = intent.getBooleanExtra("isFollowLiveUser", false);
                    Iterator it2 = this.h.iterator();
                    while (it2.hasNext()) {
                        fragment = (Fragment) it2.next();
                        if (fragment instanceof BaseLiveTabFragment) {
                            ((BaseLiveTabFragment) fragment).onFollowStateChange(stringExtra, booleanExtra);
                        }
                    }
                }
            }
        }
    }

    public void refresh() {
        if (this.e != null) {
            Fragment fragment = (Fragment) this.h.get(this.e.getCurrentItem());
            if (fragment instanceof BaseLiveTabFragment) {
                ((BaseLiveTabFragment) fragment).refresh();
            }
            if (fragment instanceof FamilyRankFragment) {
                ((FamilyRankFragment) fragment).forceRefresh();
            }
        }
    }

    public View getAndInitPagerSlidingTabStrip(View view) {
        if (this.k == null) {
            this.k = (Toolbar) view.findViewById(R.id.toolbar_live);
            this.j = (QiuyouCircleTab) this.k.findViewById(R.id.tab);
            this.k.setBackgroundColor(UIHelper.isNightTheme() ? 16759552 : 12352283);
            this.j.notifyDataSetChanged();
            this.j.setTextColor(UIHelper.isNightTheme() ? -10797805 : -1157627905);
            this.j.setSelectedTabTextColor(UIHelper.isNightTheme() ? -4359412 : -1);
            this.j.setTabBackground(R.color.transparent);
            this.j.setUnderlineHeight(0);
            this.j.setIndicatorHeight(0);
            this.j.setTextSize(UIHelper.dip2px(this.j.getContext(), 15.0f), UIHelper.dip2px(this.j.getContext(), 17.0f));
            this.j.setDividerColor(0);
            this.j.setDividerPadding(0);
            this.j.setSelectedTab(0);
            this.j.setTabsData(c);
            this.j.setITabOnClickListener(this);
            this.z = (ImageView) this.k.findViewById(R.id.add);
            this.z.setImageResource(UIHelper.getNewSubmitMenuIcon());
            this.A = (ImageView) this.k.findViewById(R.id.rank);
            this.A.setImageResource(UIHelper.getLiveRankingMenuIcon());
            this.z.setOnClickListener(new es(this));
            this.A.setOnClickListener(new eu(this));
        }
        return this.k;
    }

    private void h() {
        this.h = new ArrayList();
        this.h.add(new BaseLiveTabFragment());
        this.h.add(new LiveFoundFragment());
        this.h.add(new FamilyRankFragment());
        this.h.add(new BagFragment());
        this.h.add(new MarketFragment());
        this.g = new a(getChildFragmentManager(), this.h, c);
    }

    public void onTabClickListener(int i) {
        if (this.e != null) {
            if (this.e.getCurrentItem() == i && (this.g.getItem(this.e.getCurrentItem()) instanceof IArticleList)) {
                ((IArticleList) this.g.getItem(this.e.getCurrentItem())).scrollToTop();
            }
            this.e.setCurrentItem(i, false);
        }
    }

    public int getTabCount() {
        return this.g.getCount();
    }

    protected void a(boolean z) {
        if (this.f != null) {
            if (z) {
                this.f.setVisibility(0);
            } else {
                this.f.setVisibility(8);
            }
        }
    }

    protected void a() {
        int i = 0;
        if (HttpUtils.netIsAvailable()) {
            a(false);
        } else if (this.f != null) {
            a(true);
            this.f.setTipsViewTextContent(getActivity().getResources().getString(R.string.no_network));
            this.f.setTipsViewBgColor(getResources().getColor(R.color.widget_tips_view_bg_color));
            this.f.setOnClickListener(null);
        } else {
            return;
        }
        boolean z = PreferenceUtils.instance().getBoolean("live_declaration", true);
        if (this.m != null) {
            View view = this.m;
            if (!(z && HttpUtils.netIsAvailable())) {
                i = 8;
            }
            view.setVisibility(i);
        }
    }

    public View getActivityNotification() {
        return this.B;
    }

    public void onDestroyView() {
        super.onDestroyView();
        a(this.i, false);
        if (this.u != null) {
            this.u.cancel(true);
        }
        if (this.D != null && !this.D.isClosed()) {
            this.D.close();
        }
    }

    private boolean i() {
        SplashAdGroup group = SplashAdManager.instance().getGroup();
        if (group == null) {
            this.B.setVisibility(8);
            return false;
        }
        SplashAdItem activityItem = group.getActivityItem("live");
        int correctTime = (int) (IMTimer.getInstance().getCorrectTime() / 1000);
        if (activityItem == null || activityItem.startTime > correctTime || correctTime >= activityItem.endTime) {
            this.B.setVisibility(8);
        } else {
            this.C = activityItem;
            this.B.setVisibility(8);
            this.D = Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(activityItem.picUrl), QsbkApp.mContext);
            this.D.subscribe(new ev(this, activityItem), UiThreadImmediateExecutorService.getInstance());
        }
        return true;
    }

    public void navigateTo(int i) {
        if (this.e != null && this.g.getCount() > i) {
            if (this.e.getCurrentItem() == i && (this.g.getItem(this.e.getCurrentItem()) instanceof IArticleList)) {
                ((IArticleList) this.g.getItem(this.e.getCurrentItem())).scrollToTop();
            }
            this.e.setCurrentItem(i, false);
        }
    }
}
