package qsbk.app.pay.adapter;

import java.text.SimpleDateFormat;

final class f extends ThreadLocal<SimpleDateFormat> {
    f() {
    }

    protected /* synthetic */ Object initialValue() {
        return a();
    }

    protected SimpleDateFormat a() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
}
