package com.budejie.www.widget.erroredittext;

import android.os.Build.VERSION;
import android.util.Patterns;
import java.util.regex.Pattern;

public class i extends h {
    public i(String str) {
        super(str, VERSION.SDK_INT >= 8 ? Patterns.PHONE : Pattern.compile("(\\+[0-9]+[\\- \\.]*)?(\\([0-9]+\\)[\\- \\.]*)?([0-9][0-9\\- \\.][0-9\\- \\.]+[0-9])"));
    }

    public boolean a(String str) {
        boolean a = super.a(str);
        if (a) {
            return b(str);
        }
        return a;
    }

    private boolean b(String str) {
        if (str.length() == 11) {
            return true;
        }
        return false;
    }
}
