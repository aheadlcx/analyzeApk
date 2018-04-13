package cn.v6.sixrooms.pay.alipay;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import com.alipay.sdk.util.j;

public class PayResult {
    private String a;
    private String b;
    private String c;

    public PayResult(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split(h.b)) {
                if (str2.startsWith(j.a)) {
                    this.a = a(str2, j.a);
                }
                if (str2.startsWith(j.c)) {
                    this.b = a(str2, j.c);
                }
                if (str2.startsWith(j.b)) {
                    this.c = a(str2, j.b);
                }
            }
        }
    }

    public String toString() {
        return "resultStatus={" + this.a + "};memo={" + this.c + "};result={" + this.b + h.d;
    }

    private static String a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str3.length() + str.indexOf(str3), str.lastIndexOf(h.d));
    }

    public String getResultStatus() {
        return this.a;
    }

    public String getMemo() {
        return this.c;
    }

    public String getResult() {
        return this.b;
    }
}
