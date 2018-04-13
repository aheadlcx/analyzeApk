package com.qiniu.android.dns.local;

import com.qiniu.android.dns.IResolver;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class AndroidDnsServer {
    public static InetAddress[] getByCommand() {
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("getprop").getInputStream()));
            ArrayList arrayList = new ArrayList(5);
            while (true) {
                String readLine = lineNumberReader.readLine();
                if (readLine == null) {
                    break;
                }
                int indexOf = readLine.indexOf("]: [");
                if (indexOf != -1) {
                    String substring = readLine.substring(1, indexOf);
                    readLine = readLine.substring(indexOf + 4, readLine.length() - 1);
                    if (substring.endsWith(".dns") || substring.endsWith(".dns1") || substring.endsWith(".dns2") || substring.endsWith(".dns3") || substring.endsWith(".dns4")) {
                        InetAddress byName = InetAddress.getByName(readLine);
                        if (byName != null) {
                            String hostAddress = byName.getHostAddress();
                            if (!(hostAddress == null || hostAddress.length() == 0)) {
                                arrayList.add(byName);
                            }
                        }
                    }
                }
            }
            if (arrayList.size() > 0) {
                return (InetAddress[]) arrayList.toArray(new InetAddress[arrayList.size()]);
            }
        } catch (Throwable e) {
            Logger.getLogger("AndroidDnsServer").log(Level.WARNING, "Exception in findDNSByExec", e);
        }
        return null;
    }

    public static InetAddress[] getByReflection() {
        try {
            Method method = Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class});
            ArrayList arrayList = new ArrayList(5);
            int length = new String[]{"net.dns1", "net.dns2", "net.dns3", "net.dns4"}.length;
            for (int i = 0; i < length; i++) {
                String str = (String) method.invoke(null, new Object[]{r5[i]});
                if (!(str == null || str.length() == 0)) {
                    InetAddress byName = InetAddress.getByName(str);
                    if (byName != null) {
                        String hostAddress = byName.getHostAddress();
                        if (!(hostAddress == null || hostAddress.length() == 0 || arrayList.contains(byName))) {
                            arrayList.add(byName);
                        }
                    }
                }
            }
            if (arrayList.size() > 0) {
                return (InetAddress[]) arrayList.toArray(new InetAddress[arrayList.size()]);
            }
        } catch (Throwable e) {
            Logger.getLogger("AndroidDnsServer").log(Level.WARNING, "Exception in findDNSByReflection", e);
        }
        return null;
    }

    public static IResolver defaultResolver() {
        return new a();
    }
}
