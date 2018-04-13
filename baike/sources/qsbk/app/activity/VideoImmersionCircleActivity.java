package qsbk.app.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ListView;
import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;
import com.qq.e.ads.nativ.NativeMediaADData;
import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.ad.feedsad.FeedsAd;
import qsbk.app.adapter.VideoImmersionCircleAdapter;
import qsbk.app.adapter.VideoImmersionCircleAdapter.VideoImmersionCell;
import qsbk.app.http.HttpTask;
import qsbk.app.model.CircleArticle;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.share.ShareUtils;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.CircleArticleBus.OnArticleUpdateListener;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ReadCircle;
import qsbk.app.utils.Util;
import qsbk.app.utils.VideoLoadConfig;
import qsbk.app.video.VideoInListHelper;
import qsbk.app.widget.ArticleMoreOperationbar;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.TipsView;

public class VideoImmersionCircleActivity extends BaseActionBarActivity implements ShareUtils$OnCircleShareStartListener, OnArticleUpdateListener, PtrListener {
    public static final int FIRST_PAGE = 1;
    public static NativeMediaADData TEMP_REF = null;
    private static final String f = VideoImmersionCircleActivity.class.getSimpleName();
    private static Handler g = new Handler(Looper.myLooper());
    private static String h = "fromQiushiRecommend";
    private static String i = "fromChicken";
    protected TipsView a;
    protected View b;
    protected View c;
    int d;
    Runnable e = new afl(this);
    private int j = 1;
    private PtrLayout k;
    private ListView l;
    public CircleArticle lauchCircleArticle = null;
    private ArrayList<Object> m;
    private VideoInListHelper n;
    private VideoImmersionCircleAdapter o;
    private CircleArticle p;
    private boolean q;
    private BroadcastReceiver r;
    private String s;
    private boolean t = false;
    private boolean u = false;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context, CircleArticle circleArticle, long j, String str, boolean z) {
        Intent intent = new Intent(context, VideoImmersionCircleActivity.class);
        intent.putExtra("position", j);
        intent.putExtra(h, z);
        intent.putExtra("circleArticle", circleArticle);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("articleId", str);
        }
        context.startActivity(intent);
    }

    public static void launch(Context context, CircleArticle circleArticle, long j, String str, boolean z, boolean z2) {
        Intent intent = new Intent(context, VideoImmersionCircleActivity.class);
        intent.putExtra("position", j);
        intent.putExtra(h, z);
        intent.putExtra(i, z2);
        intent.putExtra("circleArticle", circleArticle);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("articleId", str);
        }
        context.startActivity(intent);
    }

    public static void launch(Context context, CircleArticle circleArticle, long j) {
        launch(context, circleArticle, j, null, false);
    }

    public static void launch(Context context, CircleArticle circleArticle, long j, boolean z) {
        launch(context, circleArticle, j, null, true);
    }

    public static void launch(Context context, NativeMediaADData nativeMediaADData, long j) {
        TEMP_REF = nativeMediaADData;
        Intent intent = new Intent(context, VideoImmersionCircleActivity.class);
        intent.putExtra("position", j);
        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        context.startActivity(intent);
    }

    protected String f() {
        return "";
    }

    protected int a() {
        return R.layout.activity_video_immersion;
    }

    protected void a(Bundle bundle) {
        getSupportActionBar().hide();
        this.lauchCircleArticle = (CircleArticle) getIntent().getSerializableExtra("circleArticle");
        if (this.lauchCircleArticle == null && TEMP_REF == null) {
            finish();
            return;
        }
        this.m = new ArrayList();
        if (this.lauchCircleArticle != null) {
            this.s = getIntent().getStringExtra("articleId");
            this.m.add(this.lauchCircleArticle);
        } else if (TEMP_REF != null) {
            this.m.add(TEMP_REF);
        }
        this.t = getIntent().getBooleanExtra(h, false);
        this.u = getIntent().getBooleanExtra(i, false);
        initViews();
        g();
        this.r = new afp(this);
        registerReceiver(this.r, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CircleArticleBus.register(this);
    }

    public void onDestroy() {
        CircleArticleBus.unregister(this);
        if (this.r != null) {
            unregisterReceiver(this.r);
        }
        this.lauchCircleArticle = null;
        TEMP_REF = null;
        if (this.n != null) {
            this.n.stopAll();
        }
        super.onDestroy();
    }

    protected boolean f_() {
        return false;
    }

    protected void g() {
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
            int i = Util.statusBarHeight;
            this.b.setPadding(0, i, 0, 0);
            this.d = i + getResources().getDimensionPixelSize(R.dimen.abc_action_bar_default_height);
            LayoutParams layoutParams = new AbsListView.LayoutParams(-1, this.d);
            this.o.setOffset(this.d);
            this.c.setLayoutParams(layoutParams);
        }
    }

    public void initViews() {
        this.b = findViewById(R.id.custom_action_bar);
        findViewById(R.id.back).setOnClickListener(new afq(this));
        this.k = (PtrLayout) findViewById(R.id.ptr);
        this.l = (ListView) findViewById(R.id.listview);
        this.k.setRefreshEnable(false);
        this.k.setLoadMoreEnable(true);
        this.k.setPtrListener(this);
        this.o = new VideoImmersionCircleAdapter(this.m, this, this.l, (int) getIntent().getLongExtra("position", 0), this);
        this.d = getResources().getDimensionPixelSize(R.dimen.abc_action_bar_default_height);
        this.o.setOffset(this.d);
        View view = new View(this);
        view.setLayoutParams(new AbsListView.LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.abc_action_bar_default_height)));
        view.setBackgroundColor(-16777216);
        this.c = view;
        this.l.setBackgroundColor(-16777216);
        this.l.addHeaderView(view);
        this.l.setAdapter(this.o);
        this.l.setOnItemClickListener(new afr(this));
        this.l.setOnItemLongClickListener(new afs(this));
        this.n = new aft(this, this.l);
        StatService.onEvent(QsbkApp.mContext, "video_circle_immersion", VideoLoadConfig.getName());
        StatSDK.onEvent(QsbkApp.mContext, "video_circle_immersion", VideoLoadConfig.getName());
        this.l.post(new afu(this));
        this.k.setOnScrollListener(new afv(this));
        this.a = (TipsView) findViewById(R.id.tipsView);
        this.a.setTipsViewBgColor(getResources().getColor(R.color.transparent));
        i();
        ReadCircle.trackListView(this.k);
        this.k.loadMore();
    }

    public void loadVideos() {
        LocationHelper.loadCache();
        String str = "";
        if (this.u) {
            str = String.format(Constants.CIRCLE_VIDEO_IMMERSION_LIST, new Object[]{Integer.valueOf(this.j), Integer.valueOf(0), Integer.valueOf(0)});
            if (!TextUtils.isEmpty(this.s)) {
                str = str + "&article_id=" + this.s;
            }
        } else if (this.u || !this.t) {
            str = String.format(Constants.CIRCLE_VIDEO_IMMERSION_LIST, new Object[]{Integer.valueOf(this.j), Double.valueOf(LocationHelper.getLatitude()), Double.valueOf(LocationHelper.getLongitude())});
            if (!TextUtils.isEmpty(this.s)) {
                str = str + "&article_id=" + this.s;
            }
        } else {
            str = String.format(Constants.CIRCLE_VIDEO_RECOMMEND_LIST, new Object[]{Integer.valueOf(this.j), Integer.valueOf(30)});
        }
        new HttpTask(str, str, new afw(this)).execute(new Void[0]);
    }

    private void a(int i) {
        FeedsAd.getQiuyouCircleInstance().insertImmersionGdtVideoAd(i, this.m);
    }

    protected void onResume() {
        super.onResume();
        this.q = true;
        checkToPlay();
    }

    public void checkToPlay() {
        if (this.q) {
            this.n.autoPlay();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        int i3 = 0;
        super.onActivityResult(i, i2, intent);
        if (i == 3 && i2 == -1 && intent != null) {
            Object stringExtra = intent.getStringExtra("video");
            long longExtra = intent.getLongExtra("time", 0);
            boolean booleanExtra = intent.getBooleanExtra("completed", false);
            if (this.l != null && this.l.getAdapter() != null && this.l.getAdapter().getCount() > 0) {
                int i4;
                for (i4 = 0; i4 < this.l.getChildCount(); i4++) {
                    View childAt = this.l.getChildAt(i4);
                    if (childAt != null && (childAt.getTag() instanceof VideoImmersionCell)) {
                        VideoImmersionCell videoImmersionCell = (VideoImmersionCell) childAt.getTag();
                        if (videoImmersionCell.playerView != null && TextUtils.equals(stringExtra, videoImmersionCell.playerView.getVideo())) {
                            videoImmersionCell.playerView.stop();
                            videoImmersionCell.playerView.setStartMs(longExtra);
                            this.n.putStartTime(stringExtra, longExtra);
                            int positionForView = this.l.getPositionForView(videoImmersionCell.getCellView());
                            if (positionForView < this.l.getAdapter().getCount()) {
                                this.l.post(new afm(this, booleanExtra, positionForView));
                                return;
                            }
                            return;
                        }
                    }
                }
                i4 = 0;
                while (i3 < this.o.getCount()) {
                    Object itemAtPosition = this.l.getItemAtPosition(i3);
                    if (itemAtPosition != null) {
                        i4++;
                    }
                    if ((itemAtPosition instanceof CircleArticle) && TextUtils.equals(stringExtra, ((CircleArticle) itemAtPosition).getVideoUrl())) {
                        this.n.putStartTime(stringExtra, longExtra);
                        LogUtil.d(f + "scroll position = " + i4 + " play position = " + i4 + this.l.getHeaderViewsCount());
                        this.l.post(new afn(this, i4));
                        return;
                    }
                    i3++;
                }
                return;
            }
            return;
        }
        ShareUtils shareUtils = new ShareUtils();
        if (i == 1 && i2 >= 1) {
            CircleArticle circleArticle = (CircleArticle) intent.getSerializableExtra("circleArticle");
            if (circleArticle == null) {
                return;
            }
            if (i2 != 12) {
                ArticleMoreOperationbar.handleShare(i2, (Activity) this, circleArticle);
            } else if (this.m.contains(circleArticle)) {
                ((VideoImmersionCell) this.l.getChildAt((this.m.indexOf(circleArticle) - this.l.getFirstVisiblePosition()) + this.l.getHeaderViewsCount()).getTag()).download();
            }
        } else if (i == 2) {
            ShareUtils.Share(this, this.p.id, i2);
        }
    }

    protected void onPause() {
        super.onPause();
        this.q = false;
        this.n.pause();
    }

    public void onRefresh() {
    }

    public void onLoadMore() {
        loadVideos();
    }

    public void onArticleCreate(CircleArticle circleArticle) {
    }

    public void onArticleUpdate(CircleArticle circleArticle) {
        if (this.m != null && this.m.size() != 0) {
            for (int i = 0; i < this.m.size(); i++) {
                Object obj = this.m.get(i);
                if (obj instanceof CircleArticle) {
                    CircleArticle circleArticle2 = (CircleArticle) obj;
                    if (TextUtils.equals(circleArticle2.id, circleArticle.id)) {
                        circleArticle2.updateWith(circleArticle);
                        break;
                    }
                }
            }
            this.o.notifyDataSetChanged();
            checkToPlay();
        }
    }

    public void onArticleDelete(CircleArticle circleArticle) {
        if (this.m != null && this.m.size() != 0) {
            int i = 0;
            while (i < this.m.size()) {
                Object obj = this.m.get(i);
                if (obj instanceof CircleArticle) {
                    CircleArticle circleArticle2 = (CircleArticle) obj;
                    if (TextUtils.equals(circleArticle2.id, circleArticle.id)) {
                        this.m.remove(circleArticle2);
                        break;
                    }
                }
                i++;
            }
            i = 0;
            this.o.notifyDataSetChanged();
            b(i);
        }
    }

    private void b(int i) {
        this.n.stopAll();
        if (this.l != null) {
            this.l.postDelayed(new afo(this, i), 100);
        } else {
            checkToPlay();
        }
    }

    public void onCircleShareStart(CircleArticle circleArticle) {
        ShareUtils.openShareDialog(this, 1, circleArticle);
    }

    public void onCircleShareStart(CircleArticle circleArticle, String str, View view) {
        ShareUtils.openShareDialog(this, 1, circleArticle, str);
    }

    protected void i() {
        if (HttpUtils.isWifi(this) || !HttpUtils.netIsAvailable()) {
            a(false);
            return;
        }
        this.a.setTipsViewTextContent(getResources().getString(R.string.mobile_connect));
        this.a.setOnClickListener(null);
        a(true);
        g.postDelayed(this.e, 3000);
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
}
