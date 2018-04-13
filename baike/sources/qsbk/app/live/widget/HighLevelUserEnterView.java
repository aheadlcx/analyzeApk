package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.baidu.mobstat.Config;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.model.LevelData;
import qsbk.app.core.model.MarketData;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.GiftResSync;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.core.widget.FrameAnimationView;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveEnterMessage;
import qsbk.app.live.ui.helper.LiveMessageListener;
import qsbk.app.live.utils.FontUtils;

public class HighLevelUserEnterView extends FrameLayout {
    private Context A;
    private FrameLayout B;
    private FrameLayout C;
    private Map<String, Drawable> D;
    private Runnable E;
    private String a;
    private ArrayList<LiveEnterMessage> b;
    private FrameLayout c;
    private TextView d;
    private TextView e;
    private TextView f;
    private ImageView g;
    private RelativeLayout h;
    private SimpleDraweeView i;
    public boolean isAvailable;
    private SimpleDraweeView j;
    private SimpleDraweeView k;
    private SimpleDraweeView l;
    private SimpleDraweeView m;
    private FrameLayout n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private FrameAnimationView t;
    private ViewFlipper u;
    private LiveEnterMessage v;
    private int w;
    private int x;
    private int y;
    private LiveMessageListener z;

    public HighLevelUserEnterView(Context context) {
        this(context, null);
    }

