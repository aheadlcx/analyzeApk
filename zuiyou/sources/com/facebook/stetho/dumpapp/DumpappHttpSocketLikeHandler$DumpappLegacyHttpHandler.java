package com.facebook.stetho.dumpapp;

import com.facebook.stetho.server.SocketLike;
import com.facebook.stetho.server.http.HttpHandler;
import com.facebook.stetho.server.http.LightHttpBody;
import com.facebook.stetho.server.http.LightHttpRequest;
import com.facebook.stetho.server.http.LightHttpResponse;
import com.tencent.connect.common.Constants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

class DumpappHttpSocketLikeHandler$DumpappLegacyHttpHandler implements HttpHandler {
    private static final String CONTENT_TYPE = "application/octet-stream";
    private static final String QUERY_PARAM_ARGV = "argv";
    private static final String RESPONSE_HEADER_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private final Dumper mDumper;

    public DumpappHttpSocketLikeHandler$DumpappLegacyHttpHandler(Dumper dumper) {
        this.mDumper = dumper;
    }

    public boolean handleRequest(SocketLike socketLike, LightHttpRequest lightHttpRequest, LightHttpResponse lightHttpResponse) throws IOException {
        boolean equals = Constants.HTTP_POST.equals(lightHttpRequest.method);
        boolean z = !equals && Constants.HTTP_GET.equals(lightHttpRequest.method);
        if (z || equals) {
            List queryParameters = lightHttpRequest.uri.getQueryParameters(QUERY_PARAM_ARGV);
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Framer framer = new Framer(new ByteArrayInputStream(new byte[0]), byteArrayOutputStream);
            framer.getStderr().println("*** " + (equals ? "ERROR" : "WARNING") + ": Using legacy HTTP protocol; update dumpapp script! ***");
            if (z) {
                DumpappSocketLikeHandler.dump(this.mDumper, framer, (String[]) queryParameters.toArray(new String[queryParameters.size()]));
            } else {
                framer.writeExitCode(1);
            }
            lightHttpResponse.code = 200;
            lightHttpResponse.reasonPhrase = "OK";
            lightHttpResponse.addHeader("Access-Control-Allow-Origin", "*");
            lightHttpResponse.body = LightHttpBody.create(byteArrayOutputStream.toByteArray(), "application/octet-stream");
        } else {
            lightHttpResponse.code = 501;
            lightHttpResponse.reasonPhrase = "Not implemented";
            lightHttpResponse.body = LightHttpBody.create(lightHttpRequest.method + " not implemented", "text/plain");
        }
        return true;
    }
}
