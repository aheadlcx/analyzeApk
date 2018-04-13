package cn.xiaochuan.a;

import android.text.TextUtils;
import android.util.Log;
import com.meizu.cloud.pushsdk.base.BuildExt;
import com.zhihu.matisse.internal.utils.Platform;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class a {
    private static final HashMap<String, Integer> a = new HashMap();

    public static boolean a() {
        return a("ro.build.version.emui");
    }

    public static boolean b() {
        return a("ro.miui.ui.version.code") || a(Platform.RUNTIME_MIUI);
    }

    public static boolean c() {
        Integer num = (Integer) a.get("ro.flyme.ui.version");
        if (num != null) {
            if (num.intValue() == 999) {
                return true;
            }
            if (num.intValue() == -999) {
                return false;
            }
        }
        if (BuildExt.isRom().ok) {
            a.put("ro.flyme.ui.version", Integer.valueOf(999));
            return true;
        }
        a.put("ro.flyme.ui.version", Integer.valueOf(-999));
        return false;
    }

    private static boolean a(String str) {
        Integer num = (Integer) a.get(str);
        if (num != null) {
            if (num.intValue() == 999) {
                return true;
            }
            if (num.intValue() == -999) {
                return false;
            }
        }
        if (TextUtils.isEmpty(b(str))) {
            a.put(str, Integer.valueOf(-999));
            return false;
        }
        a.put(str, Integer.valueOf(999));
        return true;
    }

    private static String b(String str) {
        BufferedReader bufferedReader;
        Throwable e;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
            try {
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                if (bufferedReader == null) {
                    return readLine;
                }
                try {
                    bufferedReader.close();
                    return readLine;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return readLine;
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    Log.e("ROM", "Unable to read prop " + str, e);
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return null;
                } catch (Throwable th) {
                    e = th;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    throw e;
                }
            }
        } catch (Exception e5) {
            e = e5;
            bufferedReader = null;
            Log.e("ROM", "Unable to read prop " + str, e);
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return null;
        } catch (Throwable th2) {
            e = th2;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw e;
        }
    }
}
