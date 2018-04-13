package android.support.v4.os;

import android.os.Build.VERSION;
import android.os.LocaleList;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.Size;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Locale;

public final class LocaleListCompat {
    static final c a;
    private static final LocaleListCompat b = new LocaleListCompat();

    @RequiresApi(24)
    static class a implements c {
        private LocaleList a = new LocaleList(new Locale[0]);

        a() {
        }

        public void setLocaleList(@NonNull Locale... localeArr) {
            this.a = new LocaleList(localeArr);
        }

        public Object getLocaleList() {
            return this.a;
        }

        public Locale get(int i) {
            return this.a.get(i);
        }

        public boolean isEmpty() {
            return this.a.isEmpty();
        }

        @IntRange(from = 0)
        public int size() {
            return this.a.size();
        }

        @IntRange(from = -1)
        public int indexOf(Locale locale) {
            return this.a.indexOf(locale);
        }

        public boolean equals(Object obj) {
            return this.a.equals(((LocaleListCompat) obj).unwrap());
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public String toString() {
            return this.a.toString();
        }

        public String toLanguageTags() {
            return this.a.toLanguageTags();
        }

        @Nullable
        public Locale getFirstMatch(String[] strArr) {
            if (this.a != null) {
                return this.a.getFirstMatch(strArr);
            }
            return null;
        }
    }

    static class b implements c {
        private b a = new b(new Locale[0]);

        b() {
        }

        public void setLocaleList(@NonNull Locale... localeArr) {
            this.a = new b(localeArr);
        }

        public Object getLocaleList() {
            return this.a;
        }

        public Locale get(int i) {
            return this.a.a(i);
        }

        public boolean isEmpty() {
            return this.a.a();
        }

        @IntRange(from = 0)
        public int size() {
            return this.a.b();
        }

        @IntRange(from = -1)
        public int indexOf(Locale locale) {
            return this.a.a(locale);
        }

        public boolean equals(Object obj) {
            return this.a.equals(((LocaleListCompat) obj).unwrap());
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public String toString() {
            return this.a.toString();
        }

        public String toLanguageTags() {
            return this.a.c();
        }

        @Nullable
        public Locale getFirstMatch(String[] strArr) {
            if (this.a != null) {
                return this.a.a(strArr);
            }
            return null;
        }
    }

    static {
        if (VERSION.SDK_INT >= 24) {
            a = new a();
        } else {
            a = new b();
        }
    }

    private LocaleListCompat() {
    }

    @RequiresApi(24)
    public static LocaleListCompat wrap(Object obj) {
        LocaleListCompat localeListCompat = new LocaleListCompat();
        if (obj instanceof LocaleList) {
            localeListCompat.a((LocaleList) obj);
        }
        return localeListCompat;
    }

    @Nullable
    public Object unwrap() {
        return a.getLocaleList();
    }

    public static LocaleListCompat create(@NonNull Locale... localeArr) {
        LocaleListCompat localeListCompat = new LocaleListCompat();
        localeListCompat.a(localeArr);
        return localeListCompat;
    }

    public Locale get(int i) {
        return a.get(i);
    }

    public boolean isEmpty() {
        return a.isEmpty();
    }

    @IntRange(from = 0)
    public int size() {
        return a.size();
    }

    @IntRange(from = -1)
    public int indexOf(Locale locale) {
        return a.indexOf(locale);
    }

    @NonNull
    public String toLanguageTags() {
        return a.toLanguageTags();
    }

    public Locale getFirstMatch(String[] strArr) {
        return a.getFirstMatch(strArr);
    }

    @NonNull
    public static LocaleListCompat getEmptyLocaleList() {
        return b;
    }

    @NonNull
    public static LocaleListCompat forLanguageTags(@Nullable String str) {
        if (str == null || str.isEmpty()) {
            return getEmptyLocaleList();
        }
        String[] split = str.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        Locale[] localeArr = new Locale[split.length];
        for (int i = 0; i < localeArr.length; i++) {
            Locale forLanguageTag;
            if (VERSION.SDK_INT >= 21) {
                forLanguageTag = Locale.forLanguageTag(split[i]);
            } else {
                forLanguageTag = a.a(split[i]);
            }
            localeArr[i] = forLanguageTag;
        }
        LocaleListCompat localeListCompat = new LocaleListCompat();
        localeListCompat.a(localeArr);
        return localeListCompat;
    }

    @Size(min = 1)
    @NonNull
    public static LocaleListCompat getAdjustedDefault() {
        if (VERSION.SDK_INT >= 24) {
            return wrap(LocaleList.getAdjustedDefault());
        }
        return create(Locale.getDefault());
    }

    @Size(min = 1)
    @NonNull
    public static LocaleListCompat getDefault() {
        if (VERSION.SDK_INT >= 24) {
            return wrap(LocaleList.getDefault());
        }
        return create(Locale.getDefault());
    }

    public boolean equals(Object obj) {
        return a.equals(obj);
    }

    public int hashCode() {
        return a.hashCode();
    }

    public String toString() {
        return a.toString();
    }

    @RequiresApi(24)
    private void a(LocaleList localeList) {
        int size = localeList.size();
        if (size > 0) {
            Locale[] localeArr = new Locale[size];
            for (int i = 0; i < size; i++) {
                localeArr[i] = localeList.get(i);
            }
            a.setLocaleList(localeArr);
        }
    }

    private void a(Locale... localeArr) {
        a.setLocaleList(localeArr);
    }
}
