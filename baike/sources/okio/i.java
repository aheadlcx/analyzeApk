package okio;

import com.alipay.sdk.data.a;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import javax.annotation.Nullable;

final class i extends AsyncTimeout {
    final /* synthetic */ Socket a;

    i(Socket socket) {
        this.a = socket;
    }

    protected IOException a(@Nullable IOException iOException) {
        IOException socketTimeoutException = new SocketTimeoutException(a.f);
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    protected void a() {
        try {
            this.a.close();
        } catch (Throwable e) {
            Okio.a.log(Level.WARNING, "Failed to close timed out socket " + this.a, e);
        } catch (AssertionError e2) {
            if (Okio.a(e2)) {
                Okio.a.log(Level.WARNING, "Failed to close timed out socket " + this.a, e2);
                return;
            }
            throw e2;
        }
    }
}
