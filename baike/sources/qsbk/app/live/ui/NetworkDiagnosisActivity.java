package qsbk.app.live.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.Proxy;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.qiniu.android.dns.DnsManager;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.local.AndroidDnsServer;
import com.r0adkll.slidr.Slidr;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Enumeration;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.DeviceUtils;
import qsbk.app.core.utils.NetworkUtils;
import qsbk.app.live.R;

public class NetworkDiagnosisActivity extends BaseActivity {
    public static final String NETWORKTYPE_INVALID = "UNKNOWN";
    public static final String NETWORKTYPE_WAP = "WAP";
    public static final String NETWORKTYPE_WIFI = "WIFI";
    private EditText a;
    private EditText b;
    private LinearLayout c;
    private ProgressBar d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        Slidr.attach(this, AppUtils.getSlidrConfig());
    }

    protected int getLayoutId() {
        return R.layout.activity_network_diagnosis;
    }

    protected void initView() {
        setUp();
        setTitle("网络诊断");
        this.a = (EditText) findViewById(R.id.tv_result);
        this.a.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.a.setOnClickListener(new fk(this));
        this.b = (EditText) findViewById(R.id.et_rtmp_addr);
        this.c = (LinearLayout) findViewById(R.id.btn_ping);
        this.c.setOnClickListener(new fl(this));
        this.d = (ProgressBar) findViewById(R.id.progress_view);
    }

    protected void initData() {
        CharSequence charSequence = null;
        Intent intent = getIntent();
        if (intent != null) {
            charSequence = intent.getStringExtra("url");
        }
        this.b.setText(charSequence);
    }

    private void a() {
        this.c.setBackgroundColor(getResources().getColor(R.color.gray));
        this.c.setEnabled(false);
        this.d.setVisibility(0);
        new fm(this, this.b.getText().toString()).start();
    }

    private void a(String str) {
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("开始诊断...\n");
        stringBuffer.append("应用版本:\t").append(DeviceUtils.getAppVersion() + "(Build " + DeviceUtils.getAPPVersionCode() + ")").append("\n");
        stringBuffer.append("应用渠道:\t").append(DeviceUtils.getChannel()).append("\n");
        stringBuffer.append("系统版本:\t").append(DeviceUtils.getSystemVersion()).append("\n");
        stringBuffer.append("手机型号:\t").append(DeviceUtils.getDeviceModel()).append("\n");
        stringBuffer.append("设备ID:\t").append(DeviceUtils.getDeviceId()).append("\n");
        stringBuffer.append("用户ID:\t").append(AppUtils.getInstance().getUserInfoProvider().getUserId()).append("\n\n");
        c(stringBuffer.toString());
        try {
            String localIpByWifi;
            String host = Uri.parse(str).getHost();
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("诊断域名:\t").append(host).append("\n\n");
            stringBuffer2.append("当前是否联网:\t").append(NetworkUtils.getInstance().isNetworkAvailable() ? "已联网" : "无网络，请检查网络设置").append("\n");
            stringBuffer2.append("当前网络类型:\t").append(getNetWorkType(this)).append("\n");
            String str2 = "127.0.0.1";
            str2 = null;
            if (NetworkUtils.getInstance().isWifiAvailable()) {
                localIpByWifi = getLocalIpByWifi(this);
                str2 = pingGateWayInWifi(this);
            } else {
                localIpByWifi = getLocalIpBy3G();
            }
            stringBuffer2.append("本地IP:\t").append(localIpByWifi).append("\n");
            stringBuffer2.append("本地网关:\t").append(str2).append("\n");
            if (NetworkUtils.getInstance().isNetworkAvailable()) {
                stringBuffer2.append("本地DNS:\t" + getLocalDns("dns1") + Constants.ACCEPT_TIME_SEPARATOR_SP + getLocalDns("dns2")).append("\n");
            } else {
                stringBuffer2.append("本地DNS:\t0.0.0.0,0.0.0.0").append("\n");
            }
            String[] query = new DnsManager(NetworkInfo.normal, new IResolver[]{AndroidDnsServer.defaultResolver()}).query(host);
            stringBuffer2.append("DNS解析结果:\t").append(Arrays.toString(query)).append("\n");
            c(stringBuffer2.toString());
            c("开始Ping域名:");
            c(b(host) + "\n");
            if (query != null && query.length > 0) {
                c("开始Ping DNS解析的IP:");
                for (String b : query) {
                    c(b(b) + "\n");
                }
            }
            String[] strArr = new String[]{"upload.qiniu.com", "www.baidu.com", "www.taobao.com"};
            c("\n开始Ping 常见的域名:");
            int length = strArr.length;
            while (i < length) {
                host = strArr[i];
                stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Ping ").append(host).append(" ...\n");
                stringBuffer2.append(b(host)).append("\n");
                c(stringBuffer2.toString());
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c("网络诊断结束\n");
    }

    private String b(String str) {
        Process exec;
        BufferedReader bufferedReader;
        String str2;
        IOException e;
        Process process;
        InterruptedException e2;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        String str3 = "";
        try {
            exec = Runtime.getRuntime().exec("/system/bin/ping -c 5 -w 15  " + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                str2 = str3;
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        str2 = str2 + readLine;
                    } catch (IOException e3) {
                        e = e3;
                        process = exec;
                    } catch (InterruptedException e4) {
                        e2 = e4;
                    }
                }
                bufferedReader.close();
                exec.waitFor();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e5) {
                    }
                }
                exec.destroy();
            } catch (IOException e6) {
                bufferedReader = null;
                e = e6;
                str2 = str3;
                process = exec;
                try {
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e7) {
                        }
                    }
                    process.destroy();
                    return str2;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader2 = bufferedReader;
                    exec = process;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Exception e8) {
                            throw th;
                        }
                    }
                    exec.destroy();
                    throw th;
                }
            } catch (InterruptedException e9) {
                bufferedReader = null;
                e2 = e9;
                str2 = str3;
                try {
                    e2.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e10) {
                        }
                    }
                    exec.destroy();
                    return str2;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    exec.destroy();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                exec.destroy();
                throw th;
            }
        } catch (IOException e62) {
            bufferedReader = null;
            IOException iOException = e62;
            str2 = str3;
            process = null;
            e = iOException;
            e.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            process.destroy();
            return str2;
        } catch (InterruptedException e92) {
            bufferedReader = null;
            exec = null;
            e2 = e92;
            str2 = str3;
            e2.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            exec.destroy();
            return str2;
        } catch (Throwable th5) {
            th = th5;
            exec = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            exec.destroy();
            throw th;
        }
        return str2;
    }

    public String traceroute(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Log.i("traceroute", "执行traceroute");
            String str2 = "/system/xbin/traceroute " + str;
            Log.i("traceroute", "执行命令前" + str);
            Process exec = Runtime.getRuntime().exec(str2);
            Log.i("traceroute", "执行命令后" + str);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            Log.i("traceroute", "执行读取" + str);
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuffer.append(readLine).append("\r\n");
            }
            Log.i("traceroute", "执行traceroute失败status" + exec.waitFor());
            bufferedReader.close();
        } catch (IOException e) {
            Log.i("traceroute", "执行traceroute失败1");
            e.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
            Log.i("traceroute", "执行traceroute失败2");
        }
        return stringBuffer.toString();
    }

    private void c(String str) {
        this.mHandler.post(new fo(this, str));
    }

    public static String getNetWorkType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return "ConnectivityManager not found";
        }
        android.net.NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return NETWORKTYPE_INVALID;
        }
        String typeName = activeNetworkInfo.getTypeName();
        if (typeName.equalsIgnoreCase(NETWORKTYPE_WIFI)) {
            return NETWORKTYPE_WIFI;
        }
        if (!typeName.equalsIgnoreCase("MOBILE")) {
            return null;
        }
        if (TextUtils.isEmpty(Proxy.getDefaultHost())) {
            return a(context);
        }
        return NETWORKTYPE_WAP;
    }

    private static String a(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "TM==null";
        }
        switch (telephonyManager.getNetworkType()) {
            case 0:
                return NETWORKTYPE_INVALID;
            case 1:
                return "2G";
            case 2:
                return "2G";
            case 3:
                return "3G";
            case 4:
                return "2G";
            case 5:
                return "3G";
            case 6:
                return "3G";
            case 7:
                return "2G";
            case 8:
                return "3G";
            case 9:
                return "3G";
            case 10:
                return "3G";
            case 11:
                return "2G";
            case 12:
                return "3G";
            case 13:
                return "4G";
            case 14:
                return "3G";
            case 15:
                return "3G";
            default:
                return "4G";
        }
    }

    public static String getMobileOperator(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "未知运营商";
        }
        String simOperator = telephonyManager.getSimOperator();
        if (simOperator != null) {
            if (simOperator.equals("46000") || simOperator.equals("46002") || simOperator.equals("46007")) {
                return "中国移动";
            }
            if (simOperator.equals("46001")) {
                return "中国联通";
            }
            if (simOperator.equals("46003")) {
                return "中国电信";
            }
        }
        return "未知运营商";
    }

    public static String getLocalIpByWifi(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (wifiManager == null) {
            return "wifiManager not found";
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return "wifiInfo not found";
        }
        int ipAddress = connectionInfo.getIpAddress();
        return String.format("%d.%d.%d.%d", new Object[]{Integer.valueOf(ipAddress & 255), Integer.valueOf((ipAddress >> 8) & 255), Integer.valueOf((ipAddress >> 16) & 255), Integer.valueOf((ipAddress >> 24) & 255)});
    }

    public static String getLocalIpBy3G() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String pingGateWayInWifi(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (wifiManager == null) {
            return "wifiManager not found";
        }
        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
        if (dhcpInfo == null) {
            return null;
        }
        int i = dhcpInfo.gateway;
        return String.format("%d.%d.%d.%d", new Object[]{Integer.valueOf(i & 255), Integer.valueOf((i >> 8) & 255), Integer.valueOf((i >> 16) & 255), Integer.valueOf((i >> 24) & 255)});
    }

    public static String getLocalDns(String str) {
        Process exec;
        BufferedReader bufferedReader;
        String str2;
        IOException e;
        Process process;
        InterruptedException e2;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        String str3 = "";
        try {
            exec = Runtime.getRuntime().exec("getprop net." + str);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                str2 = str3;
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        str2 = str2 + readLine;
                    } catch (IOException e3) {
                        e = e3;
                        process = exec;
                    } catch (InterruptedException e4) {
                        e2 = e4;
                    }
                }
                bufferedReader.close();
                exec.waitFor();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e5) {
                    }
                }
                exec.destroy();
            } catch (IOException e6) {
                bufferedReader = null;
                e = e6;
                str2 = str3;
                process = exec;
                try {
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e7) {
                        }
                    }
                    process.destroy();
                    return str2.trim();
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader2 = bufferedReader;
                    exec = process;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Exception e8) {
                            throw th;
                        }
                    }
                    exec.destroy();
                    throw th;
                }
            } catch (InterruptedException e9) {
                bufferedReader = null;
                e2 = e9;
                str2 = str3;
                try {
                    e2.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e10) {
                        }
                    }
                    exec.destroy();
                    return str2.trim();
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    exec.destroy();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                exec.destroy();
                throw th;
            }
        } catch (IOException e62) {
            bufferedReader = null;
            IOException iOException = e62;
            str2 = str3;
            process = null;
            e = iOException;
            e.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            process.destroy();
            return str2.trim();
        } catch (InterruptedException e92) {
            bufferedReader = null;
            exec = null;
            e2 = e92;
            str2 = str3;
            e2.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            exec.destroy();
            return str2.trim();
        } catch (Throwable th5) {
            th = th5;
            exec = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            exec.destroy();
            throw th;
        }
        return str2.trim();
    }
}
