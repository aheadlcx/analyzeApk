package qsbk.app.live.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.core.model.BarrageDecorData;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveBarrageMessage;
import qsbk.app.live.ui.helper.LiveMessageListener;

public class BarrageLayout extends LinearLayout {
    private String a;
    private int b;
    private ArrayList<LiveBarrageMessage> c;
    private LiveMessageListener d;
    private int e;
    private int f;
    private int g;
    private int h;
    private long i;
    private Runnable j;

    public class BarrageItem extends FrameLayout {
        final /* synthetic */ BarrageLayout a;
        private LinearLayout b;
        private ImageView c;
        private ImageView d;
        private ImageView e;
        private TextView f;
        private TextPaint g;
        private TextView h;
        private TextPaint i;
        private FamilyLevelView j;
        private LiveBarrageMessage k;
        public long startTime;

        public BarrageItem(BarrageLayout barrageLayout, Context context, LiveBarrageMessage liveBarrageMessage) {
            this.a = barrageLayout;
            super(context);
            this.k = liveBarrageMessage;
            a(context);
        }

        private void a(Context context) {
            View inflate;
            this.a.setGravity(80);
            if (this.k.getBarrageDecorData() != null) {
                inflate = View.inflate(context, R.layout.live_barrage_anim_special, this);
                this.b = (LinearLayout) inflate.findViewById(R.id.live_barrage_bg);
                this.d = (ImageView) inflate.findViewById(R.id.live_barrage_decor);
                this.e = (ImageView) inflate.findViewById(R.id.live_barrage_webp);
            } else {
                inflate = View.inflate(context, R.layout.live_barrage_anim_layout, this);
            }
            this.c = (ImageView) inflate.findViewById(R.id.live_gift_avatar);
            this.f = (TextView) inflate.findViewById(R.id.live_username);
            this.h = (TextView) inflate.findViewById(R.id.live_content);
            this.j = (FamilyLevelView) inflate.findViewById(R.id.fl_level);
            this.g = this.f.getPaint();
            this.i = this.h.getPaint();
            this.c.setOnClickListener(new e(this));
            if (this.k.getMessageFamilyLevel() > 9) {
                this.h.setBackgroundResource(FamilyLevelView.getFamilyLevelDrawableResource(this.k.getMessageFamilyLevel()));
            }
        }

        public void startMarquee() {
            AppUtils.getInstance().getImageProvider().loadAvatar(this.c, this.k.getUserAvatar());
            BarrageDecorData barrageDecorData = this.k.getBarrageDecorData();
            if (barrageDecorData != null) {
                ((GradientDrawable) this.b.getBackground()).setColor(Color.parseColor(barrageDecorData.c));
                AppUtils.getInstance().getImageProvider().loadGift(this.d, barrageDecorData.u, false);
                AppUtils.getInstance().getImageProvider().loadWebpImage(this.e, "res:///" + R.drawable.live_barrage_heart);
                this.f.setTextColor(Color.parseColor(barrageDecorData.cu));
                this.h.setTextColor(Color.parseColor(barrageDecorData.cd));
            }
            this.f.setText(this.k.getUserName());
            this.h.setText(this.k.getContent());
            if (TextUtils.isEmpty(this.k.getMessageBadge())) {
                this.j.setVisibility(8);
            } else {
                this.j.setVisibility(0);
                this.j.setLevelAndName(this.k.getMessageFamilyLevel(), this.k.getMessageBadge());
            }
            post(new f(this));
        }

