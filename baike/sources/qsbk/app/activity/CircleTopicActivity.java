package qsbk.app.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.text.Layout;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.qiniu.auth.Authorizer;
import com.qiniu.io.IO;
import com.qiniu.rs.PutExtra;
import com.qiniu.rs.UploadTaskExecutor;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.umeng.commonsdk.proguard.g;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.publish.CirclePublishActivity;
import qsbk.app.ad.feedsad.qbad.QbAdApkDownloader;
import qsbk.app.api.UserHeaderHelper;
import qsbk.app.core.AsyncTask;
import qsbk.app.fragments.CircleTopicFragment;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleTopic;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.BlurUtil;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.RemixUtil;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserClickDelegate;
import qsbk.app.widget.ArticleMoreOperationbar;
import qsbk.app.widget.LoadingLayout;
import qsbk.app.widget.PagerSlidingTabStrip;
import qsbk.app.widget.StickyNavLayout;
import qsbk.app.widget.TopicHeader;

public class CircleTopicActivity extends BaseActionBarActivity implements OnMenuItemClickListener {
    public static final int APP_DOWNLOADED = 2;
    public static final int APP_INSTALLED = 3;
    public static final int APP_UNDOWNLOAD = 1;
    public static final int TO_SHARE = 101;
    private boolean A = false;
    private boolean B = false;
    private RelativeLayout C;
    private TextView D;
    private ImageView E;
    private ImageView F;
    private ImageView G;
    private FrameLayout H;
    private FrameLayout I;
    private FrameLayout J;
    private RelativeLayout K;
    private ImageView O;
    private TextView P;
    private TextView Q;
    private LinearLayout R;
    private TextView S;
    private View T;
    private boolean U;
    private UserHeaderHelper V;
    private TextView W;
    private TopicHeader X;
    private View Y;
    private View Z;
    LoadingLayout a;
    private int aa;
    private PopupMenu ab;
    private ProgressDialog ac;
    private SimpleHttpTask ad;
    private String ae;
    StickyNavLayout b;
    ViewPager c;
    TopicHeader d;
    PagerSlidingTabStrip e;
    ArrayList<Fragment> f = new ArrayList();
    private CircleTopic g;
    private CircleTopicPagerAdapter h;
    private ImageView i;
    private TextView j;
    private ImageView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private View o;
    private View p;
    private View q;
    private View r;
    private View s;
    private View t;
    private ImageView u;
    private View v;
    private TextView w;
    private View x;
    private ImageView y;
    private TextView z;

    public static final class CircleTopicPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<String> a;
        private ArrayList<Fragment> b;

