package com.tencent.smtt.export.external.interfaces;

import android.content.Intent;
import android.net.Uri;

public abstract class IX5WebChromeClient$FileChooserParams {
    public static final int MODE_OPEN = 0;
    public static final int MODE_OPEN_FOLDER = 2;
    public static final int MODE_OPEN_MULTIPLE = 1;
    public static final int MODE_SAVE = 3;

    public static Uri[] parseResult(int i, Intent intent) {
        return null;
    }

    public abstract Intent createIntent();

    public abstract String[] getAcceptTypes();

    public abstract String getFilenameHint();

    public abstract int getMode();

    public abstract CharSequence getTitle();

    public abstract boolean isCaptureEnabled();
}
