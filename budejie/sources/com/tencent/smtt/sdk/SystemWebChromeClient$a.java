package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage.MessageLevel;

class SystemWebChromeClient$a implements ConsoleMessage {
    private MessageLevel a;
    private String b;
    private String c;
    private int d;

    SystemWebChromeClient$a(android.webkit.ConsoleMessage consoleMessage) {
        this.a = MessageLevel.valueOf(consoleMessage.messageLevel().name());
        this.b = consoleMessage.message();
        this.c = consoleMessage.sourceId();
        this.d = consoleMessage.lineNumber();
    }

    SystemWebChromeClient$a(String str, String str2, int i) {
        this.a = MessageLevel.LOG;
        this.b = str;
        this.c = str2;
        this.d = i;
    }

    public int lineNumber() {
        return this.d;
    }

    public String message() {
        return this.b;
    }

    public MessageLevel messageLevel() {
        return this.a;
    }

    public String sourceId() {
        return this.c;
    }
}
