package qsbk.app.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mobstat.StatService;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.image.ImageSizeHelper;
import qsbk.app.model.Article;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.ImageSize;
import qsbk.app.model.ReportBean;
import qsbk.app.report.ReportUtils;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.image.issue.TaskExecutor;
import qsbk.app.utils.image.issue.TaskExecutor.Task;
import qsbk.app.video.VideoPlayerView;
import qsbk.app.widget.ArrowView;
import qsbk.app.widget.OverScrollView;
import qsbk.app.widget.QiushiImageLayout;

public class AuditNativeActivity2 extends BaseActionBarActivity implements OnClickListener, AnimationListener {
    private static final String a = AuditNativeActivity2.class.getName();
    private static final boolean b = DebugUtil.DEBUG;
    private static final String[] c = new String[]{"审核新糗事", "审核新糗事"};
    private static final String d = Constants.AUDIT.substring(0, Constants.AUDIT.indexOf("?"));
    private View A;
    private boolean B = true;
    private RelativeLayout C;
    private boolean D = true;
    private View E = null;
    private View F = null;
    private b G = null;
    private AtomicBoolean H = new AtomicBoolean(false);
    private String I = "";
    private int J = 0;
    private List<Article> K;
    private List<Article> O;
    private final Runnable P = new cs(this);
    private final Runnable Q = new cx(this);
    private int R;
    private Runnable S = new cy(this);
    private int T = -1;
    private Handler U = new cz(this, Looper.getMainLooper());
    private final Runnable V = new db(this);
    private final Runnable W = new dc(this);
    private final Runnable X = new dd(this);
    private final a e = new a(this);
    private final Runnable f = new cg(this);
    private boolean g = false;
    private ImageView h;
    private TextView i;
    private AnimationDrawable j;
    private View k;
    private View l;
    private AtomicBoolean m = new AtomicBoolean(Boolean.TRUE.booleanValue());
    private FrameLayout n;
    private List<View> o;
    private Drawable p;
    private Drawable q;
    private AnimationSet r;
    private AnimationSet s;
    private AnimationSet t;
    private AnimationSet u;
    private Animation v;
    private Animation w;
    private View x;
    private View y;
    private View z;

    class a {
        int a;
        LinkedList<View> b;
        final /* synthetic */ AuditNativeActivity2 c;

        a(AuditNativeActivity2 auditNativeActivity2, int i) {
            this.c = auditNativeActivity2;
            this.a = 1;
            if (i < 1) {
                throw new IllegalArgumentException("Max Size " + i + " must be positive.");
            }
            this.b = new LinkedList();
            this.a = i;
        }

        public a(AuditNativeActivity2 auditNativeActivity2) {
            this(auditNativeActivity2, 3);
        }

        public View add(View view) {
            View view2 = null;
            synchronized (this.b) {
                if (this.b.size() >= this.a) {
                    view2 = (View) this.b.removeFirst();
                }
                this.b.addLast(view);
            }
            return view2;
        }

        public List getAll() {
            return this.b;
        }

        public void clear() {
            if (this.b != null) {
                this.b.clear();
            }
        }
    }

    private static class b {
        ProgressBar a;
        QiushiImageLayout b;
        View c;
        ImageView d;
        TextView e;
        TextView f;
        ProgressBar g;
        VideoPlayerView h;
        ImageView i;
        public ImageView image;
        View j;
        ArrowView k;

        private b() {
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return c();
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, AuditNativeActivity2.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            getWindow().setFlags(8192, 8192);
        }
    }

    private void i() {
        this.A = findViewById(R.id.loading_content);
        this.h = (ImageView) findViewById(R.id.loading);
        this.i = (TextView) findViewById(R.id.loading_text);
        this.j = (AnimationDrawable) this.h.getDrawable();
        this.C = (RelativeLayout) findViewById(R.id.center_container);
        this.l = View.inflate(this, R.layout.layout_review_content2, null);
        this.C.addView(this.l);
        this.x = this.l.findViewById(R.id.bottom_right_container);
        this.y = this.l.findViewById(R.id.bottom_left_container);
        this.z = this.l.findViewById(R.id.bottom_layout);
        this.n = (FrameLayout) this.l.findViewById(R.id.scroll_view_container);
        this.k = View.inflate(this, R.layout.layout_review_rules, null);
        ((OverScrollView) this.k.findViewById(R.id.review_rules_item_container)).setOnLoadMoreListener(new de(this));
        this.C.addView(this.k);
    }

