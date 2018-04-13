package qsbk.app.thirdparty.net;

import android.util.Pair;
import qsbk.app.core.AsyncTask;
import qsbk.app.thirdparty.ThirdPartyException;
import qsbk.app.thirdparty.ThirdPartyParameters;

final class b extends AsyncTask<String, Void, Pair<Integer, String>> {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ ThirdPartyParameters c;
    final /* synthetic */ byte[] d;
    final /* synthetic */ RequestListener e;

    b(String str, String str2, ThirdPartyParameters thirdPartyParameters, byte[] bArr, RequestListener requestListener) {
        this.a = str;
        this.b = str2;
        this.c = thirdPartyParameters;
        this.d = bArr;
        this.e = requestListener;
    }

    protected Pair<Integer, String> a(String... strArr) {
        try {
            this.e.onComplete(HttpManager.openUrl(this.a, this.b, this.c, this.d));
        } catch (ThirdPartyException e) {
            this.e.onError(e);
            e.printStackTrace();
        }
        return null;
    }

    protected void a(Pair<Integer, String> pair) {
    }
}
