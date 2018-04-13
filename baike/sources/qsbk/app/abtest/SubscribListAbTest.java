package qsbk.app.abtest;

import android.content.Context;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.baidu.mobstat.StatService;
import qsbk.app.QsbkApp;

public class SubscribListAbTest {
    public static final String ATEST = "ATest";
    public static final String BTEST = "BTest";
    private static LocalBroadcastManager a = null;
    private static SubscribListAbTest b = null;

    public static synchronized SubscribListAbTest getInstance() {
        SubscribListAbTest subscribListAbTest;
        synchronized (SubscribListAbTest.class) {
            if (b == null) {
                b = new SubscribListAbTest();
                b.init();
            }
            subscribListAbTest = b;
        }
        return subscribListAbTest;
    }

    public static String getSubscripConfig() {
        return QsbkApp.indexConfig.optString("subscribe_abtest");
    }

    public static boolean isBTest() {
        return BTEST.equalsIgnoreCase(getSubscripConfig());
    }

    public static boolean isATest() {
        return ATEST.equalsIgnoreCase(getSubscripConfig());
    }

    public static boolean isInABTest() {
        return ATEST.equalsIgnoreCase(getSubscripConfig()) || BTEST.equalsIgnoreCase(getSubscripConfig());
    }

    public void init() {
        if (a == null) {
            a = LocalBroadcastManager.getInstance(QsbkApp.mContext);
            a.registerReceiver(new a(this), new IntentFilter(ArticleActionStater.KEY_STAT_ARTICLE_ACTION));
        }
    }

    public void stat(Context context, String str) {
        if (getSubscripConfig().equals(ATEST)) {
            StatService.onEvent(context, ATEST, str);
        } else {
            StatService.onEvent(context, BTEST, str);
        }
    }
}