        public void startMarqueeAnim() {
            ViewGroup viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                long measuredWidth = (long) (((viewGroup.getMeasuredWidth() - (-getWidth())) * 1000) / this.a.f);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, View.TRANSLATION_X, new float[]{(float) r1, (float) r2});
                ofFloat.setDuration(measuredWidth);
                ofFloat.setInterpolator(new LinearInterpolator());
                ofFloat.addListener(new g(this, viewGroup));
                ofFloat.start();
            }
        }
    }

    public class BarrageLine extends FrameLayout {
        final /* synthetic */ BarrageLayout a;

        public BarrageLine(BarrageLayout barrageLayout, Context context) {
            this(barrageLayout, context, null);
        }

        public BarrageLine(BarrageLayout barrageLayout, Context context, AttributeSet attributeSet) {
            this(barrageLayout, context, attributeSet, 0);
        }

        public BarrageLine(BarrageLayout barrageLayout, Context context, AttributeSet attributeSet, int i) {
            this.a = barrageLayout;
            super(context, attributeSet, i);
            a();
        }

        private void a() {
            this.a.setGravity(80);
            this.a.setGravity(16);
        }
    }

    public BarrageLayout(Context context) {
        this(context, null);
    }

    public BarrageLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BarrageLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = BarrageLayout.class.getSimpleName();
        this.b = 2;
        this.c = new ArrayList();
        this.j = new d(this);
        a();
    }

    private void a() {
        int i;
        setOrientation(1);
        setGravity(80);
        this.g = WindowUtils.dp2Px(85);
        this.h = WindowUtils.dp2Px(72);
        for (i = 0; i < this.b; i++) {
            addBarrageLine(i);
        }
        i = WindowUtils.getScreenWidth();
        this.e = WindowUtils.dp2Px(20);
        this.f = i / 4;
        this.i = (long) ((this.e * 1000) / this.f);
        this.i = a(this.i);
    }

    private long a(long j) {
        if (j < 100) {
            return 100;
        }
        return j;
    }

    public void addBarrage(LiveBarrageMessage liveBarrageMessage) {
        a(liveBarrageMessage);
        b();
    }

    private void a(LiveBarrageMessage liveBarrageMessage) {
        if (liveBarrageMessage != null) {
            this.c.add(liveBarrageMessage);
        }
    }

    private void b() {
        if (this.c.size() > 0) {
            postDelayed(this.j, this.i);
        }
    }

    private BarrageLine getAvailableAnimLine() {
        BarrageLine barrageLine;
        int childCount = getChildCount();
        BarrageLine barrageLine2 = null;
        BarrageLine barrageLine3 = null;
        for (int i = 0; i < childCount; i++) {
            barrageLine3 = (BarrageLine) getChildAt((childCount - i) - 1);
            int childCount2 = barrageLine3.getChildCount();
            if (childCount2 == 0) {
                barrageLine = barrageLine3;
                break;
            }
            if (childCount2 <= 3) {
                if (childCount == 1) {
                    barrageLine = barrageLine3;
                    break;
                } else if (i + 1 < childCount) {
                    barrageLine2 = (BarrageLine) getChildAt((childCount - i) - 2);
                    if (barrageLine2.getChildCount() == 0 || childCount2 > barrageLine2.getChildCount()) {
                        barrageLine = barrageLine3;
                        barrageLine3 = barrageLine2;
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        BarrageLine barrageLine4 = barrageLine3;
        barrageLine3 = null;
        barrageLine = barrageLine4;
        if (barrageLine == null || barrageLine.getChildCount() >= 3 || r1 == null || r1.getChildCount() >= 3 || barrageLine.getChildCount() != r1.getChildCount()) {
            return barrageLine3;
        }
        return (BarrageLine) getChildAt(childCount - 1);
    }

    public BarrageLine addBarrageLine(int i) {
        View barrageLine = new BarrageLine(this, getContext());
        barrageLine.setId(i);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
        layoutParams.weight = 1.0f;
        addView(barrageLine, layoutParams);
        return barrageLine;
    }

    public void removeBarrageLine(int i) {
        int childCount = getChildCount();
        if (i >= 0 && childCount > 0 && i < childCount) {
            removeViewAt(i);
            getLayoutParams().height -= getLayoutParams().height / childCount;
        }
    }

    public BarrageItem addBarrageItem(ViewGroup viewGroup, LiveBarrageMessage liveBarrageMessage) {
        View barrageItem = new BarrageItem(this, getContext(), liveBarrageMessage);
        viewGroup.addView(barrageItem, new LinearLayout.LayoutParams(-2, -2));
        barrageItem.setVisibility(4);
        return barrageItem;
    }

    public void setLiveMessageListener(LiveMessageListener liveMessageListener) {
        this.d = liveMessageListener;
    }

    public void releaseResource() {
        this.d = null;
        this.c.clear();
        removeCallbacks(this.j);
        removeAllViews();
    }
}
