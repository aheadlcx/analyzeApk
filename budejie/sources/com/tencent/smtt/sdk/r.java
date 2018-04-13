package com.tencent.smtt.sdk;

import android.content.Intent;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient$FileChooserParams;
import com.tencent.smtt.sdk.WebChromeClient.FileChooserParams;

class r extends FileChooserParams {
    final /* synthetic */ IX5WebChromeClient$FileChooserParams a;
    final /* synthetic */ n b;

    r(n nVar, IX5WebChromeClient$FileChooserParams iX5WebChromeClient$FileChooserParams) {
        this.b = nVar;
        this.a = iX5WebChromeClient$FileChooserParams;
    }

    public Intent createIntent() {
        return this.a.createIntent();
    }

    public String[] getAcceptTypes() {
        return this.a.getAcceptTypes();
    }

    public String getFilenameHint() {
        return this.a.getFilenameHint();
    }

    public int getMode() {
        return this.a.getMode();
    }

    public CharSequence getTitle() {
        return this.a.getTitle();
    }

    public boolean isCaptureEnabled() {
        return this.a.isCaptureEnabled();
    }
}
