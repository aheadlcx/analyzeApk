package okhttp3.internal.http2;

import java.io.IOException;
import java.util.List;
import okio.BufferedSource;

public interface k {
    public static final k a = new k() {
        public boolean a(int i, List<a> list) {
            return true;
        }

        public boolean a(int i, List<a> list, boolean z) {
            return true;
        }

        public boolean a(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException {
            bufferedSource.skip((long) i2);
            return true;
        }

        public void a(int i, ErrorCode errorCode) {
        }
    };

    void a(int i, ErrorCode errorCode);

    boolean a(int i, List<a> list);

    boolean a(int i, List<a> list, boolean z);

    boolean a(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException;
}
