package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import qsbk.app.thirdparty.net.HttpManager;

public final class MultipartBody extends RequestBody {
    public static final MediaType ALTERNATIVE = MediaType.parse("multipart/alternative");
    public static final MediaType DIGEST = MediaType.parse("multipart/digest");
    public static final MediaType FORM = MediaType.parse(HttpManager.MULTIPART_FORM_DATA);
    public static final MediaType MIXED = MediaType.parse("multipart/mixed");
    public static final MediaType PARALLEL = MediaType.parse("multipart/parallel");
    private static final byte[] a = new byte[]{(byte) 58, (byte) 32};
    private static final byte[] b = new byte[]{(byte) 13, (byte) 10};
    private static final byte[] c = new byte[]{(byte) 45, (byte) 45};
    private final ByteString d;
    private final MediaType e;
    private final MediaType f;
    private final List<MultipartBody$Part> g;
    private long h = -1;

    public static final class Builder {
        private final ByteString a;
        private MediaType b;
        private final List<MultipartBody$Part> c;

        public Builder() {
            this(UUID.randomUUID().toString());
        }

        public Builder(String str) {
            this.b = MultipartBody.MIXED;
            this.c = new ArrayList();
            this.a = ByteString.encodeUtf8(str);
        }

        public Builder setType(MediaType mediaType) {
            if (mediaType == null) {
                throw new NullPointerException("type == null");
            } else if (mediaType.type().equals("multipart")) {
                this.b = mediaType;
                return this;
            } else {
                throw new IllegalArgumentException("multipart != " + mediaType);
            }
        }

        public Builder addPart(RequestBody requestBody) {
            return addPart(MultipartBody$Part.create(requestBody));
        }

        public Builder addPart(@Nullable Headers headers, RequestBody requestBody) {
            return addPart(MultipartBody$Part.create(headers, requestBody));
        }

        public Builder addFormDataPart(String str, String str2) {
            return addPart(MultipartBody$Part.createFormData(str, str2));
        }

        public Builder addFormDataPart(String str, @Nullable String str2, RequestBody requestBody) {
            return addPart(MultipartBody$Part.createFormData(str, str2, requestBody));
        }

        public Builder addPart(MultipartBody$Part multipartBody$Part) {
            if (multipartBody$Part == null) {
                throw new NullPointerException("part == null");
            }
            this.c.add(multipartBody$Part);
            return this;
        }

        public MultipartBody build() {
            if (!this.c.isEmpty()) {
                return new MultipartBody(this.a, this.b, this.c);
            }
            throw new IllegalStateException("Multipart body must have at least one part.");
        }
    }

    MultipartBody(ByteString byteString, MediaType mediaType, List<MultipartBody$Part> list) {
        this.d = byteString;
        this.e = mediaType;
        this.f = MediaType.parse(mediaType + "; boundary=" + byteString.utf8());
        this.g = Util.immutableList(list);
    }

    public MediaType type() {
        return this.e;
    }

    public String boundary() {
        return this.d.utf8();
    }

    public int size() {
        return this.g.size();
    }

    public List<MultipartBody$Part> parts() {
        return this.g;
    }

    public MultipartBody$Part part(int i) {
        return (MultipartBody$Part) this.g.get(i);
    }

    public MediaType contentType() {
        return this.f;
    }

    public long contentLength() throws IOException {
        long j = this.h;
        if (j != -1) {
            return j;
        }
        j = a(null, true);
        this.h = j;
        return j;
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        a(bufferedSink, false);
    }

    private long a(@Nullable BufferedSink bufferedSink, boolean z) throws IOException {
        Buffer buffer;
        long j = 0;
        if (z) {
            Buffer buffer2 = new Buffer();
            buffer = buffer2;
            bufferedSink = buffer2;
        } else {
            buffer = null;
        }
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            MultipartBody$Part multipartBody$Part = (MultipartBody$Part) this.g.get(i);
            Headers headers = multipartBody$Part.a;
            RequestBody requestBody = multipartBody$Part.b;
            bufferedSink.write(c);
            bufferedSink.write(this.d);
            bufferedSink.write(b);
            if (headers != null) {
                int size2 = headers.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    bufferedSink.writeUtf8(headers.name(i2)).write(a).writeUtf8(headers.value(i2)).write(b);
                }
            }
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                bufferedSink.writeUtf8("Content-Type: ").writeUtf8(contentType.toString()).write(b);
            }
            long contentLength = requestBody.contentLength();
            if (contentLength != -1) {
                bufferedSink.writeUtf8("Content-Length: ").writeDecimalLong(contentLength).write(b);
            } else if (z) {
                buffer.clear();
                return -1;
            }
            bufferedSink.write(b);
            if (z) {
                j += contentLength;
            } else {
                requestBody.writeTo(bufferedSink);
            }
            bufferedSink.write(b);
        }
        bufferedSink.write(c);
        bufferedSink.write(this.d);
        bufferedSink.write(c);
        bufferedSink.write(b);
        if (!z) {
            return j;
        }
        j += buffer.size();
        buffer.clear();
        return j;
    }

    static StringBuilder a(StringBuilder stringBuilder, String str) {
        stringBuilder.append('\"');
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '\n':
                    stringBuilder.append("%0A");
                    break;
                case '\r':
                    stringBuilder.append("%0D");
                    break;
                case '\"':
                    stringBuilder.append("%22");
                    break;
                default:
                    stringBuilder.append(charAt);
                    break;
            }
        }
        stringBuilder.append('\"');
        return stringBuilder;
    }
}
