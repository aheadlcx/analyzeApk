package qsbk.app.thirdparty.net;

import android.util.Pair;
import qsbk.app.core.AsyncTask;
import qsbk.app.thirdparty.ThirdPartyException;
import qsbk.app.thirdparty.ThirdPartyParameters;

final class a extends AsyncTask<String, Void, Pair<Integer, String>> {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ ThirdPartyParameters c;
    final /* synthetic */ RequestListener d;

    a(String str, String str2, ThirdPartyParameters thirdPartyParameters, RequestListener requestListener) {
        this.a = str;
        this.b = str2;
        this.c = thirdPartyParameters;
        this.d = requestListener;
    }

    protected Pair<Integer, String> a(String... strArr) {
        try {
            this.d.onComplete(HttpManager.openUrl(this.a, this.b, this.c));
        } catch (ThirdPartyException e) {
            this.d.onError(e);
            e.printStackTrace();
        }
        return null;
    }

    protected void a(Pair<Integer, String> pair) {
    }
}
