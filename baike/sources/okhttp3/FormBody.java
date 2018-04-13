package okhttp3;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;

public final class FormBody extends RequestBody {
    private static final MediaType a = MediaType.parse("application/x-www-form-urlencoded");
    private final List<String> b;
    private final List<String> c;

    public static final class Builder {
        private final List<String> a;
        private final List<String> b;
        private final Charset c;

        public Builder() {
            this(null);
        }

        public Builder(Charset charset) {
            this.a = new ArrayList();
            this.b = new ArrayList();
            this.c = charset;
        }

        public Builder add(String str, String str2) {
            this.a.add(HttpUrl.a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true, this.c));
            this.b.add(HttpUrl.a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true, this.c));
            return this;
        }

        public Builder addEncoded(String str, String str2) {
            this.a.add(HttpUrl.a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true, this.c));
            this.b.add(HttpUrl.a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true, this.c));
            return this;
        }

        public FormBody build() {
            return new FormBody(this.a, this.b);
        }
    }

    FormBody(List<String> list, List<String> list2) {
        this.b = Util.immutableList(list);
        this.c = Util.immutableList(list2);
    }

    public int size() {
        return this.b.size();
    }

    public String encodedName(int i) {
        return (String) this.b.get(i);
    }

    public String name(int i) {
        return HttpUrl.a(encodedName(i), true);
    }

    public String encodedValue(int i) {
        return (String) this.c.get(i);
    }

    public String value(int i) {
        return HttpUrl.a(encodedValue(i), true);
    }

    public MediaType contentType() {
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
