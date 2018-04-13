package cz.msebera.android.httpclient.entity.mime;

import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.mime.content.AbstractContentBody;
import cz.msebera.android.httpclient.entity.mime.content.ContentBody;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;

public class FormBodyPartBuilder {
    private String a;
    private ContentBody b;
    private final Header c;

    public static FormBodyPartBuilder create(String str, ContentBody contentBody) {
        return new FormBodyPartBuilder(str, contentBody);
    }

    public static FormBodyPartBuilder create() {
        return new FormBodyPartBuilder();
    }

    FormBodyPartBuilder(String str, ContentBody contentBody) {
        this();
        this.a = str;
        this.b = contentBody;
    }

    FormBodyPartBuilder() {
        this.c = new Header();
    }

    public FormBodyPartBuilder setName(String str) {
        this.a = str;
        return this;
    }

    public FormBodyPartBuilder setBody(ContentBody contentBody) {
        this.b = contentBody;
        return this;
    }

    public FormBodyPartBuilder addField(String str, String str2) {
        Args.notNull(str, "Field name");
        this.c.addField(new MinimalField(str, str2));
        return this;
    }

    public FormBodyPartBuilder setField(String str, String str2) {
        Args.notNull(str, "Field name");
        this.c.setField(new MinimalField(str, str2));
        return this;
    }

    public FormBodyPartBuilder removeFields(String str) {
        Args.notNull(str, "Field name");
        this.c.removeFields(str);
        return this;
    }

    public FormBodyPart build() {
        StringBuilder stringBuilder;
        Asserts.notBlank(this.a, "Name");
        Asserts.notNull(this.b, "Content body");
        Header header = new Header();
        for (MinimalField addField : this.c.getFields()) {
            header.addField(addField);
        }
        if (header.getField(MIME.CONTENT_DISPOSITION) == null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("form-data; name=\"");
            stringBuilder.append(this.a);
            stringBuilder.append("\"");
            if (this.b.getFilename() != null) {
                stringBuilder.append("; filename=\"");
                stringBuilder.append(this.b.getFilename());
                stringBuilder.append("\"");
            }
            header.addField(new MinimalField(MIME.CONTENT_DISPOSITION, stringBuilder.toString()));
        }
        if (header.getField("Content-Type") == null) {
            ContentType contentType;
            if (this.b instanceof AbstractContentBody) {
                contentType = ((AbstractContentBody) this.b).getContentType();
            } else {
                contentType = null;
            }
            if (contentType != null) {
                header.addField(new MinimalField("Content-Type", contentType.toString()));
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.b.getMimeType());
                if (this.b.getCharset() != null) {
                    stringBuilder.append(HTTP.CHARSET_PARAM);
                    stringBuilder.append(this.b.getCharset());
                }
                header.addField(new MinimalField("Content-Type", stringBuilder.toString()));
            }
        }
        if (header.getField(MIME.CONTENT_TRANSFER_ENC) == null) {
            header.addField(new MinimalField(MIME.CONTENT_TRANSFER_ENC, this.b.getTransferEncoding()));
        }
        return new FormBodyPart(this.a, this.b, header);
    }
}
