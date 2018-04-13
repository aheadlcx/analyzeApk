package com.prolificinteractive.materialcalendarview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

public class MaterialCalendarView extends ViewGroup {
    public static final int DEFAULT_TILE_SIZE_DP = 44;
    public static final int HORIZONTAL = 1;
    public static final int INVALID_TILE_DIMENSION = -10;
    public static final int SELECTION_MODE_MULTIPLE = 2;
    public static final int SELECTION_MODE_NONE = 0;
    public static final int SELECTION_MODE_RANGE = 3;
    public static final int SELECTION_MODE_SINGLE = 1;
    public static final int SHOW_ALL = 7;
    public static final int SHOW_DECORATED_DISABLED = 4;
    public static final int SHOW_DEFAULTS = 4;
    public static final int SHOW_NONE = 0;
    public static final int SHOW_OTHER_MONTHS = 1;
    public static final int SHOW_OUT_OF_RANGE = 2;
    public static final int VERTICAL = 0;
    private static final TitleFormatter b = new DateFormatTitleFormatter();
    private OnMonthChangedListener A;
    private final OnPageChangeListener B;
    private OnRangeSelectedListener C;
    private int D;
    private int E;
    private Drawable F;
    private Drawable G;
    private int H;
    private int I;
    @SelectionMode
    private int J;
    private boolean K;
    private int L;
    private State M;
    CharSequence a;
    private final ArrayList<DayViewDecorator> c;
    private InterceptDateClickListener d;
    private o e;
    private TextView f;
    private ImageView g;
    private TextView h;
    private ImageView i;
    private LinearLayout j;
    private h k;
    private h l;
    private h m;
    private c n;
    private d<?> o;
    private CalendarDay p;
    private RelativeLayout q;
    private CalendarMode r;
    private boolean s;
    private OnClickListener t;
    private OnClickListener u;
    private OnClickListener v;
    private final OnClickListener w;
    private CalendarDay x;
    private CalendarDay y;
    private OnDateSelectedListener z;

    protected static class LayoutParams extends MarginLayoutParams {
        public LayoutParams(int i) {
            super(-1, i);
        }
    }

    public static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new m();
        int a;
        int b;
        int c;
        int d;
        boolean e;
        CalendarDay f;
        CalendarDay g;
        List<CalendarDay> h;
        int i;
        int j;
        int k;
        int l;
        boolean m;
        int n;
        boolean o;
        CalendarMode p;
        CalendarDay q;
        boolean r;

        SavedState(Parcelable parcelable) {
            super(parcelable);
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 4;
            this.e = true;
            this.f = null;
            this.g = null;
            this.h = new ArrayList();
            this.i = 1;
            this.j = 0;
            this.k = -1;
            this.l = -1;
            this.m = true;
            this.n = 1;
            this.o = false;
            this.p = CalendarMode.MONTHS;
            this.q = null;
        }

