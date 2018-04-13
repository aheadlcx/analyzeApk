package cz.msebera.android.httpclient.entity.mime;

import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.mime.content.AbstractContentBody;
import cz.msebera.android.httpclient.entity.mime.content.ContentBody;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.Args;

public class FormBodyPart {
    private final String a;
    private final Header b;
    private final ContentBody c;

    FormBodyPart(String str, ContentBody contentBody, Header header) {
        Args.notNull(str, "Name");
        Args.notNull(contentBody, "Body");
        this.a = str;
        this.c = contentBody;
        if (header == null) {
            header = new Header();
        }
        this.b = header;
    }

    @Deprecated
    public FormBodyPart(String str, ContentBody contentBody) {
        Args.notNull(str, "Name");
        Args.notNull(contentBody, "Body");
        this.a = str;
        this.c = contentBody;
        this.b = new Header();
        a(contentBody);
        b(contentBody);
        c(contentBody);
    }

    public String getName() {
        return this.a;
    }

    public ContentBody getBody() {
        return this.c;
    }

    public Header getHeader() {
        return this.b;
    }

    public void addField(String str, String str2) {
        Args.notNull(str, "Field name");
        this.b.addField(new MinimalField(str, str2));
    }

    @Deprecated
    protected void a(ContentBody contentBody) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("form-data; name=\"");
        stringBuilder.append(getName());
        stringBuilder.append("\"");
        if (contentBody.getFilename() != null) {
            stringBuilder.append("; filename=\"");
            stringBuilder.append(contentBody.getFilename());
            stringBuilder.append("\"");
        }
        addField(MIME.CONTENT_DISPOSITION, stringBuilder.toString());
    }

    @Deprecated
    protected void b(ContentBody contentBody) {
        ContentType contentType;
        if (contentBody instanceof AbstractContentBody) {
            contentType = ((AbstractContentBody) contentBody).getContentType();
        } else {
            contentType = null;
        }
        if (contentType != null) {
            addField("Content-Type", contentType.toString());
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(contentBody.getMimeType());
        if (contentBody.getCharset() != null) {
            stringBuilder.append(HTTP.CHARSET_PARAM);
            stringBuilder.append(contentBody.getCharset());
        }
        addField("Content-Type", stringBuilder.toString());
    }

    @Deprecated
    protected void c(ContentBody contentBody) {
        addField(MIME.CONTENT_TRANSFER_ENC, contentBody.getTransferEncoding());
    }
}
