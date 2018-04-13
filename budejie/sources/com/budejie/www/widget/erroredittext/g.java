package com.budejie.www.widget.erroredittext;

import java.util.regex.Pattern;

public class g extends h {
    public g(String str) {
        super(str, Pattern.compile("[\\w]{8,16}"));
    }

    public boolean a(String str) {
        boolean a = super.a(str);
        if (a) {
            return b(str);
        }
        return a;
    }

    private boolean b(String str) {
        return Pattern.compile("(?=.*[0-9].*)(?=.*[A-Z].*)(?=.*[a-z].*).{8,16}").matcher(str).matches();
    }
}
