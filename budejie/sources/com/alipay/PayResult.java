package com.alipay;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import com.alipay.sdk.util.j;

public class PayResult {
    private String memo;
    private String result;
    private String resultStatus;

    public PayResult(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split(h.b)) {
                if (str2.startsWith(j.a)) {
                    this.resultStatus = gatValue(str2, j.a);
                }
                if (str2.startsWith(j.c)) {
                    this.result = gatValue(str2, j.c);
                }
                if (str2.startsWith(j.b)) {
                    this.memo = gatValue(str2, j.b);
                }
            }
        }
    }

    public String toString() {
        return "resultStatus={" + this.resultStatus + "};memo={" + this.memo + "};result={" + this.result + h.d;
    }

    private String gatValue(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str3.length() + str.indexOf(str3), str.lastIndexOf(h.d));
    }

    public String getResultStatus() {
        return this.resultStatus;
    }

    public String getMemo() {
        return this.memo;
    }

    public String getResult() {
        return this.result;
    }
}
