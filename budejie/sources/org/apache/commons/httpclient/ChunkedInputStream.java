package org.apache.commons.httpclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.httpclient.util.ExceptionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ChunkedInputStream extends InputStream {
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$ChunkedInputStream;
    private boolean bof;
    private int chunkSize;
    private boolean closed;
    private boolean eof;
    private InputStream in;
    private HttpMethod method;
    private int pos;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$ChunkedInputStream == null) {
            class$ = class$("org.apache.commons.httpclient.ChunkedInputStream");
            class$org$apache$commons$httpclient$ChunkedInputStream = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$ChunkedInputStream;
        }
        LOG = LogFactory.getLog(class$);
    }

    public ChunkedInputStream(InputStream inputStream, HttpMethod httpMethod) throws IOException {
        this.bof = true;
        this.eof = false;
        this.closed = false;
        this.method = null;
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream parameter may not be null");
        }
        this.in = inputStream;
        this.method = httpMethod;
        this.pos = 0;
    }

    public ChunkedInputStream(InputStream inputStream) throws IOException {
        this(inputStream, null);
    }

    public int read() throws IOException {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.eof) {
            return -1;
        } else {
            if (this.pos >= this.chunkSize) {
                nextChunk();
                if (this.eof) {
                    return -1;
                }
            }
            this.pos++;
            return this.in.read();
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.eof) {
            return -1;
        } else {
            if (this.pos >= this.chunkSize) {
                nextChunk();
                if (this.eof) {
                    return -1;
                }
            }
            int read = this.in.read(bArr, i, Math.min(i2, this.chunkSize - this.pos));
            this.pos += read;
            return read;
        }
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    private void readCRLF() throws IOException {
        int read = this.in.read();
        int read2 = this.in.read();
        if (read != 13 || read2 != 10) {
            throw new IOException(new StringBuffer().append("CRLF expected at end of chunk: ").append(read).append("/").append(read2).toString());
        }
    }

    private void nextChunk() throws IOException {
        if (!this.bof) {
            readCRLF();
        }
        this.chunkSize = getChunkSizeFromInputStream(this.in);
        this.bof = false;
        this.pos = 0;
        if (this.chunkSize == 0) {
            this.eof = true;
            parseTrailerHeaders();
        }
    }

    private static int getChunkSizeFromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i != -1) {
            int read = inputStream.read();
            if (read == -1) {
                throw new IOException("chunked stream ended unexpectedly");
            }
            switch (i) {
                case 0:
                    switch (read) {
                        case 13:
                            i = 1;
                            continue;
                        case 34:
                            i = 2;
                            break;
                    }
                    byteArrayOutputStream.write(read);
                    break;
                case 1:
                    if (read == 10) {
                        i = -1;
                        break;
                    }
                    throw new IOException("Protocol violation: Unexpected single newline character in chunk size");
                case 2:
                    switch (read) {
                        case 34:
                            i = 0;
                            break;
                        case 92:
                            byteArrayOutputStream.write(inputStream.read());
                            continue;
                    }
                    byteArrayOutputStream.write(read);
                    break;
                default:
                    throw new RuntimeException("assertion failed");
            }
        }
        String asciiString = EncodingUtil.getAsciiString(byteArrayOutputStream.toByteArray());
        int indexOf = asciiString.indexOf(59);
        asciiString = indexOf > 0 ? asciiString.substring(0, indexOf).trim() : asciiString.trim();
        try {
            return Integer.parseInt(asciiString.trim(), 16);
        } catch (NumberFormatException e) {
            throw new IOException(new StringBuffer().append("Bad chunk size: ").append(asciiString).toString());
        }
    }

    private void parseTrailerHeaders() throws IOException {
        try {
            String str = "US-ASCII";
            if (this.method != null) {
                str = this.method.getParams().getHttpElementCharset();
            }
            Header[] parseHeaders = HttpParser.parseHeaders(this.in, str);
            if (this.method != null) {
                for (Header addResponseFooter : parseHeaders) {
                    this.method.addResponseFooter(addResponseFooter);
                }
            }
        } catch (Throwable e) {
            LOG.error("Error parsing trailer headers", e);
            Throwable iOException = new IOException(e.getMessage());
            ExceptionUtil.initCause(iOException, e);
            throw iOException;
        }
    }

    public void close() throws IOException {
        if (!this.closed) {
            try {
                if (!this.eof) {
                    exhaustInputStream(this);
                }
                this.eof = true;
                this.closed = true;
            } catch (Throwable th) {
                this.eof = true;
                this.closed = true;
            }
        }
    }

    static void exhaustInputStream(InputStream inputStream) throws IOException {
        do {
        } while (inputStream.read(new byte[1024]) >= 0);
    }
}
