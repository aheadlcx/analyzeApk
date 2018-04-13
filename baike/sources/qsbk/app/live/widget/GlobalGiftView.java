package qsbk.app.live.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.text.Html;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveGlobalGiftMessage;
import qsbk.app.live.ui.helper.LiveMessageListener;

public class GlobalGiftView extends FrameLayout {
    private String a;
    private ArrayList<LiveGlobalGiftMessage> b;
    private LiveMessageListener c;
    private int d;
    private int e;
    private float[] f;
    private long g;
    private long h;
    private Runnable i;

    public class MarqueeItem extends RelativeLayout {
        final /* synthetic */ GlobalGiftView a;
        private ImageView b;
        private TextView c;
        private TextPaint d;
        private LiveGlobalGiftMessage e;
        public long startTime;

        public MarqueeItem(GlobalGiftView globalGiftView, Context context) {
            this(globalGiftView, context, null);
        }

        public MarqueeItem(GlobalGiftView globalGiftView, Context context, AttributeSet attributeSet) {
            this(globalGiftView, context, attributeSet, 0);
        }

        public MarqueeItem(GlobalGiftView globalGiftView, Context context, AttributeSet attributeSet, int i) {
            this.a = globalGiftView;
            super(context, attributeSet, i);
            a(context);
        }

        protected void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
        }

        private void a(Context context) {
            View inflate = View.inflate(context, R.layout.live_global_gift_view, this);
            this.b = (ImageView) inflate.findViewById(R.id.live_icon);
            this.c = (TextView) inflate.findViewById(R.id.live_content);
            this.d = this.c.getPaint();
            inflate.setOnClickListener(new ej(this));
        }

        public void startMarquee(LiveGlobalGiftMessage liveGlobalGiftMessage) {
            this.e = liveGlobalGiftMessage;
            if (TextUtils.isEmpty(liveGlobalGiftMessage.getHtml())) {
                a();
                return;
            }
            String str = "<pre>　</pre>";
            this.c.setText(Html.fromHtml(str + liveGlobalGiftMessage.getHtml() + str));
            ((SimpleDraweeView) this.b).setAspectRatio((float) liveGlobalGiftMessage.getRatio());
            AppUtils.getInstance().getImageProvider().loadGift(this.b, liveGlobalGiftMessage.getGiftIcon(), false);
            Drawable gradientDrawable = new GradientDrawable(Orientation.LEFT_RIGHT, liveGlobalGiftMessage.getBackgroundGradientColors());
            gradientDrawable.setCornerRadii(this.a.f);
            gradientDrawable.setGradientType(0);
            this.c.setBackgroundDrawable(gradientDrawable);
            int measureText = (int) this.d.measureText(this.c.getText().toString());
            getLayoutParams().width = measureText + ((int) (((float) this.a.d) * ((float) liveGlobalGiftMessage.getRatio())));
            post(new ek(this));
        }

        public void startMarqueeAnim() {
            ViewGroup viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                int measuredWidth = viewGroup.getMeasuredWidth();
                int i = -getWidth();
                long abs = (long) (Math.abs((measuredWidth - i) * 1000) / this.a.e);
                Animation translateAnimation = new TranslateAnimation((float) measuredWidth, (float) i, 0.0f, 0.0f);
                translateAnimation.setDuration(abs);
                translateAnimation.setInterpolator(new LinearInterpolator());
                translateAnimation.setAnimationListener(new el(this));
                startAnimation(translateAnimation);
            }
        }

        private void a() {
            post(new em(this));
        }
    }

    public GlobalGiftView(Context context) {
        this(context, null);
    }

    public GlobalGiftView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GlobalGiftView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = GlobalGiftView.class.getSimpleName();
        this.b = new ArrayList();
        this.i = new ei(this);
        a();
    }

    private void a() {
        int screenWidth = WindowUtils.getScreenWidth();
        this.d = WindowUtils.dp2Px(20);
        int i = this.d / 2;
        this.f = new float[]{(float) i, (float) i, 0.0f, 0.0f, 0.0f, 0.0f, (float) i, (float) i};
        this.e = screenWidth / 4;
        this.g = (long) ((this.d * 1000) / this.e);
        this.g = a(this.g);
    }

    private long a(long j) {
        if (j < 100) {
            return 100;
        }
        return j;
    }

    public void addGlobalGift(LiveGlobalGiftMessage liveGlobalGiftMessage) {
        a(liveGlobalGiftMessage);
        b();
    }

    private void a(LiveGlobalGiftMessage liveGlobalGiftMessage) {
        if (liveGlobalGiftMessage != null) {
            this.b.add(liveGlobalGiftMessage);
        }
    }

    private void b() {
        if (this.b.size() > 0) {
            postDelayed(this.i, this.g);
        }
    }

    public MarqueeItem addMarqueeItem() {
        View marqueeItem = new MarqueeItem(this, getContext());
        marqueeItem.setVisibility(4);
        addView(marqueeItem, new LayoutParams(-2, -2));
        return marqueeItem;
    }

    public void releaseResource() {
        this.c = null;
        this.b.clear();
        removeCallbacks(this.i);
        removeAllViews();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    public void setLiveMessageListener(LiveMessageListener liveMessageListener) {
        this.c = liveMessageListener;
    }
}