    private void j() {
        this.x.setOnClickListener(this);
        this.y.setOnClickListener(this);
        this.A.setOnClickListener(this);
        this.A.setClickable(false);
    }

    private void k() {
        if (!this.B) {
            this.C.setVisibility(8);
            this.A.setVisibility(0);
            this.i.setText("努力加载中...");
            this.h.postDelayed(new ch(this), 200);
            if (this.g) {
                this.h.postDelayed(new ci(this), 1700);
            }
        }
    }

    private void l() {
        if (b) {
            Log.e(a, "hideLoadingPage-->" + this.B);
        }
        if (this.A.getVisibility() == 0) {
            this.z.setVisibility(0);
            this.C.setVisibility(0);
            this.A.setVisibility(8);
            this.j.stop();
            this.j.setLevel(0);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
            case R.id.action_feedback:
                gotoFeedbackActivity();
                break;
            case R.id.action_about:
                Intent intent = new Intent(this, About.class);
                intent.putExtra("targetPage", "about");
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.K = new ArrayList();
        this.O = new ArrayList();
        this.p = null;
        this.q = null;
        i();
        j();
        this.U.postDelayed(new cj(this), 100);
    }

    protected void onStart() {
        super.onStart();
    }

    private void a(int i) {
        if (this.o != null) {
            this.o.clear();
            this.o = null;
        }
        this.o = new ArrayList(i);
        LayoutInflater from = LayoutInflater.from(this);
        for (int i2 = 0; i2 < i; i2++) {
            View inflate = from.inflate(R.layout.layout_review_content_item2, null);
            b bVar = new b();
            bVar.j = inflate.findViewById(R.id.report);
            bVar.k = (ArrowView) inflate.findViewById(R.id.arrow);
            bVar.a = (ProgressBar) inflate.findViewById(R.id.progressbar);
            bVar.b = (QiushiImageLayout) inflate.findViewById(R.id.image_layout);
            bVar.c = inflate.findViewById(R.id.video_layout);
            bVar.image = (ImageView) inflate.findViewById(R.id.content_img);
            bVar.d = (ImageView) inflate.findViewById(R.id.image_tip);
            bVar.f = (TextView) inflate.findViewById(R.id.content_txt);
            bVar.e = (TextView) inflate.findViewById(R.id.tagContent);
            bVar.g = (ProgressBar) inflate.findViewById(R.id.video_progress);
            bVar.h = (VideoPlayerView) inflate.findViewById(R.id.videoView);
            bVar.i = (ImageView) inflate.findViewById(R.id.play_video);
            bVar.h.setWidget(bVar.g, bVar.i, bVar.b);
            bVar.j.setOnClickListener(new ck(this));
            OverScrollView overScrollView = (OverScrollView) inflate.findViewById(R.id.scrollview);
            bVar.k.trackOverScrollView(overScrollView);
            overScrollView.setOnLoadMoreListener(new cl(this));
            View findViewById = inflate.findViewById(R.id.content_container_item_bg);
            if (this.R <= 0) {
                View findViewById2 = inflate.findViewById(R.id.arrowContainer);
                findViewById2.getViewTreeObserver().addOnGlobalLayoutListener(new cm(this, findViewById2, findViewById));
            }
            findViewById.setMinimumHeight(this.R);
            this.o.add(inflate);
            a((Article) this.K.get(i2), bVar, i2);
        }
    }

    private void a(Article article, b bVar, int i) {
        a(article.tag, bVar.e);
        a(article.content, bVar.f);
        if (article.isVideoArticle()) {
            String str = article.absPicPath;
        } else {
            QsbkApp.absoluteUrlOfMediumContentImage(article.id, article.image);
        }
        if (article.isVideoArticle()) {
            bVar.b.setVisibility(8);
            bVar.c.setVisibility(0);
            bVar.i.setVisibility(0);
            bVar.h.setVisibility(0);
            bVar.h.reset();
            a(article, bVar, null, true, article.absPicWidth, article.absPicHeight);
        } else if (article.isImageArticle()) {
            bVar.i.setVisibility(8);
            bVar.b.setVisibility(0);
            bVar.c.setVisibility(8);
            a(article, bVar.b, bVar.a, article.absPicWidth, article.absPicHeight);
        } else {
            bVar.b.setVisibility(8);
            bVar.c.setVisibility(8);
        }
    }

    private void a(Article article, b bVar, Object obj, boolean z, int i, int i2) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int dip2px = displayMetrics.widthPixels - UIHelper.dip2px(this, 32.0f);
        int i3 = displayMetrics.widthPixels;
        int[] iArr = new int[]{i, i2};
        LayoutParams layoutParams = bVar.image.getLayoutParams();
        int[] iArr2 = new int[2];
        ImageSizeHelper.calWidthAndHeight(dip2px, i3, iArr2, new ImageSize(iArr[0], iArr[1]), true);
        if (layoutParams != null) {
            layoutParams.width = dip2px;
            layoutParams.height = iArr2[1];
        } else {
            layoutParams = new LayoutParams(dip2px, iArr2[1]);
        }
        bVar.image.setLayoutParams(layoutParams);
        FrescoImageloader.displayImage(bVar.image, article.absPicPath, TileBackground.getBackgroud(this, BgImageType.ARTICLE), TileBackground.getBackgroud(this, BgImageType.ARTICLE), new IterativeBoxBlurPostProcessor(32));
        layoutParams = (RelativeLayout.LayoutParams) bVar.d.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = iArr2[0];
            layoutParams.height = iArr2[1];
        } else {
            layoutParams = new RelativeLayout.LayoutParams(iArr2[0], iArr2[1]);
        }
        layoutParams.addRule(13);
        bVar.d.setLayoutParams(layoutParams);
        bVar.h.setWidget(null, bVar.i, bVar.image);
        bVar.h.setAspectRatio(article.absPicWidth, article.absPicHeight);
        FrescoImageloader.displayImage(bVar.d, article.absPicPath, TileBackground.getBackgroud(this, BgImageType.ARTICLE));
        bVar.h.setVideo(article.getVideoUrl());
    }

    private synchronized void b(int i) {
        if (b) {
            Log.e(a, String.format("nextArticle invoked. current index %1s , articles size: %2s, from %3s, preview %4s, curview  %5s ", new Object[]{Integer.valueOf(this.J), Integer.valueOf(this.K.size()), Integer.valueOf(i), this.F, this.E}));
        }
        if (this.G != null) {
            Log.e(a, "cancel play video...");
            this.G.h.setVisibility(8);
            this.G.h.reset();
            this.G.b.setVisibility(0);
        }
        if (this.K.isEmpty()) {
            this.n.removeAllViews();
            a(null);
        } else if (this.J == this.K.size()) {
            this.J = 0;
            this.K.clear();
            if (b) {
                Log.e(a, "清空原来的articles：" + this.K.size());
            }
            if (this.E != null) {
                switch (i) {
                    case 1:
                        this.E.clearAnimation();
                        this.E.startAnimation(this.s);
                        a(null);
                        break;
                    case 2:
                        this.E.clearAnimation();
                        this.E.startAnimation(this.s);
                        a(null);
                        break;
                    case 3:
                        this.E.clearAnimation();
                        this.t.setDuration(this.t.getDuration() + 1);
                        a(this.E, 3);
                        break;
                    case 4:
                        this.E.clearAnimation();
                        this.u.setDuration(this.u.getDuration() + 1);
                        a(this.E, 4);
                        break;
                    default:
                        break;
                }
            }
        } else {
            c(i);
        }
    }

    private synchronized void c(int i) {
        Object obj = 1;
        synchronized (this) {
            if (this.o.size() != 0) {
                Animation animation;
                this.F = this.E;
                this.E = (View) this.o.get(0);
                boolean remove = this.o.remove(this.E);
                if (b) {
                    Log.e(a, "doShowNextArticle ： from：" + i + "，preView：" + this.F + "，curView：" + this.E + "，curView parent： " + this.E.getParent() + "，remove：" + remove + "，currentIndex：" + this.J + "，text：" + ((TextView) this.E.findViewById(R.id.content_txt)).getText());
                }
                View view = this.F;
                switch (i) {
                    case -1:
                        animation = null;
                        break;
                    case 0:
                        animation = null;
                        break;
                    case 1:
                        animation = this.s;
                        break;
                    case 2:
                        animation = this.s;
                        break;
                    case 3:
                        if (a(view, i)) {
                            obj = null;
                        }
                        animation = null;
                        break;
                    case 4:
                        Object obj2;
                        if (a(view, i)) {
                            obj2 = null;
                        } else {
                            obj2 = 1;
                        }
                        obj = obj2;
                        animation = null;
                        break;
                    default:
                        animation = null;
                        break;
                }
                if (obj != null) {
                    a(view, animation);
                }
            }
        }
    }

    private boolean a(View view, int i) {
        if (view != null) {
            View findViewById;
            Animation animation;
            if (i == 4) {
                findViewById = view.findViewById(R.id.review_seal_no_pass);
                animation = this.u;
            } else if (i == 3) {
                findViewById = view.findViewById(R.id.review_seal_pass);
                animation = this.t;
            } else {
                animation = null;
                findViewById = null;
            }
            if (!(findViewById == null || animation == null)) {
                findViewById.setVisibility(0);
                findViewById.clearAnimation();
                findViewById.startAnimation(animation);
                this.U.postDelayed(new cn(this, view), 50);
                return true;
            }
        }
        return false;
    }

    private synchronized void a(View view, Animation animation) {
        if (view == null) {
            view = this.F;
        }
        if (b) {
            Object obj;
            String str = a;
            StringBuilder append = new StringBuilder().append("removeViews and show ...").append(this.J).append(",curView: ").append(this.E).append(",curView image: ").append(this.E.findViewById(R.id.content_img)).append(",curView parent: ").append(this.E.getParent()).append(",preView: ").append(this.F).append(",child :").append(view).append(",child visibility : ");
            if (view == null) {
                obj = "";
            } else {
                obj = Integer.valueOf(view.getVisibility());
            }
            Log.e(str, append.append(obj).append(",container view count : ").append(this.n.getChildCount()).toString());
        }
        if (!(view == null || animation == null || view.getVisibility() == 8)) {
            view.clearAnimation();
            view.startAnimation(animation);
        }
        this.n.removeAllViews();
        if (this.E.getParent() != null) {
            ((ViewGroup) this.E.getParent()).removeView(this.E);
        }
        this.n.addView(this.E);
        this.E.startAnimation(this.r);
        if (this.J == 2) {
            a("preLoad");
        }
        l();
        this.J++;
    }

    private void a(Article article, QiushiImageLayout qiushiImageLayout, ProgressBar progressBar, int i, int i2) {
        if (article.imageInfos != null && article.imageInfos.size() > 0) {
            qiushiImageLayout.setImages(article.imageInfos);
        } else if (TextUtils.isEmpty(article.image)) {
            qiushiImageLayout.setVisibility(8);
            if (progressBar != null) {
                progressBar.setVisibility(8);
            }
        } else {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int i3 = displayMetrics.widthPixels - 20;
            int i4 = displayMetrics.heightPixels * 2;
            int[] iArr = new int[]{i, i2};
            int i5 = iArr[0];
            int i6 = iArr[1];
            ImageSizeHelper.calWidthAndHeight(i5, i4, iArr, new ImageSize(iArr[0], iArr[1]));
            ImageInfo imageInfo = new ImageInfo(article.getImageUrl(), i3, i6);
            List arrayList = new ArrayList();
            arrayList.add(imageInfo);
            qiushiImageLayout.setImages(arrayList);
        }
        qiushiImageLayout.setOnChildClickListener(new co(this, article, qiushiImageLayout));
    }

    private void a(String str, TextView textView) {
        if (str == null || str.trim().length() == 0 || "null".equals(str.trim())) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        textView.setText(str.trim());
    }

    private boolean a(JSONObject jSONObject) throws JSONException {
        Bundle bundle = new Bundle();
        bundle.putString(ActionBarLoginActivity.TOAST_WHEN_CREATED, "即将跳转至登录...");
        if (HttpClient.testNeedLogin(jSONObject, this, 108, bundle)) {
            return false;
        }
        String string = jSONObject.getString(NotificationCompat.CATEGORY_ERROR);
        String optString = jSONObject.optString("err_msg");
        if (string.startsWith("7")) {
            a(12, optString);
            return false;
        } else if (!string.startsWith(com.tencent.connect.common.Constants.VIA_SHARE_TYPE_PUBLISHVIDEO)) {
            return true;
        } else {
            a(11, optString);
            return false;
        }
    }

    private synchronized void a(String str) {
        boolean equalsIgnoreCase = "preLoad".equalsIgnoreCase(str);
        if (this.O == null || this.O.size() <= 0 || equalsIgnoreCase) {
            Task cpVar = new cp(this, equalsIgnoreCase);
            if (!equalsIgnoreCase) {
                m();
            }
            TaskExecutor.getInstance().addTask(cpVar);
            if (this.G != null) {
                this.G.h.stop();
            }
        } else {
            this.K.clear();
            this.K.addAll(this.O);
            this.O.clear();
            n();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i != 108) {
            super.onActivityResult(i, i2, intent);
        } else if (i2 != -1) {
            ToastAndDialog.makePositiveToast(this, "已取消登录").show();
            finish();
        } else {
            b(0);
        }
    }

    private void m() {
        this.H.set(true);
        a(0, null);
    }

    private void a(String str, String str2) {
        a(12, str2);
    }

    private void n() {
        this.H.set(false);
        a(1, null);
    }

    private void b(String str) {
        this.g = true;
        a(-1, str);
    }

    private void a(int i, String str) {
        if (this.U != null) {
            Message message = new Message();
            message.what = i;
            message.obj = str;
            this.U.sendMessage(message);
        }
    }

    private void q() {
        this.g = false;
        this.j.stop();
        this.j.setLevel(0);
        this.i.setText(Html.fromHtml("加载失败，请稍后<font color=#000000>点我</font>重试..."));
        this.A.setClickable(true);
    }

    public void finish() {
        super.finish();
        if (this.o != null) {
            synchronized (this.o) {
                for (View a : this.o) {
                    a(a);
                }
                this.o.clear();
            }
        }
        if (this.E != null) {
            a(this.E);
        }
        if (this.F != null) {
            a(this.F);
        }
        List<View> all = this.e.getAll();
        if (all != null && !all.isEmpty()) {
            synchronized (all) {
                for (View a2 : all) {
                    a(a2);
                }
            }
        }
    }

    private void a(View view) {
        if (view != null) {
            View findViewById = view.findViewById(R.id.scrollview);
            if (findViewById != null) {
                findViewById.setOnTouchListener(null);
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    private void d(int i) {
        int i2 = this.J > 0 ? this.J - 1 : 0;
        if (!this.K.isEmpty() && this.K.size() > i2) {
            TaskExecutor.getInstance().addTask(new cq(this, (Article) this.K.get(i2), i));
            switch (i) {
                case -100:
                    b(4);
                    return;
                case 1:
                    b(3);
                    return;
                default:
                    return;
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loading_content:
                if (!this.g) {
                    a("retry");
                    view.setClickable(false);
                    return;
                }
                return;
            case R.id.bottom_left_container:
                this.U.postDelayed(this.X, 200);
                return;
            case R.id.bottom_right_container:
                this.U.postDelayed(this.W, 200);
                return;
            default:
                return;
        }
    }

    private void b(String str, String str2) {
        StatService.onEvent(this, "AUDIT_REPORT", "ok");
        new ct(this, str, str2).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{Constants.REPORT});
        ToastAndDialog.makePositiveToast(this, "感谢您的举报，我们会尽快处理。", Integer.valueOf(0)).show();
        this.U.postDelayed(this.f, 950);
    }

    private void a(Article article) {
        List arrayList = new ArrayList(ReportUtils.RESOURCE.size());
        for (ReportBean reportBean : ReportUtils.RESOURCE) {
            arrayList.add(new Pair(reportBean.getName(), Integer.valueOf(reportBean.getValue())));
        }
        StatService.onEvent(this, "AUDIT_REPORT", "click");
        a(arrayList, (Context) this, article.id);
    }

    private void a(List<Pair<String, Integer>> list, Context context, String str) {
        CharSequence[] charSequenceArr = new String[list.size()];
        for (int i = 0; i < charSequenceArr.length; i++) {
            charSequenceArr[i] = (String) ((Pair) list.get(i)).first;
        }
        this.T = -1;
        Builder negativeButton = new Builder(context).setSingleChoiceItems(charSequenceArr, -1, new cv(this)).setNegativeButton("取消", new cu(this));
        negativeButton.setCancelable(true);
        AlertDialog create = negativeButton.create();
        create.setButton(-1, "确定", (DialogInterface.OnClickListener) null);
        create.show();
        create.getButton(-1).setOnClickListener(new cw(this, create, str, list, context));
    }

    protected void onStop() {
        super.onStop();
        if (this.G != null) {
            this.G.h.reset();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onAnimationStart(Animation animation) {
    }

    private void a(Animation animation) {
        String str = "动画结束！！！！";
        String str2 = animation + " , " + animation.getDuration();
        if (animation == this.u) {
            Log.e(a, str + "盖章不通过的动画，" + str2);
        } else if (animation == this.t) {
            Log.e(a, str + "盖章通过的动画，" + str2);
        } else if (animation == this.s) {
            Log.e(a, str + "帖子通过，从上上上上上上上上上上上上上上上上面出去，" + str2);
        }
    }

    public void onAnimationEnd(Animation animation) {
        if (b) {
            a(animation);
        }
        long duration;
        if (animation == this.t) {
            duration = this.t.getDuration();
            if (duration % 2 != 0) {
                this.t.setDuration(duration - 1);
                this.U.postDelayed(this.V, 200);
                return;
            }
            this.U.postDelayed(this.P, 200);
        } else if (animation == this.u) {
            duration = this.u.getDuration();
            if (duration % 2 != 0) {
                this.u.setDuration(duration - 1);
                this.U.postDelayed(this.V, 200);
                return;
            }
            this.U.postDelayed(this.Q, 200);
        } else if (animation == this.s) {
            duration = this.s.getDuration();
            if (duration % 2 != 0) {
                this.s.setDuration(duration - 1);
            }
        } else if (animation == this.r) {
            this.D = true;
        } else if (animation != this.w && animation == this.v) {
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    private void r() {
        this.r = new AnimationSet(true);
        this.r.setDuration(400);
        this.r.setAnimationListener(this);
        Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        Animation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        this.r.addAnimation(alphaAnimation);
        this.r.addAnimation(translateAnimation);
        this.s = new AnimationSet(true);
        this.s.setDuration(400);
        this.s.setAnimationListener(this);
        this.t = new AnimationSet(true);
        this.t.setDuration(166);
        this.u = new AnimationSet(true);
        this.u.setDuration(166);
        this.v = new ScaleAnimation(1.0f, 0.98f, 1.0f, 0.98f, 1, 0.5f, 1, 0.5f);
        this.v.setDuration(166);
        this.v.setAnimationListener(this);
        this.w = new ScaleAnimation(1.0f, 0.98f, 1.0f, 0.98f, 1, 0.5f, 1, 0.5f);
        this.w.setDuration(166);
        this.u.setAnimationListener(this);
        alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        Animation alphaAnimation2 = new AlphaAnimation(0.08f, 1.0f);
        translateAnimation = new ScaleAnimation(1.5f, 1.0f, 1.5f, 1.0f, 1, 0.5f, 1, 0.5f);
        this.t.addAnimation(alphaAnimation2);
        this.t.addAnimation(translateAnimation);
        this.t.setAnimationListener(this);
        this.u.addAnimation(alphaAnimation2);
        this.u.addAnimation(translateAnimation);
        this.u.setAnimationListener(this);
        new TranslateAnimation(1, 0.0f, 1, -1.0f, 1, 0.0f, 1, 0.0f).setDuration(500);
        this.s.addAnimation(new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -1.0f));
        this.s.addAnimation(alphaAnimation);
    }

    protected String c() {
        return c[0];
    }

    protected int a() {
        return R.layout.activity_audit_native;
    }
}
