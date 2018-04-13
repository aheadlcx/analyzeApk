package com.bruce.pickerview.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.PopupWindow;
import com.bruce.pickerview.LoopView;
import com.xiaomi.mipush.sdk.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import qsbk.app.R;

public class DatePickerPopWin extends PopupWindow implements OnClickListener {
    List<String> a = new ArrayList();
    List<String> b = new ArrayList();
    List<String> c = new ArrayList();
    public Button cancelBtn;
    public Button confirmBtn;
    public View contentView;
    private final Calendar d;
    public LoopView dayLoopView;
    private final Calendar e;
    private int f;
    private int g;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private Context k;
    private String l;
    private String m;
    public LoopView monthLoopView;
    private int n;
    private int o;
    private int p;
    public View pickerContainerV;
    private int q;
    private boolean r;
    private boolean s;
    private OnDatePickedListener t;
    public LoopView yearLoopView;

    public static class Builder {
        private Context a;
        private OnDatePickedListener b;
        private Calendar c;
        private Calendar d;
        private boolean e = false;
        private boolean f = true;
        private int g = 1900;
        private int h = (Calendar.getInstance().get(1) + 1);
        private String i = "Cancel";
        private String j = "Confirm";
        private String k = DatePickerPopWin.getStrDate();
        private int l = Color.parseColor("#999999");
        private int m = Color.parseColor("#303F9F");
        private int n = 16;
        private int o = 25;

        public Builder(Context context, OnDatePickedListener onDatePickedListener) {
            this.a = context;
            this.b = onDatePickedListener;
        }

        public Builder maxDate(Calendar calendar) {
            this.c = calendar;
            this.h = calendar.get(1) + 1;
            return this;
        }

        public Builder minDate(Calendar calendar) {
            this.d = calendar;
            this.g = calendar.get(1);
            return this;
        }

        public Builder textCancel(String str) {
            this.i = str;
            return this;
        }

        public Builder textConfirm(String str) {
            this.j = str;
            return this;
        }

        public Builder dateChose(String str) {
            this.k = str;
            return this;
        }

        public Builder colorCancel(int i) {
            this.l = i;
            return this;
        }

        public Builder colorConfirm(int i) {
            this.m = i;
            return this;
        }

        public Builder btnTextSize(int i) {
            this.n = i;
            return this;
        }

        public Builder viewTextSize(int i) {
            this.o = i;
            return this;
        }

        public DatePickerPopWin build() {
            if (this.g <= this.h) {
                return new DatePickerPopWin(this);
            }
            throw new IllegalArgumentException();
        }

        public Builder showDayMonthYear(boolean z) {
            this.e = z;
            return this;
        }

        public Builder showDay(boolean z) {
            this.f = z;
            return this;
        }
    }

    public interface OnDatePickedListener {
        void onDatePickCompleted(DatePickerPopWin datePickerPopWin, int i, int i2, int i3, String str);
    }

    public DatePickerPopWin(Builder builder) {
        this.d = builder.c;
        this.e = builder.d;
        this.f = builder.g;
        this.g = builder.h;
        this.l = builder.i;
        this.m = builder.j;
        this.k = builder.a;
        this.t = builder.b;
        this.n = builder.l;
        this.o = builder.m;
        this.p = builder.n;
        this.q = builder.o;
        this.r = builder.e;
        this.s = builder.f;
        setSelectedDate(builder.k);
        a();
    }

