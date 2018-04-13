package com.xiaomi.mipush.sdk;

import com.alipay.sdk.util.h;
import java.io.Serializable;
import java.util.List;

public class MiPushCommandMessage implements Serializable {
    private static final long serialVersionUID = 1;
    private String category;
    private String command;
    private List commandArguments;
    private String reason;
    private long resultCode;

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String str) {
        this.command = str;
    }

    public List getCommandArguments() {
        return this.commandArguments;
    }

    public void setCommandArguments(List list) {
        this.commandArguments = list;
    }

    public long getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(long j) {
        this.resultCode = j;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public String toString() {
        return "command={" + this.command + "}, resultCode={" + this.resultCode + "}, reason={" + this.reason + "}, category={" + this.category + "}, commandArguments={" + this.commandArguments + h.d;
    }
}