        private SavedState(Parcel parcel) {
            boolean z;
            boolean z2 = true;
            super(parcel);
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 4;
            this.e = true;
            this.f = null;
            this.g = null;
            this.h = new ArrayList();
            this.i = 1;
            this.j = 0;
            this.k = -1;
            this.l = -1;
            this.m = true;
            this.n = 1;
            this.o = false;
            this.p = CalendarMode.MONTHS;
            this.q = null;
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readByte() != (byte) 0;
            ClassLoader classLoader = CalendarDay.class.getClassLoader();
            this.f = (CalendarDay) parcel.readParcelable(classLoader);
            this.g = (CalendarDay) parcel.readParcelable(classLoader);
            parcel.readTypedList(this.h, CalendarDay.CREATOR);
            this.i = parcel.readInt();
            this.j = parcel.readInt();
            this.k = parcel.readInt();
            this.l = parcel.readInt();
            if (parcel.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            this.m = z;
            this.n = parcel.readInt();
            if (parcel.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            this.o = z;
            this.p = parcel.readInt() == 1 ? CalendarMode.WEEKS : CalendarMode.MONTHS;
            this.q = (CalendarDay) parcel.readParcelable(classLoader);
            if (parcel.readByte() == (byte) 0) {
                z2 = false;
            }
            this.r = z2;
        }

        public void writeToParcel(@NonNull Parcel parcel, int i) {
            int i2;
            int i3 = 1;
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
            parcel.writeByte((byte) (this.e ? 1 : 0));
            parcel.writeParcelable(this.f, 0);
            parcel.writeParcelable(this.g, 0);
            parcel.writeTypedList(this.h);
            parcel.writeInt(this.i);
            parcel.writeInt(this.j);
            parcel.writeInt(this.k);
            parcel.writeInt(this.l);
            if (this.m) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            parcel.writeInt(i2);
            parcel.writeInt(this.n);
            if (this.o) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            parcel.writeInt(i2);
            if (this.p == CalendarMode.WEEKS) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            parcel.writeInt(i2);
            parcel.writeParcelable(this.q, 0);
            if (!this.r) {
                i3 = 0;
            }
            parcel.writeByte((byte) i3);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface SelectionMode {
    }

    @SuppressLint({"UniqueConstants"})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ShowOtherDates {
    }

    public class State {
        final /* synthetic */ MaterialCalendarView a;
        private final CalendarMode b;
        private final int c;
        private final CalendarDay d;
        private final CalendarDay e;
        private final boolean f;

        private State(MaterialCalendarView materialCalendarView, StateBuilder stateBuilder) {
            this.a = materialCalendarView;
            this.b = stateBuilder.b;
            this.c = stateBuilder.c;
            this.d = stateBuilder.e;
            this.e = stateBuilder.f;
            this.f = stateBuilder.d;
        }

        public StateBuilder edit() {
            return new StateBuilder(this);
        }
    }

    public class StateBuilder {
        final /* synthetic */ MaterialCalendarView a;
        private CalendarMode b;
        private int c;
        private boolean d;
        private CalendarDay e;
        private CalendarDay f;

        public StateBuilder(MaterialCalendarView materialCalendarView) {
            this.a = materialCalendarView;
            this.b = CalendarMode.MONTHS;
            this.c = Calendar.getInstance().getFirstDayOfWeek();
            this.d = false;
            this.e = null;
            this.f = null;
        }

        private StateBuilder(MaterialCalendarView materialCalendarView, State state) {
            this.a = materialCalendarView;
            this.b = CalendarMode.MONTHS;
            this.c = Calendar.getInstance().getFirstDayOfWeek();
            this.d = false;
            this.e = null;
            this.f = null;
            this.b = state.b;
            this.c = state.c;
            this.e = state.d;
            this.f = state.e;
            this.d = state.f;
        }

        public StateBuilder setFirstDayOfWeek(int i) {
            this.c = i;
            return this;
        }

        public StateBuilder setCalendarDisplayMode(CalendarMode calendarMode) {
            this.b = calendarMode;
            return this;
        }

        public StateBuilder setMinimumDate(@Nullable Calendar calendar) {
            setMinimumDate(CalendarDay.from(calendar));
            return this;
        }

        public StateBuilder setMinimumDate(@Nullable Date date) {
            setMinimumDate(CalendarDay.from(date));
            return this;
        }

        public StateBuilder setMinimumDate(@Nullable CalendarDay calendarDay) {
            this.e = calendarDay;
            return this;
        }

        public StateBuilder setMaximumDate(@Nullable Calendar calendar) {
            setMaximumDate(CalendarDay.from(calendar));
            return this;
        }

        public StateBuilder setMaximumDate(@Nullable Date date) {
            setMaximumDate(CalendarDay.from(date));
            return this;
        }

        public StateBuilder setMaximumDate(@Nullable CalendarDay calendarDay) {
            this.f = calendarDay;
            return this;
        }

        public StateBuilder isCacheCalendarPositionEnabled(boolean z) {
            this.d = z;
            return this;
        }

        public void commit() {
            this.a.a(new State(this));
        }
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return a();
    }

    public MaterialCalendarView(Context context) {
        this(context, null);
        a(context, null, 0);
    }

    public MaterialCalendarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new ArrayList();
        this.w = new i(this);
        this.x = null;
        this.y = null;
        this.B = new j(this);
        this.D = 0;
        this.E = -16777216;
        this.H = -10;
        this.I = -10;
        this.J = 1;
        this.K = true;
        a(context, attributeSet, 0);
    }

    public MaterialCalendarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new ArrayList();
        this.w = new i(this);
        this.x = null;
        this.y = null;
        this.B = new j(this);
        this.D = 0;
        this.E = -16777216;
        this.H = -10;
        this.I = -10;
        this.J = 1;
        this.K = true;
        a(context, attributeSet, i);
    }

    private static int a(Context context) {
        int i;
        if (VERSION.SDK_INT >= 21) {
            i = 16843829;
        } else {
            i = context.getResources().getIdentifier("colorAccent", "attr", context.getPackageName());
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.data;
    }

    public static boolean showOtherMonths(@ShowOtherDates int i) {
        return (i & 1) != 0;
    }

    public static boolean showOutOfRange(@ShowOtherDates int i) {
        return (i & 2) != 0;
    }

    public static boolean showDecoratedDisabled(@ShowOtherDates int i) {
        return (i & 4) != 0;
    }

    private static int a(int i, int i2) {
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        switch (mode) {
            case Integer.MIN_VALUE:
                return Math.min(i, size);
            case 1073741824:
                return size;
            default:
                return i;
        }
    }

    public void setBtnLeftClickListener(OnClickListener onClickListener) {
        this.t = onClickListener;
    }

    public void setBtnRightClickListener(OnClickListener onClickListener) {
        this.u = onClickListener;
    }

    public void setArrowDownClickListener(OnClickListener onClickListener) {
        this.v = onClickListener;
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        if (VERSION.SDK_INT >= 19) {
            setClipToPadding(false);
            setClipChildren(false);
        } else {
            setClipChildren(true);
            setClipToPadding(true);
        }
        this.m = new h(getContext());
        this.m.setPadding(UIHelper.dip2px(getContext(), 6.0f), 0, 6, 0);
        this.k = new h(getContext());
        this.k.setContentDescription(getContext().getString(R.string.previous));
        this.f = new TextView(getContext());
        this.f.setTextSize(2, 16.0f);
        this.l = new h(getContext());
        this.l.setContentDescription(getContext().getString(R.string.next));
        this.n = new c(getContext());
        this.k.setOnClickListener(this.w);
        this.l.setOnClickListener(this.w);
        this.m.setOnClickListener(this.w);
        this.f.setOnClickListener(this.w);
        this.e = new o(this.f);
        this.e.setTitleFormatter(b);
        this.n.setOnPageChangeListener(this.B);
        this.n.setPageTransformer(false, new k(this));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MaterialCalendarView, 0, 0);
        try {
            int integer = obtainStyledAttributes.getInteger(17, 0);
            this.L = obtainStyledAttributes.getInteger(16, -1);
            this.e.setOrientation(obtainStyledAttributes.getInteger(18, 0));
            if (this.L < 0) {
                this.L = Calendar.getInstance().getFirstDayOfWeek();
            }
            newState().setFirstDayOfWeek(this.L).setCalendarDisplayMode(CalendarMode.values()[integer]).commit();
            integer = obtainStyledAttributes.getLayoutDimension(13, -10);
            if (integer > -10) {
                setTileSize(integer);
            }
            integer = obtainStyledAttributes.getLayoutDimension(15, -10);
            if (integer > -10) {
                setTileWidth(integer);
            }
            integer = obtainStyledAttributes.getLayoutDimension(14, -10);
            if (integer > -10) {
                setTileHeight(integer);
            }
            Drawable drawable = obtainStyledAttributes.getDrawable(7);
            if (drawable != null) {
                this.g.setImageDrawable(drawable);
            }
            drawable = obtainStyledAttributes.getDrawable(8);
            if (drawable != null) {
                this.i.setImageDrawable(drawable);
            }
            setArrowColor(obtainStyledAttributes.getColor(3, -16777216));
            setLeftArrowMask(obtainStyledAttributes.getDrawable(4));
            setRightArrowMask(obtainStyledAttributes.getDrawable(5));
            setSelectionColor(obtainStyledAttributes.getColor(6, a(context)));
            CharSequence[] textArray = obtainStyledAttributes.getTextArray(11);
            if (textArray != null) {
                setWeekDayFormatter(new ArrayWeekDayFormatter(textArray));
            }
            textArray = obtainStyledAttributes.getTextArray(12);
            if (textArray != null) {
                setTitleFormatter(new MonthArrayTitleFormatter(textArray));
            }
            setHeaderTextAppearance(obtainStyledAttributes.getResourceId(2, R.style.TextAppearance.MaterialCalendarWidget.Header));
            setWeekDayTextAppearance(obtainStyledAttributes.getResourceId(1, R.style.TextAppearance.MaterialCalendarWidget.WeekDay));
            setDateTextAppearance(obtainStyledAttributes.getResourceId(0, R.style.TextAppearance.MaterialCalendarWidget.Date));
            setShowOtherDates(obtainStyledAttributes.getInteger(9, 4));
            setAllowClickDaysOutsideCurrentMonth(obtainStyledAttributes.getBoolean(10, true));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            obtainStyledAttributes.recycle();
        }
        this.o.setTitleFormatter(b);
        b();
        this.p = CalendarDay.today();
        setCurrentDate(this.p);
        if (isInEditMode()) {
            removeView(this.n);
            View nVar = new n(this, this.p, getFirstDayOfWeek());
            nVar.setSelectionColor(getSelectionColor());
            nVar.setDateTextAppearance(this.o.a());
            nVar.setWeekDayTextAppearance(this.o.b());
            nVar.setShowOtherDates(getShowOtherDates());
            addView(nVar, new LayoutParams(this.r.a + 1));
        }
    }

    private void b() {
        this.q = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.layout_calendar_top, null);
        this.q.setClipChildren(false);
        this.q.setClipToPadding(false);
        this.q.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.shape_yellow_top_dark : R.drawable.shape_yellow_top);
        addView(this.q, new LayoutParams(1));
        this.g = (ImageView) this.q.findViewById(R.id.left_img);
        this.h = (TextView) this.q.findViewById(R.id.left_count);
        this.i = (ImageView) this.q.findViewById(R.id.btn_right);
        this.g.setOnClickListener(this.w);
        this.i.setOnClickListener(this.w);
        this.f.setGravity(17);
        this.j = (LinearLayout) this.q.findViewById(R.id.title_container);
        this.j.addView(this.f, new android.widget.LinearLayout.LayoutParams(-2, -1));
        this.m.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_down_sign));
        this.m.setScaleType(ScaleType.CENTER_INSIDE);
        this.j.addView(this.m, new android.widget.LinearLayout.LayoutParams(-2, -1));
        this.n.setId(R.id.mcv_pager);
        this.n.setOffscreenPageLimit(1);
        addView(this.n, new LayoutParams(this.r.a + 1));
    }

    public View getLeftView() {
        return this.g;
    }

    public View getRightView() {
        return this.i;
    }

    public View getTitleView() {
        return this.j;
    }

    public View getPagerView() {
        return this.n;
    }

    public void setLeftCount(int i) {
        if (i > 0) {
            this.h.setVisibility(0);
            this.h.setText(i + "");
            this.g.setImageResource(R.drawable.ic_sign_card);
            return;
        }
        this.h.setVisibility(4);
        this.g.setImageResource(R.drawable.ic_catch_up_card_init);
    }

    private void c() {
        this.e.change(this.p);
        this.k.setEnabled(canGoBack());
        this.l.setEnabled(canGoForward());
    }

    public void goToPrevious() {
        if (canGoBack()) {
            this.n.setCurrentItem(this.n.getCurrentItem() - 1, true);
        }
    }

    public void goToNext() {
        if (canGoForward()) {
            this.n.setCurrentItem(this.n.getCurrentItem() + 1, true);
        }
    }

    @SelectionMode
    public int getSelectionMode() {
        return this.J;
    }

    public void setSelectionMode(@SelectionMode int i) {
        boolean z = false;
        int i2 = this.J;
        this.J = i;
        switch (i) {
            case 1:
                if ((i2 == 2 || i2 == 3) && !getSelectedDates().isEmpty()) {
                    setSelectedDate(getSelectedDate());
                    break;
                }
            case 2:
                break;
            case 3:
                clearSelection();
                break;
            default:
                this.J = 0;
                if (i2 != 0) {
                    clearSelection();
                    break;
                }
                break;
        }
        d dVar = this.o;
        if (this.J != 0) {
            z = true;
        }
        dVar.setSelectionEnabled(z);
    }

    @Deprecated
    public int getTileSize() {
        return Math.max(this.H, this.I);
    }

    public void setTileSize(int i) {
        this.I = i;
        this.H = i;
        requestLayout();
    }

    public void setTileSizeDp(int i) {
        setTileSize(a(i));
    }

    public int getTileHeight() {
        return this.H;
    }

    public void setTileHeight(int i) {
        this.H = i;
        requestLayout();
    }

    public void setTileHeightDp(int i) {
        setTileHeight(a(i));
    }

    public int getTileWidth() {
        return this.I;
    }

    public void setTileWidth(int i) {
        this.I = i;
        requestLayout();
    }

    public void setTileWidthDp(int i) {
        setTileWidth(a(i));
    }

    private int a(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getResources().getDisplayMetrics());
    }

    public boolean canGoForward() {
        return this.n.getCurrentItem() < this.o.getCount() + -1;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.n.dispatchTouchEvent(motionEvent);
    }

    public boolean canGoBack() {
        return this.n.getCurrentItem() > 0;
    }

    public int getSelectionColor() {
        return this.D;
    }

    public void setSelectionColor(int i) {
        if (i == 0) {
            if (isInEditMode()) {
                i = -7829368;
            } else {
                return;
            }
        }
        this.D = i;
        this.o.setSelectionColor(i);
        invalidate();
    }

    public int getArrowColor() {
        return this.E;
    }

    public void setArrowColor(int i) {
        if (i != 0) {
            this.E = i;
            this.k.setColor(i);
            this.l.setColor(i);
            invalidate();
        }
    }

    public void setContentDescriptionArrowPast(CharSequence charSequence) {
        this.k.setContentDescription(charSequence);
    }

    public void setContentDescriptionArrowFuture(CharSequence charSequence) {
        this.l.setContentDescription(charSequence);
    }

    public void setContentDescriptionCalendar(CharSequence charSequence) {
        this.a = charSequence;
    }

    public CharSequence getCalendarContentDescription() {
        if (this.a != null) {
            return this.a;
        }
        return getContext().getString(R.string.calendar);
    }

    public Drawable getLeftArrowMask() {
        return this.F;
    }

    public void setLeftArrowMask(Drawable drawable) {
        this.F = drawable;
        this.k.setImageDrawable(drawable);
    }

    public Drawable getRightArrowMask() {
        return this.G;
    }

    public void setRightArrowMask(Drawable drawable) {
        this.G = drawable;
        this.l.setImageDrawable(drawable);
    }

    public void setHeaderTextAppearance(int i) {
        this.f.setTextAppearance(getContext(), i);
    }

    public void setDateTextAppearance(int i) {
        this.o.setDateTextAppearance(i);
    }

    public void setWeekDayTextAppearance(int i) {
        this.o.setWeekDayTextAppearance(i);
    }

    public CalendarDay getSelectedDate() {
        List selectedDates = this.o.getSelectedDates();
        if (selectedDates.isEmpty()) {
            return null;
        }
        return (CalendarDay) selectedDates.get(selectedDates.size() - 1);
    }

    public void setSelectedDate(@Nullable CalendarDay calendarDay) {
        clearSelection();
        if (calendarDay != null) {
            setDateSelected(calendarDay, true);
        }
    }

    @NonNull
    public List<CalendarDay> getSelectedDates() {
        return this.o.getSelectedDates();
    }

    public void clearSelection() {
        List<CalendarDay> selectedDates = getSelectedDates();
        this.o.clearSelections();
        for (CalendarDay a : selectedDates) {
            a(a, false);
        }
    }

    public void setSelectedDate(@Nullable Calendar calendar) {
        setSelectedDate(CalendarDay.from(calendar));
    }

    public void setSelectedDate(@Nullable Date date) {
        setSelectedDate(CalendarDay.from(date));
    }

    public void setDateSelected(@Nullable Calendar calendar, boolean z) {
        setDateSelected(CalendarDay.from(calendar), z);
    }

    public void setDateSelected(@Nullable Date date, boolean z) {
        setDateSelected(CalendarDay.from(date), z);
    }

    public void setDateSelected(@Nullable CalendarDay calendarDay, boolean z) {
        if (calendarDay != null) {
            this.o.setDateSelected(calendarDay, z);
        }
    }

    public void setCurrentDate(@Nullable Calendar calendar) {
        setCurrentDate(CalendarDay.from(calendar));
    }

    public void setCurrentDate(@Nullable Date date) {
        setCurrentDate(CalendarDay.from(date));
    }

    public CalendarDay getCurrentDate() {
        return this.o.getItem(this.n.getCurrentItem());
    }

    public void setCurrentDate(@Nullable CalendarDay calendarDay) {
        setCurrentDate(calendarDay, true);
    }

    public void setCurrentDate(@Nullable CalendarDay calendarDay, boolean z) {
        if (calendarDay != null) {
            this.n.setCurrentItem(this.o.getIndexForDay(calendarDay), z);
            c();
        }
    }

    public CalendarDay getMinimumDate() {
        return this.x;
    }

    public CalendarDay getMaximumDate() {
        return this.y;
    }

    public void setAllowClickDaysOutsideCurrentMonth(boolean z) {
        this.K = z;
    }

    public void setWeekDayFormatter(WeekDayFormatter weekDayFormatter) {
        d dVar = this.o;
        if (weekDayFormatter == null) {
            weekDayFormatter = WeekDayFormatter.DEFAULT;
        }
        dVar.setWeekDayFormatter(weekDayFormatter);
    }

    public void setDayFormatter(DayFormatter dayFormatter) {
        d dVar = this.o;
        if (dayFormatter == null) {
            dayFormatter = DayFormatter.DEFAULT;
        }
        dVar.setDayFormatter(dayFormatter);
    }

    public void setWeekDayLabels(CharSequence[] charSequenceArr) {
        setWeekDayFormatter(new ArrayWeekDayFormatter(charSequenceArr));
    }

    public void setWeekDayLabels(@ArrayRes int i) {
        setWeekDayLabels(getResources().getTextArray(i));
    }

    @ShowOtherDates
    public int getShowOtherDates() {
        return this.o.getShowOtherDates();
    }

    public void setShowOtherDates(@ShowOtherDates int i) {
        this.o.setShowOtherDates(i);
    }

    public boolean allowClickDaysOutsideCurrentMonth() {
        return this.K;
    }

    public void setTitleFormatter(TitleFormatter titleFormatter) {
        if (titleFormatter == null) {
            titleFormatter = b;
        }
        this.e.setTitleFormatter(titleFormatter);
        this.o.setTitleFormatter(titleFormatter);
        c();
    }

    public void setTitleMonths(CharSequence[] charSequenceArr) {
        setTitleFormatter(new MonthArrayTitleFormatter(charSequenceArr));
    }

    public void setTitleMonths(@ArrayRes int i) {
        setTitleMonths(getResources().getTextArray(i));
    }

    public int getTitleAnimationOrientation() {
        return this.e.getOrientation();
    }

    public void setTitleAnimationOrientation(int i) {
        this.e.setOrientation(i);
    }

    public boolean getTopbarVisible() {
        return this.q.getVisibility() == 0;
    }

    public void setTopbarVisible(boolean z) {
        this.q.setVisibility(z ? 0 : 8);
        requestLayout();
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = getSelectionColor();
        savedState.b = this.o.a();
        savedState.c = this.o.b();
        savedState.d = getShowOtherDates();
        savedState.e = allowClickDaysOutsideCurrentMonth();
        savedState.f = getMinimumDate();
        savedState.g = getMaximumDate();
        savedState.h = getSelectedDates();
        savedState.i = getFirstDayOfWeek();
        savedState.j = getTitleAnimationOrientation();
        savedState.n = getSelectionMode();
        savedState.k = getTileWidth();
        savedState.l = getTileHeight();
        savedState.m = getTopbarVisible();
        savedState.p = this.r;
        savedState.o = this.s;
        savedState.q = this.p;
        savedState.r = this.M.f;
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        newState().setFirstDayOfWeek(savedState.i).setCalendarDisplayMode(savedState.p).setMinimumDate(savedState.f).setMaximumDate(savedState.g).isCacheCalendarPositionEnabled(savedState.r).commit();
        setSelectionColor(savedState.a);
        setDateTextAppearance(savedState.b);
        setWeekDayTextAppearance(savedState.c);
        setShowOtherDates(savedState.d);
        setAllowClickDaysOutsideCurrentMonth(savedState.e);
        clearSelection();
        for (CalendarDay dateSelected : savedState.h) {
            setDateSelected(dateSelected, true);
        }
        setTitleAnimationOrientation(savedState.j);
        setTileWidth(savedState.k);
        setTileHeight(savedState.l);
        setTopbarVisible(savedState.m);
        setSelectionMode(savedState.n);
        setDynamicHeightEnabled(savedState.o);
        setCurrentDate(savedState.q);
    }

    protected void dispatchSaveInstanceState(@NonNull SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    protected void dispatchRestoreInstanceState(@NonNull SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    private void b(CalendarDay calendarDay, CalendarDay calendarDay2) {
        CalendarDay calendarDay3 = this.p;
        this.o.setRangeDates(calendarDay, calendarDay2);
        this.p = calendarDay3;
        if (calendarDay != null) {
            if (!calendarDay.isAfter(this.p)) {
                calendarDay = this.p;
            }
            this.p = calendarDay;
        }
        this.n.setCurrentItem(this.o.getIndexForDay(calendarDay3), false);
        c();
    }

    public int getFirstDayOfWeek() {
        return this.L;
    }

    public boolean isDynamicHeightEnabled() {
        return this.s;
    }

    public void setDynamicHeightEnabled(boolean z) {
        this.s = z;
    }

    public void addDecorators(Collection<? extends DayViewDecorator> collection) {
        if (collection != null) {
            this.c.addAll(collection);
            this.o.setDecorators(this.c);
        }
    }

    public void addDecorators(DayViewDecorator... dayViewDecoratorArr) {
        addDecorators(Arrays.asList(dayViewDecoratorArr));
    }

    public void addDecorator(DayViewDecorator dayViewDecorator) {
        if (dayViewDecorator != null) {
            this.c.add(dayViewDecorator);
            this.o.setDecorators(this.c);
        }
    }

    public void removeDecorators() {
        this.c.clear();
        this.o.setDecorators(this.c);
    }

    public void removeDecorator(DayViewDecorator dayViewDecorator) {
        this.c.remove(dayViewDecorator);
        this.o.setDecorators(this.c);
    }

    public void invalidateDecorators() {
        this.o.invalidateDecorators();
    }

    public void setOnDateChangedListener(OnDateSelectedListener onDateSelectedListener) {
        this.z = onDateSelectedListener;
    }

    public void setOnMonthChangedListener(OnMonthChangedListener onMonthChangedListener) {
        this.A = onMonthChangedListener;
    }

    public void setOnRangeSelectedListener(OnRangeSelectedListener onRangeSelectedListener) {
        this.C = onRangeSelectedListener;
    }

    public void setOnTitleClickListener(OnClickListener onClickListener) {
        this.f.setOnClickListener(onClickListener);
    }

    public void setOnInterceptDateClickListener(InterceptDateClickListener interceptDateClickListener) {
        this.d = interceptDateClickListener;
    }

    protected void a(CalendarDay calendarDay, boolean z) {
        OnDateSelectedListener onDateSelectedListener = this.z;
        if (onDateSelectedListener != null) {
            onDateSelectedListener.onDateSelected(this, calendarDay, z);
        }
    }

    protected void a(CalendarDay calendarDay, CalendarDay calendarDay2) {
        OnRangeSelectedListener onRangeSelectedListener = this.C;
        List arrayList = new ArrayList();
        Calendar instance = Calendar.getInstance();
        instance.setTime(calendarDay.getDate());
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(calendarDay2.getDate());
        while (true) {
            if (!instance.before(instance2) && !instance.equals(instance2)) {
                break;
            }
            CalendarDay from = CalendarDay.from(instance);
            this.o.setDateSelected(from, true);
            arrayList.add(from);
            instance.add(5, 1);
        }
        if (onRangeSelectedListener != null) {
            onRangeSelectedListener.onRangeSelected(this, arrayList);
        }
    }

    protected void a(CalendarDay calendarDay) {
        OnMonthChangedListener onMonthChangedListener = this.A;
        if (onMonthChangedListener != null) {
            onMonthChangedListener.onMonthChanged(this, calendarDay);
        }
    }

    protected void b(@NonNull CalendarDay calendarDay, boolean z) {
        if (this.d == null || !this.d.intercept(calendarDay, z)) {
            switch (this.J) {
                case 2:
                    this.o.setDateSelected(calendarDay, z);
                    a(calendarDay, z);
                    return;
                case 3:
                    this.o.setDateSelected(calendarDay, z);
                    if (this.o.getSelectedDates().size() > 2) {
                        this.o.clearSelections();
                        this.o.setDateSelected(calendarDay, z);
                        a(calendarDay, z);
                        return;
                    } else if (this.o.getSelectedDates().size() == 2) {
                        List selectedDates = this.o.getSelectedDates();
                        if (((CalendarDay) selectedDates.get(0)).isAfter((CalendarDay) selectedDates.get(1))) {
                            a((CalendarDay) selectedDates.get(1), (CalendarDay) selectedDates.get(0));
                            return;
                        } else {
                            a((CalendarDay) selectedDates.get(0), (CalendarDay) selectedDates.get(1));
                            return;
                        }
                    } else {
                        this.o.setDateSelected(calendarDay, z);
                        a(calendarDay, z);
                        return;
                    }
                default:
                    this.o.clearSelections();
                    this.o.setDateSelected(calendarDay, true);
                    a(calendarDay, true);
                    return;
            }
        }
    }

    public void selectRange(CalendarDay calendarDay, CalendarDay calendarDay2) {
        clearSelection();
        if (calendarDay != null && calendarDay2 != null) {
            if (calendarDay.isAfter(calendarDay2)) {
                a(calendarDay2, calendarDay);
            } else {
                a(calendarDay, calendarDay2);
            }
        }
    }

    protected void a(f fVar) {
        CalendarDay currentDate = getCurrentDate();
        CalendarDay date = fVar.getDate();
        int month = currentDate.getMonth();
        int month2 = date.getMonth();
        if (this.r == CalendarMode.MONTHS && this.K && month != month2) {
            if (currentDate.isAfter(date)) {
                goToPrevious();
            } else if (currentDate.isBefore(date)) {
                goToNext();
            }
        }
        b(fVar.getDate(), !fVar.isChecked());
    }

    protected void b(CalendarDay calendarDay) {
        a(calendarDay, false);
    }

    protected LayoutParams a() {
        return new LayoutParams(1);
    }

    protected void onMeasure(int i, int i2) {
        int i3 = -1;
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i2);
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int paddingTop = (size2 - getPaddingTop()) - getPaddingBottom();
        size = getWeekCountBasedOnMode();
        if (getTopbarVisible()) {
            size++;
        }
        size2 = paddingLeft / 7;
        paddingLeft = paddingTop / size;
        if (this.I != -10 || this.H != -10) {
            if (this.I > 0) {
                size2 = this.I;
            }
            if (this.H > 0) {
                paddingLeft = size2;
                size2 = -1;
                i3 = this.H;
            } else {
                int i4 = paddingLeft;
                paddingLeft = size2;
                size2 = -1;
                i3 = i4;
            }
        } else if (mode == 1073741824 || mode == Integer.MIN_VALUE) {
            if (mode2 == 1073741824) {
                size2 = Math.min(size2, paddingLeft);
                paddingLeft = -1;
            } else {
                paddingLeft = -1;
            }
        } else if (mode2 == 1073741824 || mode2 == Integer.MIN_VALUE) {
            size2 = paddingLeft;
            paddingLeft = -1;
        } else {
            paddingLeft = -1;
            size2 = -1;
        }
        if (size2 > 0) {
            i3 = size2;
        } else {
            if (size2 <= 0) {
                if (paddingLeft <= 0) {
                    paddingLeft = a(44);
                }
                if (i3 <= 0) {
                    size2 = a(44);
                    i3 = paddingLeft;
                }
            }
            size2 = i3;
            i3 = paddingLeft;
        }
        setMeasuredDimension(a((i3 * 7) + (getPaddingLeft() + getPaddingRight()), i), a((size * size2) + (getPaddingTop() + getPaddingBottom()), i2));
        mode = getChildCount();
        for (paddingLeft = 0; paddingLeft < mode; paddingLeft++) {
            View childAt = getChildAt(paddingLeft);
            childAt.measure(MeasureSpec.makeMeasureSpec(i3 * 7, 1073741824), MeasureSpec.makeMeasureSpec(((LayoutParams) childAt.getLayoutParams()).height * size2, 1073741824));
        }
    }

    private int getWeekCountBasedOnMode() {
        int i = this.r.a;
        if (this.r.equals(CalendarMode.MONTHS) && this.s && this.o != null && this.n != null) {
            Calendar calendar = (Calendar) this.o.getItem(this.n.getCurrentItem()).getCalendar().clone();
            calendar.set(5, calendar.getActualMaximum(5));
            calendar.setFirstDayOfWeek(getFirstDayOfWeek());
            i = calendar.get(4);
        }
        return i + 1;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = ((i3 - i) - paddingLeft) - getPaddingRight();
        int paddingTop = getPaddingTop();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = ((paddingRight - measuredWidth) / 2) + paddingLeft;
                childAt.layout(i6, paddingTop, measuredWidth + i6, paddingTop + measuredHeight);
                paddingTop += measuredHeight;
            }
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(1);
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(1);
    }

    public void onInitializeAccessibilityEvent(@NonNull AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(MaterialCalendarView.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(MaterialCalendarView.class.getName());
    }

    public boolean isPagingEnabled() {
        return this.n.isPagingEnabled();
    }

    public void setPagingEnabled(boolean z) {
        this.n.setPagingEnabled(z);
        c();
    }

    public State state() {
        return this.M;
    }

    public StateBuilder newState() {
        return new StateBuilder(this);
    }

    private void a(State state) {
        CalendarDay calendarDay;
        CalendarDay item;
        d monthPagerAdapter;
        if (this.o == null || !state.f) {
            calendarDay = null;
        } else {
            item = this.o.getItem(this.n.getCurrentItem());
            if (this.r != state.b) {
                calendarDay = getSelectedDate();
                Calendar calendar;
                CalendarDay from;
                if (this.r == CalendarMode.MONTHS && calendarDay != null) {
                    calendar = item.getCalendar();
                    calendar.add(2, 1);
                    from = CalendarDay.from(calendar);
                    if (calendarDay.equals(item) || (calendarDay.isAfter(item) && calendarDay.isBefore(from))) {
                        item = calendarDay;
                    }
                    calendarDay = item;
                } else if (this.r == CalendarMode.WEEKS) {
                    calendar = item.getCalendar();
                    calendar.add(7, 6);
                    from = CalendarDay.from(calendar);
                    if (calendarDay == null || !(calendarDay.equals(item) || calendarDay.equals(from) || (calendarDay.isAfter(item) && calendarDay.isBefore(from)))) {
                        calendarDay = from;
                    }
                }
            }
            calendarDay = item;
        }
        this.M = state;
        this.r = state.b;
        this.L = state.c;
        this.x = state.d;
        this.y = state.e;
        switch (l.a[this.r.ordinal()]) {
            case 1:
                monthPagerAdapter = new MonthPagerAdapter(this);
                break;
            case 2:
                monthPagerAdapter = new WeekPagerAdapter(this);
                break;
            default:
                throw new IllegalArgumentException("Provided display mode which is not yet implemented");
        }
        if (this.o == null) {
            this.o = monthPagerAdapter;
        } else {
            this.o = this.o.migrateStateAndReturn(monthPagerAdapter);
        }
        this.n.setAdapter(this.o);
        b(this.x, this.y);
        this.n.setLayoutParams(new LayoutParams(this.r.a + 1));
        if (this.J != 1 || this.o.getSelectedDates().isEmpty()) {
            item = CalendarDay.today();
        } else {
            item = (CalendarDay) this.o.getSelectedDates().get(0);
        }
        setCurrentDate(item);
        if (calendarDay != null) {
            this.n.setCurrentItem(this.o.getIndexForDay(calendarDay));
        }
        invalidateDecorators();
        c();
    }
}
