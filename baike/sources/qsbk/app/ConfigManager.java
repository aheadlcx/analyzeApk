package qsbk.app;

import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import java.io.IOException;
import java.util.Properties;
import qsbk.app.utils.LogUtil;

public class ConfigManager {
    public static String KEY_BUILD_VERSION = "build_version";
    public static String KEY_CHANNEL = "channel";
    public static String KEY_DEBUG = "debug";
    public static String KEY_LOTTERY_DAY = "lottery_day";
    public static String KEY_LOTTERY_URL = "lottery_url";
    public static String KEY_PROMOTE = "promote_channels";
    public static String KEY_SHOW_GUIDE = "showGuide";
    public static String KEY_TEST_CAFE_ZIP_URL = "test_cafe_zip_url";
    public static final String TEST_ONLY_CHANNEL = "130";
    private static ConfigManager a = null;
    public static String sChannel = null;
    private Properties b = new Properties();

    private ConfigManager() {
        a();
        LogUtil.d("PropertyManager:properties:" + this.b.toString());
    }

    public static ConfigManager getInstance() {
        if (a == null) {
            a = new ConfigManager();
        }
        return a;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getChannelFromZipFile(android.content.Context r6) {
        /*
        r0 = r6.getApplicationInfo();
        r0 = r0.sourceDir;
        r3 = "";
        r1 = 0;
        r2 = new java.util.zip.ZipFile;	 Catch:{ IOException -> 0x006d, all -> 0x007f }
        r2.<init>(r0);	 Catch:{ IOException -> 0x006d, all -> 0x007f }
        r1 = r2.entries();	 Catch:{ IOException -> 0x0091 }
        r4 = new qsbk.app.utils.TimeDelta;	 Catch:{ IOException -> 0x0091 }
        r4.<init>();	 Catch:{ IOException -> 0x0091 }
    L_0x0017:
        r0 = r1.hasMoreElements();	 Catch:{ IOException -> 0x0091 }
        if (r0 == 0) goto L_0x0097;
    L_0x001d:
        r0 = r1.nextElement();	 Catch:{ IOException -> 0x0091 }
        r0 = (java.util.zip.ZipEntry) r0;	 Catch:{ IOException -> 0x0091 }
        r0 = r0.getName();	 Catch:{ IOException -> 0x0091 }
        r5 = "META-INF/qbchannel_";
        r5 = r0.startsWith(r5);	 Catch:{ IOException -> 0x0091 }
        if (r5 == 0) goto L_0x0017;
    L_0x002f:
        r1 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0095 }
        r1.<init>();	 Catch:{ IOException -> 0x0095 }
        r3 = "get channel td:";
        r1 = r1.append(r3);	 Catch:{ IOException -> 0x0095 }
        r4 = r4.getDelta();	 Catch:{ IOException -> 0x0095 }
        r1 = r1.append(r4);	 Catch:{ IOException -> 0x0095 }
        r1 = r1.toString();	 Catch:{ IOException -> 0x0095 }
        qsbk.app.utils.LogUtil.d(r1);	 Catch:{ IOException -> 0x0095 }
        if (r2 == 0) goto L_0x004e;
    L_0x004b:
        r2.close();	 Catch:{ IOException -> 0x0068 }
    L_0x004e:
        r1 = "_";
        r1 = r0.split(r1);
        if (r1 == 0) goto L_0x008c;
    L_0x0056:
        r2 = r1.length;
        r3 = 2;
        if (r2 < r3) goto L_0x008c;
    L_0x005a:
        r2 = 0;
        r1 = r1[r2];
        r1 = r1.length();
        r1 = r1 + 1;
        r0 = r0.substring(r1);
    L_0x0067:
        return r0;
    L_0x0068:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x004e;
    L_0x006d:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        r0 = r3;
    L_0x0071:
        r1.printStackTrace();	 Catch:{ all -> 0x008f }
        if (r2 == 0) goto L_0x004e;
    L_0x0076:
        r2.close();	 Catch:{ IOException -> 0x007a }
        goto L_0x004e;
    L_0x007a:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x004e;
    L_0x007f:
        r0 = move-exception;
        r2 = r1;
    L_0x0081:
        if (r2 == 0) goto L_0x0086;
    L_0x0083:
        r2.close();	 Catch:{ IOException -> 0x0087 }
    L_0x0086:
        throw r0;
    L_0x0087:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0086;
    L_0x008c:
        r0 = "";
        goto L_0x0067;
    L_0x008f:
        r0 = move-exception;
        goto L_0x0081;
    L_0x0091:
        r0 = move-exception;
        r1 = r0;
        r0 = r3;
        goto L_0x0071;
    L_0x0095:
        r1 = move-exception;
        goto L_0x0071;
    L_0x0097:
        r0 = r3;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.ConfigManager.getChannelFromZipFile(android.content.Context):java.lang.String");
    }

    public String getChannel() {
        if (!TextUtils.isEmpty(sChannel)) {
            return sChannel;
        }
        String channelFromZipFile = getChannelFromZipFile(QsbkApp.mContext);
        if (TextUtils.isEmpty(channelFromZipFile)) {
            channelFromZipFile = getConfig(KEY_CHANNEL, "1");
        }
        sChannel = channelFromZipFile;
        return sChannel;
    }

    public boolean isPromoteChannel() {
        String channel = getInstance().getChannel();
        Object config = getInstance().getConfig(KEY_PROMOTE, "");
        if (TextUtils.isEmpty(config)) {
            return false;
        }
        String[] split = config.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        for (Object equals : split) {
            if (channel.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTestOnlyChannel() {
        return TEST_ONLY_CHANNEL.equals(getChannel());
    }

    public boolean isInTestMode() {
        return Constants.isTestMode;
    }

    public boolean isGoolePlayChannel() {
        return "2".equals(getChannel());
    }

    public boolean isVideoPublishDisabled() {
        return !TextUtils.isEmpty(getInstance().getConfig("disable_video_create"));
    }

    public String getConfig(String str, String str2) {
        return this.b.getProperty(str, str2);
    }

    public String getConfig(String str) {
        return this.b.getProperty(str);
    }

    private void a() {
        try {
            this.b.load(QsbkApp.mContext.getResources().getAssets().open("all_cfg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
