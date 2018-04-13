package org.apache.commons.httpclient.methods.multipart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FilePart extends PartBase {
    public static final String DEFAULT_CHARSET = "ISO-8859-1";
    public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
    public static final String DEFAULT_TRANSFER_ENCODING = "binary";
    protected static final String FILE_NAME = "; filename=";
    private static final byte[] FILE_NAME_BYTES = EncodingUtil.getAsciiBytes(FILE_NAME);
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$methods$multipart$FilePart;
    private PartSource source;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$methods$multipart$FilePart == null) {
            class$ = class$("org.apache.commons.httpclient.methods.multipart.FilePart");
            class$org$apache$commons$httpclient$methods$multipart$FilePart = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$methods$multipart$FilePart;
        }
        LOG = LogFactory.getLog(class$);
    }

    public FilePart(String str, PartSource partSource, String str2, String str3) {
        if (str2 == null) {
            str2 = DEFAULT_CONTENT_TYPE;
        }
        if (str3 == null) {
            str3 = "ISO-8859-1";
        }
        super(str, str2, str3, "binary");
        if (partSource == null) {
            throw new IllegalArgumentException("Source may not be null");
        }
        this.source = partSource;
    }

    public FilePart(String str, PartSource partSource) {
        this(str, partSource, null, null);
    }

    public FilePart(String str, File file) throws FileNotFoundException {
        this(str, new FilePartSource(file), null, null);
    }

    public FilePart(String str, File file, String str2, String str3) throws FileNotFoundException {
        this(str, new FilePartSource(file), str2, str3);
    }

    public FilePart(String str, String str2, File file) throws FileNotFoundException {
        this(str, new FilePartSource(str2, file), null, null);
    }

    public FilePart(String str, String str2, File file, String str3, String str4) throws FileNotFoundException {
        this(str, new FilePartSource(str2, file), str3, str4);
    }

    protected void sendDispositionHeader(OutputStream outputStream) throws IOException {
        LOG.trace("enter sendDispositionHeader(OutputStream out)");
        super.sendDispositionHeader(outputStream);
        String fileName = this.source.getFileName();
        if (fileName != null) {
            outputStream.write(FILE_NAME_BYTES);
            outputStream.write(Part.QUOTE_BYTES);
            outputStream.write(EncodingUtil.getAsciiBytes(fileName));
            outputStream.write(Part.QUOTE_BYTES);
        }
    }

    protected void sendData(OutputStream outputStream) throws IOException {
        LOG.trace("enter sendData(OutputStream out)");
        if (lengthOfData() == 0) {
            LOG.debug("No data to send.");
            return;
        }
        byte[] bArr = new byte[4096];
        InputStream createInputStream = this.source.createInputStream();
        while (true) {
            try {
                int read = createInputStream.read(bArr);
                if (read < 0) {
                    break;
                }
                outputStream.write(bArr, 0, read);
            } finally {
                createInputStream.close();
            }
        }
    }

    protected PartSource getSource() {
        LOG.trace("enter getSource()");
        return this.source;
    }

    protected long lengthOfData() throws IOException {
        LOG.trace("enter lengthOfData()");
        return this.source.getLength();
    }
}
