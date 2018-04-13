package com.prolificinteractive.materialcalendarview;

import android.content.res.Resources;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.ViewPropertyAnimator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.TextView;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

class o {
    public static final int DEFAULT_ANIMATION_DELAY = 400;
    public static final int DEFAULT_Y_TRANSLATION_DP = 20;
    private final TextView a;
    private final int b;
    private final int c;
    private final int d;
    private final Interpolator e = new DecelerateInterpolator(2.0f);
    private TitleFormatter f;
    private int g = 0;
    private long h = 0;
    private CalendarDay i = null;

    public o(TextView textView) {
        this.a = textView;
        Resources resources = textView.getResources();
        this.b = 400;
        this.c = resources.getInteger(17694720) / 2;
        this.d = (int) TypedValue.applyDimension(1, 20.0f, resources.getDisplayMetrics());
    }

    public void change(CalendarDay calendarDay) {
        long currentTimeMillis = System.currentTimeMillis();
        if (calendarDay != null) {
            if (TextUtils.isEmpty(this.a.getText()) || currentTimeMillis - this.h < ((long) this.b)) {
                a(currentTimeMillis, calendarDay, false);
            }
            if (!calendarDay.equals(this.i)) {
                if (calendarDay.getMonth() != this.i.getMonth() || calendarDay.getYear() != this.i.getYear()) {
                    a(currentTimeMillis, calendarDay, true);
                }
            }
        }
    }

    private void a(long j, CalendarDay calendarDay, boolean z) {
        this.a.animate().cancel();
        a(this.a, 0);
        this.a.setAlpha(1.0f);
        this.h = j;
        CharSequence format = this.f.format(calendarDay);
        if (z) {
            int i = (this.i.isBefore(calendarDay) ? 1 : -1) * this.d;
            ViewPropertyAnimator animate = this.a.animate();
            if (this.g == 1) {
                animate.translationX((float) (i * -1));
            } else {
                animate.translationY((float) (i * -1));
            }
            animate.alpha(0.0f).setDuration((long) this.c).setInterpolator(this.e).setListener(new p(this, format, i)).start();
        } else {
            this.a.setText(format);
        }
        this.i = calendarDay;
    }

    private void a(TextView textView, int i) {
        if (this.g == 1) {
            textView.setTranslationX((float) i);
        } else {
            textView.setTranslationY((float) i);
        }
    }

    public TitleFormatter getTitleFormatter() {
        return this.f;
    }

    public void setTitleFormatter(TitleFormatter titleFormatter) {
        this.f = titleFormatter;
    }

    public int getOrientation() {
        return this.g;
    }

    public void setOrientation(int i) {
        this.g = i;
    }

    public void setPreviousMonth(CalendarDay calendarDay) {
        this.i = calendarDay;
    }
}
