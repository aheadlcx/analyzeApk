package org.apache.http.entity.mime;

import org.apache.http.entity.mime.content.ContentBody;

public class FormBodyPart {
    private final ContentBody body;
    private final Header header;
    private final String name;

    public FormBodyPart(String str, ContentBody contentBody) {
        if (str == null) {
            throw new IllegalArgumentException("Name may not be null");
        } else if (contentBody == null) {
            throw new IllegalArgumentException("Body may not be null");
        } else {
            this.name = str;
            this.body = contentBody;
            this.header = new Header();
            generateContentDisp(contentBody);
            generateContentType(contentBody);
            generateTransferEncoding(contentBody);
        }
    }

    public String getName() {
        return this.name;
    }

    public ContentBody getBody() {
        return this.body;
    }

    public Header getHeader() {
        return this.header;
    }

    public void addField(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Field name may not be null");
        }
        this.header.addField(new MinimalField(str, str2));
    }

    protected void generateContentDisp(ContentBody contentBody) {
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

    protected void generateContentType(ContentBody contentBody) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(contentBody.getMimeType());
        if (contentBody.getCharset() != null) {
            stringBuilder.append("; charset=");
            stringBuilder.append(contentBody.getCharset());
        }
        addField(MIME.CONTENT_TYPE, stringBuilder.toString());
    }

    protected void generateTransferEncoding(ContentBody contentBody) {
        addField(MIME.CONTENT_TRANSFER_ENC, contentBody.getTransferEncoding());
    }
}
