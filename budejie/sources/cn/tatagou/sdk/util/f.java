package cn.tatagou.sdk.util;

import com.umeng.analytics.a;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class f {
    public static boolean judgmentDate(String str, String str2) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        if (simpleDateFormat.parse(str2).getTime() - simpleDateFormat.parse(str).getTime() <= 0) {
            return false;
        }
        return true;
    }

    public static String unixTS2Time(long j, Locale locale) {
        return unixTS2Time(j, locale, null);
    }

    public static int daysBetween(String str, Date date) {
        long j = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = simpleDateFormat.parse(str);
            if (parse != null) {
                j = Math.abs(simpleDateFormat.parse(simpleDateFormat.format(date)).getTime() - parse.getTime()) / a.i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.parseInt(String.valueOf(j));
    }

    public static String unixTS2Time(long j, Locale locale, String str) {
        if (p.isEmpty(str)) {
            str = "yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(str, locale).format(new Date(1000 * j));
    }

    public static long unixTS2TimeTamp(int i, String str) {
        if (p.isEmpty(str)) {
            str = "yyyy-MM-dd";
        }
        long j = 0;
        try {
            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date());
            instance.add(7, -i);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.CANADA);
            return simpleDateFormat.parse(simpleDateFormat.format(instance.getTime())).getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
            return j;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean deffDate(java.lang.String r8) {
        /*
        r6 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r1 = 1;
        r0 = 0;
        r2 = cn.tatagou.sdk.util.p.isEmpty(r8);
        if (r2 == 0) goto L_0x000b;
    L_0x000a:
        return r1;
    L_0x000b:
        r2 = new java.text.SimpleDateFormat;
        r3 = "yyyy-MM-dd";
        r4 = java.util.Locale.CANADA;
        r2.<init>(r3, r4);
        r4 = java.lang.Long.parseLong(r8);	 Catch:{ Exception -> 0x0058 }
        r4 = r4 * r6;
        r3 = new java.util.Date;	 Catch:{ Exception -> 0x0058 }
        r3.<init>();	 Catch:{ Exception -> 0x0058 }
        r3 = r2.format(r3);	 Catch:{ Exception -> 0x0058 }
        r6 = new java.util.Date;	 Catch:{ Exception -> 0x0058 }
        r6.<init>(r4);	 Catch:{ Exception -> 0x0058 }
        r6 = r2.format(r6);	 Catch:{ Exception -> 0x0058 }
        r6 = r2.parse(r6);	 Catch:{ Exception -> 0x0058 }
        r2 = r2.parse(r3);	 Catch:{ Exception -> 0x0058 }
        r2 = r2.getTime();	 Catch:{ Exception -> 0x0058 }
        r6 = r6.getTime();	 Catch:{ Exception -> 0x0058 }
        r2 = r2 - r6;
        r6 = 0;
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 != 0) goto L_0x0044;
    L_0x0042:
        r1 = r0;
        goto L_0x000a;
    L_0x0044:
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0058 }
        r2 = r2 - r4;
        r2 = java.lang.Math.abs(r2);	 Catch:{ Exception -> 0x0058 }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r4 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 < 0) goto L_0x0042;
    L_0x0056:
        r0 = r1;
        goto L_0x0042;
    L_0x0058:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0056;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.tatagou.sdk.util.f.deffDate(java.lang.String):boolean");
    }

    public static long dtToUnixTS(String str, Locale locale) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale).parse(str).getTime() / 1000;
    }

    public static String getNowTimeYMD() {
        return getNowTimeYMD(null);
    }

    public static String getNowTimeYMD(String str) {
        if (p.isEmpty(str)) {
            str = "yyyy-MM-dd";
        }
        return new SimpleDateFormat(str).format(new Date());
    }

    public static long subDate(String str, String str2, String str3) {
        long j = 0;
        if (p.isEmpty(str) || p.isEmpty(str2)) {
            return j;
        }
        if (str3 == null) {
            str3 = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str3);
        try {
            return simpleDateFormat.parse(str2).getTime() - simpleDateFormat.parse(str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return j;
        }
    }

    public static long subTimeDay(String str, String str2, String str3) {
        return subDate(str, str2, str3) / a.i;
    }

    public static Date str2Date(String str, String str2) {
        if (str2 == null) {
            str2 = "yyyy-MM-dd";
        }
        Date date = null;
        try {
            date = new SimpleDateFormat(str2).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
