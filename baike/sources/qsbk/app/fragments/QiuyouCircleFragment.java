package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.MainActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.IMTimer;
import qsbk.app.im.QiuyouquanNotificationCountManager;
import qsbk.app.im.QiuyouquanNotificationCountManager.NotificationListener;
import qsbk.app.im.QiuyouquanNotificationCountManager.NotificationModel;
import qsbk.app.im.TimeUtils;
import qsbk.app.utils.CircleVoteBuffer;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LiveRecommendManager;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.SplashAdManager;
import qsbk.app.utils.SplashAdManager.SplashAdGroup;
import qsbk.app.utils.SplashAdManager.SplashAdItem;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.timer.ITimerProcessor;
import qsbk.app.utils.timer.TimerHelper;
import qsbk.app.widget.HeaderRelativeLayout;
import qsbk.app.widget.IconButton;
import qsbk.app.widget.OnNavigationListener;
import qsbk.app.widget.QiuyouCircleTab;
import qsbk.app.widget.QiuyouCircleTab.ITabOnClickListener;
import qsbk.app.widget.TipsView;
import qsbk.app.widget.VerticalImageSpan;

public class QiuyouCircleFragment extends StatisticFragment implements NotificationListener, ITimerProcessor, OnNavigationListener, ITabOnClickListener {
    public static final String NICK_STATUS = "nick_status_";
    public static final String SIGN_DAYS = "circle_sign_days_";
    public static final String SIGN_TIME = "circle_sign_time_";
    public static final String SP_CIRCLE_SIGN_TIP = "qiuyou_circle_sign_tip";
    public static final int TAB_INDEX_FOLLOW = 1;
    public static final int TAB_INDEX_NEARBY = 0;
    public static final int TAB_INDEX_TOPIC = 3;
    public static final int TAB_INDEX_VIDEO = 2;
    private static final TimeZone d = TimeZone.getTimeZone("GMT+8");
    private static final String[] e = new String[]{"隔壁", "已粉", "视频", "话题"};
    protected TipsView a;
    protected BroadcastReceiver b;
    BroadcastReceiver c = new jl(this);
    private int f = 0;
    private int g = -1;
    private View h;
    private RelativeLayout i;
    private IconButton j;
    private FragmentPagerAdapter k;
    private ArrayList<Fragment> l = new ArrayList();
    private Handler m = new Handler();
    public ViewPager mViewPager;
    private View n;
    private ImageView o;
    private TextView p;
    private NotificationModel q;
    private QiuyouCircleTab r;
    private TimerHelper s;
    private boolean t = false;
    private ImageView u;
    private DataSource<CloseableReference<CloseableImage>> v;
    private ImageView w;
    private SplashAdItem x;
    private boolean y;

    public interface IPageFocus {
        void setSelected(boolean z);
    }

    private static class a extends FragmentPagerAdapter {
        private final String[] a;
        private final List<Fragment> b;

