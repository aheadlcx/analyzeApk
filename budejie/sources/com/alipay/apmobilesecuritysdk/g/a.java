package com.alipay.apmobilesecuritysdk.g;

import android.content.Context;
import android.os.Environment;
import com.alipay.b.a.a.d.b;
import com.alipay.b.a.a.d.c;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public final class a {
    public static String a(Context context, String str, String str2) {
        String str3 = null;
        if (!(context == null || com.alipay.b.a.a.a.a.a(str) || com.alipay.b.a.a.a.a.a(str2))) {
            try {
                String a = c.a(context, str, str2, "");
                if (!com.alipay.b.a.a.a.a.a(a)) {
                    str3 = com.alipay.b.a.a.a.a.c.b(com.alipay.b.a.a.a.a.c.a(), a);
                }
            } catch (Exception e) {
            }
        }
        return str3;
    }

    public static String a(String str, String str2) {
        String str3 = null;
        if (!(com.alipay.b.a.a.a.a.a(str) || com.alipay.b.a.a.a.a.a(str2))) {
            try {
                String a = com.alipay.b.a.a.d.a.a(str);
                if (!com.alipay.b.a.a.a.a.a(a)) {
                    a = new JSONObject(a).getString(str2);
                    if (!com.alipay.b.a.a.a.a.a(a)) {
                        str3 = com.alipay.b.a.a.a.a.c.b(com.alipay.b.a.a.a.a.c.a(), a);
                    }
                }
            } catch (Exception e) {
            }
        }
        return str3;
    }

    public static void a(Context context, String str, String str2, String str3) {
        if (!com.alipay.b.a.a.a.a.a(str) && !com.alipay.b.a.a.a.a.a(str2) && context != null) {
            try {
                String a = com.alipay.b.a.a.a.a.c.a(com.alipay.b.a.a.a.a.c.a(), str3);
                Map hashMap = new HashMap();
                hashMap.put(str2, a);
                c.a(context, str, hashMap);
            } catch (Exception e) {
            }
        }
    }

    public static void a(String str, String str2, String str3) {
        FileWriter fileWriter;
        Throwable th;
        if (!com.alipay.b.a.a.a.a.a(str) && !com.alipay.b.a.a.a.a.a(str2)) {
            try {
                String a = com.alipay.b.a.a.a.a.c.a(com.alipay.b.a.a.a.a.c.a(), str3);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(str2, a);
                String jSONObject2 = jSONObject.toString();
                try {
                    if (!com.alipay.b.a.a.a.a.a(jSONObject2)) {
                        System.setProperty(str, jSONObject2);
                    }
                } catch (Throwable th2) {
                }
                if (b.a()) {
                    a = ".SystemConfig" + File.separator + str;
                    try {
                        if (b.a()) {
                            File file = new File(Environment.getExternalStorageDirectory(), a);
                            if (!file.exists()) {
                                file.getParentFile().mkdirs();
                            }
                            File file2 = new File(file.getAbsolutePath());
                            FileWriter fileWriter2 = null;
                            try {
                                fileWriter = new FileWriter(file2, false);
                                try {
                                    fileWriter.write(jSONObject2);
                                    try {
                                        fileWriter.close();
                                    } catch (IOException e) {
                                    }
                                } catch (Exception e2) {
                                    if (fileWriter != null) {
                                        try {
                                            fileWriter.close();
                                        } catch (IOException e3) {
                                        }
                                    }
                                } catch (Throwable th3) {
                                    Throwable th4 = th3;
                                    fileWriter2 = fileWriter;
                                    th = th4;
                                    if (fileWriter2 != null) {
                                        try {
                                            fileWriter2.close();
                                        } catch (IOException e4) {
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Exception e5) {
                                fileWriter = null;
                                if (fileWriter != null) {
                                    fileWriter.close();
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                if (fileWriter2 != null) {
                                    fileWriter2.close();
                                }
                                throw th;
                            }
                        }
                    } catch (Exception e6) {
                    }
                }
            } catch (Exception e7) {
            }
        }
    }
}