        public CircleTopicPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> arrayList, ArrayList<String> arrayList2) {
            super(fragmentManager);
            this.b = arrayList;
            this.a = arrayList2;
        }

        public CharSequence getPageTitle(int i) {
            return (CharSequence) this.a.get(i);
        }

        public Fragment getItem(int i) {
            return (Fragment) this.b.get(i);
        }

        public int getCount() {
            return this.a.size();
        }
    }

    public static void launch(Context context, String str) {
        launch(context, new CircleTopic(str), 0);
    }

    public static void launch(Context context, CircleTopic circleTopic, int i) {
        launch(context, circleTopic, i, true);
    }

    public static void launch(Context context, CircleTopic circleTopic, int i, boolean z) {
        Intent intent = new Intent(context, CircleTopicActivity.class);
        intent.putExtra("topic", circleTopic);
        intent.putExtra("pic_index", i);
        if (z) {
            intent.addFlags(67108864);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    public static void launch(Context context, CircleTopic circleTopic, int i, boolean z, String str) {
        Intent intent = new Intent(context, CircleTopicActivity.class);
        intent.putExtra("topic", circleTopic);
        intent.putExtra("pic_index", i);
        intent.putExtra("circle_article_id", str);
        if (z) {
            intent.addFlags(67108864);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    private static void a(String str, String str2, ImageView imageView) {
        if (TextUtils.isEmpty(str)) {
            imageView.setImageResource(UIHelper.getDefaultAvatar());
        } else {
            FrescoImageloader.displayAvatar(imageView, QsbkApp.absoluteUrlOfMediumUserIcon(str, str2));
        }
    }

    public static boolean isAdmin() {
        if (QsbkApp.currentUser == null || (!TextUtils.equals(QsbkApp.currentUser.adminRole, "admin") && !"1".equalsIgnoreCase(QsbkApp.currentUser.userId))) {
            return false;
        }
        return true;
    }

    protected CharSequence getCustomTitle() {
        return null;
    }

    protected int a() {
        return R.layout.activity_topic;
    }

    protected boolean f_() {
        return false;
    }

    protected void a(Bundle bundle) {
        getSupportActionBar().hide();
        Intent intent = getIntent();
        this.g = (CircleTopic) intent.getSerializableExtra("topic");
        this.ae = intent.getStringExtra("circle_article_id");
        if (this.g == null) {
            finish();
            return;
        }
        g();
        e();
        i();
        this.V = new UserHeaderHelper(this, bundle);
        CircleTopicManager.getInstance().insertTopicToLRU(this.g);
    }

    private void g() {
        this.aa = UIHelper.dip2px(this, 30.0f);
        this.Z = findViewById(R.id.custom_action_bar);
        this.W = (TextView) findViewById(R.id.back);
        this.W.setText("话题");
        this.W.setOnClickListener(new gy(this));
        this.Y = findViewById(R.id.more);
        this.Y.setBackgroundResource(UIHelper.getFakeOverflowMenuIcon());
        this.Y.setOnClickListener(new hl(this));
        this.s = findViewById(R.id.topic_bar);
        this.s.setOnClickListener(new LoginPermissionClickDelegate(new ib(this), null));
        this.t = findViewById(R.id.black_room_tip);
        this.u = (ImageView) findViewById(R.id.remix);
        this.w = (TextView) findViewById(R.id.topic_bar_text);
        this.v = findViewById(R.id.bar_line);
        this.e = (PagerSlidingTabStrip) findViewById(R.id.id_stickynavlayout_indicator);
        this.e.setTextSize(UIHelper.dip2px(this, 12.0f));
        this.e.setTextColor(getResources().getColor(R.color.gray));
        this.e.setSelectedTabTextColor(getResources().getColor(R.color.yellow));
        this.d = (TopicHeader) findViewById(R.id.header);
        this.c = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
        this.b = (StickyNavLayout) findViewById(R.id.sticky_layout);
        this.X = (TopicHeader) findViewById(R.id.header);
        this.i = (ImageView) findViewById(R.id.header_image);
        this.T = findViewById(R.id.topic_title);
        this.j = (TextView) findViewById(R.id.topic_title);
        this.k = (ImageView) findViewById(R.id.topic_icon);
        this.l = (TextView) findViewById(R.id.topic_total);
        this.m = (TextView) findViewById(R.id.topic_today);
        this.n = (TextView) findViewById(R.id.topic_intro);
        this.o = findViewById(R.id.topic_intro_modify);
        this.p = findViewById(R.id.topic_intro_more);
        this.q = findViewById(R.id.topic_des_more_icon);
        this.r = findViewById(R.id.topic_add_intro);
        this.x = findViewById(R.id.apply_for_master);
        this.y = (ImageView) findViewById(R.id.master_avatar);
        this.z = (TextView) findViewById(R.id.master_nick);
        this.C = (RelativeLayout) findViewById(R.id.clocked_rank_relativelayout);
        this.D = (TextView) findViewById(R.id.clocked_rank);
        this.D.setTextColor(UIHelper.isNightTheme() ? -12169122 : -4276546);
        this.E = (ImageView) findViewById(R.id.avatar);
        this.F = (ImageView) findViewById(R.id.avatar1);
        this.G = (ImageView) findViewById(R.id.avatar2);
        this.H = (FrameLayout) findViewById(R.id.frame);
        this.I = (FrameLayout) findViewById(R.id.frame1);
        this.J = (FrameLayout) findViewById(R.id.frame2);
        this.K = (RelativeLayout) findViewById(R.id.topic_rank_relative_layout);
        this.O = (ImageView) findViewById(R.id.topic_rank_icon);
        this.P = (TextView) findViewById(R.id.topic_rank);
        this.Q = (TextView) findViewById(R.id.topic_rank_text);
        this.a = (LoadingLayout) findViewById(R.id.loading);
        this.C.setOnClickListener(new iq(this));
        this.k.setOnClickListener(new iu(this));
        this.n.setMaxLines(1);
        this.n.getViewTreeObserver().addOnPreDrawListener(new ix(this));
        this.T.setOnLongClickListener(new iy(this));
        OnClickListener izVar = new iz(this);
        this.o.setOnClickListener(izVar);
        this.r.setOnClickListener(izVar);
        this.x.setOnClickListener(new ja(this));
        izVar = new gz(this);
        this.p.setOnClickListener(izVar);
        this.n.setOnClickListener(izVar);
        this.Z.getViewTreeObserver().addOnGlobalLayoutListener(new hc(this));
        this.b.setOnScrollHeaderListener(new hd(this));
    }

    protected void e() {
        if (VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(67108864);
            window.getDecorView().setSystemUiVisibility(1280);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
        } else if (VERSION.SDK_INT >= 19) {
            b(true);
        }
        if (VERSION.SDK_INT >= 19) {
            int dimensionPixelSize;
            try {
                dimensionPixelSize = Resources.getSystem().getDimensionPixelSize(Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
            } catch (Exception e) {
                dimensionPixelSize = 23;
            }
            this.Z.setPadding(0, dimensionPixelSize, 0, 0);
            this.X.setStatusBarHeight(dimensionPixelSize);
        }
        this.R = (LinearLayout) findViewById(R.id.clocked_bar);
        this.S = (TextView) findViewById(R.id.clocked_bar_text);
        this.R.setOnClickListener(new he(this));
    }

    private void i() {
        this.a.onLoading();
        refresh();
        this.f.add(CircleTopicFragment.newInstance(this.g.id, 0, this.ae));
        this.f.add(CircleTopicFragment.newInstance(this.g.id, 1));
        ArrayList arrayList = new ArrayList();
        arrayList.add("默认");
        arrayList.add("最新");
        this.h = new CircleTopicPagerAdapter(getSupportFragmentManager(), this.f, arrayList);
        this.c.setAdapter(this.h);
        this.e.setViewPager(this.c, new hf(this));
    }

    void f() {
        boolean z;
        int i = this.g.user == null ? 1 : 0;
        if (i == 0 && QsbkApp.currentUser != null && QsbkApp.currentUser.userId.equals(this.g.user.userId)) {
            z = true;
        } else {
            z = false;
        }
        this.U = z;
        d(this.g.icon == null ? null : this.g.icon.url);
        this.W.setText(this.g.content);
        this.j.setText(this.g.content);
        if (this.g.isAnonymous) {
            this.j.setCompoundDrawablesWithIntrinsicBounds(0, 0, UIHelper.getTopicAnonymous(), 0);
        } else {
            this.j.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        this.l.setText(String.format("动态 %d", new Object[]{Integer.valueOf(this.g.articleCount)}));
        this.m.setText(String.format("今日 %d", new Object[]{Integer.valueOf(this.g.todayCount)}));
        if (TextUtils.isEmpty(this.g.intro)) {
            int i2;
            this.n.setVisibility(8);
            this.o.setVisibility(8);
            View view = this.r;
            if (this.U) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            view.setVisibility(i2);
            this.p.setVisibility(8);
        } else {
            this.n.setVisibility(0);
            this.n.setText(this.g.intro);
            this.r.setVisibility(8);
            this.o.setVisibility(this.U ? 0 : 8);
            Layout layout = this.n.getLayout();
            if (layout != null) {
                this.p.setVisibility(4);
                if (layout.getLineCount() >= 1 && layout.getEllipsisCount(0) > 0) {
                    this.p.setVisibility(0);
                }
            }
        }
        if (i != 0) {
            this.y.setVisibility(8);
            this.z.setVisibility(8);
            this.x.setVisibility(0);
        } else {
            this.x.setVisibility(8);
            this.y.setVisibility(0);
            this.z.setVisibility(0);
            if (!TextUtils.isEmpty(this.g.user.userIcon)) {
                a(this.g.user.userIcon, this.g.user.userId, this.y);
            }
            CharSequence remark = RemarkManager.getRemark(this.g.user.userId);
            if (TextUtils.isEmpty(remark)) {
                this.z.setText(this.g.user.userName);
            } else {
                this.z.setText(remark);
            }
        }
        OnClickListener userClickDelegate = this.g.user == null ? null : new UserClickDelegate(this.g.user.userId, new hg(this));
        this.y.setOnClickListener(userClickDelegate);
        this.z.setOnClickListener(userClickDelegate);
        this.w.setText(this.g.isAnonymous ? "匿名发动态，没人知道你是谁哦~" : "我也说一下");
        if (this.g != null && this.g.rank > 0) {
            this.K.setVisibility(0);
            this.P.setTextColor(UIHelper.isNightTheme() ? -8683381 : -12171706);
            this.Q.setTextColor(UIHelper.isNightTheme() ? -12169122 : -4276546);
            this.Q.setText("第" + this.g.rank + "名");
            this.K.setOnClickListener(new hh(this));
        }
        if (this.g.isBlackRoom()) {
            this.R.setVisibility(8);
            this.s.setVisibility(8);
            this.u.setVisibility(8);
            if (TextUtils.isEmpty(this.g.extraLink)) {
                this.v.setVisibility(8);
                this.t.setVisibility(8);
            } else {
                this.v.setVisibility(0);
                a(48);
                this.t.setVisibility(0);
                this.t.setOnClickListener(new hi(this));
            }
        } else if (this.g.isRemix()) {
            this.R.setVisibility(8);
            this.s.setVisibility(8);
            this.t.setVisibility(8);
            String str = this.g.extraLink;
            if (TextUtils.isEmpty(str)) {
                this.u.setVisibility(8);
                this.v.setVisibility(8);
            } else {
                this.v.setVisibility(0);
                a(60);
                this.u.setVisibility(0);
                String str2 = "Remix";
                str2 = RemixUtil.REMIX_PACKAGE_NAME;
                i = getApkStatus(str, RemixUtil.REMIX_PACKAGE_NAME);
                this.u.setOnClickListener(new hj(this, i, str));
                if (i == 3) {
                    this.u.setImageResource(R.drawable.circle_topic_remix_open);
                } else {
                    this.u.setImageResource(R.drawable.circle_topic_remix);
                }
            }
        } else if (this.g.isClocked()) {
            this.R.setVisibility(0);
            this.t.setVisibility(8);
            this.u.setVisibility(8);
            this.v.setVisibility(8);
            this.s.setVisibility(8);
        } else {
            this.t.setVisibility(8);
            this.u.setVisibility(8);
            this.R.setVisibility(8);
            if (this.g.canPublishArticle()) {
                this.v.setVisibility(0);
                a(48);
                this.s.setVisibility(0);
            } else {
                this.v.setVisibility(8);
                this.s.setVisibility(8);
            }
        }
        if (this.g.isBlackRoom() || this.g.isRemix() || this.g.isClocked() || this.g.canPublishArticle()) {
            this.c.setPadding(0, 0, 0, UIHelper.dip2px(this, 114.0f));
        } else {
            this.c.setPadding(0, 0, 0, 0);
        }
        if (this.g == null || this.g.clockedRankingUsers == null || this.g.clockedRankingUsers.size() <= 0) {
            this.C.setVisibility(8);
            this.C.getParent().requestLayout();
            return;
        }
        this.C.setVisibility(0);
        BaseUserInfo baseUserInfo;
        if (this.g.clockedRankingUsers.size() == 1) {
            baseUserInfo = (BaseUserInfo) this.g.clockedRankingUsers.get(0);
            this.H.setVisibility(0);
            this.I.setVisibility(8);
            this.J.setVisibility(8);
            a(baseUserInfo.userIcon, baseUserInfo.userId, this.E);
            this.H.setOnClickListener(new hk(this, baseUserInfo));
        } else if (this.g.clockedRankingUsers.size() == 2) {
            baseUserInfo = (BaseUserInfo) this.g.clockedRankingUsers.get(0);
            r1 = (BaseUserInfo) this.g.clockedRankingUsers.get(1);
            this.H.setVisibility(0);
            this.I.setVisibility(0);
            this.J.setVisibility(8);
            a(baseUserInfo.userIcon, baseUserInfo.userId, this.E);
            a(r1.userIcon, r1.userId, this.F);
            this.H.setOnClickListener(new hm(this, baseUserInfo));
            this.I.setOnClickListener(new hn(this, r1));
        } else if (this.g.clockedRankingUsers.size() == 3) {
            baseUserInfo = (BaseUserInfo) this.g.clockedRankingUsers.get(0);
            r1 = (BaseUserInfo) this.g.clockedRankingUsers.get(1);
            BaseUserInfo baseUserInfo2 = (BaseUserInfo) this.g.clockedRankingUsers.get(2);
            this.H.setVisibility(0);
            this.I.setVisibility(0);
            this.J.setVisibility(0);
            a(baseUserInfo.userIcon, baseUserInfo.userId, this.E);
            a(r1.userIcon, r1.userId, this.F);
            a(baseUserInfo2.userIcon, baseUserInfo2.userId, this.G);
            this.H.setOnClickListener(new ho(this, baseUserInfo));
            this.I.setOnClickListener(new hp(this, r1));
            this.J.setOnClickListener(new hq(this, baseUserInfo2));
        }
    }

    public boolean hasTop() {
        return this.B;
    }

    public boolean isTopicMaster() {
        return this.U;
    }

    public void getRankForApply() {
        e("");
        String str = QsbkApp.currentUser.userId;
        new SimpleHttpTask(String.format(Constants.PERSONAL_SCORE, new Object[]{str}), new hr(this)).execute();
    }

    private void j() {
        new hs(this, this).show();
    }

    private void k() {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.CIRCLE_TOPIC_APPLY_FOR_MASTER, new hv(this, this, "处理中"));
        Map hashMap = new HashMap();
        hashMap.put("topic_id", this.g.id);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    private void a(String str) {
        new hw(this, this, str).show();
    }

    private void b(String str) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.CIRCLE_TOPIC_UPDATE_INTRO, new ia(this, this, "处理中", str));
        Map hashMap = new HashMap();
        hashMap.put("topic_id", this.g.id);
        hashMap.put("topic_abstract", str);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    private void l() {
        CharSequence substring;
        StringBuffer append = new StringBuffer("举报 : ").append(this.g.content);
        if (append.length() > 30) {
            substring = append.substring(0, 29);
        } else {
            substring = append.toString();
        }
        String[] strArr = new String[]{g.an, "dirty", "abuse", "politics", "others"};
        new Builder(this).setTitle(substring).setItems(new String[]{"广告/欺诈", "淫秽色情", "骚扰谩骂", "反动政治", "其他"}, new id(this, strArr)).setNegativeButton("取消", new ic(this)).show();
    }

    private void c(String str) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.CIRCLE_REPORT_TOPIC, new Object[]{this.g.id}), new if(this, this, "处理中"));
        Map hashMap = new HashMap();
        hashMap.put("reason", str);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    private void m() {
        new ig(this, this).show();
    }

    private void n() {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.CIRCLE_TOPIC_MASTER_RESIGN, new ij(this, this, "处理中"));
        Map hashMap = new HashMap();
        hashMap.put("topic_id", this.g.id);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    private void d(String str) {
        String str2;
        CharSequence absoluteUrlOfCircleWebpImage = QsbkApp.absoluteUrlOfCircleWebpImage(str, this.g.createAt);
        if (TextUtils.isEmpty(absoluteUrlOfCircleWebpImage)) {
            str2 = "";
        } else {
            CharSequence charSequence = absoluteUrlOfCircleWebpImage;
        }
        Drawable drawable = getResources().getDrawable(R.drawable.circle_topic_default);
        Bitmap fastblur = BlurUtil.fastblur(((BitmapDrawable) drawable).getBitmap(), 16);
        FrescoImageloader.displayImage(this.k, str2, drawable);
        ((SimpleDraweeView) this.i).setController((PipelineDraweeController) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str2)).setPostprocessor(new ik(this, str2)).setResizeOptions(new ResizeOptions(300, 300)).build())).setOldController(((SimpleDraweeView) this.i).getController())).build());
        ((GenericDraweeHierarchy) ((SimpleDraweeView) this.i).getHierarchy()).setPlaceholderImage(new BitmapDrawable(fastblur), ScaleType.CENTER_CROP);
    }

    private void a(View view) {
        boolean z = true;
        if (this.ab == null) {
            this.ab = new PopupMenu(this, view);
            this.ab.inflate(R.menu.topic);
            this.ab.setOnMenuItemClickListener(this);
        }
        Menu menu = this.ab.getMenu();
        MenuItem findItem = menu.findItem(R.id.black_list_manage);
        MenuItem findItem2 = menu.findItem(R.id.resign_master);
        MenuItem findItem3 = menu.findItem(R.id.black_room);
        MenuItem findItem4 = menu.findItem(R.id.topic_report);
        boolean z2 = (this.U || this.g.isChicken()) ? false : true;
        findItem4.setVisible(z2);
        if ((!this.U || this.g.isAnonymous) && !isAdmin()) {
            z2 = false;
        } else {
            z2 = true;
        }
        findItem.setVisible(z2);
        if (this.U || (isAdmin() && this.g.user != null)) {
            z2 = true;
        } else {
            z2 = false;
        }
        findItem2.setVisible(z2);
        if (this.g.isBlackRoom()) {
            z = false;
        }
        findItem3.setVisible(z);
        menu.findItem(R.id.topic_publish).setVisible(false);
        this.ab.show();
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        if (QsbkApp.currentUser != null || menuItem.getItemId() == R.id.topic_more) {
            switch (menuItem.getItemId()) {
                case R.id.black_room:
                    launch(this, CircleTopic.BLACK_ROOM_ID);
                    break;
                case R.id.black_list_manage:
                    TopicBlackListActivity.launch(this, this.g);
                    break;
                case R.id.topic_share:
                    ShareUtils.openShareDialog(this, 101, this.g);
                    break;
                case R.id.topic_report:
                    l();
                    break;
                case R.id.topic_publish:
                    if (QsbkApp.currentUser != null) {
                        if (!this.g.isClocked()) {
                            CirclePublishActivity.launch((Context) this, this.g);
                            break;
                        }
                        clocked();
                        break;
                    }
                    LoginPermissionClickDelegate.startLoginActivity(this);
                    break;
                case R.id.topic_more:
                    CircleTopicsActivity.launch(this);
                    break;
                case R.id.resign_master:
                    m();
                    break;
                default:
                    break;
            }
        }
        startActivity(new Intent(this, ActionBarLoginActivity.class));
        return true;
    }

    private void a(int i) {
        LayoutParams layoutParams = (LayoutParams) this.v.getLayoutParams();
        layoutParams.bottomMargin = UIHelper.dip2px(this.v.getContext(), (float) i);
        this.v.setLayoutParams(layoutParams);
    }

    public int getApkStatus(String str, String str2) {
        if (QbAdApkDownloader.instance().isPackageInstalled(str2)) {
            return 3;
        }
        if (QbAdApkDownloader.instance().isDownloadApkExist(str)) {
            return 2;
        }
        return 1;
    }

    public Map<String, Object> getPostParams() {
        Map hashMap = new HashMap();
        double latitude = LocationHelper.getLatitude();
        double longitude = LocationHelper.getLongitude();
        if (!(latitude == 0.0d || longitude == 0.0d)) {
            hashMap.put(ParamKey.LATITUDE, Double.valueOf(latitude));
            hashMap.put(ParamKey.LONGITUDE, Double.valueOf(longitude));
        }
        Object city = LocationHelper.getCity();
        Object district = LocationHelper.getDistrict();
        if (!(TextUtils.isEmpty(city) || TextUtils.isEmpty(district))) {
            hashMap.put("location", city + "·" + district);
        }
        hashMap.put("topic_id", Integer.valueOf(Integer.parseInt(this.g.id)));
        return hashMap;
    }

    public void clocked() {
        SimpleHttpTask inVar = new in(this, Constants.CIRCLE_PUBLISH, new il(this));
        inVar.setMapParams(getPostParams());
        inVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void refresh() {
        this.ad = new SimpleHttpTask(String.format(Constants.CIRCLE_TOPIC_LIST_ALL, new Object[]{this.g.id, Integer.valueOf(1), Double.valueOf(LocationHelper.getLatitude()), Double.valueOf(LocationHelper.getLongitude())}), new io(this));
        this.ad.execute();
        if (this.f != null && this.f.size() > 0) {
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                Fragment fragment = (Fragment) it.next();
                if (fragment instanceof CircleTopicFragment) {
                    ((CircleTopicFragment) fragment).refresh();
                }
            }
        }
    }

    private void q() {
        if (this.ac == null) {
            this.ac = new ProgressDialog(this, 3);
            this.ac.setProgressStyle(0);
            this.ac.setCancelable(false);
        }
    }

    private void e(String str) {
        runOnUiThread(new ip(this, str));
    }

    private void r() {
        if (this.ac != null) {
            this.ac.cancel();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != 0) {
            switch (i) {
                case 1:
                    if (!(intent == null || intent.getData() == null)) {
                        this.V.doCropPhoto(intent.getData());
                        break;
                    }
                case 2:
                    String savePickedBitmap = this.V.savePickedBitmap(intent);
                    if (!TextUtils.isEmpty(savePickedBitmap)) {
                        e("上传图片中...");
                        f(savePickedBitmap);
                        break;
                    }
                    break;
                case 3:
                    this.V.doCropPhotoWithCaptured();
                    break;
                case 101:
                    CircleArticle circleArticle = (CircleArticle) intent.getSerializableExtra("circleArticle");
                    if (circleArticle == null) {
                        if (this.g != null) {
                            ShareUtils.doShare(i2, this, null, this.g, null, true);
                            break;
                        }
                    }
                    ArticleMoreOperationbar.handleShare(i2, (Activity) this, circleArticle);
                    break;
                    break;
            }
            super.onActivityResult(i, i2, intent);
        }
    }

    private void f(String str) {
        new SimpleHttpTask(Constants.CIRCLE_IMAGE_TOKEN, new ir(this, Uri.fromFile(new File(str)))).execute();
    }

    private UploadTaskExecutor a(String str, Uri uri) {
        String str2 = IO.UNDEFINED_KEY;
        PutExtra putExtra = new PutExtra();
        putExtra.params = new HashMap();
        Authorizer authorizer = new Authorizer();
        authorizer.setUploadToken(str);
        return IO.putFile(QsbkApp.getInstance().getApplicationContext(), authorizer, str2, uri, putExtra, new is(this, uri));
    }

    private void a(Uri uri, String str) {
        Map hashMap = new HashMap();
        hashMap.put("topic_id", this.g.id);
        hashMap.put("avatar_urls", str);
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.CIRCLE_TOPIC_AVATAR, new it(this));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }
}
