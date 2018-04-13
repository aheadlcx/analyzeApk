package com.facebook.stetho.inspector.network;

import com.facebook.stetho.inspector.console.CLog;
import com.facebook.stetho.inspector.protocol.module.Console.MessageLevel;
import com.facebook.stetho.inspector.protocol.module.Console.MessageSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.InflaterOutputStream;
import javax.annotation.Nullable;

public class DecompressionHelper {
    static final String DEFLATE_ENCODING = "deflate";
    static final String GZIP_ENCODING = "gzip";

    public static InputStream teeInputWithDecompression(NetworkPeerManager networkPeerManager, String str, InputStream inputStream, OutputStream outputStream, @Nullable String str2, ResponseHandler responseHandler) throws IOException {
        OutputStream create;
        CountingOutputStream countingOutputStream = null;
        if (str2 != null) {
            boolean equals = GZIP_ENCODING.equals(str2);
            boolean equals2 = DEFLATE_ENCODING.equals(str2);
            if (equals || equals2) {
                countingOutputStream = new CountingOutputStream(outputStream);
                if (equals) {
                    create = GunzippingOutputStream.create(countingOutputStream);
                } else if (equals2) {
                    create = new InflaterOutputStream(countingOutputStream);
                }
                return new ResponseHandlingInputStream(inputStream, str, create, countingOutputStream, networkPeerManager, responseHandler);
            }
            CLog.writeToConsole(networkPeerManager, MessageLevel.WARNING, MessageSource.NETWORK, "Unsupported Content-Encoding in response for request #" + str + ": " + str2);
        }
        create = outputStream;
        return new ResponseHandlingInputStream(inputStream, str, create, countingOutputStream, networkPeerManager, responseHandler);
    }
}
