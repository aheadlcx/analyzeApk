package org.apache.commons.httpclient.methods;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.NameValuePair;

public class StringRequestEntity implements RequestEntity {
    private String charset;
    private byte[] content;
    private String contentType;

    public StringRequestEntity(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The content cannot be null");
        }
        this.contentType = null;
        this.charset = null;
        this.content = str.getBytes();
    }

    public StringRequestEntity(String str, String str2, String str3) throws UnsupportedEncodingException {
        if (str == null) {
            throw new IllegalArgumentException("The content cannot be null");
        }
        this.contentType = str2;
        this.charset = str3;
        if (str2 != null) {
            HeaderElement[] parseElements = HeaderElement.parseElements(str2);
            NameValuePair nameValuePair = null;
            for (HeaderElement parameterByName : parseElements) {
                nameValuePair = parameterByName.getParameterByName("charset");
                if (nameValuePair != null) {
                    break;
                }
            }
            if (str3 == null && nameValuePair != null) {
                this.charset = nameValuePair.getValue();
            } else if (str3 != null && nameValuePair == null) {
                this.contentType = new StringBuffer().append(str2).append("; charset=").append(str3).toString();
            }
        }
        if (this.charset != null) {
            this.content = str.getBytes(this.charset);
        } else {
            this.content = str.getBytes();
        }
    }

    public String getContentType() {
        return this.contentType;
    }

    public boolean isRepeatable() {
        return true;
    }

    public void writeRequest(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        outputStream.write(this.content);
        outputStream.flush();
    }

    public long getContentLength() {
        return (long) this.content.length;
    }

    public String getContent() {
        if (this.charset == null) {
            return new String(this.content);
        }
        try {
            return new String(this.content, this.charset);
        } catch (UnsupportedEncodingException e) {
            return new String(this.content);
        }
    }

    public String getCharset() {
        return this.charset;
    }
}
