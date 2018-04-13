package retrofit2;

import java.io.IOException;
import okhttp3.aa;
import okhttp3.e;
import okhttp3.f;

class h$1 implements f {
    final /* synthetic */ d a;
    final /* synthetic */ h b;

    h$1(h hVar, d dVar) {
        this.b = hVar;
        this.a = dVar;
    }

    public void a(e eVar, aa aaVar) throws IOException {
        try {
            a(this.b.a(aaVar));
        } catch (Throwable th) {
            a(th);
        }
    }

    public void a(e eVar, IOException iOException) {
        try {
            this.a.a(this.b, (Throwable) iOException);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(Throwable th) {
        try {
            this.a.a(this.b, th);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    private void a(l<T> lVar) {
        try {
            this.a.a(this.b, (l) lVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
