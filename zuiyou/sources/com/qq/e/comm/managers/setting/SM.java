package com.qq.e.comm.managers.setting;

import android.content.Context;
import android.util.Base64;
import com.qq.e.comm.a;
import com.qq.e.comm.constants.Constants.SETTING;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import java.io.File;

public class SM {
    private a a;
    private String b;
    private a c;
    private a d;
    private String e;
    private c f;
    private c g;
    private String h;
    private String i = "";
    private Context j;

    public SM(Context context) {
        this.j = context;
        this.a = new a();
        this.d = new a();
        this.g = new b();
        try {
            this.h = StringUtil.readAll(new File(this.j.getDir(SETTING.SETTINGDIR, 0), SETTING.SUID_FILE));
        } catch (Throwable th) {
            this.h = null;
            GDTLogger.e("IO Exception while loading suid");
        }
        a();
        b();
    }

    private void a() {
        d b = a.b(this.j);
        if (b != null) {
            this.e = b.a();
            this.f = b.b();
            return;
        }
        GDTLogger.d("Load Local SDK Cloud setting fail");
    }

    private void b() {
        a a = a.a(this.j);
        if (a != null) {
            this.c = a.b();
            this.b = a.a();
            return;
        }
        GDTLogger.d("Load Local DEV Cloud setting fail");
    }

    public Object get(String str) {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        try {
            Object a;
            if (this.a.a(str) != null) {
                a = this.a.a(str);
                if (a != null) {
                    return a;
                }
            }
            if (this.c != null) {
                a = this.c.a(str);
                if (a != null) {
                    return a;
                }
            }
            if (this.d != null) {
                a = this.d.a(str);
                if (a != null) {
                    return a;
                }
            }
            if (this.f != null) {
                a = this.f.a(str);
                if (a != null) {
                    return a;
                }
            }
            return this.g != null ? this.g.a(str) : null;
        } catch (Throwable th) {
            GDTLogger.report("Exception in settingManager.get Setting for key:" + str, th);
            return null;
        }
    }

    public String getDevCloudSettingSig() {
        return this.b;
    }

    public Object getForPlacement(String str, String str2) {
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(str2)) {
            return null;
        }
        try {
            Object a;
            if (this.a != null) {
                a = this.a.a(str, str2);
                if (a != null) {
                    return a;
                }
            }
            if (this.c != null) {
                a = this.c.a(str, str2);
                if (a != null) {
                    return a;
                }
            }
            if (this.d != null) {
                a = this.d.a(str, str2);
                if (a != null) {
                    return a;
                }
            }
            return get(str);
        } catch (Throwable th) {
            GDTLogger.report("Exception in settingManager.getForPlacement", th);
            return null;
        }
    }

    public int getInteger(String str, int i) {
        Object obj = get(str);
        return (obj == null || !(obj instanceof Integer)) ? i : ((Integer) obj).intValue();
    }

    public int getIntegerForPlacement(String str, String str2, int i) {
        Object forPlacement = getForPlacement(str, str2);
        return (forPlacement == null || !(forPlacement instanceof Integer)) ? i : ((Integer) forPlacement).intValue();
    }

    public String getSdkCloudSettingSig() {
        return this.e;
    }

    public String getSid() {
        return this.i;
    }

    public String getString(String str) {
        Object obj = get(str);
        return obj == null ? null : obj.toString();
    }

    public String getStringForPlacement(String str, String str2) {
        Object forPlacement = getForPlacement(str, str2);
        return forPlacement == null ? null : forPlacement.toString();
    }

    public String getSuid() {
        return this.h;
    }

    public void setDEVCodeSetting(String str, Object obj) {
        this.d.a(str, obj);
    }

    public void setDEVCodeSetting(String str, Object obj, String str2) {
        this.d.a(str, obj, str2);
    }

    public void updateContextSetting(String str) {
        try {
            a aVar = new a();
            if (!StringUtil.isEmpty(str)) {
                aVar = new a(new String(Base64.decode(str, 0), "UTF-8"));
            }
            this.a = aVar;
        } catch (Throwable th) {
            GDTLogger.report("Exception while update Context Setting", th);
        }
    }

    public void updateDEVCloudSetting(String str, String str2) {
        if (a.b(this.j, str, str2)) {
            b();
        }
    }

    public void updateSDKCloudSetting(String str, String str2) {
        if (a.a(this.j, str, str2)) {
            a();
        }
    }

    public void updateSID(String str) {
        this.i = str;
    }

    public void updateSUID(String str) {
        if (!StringUtil.isEmpty(str) && !str.equals(this.h)) {
            this.h = str;
            try {
                StringUtil.writeTo(str, new File(this.j.getDir(SETTING.SETTINGDIR, 0), SETTING.SUID_FILE));
            } catch (Throwable e) {
                GDTLogger.report("Exception while persit suid", e);
            }
        }
    }
}
