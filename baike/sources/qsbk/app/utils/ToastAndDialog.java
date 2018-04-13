package qsbk.app.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import qsbk.app.R;
import qsbk.app.core.widget.toast.AbstractToast;
import qsbk.app.core.widget.toast.ToastManager;
import qsbk.app.guide.dialog.LoginGuideDialog;

public class ToastAndDialog {
    public static AbstractToast makeText(Context context, String str) {
        return ToastManager.makeText(context, str, 1);
    }

    public static AbstractToast makeText(Context context, int i) {
        return ToastManager.makeText(context, context.getResources().getString(i), 1);
    }

    public static AbstractToast makePositiveToast(Context context, String str, Integer num) {
        AbstractToast makeText = ToastManager.makeText(context, str, a(str));
        makeText.setGravity(17, 0, 0);
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        TextView textView = (TextView) inflate.findViewById(R.id.toast_textview);
        textView.setPadding((int) (Util.density * 5.0f), (int) (35.0f * Util.density), (int) (Util.density * 5.0f), 0);
        textView.setCompoundDrawablesWithIntrinsicBounds(null, context.getResources().getDrawable(R.drawable.toast_ok), null, null);
        textView.setEllipsize(TruncateAt.END);
        textView.setMaxLines(2);
        textView.setCompoundDrawablePadding((int) (7.0f * Util.density));
        textView.setText(str);
        makeText.setView(inflate);
        return makeText;
    }

    public static AbstractToast makePositiveToast(Context context, String str) {
        return makePositiveToast(context, str, Integer.valueOf(1));
    }

    public static AbstractToast makeNegativeToast(Context context, String str, Integer num) {
        AbstractToast makeText = ToastManager.makeText(context, str, a(str));
        makeText.setGravity(17, 0, 0);
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        TextView textView = (TextView) inflate.findViewById(R.id.toast_textview);
        textView.setPadding((int) (Util.density * 5.0f), (int) (35.0f * Util.density), (int) (Util.density * 5.0f), 0);
        textView.setEllipsize(TruncateAt.END);
        textView.setMaxLines(2);
        textView.setCompoundDrawablesWithIntrinsicBounds(null, context.getResources().getDrawable(R.drawable.toast_sad), null, null);
        textView.setCompoundDrawablePadding((int) (7.0f * Util.density));
        textView.setText(str);
        makeText.setView(inflate);
        return makeText;
    }

    public static AbstractToast makeNegativeToast(Context context, String str) {
        return makeNegativeToast(context, str, Integer.valueOf(1));
    }

    public static AbstractToast makeNeutralToast(Context context, String str, Integer num) {
        AbstractToast makeText = ToastManager.makeText(context, str, a(str));
        makeText.setGravity(17, 0, 0);
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        TextView textView = (TextView) inflate.findViewById(R.id.toast_textview);
        textView.setEllipsize(TruncateAt.END);
        textView.setMaxLines(5);
        textView.setMaxWidth((int) (Util.density * 120.0f));
        textView.setMinWidth((int) (Util.density * 120.0f));
        textView.setText(str);
        textView.setGravity(17);
        makeText.setView(inflate);
        return makeText;
    }

    public static AbstractToast makeNeutralToast(Context context, String str) {
        return makeNeutralToast(context, str, Integer.valueOf(1));
    }

    private static final int a(String str) {
        return (!TextUtils.isEmpty(str) && str.length() > 12) ? 1 : 0;
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
        animationSet.setAnimationListener(new ToastAndDialog$a(view, textView));
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
        animationSet.setAnimationListener(new ToastAndDialog$a(view, textView));
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
        loadAnimation.setInterpolator(new ToastAndDialog$Lofter());
        if (z) {
            loadAnimation.setAnimationListener(new ToastAndDialog$b(view));
        }
        view.startAnimation(loadAnimation);
    }

    @Deprecated
    public static void showLoginGuideDialog(Context context, int i) {
        DebugUtil.debug("luolong", "showLoginGuideDialog");
        LoginGuideDialog loginGuideDialog = new LoginGuideDialog(context, R.style.login_guide_dialog, i);
        Window window = loginGuideDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        window.setGravity(17);
        window.setAttributes(attributes);
        loginGuideDialog.setCanceledOnTouchOutside(true);
        loginGuideDialog.show();
    }
}
