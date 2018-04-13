package qsbk.app.activity;

import android.app.AlertDialog.Builder;
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
import java.util.HashMap;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.ad.feedsad.FeedsAd;
import qsbk.app.adapter.VideoImmersionAdapter;
import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;
import qsbk.app.cache.FileCache;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.HttpTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.Article;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.CollectionUtils;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.ReadCircle;
import qsbk.app.utils.ReportArticle;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.Util;
import qsbk.app.utils.VideoLoadConfig;
import qsbk.app.video.VideoInListHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.TipsView;

public class VideoImmersionActivity extends BaseActionBarActivity implements PtrListener {
    public static final int FIRST_PAGE = 1;
    private static final String j = VideoImmersionActivity.class.getSimpleName();
    private static Handler k = new Handler(Looper.myLooper());
    public static NativeMediaADData sLauchAD = null;
    protected Article a = null;
    protected Article b = null;
    protected View c = null;
    protected View d;
    protected View e;
    protected View f;
    protected TipsView g;
    int h;
    Runnable i = new aet(this);
    private int l = 1;
    private PtrLayout m;
    private ListView n;
    private ArrayList<Object> o;
    private VideoInListHelper p;
    private VideoImmersionAdapter q;
    private boolean r;
    private boolean s;
    private BroadcastReceiver t;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context, Article article, long j) {
        Intent intent = new Intent(context, VideoImmersionActivity.class);
        intent.putExtra("position", j);
        intent.putExtra("article", article);
        context.startActivity(intent);
    }

    public static void launch(Context context, Article article, long j, boolean z) {
        sLauchAD = null;
        Intent intent = new Intent(context, VideoImmersionActivity.class);
        intent.putExtra("position", j);
        intent.putExtra("is_fixed_number", z);
        intent.putExtra("article", article);
        context.startActivity(intent);
    }

    public static void launch(Context context, NativeMediaADData nativeMediaADData, long j) {
        if (sLauchAD != null) {
            sLauchAD = nativeMediaADData;
            Intent intent = new Intent(context, VideoImmersionActivity.class);
            intent.putExtra("position", j);
            context.startActivity(intent);
        }
    }

    protected String f() {
        return "";
    }

    protected int a() {
        return R.layout.activity_video_immersion;
    }

    protected void a(Bundle bundle) {
        getSupportActionBar().hide();
        this.a = (Article) getIntent().getSerializableExtra("article");
        if (this.a == null && sLauchAD == null) {
            finish();
            return;
        }
        this.o = new ArrayList();
        if (this.a != null) {
            this.o.add(this.a);
        } else if (sLauchAD != null) {
            this.o.add(sLauchAD);
        }
        initViews();
        g();
        this.t = new afd(this);
        registerReceiver(this.t, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
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
            this.e.setPadding(0, i, 0, 0);
            this.h = i + getResources().getDimensionPixelSize(R.dimen.abc_action_bar_default_height);
            LayoutParams layoutParams = new AbsListView.LayoutParams(-1, this.h);
            this.q.setOffset(this.h);
            this.f.setLayoutParams(layoutParams);
        }
    }

    public void initViews() {
        this.e = findViewById(R.id.custom_action_bar);
        findViewById(R.id.back).setOnClickListener(new afe(this));
        this.m = (PtrLayout) findViewById(R.id.ptr);
        this.n = (ListView) findViewById(R.id.listview);
        this.m.setRefreshEnable(false);
        this.m.setLoadMoreEnable(true);
        this.m.setPtrListener(this);
        int longExtra = (int) getIntent().getLongExtra("position", 0);
        this.s = getIntent().getBooleanExtra("is_fixed_number", false);
        this.q = new VideoImmersionAdapter(this.o, this, this.n, longExtra, "video");
        this.h = getResources().getDimensionPixelSize(R.dimen.abc_action_bar_default_height);
        this.q.setOffset(this.h);
        View view = new View(this);
        view.setLayoutParams(new AbsListView.LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.abc_action_bar_default_height)));
        view.setBackgroundColor(-16777216);
        this.f = view;
        this.n.setBackgroundColor(-16777216);
        this.n.addHeaderView(view);
        this.n.setAdapter(this.q);
        this.n.setOnItemClickListener(new aff(this));
        this.n.setOnItemLongClickListener(new afg(this));
        this.p = new afh(this, this.n);
        StatService.onEvent(QsbkApp.mContext, "video_immersion", VideoLoadConfig.getName());
        StatSDK.onEvent(QsbkApp.mContext, "video_immersion", VideoLoadConfig.getName());
        this.n.post(new afi(this));
        this.m.setOnScrollListener(new afj(this));
        this.g = (TipsView) findViewById(R.id.tipsView);
        this.g.setTipsViewBgColor(getResources().getColor(R.color.transparent));
        i();
        ReadCircle.trackListView(this.m);
        if (this.s) {
            this.m.setLoadMoreEnable(false);
        } else {
            this.m.loadMore();
        }
    }

    private void j() {
        boolean z = false;
        if (this.d != null && this.d.getTag().equals("active")) {
            z = true;
        }
        ShareUtils.openShareDialog(null, this, 1, z, this.b, this.d);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        View findViewById;
        int i3 = 0;
        super.onActivityResult(i, i2, intent);
        if (this.c != null) {
            findViewById = this.c.findViewById(R.id.layerMask);
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            this.c = null;
        }
        if (i == 3 && i2 == -1 && intent != null) {
            Object stringExtra = intent.getStringExtra("video");
            long longExtra = intent.getLongExtra("time", 0);
            boolean booleanExtra = intent.getBooleanExtra("completed", false);
            if (this.n != null && this.q != null && this.n.getAdapter().getCount() > 0) {
                int i4;
                for (i4 = 0; i4 < this.n.getChildCount(); i4++) {
                    findViewById = this.n.getChildAt(i4);
                    if (findViewById != null && (findViewById.getTag() instanceof VideoImmersionCell)) {
                        VideoImmersionCell videoImmersionCell = (VideoImmersionCell) findViewById.getTag();
                        if (videoImmersionCell.player != null && TextUtils.equals(stringExtra, videoImmersionCell.player.getVideo())) {
                            videoImmersionCell.player.stop();
                            videoImmersionCell.player.setStartMs(longExtra);
                            this.p.putStartTime(stringExtra, longExtra);
                            int positionForView = this.n.getPositionForView(videoImmersionCell.getCellView());
                            if (positionForView < this.n.getAdapter().getCount()) {
                                this.n.post(new afk(this, booleanExtra, positionForView));
                                return;
                            }
                            return;
                        }
                    }
                }
                i4 = 0;
                while (i3 < this.q.getCount()) {
                    Object itemAtPosition = this.n.getItemAtPosition(i3);
                    if (itemAtPosition != null) {
                        i4++;
                    }
                    if ((itemAtPosition instanceof Article) && TextUtils.equals(stringExtra, ((Article) itemAtPosition).getVideoUrl())) {
                        LogUtil.d(j + "scroll position = " + i3 + "play position = ");
                        this.p.putStartTime(stringExtra, longExtra);
                        this.n.post(new aeu(this, i4));
                        return;
                    }
                    i3++;
                }
                return;
            }
            return;
        }
        ShareUtils shareUtils = new ShareUtils();
        if (i2 >= 1 && this.b != null) {
            if (i == 1) {
                ShareUtils.doShare(i2, this, null, this.b, this.d);
                if (i2 != 6 || !this.d.getTag().equals("enable")) {
                    if (i2 == 11) {
                        if (this.o.contains(this.b)) {
                            delete(this.b);
                        }
                    } else if (i2 == 12) {
                        if (this.o.contains(this.b)) {
                            ((VideoImmersionCell) this.n.getChildAt((this.o.indexOf(this.b) - this.n.getFirstVisiblePosition()) + this.n.getHeaderViewsCount()).getTag()).download();
                        }
                    } else if (i2 == 13) {
                        if (this.o.contains(this.b)) {
                            anonymous(this.b);
                        }
                    } else if (i2 == 14) {
                        if (this.o.contains(this.b)) {
                            forbid(this.b);
                        }
                    } else if (i2 == 15) {
                        ShareUtils.shareArticle2QiuyouCircle(this, this.b);
                    }
                }
            } else if (i == 2) {
                ShareUtils.Share(this, this.b.id, i2);
            } else if (i == 3) {
                report(this.b, i2);
            }
        }
    }

    public void loadVideos() {
        if (!this.s) {
            LocationHelper.loadCache();
            String format = String.format(Constants.VIDEO_IMMERSION_LIST, new Object[]{Integer.valueOf(this.l), Double.valueOf(LocationHelper.getLatitude()), Double.valueOf(LocationHelper.getLongitude())});
            new HttpTask(format, format, new aev(this)).execute(new Void[0]);
        }
    }

    private void a(int i) {
        FeedsAd.getInstance().insertImmersionGdtVideoAd(i, this.o);
    }

    public void delete(Article article) {
        if (article != null) {
            new Builder(this).setTitle("刪除此条糗事？").setItems(new String[]{"立即删除", "取消"}, new aew(this, article)).show();
        }
    }

    private void a(Article article) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.DELETE_CREATE, new Object[]{article.id}), new aex(this, article));
        simpleHttpTask.setMapParams(new HashMap());
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void anonymous(Article article) {
        if (article != null) {
            new Builder(this).setTitle("将此条糗事匿名？").setItems(new String[]{"匿名", "取消"}, new aey(this, article)).show();
        }
    }

    private void b(Article article) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.ANONYMOUS_CREATE, new Object[]{article.id}), new aez(this, article));
        simpleHttpTask.setMapParams(new HashMap());
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void forbid(Article article) {
        if (article != null) {
            new Builder(this).setTitle("删除该糗事并封禁该用户？").setItems(new String[]{"删除并封禁", "取消"}, new afa(this, article)).show();
        }
    }

    private void c(Article article) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.FORBID_CREATE, new Object[]{article.id}), new afb(this, article));
        simpleHttpTask.setMapParams(new HashMap());
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void report(Article article, int i) {
        if (article != null) {
            if (QsbkApp.currentUser == null) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能举报哦!", Integer.valueOf(0)).show();
                LoginPermissionClickDelegate.startLoginActivity(this);
                return;
            }
            ReportArticle.setReportArticle(article, i);
            int i2 = 0;
            while (i2 < this.o.size()) {
                if (this.o.get(i2).equals(article)) {
                    this.o.remove(article);
                    break;
                }
                i2++;
            }
            i2 = 0;
            this.q.notifyDataSetChanged();
            b(i2);
            FileCache.cacheTextToDiskImmediately(this, "video", CollectionUtils.artilesToJsonString(this.o));
            ReportArticle.reportHandler(true);
        }
    }

    protected void onResume() {
        super.onResume();
        this.r = true;
        checkToPlay();
        if (this.c != null) {
            this.c.findViewById(R.id.layerMask).setVisibility(8);
            this.c = null;
        }
    }

    protected void onPause() {
        super.onPause();
        this.r = false;
        this.p.stopAll();
    }

    protected void onDestroy() {
        if (this.t != null) {
            unregisterReceiver(this.t);
        }
        super.onDestroy();
        this.a = null;
        k.removeCallbacks(this.i);
    }

    public void checkToPlay() {
        if (this.r) {
            this.p.autoPlay();
        }
    }

    public void onRefresh() {
    }

    public void onLoadMore() {
        loadVideos();
    }

    protected void i() {
        if (HttpUtils.isWifi(this) || !HttpUtils.netIsAvailable()) {
            a(false);
            return;
        }
        this.g.setTipsViewTextContent(getResources().getString(R.string.mobile_connect));
        this.g.setOnClickListener(null);
        a(true);
        k.postDelayed(this.i, 3000);
    }

    protected void a(boolean z) {
        DebugUtil.debug("luolong", "showOrHideTipsView, flag=" + z);
        if (this.g != null) {
            if (z) {
                this.g.setVisibility(0);
            } else {
                this.g.setVisibility(8);
            }
        }
    }

    private void b(int i) {
        if (this.n != null) {
            this.n.postDelayed(new afc(this, i), 100);
        } else {
            checkToPlay();
        }
    }
}
