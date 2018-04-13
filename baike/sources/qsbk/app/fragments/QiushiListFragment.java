package qsbk.app.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import com.baidu.mobstat.StatService;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.AuditNativeActivity2;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.publish.PublishActivity;
import qsbk.app.im.IMTimer;
import qsbk.app.im.QiushiNotificationCountManager;
import qsbk.app.im.QiushiNotificationCountManager.NotificationListener;
import qsbk.app.im.QiushiNotificationCountManager.NotificationModel;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.ArticleListConfig;
import qsbk.app.model.EventWindow;
import qsbk.app.service.VoteManager;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LiveRecommendManager;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SplashAdManager;
import qsbk.app.utils.SplashAdManager.SplashAdGroup;
import qsbk.app.utils.SplashAdManager.SplashAdItem;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.OnNavigationListener;
import qsbk.app.widget.QiuyouCircleTab;
import qsbk.app.widget.QiuyouCircleTab.ITabOnClickListener;
import qsbk.app.widget.TipsView;

public class QiushiListFragment extends BaseFragment implements NotificationListener, OnNavigationListener, ITabOnClickListener {
    public static final String KEY_ARTICLE_LIST_CONFIG = "article_list_config";
    public static final String KEY_SELECTED_ITEM = "selected_item";
    public static final int TAB_INDEX_GOOD = 5;
    public static final int TAB_INDEX_IMG = 4;
    public static final int TAB_INDEX_SUB = 0;
    public static final int TAB_INDEX_TEXT = 3;
    public static final int TAB_INDEX_TIME = 6;
    public static final int TAB_INDEX_TOPIC = 2;
    public static final int TAB_INDEX_VIDEO = 1;
    public static final String VIDEO_PLAY_MODE_TIP_SHOW = "video_play_mode_tip_show";
    public static int circle4GPosition = 8;
    public static int circleWifiPosition = 3;
    private static final String i = QiushiListFragment.class.getSimpleName();
    private static ArrayList<ArticleListConfig> j = null;
    public static boolean showCircleVideo = false;
    private ImageView A;
    private List<Runnable> B = new ArrayList();
    private Toolbar C;
    private boolean D = false;
    private Runnable E;
    @SuppressLint({"HandlerLeak"})
    private Handler F = new Handler();
    protected TipsView a;
    protected BroadcastReceiver b;
    protected AudioManager c;
    protected int d = 0;
    DataSource<CloseableReference<CloseableImage>> e;
    Runnable f = new hw(this);
    Runnable g = new ie(this);
    BroadcastReceiver h = new if(this);
    private boolean k;
    private ViewPager l;
    private FragmentPagerAdapter m;
    private ArrayList<Fragment> n = new ArrayList();
    private int o = 0;
    private String[] p = null;
    private boolean q = false;
    private View r;
    private ImageView s;
    private TextView t;
    private NotificationModel u;
    private ImageView v;
    private SplashAdItem w;
    private QiuyouCircleTab x;
    private ImageView y;
    private ImageView z;

    public static final class QiushiViewPagerAdapter extends FragmentPagerAdapter {
        private final String[] a;
        private ArrayList<Fragment> b = new ArrayList();

        public QiushiViewPagerAdapter(FragmentManager fragmentManager, String[] strArr, ArrayList<Fragment> arrayList) {
            super(fragmentManager);
            this.b = arrayList;
            this.a = strArr;
        }

        public CharSequence getPageTitle(int i) {
            return this.a[i];
        }

        public Fragment getItem(int i) {
            DebugUtil.debug("luolong", "getItem, " + i);
            return (Fragment) this.b.get(i);
        }

        public int getCount() {
            return this.a.length;
        }

