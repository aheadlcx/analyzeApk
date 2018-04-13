package com.qq.e.comm.managers.status;

import android.content.Context;
import com.qq.e.comm.util.StringUtil;

public class APPStatus {
    private String a;
    private Context b;

    public APPStatus(String str, Context context) {
        this.a = str;
        this.b = context;
    }

    public String getAPPID() {
        return this.a;
    }

    public String getAPPName() {
        return this.b.getPackageName();
    }

    public String getAPPRealName() {
        String str = null;
        String aPPName = getAPPName();
        if (!StringUtil.isEmpty(aPPName)) {
            try {
                str = this.b.getPackageManager().getPackageInfo(aPPName, 0).applicationInfo.loadLabel(this.b.getPackageManager()).toString();
            } catch (Exception e) {
            }
        }
        return str;
    }

    public String getAPPVersion() {
        String str = null;
        String aPPName = getAPPName();
        if (StringUtil.isEmpty(aPPName)) {
            return str;
        }
        try {
            return this.b.getPackageManager().getPackageInfo(aPPName, 0).versionName;
        } catch (Exception e) {
            return str;
        }
    }
}
