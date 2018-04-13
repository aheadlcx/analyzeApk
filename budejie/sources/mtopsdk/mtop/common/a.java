package mtopsdk.mtop.common;

import mtopsdk.common.util.m;
import mtopsdk.mtop.domain.b;

public class a implements b {
    private mtopsdk.mtop.a a;
    private volatile mtopsdk.a.a b;

    public a(mtopsdk.a.a aVar, mtopsdk.mtop.a aVar2) {
        this.b = aVar;
        this.a = aVar2;
    }

    public mtopsdk.a.a a() {
        return this.b;
    }

    public void a(mtopsdk.a.a aVar) {
        this.b = aVar;
    }

    public boolean b() {
        if (this.b == null) {
            m.d("mtopsdk.ApiID", "Future is null,cancel apiCall failed");
            return false;
        }
        this.b.c();
        return true;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ApiID [");
        stringBuilder.append("call=").append(this.b);
        stringBuilder.append(", mtopProxy=").append(this.a);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
