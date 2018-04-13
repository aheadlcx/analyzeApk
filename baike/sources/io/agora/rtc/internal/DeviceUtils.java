package io.agora.rtc.internal;

import android.os.Build;
import android.os.Build.VERSION;
import com.umeng.commonsdk.proguard.g;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class DeviceUtils {
    private static final FileFilter CPU_FILTER = new FileFilter() {
        public boolean accept(File file) {
            String name = file.getName();
            if (!name.startsWith(g.v)) {
                return false;
            }
            for (int i = 3; i < name.length(); i++) {
                if (!Character.isDigit(name.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    };
    public static final int DEVICE_INFO_UNKNOWN = -1;
    private static final String[] H264_HW_BLACKLIST = new String[]{"SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4", "P6-C00", "HM 2A", "XT105", "XT109", "XT1060"};
    private static final String TAG = "DeviceUtils";

    public static String getDeviceId() {
        String str = Build.MANUFACTURER + MqttTopic.TOPIC_LEVEL_SEPARATOR + Build.MODEL + MqttTopic.TOPIC_LEVEL_SEPARATOR + Build.PRODUCT + MqttTopic.TOPIC_LEVEL_SEPARATOR + Build.DEVICE + MqttTopic.TOPIC_LEVEL_SEPARATOR + VERSION.SDK_INT + MqttTopic.TOPIC_LEVEL_SEPARATOR + System.getProperty("os.version");
        if (str != null) {
            return str.toLowerCase();
        }
        return str;
    }

    public static int getRecommendedEncoderType() {
        if (Arrays.asList(H264_HW_BLACKLIST).contains(Build.MODEL)) {
            Logging.w(TAG, "Model: " + Build.MODEL + " has black listed H.264 encoder.");
            return 1;
        } else if (VERSION.SDK_INT > 18) {
            return 0;
        } else {
            return 1;
        }
    }

    public static int getNumberOfCPUCores() {
        if (VERSION.SDK_INT <= 10) {
            return 1;
        }
        try {
            int coresFromFileInfo = getCoresFromFileInfo("/sys/devices/system/cpu/possible");
            if (coresFromFileInfo == -1) {
                coresFromFileInfo = getCoresFromFileInfo("/sys/devices/system/cpu/present");
            }
            if (coresFromFileInfo == -1) {
                return getCoresFromCPUFileList();
            }
            return coresFromFileInfo;
        } catch (SecurityException e) {
            return -1;
        } catch (NullPointerException e2) {
            return -1;
        }
    }

    public static String getCpuName() {
        try {
            String[] split = new BufferedReader(new FileReader("/proc/cpuinfo")).readLine().split(":\\s+", 2);
            for (int i = 0; i < split.length; i++) {
            }
            return split[1];
        } catch (Throwable e) {
            Logging.e(TAG, "getCpuName failed, no /proc/cpuinfo found in system", e);
            return null;
        } catch (Throwable e2) {
            Logging.e(TAG, "getCpuName failed,", e2);
            return null;
        }
    }

    public static String getCpuABI() {
        return Build.CPU_ABI;
    }

    private static int getCoresFromFileInfo(String str) {
        try {
            return getCoresFromFileString(new BufferedReader(new InputStreamReader(new FileInputStream(str))).readLine());
        } catch (IOException e) {
            return -1;
        }
    }

    private static int getCoresFromFileString(String str) {
        if (str == null || !str.matches("0-[\\d]+$")) {
            return -1;
        }
        return Integer.valueOf(str.substring(2)).intValue() + 1;
    }

    private static int getCoresFromCPUFileList() {
        return new File("/sys/devices/system/cpu").listFiles(CPU_FILTER).length;
    }

    public static int getCPUMaxFreqKHz() {
        int i = -1;
        for (int i2 = 0; i2 < getNumberOfCPUCores(); i2++) {
            File file = new File("/sys/devices/system/cpu/cpu" + i2 + "/cpufreq/cpuinfo_max_freq");
            int i3;
            if (file.exists()) {
                byte[] bArr = new byte[128];
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    fileInputStream.read(bArr);
                    i3 = 0;
                    while (Character.isDigit(bArr[i3]) && i3 < bArr.length) {
                        i3++;
                    }
                    Integer valueOf = Integer.valueOf(Integer.parseInt(new String(bArr, 0, i3)));
                    if (valueOf.intValue() > i) {
                        i = valueOf.intValue();
                    }
                    fileInputStream.close();
                } catch (NumberFormatException e) {
                    fileInputStream.close();
                } catch (IOException e2) {
                    return -1;
                } catch (Throwable th) {
                    fileInputStream.close();
                }
            }
        }
        if (i != -1) {
            return i;
        }
        FileInputStream fileInputStream2 = new FileInputStream("/proc/cpuinfo");
        i3 = parseFileForValue("cpu MHz", fileInputStream2) * 1000;
        if (i3 > i) {
            i = i3;
        }
        fileInputStream2.close();
        return i;
    }

    private static int parseFileForValue(String str, FileInputStream fileInputStream) {
        byte[] bArr = new byte[1024];
        try {
            int read = fileInputStream.read(bArr);
            int i = 0;
            while (i < read) {
                if (bArr[i] == (byte) 10 || i == 0) {
                    if (bArr[i] == (byte) 10) {
                        i++;
                    }
                    int i2 = i;
                    while (i2 < read) {
                        int i3 = i2 - i;
                        if (bArr[i2] != str.charAt(i3)) {
                            continue;
                            break;
                        } else if (i3 == str.length() - 1) {
                            return extractValue(bArr, i2);
                        } else {
                            i2++;
                        }
                    }
                    continue;
                }
                i++;
            }
        } catch (IOException e) {
        } catch (NumberFormatException e2) {
        }
        return -1;
    }

    private static int extractValue(byte[] bArr, int i) {
        while (i < bArr.length && bArr[i] != (byte) 10) {
            if (Character.isDigit(bArr[i])) {
                int i2 = i + 1;
                while (i2 < bArr.length && Character.isDigit(bArr[i2])) {
                    i2++;
                }
                return Integer.parseInt(new String(bArr, 0, i, i2 - i));
            }
            i++;
        }
        return -1;
    }
}
