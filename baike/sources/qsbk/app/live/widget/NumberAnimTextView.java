package qsbk.app.live.widget;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import java.math.BigDecimal;

public class NumberAnimTextView extends AppCompatTextView {
    private String a = "0";
    private String b;
    private long c = 2000;
    private String d = "";
    private String e = "";

    class a implements TypeEvaluator {
        final /* synthetic */ NumberAnimTextView a;

        a(NumberAnimTextView numberAnimTextView) {
            this.a = numberAnimTextView;
        }

        public Object evaluate(float f, Object obj, Object obj2) {
            BigDecimal bigDecimal = (BigDecimal) obj;
            return ((BigDecimal) obj2).subtract(bigDecimal).multiply(new BigDecimal("" + f)).add(bigDecimal);
        }
    }

    public NumberAnimTextView(Context context) {
        super(context);
    }

    public NumberAnimTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NumberAnimTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setNumberString(String str) {
        setNumberString("0", str);
    }

    public void setNumberString(String str, String str2) {
        this.a = str;
        this.b = str2;
        if (a(str, str2)) {
            a();
        } else {
            setText(this.d + str2 + this.e);
        }
    }

    public void setDuration(long j) {
        this.c = j;
    }

    public void setPrefixString(String str) {
        this.d = str;
    }

    public void setPostfixString(String str) {
        this.e = str;
    }

    private boolean a(String str, String str2) {
        try {
            if (new BigDecimal(str2).compareTo(new BigDecimal(str)) >= 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void a() {
        ValueAnimator ofObject = ValueAnimator.ofObject(new a(this), new Object[]{new BigDecimal(this.a), new BigDecimal(this.b)});
        ofObject.setDuration(this.c);
        ofObject.setInterpolator(new AccelerateDecelerateInterpolator());
        ofObject.addUpdateListener(new ho(this));
        ofObject.start();
    }
}
