package qsbk.app.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.model.AuditArticle;
import qsbk.app.model.ReportBean;
import qsbk.app.report.ReportUtils;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.audit.Rotate3dAnimation;
import qsbk.app.utils.audit.SimpleImageLoader;
import qsbk.app.utils.image.issue.TaskExecutor;
import qsbk.app.utils.image.issue.TaskExecutor.Task;
import qsbk.app.video.VideoPlayerView;
import qsbk.app.widget.MyScrollView;

@Deprecated
public class AuditNativeActivity extends BaseActionBarActivity implements OnClickListener, AnimationListener {
    private static final String a = AuditNativeActivity.class.getName();
    private static final boolean b = DebugUtil.DEBUG;
    private static final String[] c = new String[]{"审核新糗事", "审核新糗事"};
    private static final String d = Constants.AUDIT.substring(0, Constants.AUDIT.indexOf("?"));
    private Animation A;
    private Animation B;
    private View C;
    private final Runnable D = new bu(this);
    private View E;
    private View F;
    private View G;
    private View H;
    private View I;
    private ViewPager J;
    private RelativeLayout K;
    private boolean O = true;
    private View P = null;
    private View Q = null;
    private AuditNativeActivity$c R = null;
    private AtomicBoolean S = new AtomicBoolean(false);
    private int T;
    private int U;
    private int V;
    private int W;
    private int X;
    private String Y = "";
    private int Z = 0;
    private List<AuditArticle> aa;
    private List<AuditArticle> ab;
    private final Runnable ac = new bx(this);
    private final Runnable ad = new by(this);
    private Runnable ae = new bz(this);
    private Handler af = new ca(this, Looper.getMainLooper());
    private final Runnable ag = new cc(this);
    private final Runnable ah = new cd(this);
    private final Runnable ai = new ce(this);
    private final Runnable aj = new bk(this);
    private int ak = -1;
    private final AuditNativeActivity$b e = new AuditNativeActivity$b(this);
    private final Runnable f = new bj(this);
    private boolean g = false;
    private ImageView h;
    private TextView i;
    private AnimationDrawable j;
    private View k;
    private View l;
    private AtomicBoolean m = new AtomicBoolean(Boolean.TRUE.booleanValue());
    private FrameLayout n;
    private List<View> o;
    private SimpleImageLoader p = SimpleImageLoader.getInstance();
    private Drawable q;
    private Drawable r;
    private AnimationSet s;
    private AnimationSet t;
    private AnimationSet u;
    private AnimationSet v;
    private AnimationSet w;
    private AnimationSet x;
    private AnimationSet y;
    private AnimationSet z;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return c();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            getWindow().setFlags(8192, 8192);
        }
    }

    private void i() {
        this.I = findViewById(R.id.loading_content);
        this.h = (ImageView) findViewById(R.id.loading);
        this.i = (TextView) findViewById(R.id.loading_text);
        this.j = (AnimationDrawable) this.h.getDrawable();
        this.l = View.inflate(this, R.layout.layout_review_content, null);
        this.E = this.l.findViewById(R.id.bottom_left_container);
        this.F = this.l.findViewById(R.id.bottom_right_container);
        this.G = this.l.findViewById(R.id.report);
        this.H = this.l.findViewById(R.id.next);
        this.C = this.l.findViewById(R.id.bottom_layout);
        this.n = (FrameLayout) this.l.findViewById(R.id.scroll_view_container);
        this.k = View.inflate(this, R.layout.layout_review_rules, null);
        this.K = (RelativeLayout) findViewById(R.id.center_container);
        this.J = (ViewPager) findViewById(R.id.pager);
        List arrayList = new ArrayList(2);
        arrayList.add(this.k);
        arrayList.add(this.l);
        Object auditNativeActivity$a = new AuditNativeActivity$a(this, arrayList, this.J, null, null, this.K);
        this.J.setAdapter(auditNativeActivity$a);
        this.J.setOnPageChangeListener(auditNativeActivity$a);
    }

    private void j() {
        this.E.setOnClickListener(this);
        this.F.setOnClickListener(this);
        this.G.setOnClickListener(this);
        this.H.setOnClickListener(this);
        this.I.setOnClickListener(this);
        this.I.setClickable(false);
    }

    private void k() {
        if (this.J.getChildCount() == 0 || this.J.getCurrentItem() == 1) {
            this.J.setVisibility(8);
            this.K.setVisibility(8);
            this.I.setVisibility(0);
            this.i.setText("努力加载中...");
            this.h.postDelayed(new bl(this), 200);
            if (this.g) {
                this.h.postDelayed(new bm(this), 1700);
            }
        }
    }

    private void l() {
        if (b) {
            Log.e(a, "hideLoadingPage-->" + this.J.getChildCount() + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP + this.J.getCurrentItem() + "mReplacementOfViewPager childCount: " + this.K.getChildCount());
        }
        if (this.I.getVisibility() == 0) {
            if (this.J.getChildCount() == 0 || this.J.getCurrentItem() == 1) {
                this.J.setVisibility(8);
                this.K.setVisibility(0);
            } else {
                this.J.setVisibility(0);
                this.K.setVisibility(8);
            }
            this.I.setVisibility(8);
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
        this.aa = new ArrayList();
        this.ab = new ArrayList();
        this.q = null;
        this.r = null;
        i();
        j();
        this.af.postDelayed(new bn(this), 200);
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
            View inflate = from.inflate(R.layout.layout_review_content_item, null);
            AuditNativeActivity$c auditNativeActivity$c = new AuditNativeActivity$c(null);
            auditNativeActivity$c.a = (ProgressBar) inflate.findViewById(R.id.progressbar);
            auditNativeActivity$c.b = (ImageView) inflate.findViewById(R.id.content_img);
            auditNativeActivity$c.d = (TextView) inflate.findViewById(R.id.content_txt);
            auditNativeActivity$c.c = (TextView) inflate.findViewById(R.id.tagContent);
            auditNativeActivity$c.e = (ProgressBar) inflate.findViewById(R.id.video_progress);
            auditNativeActivity$c.f = (VideoPlayerView) inflate.findViewById(R.id.videoView);
            auditNativeActivity$c.g = (ImageView) inflate.findViewById(R.id.play_video);
            auditNativeActivity$c.f.setWidget(auditNativeActivity$c.e, auditNativeActivity$c.g, auditNativeActivity$c.b);
            ((MyScrollView) inflate.findViewById(R.id.scrollview)).setOnDerection(new bo(this));
            this.o.add(inflate);
            a((AuditArticle) this.aa.get(i2), auditNativeActivity$c, i2);
        }
    }

    private void a(AuditArticle auditArticle, AuditNativeActivity$c auditNativeActivity$c, int i) {
        String str;
        a(auditArticle.tag, auditNativeActivity$c.c);
        a(auditArticle.content, auditNativeActivity$c.d);
        if (auditArticle.isVideoArticle()) {
            str = auditArticle.absPicPath;
        } else {
            str = QsbkApp.absoluteUrlOfMediumContentImage(auditArticle.id, auditArticle.image);
        }
        if (auditArticle.isVideoArticle()) {
            auditNativeActivity$c.g.setVisibility(0);
            auditNativeActivity$c.f.setVisibility(0);
            auditNativeActivity$c.f.reset();
            auditNativeActivity$c.f.setVideo(auditArticle.getVideoUrl());
            auditNativeActivity$c.f.setAspectRatio(auditArticle.absPicWidth, auditArticle.absPicHeight);
            a(str, auditNativeActivity$c.b, null, true, auditArticle.absPicWidth, auditArticle.absPicHeight);
            return;
        }
        auditNativeActivity$c.g.setVisibility(8);
        a(str, auditNativeActivity$c.b, auditNativeActivity$c.a, false, 0, 0);
    }

    private synchronized void b(int i) {
        if (b) {
            Log.e(a, String.format("nextArticle invoked. current index %1s , articles size: %2s, from %3s, preview %4s, curview  %5s ", new Object[]{Integer.valueOf(this.Z), Integer.valueOf(this.aa.size()), Integer.valueOf(i), this.Q, this.P}));
        }
        if (this.R != null) {
            Log.e(a, "cancel play video...");
            this.R.f.setVisibility(8);
            this.R.f.reset();
            this.R.b.setVisibility(0);
        }
        if (this.aa.isEmpty()) {
            this.n.removeAllViews();
            a(null);
        } else if (this.Z == this.aa.size()) {
            this.Z = 0;
            this.aa.clear();
            if (b) {
                Log.e(a, "清空原来的articles：" + this.aa.size());
            }
            if (this.P != null) {
                switch (i) {
                    case 1:
                        this.P.clearAnimation();
                        this.P.startAnimation(this.v);
                        a(null);
                        break;
                    case 2:
                        this.P.clearAnimation();
                        this.P.startAnimation(this.v);
                        a(null);
                        break;
                    case 3:
                        this.P.clearAnimation();
                        this.y.setDuration(this.y.getDuration() + 1);
                        a(this.P, 3);
                        break;
                    case 4:
                        this.P.clearAnimation();
                        this.z.setDuration(this.z.getDuration() + 1);
                        a(this.P, 4);
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
                this.Q = this.P;
                this.P = (View) this.o.get(0);
                boolean remove = this.o.remove(this.P);
                if (b) {
                    Log.e(a, "doShowNextArticle ： from：" + i + "，preView：" + this.Q + "，curView：" + this.P + "，curView parent： " + this.P.getParent() + "，remove：" + remove + "，currentIndex：" + this.Z + "，text：" + ((TextView) this.P.findViewById(R.id.content_txt)).getText());
                }
                View view = this.Q;
                switch (i) {
                    case -1:
                        animation = null;
                        break;
                    case 0:
                        animation = null;
                        break;
                    case 1:
                        animation = this.v;
                        break;
                    case 2:
                        animation = this.v;
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
                animation = this.z;
            } else if (i == 3) {
                findViewById = view.findViewById(R.id.review_seal_pass);
                animation = this.y;
            } else {
                animation = null;
                findViewById = null;
            }
            if (!(findViewById == null || animation == null)) {
                findViewById.setVisibility(0);
                findViewById.clearAnimation();
                findViewById.startAnimation(animation);
                this.af.postDelayed(new bp(this, view), 50);
                return true;
            }
        }
        return false;
    }

    private synchronized void a(View view, Animation animation) {
        Object obj;
        if (animation == this.v) {
            r();
        }
        if (view == null) {
            view = this.Q;
        }
        if (b) {
            String str = a;
            StringBuilder append = new StringBuilder().append("removeViews and show ...").append(this.Z).append(",curView: ").append(this.P).append(",curView image: ").append(this.P.findViewById(R.id.content_img)).append(",curView parent: ").append(this.P.getParent()).append(",preView: ").append(this.Q).append(",child :").append(view).append(",child visibility : ");
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
        if (this.Q != null) {
            View findViewById = this.Q.findViewById(R.id.content_img);
            if (findViewById != null) {
                obj = findViewById.getTag();
                if (obj != null && (obj instanceof String)) {
                    this.p.cancel((String) obj);
                }
            }
            findViewById = this.e.add(this.Q);
            if (findViewById != null) {
                a(findViewById);
            }
        }
        if (this.P.getParent() != null) {
            ((ViewGroup) this.P.getParent()).removeView(this.P);
        }
        this.n.addView(this.P);
        this.P.startAnimation(this.x);
        if (this.Z == 2) {
            a("preLoad");
        }
        l();
        this.Z++;
    }

    private void a(String str, ImageView imageView, ProgressBar progressBar, boolean z, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            imageView.setVisibility(8);
            if (progressBar != null) {
                progressBar.setVisibility(8);
                return;
            }
            return;
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.p.loadImage(imageView, progressBar, str, this.q, this.r, displayMetrics.widthPixels - 20, displayMetrics.heightPixels * 2, null, z ? new int[]{i, i2} : null);
        imageView.setTag(str);
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
        if (this.ab == null || this.ab.size() <= 0 || equalsIgnoreCase) {
            Task bqVar = new bq(this, equalsIgnoreCase);
            if (!equalsIgnoreCase) {
                m();
            }
            TaskExecutor.getInstance().addTask(bqVar);
        } else {
            this.aa.clear();
            this.aa.addAll(this.ab);
            this.ab.clear();
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
        this.S.set(true);
        a(0, null);
    }

    private void a(String str, String str2) {
        a(12, str2);
    }

    private void n() {
        this.S.set(false);
        a(1, null);
    }

    private void b(String str) {
        this.g = true;
        a(-1, str);
    }

    private void a(int i, String str) {
        if (this.af != null) {
            Message message = new Message();
            message.what = i;
            message.obj = str;
            this.af.sendMessage(message);
        }
    }

    private void q() {
        this.g = false;
        this.j.stop();
        this.j.setLevel(0);
        this.i.setText(Html.fromHtml("加载失败，请稍后<font color=#000000>点我</font>重试..."));
        this.I.setClickable(true);
    }

    public void finish() {
        super.finish();
        if (this.p != null) {
            this.p.cancelAll();
        }
        if (this.o != null) {
            synchronized (this.o) {
                for (View a : this.o) {
                    a(a);
                }
                this.o.clear();
            }
        }
        if (this.P != null) {
            a(this.P);
        }
        if (this.Q != null) {
            a(this.Q);
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
            Drawable drawable = ((ImageView) view.findViewById(R.id.content_img)).getDrawable();
            if (!(drawable == null || drawable == this.q || drawable == this.r)) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                if (!(bitmap == null || bitmap.isRecycled())) {
                    bitmap.recycle();
                }
                drawable.setCallback(null);
            }
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
        TaskExecutor.getInstance().addTask(new br(this, (AuditArticle) this.aa.get(this.Z - 1), i));
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

    private void r() {
        this.E.setClickable(false);
        this.F.setClickable(false);
        this.G.setClickable(false);
        this.H.setClickable(false);
        this.C.clearAnimation();
        this.C.startAnimation(this.s);
        this.C.setVisibility(4);
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
            case R.id.next:
                s();
                return;
            case R.id.bottom_left_container:
                r();
                this.af.postDelayed(this.ai, 200);
                return;
            case R.id.bottom_right_container:
                r();
                this.af.postDelayed(this.aj, 200);
                return;
            case R.id.report:
                a((AuditArticle) this.aa.get(this.Z - 1));
                return;
            default:
                return;
        }
    }

    private void s() {
        if (!this.m.get()) {
            return;
        }
        if (this.K.getVisibility() == 0) {
            this.m.set(Boolean.FALSE.booleanValue());
            d(0);
            b(1);
            return;
        }
        this.J.setCurrentItem(1, true);
    }

    private void b(String str, String str2) {
        StatService.onEvent(this, "AUDIT_REPORT", "ok");
        new bs(this, str, str2).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Constants.REPORT);
        ToastAndDialog.makePositiveToast(this, "感谢您的举报，我们会尽快处理。", Integer.valueOf(0)).show();
        this.af.postDelayed(this.f, 950);
    }

    private void a(AuditArticle auditArticle) {
        List arrayList = new ArrayList(ReportUtils.RESOURCE.size());
        for (ReportBean reportBean : ReportUtils.RESOURCE) {
            arrayList.add(new Pair(reportBean.getName(), Integer.valueOf(reportBean.getValue())));
        }
        StatService.onEvent(this, "AUDIT_REPORT", "click");
        a(arrayList, (Context) this, auditArticle.id);
    }

    private void a(List<Pair<String, Integer>> list, Context context, String str) {
        CharSequence[] charSequenceArr = new String[list.size()];
        for (int i = 0; i < charSequenceArr.length; i++) {
            charSequenceArr[i] = (String) ((Pair) list.get(i)).first;
        }
        this.ak = -1;
        Builder negativeButton = new Builder(context).setSingleChoiceItems(charSequenceArr, -1, new bv(this)).setNegativeButton("取消", new bt(this));
        negativeButton.setCancelable(true);
        AlertDialog create = negativeButton.create();
        create.setButton(-1, "确定", (DialogInterface.OnClickListener) null);
        create.show();
        create.getButton(-1).setOnClickListener(new bw(this, create, str, list, context));
    }

    protected void onStop() {
        super.onStop();
        if (this.R != null) {
            this.R.f.reset();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onAnimationStart(Animation animation) {
        if (animation == this.x) {
            this.af.postDelayed(this.D, 400);
        }
        if (animation == this.s) {
        }
        if (animation != this.t) {
        }
    }

    private void a(Animation animation) {
        String str = "动画结束！！！！";
        String str2 = animation + " , " + animation.getDuration();
        if (animation == this.z) {
            Log.e(a, str + "盖章不通过的动画，" + str2);
        } else if (animation == this.y) {
            Log.e(a, str + "盖章通过的动画，" + str2);
        } else if (animation == this.w) {
            Log.e(a, str + "帖子不通过，从下下下下下下下下下下下下下下下下面出去，" + str2);
        } else if (animation == this.x) {
            Log.e(a, str + "新帖子进来，" + str2);
        } else if (animation == this.u) {
            Log.e(a, str + "帖子通过，从上上上上上上上上上上上上上上上上面出去，" + str2);
        } else if (animation == this.t) {
            Log.e(a, str + "底部操作栏进来~~~~~~~~~~~~~");
        } else if (animation == this.s) {
            Log.e(a, str + "底部操作栏出去~~~~~~~~~~~~~");
        }
    }

    public void onAnimationEnd(Animation animation) {
        if (b) {
            a(animation);
        }
        long duration;
        if (animation == this.y) {
            duration = this.y.getDuration();
            if (duration % 2 != 0) {
                this.y.setDuration(duration - 1);
                this.af.postDelayed(this.ag, 200);
                return;
            }
            this.af.postDelayed(this.ac, 200);
        } else if (animation == this.z) {
            duration = this.z.getDuration();
            if (duration % 2 != 0) {
                this.z.setDuration(duration - 1);
                this.af.postDelayed(this.ah, 200);
                return;
            }
            this.af.postDelayed(this.ad, 200);
        } else if (animation == this.w) {
            duration = this.w.getDuration();
            if (duration % 2 != 0) {
                this.w.setDuration(duration - 1);
            }
        } else if (animation == this.u) {
            duration = this.u.getDuration();
            if (duration % 2 != 0) {
                this.u.setDuration(duration - 1);
            }
        } else if (animation == this.x) {
            this.O = true;
        } else if (animation == this.t) {
            this.C.setVisibility(0);
            this.E.setClickable(true);
            this.F.setClickable(true);
            this.G.setClickable(true);
            this.H.setClickable(true);
            this.m.set(Boolean.TRUE.booleanValue());
        } else if (animation != this.B && animation == this.A) {
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    private void t() {
        this.t = new AnimationSet(true);
        this.t.setDuration(300);
        this.t.setAnimationListener(this);
        this.s = new AnimationSet(true);
        this.s.setDuration(300);
        this.s.setAnimationListener(this);
        this.w = new AnimationSet(true);
        this.w.setDuration(400);
        this.w.setAnimationListener(this);
        this.v = new AnimationSet(true);
        this.v.setDuration(400);
        this.x = new AnimationSet(true);
        this.x.setDuration(400);
        this.x.setAnimationListener(this);
        this.u = new AnimationSet(true);
        this.u.setDuration(400);
        this.u.setAnimationListener(this);
        this.y = new AnimationSet(true);
        this.y.setDuration(166);
        this.z = new AnimationSet(true);
        this.z.setDuration(166);
        this.A = new ScaleAnimation(1.0f, 0.98f, 1.0f, 0.98f, 1, 0.5f, 1, 0.5f);
        this.A.setDuration(166);
        this.A.setAnimationListener(this);
        this.B = new ScaleAnimation(1.0f, 0.98f, 1.0f, 0.98f, 1, 0.5f, 1, 0.5f);
        this.B.setDuration(166);
        this.z.setAnimationListener(this);
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        Animation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
        Animation alphaAnimation3 = new AlphaAnimation(0.08f, 1.0f);
        Animation scaleAnimation = new ScaleAnimation(1.5f, 1.0f, 1.5f, 1.0f, 1, 0.5f, 1, 0.5f);
        this.y.addAnimation(alphaAnimation3);
        this.y.addAnimation(scaleAnimation);
        this.y.setAnimationListener(this);
        this.z.addAnimation(alphaAnimation3);
        this.z.addAnimation(scaleAnimation);
        this.z.setAnimationListener(this);
        int i = getResources().getDisplayMetrics().widthPixels;
        new Rotate3dAnimation(0.0f, -24.0f, (float) (i / 2), (float) (this.U / 2), 310.0f, true, "y").setDuration(500);
        scaleAnimation = new TranslateAnimation(1, 0.0f, 1, -1.0f, 1, 0.0f, 1, 0.0f);
        scaleAnimation.setDuration(500);
        Animation alphaAnimation4 = new AlphaAnimation(0.9f, 0.0f);
        this.v.addAnimation(new ScaleAnimation(1.0f, 0.75f, 1.0f, 0.75f, 1, 0.5f, 1, 0.5f));
        this.v.addAnimation(alphaAnimation4);
        this.v.addAnimation(scaleAnimation);
        Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(-24.0f, 0.0f, (float) (i / 2), (float) (this.U / 2), 310.0f, false, "y");
        scaleAnimation = new TranslateAnimation(1, 0.8f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
        Animation alphaAnimation5 = new AlphaAnimation(0.1f, 1.0f);
        this.x.addAnimation(new ScaleAnimation(0.75f, 1.0f, 0.75f, 1.0f, 1, 0.5f, 1, 0.5f));
        this.x.addAnimation(alphaAnimation5);
        this.x.addAnimation(scaleAnimation);
        this.u.addAnimation(new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -1.0f));
        this.u.addAnimation(alphaAnimation);
        scaleAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        this.w.addAnimation(scaleAnimation);
        this.w.addAnimation(alphaAnimation);
        this.t.addAnimation(new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f));
        this.t.addAnimation(alphaAnimation2);
        scaleAnimation.setFillAfter(true);
        alphaAnimation.setFillAfter(true);
        this.s.addAnimation(scaleAnimation);
        this.s.addAnimation(alphaAnimation);
    }

    protected String c() {
        return c[0];
    }

    protected int a() {
        return R.layout.activity_audit_native;
    }
}
