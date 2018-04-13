package cn.xiaochuankeji.tieba.background.upload;

import com.alibaba.sdk.android.oss.common.OSSConstants;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.annotation.Nullable;
import okhttp3.u;
import okhttp3.z;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class a extends z {
    private b a;
    private long b;
    private byte[] c;
    private File d;

    public a(File file, b bVar) {
        this.d = file;
        this.b = file.length();
        this.a = bVar;
    }

    public a(byte[] bArr, b bVar) {
        this.c = bArr;
        this.b = (long) bArr.length;
        this.a = bVar;
    }

    @Nullable
    public u contentType() {
        if (this.d != null) {
            return u.a(a(this.d.getName()));
        }
        return u.a(OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE);
    }

    public long contentLength() throws IOException {
        return this.b;
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        Source source = null;
        if (this.d != null) {
            source = Okio.source(this.d);
        } else if (this.c != null) {
            source = Okio.source(new ByteArrayInputStream(this.c));
        }
        long j = 0;
        while (j < this.b) {
            long read = source.read(bufferedSink.buffer(), Math.min(this.b - j, 2048));
            if (read == -1) {
                break;
            }
            j += read;
            bufferedSink.flush();
            if (this.a != null) {
                this.a.a(this.b, j, 0);
            }
        }
        if (source != null) {
            source.close();
        }
    }

    private String a(String str) {
        String contentTypeFor;
        try {
            contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(URLEncoder.encode(str, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            contentTypeFor = null;
        }
        if (contentTypeFor == null) {
            return OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE;
        }
        return contentTypeFor;
    }
}
