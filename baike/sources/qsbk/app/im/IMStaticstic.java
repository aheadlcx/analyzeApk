package qsbk.app.im;

import com.baidu.mobstat.StatService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import qsbk.app.live.ui.NetworkDiagnosisActivity;
import qsbk.app.utils.AppContext;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.image.issue.Logger;

public final class IMStaticstic {
    private static final String a = IMStaticstic.class.getSimpleName();
    private static AtomicLong b = new AtomicLong(System.currentTimeMillis());
    private static AtomicLong c = new AtomicLong(System.currentTimeMillis());
    private static AtomicBoolean d = new AtomicBoolean(false);

    private IMStaticstic() {
    }

    public static synchronized void onConnectStart() {
        synchronized (IMStaticstic.class) {
            b.set(System.currentTimeMillis());
        }
    }

    public static synchronized void onAccessStart() {
        synchronized (IMStaticstic.class) {
            d.set(false);
            c.set(System.currentTimeMillis());
        }
    }

    private static long a(AtomicLong atomicLong) {
        return Math.min(Math.max(atomicLong == null ? 0 : System.currentTimeMillis() - atomicLong.get(), 0) * 1000, 2147483647L);
    }

    public static synchronized void onConnectSuccess() {
        synchronized (IMStaticstic.class) {
            try {
                long a = a(b);
                long a2 = a(c);
                if (isWifi()) {
                    DebugUtil.debug(a, "WIFI_ON");
                    StatService.onEventDuration(AppContext.getContext(), "IM_CONNECT_COST", NetworkDiagnosisActivity.NETWORKTYPE_WIFI, (long) ((int) a));
                    StatService.onEvent(AppContext.getContext(), "IM_CONNECTIVITY", "WIFI_ON");
                    Logger.getInstance().debug(a, "onConnectSuccess", String.format("%s : %s : %s", new Object[]{"IM_CONNECT_COST", NetworkDiagnosisActivity.NETWORKTYPE_WIFI, Integer.valueOf((int) a)}));
                    Logger.getInstance().debug(a, "onConnectSuccess", String.format("%s : %s", new Object[]{"IM_CONNECTIVITY", "WIFI_ON"}));
                    if (d.get()) {
                        StatService.onEventDuration(AppContext.getContext(), "IM_CONNECT_ONETIME_SUCCESS", NetworkDiagnosisActivity.NETWORKTYPE_WIFI, (long) ((int) a2));
                        Logger.getInstance().debug(a, "onConnectSuccess", String.format("%s : %s : %s", new Object[]{"IM_CONNECT_ONETIME_SUCCESS", NetworkDiagnosisActivity.NETWORKTYPE_WIFI, Integer.valueOf((int) a2)}));
                    }
                } else {
                    DebugUtil.debug(a, "GPRS_ON");
                    StatService.onEventDuration(AppContext.getContext(), "IM_CONNECT_COST", "GPRS", (long) ((int) a));
                    StatService.onEvent(AppContext.getContext(), "IM_CONNECTIVITY", "GPRS_ON");
                    Logger.getInstance().debug(a, "onConnectSuccess", String.format("%s : %s : %s", new Object[]{"IM_CONNECT_COST", "GPRS", Integer.valueOf((int) a)}));
                    Logger.getInstance().debug(a, "onConnectSuccess", String.format("%s : %s", new Object[]{"IM_CONNECTIVITY", "GPRS_ON"}));
                    if (d.get()) {
                        StatService.onEventDuration(AppContext.getContext(), "IM_CONNECT_ONETIME_SUCCESS", "GPRS", (long) ((int) a2));
                        Logger.getInstance().debug(a, "onConnectSuccess", String.format("%s : %s : %s", new Object[]{"IM_CONNECT_ONETIME_SUCCESS", "GPRS", Integer.valueOf((int) a2)}));
                    }
                }
                d.set(false);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void onAccessFailed() {
        d.set(false);
        if (isNetworkConnected()) {
            try {
                StatService.onEvent(AppContext.getContext(), "IM_ACCESS", "FAILURE");
                Logger.getInstance().debug(a, "onAccessFailed", String.format("%s : %s", new Object[]{"IM_ACCESS", "FAILURE"}));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void onAccessSuccess() {
        d.set(true);
        try {
            StatService.onEvent(AppContext.getContext(), "IM_ACCESS", "SUCCESS");
            Logger.getInstance().debug(a, "onAccessSuccess", String.format("%s : %s", new Object[]{"IM_ACCESS", "SUCCESS"}));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void onLostConnectAccidently() {
        if (isNetworkConnected()) {
            try {
                StatService.onEvent(AppContext.getContext(), "IM_LOST_CONNECT_ACCIDENT", "");
                Logger.getInstance().debug(a, "onLostConnectAccidently", String.format("%s", new Object[]{"IM_LOST_CONNECT_ACCIDENT"}));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void onDataMigrationFailed() {
        if (isNetworkConnected()) {
            try {
                StatService.onEvent(AppContext.getContext(), "IM_DATA_MIGRATION", "FAILURE");
                Logger.getInstance().debug(a, "onDataMigrationFailed", String.format("%s : %s", new Object[]{"IM_DATA_MIGRATION", "FAILURE"}));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void onDataMigrationSuccess() {
        try {
            StatService.onEvent(AppContext.getContext(), "IM_DATA_MIGRATION", "SUCCESS");
            Logger.getInstance().debug(a, "onDataMigrationSuccess", String.format("%s : %s", new Object[]{"IM_DATA_MIGRATION", "SUCCESS"}));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void onConnectFailed() {
        if (isNetworkConnected()) {
            try {
                if (isWifi()) {
                    DebugUtil.debug(a, "WIFI_OFF");
                    StatService.onEvent(AppContext.getContext(), "IM_CONNECTIVITY", "WIFI_OFF");
                    Logger.getInstance().debug(a, "onConnectFailed", String.format("%s : %s", new Object[]{"IM_CONNECTIVITY", "WIFI_OFF"}));
                    return;
                }
                DebugUtil.debug(a, "GPRS_OFF");
                StatService.onEvent(AppContext.getContext(), "IM_CONNECTIVITY", "GPRS_OFF");
                Logger.getInstance().debug(a, "onConnectFailed", String.format("%s : %s", new Object[]{"IM_CONNECTIVITY", "GPRS_OFF"}));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static boolean isNetworkConnected() {
        return HttpUtils.isNetworkConnected(AppContext.getContext());
    }

    public static boolean isWifi() {
        return HttpUtils.isWifi(AppContext.getContext());
    }
}
