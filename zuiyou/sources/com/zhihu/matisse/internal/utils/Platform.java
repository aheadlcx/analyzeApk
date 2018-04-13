package com.zhihu.matisse.internal.utils;

import android.os.Build.VERSION;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Platform {
    public static final String COLOROS = "coloros";
    public static final String EMUI = "EMUI";
    public static final String FLYME = "Flyme";
    public static final String MIUI = "miui";
    public static final String RUNTIME_DISPLAY = "ro.build.display.id";
    public static final String RUNTIME_MIUI = "ro.miui.ui.version.name";
    public static final String RUNTIME_OPPO = "ro.build.version.opporom";
    public static final String RUNTIME_VIVO = "ro.product.manufacturer";
    public static final String UNKNOW = "unknow";
    public static final String VIVO = "vivo";

    public static boolean hasICS() {
        return VERSION.SDK_INT >= 14;
    }

    public static boolean hasKitKat() {
        return VERSION.SDK_INT >= 19;
    }

    public static boolean isMIUI() {
        return MIUI.equalsIgnoreCase(getRomProperty(RUNTIME_MIUI));
    }

    public static String getRomInfo() {
        if (!TextUtils.isEmpty(getRomProperty(RUNTIME_MIUI))) {
            return MIUI;
        }
        if (!TextUtils.isEmpty(getRomProperty(RUNTIME_OPPO))) {
            return COLOROS;
        }
        if (getRomProperty(RUNTIME_DISPLAY).contains(EMUI)) {
            return EMUI;
        }
        if (getRomProperty(RUNTIME_DISPLAY).contains(FLYME)) {
            return FLYME;
        }
        if (getRomProperty(RUNTIME_VIVO).contains(VIVO)) {
            return VIVO;
        }
        return UNKNOW;
    }

    private static String getRomProperty(String str) {
        Process exec;
        BufferedReader bufferedReader;
        IOException e;
        BufferedReader bufferedReader2;
        Throwable th;
        Process process = null;
        String str2 = "";
        try {
            exec = Runtime.getRuntime().exec("getprop " + str);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()), 1024);
            } catch (IOException e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (exec != null) {
                        exec.destroy();
                    }
                    return str2;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader2;
                    process = exec;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    if (process != null) {
                        process.destroy();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                process = exec;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (process != null) {
                    process.destroy();
                }
                throw th;
            }
            try {
                str2 = bufferedReader.readLine();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e322) {
                        e322.printStackTrace();
                    }
                }
                if (exec != null) {
                    exec.destroy();
                }
            } catch (IOException e4) {
                e322 = e4;
                bufferedReader2 = bufferedReader;
                e322.printStackTrace();
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (exec != null) {
                    exec.destroy();
                }
                return str2;
            } catch (Throwable th4) {
                th = th4;
                process = exec;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (process != null) {
                    process.destroy();
                }
                throw th;
            }
        } catch (IOException e5) {
            e322 = e5;
            exec = null;
            e322.printStackTrace();
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (exec != null) {
                exec.destroy();
            }
            return str2;
        } catch (Throwable th5) {
            th = th5;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (process != null) {
                process.destroy();
            }
            throw th;
        }
        return str2;
    }
}
