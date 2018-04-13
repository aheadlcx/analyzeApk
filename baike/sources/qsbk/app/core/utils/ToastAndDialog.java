package qsbk.app.core.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import qsbk.app.core.R;

public class ToastAndDialog {

    public static final class Lofter implements Interpolator {
        double[] a;

        public Lofter() {
            double log = Math.log(0.1d / ((double) Math.abs(1.0f))) / 300.0d;
            if (log > 0.0d) {
                log *= -1.0d;
            }
            this.a = new double[300];
            for (int i = 0; i < 300; i++) {
                this.a[i] = 1.0d + (Math.cos(0.03141592653589793d * ((double) i)) * (Math.pow(2.71d, ((double) i) * log) * -1.0d));
            }
        }

        public final float getInterpolation(float f) {
            float f2 = (float) this.a[this.a.length - 1];
            int floor = (int) Math.floor((double) (((float) this.a.length) * f));
            if (floor < this.a.length) {
                return (float) this.a[floor];
            }
            return f2;
        }
    }

    static class a implements AnimationListener {
        private View a;
        private TextView b;

        public a(View view, TextView textView) {
            this.a = view;
            this.b = textView;
        }

        public void onAnimationEnd(Animation animation) {
            this.a.setClickable(true);
            ((ViewGroup) this.b.getParent()).removeView(this.b);
            this.b.setVisibility(8);
            this.b = null;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    private static class b implements AnimationListener {
        private final View a;

        public b(View view) {
            this.a = view;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            if (this.a != null) {
                this.a.setClickable(true);
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public static Toast makeText(Context context, String str, Integer num) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.my_toast, null);
        Toast toast = new Toast(context);
        toast.setDuration(num.intValue());
        toast.setView(inflate);
        ((TextView) inflate.findViewById(R.id.TextViewInfo)).setText(str);
        return toast;
    }

    public static Toast makeText(Context context, String str) {
        return makeText(context, str, Integer.valueOf(1));
    }

    public static Toast makeText(Context context, int i) {
        return makeText(context, context.getResources().getString(i), Integer.valueOf(1));
    }

    public static void makeText(Context context, View view, String str, int i, int i2) {
        view.setClickable(false);
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i3 = iArr[0];
        int i4 = iArr[1];
        int measuredHeight = view.getMeasuredHeight();
        int measuredWidth = view.getMeasuredWidth();
        View textView = new TextView(context);
        textView.setText(str);
        textView.setTextColor(i2);
        textView.setTextSize(22.0f);
        textView.setTypeface(Typeface.DEFAULT_BOLD, 1);
        textView.setClickable(false);
        textView.measure(0, 0);
        int measuredWidth2 = textView.getMeasuredWidth();
        int measuredHeight2 = textView.getMeasuredHeight();
        ((Activity) context).addContentView(textView, new LayoutParams(-2, -2));
        float f = (float) ((i4 - ((measuredHeight * 3) / 2)) - measuredHeight2);
        float f2 = (float) ((i3 + (measuredWidth / 2)) - (measuredWidth2 / 2));
        Animation translateAnimation = new TranslateAnimation(f2, f2, f, f - 20.0f);
        translateAnimation.setDuration((long) i);
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration((long) i);
        Animation animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setFillAfter(true);
        textView.startAnimation(animationSet);
        animationSet.setAnimationListener(new a(view, textView));
    }

    public static void makeTextVideoAdapter(View view, String str, int i, int i2) {
        view.setClickable(false);
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i3 = iArr[0];
        int i4 = iArr[1];
        int measuredHeight = view.getMeasuredHeight();
        int measuredWidth = view.getMeasuredWidth();
        View textView = new TextView(view.getContext());
        textView.setText(str);
        textView.setTextColor(i2);
        textView.setTextSize(22.0f);
        textView.setTypeface(Typeface.DEFAULT_BOLD, 1);
        textView.setClickable(false);
        textView.measure(0, 0);
        int measuredWidth2 = textView.getMeasuredWidth();
        int measuredHeight2 = textView.getMeasuredHeight();
        ((Activity) view.getContext()).addContentView(textView, new LayoutParams(-2, -2));
        float f = (float) (((i4 - (measuredHeight * 3)) - measuredHeight2) - 20);
        float f2 = (float) ((i3 + (measuredWidth / 6)) - (measuredWidth2 / 2));
        Animation translateAnimation = new TranslateAnimation(f2, f2, f, f - 20.0f);
        translateAnimation.setDuration((long) i);
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration((long) i);
        Animation animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setFillAfter(true);
        textView.startAnimation(animationSet);
        animationSet.setAnimationListener(new a(view, textView));
    }

    public static void tipsScroll(Toast toast, Context context, int i, int i2, int i3, int i4) {
        View imageView = new ImageView(context);
        imageView.setImageResource(i);
        toast.setView(imageView);
        toast.setGravity(i2, i3, i4);
        toast.setDuration(0);
        toast.show();
    }

    public static void scale(View view, boolean z) {
        Animation loadAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.support_icon_scale);
        loadAnimation.setInterpolator(new Lofter());
        if (z) {
            loadAnimation.setAnimationListener(new b(view));
        }
        view.startAnimation(loadAnimation);
    }
}
