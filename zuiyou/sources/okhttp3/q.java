package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.internal.c;
import okio.Buffer;
import okio.BufferedSink;

public final class q extends z {
    private static final u a = u.a("application/x-www-form-urlencoded");
    private final List<String> b;
    private final List<String> c;

    public static final class a {
        private final List<String> a = new ArrayList();
        private final List<String> b = new ArrayList();

        public a a(String str, String str2) {
            this.a.add(HttpUrl.a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
            this.b.add(HttpUrl.a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
            return this;
        }

        public a b(String str, String str2) {
            this.a.add(HttpUrl.a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true));
            this.b.add(HttpUrl.a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true));
            return this;
        }

        public q a() {
            return new q(this.a, this.b);
        }
    }

    q(List<String> list, List<String> list2) {
        this.b = c.a((List) list);
        this.c = c.a((List) list2);
    }

    public u contentType() {
        return a;
    }

    public long contentLength() {
        return a(null, true);
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        a(bufferedSink, false);
    }

    private long a(@Nullable BufferedSink bufferedSink, boolean z) {
        Buffer buffer;
        long j = 0;
        if (z) {
            buffer = new Buffer();
        } else {
            buffer = bufferedSink.buffer();
        }
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                buffer.writeByte(38);
            }
            buffer.writeUtf8((String) this.b.get(i));
            buffer.writeByte(61);
            buffer.writeUtf8((String) this.c.get(i));
        }
        if (z) {
            j = buffer.size();
            buffer.clear();
        }
        return j;
    }
}
