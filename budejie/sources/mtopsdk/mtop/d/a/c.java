package mtopsdk.mtop.d.a;

import java.io.OutputStream;
import mtopsdk.a.b.d;

class c extends d {
    final /* synthetic */ byte[] a;
    final /* synthetic */ b b;

    c(b bVar, byte[] bArr) {
        this.b = bVar;
        this.a = bArr;
    }

    public String a() {
        return "application/x-www-form-urlencoded;charset=UTF-8";
    }

    public void a(OutputStream outputStream) {
        if (outputStream != null && this.a != null) {
            outputStream.write(this.a);
        }
    }
}