    public HighLevelUserEnterView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HighLevelUserEnterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = HighLevelUserEnterView.class.getSimpleName();
        this.b = new ArrayList();
        this.isAvailable = true;
        this.w = 0;
        this.D = new HashMap();
        this.E = new fh(this);
        this.A = context;
        a();
    }

    private void a() {
        this.x = WindowUtils.dp2Px(-15);
        this.y = WindowUtils.dp2Px(30);
        View inflate = View.inflate(getContext(), R.layout.live_high_level_user_enter_view, this);
        this.c = (FrameLayout) inflate.findViewById(R.id.container);
        this.d = (TextView) inflate.findViewById(R.id.live_level);
        this.e = (TextView) inflate.findViewById(R.id.live_name);
        this.f = (TextView) inflate.findViewById(R.id.live_content);
        this.g = (ImageView) inflate.findViewById(R.id.live_enter_highlight);
        this.h = (RelativeLayout) inflate.findViewById(R.id.rl_high_level);
        this.i = (SimpleDraweeView) inflate.findViewById(R.id.sd_high_level1);
        this.j = (SimpleDraweeView) inflate.findViewById(R.id.sd_high_level2);
        this.k = (SimpleDraweeView) inflate.findViewById(R.id.sd_high_level3);
        this.l = (SimpleDraweeView) inflate.findViewById(R.id.iv_live_left_anim);
        this.m = (SimpleDraweeView) inflate.findViewById(R.id.iv_live_right_anim);
        this.n = (FrameLayout) inflate.findViewById(R.id.fl_user_info);
        this.o = (TextView) inflate.findViewById(R.id.live_barrage_bg);
        this.p = (TextView) inflate.findViewById(R.id.live_level_v2);
        this.q = (TextView) inflate.findViewById(R.id.live_name_v2);
        this.r = (TextView) inflate.findViewById(R.id.live_content_v2);
        this.s = (TextView) inflate.findViewById(R.id.tv_family);
        this.t = (FrameAnimationView) inflate.findViewById(R.id.iv_frame_anim);
        this.u = (ViewFlipper) inflate.findViewById(R.id.vf_desc);
        this.B = (FrameLayout) inflate.findViewById(R.id.fl_enter);
        this.C = (FrameLayout) inflate.findViewById(R.id.fl_enter_v2);
        this.c.setOnClickListener(new ff(this));
    }

    public void addEnterMessage(LiveEnterMessage liveEnterMessage) {
        a(liveEnterMessage);
        b();
    }

    private void a(LiveEnterMessage liveEnterMessage) {
        this.b.add(liveEnterMessage);
    }

    private void b() {
        if (this.b.size() > 0 && this.isAvailable) {
            post(this.E);
        }
    }

    public void setViewContentAndStartAnim(LiveEnterMessage liveEnterMessage) {
        if ((this.A instanceof BaseActivity) && ((BaseActivity) this.A).isOnResume) {
            LevelData c = c(liveEnterMessage);
            if (c != null) {
                this.isAvailable = false;
                boolean z = liveEnterMessage.getUser() != null && liveEnterMessage.getUser().showFamilyEnterEffect();
                if (z) {
                    if (this.z != null) {
                        this.z.onFamilyEnterEffect(liveEnterMessage);
                        postDelayed(new fi(this), Config.BPLUS_DELAY_TIME);
                        return;
                    }
                    return;
                } else if (b(liveEnterMessage)) {
                    b(liveEnterMessage, c);
                    return;
                } else {
                    a(liveEnterMessage, c);
                    return;
                }
            }
            b();
        }
    }

    private boolean b(LiveEnterMessage liveEnterMessage) {
        String enterScopeByLevel = ConfigInfoUtil.instance().getEnterScopeByLevel(liveEnterMessage.getUserLevel());
        Map marketDataMap = ConfigInfoUtil.instance().getMarketDataMap();
        if (enterScopeByLevel == null && !marketDataMap.containsKey(Long.valueOf((long) liveEnterMessage.getEnterAnimId()))) {
            return false;
        }
        File file = new File(Environment.getExternalStorageDirectory() + "/Remix/.Scene/" + enterScopeByLevel);
        File file2 = new File(Environment.getExternalStorageDirectory() + "/Remix/.Market/" + liveEnterMessage.getEnterAnimId());
        if (file.exists() || file2.exists()) {
            return true;
        }
        return false;
    }

    private void a(LiveEnterMessage liveEnterMessage, LevelData levelData) {
        this.C.setVisibility(4);
        this.B.setVisibility(4);
        this.v = liveEnterMessage;
        this.d.setTypeface(FontUtils.getBloggerSansFontBold());
        this.d.setText("Lv" + liveEnterMessage.getUserLevel());
        this.e.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + liveEnterMessage.getUserName() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        this.f.setText(levelData.d);
        this.o.setText(liveEnterMessage.getMessageBadge());
        this.o.setVisibility(TextUtils.isEmpty(liveEnterMessage.getMessageBadge()) ? 8 : 0);
        this.d.setTextColor(Color.parseColor(levelData.c));
        this.e.setTextColor(Color.parseColor(levelData.c));
        this.f.setTextColor(Color.parseColor(levelData.c));
        setLevelBackground(levelData);
        postDelayed(new fj(this, levelData), 25);
    }

    private void b(LiveEnterMessage liveEnterMessage, LevelData levelData) {
        this.B.setVisibility(4);
        this.C.setVisibility(0);
        this.v = liveEnterMessage;
        this.p.setTypeface(FontUtils.getBloggerSansFontBoldItalic());
        this.p.setText("Lv" + liveEnterMessage.getUserLevel());
        this.q.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + liveEnterMessage.getUserName() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        this.r.setText(levelData.d);
        this.s.setText(getContext().getString(R.string.family_enter, new Object[]{liveEnterMessage.getMessageBadge()}));
        if (liveEnterMessage.isSmallFrameAnim()) {
            this.t.setFramesInSdCard(Environment.getExternalStorageDirectory() + "/Remix/.Market/" + liveEnterMessage.getEnterAnimId());
        } else {
            this.t.setFramesInSdCard(Environment.getExternalStorageDirectory() + "/Remix/.Scene/" + ConfigInfoUtil.instance().getEnterScopeByLevel(liveEnterMessage.getUserLevel()));
        }
        this.t.setAnimationListener(new fk(this));
        this.t.play();
        if (TextUtils.isEmpty(liveEnterMessage.getMessageBadge())) {
            this.u.setDisplayedChild(1);
        } else {
            this.u.setDisplayedChild(0);
            this.u.setFlipInterval(1200);
        }
        postDelayed(new fl(this, liveEnterMessage), 1000);
    }

    public void startEnterAnim(LiveEnterMessage liveEnterMessage) {
        ObjectAnimator.ofFloat(this.u, ALPHA, new float[]{0.0f, 1.0f}).setDuration(220);
        ObjectAnimator.ofFloat(this.u, ALPHA, new float[]{1.0f, 1.0f}).setDuration(4000);
        if (!TextUtils.isEmpty(liveEnterMessage.getMessageBadge())) {
            this.u.startFlipping();
            postDelayed(new fm(this), 1300);
        }
        ObjectAnimator.ofFloat(this.u, ALPHA, new float[]{1.0f, 0.0f}).setDuration(150);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(new Animator[]{r0, r1, r2});
        animatorSet.start();
    }

    public void startEnterAnim(LevelData levelData) {
        int width = getWidth();
        int width2 = this.c.getWidth();
        Animation animationSet = new AnimationSet(false);
        animationSet.setDuration(333);
        animationSet.addAnimation(new AlphaAnimation(0.0f, 1.0f));
        animationSet.addAnimation(new TranslateAnimation((float) width, (float) this.x, 0.0f, 0.0f));
        Animation translateAnimation = new TranslateAnimation((float) this.x, (float) this.x, 0.0f, 0.0f);
        translateAnimation.setInterpolator(new LinearInterpolator());
        translateAnimation.setDuration(3000);
        AnimationSet animationSet2 = new AnimationSet(false);
        animationSet2.setDuration(333);
        animationSet2.addAnimation(new AlphaAnimation(1.0f, 0.0f));
        animationSet2.addAnimation(new TranslateAnimation((float) this.x, (float) (-width2), 0.0f, 0.0f));
        animationSet.setAnimationListener(new fn(this, translateAnimation));
        translateAnimation.setAnimationListener(new fo(this, levelData, animationSet2));
        animationSet2.setAnimationListener(new fg(this));
        startAnimation(animationSet);
    }

    public void setLiveMessageListener(LiveMessageListener liveMessageListener) {
        this.z = liveMessageListener;
    }

    public void terminateAllAnim() {
        this.b.clear();
        removeCallbacks(this.E);
    }

    public void releaseResource() {
        this.z = null;
        terminateAllAnim();
        removeAllViews();
    }

    private LevelData c(LiveEnterMessage liveEnterMessage) {
        if (liveEnterMessage.isSmallFrameAnim()) {
            LevelData levelData = new LevelData();
            MarketData marketDatById = ConfigInfoUtil.instance().getMarketDatById(liveEnterMessage.getEnterAnimId());
            levelData.d = marketDatById != null ? marketDatById.w : "";
            return levelData;
        }
        LevelData enterConfigByLevel = ConfigInfoUtil.instance().getEnterConfigByLevel(liveEnterMessage.getActivityLevel());
        if (enterConfigByLevel == null) {
            return ConfigInfoUtil.instance().getEnterConfigByLevel(liveEnterMessage.getUserLevel());
        }
        return enterConfigByLevel;
    }

    private void setLevelBackground(LevelData levelData) {
        String downloadedGiftResPath = GiftResSync.getDownloadedGiftResPath(levelData.l);
        if (!this.D.containsKey(downloadedGiftResPath)) {
            this.D.put(downloadedGiftResPath, (BitmapDrawable) Drawable.createFromPath(downloadedGiftResPath));
        }
        String downloadedGiftResPath2 = GiftResSync.getDownloadedGiftResPath(levelData.m);
        if (!this.D.containsKey(downloadedGiftResPath2)) {
            this.D.put(downloadedGiftResPath2, (BitmapDrawable) Drawable.createFromPath(downloadedGiftResPath2));
        }
        String downloadedGiftResPath3 = GiftResSync.getDownloadedGiftResPath(levelData.r);
        if (!this.D.containsKey(downloadedGiftResPath3)) {
            this.D.put(downloadedGiftResPath3, (BitmapDrawable) Drawable.createFromPath(downloadedGiftResPath3));
        }
        if (downloadedGiftResPath != null && downloadedGiftResPath2 != null && downloadedGiftResPath3 != null && this.D.get(downloadedGiftResPath) != null && this.D.get(downloadedGiftResPath2) != null && this.D.get(downloadedGiftResPath3) != null) {
            this.i.setBackgroundDrawable((Drawable) this.D.get(downloadedGiftResPath));
            this.j.setBackgroundDrawable((Drawable) this.D.get(downloadedGiftResPath2));
            this.k.setBackgroundDrawable((Drawable) this.D.get(downloadedGiftResPath3));
        }
    }
}
