package com.loc;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.baidu.mobstat.Config;
import com.umeng.commonsdk.proguard.ar;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.msgpack.core.MessagePack.Code;

public final class cc {
    BluetoothAdapter a = null;
    boolean b = false;
    a c = null;
    Object d = new Object();
    private ArrayList<cb> e = new ArrayList();
    private ArrayList<cb> f = new ArrayList();
    private boolean g = false;
    private Map<String, String> h = new HashMap();

    @SuppressLint({"NewApi"})
    class a implements LeScanCallback {
        final /* synthetic */ cc a;

        a(cc ccVar) {
            this.a = ccVar;
        }

        public final void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            try {
                cb a = this.a.a(bluetoothDevice, i, bArr);
                synchronized (this.a.d) {
                    int i2 = 0;
                    while (i2 < this.a.e.size()) {
                        cb cbVar = (cb) this.a.e.get(i2);
                        if (cbVar.h.equals(a.h)) {
                            this.a.e.remove(cbVar);
                            this.a.e.add(a);
                            return;
                        }
                        int i3;
                        if (de.b() - cbVar.i > 3000) {
                            this.a.e.remove(cbVar);
                            i3 = i2 - 1;
                        } else {
                            i3 = i2;
                        }
                        i2 = i3 + 1;
                    }
                    this.a.e.add(a);
                }
            } catch (Throwable th) {
                cw.a(th, "APS", "onLeScan");
            }
        }
    }

    public cc(Context context) {
        try {
            if (context.checkCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN") == 0 && context.checkCallingOrSelfPermission("android.permission.BLUETOOTH") == 0) {
                this.g = true;
            }
            if (de.c() >= 18 && this.g) {
                this.a = BluetoothAdapter.getDefaultAdapter();
            }
        } catch (Throwable th) {
        }
    }

    private cb a(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        try {
            String str;
            int i2;
            int i3;
            long b = de.b();
            String a = a(bArr);
            if (a.length() == 16) {
                str = a + "0000000000000000";
                i2 = 0;
                i3 = 0;
            } else if (a.length() == 12) {
                str = a + "00000000000000000000";
                i2 = 0;
                i3 = 0;
            } else {
                byte[] bArr2 = new byte[16];
                System.arraycopy(bArr, 9, bArr2, 0, 16);
                a = de.b(bArr2);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(a.substring(0, 32));
                str = stringBuilder.toString().toUpperCase(Locale.getDefault());
                i3 = ((bArr[25] & 255) * 256) + (bArr[26] & 255);
                i2 = ((bArr[27] & 255) * 256) + (bArr[28] & 255);
                if (i2 == 11669 || i3 == 2080 || i3 == 1796 || bluetoothDevice == null) {
                    return null;
                }
            }
            byte b2 = bArr[29];
            String address = bluetoothDevice.getAddress();
            if (!BluetoothAdapter.checkBluetoothAddress(address.toUpperCase(Locale.CHINA))) {
                return null;
            }
            byte[] bArr3 = new byte[6];
            int i4 = 0;
            for (String parseInt : address.split(Config.TRACE_TODAY_VISIT_SPLIT)) {
                bArr3[i4] = (byte) Integer.parseInt(parseInt, 16);
                i4++;
            }
            return new cb(str, bluetoothDevice.getName(), bArr3, address, i3, i2, b2, i, b);
        } catch (Throwable th) {
            cw.a(th, "APS", "createFromScanData");
            return null;
        }
    }

    private String a(byte[] bArr) {
        if (bArr == null || bArr.length <= 24) {
            return "";
        }
        int i;
        if (bArr[0] == (byte) 2 && bArr[1] == (byte) 1 && ((bArr[2] == (byte) 5 || bArr[2] == (byte) 6) && bArr[3] == (byte) 23)) {
            byte[] bArr2 = new byte[16];
            System.arraycopy(bArr, 9, bArr2, 0, 16);
            StringBuffer stringBuffer = new StringBuffer();
            int length = bArr2.length;
            for (i = 0; i < length; i++) {
                stringBuffer.append(String.format("%02X", new Object[]{Byte.valueOf(bArr2[i])}));
            }
            String stringBuffer2 = stringBuffer.toString();
            String str = (String) this.h.get(stringBuffer2);
            if (str != null) {
                return str;
            }
            bArr2 = cj.a(de.c(bArr2), new BigInteger("8021267762677846189778330391499"), new BigInteger("49549924105414102803086139689747"));
            if (bArr2 == null || bArr2.length < 8) {
                return "";
            }
            StringBuffer stringBuffer3 = new StringBuffer();
            for (i = 6; i > 0; i--) {
                stringBuffer3.append(String.format("%02X", new Object[]{Byte.valueOf(bArr2[i])}));
            }
            str = stringBuffer3.toString();
            this.h.put(stringBuffer2, str);
            return str;
        }
        if (bArr[0] == (byte) 2 && bArr[1] == (byte) 1 && bArr[2] == (byte) 6 && bArr[3] == (byte) 22 && bArr[5] == (byte) -88 && bArr[6] == (byte) 1 && bArr[7] == (byte) 32) {
            try {
                if (cj.b(de.d(bArr), new byte[]{(byte) -1, (byte) -15, (byte) 55, (byte) 33, (byte) 4, (byte) 21, ar.n, (byte) 20, (byte) -85, (byte) 9, (byte) 0, (byte) 2, (byte) -91, Code.FIXEXT2, Code.BIN16, (byte) -75}) != null) {
                    StringBuffer stringBuffer4 = new StringBuffer();
                    for (i = 0; i < 8; i++) {
                        stringBuffer4.append(String.format("%02X", new Object[]{Byte.valueOf(r2[i])}));
                    }
                    return stringBuffer4.toString();
                }
            } catch (Throwable th) {
            }
        }
        return "";
    }

    private synchronized void e() {
        List list = this.f;
        List list2 = this.e;
        list.clear();
        synchronized (this.d) {
            if (list2 != null) {
                if (list2.size() > 0) {
                    if (list2.size() > 20) {
                        Collections.sort(list2);
                    }
                    for (int i = 0; i < list2.size(); i++) {
                        list.add(list2.get(i));
                        if (i >= 25) {
                            break;
                        }
                    }
                }
            }
        }
    }

    private boolean f() {
        try {
            return this.a != null && this.a.isEnabled() && cv.A() && de.c() >= 18;
        } catch (Throwable th) {
            return false;
        }
    }

    @SuppressLint({"NewApi"})
    public final void a() {
        try {
            if (de.c() >= 18 && this.a != null) {
                if (this.c == null) {
                    this.c = new a(this);
                }
                if (this.b) {
                    this.a.stopLeScan(this.c);
                }
                this.b = false;
                this.f.clear();
                this.e.clear();
            }
        } catch (Throwable th) {
        }
    }

    @SuppressLint({"NewApi"})
    public final void b() {
        if (!this.b && f()) {
            if (this.c == null) {
                this.c = new a(this);
            }
            this.a.startLeScan(this.c);
            this.b = true;
        }
    }

    public final ArrayList<cb> c() {
        if (f()) {
            e();
        } else {
            this.f.clear();
        }
        return this.f;
    }

    public final void d() {
        a();
        this.a = null;
    }
}
