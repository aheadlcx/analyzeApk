package cz.msebera.android.httpclient.entity.mime;

import cz.msebera.android.httpclient.Consts;
import java.nio.charset.Charset;

public final class MIME {
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String CONTENT_TRANSFER_ENC = "Content-Transfer-Encoding";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final Charset DEFAULT_CHARSET = Consts.ASCII;
    public static final String ENC_8BIT = "8bit";
    public static final String ENC_BINARY = "binary";
    public static final Charset UTF8_CHARSET = Consts.UTF_8;
}
