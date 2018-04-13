package okhttp3.internal.http2;

import java.io.IOException;
import okhttp3.internal.http2.Http2Connection.Listener;

final class i extends Listener {
    i() {
    }

    public void onStream(Http2Stream http2Stream) throws IOException {
        http2Stream.close(ErrorCode.REFUSED_STREAM);
    }
}
