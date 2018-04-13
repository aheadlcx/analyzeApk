package qsbk.app.core.widget.parallax;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.util.ArrayList;
import qsbk.app.core.R;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBottom$OnRefreshListener;

public class ParallaxRecyclerView extends RecyclerView {
    private static final String I = ParallaxRecyclerView.class.getSimpleName();
    public static final double NO_ZOOM = 1.0d;
    private ArrayList<OnTouchEventListener> J = new ArrayList();
    private ImageView K;
    private int L = -1;
    private int M = -1;
    private double N = 1.0d;
    private float O;
    private SwipeRefreshLayoutBottom$OnRefreshListener P;
    private OnTouchEventListener Q = new a(this);

    public class BackAnimimation extends Animation {
        int a;
        int b;
        int c = (this.a - this.b);
        View d;
        boolean e;
        final /* synthetic */ ParallaxRecyclerView f;

        protected BackAnimimation(ParallaxRecyclerView parallaxRecyclerView, View view, int i, boolean z) {
            this.f = parallaxRecyclerView;
            this.d = view;
            this.a = i;
            this.e = z;
            this.b = view.getHeight();
        }

        protected void applyTransformation(float f, Transformation transformation) {
            this.d.getLayoutParams().height = (int) (((float) this.a) - (((float) this.c) * (1.0f - f)));
            this.d.requestLayout();
        }

        public void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
        }
    }

    public interface OnTouchEventListener {
        boolean onTouchEvent(MotionEvent motionEvent);
    }

    public ParallaxRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    public ParallaxRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public ParallaxRecyclerView(Context context) {
        super(context);
        a(context, null);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            this.N = (double) context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ParallaxRecyclerView, 0, 0).getFloat(R.styleable.ParallaxRecyclerView_zoomRatio, 1.0f);
        }
        this.O = getResources().getDisplayMetrics().density * 48.0f;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        for (int i = 0; i < this.J.size(); i++) {
            ((OnTouchEventListener) this.J.get(i)).onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setParallaxImageView(ImageView imageView) {
        this.K = imageView;
        this.K.setScaleType(ScaleType.CENTER_CROP);
    }

    public void setOnRefreshListener(SwipeRefreshLayoutBottom$OnRefreshListener swipeRefreshLayoutBottom$OnRefreshListener) {
        this.P = swipeRefreshLayoutBottom$OnRefreshListener;
    }

    public void setImageViewToParallax(ImageView imageView) {
        imageView.setScaleType(ScaleType.CENTER_CROP);
        this.K = imageView;
        a(this.Q);
    }

    private void a(OnTouchEventListener onTouchEventListener) {
        this.J.add(onTouchEventListener);
    }

    public boolean hasSetParallaxImageView() {
        return this.K != null;
    }

    public void setViewsBounds(double d) {
        if (this.M == -1) {
            this.M = this.K.getHeight();
            double intrinsicHeight = ((double) this.K.getDrawable().getIntrinsicHeight()) / (((double) this.K.getDrawable().getIntrinsicWidth()) / ((double) this.K.getWidth()));
            if (d <= 1.0d) {
                d = 1.0d;
            }
            this.L = (int) (intrinsicHeight * d);
        }
    }
}
