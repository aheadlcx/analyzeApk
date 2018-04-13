package com.tencent.smtt.utils;

import android.content.Context;
import android.provider.Settings.System;
import android.text.TextUtils;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

public class u {
    private static u a = null;
    private static Context b = null;

    private u() {
    }

    public static u a(Context context) {
        b = context.getApplicationContext();
        if (a == null) {
            a = new u();
        }
        return a;
    }

    private void b(String str) {
        IOException e;
        Exception e2;
        Throwable th;
        if (k.b()) {
            DataOutputStream dataOutputStream = null;
            try {
                File file = new File(k.c(), "ins.dat");
                if (!file.exists()) {
                    file.createNewFile();
                }
                DataOutputStream dataOutputStream2 = new DataOutputStream(new BufferedOutputStream(k.d(file)));
                try {
                    dataOutputStream2.writeUTF(str);
                    dataOutputStream2.flush();
                    if (dataOutputStream2 != null) {
                        try {
                            dataOutputStream2.close();
                        } catch (IOException e3) {
                            e = e3;
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e4) {
                    e2 = e4;
                    dataOutputStream = dataOutputStream2;
                    try {
                        e2.printStackTrace();
                        if (dataOutputStream != null) {
                            try {
                                dataOutputStream.close();
                            } catch (IOException e5) {
                                e = e5;
                                e.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (dataOutputStream != null) {
                            try {
                                dataOutputStream.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    dataOutputStream = dataOutputStream2;
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e7) {
                e2 = e7;
                e2.printStackTrace();
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
            }
        }
    }

    private void c(String str) {
        try {
            System.putString(b.getContentResolver(), "sys_setting_tbs_qb_installer", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            b(str);
            c(str);
        }
    }
}
