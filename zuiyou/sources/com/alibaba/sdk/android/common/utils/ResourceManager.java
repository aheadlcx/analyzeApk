package com.alibaba.sdk.android.common.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManager {
    private ResourceBundle bundle;

    ResourceManager(String str, Locale locale) {
        this.bundle = ResourceBundle.getBundle(str, locale);
    }

    public static ResourceManager getInstance(String str) {
        return new ResourceManager(str, Locale.getDefault());
    }

    public static ResourceManager getInstance(String str, Locale locale) {
        return new ResourceManager(str, locale);
    }

    public String getString(String str) {
        return this.bundle.getString(str);
    }

    public String getFormattedString(String str, Object... objArr) {
        return MessageFormat.format(getString(str), objArr);
    }
}
