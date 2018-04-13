package qsbk.app.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.Article;
import qsbk.app.model.CircleArticle;
import qsbk.app.share.ShareUtils;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.utils.ReportArticle;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.video.CircleVideoPlayerView;
import qsbk.app.widget.ArticleMoreOperationbar;

public class VideoFullScreenActivity extends BaseActionBarActivity {
    public static final int REQUEST_VIDEO = 3;
    private static VideoFullScreenActivity$SimpleVideoInfo[] a;
    private static VideoFullScreenActivity$FullPlayCallBack b;
    private CircleVideoPlayerView c;
    private View d;
    private View e;
    private ImageView f;
    private ProgressBar g;
    private VideoFullScreenActivity$SimpleVideoInfo[] h;
    private VideoFullScreenActivity$SimpleVideoInfo i;
    private VideoFullScreenActivity$FullPlayCallBack j;
    private int k;
    private boolean l;
    private View m;
    private View n;
    private View o;
    private CircleArticle p;
    private Article q;
    private View r;
    private boolean s = false;
    private int t = 3;
    private TextView u;
    private String v;
    private Runnable w = new aej(this);

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    @Deprecated
    public static void launch(Context context, String str, int i, int i2, long j) {
        VideoFullScreenActivity$SimpleVideoInfo videoFullScreenActivity$SimpleVideoInfo = new VideoFullScreenActivity$SimpleVideoInfo();
        videoFullScreenActivity$SimpleVideoInfo.url = str;
        videoFullScreenActivity$SimpleVideoInfo.width = i;
        videoFullScreenActivity$SimpleVideoInfo.height = i2;
        videoFullScreenActivity$SimpleVideoInfo.currentTime = j;
        launch(context, new VideoFullScreenActivity$SimpleVideoInfo[]{videoFullScreenActivity$SimpleVideoInfo}, null);
    }

    public static void launch(Context context, VideoFullScreenActivity$SimpleVideoInfo[] videoFullScreenActivity$SimpleVideoInfoArr, VideoFullScreenActivity$FullPlayCallBack videoFullScreenActivity$FullPlayCallBack) {
        a(context, videoFullScreenActivity$SimpleVideoInfoArr, 0, videoFullScreenActivity$FullPlayCallBack);
    }

    private static void a(Context context, VideoFullScreenActivity$SimpleVideoInfo[] videoFullScreenActivity$SimpleVideoInfoArr, int i, VideoFullScreenActivity$FullPlayCallBack videoFullScreenActivity$FullPlayCallBack) {
        Class cls = VideoFullScreenActivity.class;
        if (videoFullScreenActivity$SimpleVideoInfoArr != null && videoFullScreenActivity$SimpleVideoInfoArr.length > i && i >= 0) {
            VideoFullScreenActivity$SimpleVideoInfo videoFullScreenActivity$SimpleVideoInfo = videoFullScreenActivity$SimpleVideoInfoArr[i];
            if (videoFullScreenActivity$SimpleVideoInfo != null && videoFullScreenActivity$SimpleVideoInfo.width > videoFullScreenActivity$SimpleVideoInfo.height) {
                cls = LandscapeVideoFullScreenActivity.class;
            }
        }
        Intent intent = new Intent(context, cls);
        a = videoFullScreenActivity$SimpleVideoInfoArr;
        b = videoFullScreenActivity$FullPlayCallBack;
        intent.putExtra("index", i);
        if (!(context instanceof Activity) || (context instanceof VideoFullScreenActivity)) {
            context.startActivity(intent);
        } else {
            ((Activity) context).startActivityForResult(intent, 3);
        }
    }

    protected String f() {
        return "";
    }

    protected int a() {
        return R.layout.activity_video_fullscreen;
    }

    public void resetPlayer() {
        long currentTime = this.c.getCurrentTime();
        this.c.isPlaying();
        if (!TextUtils.equals(this.c.getVideo(), this.i.url)) {
            this.c.setVideo(this.i.url);
        }
        this.c.stop();
        this.c.setStartMs((currentTime / 1000) * 1000);
        this.c.play();
    }

