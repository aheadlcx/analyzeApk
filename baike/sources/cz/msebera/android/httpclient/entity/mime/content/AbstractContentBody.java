package cz.msebera.android.httpclient.entity.mime.content;

import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.util.Args;
import java.nio.charset.Charset;

public abstract class AbstractContentBody implements ContentBody {
    private final ContentType a;

    public AbstractContentBody(ContentType contentType) {
        Args.notNull(contentType, "Content type");
        this.a = contentType;
    }

    @Deprecated
    public AbstractContentBody(String str) {
        this(ContentType.parse(str));
    }

    public ContentType getContentType() {
        return this.a;
    }

    public String getMimeType() {
        return this.a.getMimeType();
    }

    public String getMediaType() {
        String mimeType = this.a.getMimeType();
        int indexOf = mimeType.indexOf(47);
        if (indexOf != -1) {
            return mimeType.substring(0, indexOf);
        }
        return mimeType;
    }

    public String getSubType() {
        String mimeType = this.a.getMimeType();
        int indexOf = mimeType.indexOf(47);
        if (indexOf != -1) {
            return mimeType.substring(indexOf + 1);
        }
        return null;
    }

    public String getCharset() {
        Charset charset = this.a.getCharset();
        return charset != null ? charset.name() : null;
    }
}
