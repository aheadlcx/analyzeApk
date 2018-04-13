package qsbk.app.ye.videotools.utils;

import com.umeng.commonsdk.proguard.g;
import java.io.File;
import java.io.FileFilter;

public class SystemUtils {
    private static final FileFilter CPU_FILTER = new FileFilter() {
        public boolean accept(File file) {
            String name = file.getName();
            if (!name.startsWith(g.v)) {
                return false;
            }
            int i = 3;
            while (i < name.length()) {
                if (name.charAt(i) < '0' || name.charAt(i) > '9') {
                    return false;
                }
                i++;
            }
            return true;
        }
    };
    private static final String TAG = SystemUtils.class.getSimpleName();
    private static int mFrequency = 0;
    private static boolean mInitialized = false;
    private static int mProcessors = 0;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void init() {
        /*
        r1 = 1;
        r3 = 0;
        r5 = getNumberOfCPUCores();
        if (r5 != 0) goto L_0x0143;
    L_0x0008:
        r4 = new java.io.FileReader;	 Catch:{ IOException -> 0x008c, all -> 0x00a1 }
        r0 = "/proc/cpuinfo";
        r4.<init>(r0);	 Catch:{ IOException -> 0x008c, all -> 0x00a1 }
        r2 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x013c, all -> 0x0135 }
        r2.<init>(r4);	 Catch:{ IOException -> 0x013c, all -> 0x0135 }
    L_0x0014:
        r0 = r2.readLine();	 Catch:{ IOException -> 0x0140 }
        if (r0 == 0) goto L_0x0025;
    L_0x001a:
        r6 = "processor";
        r0 = r0.startsWith(r6);	 Catch:{ IOException -> 0x0140 }
        if (r0 == 0) goto L_0x0014;
    L_0x0022:
        r5 = r5 + 1;
        goto L_0x0014;
    L_0x0025:
        if (r2 == 0) goto L_0x002a;
    L_0x0027:
        r2.close();	 Catch:{ IOException -> 0x0104 }
    L_0x002a:
        if (r4 == 0) goto L_0x0143;
    L_0x002c:
        r4.close();	 Catch:{ IOException -> 0x0089 }
        r0 = r5;
    L_0x0030:
        if (r0 != 0) goto L_0x0033;
    L_0x0032:
        r0 = r1;
    L_0x0033:
        r2 = 0;
        r6 = "";
        r5 = new java.io.FileReader;	 Catch:{ IOException -> 0x00ae, NumberFormatException -> 0x00c4, all -> 0x00f6 }
        r4 = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";
        r5.<init>(r4);	 Catch:{ IOException -> 0x00ae, NumberFormatException -> 0x00c4, all -> 0x00f6 }
        r4 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x012d, NumberFormatException -> 0x0124, all -> 0x011b }
        r4.<init>(r5);	 Catch:{ IOException -> 0x012d, NumberFormatException -> 0x0124, all -> 0x011b }
        r3 = r4.readLine();	 Catch:{ IOException -> 0x0130, NumberFormatException -> 0x0128 }
        if (r3 == 0) goto L_0x004c;
    L_0x0048:
        r2 = java.lang.Integer.parseInt(r3);	 Catch:{ IOException -> 0x0130, NumberFormatException -> 0x012b }
    L_0x004c:
        if (r4 == 0) goto L_0x0051;
    L_0x004e:
        r4.close();	 Catch:{ IOException -> 0x010d }
    L_0x0051:
        if (r5 == 0) goto L_0x0056;
    L_0x0053:
        r5.close();	 Catch:{ IOException -> 0x0110 }
    L_0x0056:
        mInitialized = r1;
        mProcessors = r0;
        mFrequency = r2;
        r0 = "abc";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "++++++++++";
        r1 = r1.append(r2);
        r2 = mProcessors;
        r1 = r1.append(r2);
        r2 = " ";
        r1 = r1.append(r2);
        r2 = mFrequency;
        r1 = r1.append(r2);
        r2 = "++++++++++";
        r1 = r1.append(r2);
        r1 = r1.toString();
        android.util.Log.d(r0, r1);
        return;
    L_0x0089:
        r0 = move-exception;
        r0 = r5;
        goto L_0x0030;
    L_0x008c:
        r0 = move-exception;
        r2 = r3;
        r4 = r3;
    L_0x008f:
        r0.printStackTrace();	 Catch:{ all -> 0x0138 }
        if (r2 == 0) goto L_0x0097;
    L_0x0094:
        r2.close();	 Catch:{ IOException -> 0x0107 }
    L_0x0097:
        if (r4 == 0) goto L_0x0143;
    L_0x0099:
        r4.close();	 Catch:{ IOException -> 0x009e }
        r0 = r5;
        goto L_0x0030;
    L_0x009e:
        r0 = move-exception;
        r0 = r5;
        goto L_0x0030;
    L_0x00a1:
        r0 = move-exception;
        r4 = r3;
    L_0x00a3:
        if (r3 == 0) goto L_0x00a8;
    L_0x00a5:
        r3.close();	 Catch:{ IOException -> 0x0109 }
    L_0x00a8:
        if (r4 == 0) goto L_0x00ad;
    L_0x00aa:
        r4.close();	 Catch:{ IOException -> 0x010b }
    L_0x00ad:
        throw r0;
    L_0x00ae:
        r4 = move-exception;
        r4 = r3;
    L_0x00b0:
        r5 = TAG;	 Catch:{ all -> 0x0120 }
        r6 = "Could not find maximum CPU frequency!";
        android.util.Log.w(r5, r6);	 Catch:{ all -> 0x0120 }
        if (r3 == 0) goto L_0x00bc;
    L_0x00b9:
        r3.close();	 Catch:{ IOException -> 0x0113 }
    L_0x00bc:
        if (r4 == 0) goto L_0x0056;
    L_0x00be:
        r4.close();	 Catch:{ IOException -> 0x00c2 }
        goto L_0x0056;
    L_0x00c2:
        r3 = move-exception;
        goto L_0x0056;
    L_0x00c4:
        r4 = move-exception;
        r4 = r3;
        r5 = r3;
        r3 = r6;
    L_0x00c8:
        r6 = TAG;	 Catch:{ all -> 0x011e }
        r7 = "Could not parse maximum CPU frequency!";
        android.util.Log.w(r6, r7);	 Catch:{ all -> 0x011e }
        r6 = TAG;	 Catch:{ all -> 0x011e }
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x011e }
        r7.<init>();	 Catch:{ all -> 0x011e }
        r8 = "Failed to parse: ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x011e }
        r3 = r7.append(r3);	 Catch:{ all -> 0x011e }
        r3 = r3.toString();	 Catch:{ all -> 0x011e }
        android.util.Log.w(r6, r3);	 Catch:{ all -> 0x011e }
        if (r4 == 0) goto L_0x00ec;
    L_0x00e9:
        r4.close();	 Catch:{ IOException -> 0x0115 }
    L_0x00ec:
        if (r5 == 0) goto L_0x0056;
    L_0x00ee:
        r5.close();	 Catch:{ IOException -> 0x00f3 }
        goto L_0x0056;
    L_0x00f3:
        r3 = move-exception;
        goto L_0x0056;
    L_0x00f6:
        r0 = move-exception;
        r4 = r3;
        r5 = r3;
    L_0x00f9:
        if (r4 == 0) goto L_0x00fe;
    L_0x00fb:
        r4.close();	 Catch:{ IOException -> 0x0117 }
    L_0x00fe:
        if (r5 == 0) goto L_0x0103;
    L_0x0100:
        r5.close();	 Catch:{ IOException -> 0x0119 }
    L_0x0103:
        throw r0;
    L_0x0104:
        r0 = move-exception;
        goto L_0x002a;
    L_0x0107:
        r0 = move-exception;
        goto L_0x0097;
    L_0x0109:
        r1 = move-exception;
        goto L_0x00a8;
    L_0x010b:
        r1 = move-exception;
        goto L_0x00ad;
    L_0x010d:
        r3 = move-exception;
        goto L_0x0051;
    L_0x0110:
        r3 = move-exception;
        goto L_0x0056;
    L_0x0113:
        r3 = move-exception;
        goto L_0x00bc;
    L_0x0115:
        r3 = move-exception;
        goto L_0x00ec;
    L_0x0117:
        r1 = move-exception;
        goto L_0x00fe;
    L_0x0119:
        r1 = move-exception;
        goto L_0x0103;
    L_0x011b:
        r0 = move-exception;
        r4 = r3;
        goto L_0x00f9;
    L_0x011e:
        r0 = move-exception;
        goto L_0x00f9;
    L_0x0120:
        r0 = move-exception;
        r5 = r4;
        r4 = r3;
        goto L_0x00f9;
    L_0x0124:
        r4 = move-exception;
        r4 = r3;
        r3 = r6;
        goto L_0x00c8;
    L_0x0128:
        r3 = move-exception;
        r3 = r6;
        goto L_0x00c8;
    L_0x012b:
        r6 = move-exception;
        goto L_0x00c8;
    L_0x012d:
        r4 = move-exception;
        r4 = r5;
        goto L_0x00b0;
    L_0x0130:
        r3 = move-exception;
        r3 = r4;
        r4 = r5;
        goto L_0x00b0;
    L_0x0135:
        r0 = move-exception;
        goto L_0x00a3;
    L_0x0138:
        r0 = move-exception;
        r3 = r2;
        goto L_0x00a3;
    L_0x013c:
        r0 = move-exception;
        r2 = r3;
        goto L_0x008f;
    L_0x0140:
        r0 = move-exception;
        goto L_0x008f;
    L_0x0143:
        r0 = r5;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.ye.videotools.utils.SystemUtils.init():void");
    }

    private static int getNumberOfCPUCores() {
        int i = 0;
        try {
            return new File("/sys/devices/system/cpu/").listFiles(CPU_FILTER).length;
        } catch (SecurityException e) {
            e.printStackTrace();
            return i;
        } catch (NullPointerException e2) {
            e2.printStackTrace();
            return i;
        }
    }

    public static int getProcessors() {
        if (!mInitialized) {
            init();
        }
        return mProcessors;
    }

    public static int getFrequency() {
        if (!mInitialized) {
            init();
        }
        return mFrequency;
    }
}
