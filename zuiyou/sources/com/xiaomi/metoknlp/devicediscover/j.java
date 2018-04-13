package com.xiaomi.metoknlp.devicediscover;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

class j {
    public static DhcpInfo a(Context context) {
        if (context == null) {
            return null;
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (wifiManager == null || !wifiManager.isWifiEnabled()) {
            return null;
        }
        DhcpInfo dhcpInfo;
        try {
            dhcpInfo = context.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", context.getPackageName()) == 0 ? wifiManager.getDhcpInfo() : null;
        } catch (Exception e) {
            dhcpInfo = null;
        }
        return dhcpInfo;
    }

    public static String a(Context context, int i) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null || !wifiManager.isWifiEnabled()) {
                return null;
            }
            WifiInfo connectionInfo;
            try {
                connectionInfo = context.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", context.getPackageName()) == 0 ? wifiManager.getConnectionInfo() : null;
            } catch (Exception e) {
                connectionInfo = null;
            }
            return connectionInfo != null ? i == 0 ? connectionInfo.getMacAddress() : i == 1 ? connectionInfo.getBSSID() : i == 2 ? a(connectionInfo.getSSID()) : null : null;
        } catch (Exception e2) {
            return null;
        }
    }

    private static String a(String str) {
        return (str.startsWith("\"") && str.endsWith("\"")) ? str.substring(1, str.length() - 1) : str;
    }

    public static InetAddress a(int i) {
        try {
            return InetAddress.getByAddress(new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)});
        } catch (UnknownHostException e) {
            throw new AssertionError();
        }
    }

    public static void a(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences("devicediscover", 0).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static String b(Context context) {
        String str = null;
        try {
            DhcpInfo a = a(context);
            if (a != null) {
                str = a(a.gateway).getHostAddress();
            }
        } catch (Exception e) {
        }
        return str;
    }

    public static String b(Context context, String str, String str2) {
        return context.getSharedPreferences("devicediscover", 0).getString(str, str2);
    }

    public static String c(Context context) {
        String str = null;
        try {
            DhcpInfo a = a(context);
            if (a != null) {
                str = a(a.serverAddress).getHostAddress();
            }
        } catch (Exception e) {
        }
        return str;
    }

    public static String d(Context context) {
        FileReader fileReader;
        Throwable th;
        String a = a(context, 0);
        if (a == null || a.isEmpty() || "02:00:00:00:00:00".equals(a)) {
            try {
                char[] cArr = new char[1024];
                fileReader = new FileReader("/sys/class/net/wlan0/address");
                try {
                    a = new String(cArr, 0, fileReader.read(cArr, 0, 1024)).trim();
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Exception e) {
                        }
                    }
                } catch (FileNotFoundException e2) {
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Exception e3) {
                        }
                    }
                    return a;
                } catch (Exception e4) {
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Exception e5) {
                        }
                    }
                    return a;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Exception e6) {
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e7) {
                fileReader = null;
                if (fileReader != null) {
                    fileReader.close();
                }
                return a;
            } catch (Exception e8) {
                fileReader = null;
                if (fileReader != null) {
                    fileReader.close();
                }
                return a;
            } catch (Throwable th3) {
                th = th3;
                fileReader = null;
                if (fileReader != null) {
                    fileReader.close();
                }
                throw th;
            }
        }
        return a;
    }
}
