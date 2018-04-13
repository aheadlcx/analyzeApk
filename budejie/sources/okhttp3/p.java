package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.c;
import okio.d;
import org.apache.commons.httpclient.methods.PostMethod;

public final class p extends z {
    private static final u a = u.a(PostMethod.FORM_URL_ENCODED_CONTENT_TYPE);
    private final List<String> b;
    private final List<String> c;

    public static final class a {
        private final List<String> a = new ArrayList();
        private final List<String> b = new ArrayList();

        public a a(String str, String str2) {
            this.a.add(s.a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
            this.b.add(s.a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
            return this;
        }

        public a b(String str, String str2) {
            this.a.add(s.a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true));
            this.b.add(s.a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true));
            return this;
        }

        public p a() {
            return new p(this.a, this.b);
        }
    }

    p(List<String> list, List<String> list2) {
        this.b = c.a((List) list);
        this.c = c.a((List) list2);
    }

    public u a() {
        return a;
    }

    public long b() {
        return a(null, true);
    }

    public void a(d dVar) throws IOException {
        a(dVar, false);
    }

    private long a(d dVar, boolean z) {
        okio.c cVar;
        long j = 0;
        if (z) {
            cVar = new okio.c();
        } else {
            cVar = dVar.c();
        }
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                cVar.b(38);
            }
            cVar.a((String) this.b.get(i));
            cVar.b(61);
            cVar.a((String) this.c.get(i));
        }
        if (z) {
            j = cVar.b();
            cVar.t();
        }
        return j;
    }
}
