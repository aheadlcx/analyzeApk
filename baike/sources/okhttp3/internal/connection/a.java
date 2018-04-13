package okhttp3.internal.connection;

import java.io.IOException;
import okhttp3.internal.ws.RealWebSocket.Streams;
import okio.BufferedSink;
import okio.BufferedSource;

class a extends Streams {
    final /* synthetic */ StreamAllocation a;
    final /* synthetic */ RealConnection b;

    a(RealConnection realConnection, boolean z, BufferedSource bufferedSource, BufferedSink bufferedSink, StreamAllocation streamAllocation) {
        this.b = realConnection;
        this.a = streamAllocation;
        super(z, bufferedSource, bufferedSink);
    }

    public void close() throws IOException {
        this.a.streamFinished(true, this.a.codec(), -1, null);
    }
}
