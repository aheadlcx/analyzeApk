package com.budejie.www.widget.erroredittext;

import java.util.regex.Pattern;

public class d extends h {
    public d(String str) {
        super(str, Pattern.compile("[\\w]{6,16}"));
    }
}
