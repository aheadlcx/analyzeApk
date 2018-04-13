package android.support.v4.os;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Locale;

@RestrictTo({Scope.LIBRARY_GROUP})
final class a {
    static Locale a(String str) {
        String[] split;
        if (str.contains(Constants.ACCEPT_TIME_SEPARATOR_SERVER)) {
            split = str.split(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
            if (split.length > 2) {
                return new Locale(split[0], split[1], split[2]);
            }
            if (split.length > 1) {
                return new Locale(split[0], split[1]);
            }
            if (split.length == 1) {
                return new Locale(split[0]);
            }
        } else if (!str.contains("_")) {
            return new Locale(str);
        } else {
            split = str.split("_");
            if (split.length > 2) {
                return new Locale(split[0], split[1], split[2]);
            }
            if (split.length > 1) {
                return new Locale(split[0], split[1]);
            }
            if (split.length == 1) {
                return new Locale(split[0]);
            }
        }
        throw new IllegalArgumentException("Can not parse language tag: [" + str + "]");
    }

    static String a(Locale locale) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(locale.getLanguage());
        String country = locale.getCountry();
        if (!(country == null || country.isEmpty())) {
            stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
            stringBuilder.append(locale.getCountry());
        }
        return stringBuilder.toString();
    }
}
