package com.huawei.hms.update.f;

import android.content.Context;
import android.content.res.Configuration;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public abstract class a {
    public static String a(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        if (configuration.locale != null) {
            String language = configuration.locale.getLanguage();
            String country = configuration.locale.getCountry();
            if (!(language == null || country == null)) {
                return language.toLowerCase(Locale.getDefault()) + '_' + country.toUpperCase(Locale.getDefault());
            }
        }
        return "";
    }

    public static String b(Context context) {
        Object a = a("ro.product.locale.region");
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        String d = d(context);
        return TextUtils.isEmpty(d) ? "" : d;
    }

    private static String d(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        if (configuration.locale != null) {
            String country = configuration.locale.getCountry();
            if (country != null) {
                return country;
            }
        }
        return "";
    }

    private static String a(String str) {
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{str});
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e2) {
        } catch (IllegalAccessException e3) {
        } catch (IllegalArgumentException e4) {
        } catch (InvocationTargetException e5) {
        } catch (ClassCastException e6) {
        }
        com.huawei.hms.support.log.a.d("UpdateUtils", "An exception occurred while reading: " + str);
        return "";
    }

    public static boolean c(Context context) {
        Object a = a("ro.product.locale.region");
        if (TextUtils.isEmpty(a)) {
            a = d(context);
            if (TextUtils.isEmpty(a)) {
                return e(context).startsWith("460") ? true : true;
            } else {
                if (AdvanceSetting.CLEAR_NOTIFICATION.equalsIgnoreCase(a)) {
                    return true;
                }
                return false;
            }
        } else if (AdvanceSetting.CLEAR_NOTIFICATION.equalsIgnoreCase(a)) {
            return true;
        } else {
            return false;
        }
    }

    private static String e(Context context) {
        Object obj;
        CharSequence charSequence = "";
        com.huawei.hms.update.f.a.a a = com.huawei.hms.update.f.a.a.a();
        if (a != null) {
            CharSequence a2;
            int b = a.b();
            if (b == -1 || 5 == a.b(b)) {
                a2 = a.a(b);
                if (TextUtils.isEmpty(a2)) {
                    a2 = a.c(b);
                }
            } else {
                a2 = charSequence;
            }
            obj = a2;
        } else {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null && 5 == telephonyManager.getSimState()) {
                obj = telephonyManager.getSimOperator();
                if (TextUtils.isEmpty(obj)) {
                    obj = telephonyManager.getSubscriberId();
                }
            }
        }
        if (TextUtils.isEmpty(obj)) {
            return "00000";
        }
        return obj.substring(0, 5);
    }
}
