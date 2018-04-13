package qsbk.app.core.net.ssl;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import qsbk.app.core.utils.AppUtils;

public class SSLUtils {
    public static SSLSocketFactory newSslSocketFactory(int i, String str) {
        try {
            TrustManager[] trustManagerArr = new TrustManager[]{new SsX509TrustManager(AppUtils.getInstance().getAppContext().getResources().openRawResource(i), str)};
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, trustManagerArr, null);
            return instance.getSocketFactory();
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
