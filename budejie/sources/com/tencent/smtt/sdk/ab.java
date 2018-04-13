package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.interfaces.WebResourceError;

class ab extends WebResourceError {
    final /* synthetic */ android.webkit.WebResourceError a;
    final /* synthetic */ z b;

    ab(z zVar, android.webkit.WebResourceError webResourceError) {
        this.b = zVar;
        this.a = webResourceError;
    }

    public CharSequence getDescription() {
        return this.a.getDescription();
    }

    public int getErrorCode() {
        return this.a.getErrorCode();
    }
}
