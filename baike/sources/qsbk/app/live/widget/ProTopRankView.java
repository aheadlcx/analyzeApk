package qsbk.app.live.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.model.LiveProTopMessage;
import qsbk.app.live.ui.helper.LiveMessageListener;

public class ProTopRankView extends FrameLayout {
    private FrameLayout a;
    private TextView b;
    private SimpleDraweeView c;
    private ImageView d;
    private boolean e;
    private LiveMessage f;
    private ArrayList<LiveMessage> g;
    private int h;
    private int i;
    private int j;
    private LiveMessageListener k;
    private Context l;
    private Runnable m;

    public ProTopRankView(Context context) {
        this(context, null, 0);
    }

    public ProTopRankView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ProTopRankView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = true;
        this.g = new ArrayList();
        this.j = 0;
        this.m = new hr(this);
        this.l = context;
        a();
    }

    private void a() {
        this.h = WindowUtils.dp2Px(8);
        this.i = WindowUtils.dp2Px(30);
        View inflate = View.inflate(getContext(), R.layout.live_pro_top_rank_view, this);
        this.a = (FrameLayout) inflate.findViewById(R.id.fl_bg);
        this.b = (TextView) inflate.findViewById(R.id.tv_content);
        this.c = (SimpleDraweeView) inflate.findViewById(R.id.sd_avatar);
        this.d = (ImageView) inflate.findViewById(R.id.live_enter_highlight);
        setVisibility(8);
        this.c.setOnClickListener(new hq(this));
    }

    public void addProMessage(LiveMessage liveMessage) {
        a(liveMessage);
        b();
    }

    private void a(LiveMessage liveMessage) {
        this.g.add(liveMessage);
    }

    private void b() {
        if (this.g.size() > 0 && this.e) {
            post(this.m);
        }
    }

    private void setViewContentAndStartAnim(LiveMessage liveMessage) {
        if ((this.l instanceof BaseActivity) && ((BaseActivity) this.l).isOnResume) {
            b(liveMessage);
            postDelayed(new hs(this), 25);
        }
    }

    private void b(LiveMessage liveMessage) {
        this.e = false;
        this.f = liveMessage;
        AppUtils.getInstance().getImageProvider().loadAvatar(this.c, liveMessage.getUserAvatar(), true);
        if (liveMessage instanceof LiveProTopMessage) {
            this.b.setText(liveMessage.getUserName() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + ((LiveProTopMessage) liveMessage).getProTopContent());
            switch (((LiveProTopMessage) liveMessage).getTopNumber()) {
                case 1:
                    this.a.setBackgroundResource(R.drawable.live_pro_top_1);
                    this.b.setTextColor(Color.parseColor("#FFBE6F15"));
                    return;
                case 2:
                    this.a.setBackgroundResource(R.drawable.live_pro_top_2);
                    this.b.setTextColor(Color.parseColor("#FF868686"));
                    return;
                case 3:
                    this.a.setBackgroundResource(R.drawable.live_pro_top_3);
                    this.b.setTextColor(Color.parseColor("#FFFFFFFF"));
                    return;
                default:
                    this.a.setBackgroundResource(R.drawable.live_pro_top_1);
                    this.b.setTextColor(Color.parseColor("#FFBE6F15"));
                    return;
            }
        }
        this.b.setText(liveMessage.getUserName());
        this.a.setBackgroundResource(R.drawable.live_pro_top_1);
        this.b.setTextColor(Color.parseColor("#FFBE6F15"));
    }

    private void c() {
        int width = getWidth();
        int width2 = this.a.getWidth();
        Animation animationSet = new AnimationSet(false);
        animationSet.setDuration(333);
        animationSet.addAnimation(new AlphaAnimation(0.0f, 1.0f));
        animationSet.addAnimation(new TranslateAnimation((float) width, (float) this.h, 0.0f, 0.0f));
        Animation translateAnimation = new TranslateAnimation((float) this.h, (float) this.h, 0.0f, 0.0f);
        translateAnimation.setInterpolator(new LinearInterpolator());
        translateAnimation.setDuration(3000);
        AnimationSet animationSet2 = new AnimationSet(false);
        animationSet2.setDuration(333);
        animationSet2.addAnimation(new AlphaAnimation(1.0f, 0.0f));
        animationSet2.addAnimation(new TranslateAnimation((float) this.h, (float) (-width2), 0.0f, 0.0f));
        animationSet.setAnimationListener(new ht(this, translateAnimation));
        translateAnimation.setAnimationListener(new hu(this, animationSet2));
        animationSet2.setAnimationListener(new hx(this));
        startAnimation(animationSet);
    }

    public void setLiveMessageListener(LiveMessageListener liveMessageListener) {
        this.k = liveMessageListener;
    }

    public void terminateAllAnim() {
        this.g.clear();
        removeCallbacks(this.m);
    }

    public void releaseResource() {
        this.k = null;
        terminateAllAnim();
        removeAllViews();
    }
}
