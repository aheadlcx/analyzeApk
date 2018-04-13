package okhttp3.internal.ws;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.ws.RealWebSocket.Streams;

class b implements Callback {
    final /* synthetic */ Request a;
    final /* synthetic */ int b;
    final /* synthetic */ RealWebSocket c;

    b(RealWebSocket realWebSocket, Request request, int i) {
        this.c = realWebSocket;
        this.a = request;
        this.b = i;
    }

    public void onResponse(Call call, Response response) {
        try {
            this.c.a(response);
            StreamAllocation streamAllocation = Internal.instance.streamAllocation(call);
            streamAllocation.noNewStreams();
            Streams newWebSocketStreams = streamAllocation.connection().newWebSocketStreams(streamAllocation);
            try {
                this.c.a.onOpen(this.c, response);
                this.c.initReaderAndWriter("OkHttp WebSocket " + this.a.url().redact(), (long) this.b, newWebSocketStreams);
                streamAllocation.connection().socket().setSoTimeout(0);
                this.c.loopReader();
            } catch (Exception e) {
                this.c.failWebSocket(e, null);
            }
        } catch (Exception e2) {
            this.c.failWebSocket(e2, response);
            Util.closeQuietly(response);
        }
    }

    public void onFailure(Call call, IOException iOException) {
        this.c.failWebSocket(iOException, null);
    }
}
