package qsbk.app.utils;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import java.util.Calendar;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.core.utils.PreferenceUtils;

public class SpringFestivalUtil {
    public static Calendar BEGIN = Calendar.getInstance();
    public static Calendar END = Calendar.getInstance();
    public static final String HAMMER = "hammer";
    public static final String HAMMER_QSJX = "qsjx_hammer";
    public static final String KEY_SPRING_FESTIVAL = "spring_festival";
    public static final String KEY_SPRING_FESTIVAL_REMIND = "spring_festival_remind";

    static {
        BEGIN.set(2017, 1, 23, 0, 0, 0);
        END.set(2017, 2, 4, 0, 0, 0);
    }

    public static boolean duringSession() {
        return Calendar.getInstance().before(END);
    }

    public static void checkRemind() {
        boolean z = PreferenceUtils.instance().getBoolean(KEY_SPRING_FESTIVAL_REMIND, true);
        Object string = PreferenceUtils.instance().getString(KEY_SPRING_FESTIVAL, "");
        if (z && duringSession() && !TextUtils.isEmpty(string)) {
            Intent intent = new Intent(Constants.ACTION_SPRING_FESTIVAL_REMIND);
            intent.putExtra("data", string);
            LocalBroadcastManager.getInstance(QsbkApp.mContext).sendBroadcast(intent);
        }
    }

    public static boolean needRemind() {
        return PreferenceUtils.instance().getBoolean(KEY_SPRING_FESTIVAL_REMIND, true);
    }

    public static void neverRemind() {
        PreferenceUtils.instance().putBoolean(KEY_SPRING_FESTIVAL_REMIND, false);
        PreferenceUtils.instance().removeKey(KEY_SPRING_FESTIVAL);
    }
}
