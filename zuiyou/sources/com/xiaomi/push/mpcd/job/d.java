package com.xiaomi.push.mpcd.job;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build.VERSION;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.xiaomi.channel.commonutils.misc.c;

public class d extends f {
    public d(Context context, int i) {
        super(context, i);
    }

    public int a() {
        return 6;
    }

    public String b() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Object<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
            if (!c.a(bondedDevices)) {
                int i = 0;
                for (BluetoothDevice bluetoothDevice : bondedDevices) {
                    if (i > 10) {
                        break;
                    }
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                    }
                    stringBuilder.append(bluetoothDevice.getName()).append(",").append(bluetoothDevice.getAddress()).append(",");
                    if (VERSION.SDK_INT >= 18) {
                        stringBuilder.append(bluetoothDevice.getType());
                    }
                    i++;
                }
            }
        } catch (Throwable th) {
        }
        return stringBuilder.toString();
    }

    protected boolean c() {
        return this.d.getPackageManager().checkPermission("android.permission.BLUETOOTH", this.d.getPackageName()) == 0;
    }

    public com.xiaomi.xmpush.thrift.d d() {
        return com.xiaomi.xmpush.thrift.d.Bluetooth;
    }
}
