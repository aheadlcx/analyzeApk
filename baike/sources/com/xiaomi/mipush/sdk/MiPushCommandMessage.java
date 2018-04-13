package com.xiaomi.mipush.sdk;

import com.alipay.sdk.util.h;
import java.util.List;

public class MiPushCommandMessage implements a {
    private String a;
    private long b;
    private String c;
    private List<String> d;
    private String e;

    public String getCategory() {
        return this.e;
    }

    public String getCommand() {
        return this.a;
    }

    public List<String> getCommandArguments() {
        return this.d;
    }

    public String getReason() {
        return this.c;
    }

    public long getResultCode() {
        return this.b;
    }

    public void setCategory(String str) {
        this.e = str;
    }

    public void setCommand(String str) {
        this.a = str;
    }

    public void setCommandArguments(List<String> list) {
        this.d = list;
    }

    public void setReason(String str) {
        this.c = str;
    }

    public void setResultCode(long j) {
        this.b = j;
    }

    public String toString() {
        return "command={" + this.a + "}, resultCode={" + this.b + "}, reason={" + this.c + "}, category={" + this.e + "}, commandArguments={" + this.d + h.d;
    }
}