    protected void a(Bundle bundle) {
        getSupportActionBar().hide();
        this.d = findViewById(R.id.back);
        this.d.setOnClickListener(new ael(this));
        this.f = (ImageView) findViewById(R.id.video_play);
        this.g = (ProgressBar) findViewById(R.id.video_progress);
        this.c = (CircleVideoPlayerView) findViewById(R.id.video_player);
        this.e = findViewById(R.id.video_control_bar);
        this.c.setControlBar(this.e);
        this.c.setWidget(this.g, this.f, null);
        this.c.setOnControlBarListener(new aem(this));
        if (this.L != null) {
            this.L.setOnClickListener(new aen(this));
        }
        ImageView imageView = (ImageView) this.e.findViewById(R.id.full_screen);
        if (imageView != null) {
            imageView.setImageResource(R.drawable.circle_video_zoom_out);
        }
        this.c.setLoop(false);
        this.m = findViewById(R.id.videoEndLayout);
        this.m.setOnClickListener(new aeo(this));
        this.u = (AppCompatTextView) findViewById(R.id.play_next);
        this.n = findViewById(R.id.share);
        this.n.setOnClickListener(new aep(this));
        this.o = findViewById(R.id.replay);
        this.o.setOnClickListener(new aeq(this));
        this.r = findViewById(R.id.collection_icon);
        a(getIntent());
        setupVideo();
        g();
    }

    private void a(Intent intent) {
        this.h = a;
        a = null;
        this.j = b;
        b = null;
        this.k = intent.getIntExtra("index", 0);
        if (this.h == null || this.k < 0 || this.k >= this.h.length) {
            finish();
        } else {
            this.i = this.h[this.k];
        }
    }

    public void setupVideo() {
        if (this.i == null || (this.i != null && TextUtils.isEmpty(this.i.url))) {
            finish();
            return;
        }
        this.c.setLandscape(this.i.width > this.i.height);
        if (!TextUtils.equals(this.c.getVideo(), this.i.url)) {
            this.c.setVideo(this.i.url);
        }
        this.c.setStartMs(this.i.currentTime);
        this.c.setAspectRatio(this.i.width, this.i.height);
        if (!this.c.isPlaying()) {
            this.c.play();
        }
        this.c.setOnVideoEventListener(new aer(this));
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        setupVideo();
    }

    private void i() {
        this.m.setVisibility(0);
        if (TextUtils.isEmpty(this.v)) {
            this.v = getString(R.string.auto_play_next);
        }
        this.u.setVisibility(0);
        this.u.setText(String.format(this.v, new Object[]{Integer.valueOf(this.t)}));
        getMainUIHandler().postDelayed(this.w, 1000);
        if (this.c != null) {
            this.c.hideControlBar();
        }
    }