        a(FragmentManager fragmentManager, List<Fragment> list, String[] strArr) {
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

    public static long calcSignTime(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                simpleDateFormat.setTimeZone(d);
                Date parse = simpleDateFormat.parse(str);
                Calendar instance = Calendar.getInstance(d);
                instance.setTime(parse);
                instance.set(11, 0);
                instance.set(12, 0);
                instance.set(13, 0);
                return instance.getTimeInMillis();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Calendar instance2 = Calendar.getInstance(d);
        instance2.set(11, 0);
        instance2.set(12, 0);
        instance2.set(13, 0);
        return instance2.getTimeInMillis();
    }

    protected boolean a() {
        long time = new Date().getTime() / 1000;
        if (SplashAdManager.mAnnouncement == null || SplashAdManager.mAnnouncement.start >= time || SplashAdManager.mAnnouncement.end <= time || ((SplashAdManager.mAnnouncement.location != 2 && SplashAdManager.mAnnouncement.location != 0) || SplashAdManager.mAnnouncement.hasClick)) {
            return false;
        }
        return true;
    }

    protected void b() {
        if (SplashAdManager.mAnnouncement != null && this.a != null) {
            this.a.setTipsViewTextContent(SplashAdManager.mAnnouncement.content);
            a(true);
            this.a.setTipsViewBgColor(UIHelper.isNightTheme() ? -1723489030 : -431643398);
            this.a.setOnClickListener(new jt(this));
        }
    }

    protected void a(boolean z) {
        if (this.a != null) {
            if (z) {
                this.a.setVisibility(0);
            } else {
                this.a.setVisibility(8);
            }
        }
    }

    protected void c() {
        if (HttpUtils.netIsAvailable()) {
            a(false);
            if (a()) {
                b();
            }
        }
        if (!HttpUtils.netIsAvailable() && this.a != null) {
            a(true);
            this.a.setTipsViewTextContent(getActivity().getResources().getString(R.string.no_network));
            this.a.setTipsViewBgColor(getResources().getColor(R.color.widget_tips_view_bg_color));
            this.a.setOnClickListener(null);
        }
    }

    public void onTabClickListener(int i) {
        if (this.mViewPager != null) {
            if (this.mViewPager.getCurrentItem() == i && (this.k.getItem(this.mViewPager.getCurrentItem()) instanceof IArticleList)) {
                ((IArticleList) this.k.getItem(this.mViewPager.getCurrentItem())).scrollToTop();
            }
            this.mViewPager.setCurrentItem(i, false);
        }
        if (!(this.l.get(i) instanceof CircleTopicsFragment)) {
            UIHelper.hideKeyboard(getActivity());
        }
    }

    public int getTabCount() {
        if (this.k != null) {
            return this.k.getCount();
        }
        return 0;
    }

    public void onResume() {
        super.onResume();
        d();
        f();
        if (QsbkApp.currentUser != null) {
            CircleVoteBuffer.retryVoteIfNeed(getActivity());
            this.q = QiuyouquanNotificationCountManager.getInstance(QsbkApp.currentUser.userId).getLastNotification();
            onNewNotification(this.q);
            QiuyouquanNotificationCountManager.getInstance(QsbkApp.currentUser.userId).addListener(this);
        } else {
            QiuyouquanNotificationCountManager.tryRemoveAll();
            this.n.setVisibility(8);
            a(null);
        }
        c();
    }

    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
        e();
        if (QsbkApp.currentUser != null) {
            QiuyouquanNotificationCountManager.getInstance(QsbkApp.currentUser.userId).removeListener(this);
        }
        getActivity().unregisterReceiver(this.b);
        if (!(this.v == null || this.v.isClosed())) {
            this.v.close();
        }
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.c);
    }

    private void e() {
        if (this.s != null) {
            this.s.stopTimer();
            this.s = null;
        }
    }

    private void a(int i) {
        e();
        this.s = new TimerHelper(this, (long) i, 240000);
        this.s.startTimer();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        a(5000);
        if (!j()) {
            SplashAdManager instance = SplashAdManager.instance();
            instance.doTaskOnLoaded(new ju(this));
            instance.loadSplashAd();
        }
    }

    protected void d() {
        getActivity().setProgressBarIndeterminateVisibility(false);
    }

