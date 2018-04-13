package qsbk.app.live.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveGlobalRedEnvelopesMessage;
import qsbk.app.live.ui.helper.LiveMessageListener;

public class GlobalBarrageLayout extends LinearLayout {
    private String a;
    private int b;
    private ArrayList<LiveGlobalRedEnvelopesMessage> c;
    private LiveMessageListener d;
    private int e;
    private int f;
    private int g;
    private int h;
    private long i;
    private Runnable j;

    public class BarrageItem extends FrameLayout {
        final /* synthetic */ GlobalBarrageLayout a;
        private TextView b;
        private LiveGlobalRedEnvelopesMessage c;
        public long startTime;

        public BarrageItem(GlobalBarrageLayout globalBarrageLayout, Context context, LiveGlobalRedEnvelopesMessage liveGlobalRedEnvelopesMessage) {
            this.a = globalBarrageLayout;
            super(context);
            this.c = liveGlobalRedEnvelopesMessage;
            a(context);
        }

        private void a(Context context) {
            this.a.setGravity(80);
            View inflate = View.inflate(context, R.layout.live_global_barrage_anim_layout, this);
            this.b = (TextView) inflate.findViewById(R.id.live_content);
            inflate.findViewById(R.id.btn_enter_live).setOnClickListener(new ee(this));
        }

        public void startMarquee() {
            this.b.setText(AppUtils.getInstance().getAppContext().getString(R.string.live_global_red_envelopes_content, new Object[]{this.c.getUserName()}));
            post(new ef(this));
        }

        public void startMarqueeAnim() {
            ViewGroup viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                long measuredWidth = (long) (((viewGroup.getMeasuredWidth() - (-getWidth())) * 1000) / this.a.f);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, View.TRANSLATION_X, new float[]{(float) r1, (float) r2});
                ofFloat.setDuration(measuredWidth);
                ofFloat.setInterpolator(new LinearInterpolator());
                ofFloat.addListener(new eg(this, viewGroup));
                ofFloat.start();
            }
        }
    }

    public class BarrageLine extends FrameLayout {
        final /* synthetic */ GlobalBarrageLayout a;

        public BarrageLine(GlobalBarrageLayout globalBarrageLayout, Context context) {
            this(globalBarrageLayout, context, null);
        }

        public BarrageLine(GlobalBarrageLayout globalBarrageLayout, Context context, AttributeSet attributeSet) {
            this(globalBarrageLayout, context, attributeSet, 0);
        }

        public BarrageLine(GlobalBarrageLayout globalBarrageLayout, Context context, AttributeSet attributeSet, int i) {
            this.a = globalBarrageLayout;
            super(context, attributeSet, i);
            a();
        }

        private void a() {
            this.a.setGravity(80);
            this.a.setGravity(16);
        }
    }

    public GlobalBarrageLayout(Context context) {
        this(context, null);
    }

    public GlobalBarrageLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GlobalBarrageLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = GlobalBarrageLayout.class.getSimpleName();
        this.b = 2;
        this.c = new ArrayList();
        this.j = new ed(this);
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

    public void addBarrage(LiveGlobalRedEnvelopesMessage liveGlobalRedEnvelopesMessage) {
        a(liveGlobalRedEnvelopesMessage);
        b();
    }

    private void a(LiveGlobalRedEnvelopesMessage liveGlobalRedEnvelopesMessage) {
        if (liveGlobalRedEnvelopesMessage != null) {
            this.c.add(liveGlobalRedEnvelopesMessage);
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

    public BarrageItem addBarrageItem(ViewGroup viewGroup, LiveGlobalRedEnvelopesMessage liveGlobalRedEnvelopesMessage) {
        View barrageItem = new BarrageItem(this, getContext(), liveGlobalRedEnvelopesMessage);
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
