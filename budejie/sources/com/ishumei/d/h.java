package com.ishumei.d;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.ishumei.b.d;
import com.ishumei.f.e;
import java.io.File;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import net.vidageek.a.b.c;

public class h {
    private static h d = null;
    private Object a = null;
    private Context b = null;
    private Object c = null;

    private h() {
        try {
            this.b = d.a;
            if (this.b != null) {
                this.a = new c().a(this.b).a().a(com.ishumei.f.d.g("989a8bac868c8b9a92ac9a8d89969c9a")).a(new Object[]{com.ishumei.f.d.g("88969996")});
                if (this.a != null) {
                    this.c = new c().a(this.a).a().a(com.ishumei.f.d.g("989a8bbc9091919a9c8b969091b6919990")).a(new Object[0]);
                }
            }
        } catch (Exception e) {
        }
    }

    public static h a() {
        if (d == null) {
            synchronized (h.class) {
                if (d == null) {
                    d = new h();
                }
            }
        }
        return d;
    }

    public static String a(int i) {
        switch (i) {
            case -101:
                return IXAdSystemUtils.NT_WIFI;
            case -1:
                return "nil";
            case 0:
                return "unknown";
            case 1:
                return "2g.gprs";
            case 2:
                return "2g.edge";
            case 3:
                return "3g.umts";
            case 4:
                return "2g.cdma";
            case 5:
                return "3g.evdo_0";
            case 6:
                return "3g.evdo_a";
            case 7:
                return "2g.1xrtt";
            case 8:
                return "3g.hsdpa";
            case 9:
                return "3g.hsupa";
            case 10:
                return "3g.hspa";
            case 11:
                return "2g.iden";
            case 12:
                return "3g.evdo_b";
            case 13:
                return "4g.lte";
            case 14:
                return "3g.ehrpd";
            case 15:
                return "3g.hspap";
            default:
                return String.format("%d", new Object[]{Integer.valueOf(i)});
        }
    }

    private String j() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.b.getSystemService("connectivity")).getActiveNetworkInfo();
            int type;
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
                type = activeNetworkInfo.getType();
                if (type == 1) {
                    type = -101;
                } else {
                    if (type == 0) {
                        type = ((TelephonyManager) this.b.getSystemService("phone")).getNetworkType();
                    }
                    type = 0;
                }
                return a(type);
            }
            type = -1;
            return a(type);
        } catch (Throwable e) {
            com.ishumei.f.c.a(e);
        }
    }

    public String b() {
        String str = "";
        try {
            if (this.c == null) {
                return str;
            }
            String str2 = (String) new c().a(this.c).a().a(com.ishumei.f.d.g("989a8bacacb6bb")).a();
            if (str2 != null) {
                return str2;
            }
            try {
                return "";
            } catch (Exception e) {
                return str2;
            }
        } catch (Exception e2) {
            return str;
        }
    }

    public String c() {
        String str = "";
        try {
            if (this.c == null) {
                return str;
            }
            String str2 = (String) new c().a(this.c).a().a(com.ishumei.f.d.g("989a8bbdacacb6bb")).a();
            if (str2 != null) {
                return str2;
            }
            try {
                return "";
            } catch (Exception e) {
                return str2;
            }
        } catch (Exception e2) {
            return str;
        }
    }

    public String d() {
        String str = "";
        try {
            if (this.c == null) {
                return str;
            }
            String str2 = (String) new c().a(this.c).a().a(com.ishumei.f.d.g("989a8bb29e9cbe9b9b8d9a8c8c")).a();
            if (str2 != null) {
                return str2;
            }
            try {
                return "";
            } catch (Exception e) {
                return str2;
            }
        } catch (Exception e2) {
            return str;
        }
    }

    public String e() {
        String str = "";
        try {
            if (this.c == null) {
                return str;
            }
            String formatIpAddress = Formatter.formatIpAddress(((Integer) new c().a(this.c).a().a(com.ishumei.f.d.g("989a8bb68fbe9b9b8d9a8c8c")).a()).intValue());
            if (formatIpAddress != null) {
                return formatIpAddress;
            }
            try {
                return "";
            } catch (Exception e) {
                return formatIpAddress;
            }
        } catch (Exception e2) {
            return str;
        }
    }

    public List<String> f() {
        List<String> arrayList = new ArrayList();
        try {
            if (this.a != null) {
                for (ScanResult scanResult : (List) new c().a(this.a).a().a(com.ishumei.f.d.g("989a8bac9c9e91ad9a8c8a938b8c")).a()) {
                    String str = (String) new c().a(scanResult).b().a(com.ishumei.f.d.g("bdacacb6bb"));
                    arrayList.add(e.d(str) + "," + ((Integer) new c().a(scanResult).b().a(com.ishumei.f.d.g("939a899a93"))).intValue());
                }
            }
        } catch (Exception e) {
        }
        return arrayList;
    }

    public String g() {
        String d;
        try {
            d = e.d((String) new c().a(new c().b(com.ishumei.f.d.g("9e919b8d90969bd19d938a9a8b90908b97d1bd938a9a8b90908b97be9b9e8f8b9a8d")).a().a(com.ishumei.f.d.g("989a8bbb9a999e8a938bbe9b9e8f8b9a8d")).a()).a().a(com.ishumei.f.d.g("989a8bbe9b9b8d9a8c8c")).a());
            return d == null ? "" : d;
        } catch (Throwable e) {
            com.ishumei.f.c.a(e);
            try {
                d = e.d(e.a(new File(com.ishumei.f.d.g("d09a998cd09d938a9a8b90908b97d09d8ba09e9b9b8d"))));
                return d == null ? "" : d;
            } catch (Throwable e2) {
                com.ishumei.f.c.a(e2);
                return null;
            }
        }
    }

    public String h() {
        String str = "";
        try {
            if (this.b == null) {
                return str;
            }
            str = j();
            return str == null ? "" : str;
        } catch (Exception e) {
            return str;
        }
    }

    public List<String> i() {
        List<String> arrayList = new ArrayList();
        try {
            Object a = new c().b(com.ishumei.f.d.g("959e899ed1919a8bd1b19a8b88908d94b6918b9a8d999e9c9a")).a().a(com.ishumei.f.d.g("989a8bb19a8b88908d94b6918b9a8d999e9c9a8c")).a();
            Method a2 = new c().a(Enumeration.class).b().b(com.ishumei.f.d.g("979e8cb2908d9aba939a929a918b8c")).a();
            Method a3 = new c().a(Enumeration.class).b().b(com.ishumei.f.d.g("919a878bba939a929a918b")).a();
            while (((Boolean) a2.invoke(a, new Object[0])).booleanValue()) {
                NetworkInterface networkInterface = (NetworkInterface) a3.invoke(a, new Object[0]);
                if (!networkInterface.isLoopback()) {
                    String str = "";
                    String str2 = "";
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    String d = (hardwareAddress == null || hardwareAddress.length <= 0) ? str2 : e.d(e.a(hardwareAddress));
                    if (!(d.isEmpty() || d.equals("000000000000"))) {
                        Enumeration inetAddresses = networkInterface.getInetAddresses();
                        while (inetAddresses.hasMoreElements()) {
                            InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                            if (!inetAddress.isLoopbackAddress()) {
                                str2 = inetAddress.getHostAddress();
                                if (str2.length() < 15) {
                                    break;
                                }
                            }
                        }
                        str2 = str;
                        arrayList.add(networkInterface.getDisplayName() + "," + str2 + "," + d);
                    }
                }
            }
        } catch (Exception e) {
        }
        return arrayList;
    }
}
