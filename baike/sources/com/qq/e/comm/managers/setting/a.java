package com.qq.e.comm.managers.setting;

import android.content.Context;
import android.util.Base64;
import android.util.Pair;
import com.qq.e.comm.constants.Constants.SETTING;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

public class a {
    private JSONObject a;

    public a() {
        this.a = new JSONObject();
    }

    public a(String str) {
        this();
        GDTLogger.d("Initialize GDTAPPSetting,Json=" + str);
        if (!StringUtil.isEmpty(str)) {
            try {
                this.a = new JSONObject(str);
            } catch (Throwable e) {
                GDTLogger.e("JsonException While build GDTAPPSetting Instance from JSON", e);
            }
        }
    }

    private static Pair<String, String> a(Context context, String str) {
        File dir = context.getDir(SETTING.SETTINGDIR, 0);
        if (!dir.exists()) {
            return null;
        }
        File file = new File(dir, str + ".sig");
        File file2 = new File(dir, str + ".cfg");
        if (!file.exists() || !file2.exists()) {
            return null;
        }
        try {
            return new Pair(StringUtil.readAll(file), StringUtil.readAll(file2));
        } catch (IOException e) {
            return null;
        }
    }

    public static com.qq.e.comm.a a(Context context) {
        Pair a = a(context, SETTING.DEV_CLOUD_SETTING);
        if (a == null) {
            return null;
        }
        try {
            if (com.qq.e.comm.util.a.a().a((String) a.first, (String) a.second)) {
                return new com.qq.e.comm.a((String) a.first, new a(new String(Base64.decode((String) a.second, 0), "UTF-8")));
            }
            GDTLogger.e("verify local dev cloud setting fail");
            return null;
        } catch (Throwable th) {
            GDTLogger.e("exception while loading local dev cloud setting", th);
            return null;
        }
    }

    public static boolean a(Context context, String str, String str2) {
        return a(context, SETTING.SDK_CLOUD_SETTING, str, str2);
    }

    private static boolean a(Context context, String str, String str2, String str3) {
        if (StringUtil.isEmpty(str2) || StringUtil.isEmpty(str3)) {
            GDTLogger.e(String.format("Fail to update Cloud setting due to sig or setting is empty,name=%s\tsig=%s\tsetting=%s", new Object[]{str, str2, str3}));
            return false;
        } else if (com.qq.e.comm.util.a.a().a(str2, str3)) {
            return b(context, str, str2, str3);
        } else {
            GDTLogger.e(String.format("Fail to update Cloud setting due to sig verify fail,name=%s\tsig=%s\tsetting=%s", new Object[]{str, str2, str3}));
            return false;
        }
    }

    public static d b(Context context) {
        Pair a = a(context, SETTING.SDK_CLOUD_SETTING);
        if (a == null) {
            return null;
        }
        try {
            if (com.qq.e.comm.util.a.a().a((String) a.first, (String) a.second)) {
                return new d((String) a.first, new c(new String(Base64.decode((String) a.second, 0), "UTF-8")));
            }
            GDTLogger.e("verify local sdk cloud setting fail");
            return null;
        } catch (Throwable th) {
            GDTLogger.e("exception while loading local sdk cloud setting", th);
            return null;
        }
    }

    public static boolean b(Context context, String str, String str2) {
        return a(context, SETTING.DEV_CLOUD_SETTING, str, str2);
    }

    private static boolean b(Context context, String str, String str2, String str3) {
        FileWriter fileWriter;
        Throwable th;
        FileWriter fileWriter2 = null;
        File dir = context.getDir(SETTING.SETTINGDIR, 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, str + ".cfg");
        File file2 = new File(dir, str + ".sig");
        FileWriter fileWriter3;
        try {
            fileWriter3 = new FileWriter(file);
            try {
                fileWriter3.write(str3);
                fileWriter = new FileWriter(file2);
                try {
                    fileWriter.write(str2);
                    try {
                        fileWriter3.close();
                        fileWriter.close();
                    } catch (Exception e) {
                    }
                    return true;
                } catch (Exception e2) {
                    fileWriter2 = fileWriter3;
                    try {
                        file.delete();
                        file2.delete();
                        if (fileWriter2 != null) {
                            try {
                                fileWriter2.close();
                            } catch (Exception e3) {
                                return false;
                            }
                        }
                        if (fileWriter != null) {
                            return false;
                        }
                        fileWriter.close();
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        fileWriter3 = fileWriter2;
                        fileWriter2 = fileWriter;
                        if (fileWriter3 != null) {
                            try {
                                fileWriter3.close();
                            } catch (Exception e4) {
                                throw th;
                            }
                        }
                        if (fileWriter2 != null) {
                            fileWriter2.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileWriter2 = fileWriter;
                    if (fileWriter3 != null) {
                        fileWriter3.close();
                    }
                    if (fileWriter2 != null) {
                        fileWriter2.close();
                    }
                    throw th;
                }
            } catch (Exception e5) {
                fileWriter = null;
                fileWriter2 = fileWriter3;
                file.delete();
                file2.delete();
                if (fileWriter2 != null) {
                    fileWriter2.close();
                }
                if (fileWriter != null) {
                    return false;
                }
                fileWriter.close();
                return false;
            } catch (Throwable th4) {
                th = th4;
                if (fileWriter3 != null) {
                    fileWriter3.close();
                }
                if (fileWriter2 != null) {
                    fileWriter2.close();
                }
                throw th;
            }
        } catch (Exception e6) {
            fileWriter = null;
            file.delete();
            file2.delete();
            if (fileWriter2 != null) {
                fileWriter2.close();
            }
            if (fileWriter != null) {
                return false;
            }
            fileWriter.close();
            return false;
        } catch (Throwable th5) {
            th = th5;
            fileWriter3 = null;
            if (fileWriter3 != null) {
                fileWriter3.close();
            }
            if (fileWriter2 != null) {
                fileWriter2.close();
            }
            throw th;
        }
    }

    final Object a(String str) {
        return this.a.opt(str);
    }

    final Object a(String str, String str2) {
        JSONObject optJSONObject = this.a.optJSONObject("ps");
        optJSONObject = optJSONObject != null ? optJSONObject.optJSONObject(str2) : null;
        return optJSONObject != null ? optJSONObject.opt(str) : null;
    }

    final void a(String str, Object obj) {
        try {
            this.a.putOpt(str, obj);
        } catch (Throwable e) {
            GDTLogger.e("Exception while update setting", e);
        }
    }

    final void a(String str, Object obj, String str2) {
        try {
            JSONObject jSONObject;
            JSONObject optJSONObject = this.a.optJSONObject("ps");
            if (optJSONObject == null) {
                optJSONObject = new JSONObject();
                this.a.putOpt("ps", optJSONObject);
                jSONObject = optJSONObject;
            } else {
                jSONObject = optJSONObject;
            }
            optJSONObject = jSONObject != null ? jSONObject.optJSONObject(str2) : null;
            if (optJSONObject == null) {
                optJSONObject = new JSONObject();
                jSONObject.putOpt(str2, optJSONObject);
            }
            if (obj == null) {
                optJSONObject.remove(str);
            } else {
                optJSONObject.putOpt(str, obj);
            }
        } catch (Throwable e) {
            GDTLogger.e("Exception while update setting", e);
        }
    }
}
