package com.spriteapp.booklibrary.model;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import com.alipay.sdk.util.j;
import java.util.Map;

public class PayResult {
    private String memo;
    private String result;
    private String resultStatus;

    public PayResult(Map<String, String> map) {
        if (map != null) {
            for (String str : map.keySet()) {
                if (TextUtils.equals(str, j.a)) {
                    this.resultStatus = (String) map.get(str);
                } else if (TextUtils.equals(str, j.c)) {
                    this.result = (String) map.get(str);
                } else if (TextUtils.equals(str, j.b)) {
                    this.memo = (String) map.get(str);
                }
            }
        }
    }

    public String toString() {
        return "resultStatus={" + this.resultStatus + "};memo={" + this.memo + "};result={" + this.result + h.d;
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
