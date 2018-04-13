package com.budejie.www.widget.erroredittext;

import java.util.regex.Pattern;

public class h extends l {
    private Pattern a;

    public h(String str, Pattern pattern) {
        super(str);
        if (pattern == null) {
            throw new IllegalArgumentException("_pattern must not be null");
        }
        this.a = pattern;
    }

    public boolean a(String str) {
        return this.a.matcher(str).matches();
    }
}
