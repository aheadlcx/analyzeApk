package org.apache.commons.httpclient.methods.multipart;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringPart extends PartBase {
    public static final String DEFAULT_CHARSET = "US-ASCII";
    public static final String DEFAULT_CONTENT_TYPE = "text/plain";
    public static final String DEFAULT_TRANSFER_ENCODING = "8bit";
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$methods$multipart$StringPart;
    private byte[] content;
    private String value;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$methods$multipart$StringPart == null) {
            class$ = class$("org.apache.commons.httpclient.methods.multipart.StringPart");
            class$org$apache$commons$httpclient$methods$multipart$StringPart = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$methods$multipart$StringPart;
        }
        LOG = LogFactory.getLog(class$);
    }

    public StringPart(String str, String str2, String str3) {
        String str4 = DEFAULT_CONTENT_TYPE;
        if (str3 == null) {
            str3 = "US-ASCII";
        }
        super(str, str4, str3, "8bit");
        if (str2 == null) {
            throw new IllegalArgumentException("Value may not be null");
        } else if (str2.indexOf(0) != -1) {
            throw new IllegalArgumentException("NULs may not be present in string parts");
        } else {
            this.value = str2;
        }
    }

    public StringPart(String str, String str2) {
        this(str, str2, null);
    }

    private byte[] getContent() {
        if (this.content == null) {
            this.content = EncodingUtil.getBytes(this.value, getCharSet());
        }
        return this.content;
    }

    protected void sendData(OutputStream outputStream) throws IOException {
        LOG.trace("enter sendData(OutputStream)");
        outputStream.write(getContent());
    }

    protected long lengthOfData() throws IOException {
        LOG.trace("enter lengthOfData()");
        return (long) getContent().length;
    }

    public void setCharSet(String str) {
        super.setCharSet(str);
        this.content = null;
    }
}
