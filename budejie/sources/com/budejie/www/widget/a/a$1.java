package com.budejie.www.widget.a;

import java.text.SimpleDateFormat;

class a$1 extends ThreadLocal<SimpleDateFormat> {
    a$1() {
    }

    protected /* synthetic */ Object initialValue() {
        return a();
    }

    protected SimpleDateFormat a() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
}