    private void j() {
        int i = this.i.extraType;
        VideoFullScreenActivity$SimpleVideoInfo videoFullScreenActivity$SimpleVideoInfo = this.i;
        if (i == VideoFullScreenActivity$SimpleVideoInfo.TYPE_CircleArticle) {
            boolean z;
            if (this.p == null) {
                try {
                    this.p = CircleArticle.parseAsCircleArticle(new JSONObject(this.i.extra));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            CircleArticle circleArticle = this.p;
            String str = ShareUtils$OnCircleShareStartListener.TYPE_SHARE;
            if (this.i.width > this.i.height) {
                z = true;
            } else {
                z = false;
            }
            ShareUtils.openShareDialog((Activity) this, 1, circleArticle, str, z);
            return;
        }
        i = this.i.extraType;
        videoFullScreenActivity$SimpleVideoInfo = this.i;
        if (i == VideoFullScreenActivity$SimpleVideoInfo.TYPE_QiushiArticle) {
            boolean z2;
            boolean z3;
            if (this.q == null) {
                try {
                    this.q = new Article(new JSONObject(this.i.extra));
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            if (QsbkApp.allCollection == null || !QsbkApp.allCollection.contains(this.q.id)) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (z2) {
                this.r.setTag("active");
            } else {
                this.r.setTag("enable");
            }
            Article article = this.q;
            if (this.i.width > this.i.height) {
                z3 = true;
            } else {
                z3 = false;
            }
            ShareUtils.openShareDialogOrientation(null, this, 1, z2, article, z3);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void finish() {
        if (!(this.i == null || TextUtils.isEmpty(this.i.url))) {
            Intent intent = new Intent();
            intent.putExtra("index", this.k);
            intent.putExtra("video", this.i.url);
            if (this.c != null) {
                intent.putExtra("time", this.c.getCurrentTime());
            }
            intent.putExtra("completed", this.s);
            setResult(-1, intent);
        }
        super.finish();
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
            int dimensionPixelSize;
            try {
                dimensionPixelSize = Resources.getSystem().getDimensionPixelSize(Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
            } catch (Exception e) {
                dimensionPixelSize = 23;
            }
            this.d.setPadding(0, dimensionPixelSize, 0, 0);
        }
    }

    @SuppressLint({"NewApi"})
    protected void h() {
        if (VERSION.SDK_INT >= 19) {
            if (SystemBarTintManager.sPostLollipop) {
                b(false);
            } else {
                b(true);
            }
            if (this.L != null && VERSION.SDK_INT >= 14) {
                this.L.setFitsSystemWindows(true);
            }
            SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
            systemBarTintManager.setStatusBarTintEnabled(true);
            systemBarTintManager.setStatusBarDarkMode(UIHelper.isNightTheme(), this);
            systemBarTintManager.setStatusBarTintColor(-16777216);
        }
    }

    protected void onResume() {
        super.onResume();
        this.l = true;
        if (this.m.getVisibility() != 0) {
            this.c.playWithOutEnd();
        }
    }

    protected void onPause() {
        super.onPause();
        this.l = false;
        this.c.pause();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.c != null) {
            this.c.reset();
            this.c = null;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        int i3 = 0;
        if (i == 1) {
            if (this.i.extraType == VideoFullScreenActivity$SimpleVideoInfo.TYPE_CircleArticle) {
                if (intent != null) {
                    CircleArticle circleArticle = (CircleArticle) intent.getSerializableExtra("circleArticle");
                    if (circleArticle != null) {
                        ArticleMoreOperationbar.handleShare(i2, this, circleArticle);
                        i3 = 1;
                    } else {
                        return;
                    }
                }
            } else if (this.i.extraType == VideoFullScreenActivity$SimpleVideoInfo.TYPE_QiushiArticle) {
                if (i == 1) {
                    ShareUtils.doShare(i2, this, null, this.q, this.r);
                    if (i2 == 11) {
                        delete(this.q);
                        i3 = 1;
                    } else if (i2 == 13) {
                        i3 = 1;
                    } else if (i2 == 15) {
                        ShareUtils.shareArticle2QiuyouCircle((Context) this, this.q);
                        i3 = 1;
                    } else if (i2 == 12 && this.c != null) {
                        if (QsbkApp.getInstance().isAutoPlayVideo()) {
                            this.c.download();
                            i3 = 1;
                        } else {
                            this.c.downloadWithoutPlay();
                            i3 = 1;
                        }
                    }
                } else if (i == 2) {
                    ShareUtils.Share((Context) this, this.q.id, i2);
                    i3 = 1;
                } else if (i == 3) {
                    ReportArticle.setReportArticle(this.q, i2);
                    ReportArticle.reportHandler(true);
                    i3 = 1;
                } else if (i == 9) {
                    ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已成功分享！", Integer.valueOf(0)).show();
                }
            }
            i3 = 1;
        }
        if (i3 == 0) {
            super.onActivityResult(i, i2, intent);
        }
    }

    public void delete(Article article) {
        if (article != null) {
            new Builder(this).setTitle("刪除此条糗事？").setItems(new String[]{"立即删除", "取消"}, new aes(this, article)).show();
        }
    }

    private void a(Article article) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.DELETE_CREATE, new Object[]{article.id}), new aek(this));
        simpleHttpTask.setMapParams(new HashMap());
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