    public static long getLongFromyyyyMMdd(String str) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            return date.getTime();
        }
        return -1;
    }

    public static String getStrDate() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new Date());
    }

    public static String format2LenStr(int i) {
        return i < 10 ? "0" + i : String.valueOf(i);
    }

    public static int spToPx(Context context, int i) {
        return (int) ((context.getResources().getDisplayMetrics().scaledDensity * ((float) i)) + 0.5f);
    }

    private void a() {
        this.contentView = LayoutInflater.from(this.k).inflate(this.r ? R.layout.layout_date_picker_inverted : R.layout.layout_date_picker, null);
        this.cancelBtn = (Button) this.contentView.findViewById(R.id.btn_cancel);
        this.cancelBtn.setTextColor(this.n);
        this.cancelBtn.setTextSize((float) this.p);
        this.confirmBtn = (Button) this.contentView.findViewById(R.id.btn_confirm);
        this.confirmBtn.setTextColor(this.o);
        this.confirmBtn.setTextSize((float) this.p);
        this.yearLoopView = (LoopView) this.contentView.findViewById(R.id.picker_year);
        this.monthLoopView = (LoopView) this.contentView.findViewById(R.id.picker_month);
        this.dayLoopView = (LoopView) this.contentView.findViewById(R.id.picker_day);
        this.pickerContainerV = this.contentView.findViewById(R.id.container_picker);
        this.yearLoopView.setLoopListener(new a(this));
        this.monthLoopView.setLoopListener(new b(this));
        this.dayLoopView.setLoopListener(new c(this));
        b();
        c();
        this.cancelBtn.setOnClickListener(this);
        this.confirmBtn.setOnClickListener(this);
        this.contentView.setOnClickListener(this);
        if (!TextUtils.isEmpty(this.m)) {
            this.confirmBtn.setText(this.m);
        }
        if (!TextUtils.isEmpty(this.l)) {
            this.cancelBtn.setText(this.l);
        }
        this.dayLoopView.setVisibility(this.s ? 0 : 8);
        setTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setAnimationStyle(R.style.FadeInPopWin);
        setContentView(this.contentView);
        setWidth(-1);
        setHeight(-1);
    }

    private void b() {
        int i = 0;
        int i2 = this.g - this.f;
        for (int i3 = 0; i3 < i2; i3++) {
            this.a.add(format2LenStr(this.f + i3));
        }
        while (i < 12) {
            this.b.add(format2LenStr(i + 1));
            i++;
        }
        this.yearLoopView.setDataList((ArrayList) this.a);
        this.yearLoopView.setInitPosition(this.h);
        this.monthLoopView.setDataList((ArrayList) this.b);
        this.monthLoopView.setInitPosition(this.i);
    }

    private void c() {
        Calendar instance = Calendar.getInstance();
        this.c = new ArrayList();
        instance.set(1, this.f + this.h);
        instance.set(2, this.i);
        int actualMaximum = instance.getActualMaximum(5);
        for (int i = 0; i < actualMaximum; i++) {
            this.c.add(format2LenStr(i + 1));
        }
        this.dayLoopView.setDataList((ArrayList) this.c);
        this.dayLoopView.setInitPosition(this.j);
    }

    public void setSelectedDate(String str) {
        if (!TextUtils.isEmpty(str)) {
            long longFromyyyyMMdd = getLongFromyyyyMMdd(str);
            Calendar instance = Calendar.getInstance(Locale.CHINA);
            if (longFromyyyyMMdd != -1) {
                instance.setTimeInMillis(longFromyyyyMMdd);
                this.h = instance.get(1) - this.f;
                this.i = instance.get(2);
                this.j = instance.get(5) - 1;
            }
        }
    }

    public void showPopWin(Activity activity) {
        if (activity != null) {
            Animation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
            showAtLocation(activity.getWindow().getDecorView(), 80, 0, 0);
            translateAnimation.setDuration(400);
            translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
            this.pickerContainerV.startAnimation(translateAnimation);
        }
    }

    public void dismissPopWin() {
        Animation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        translateAnimation.setDuration(400);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        translateAnimation.setAnimationListener(new d(this));
        this.pickerContainerV.startAnimation(translateAnimation);
    }

    public void onClick(View view) {
        if (view == this.contentView || view == this.cancelBtn) {
            dismissPopWin();
        } else if (view == this.confirmBtn && this.t != null) {
            int i = this.f + this.h;
            int i2 = this.i;
            int i3 = this.j + 1;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(String.valueOf(i));
            stringBuffer.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
            stringBuffer.append(format2LenStr(i2));
            stringBuffer.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
            stringBuffer.append(format2LenStr(i3));
            this.t.onDatePickCompleted(this, i, i2, i3, stringBuffer.toString());
        }
    }
}
