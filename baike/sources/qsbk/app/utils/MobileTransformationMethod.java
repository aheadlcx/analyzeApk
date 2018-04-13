package qsbk.app.utils;

import android.text.method.ReplacementTransformationMethod;
import cz.msebera.android.httpclient.message.TokenParser;

public class MobileTransformationMethod extends ReplacementTransformationMethod {
    protected char[] getOriginal() {
        return new char[]{TokenParser.CR};
    }

    protected char[] getReplacement() {
        return new char[]{'﻿'};
    }
}
