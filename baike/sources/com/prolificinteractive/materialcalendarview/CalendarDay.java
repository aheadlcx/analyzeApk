package com.prolificinteractive.materialcalendarview;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.alipay.sdk.util.h;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Calendar;
import java.util.Date;

public final class CalendarDay implements Parcelable {
    public static final Creator<CalendarDay> CREATOR = new b();
    private final int a;
    private final int b;
    private final int c;
    private transient Calendar d;
    private transient Date e;

    @Deprecated
    public CalendarDay() {
        this(CalendarUtils.getInstance());
    }

    @Deprecated
    public CalendarDay(Calendar calendar) {
        this(CalendarUtils.getYear(calendar), CalendarUtils.getMonth(calendar), CalendarUtils.getDay(calendar));
    }

    @Deprecated
    public CalendarDay(int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    @Deprecated
    public CalendarDay(Date date) {
        this(CalendarUtils.getInstance(date));
    }

    public CalendarDay(Parcel parcel) {
        this(parcel.readInt(), parcel.readInt(), parcel.readInt());
    }

    @NonNull
    public static CalendarDay today() {
        return from(CalendarUtils.getInstance());
    }

    @NonNull
    public static CalendarDay from(int i, int i2, int i3) {
        return new CalendarDay(i, i2, i3);
    }

    public static CalendarDay from(@Nullable Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        return from(CalendarUtils.getYear(calendar), CalendarUtils.getMonth(calendar), CalendarUtils.getDay(calendar));
    }

    public static CalendarDay from(@Nullable Date date) {
        if (date == null) {
            return null;
        }
        return from(CalendarUtils.getInstance(date));
    }

    private static int a(int i, int i2, int i3) {
        return ((i * 10000) + (i2 * 100)) + i3;
    }

    public int getYear() {
        return this.a;
    }

    public int getMonth() {
        return this.b;
    }

    public int getDay() {
        return this.c;
    }

    @NonNull
    public Date getDate() {
        if (this.e == null) {
            this.e = getCalendar().getTime();
        }
        return this.e;
    }

    @NonNull
    public Calendar getCalendar() {
        if (this.d == null) {
            this.d = CalendarUtils.getInstance();
            copyTo(this.d);
        }
        return this.d;
    }

    public void copyTo(@NonNull Calendar calendar) {
        calendar.clear();
        calendar.set(this.a, this.b, this.c);
    }

    public boolean isInRange(@Nullable CalendarDay calendarDay, @Nullable CalendarDay calendarDay2) {
        return (calendarDay == null || !calendarDay.isAfter(this)) && (calendarDay2 == null || !calendarDay2.isBefore(this));
    }

    public boolean isBefore(@NonNull CalendarDay calendarDay) {
        if (calendarDay == null) {
            throw new IllegalArgumentException("other cannot be null");
        } else if (this.a == calendarDay.a) {
            if (this.b == calendarDay.b) {
                if (this.c < calendarDay.c) {
                    return true;
                }
                return false;
            } else if (this.b >= calendarDay.b) {
                return false;
            } else {
                return true;
            }
        } else if (this.a >= calendarDay.a) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isAfter(@NonNull CalendarDay calendarDay) {
        if (calendarDay == null) {
            throw new IllegalArgumentException("other cannot be null");
        } else if (this.a == calendarDay.a) {
            if (this.b == calendarDay.b) {
                if (this.c > calendarDay.c) {
                    return true;
                }
                return false;
            } else if (this.b <= calendarDay.b) {
                return false;
            } else {
                return true;
            }
        } else if (this.a <= calendarDay.a) {
            return false;
        } else {
            return true;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CalendarDay calendarDay = (CalendarDay) obj;
        if (this.c == calendarDay.c && this.b == calendarDay.b && this.a == calendarDay.a) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return a(this.a, this.b, this.c);
    }

    public String toString() {
        return "CalendarDay{" + this.a + Constants.ACCEPT_TIME_SEPARATOR_SERVER + this.b + Constants.ACCEPT_TIME_SEPARATOR_SERVER + this.c + h.d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
    }
}
