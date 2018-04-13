package cn.v6.sixrooms.room.utils.phone;

import android.content.Context;
import android.text.TextUtils;
import cn.v6.sixrooms.utils.NetWorkUtil;

public class LiveFlvManager {
    public static final int HIGH = 0;
    public static final int NONE = -1;
    public static final int STANDARD = 1;
    private String a;
    private String b;
    private String c;

    public void init(String str, String str2) {
        this.b = str;
        this.c = str2;
    }

    public boolean isChangeable() {
        if (TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.c)) {
            return false;
        }
        return true;
    }

    public int getFlvStatus() {
        if (TextUtils.isEmpty(this.a)) {
            return -1;
        }
        if (this.a.equals(this.b)) {
            return 0;
        }
        return 1;
    }

    public String getNextFlv() {
        if (TextUtils.isEmpty(this.a)) {
            return "";
        }
        if (this.a.equals(this.b) && !TextUtils.isEmpty(this.c)) {
            this.a = this.c;
        } else if (this.a.equals(this.c) && !TextUtils.isEmpty(this.b)) {
            this.a = this.b;
        }
        return this.a;
    }

    public String getCurrentFlv(Context context) {
        String str;
        if (NetWorkUtil.getNetWorkType(context) == 4) {
            if (TextUtils.isEmpty(this.b)) {
                str = this.c;
            }
            str = this.b;
        } else {
            if (!TextUtils.isEmpty(this.c)) {
                str = this.c;
            }
            str = this.b;
        }
        this.a = str;
        return this.a;
    }
}
