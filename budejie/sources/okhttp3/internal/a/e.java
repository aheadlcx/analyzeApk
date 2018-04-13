package okhttp3.internal.a;

import java.io.IOException;
import okio.c;
import okio.f;
import okio.q;

class e extends f {
    private boolean a;

    public e(q qVar) {
        super(qVar);
    }

    public void a_(c cVar, long j) throws IOException {
        if (this.a) {
            cVar.g(j);
            return;
        }
        try {
            super.a_(cVar, j);
        } catch (IOException e) {
            this.a = true;
            a(e);
        }
    }

    public void flush() throws IOException {
        if (!this.a) {
            try {
                super.flush();
            } catch (IOException e) {
                this.a = true;
                a(e);
            }
        }
    }

    public void close() throws IOException {
        if (!this.a) {
            try {
                super.close();
            } catch (IOException e) {
                this.a = true;
                a(e);
            }
        }
    }

    protected void a(IOException iOException) {
    }
}
