package qsbk.app.thirdparty.net;

import qsbk.app.core.AsyncTask;
import qsbk.app.thirdparty.ThirdPartyParameters;

public class AsyncWeiboRunner {
    public static void request(String str, ThirdPartyParameters thirdPartyParameters, String str2, RequestListener requestListener) {
        new a(str, str2, thirdPartyParameters, requestListener).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
    }

    public static void request(String str, ThirdPartyParameters thirdPartyParameters, String str2, RequestListener requestListener, byte[] bArr) {
        new b(str, str2, thirdPartyParameters, bArr, requestListener).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
    }
}