    public View getAndInitPagerSlidingTabStrip(View view) {
        if (this.i == null) {
            Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_circle);
            this.i = new RelativeLayout(view.getContext());
            this.u = new ImageView(view.getContext());
            this.u.setId(R.id.add);
            this.u.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_create_night : R.drawable.group_create);
            this.u.setScaleType(ScaleType.CENTER);
            this.u.setVisibility(0);
            this.u.setOnClickListener(new jw(this));
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(UIHelper.dip2px(view.getContext(), 48.0f), -2);
            layoutParams.addRule(11);
            layoutParams.addRule(15);
            this.i.addView(this.u, layoutParams);
            this.j = new IconButton(view.getContext());
            this.j.setId(R.id.btn);
            this.j.setIconResource(UIHelper.isNightTheme() ? R.drawable.qiuyou_circle_foot_night : R.drawable.qiuyou_circle_foot);
            this.j.setGravity(16);
            this.j.setTextColor(UIHelper.isNightTheme() ? -5674752 : -156113);
            this.j.setVisibility(4);
            int dip2px = UIHelper.dip2px(view.getContext(), 5.0f);
            int dip2px2 = UIHelper.dip2px(view.getContext(), 7.0f);
            int dip2px3 = UIHelper.dip2px(view.getContext(), 3.0f);
            this.j.setPadding(dip2px, dip2px3, dip2px2, dip2px3);
            this.j.setIconPadding(dip2px3);
            this.j.setTextSize((float) UIHelper.dip2px(view.getContext(), 14.0f));
            this.j.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.circle_sign_in_bg_night : R.drawable.circle_sign_in_bg);
            this.j.setOnClickListener(new LoginPermissionClickDelegate(new jx(this), null));
            if (!SharePreferenceUtils.getSharePreferencesBoolValue(SP_CIRCLE_SIGN_TIP)) {
                this.j.getViewTreeObserver().addOnGlobalLayoutListener(new jy(this));
            }
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(15);
            layoutParams.addRule(0, R.id.add);
            layoutParams.leftMargin = dip2px2;
            this.i.addView(this.j, layoutParams);
            this.r = new QiuyouCircleTab(view.getContext());
            this.r.notifyDataSetChanged();
            this.r.setTextColor(UIHelper.isNightTheme() ? -10797805 : -1157627905);
            QiuyouCircleTab qiuyouCircleTab = this.r;
            if (UIHelper.isNightTheme()) {
                dip2px = -4359412;
            } else {
                dip2px = -1;
            }
            qiuyouCircleTab.setSelectedTabTextColor(dip2px);
            this.r.setTabBackground(R.color.transparent);
            this.r.setUnderlineHeight(0);
            this.r.setIndicatorHeight(0);
            this.r.setTextSize(UIHelper.dip2px(this.r.getContext(), 15.0f), UIHelper.dip2px(this.r.getContext(), 17.0f));
            this.r.setDividerColor(0);
            this.r.setDividerPadding(0);
            this.r.setSelectedTab(0);
            this.r.setTabsData(e);
            this.r.setITabOnClickListener(this);
            layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            dip2px2 = UIHelper.dip2px(this.r.getContext(), 4.0f);
            layoutParams.leftMargin = dip2px2;
            layoutParams.rightMargin = dip2px2;
            layoutParams.addRule(0, R.id.btn);
            this.i.addView(this.r, layoutParams);
            View view2 = new View(view.getContext());
            view2.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.ab_alpha_mask_dark : R.drawable.ab_alpha_mask);
            layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(0, R.id.btn);
            this.i.addView(view2, layoutParams);
            toolbar.addView(this.i);
        }
        return this.i;
    }

    public void setHeadVisible() {
        if (this.h != null && (this.h instanceof HeaderRelativeLayout)) {
            ((HeaderRelativeLayout) this.h).setHeadViewState(true);
        }
    }

    private void f() {
        if (QsbkApp.currentUser != null) {
            String str = QsbkApp.currentUser.userId;
            long sharePreferencesLongValue = SharePreferenceUtils.getSharePreferencesLongValue(SIGN_TIME + str);
            int sharePreferencesIntValue = SharePreferenceUtils.getSharePreferencesIntValue(SIGN_DAYS + str);
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(sharePreferencesLongValue);
            if (TimeUtils.isSameDay(instance, Calendar.getInstance(d))) {
                c(sharePreferencesIntValue);
                return;
            } else {
                g();
                return;
            }
        }
        h();
    }

    private void g() {
        if (!this.y && QsbkApp.currentUser != null) {
            this.y = true;
            new SimpleHttpTask(Constants.CIRCLE_SIGN_DAYS, new jz(this)).execute();
        }
    }

    private void h() {
        this.j.setText(b(0));
        this.j.setEnabled(true);
        this.j.setVisibility(0);
    }

    private SpannableStringBuilder b(int i) {
        CharSequence charSequence;
        if (i == 0) {
            charSequence = "签到";
        } else {
            charSequence = i + "天";
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        int length2 = spannableStringBuilder.length();
        VerticalImageSpan verticalImageSpan = new VerticalImageSpan(getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.ic_right_arrow_small_dark : R.drawable.ic_right_arrow_small), UIHelper.dip2px(getContext(), 7.0f), UIHelper.dip2px(getContext(), 10.0f));
        verticalImageSpan.setMargin(UIHelper.dip2px(getContext(), 3.0f), UIHelper.dip2px(getContext(), 3.0f));
        spannableStringBuilder.setSpan(verticalImageSpan, length, length2, 33);
        return spannableStringBuilder;
    }

    private void c(int i) {
        this.j.setText(b(i));
        this.j.setVisibility(0);
    }

    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            bundle.remove("android:support:fragments");
        }
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        this.b = new ka(this);
        getActivity().registerReceiver(this.b, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.c, new IntentFilter(Constants.ACTION_CIRCLE_LIKE));
    }

    private void a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.h = layoutInflater.inflate(R.layout.fragment_qiuyoucircle, viewGroup, false);
        this.mViewPager = (ViewPager) this.h.findViewById(R.id.pager);
        this.a = (TipsView) this.h.findViewById(R.id.tipsView);
        this.w = (ImageView) this.h.findViewById(R.id.activity_notification);
        this.w.setOnClickListener(new kb(this));
        i();
        this.mViewPager.setOffscreenPageLimit(5);
        this.mViewPager.setAdapter(this.k);
        getAndInitPagerSlidingTabStrip(this.h);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.h == null) {
            a(layoutInflater, viewGroup);
            this.n = this.h.findViewById(R.id.qiushi_notification);
            this.o = (ImageView) this.n.findViewById(R.id.qiushi_ic);
            this.p = (TextView) this.n.findViewById(R.id.qiushi_des);
            this.n.setOnClickListener(new jm(this));
        } else {
            ViewParent parent = this.h.getParent();
            if (this.n != null && this.n.getVisibility() == 0) {
                this.n.setVisibility(8);
            }
            if (parent != null) {
                ((ViewGroup) parent).removeView(this.h);
            }
        }
        LiveRecommendManager instance = LiveRecommendManager.getInstance();
        if (instance != null) {
            instance.refresh();
        }
        return this.h;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mViewPager.getViewTreeObserver().addOnPreDrawListener(new jn(this));
    }

    public View getQiuyouquanNotificationView() {
        return this.n;
    }

    public boolean canShowQiuyouquanNotificationView() {
        return (QsbkApp.currentUser == null || this.q == null || this.q.getUnReadCount() <= 0) ? false : true;
    }

    private void i() {
        this.l = new ArrayList(4);
        this.l.add(new NearbyCircleFragment());
        this.l.add(new FollowCircleFragment());
        this.l.add(new CircleVideoFragment());
        this.l.add(CircleTopicsFragment.newInstance(false));
        this.k = new a(getChildFragmentManager(), this.l, e);
    }

    private void a(String str) {
        boolean z = !TextUtils.isEmpty(str);
        FragmentActivity activity = getActivity();
        if (activity != null && (activity instanceof MainActivity)) {
            this.t = z;
            if (z) {
                ((MainActivity) activity).setTips(MainActivity.TAB_QIUYOUCIRCLE_ID, str);
            } else {
                ((MainActivity) activity).hideTips(MainActivity.TAB_QIUYOUCIRCLE_ID);
            }
        }
    }

    public void showSmallTipsOnMainActitivty(boolean z) {
        FragmentActivity activity = getActivity();
        if (activity != null && (activity instanceof MainActivity)) {
            if (!z) {
                ((MainActivity) activity).hideSmallTips(MainActivity.TAB_QIUYOUCIRCLE_ID);
            } else if (!this.t) {
                ((MainActivity) activity).setSmallTips(MainActivity.TAB_QIUYOUCIRCLE_ID);
            }
        }
    }

    public void refresh() {
        showSmallTipsOnMainActitivty(false);
        a(240000);
        if (this.mViewPager != null) {
            Fragment fragment = (Fragment) this.l.get(this.mViewPager.getCurrentItem());
            if (fragment instanceof IArticleList) {
                ((IArticleList) fragment).refresh();
            }
        }
    }

    public void onNewNotification(NotificationModel notificationModel) {
        if (notificationModel != null) {
            int unReadCount = notificationModel.getUnReadCount();
            if (unReadCount <= 0) {
                this.m.postDelayed(new jp(this), 100);
                return;
            }
            this.q = notificationModel;
            this.m.postDelayed(new jq(this, QsbkApp.absoluteUrlOfMediumUserIcon(notificationModel.getUserInfo().icon, notificationModel.getUserInfo().id), unReadCount), 100);
        }
    }

    public void process() {
        new jr(this).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new String[]{Constants.CIRCLE_CHECK});
    }

    public View getActivityNotification() {
        return this.w;
    }

    private boolean j() {
        SplashAdGroup group = SplashAdManager.instance().getGroup();
        if (group == null) {
            this.w.setVisibility(8);
            return false;
        }
        SplashAdItem activityItem = group.getActivityItem(SplashAdItem.TAB_CIRCLE);
        int correctTime = (int) (IMTimer.getInstance().getCorrectTime() / 1000);
        if (activityItem == null || activityItem.startTime > correctTime || correctTime >= activityItem.endTime) {
            this.w.setVisibility(8);
        } else {
            this.x = activityItem;
            this.w.setVisibility(8);
            this.v = Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(activityItem.picUrl), QsbkApp.mContext);
            this.v.subscribe(new js(this, activityItem), UiThreadImmediateExecutorService.getInstance());
        }
        return true;
    }

    public void navigateTo(int i) {
        this.g = i;
        if (this.l != null && this.l.size() > i) {
            if (this.r != null) {
                this.r.setSelectedTab(this.g);
            }
            if (this.mViewPager != null) {
                if (this.mViewPager.getCurrentItem() == i && (this.k.getItem(this.mViewPager.getCurrentItem()) instanceof IArticleList)) {
                    ((IArticleList) this.k.getItem(this.mViewPager.getCurrentItem())).scrollToTop();
                }
                this.mViewPager.setCurrentItem(i, false);
            }
            if (!(this.l.get(i) instanceof CircleTopicsFragment)) {
                UIHelper.hideKeyboard(getActivity());
            }
        }
    }

    public void gotoCircleVideo() {
        if (this.mViewPager != null) {
            this.mViewPager.setCurrentItem(2);
        }
    }
}