        public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
            super.setPrimaryItem(viewGroup, i, obj);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onStart() {
        DebugUtil.debug("luolong", "onStart");
        e();
        super.onStart();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getActivity() instanceof QiushiListFragment$OnViewed) {
            ((QiushiListFragment$OnViewed) getActivity()).onViewed();
        }
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.h, new IntentFilter(VoteManager.ACTION_VOTE_COUNT));
    }

    public void onResume() {
        this.D = true;
        super.onResume();
        if (QsbkApp.currentUser != null) {
            this.u = QiushiNotificationCountManager.getInstance(QsbkApp.currentUser.userId).getLastNotification();
            onNewNotification(this.u);
            QiushiNotificationCountManager.getInstance(QsbkApp.currentUser.userId).addListener(this);
        } else {
            QiushiNotificationCountManager.tryRemoveAll();
            this.r.setVisibility(8);
            a(null);
        }
        if (this.n != null && this.n.size() - 1 > this.o) {
            Fragment fragment = (Fragment) this.n.get(this.o);
            if (fragment instanceof IPageableFragment) {
                ((IPageableFragment) fragment).doResume();
            }
            if ((fragment instanceof IArticleList) && (getActivity() instanceof QiushiListFragment$QiushiCallback)) {
                ((QiushiListFragment$QiushiCallback) getActivity()).onResume((IArticleList) fragment);
            }
            a(fragment);
        }
        this.x.setSelectedTab(this.d);
        i();
    }

    private void i() {
        if (this.A != null) {
            this.A.setVisibility(EventWindow.hasQiushiTopicEvent() ? 0 : 4);
            if (EventWindow.hasQiushiTopicEvent()) {
                FrescoImageloader.displayAvatar(this.A, EventWindow.getEventWindow().iconUrl, 0);
            }
        }
    }

    public void onPause() {
        this.D = false;
        super.onPause();
        if (this.n != null && this.n.size() - 1 > this.o) {
            Fragment fragment = (Fragment) this.n.get(this.o);
            if (fragment instanceof IPageableFragment) {
                ((IPageableFragment) fragment).doPause();
            }
        }
    }

    public void onStop() {
        super.onStop();
        if (this.n != null && this.n.size() - 1 >= this.o) {
            Fragment fragment = (Fragment) this.n.get(this.o);
            if (fragment instanceof IPageableFragment) {
                ((IPageableFragment) fragment).doStop();
            }
        }
    }

    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            bundle.remove("android:support:fragments");
        }
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.o = arguments.getInt(KEY_SELECTED_ITEM);
        }
        DebugUtil.debug(i, "onCreate, mCurrentItem=" + this.o);
        l();
        b(j);
        a(j);
        j();
        this.c = (AudioManager) getActivity().getSystemService("audio");
        c();
        d();
        a(0, true);
    }

    private void j() {
        if (QsbkApp.indexConfig != null) {
            JSONObject optJSONObject = QsbkApp.indexConfig.optJSONObject(EventWindow.JUMP_CIRCLE_VIDEO);
            if (optJSONObject != null) {
                circle4GPosition = optJSONObject.optInt("g_position");
                circleWifiPosition = optJSONObject.optInt("w_position");
                showCircleVideo = optJSONObject.optBoolean("isShowAd");
                return;
            }
            return;
        }
        showCircleVideo = false;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_qiushi, viewGroup, false);
        a(inflate);
        this.l = (ViewPager) inflate.findViewById(R.id.pager);
        this.m = new QiushiViewPagerAdapter(getChildFragmentManager(), this.p, this.n);
        this.l.setAdapter(this.m);
        this.l.setOnPageChangeListener(new ig(this));
        if (this.o != 0) {
            this.l.setCurrentItem(this.o);
            if (this.x != null) {
                this.x.setSelectedTab(this.o);
            } else {
                this.B.add(new ih(this));
            }
        }
        this.r = inflate.findViewById(R.id.qiushi_notification);
        this.s = (ImageView) this.r.findViewById(R.id.qiushi_ic);
        this.t = (TextView) this.r.findViewById(R.id.qiushi_des);
        this.r.setOnClickListener(new ii(this));
        this.v = (ImageView) inflate.findViewById(R.id.activity_notification);
        this.v.setOnClickListener(new ij(this));
        if (!k()) {
            SplashAdManager instance = SplashAdManager.instance();
            this.E = new ik(this);
            instance.doTaskOnLoaded(this.E);
            instance.loadSplashAd();
        }
        getAndInitPagerSlidingTabStrip(inflate);
        LiveRecommendManager instance2 = LiveRecommendManager.getInstance();
        if (instance2 != null) {
            instance2.refresh();
        }
        return inflate;
    }

    private void a(Fragment fragment) {
        boolean z;
        if (fragment instanceof IArticleList) {
            IArticleList iArticleList = (IArticleList) fragment;
            if (iArticleList.hasNewArticle() && (getActivity() instanceof QiushiListFragment$QiushiCallback)) {
                ((QiushiListFragment$QiushiCallback) getActivity()).onNewQiushi(iArticleList);
                z = true;
                if (!z && (getActivity() instanceof MainActivity)) {
                    ((MainActivity) getActivity()).setHighlightedTab(MainActivity.TAB_QIUSHI_ID, false);
                    return;
                }
            }
        }
        z = false;
        if (!z) {
        }
    }

    public void getAndInitPagerSlidingTabStrip(View view) {
        this.C = (Toolbar) view.findViewById(R.id.toolbar_qiushi);
        this.x = (QiuyouCircleTab) this.C.findViewById(R.id.tab);
        this.x.setITabOnClickListener(this);
        this.y = (ImageView) this.C.findViewById(R.id.add);
        this.z = (ImageView) this.C.findViewById(R.id.audit);
        this.C.setBackgroundColor(UIHelper.isNightTheme() ? 16759552 : 12352283);
        this.z.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_audit_dark : R.drawable.ic_audit);
        this.y.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_create_night : R.drawable.group_create);
        this.x.notifyDataSetChanged();
        this.x.setTextColor(UIHelper.isNightTheme() ? -10797805 : -1157627905);
        this.x.setSelectedTabTextColor(UIHelper.isNightTheme() ? -4359412 : -1);
        this.x.setTabBackground(R.color.transparent);
        this.x.setUnderlineHeight(0);
        this.x.setIndicatorHeight(0);
        this.x.setTextSize(UIHelper.dip2px(this.x.getContext(), 15.0f), UIHelper.dip2px(this.x.getContext(), 17.0f));
        this.x.setDividerColor(0);
        this.x.setDividerPadding(0);
        this.x.setSelectedTab(0);
        this.x.setTabsData(this.p);
        if (!this.k) {
            this.x.getViewTreeObserver().addOnPreDrawListener(new im(this));
        }
        this.y.setOnClickListener(new hx(this));
        this.z.setOnClickListener(new hy(this));
        this.A = (ImageView) this.C.findViewById(R.id.event);
        for (Runnable post : this.B) {
            this.x.post(post);
        }
        this.B.clear();
    }

    private boolean k() {
        SplashAdGroup group = SplashAdManager.instance().getGroup();
        if (group == null) {
            this.v.setVisibility(8);
            return false;
        }
        SplashAdItem activityItem = group.getActivityItem(SplashAdItem.TAB_QIUSHI);
        int correctTime = (int) (IMTimer.getInstance().getCorrectTime() / 1000);
        if (activityItem == null || activityItem.startTime > correctTime || correctTime >= activityItem.endTime) {
            this.v.setVisibility(8);
        } else {
            this.w = activityItem;
            this.v.setVisibility(8);
            this.e = Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(activityItem.picUrl), QsbkApp.mContext);
            this.e.subscribe(new hz(this, activityItem), UiThreadImmediateExecutorService.getInstance());
        }
        return true;
    }

    private void a(String str) {
        Object obj = !TextUtils.isEmpty(str) ? 1 : null;
        FragmentActivity activity = getActivity();
        if (activity != null && (activity instanceof MainActivity)) {
            if (obj != null) {
                ((MainActivity) activity).setTips(MainActivity.TAB_QIUSHI_ID, str);
            } else {
                ((MainActivity) activity).hideTips(MainActivity.TAB_QIUSHI_ID);
            }
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (!(this.e == null || this.e.isClosed())) {
            this.e.close();
        }
        if (this.h != null) {
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.h);
        }
    }

    public void onDestroy() {
        getActivity().unregisterReceiver(this.b);
        if (QsbkApp.currentUser != null) {
            QiushiNotificationCountManager.getInstance(QsbkApp.currentUser.userId).removeListener(this);
        }
        if (this.E != null) {
            SplashAdManager.instance().cancelTask(this.E);
        }
        super.onDestroy();
    }

    public View getQiushiNotificationView() {
        return this.r;
    }

    public View getActivityNotification() {
        return this.v;
    }

    public boolean canShowQiushiNotificationView() {
        return (QsbkApp.currentUser == null || this.u == null || this.u.getUnReadCount() <= 0) ? false : true;
    }

    public boolean canShowActivity() {
        return this.w != null;
    }

    protected void a(View view) {
        this.a = (TipsView) view.findViewById(R.id.tipsView);
    }

    public void onDetach() {
        super.onDetach();
        a(this.o, false);
    }

    public void startPublish() {
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.showLoginGuideDialog(getActivity(), R.string.login_guide_dialog_content_publish);
            return;
        }
        Intent intent = new Intent(getActivity(), PublishActivity.class);
        intent.putExtra("flag", "article");
        getActivity().startActivity(intent);
    }

    public boolean isOnVideoTab() {
        if (this.D && this.n.size() > this.o) {
            Fragment fragment = (Fragment) this.n.get(this.o);
            if (fragment != null && (fragment instanceof VideoFragment)) {
                return true;
            }
        }
        return false;
    }

    public void startAudit() {
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.showLoginGuideDialog(getActivity(), R.string.login_guide_dialog_content_audit);
            return;
        }
        getActivity().startActivity(new Intent(getActivity(), AuditNativeActivity2.class));
    }

    private void l() {
        if (QsbkApp.indexConfig != null) {
            try {
                JSONArray jSONArray = QsbkApp.indexConfig.getJSONArray("index_v7");
                if (jSONArray.length() != 0) {
                    j = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        ArticleListConfig articleListConfig = new ArticleListConfig();
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        articleListConfig.mIsShuffle = jSONObject.optBoolean("shuffle");
                        articleListConfig.mTitle = jSONObject.optString("title");
                        articleListConfig.mUniqueName = jSONObject.optString("uniquename");
                        articleListConfig.mUrl = jSONObject.optString("url");
                        j.add(articleListConfig);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void a(ArrayList<ArticleListConfig> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            int size = arrayList.size();
            this.p = new String[size];
            for (int i = 0; i < size; i++) {
                this.p[i] = ((ArticleListConfig) arrayList.get(i)).mTitle;
            }
        }
    }

    private void b(ArrayList<ArticleListConfig> arrayList) {
        SubscribeFragment subscribeFragment = new SubscribeFragment();
        int size = arrayList == null ? 0 : arrayList.size();
        try {
            this.n.add(subscribeFragment);
            this.n.add(VideoFragment.newInstance(size <= 1 ? null : (ArticleListConfig) arrayList.get(1)));
            this.n.add(QiushiTopicTabFragment.newInstance(size <= 2 ? null : (ArticleListConfig) arrayList.get(2)));
            this.n.add(HotTextFragment.newInstance(size <= 3 ? null : (ArticleListConfig) arrayList.get(3)));
            this.n.add(HotImageFragment.newInstance(size <= 4 ? null : (ArticleListConfig) arrayList.get(4)));
            this.n.add(EssenceFragment.newInstance(size <= 5 ? null : (ArticleListConfig) arrayList.get(5)));
            this.n.add(AcrossFragment.newInstance(size <= 6 ? null : (ArticleListConfig) arrayList.get(6)));
            for (int i = 7; i < size; i++) {
                this.n.add(VideoFragment.newInstance(arrayList == null ? null : (ArticleListConfig) arrayList.get(i)));
            }
            LogUtil.e("mfragments length:" + this.n.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(int i, boolean z) {
        String str = this.p[i];
        if (i == 0) {
            str = "订阅";
        }
        if (!TextUtils.isEmpty(str)) {
            DebugUtil.debug(i, "baiduStatistics " + str + Config.TRACE_TODAY_VISIT_SPLIT + z);
            if (z) {
                StatService.onPageStart(getActivity(), str);
            } else {
                StatService.onPageEnd(getActivity(), str);
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        FragmentManager childFragmentManager = getChildFragmentManager();
        if (childFragmentManager != null) {
            List<Fragment> fragments = childFragmentManager.getFragments();
            if (fragments != null && fragments.size() != 0) {
                for (Fragment fragment : fragments) {
                    if (fragment.getUserVisibleHint()) {
                        fragment.onActivityResult(i, i2, intent);
                        break;
                    }
                }
            }
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.l != null && (this.m.getItem(this.l.getCurrentItem()) instanceof IArticleList)) {
            return ((IArticleList) this.m.getItem(this.l.getCurrentItem())).onKeyDown(i, keyEvent);
        }
        return false;
    }

    protected boolean a() {
        return this.d == 1;
    }

    protected void a(boolean z) {
        DebugUtil.debug("luolong", "showOrHideTipsView, flag=" + z);
        if (this.a != null) {
            if (z) {
                this.a.setVisibility(0);
            } else {
                this.a.setVisibility(8);
            }
        }
    }

    protected void b() {
        if (a() && HttpUtils.netIsAvailable()) {
            if (f()) {
                g();
            } else {
                a(false);
            }
        } else if (f()) {
            g();
        }
    }

    protected void c() {
        this.b = new ia(this);
    }

    protected void d() {
        DebugUtil.debug(i, "registerListener");
        getActivity().registerReceiver(this.b, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    protected void e() {
        int i;
        if (HttpUtils.netIsAvailable()) {
            a(false);
            b();
        }
        if (!HttpUtils.netIsAvailable()) {
            if (this.a != null) {
                a(true);
                this.a.setTipsViewTextContent(getActivity().getResources().getString(R.string.no_network));
                this.a.setTipsViewBgColor(getResources().getColor(R.color.widget_tips_view_bg_color));
                this.a.setOnClickListener(null);
            } else {
                return;
            }
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getActivity().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getState() == State.CONNECTED) {
            switch (activeNetworkInfo.getType()) {
                case 0:
                    if (!a()) {
                        i = 2;
                        break;
                    } else {
                        i = 2;
                        break;
                    }
                case 1:
                    i = 1;
                    break;
                default:
                    i = 2;
                    break;
            }
        }
        i = 0;
        DebugUtil.debug(i, "updateNetworkState mReceiver networkState=" + i);
    }

    protected boolean f() {
        long time = new Date().getTime() / 1000;
        if (SplashAdManager.mAnnouncement == null || SplashAdManager.mAnnouncement.start >= time || SplashAdManager.mAnnouncement.end <= time || SplashAdManager.mAnnouncement.location > 1 || SplashAdManager.mAnnouncement.hasClick) {
            return false;
        }
        return true;
    }

    protected void g() {
        if (SplashAdManager.mAnnouncement != null) {
            this.a.setTipsViewTextContent(SplashAdManager.mAnnouncement.content);
            this.a.setTipsViewBgColor(UIHelper.isNightTheme() ? -1723489030 : -431643398);
            a(true);
            this.a.setOnClickListener(new ib(this));
        }
    }

    public void onNewNotification(NotificationModel notificationModel) {
        if (notificationModel != null) {
            int unReadCount = notificationModel.getUnReadCount();
            if (unReadCount <= 0) {
                this.F.postDelayed(new ic(this), 100);
                return;
            }
            this.u = notificationModel;
            this.F.postDelayed(new id(this, QsbkApp.absoluteUrlOfMediumUserIcon(notificationModel.getUserInfo().icon, notificationModel.getUserInfo().id), unReadCount), 100);
        }
    }

    public void onTabClickListener(int i) {
        if (!a() && this.q) {
            a(false);
        }
        e();
        if (this.o == i && (this.m.getItem(this.l.getCurrentItem()) instanceof IArticleList)) {
            ((IArticleList) this.m.getItem(this.l.getCurrentItem())).scrollToTop();
        }
        if (this.l != null) {
            this.l.setCurrentItem(i, false);
        }
    }

    public int getTabCount() {
        return this.m.getCount();
    }

    public void navigateTo(int i) {
        gotoFragment(i);
    }

    public void gotoFragment(int i) {
        if (this.l != null && this.n.size() > i) {
            this.l.setCurrentItem(i);
            if (this.m.getItem(i) instanceof IArticleList) {
                ((IArticleList) this.m.getItem(i)).scrollToTop();
            }
        }
    }

    public void gotoSubscribeFragment() {
        gotoFragment(0);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(MainActivity.ACTION_NEW_INTENT));
    }

    public void gotoNewsFragment() {
        if (this.l != null && this.n.size() > 2) {
            this.l.setCurrentItem(2);
        }
    }
}
