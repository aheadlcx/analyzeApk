package qsbk.app.live.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
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
import com.facebook.drawee.view.SimpleDraweeView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.model.LevelData;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.GiftResSync;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveEnterMessage;
import qsbk.app.live.ui.helper.LiveMessageListener;
import qsbk.app.live.utils.FontUtils;

public class FamilyCreatorEnterView extends FrameLayout {
    private FrameLayout a;
    private TextView b;
    private TextView c;
    private TextView d;
    private ImageView e;
    private RelativeLayout f;
    private SimpleDraweeView g;
    private SimpleDraweeView h;
    private SimpleDraweeView i;
    private SimpleDraweeView j;
    private SimpleDraweeView k;
    private FrameLayout l;
    private TextView m;
    private LiveEnterMessage n;
    private LiveMessageListener o;
    private Map<String, Drawable> p;
    private int q;
    private int r;
    private int s;

    public FamilyCreatorEnterView(Context context) {
        this(context, null);
    }

    public FamilyCreatorEnterView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FamilyCreatorEnterView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.p = new HashMap();
        a();
    }

    private void a() {
        this.s = WindowUtils.dp2Px(30);
        View inflate = View.inflate(getContext(), R.layout.live_family_creator_enter_view, this);
        this.a = (FrameLayout) inflate.findViewById(R.id.container);
        this.b = (TextView) inflate.findViewById(R.id.live_level);
        this.c = (TextView) inflate.findViewById(R.id.live_name);
        this.d = (TextView) inflate.findViewById(R.id.live_content);
        this.e = (ImageView) inflate.findViewById(R.id.live_enter_highlight);
        this.f = (RelativeLayout) inflate.findViewById(R.id.rl_high_level);
        this.g = (SimpleDraweeView) inflate.findViewById(R.id.sd_high_level1);
        this.h = (SimpleDraweeView) inflate.findViewById(R.id.sd_high_level2);
        this.i = (SimpleDraweeView) inflate.findViewById(R.id.sd_high_level3);
        this.j = (SimpleDraweeView) inflate.findViewById(R.id.iv_live_left_anim);
        this.k = (SimpleDraweeView) inflate.findViewById(R.id.iv_live_right_anim);
        this.l = (FrameLayout) inflate.findViewById(R.id.fl_user_info);
        this.m = (TextView) inflate.findViewById(R.id.live_barrage_bg);
        setVisibility(4);
        this.a.setOnClickListener(new y(this));
    }

    private LevelData a(LiveEnterMessage liveEnterMessage) {
        LevelData enterConfigByLevel = ConfigInfoUtil.instance().getEnterConfigByLevel(liveEnterMessage.getActivityLevel());
        if (enterConfigByLevel == null) {
            return ConfigInfoUtil.instance().getEnterConfigByLevel(liveEnterMessage.getUserLevel());
        }
        return enterConfigByLevel;
    }

    public void setViewContentAndStartAnim(LiveEnterMessage liveEnterMessage) {
        LevelData a = a(liveEnterMessage);
        if (a != null) {
            this.n = liveEnterMessage;
            this.b.setTypeface(FontUtils.getBloggerSansFontBold());
            this.b.setText("Lv" + liveEnterMessage.getUserLevel());
            this.c.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + liveEnterMessage.getUserName() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            this.d.setText(a.d);
            this.m.setText(liveEnterMessage.getFamilyLeaderBadge());
            this.m.setVisibility(TextUtils.isEmpty(liveEnterMessage.getFamilyLeaderBadge()) ? 8 : 0);
            this.b.setTextColor(Color.parseColor(a.c));
            this.c.setTextColor(Color.parseColor(a.c));
            this.d.setTextColor(Color.parseColor(a.c));
            postDelayed(new z(this, a), 25);
        }
    }

    public void startEnterAnim(LevelData levelData) {
        int width = getWidth();
        int width2 = this.a.getWidth();
        Animation animationSet = new AnimationSet(false);
        animationSet.setDuration(333);
        animationSet.addAnimation(new AlphaAnimation(0.0f, 1.0f));
        animationSet.addAnimation(new TranslateAnimation((float) width, (float) this.q, 0.0f, 0.0f));
        Animation translateAnimation = new TranslateAnimation((float) this.q, (float) this.q, 0.0f, 0.0f);
        translateAnimation.setInterpolator(new LinearInterpolator());
        translateAnimation.setDuration(3000);
        AnimationSet animationSet2 = new AnimationSet(false);
        animationSet2.setDuration(333);
        animationSet2.addAnimation(new AlphaAnimation(1.0f, 0.0f));
        animationSet2.addAnimation(new TranslateAnimation((float) this.q, (float) (-width2), 0.0f, 0.0f));
        animationSet.setAnimationListener(new aa(this, levelData, translateAnimation));
        translateAnimation.setAnimationListener(new ab(this, levelData, animationSet2));
        animationSet2.setAnimationListener(new ae(this));
        startAnimation(animationSet);
    }

    private void setLevelBackground(LevelData levelData) {
        String downloadedGiftResPath = GiftResSync.getDownloadedGiftResPath(levelData.l);
        if (!this.p.containsKey(downloadedGiftResPath)) {
            this.p.put(downloadedGiftResPath, (BitmapDrawable) Drawable.createFromPath(downloadedGiftResPath));
        }
        String downloadedGiftResPath2 = GiftResSync.getDownloadedGiftResPath(levelData.m);
        if (!this.p.containsKey(downloadedGiftResPath2)) {
            this.p.put(downloadedGiftResPath2, (BitmapDrawable) Drawable.createFromPath(downloadedGiftResPath2));
        }
        String downloadedGiftResPath3 = GiftResSync.getDownloadedGiftResPath(levelData.r);
        if (!this.p.containsKey(downloadedGiftResPath3)) {
            this.p.put(downloadedGiftResPath3, (BitmapDrawable) Drawable.createFromPath(downloadedGiftResPath3));
        }
        if (downloadedGiftResPath != null && downloadedGiftResPath2 != null && downloadedGiftResPath3 != null && this.p.get(downloadedGiftResPath) != null && this.p.get(downloadedGiftResPath2) != null && this.p.get(downloadedGiftResPath3) != null) {
            this.g.setBackgroundDrawable((Drawable) this.p.get(downloadedGiftResPath));
            this.h.setBackgroundDrawable((Drawable) this.p.get(downloadedGiftResPath2));
            this.i.setBackgroundDrawable((Drawable) this.p.get(downloadedGiftResPath3));
        }
    }

    public void releaseResource() {
        this.o = null;
        removeAllViews();
    }
}
