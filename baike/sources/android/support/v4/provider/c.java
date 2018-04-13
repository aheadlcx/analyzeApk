package android.support.v4.provider;

import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat.FontCallback;
import android.support.v4.provider.SelfDestructiveThread.ReplyCallback;

final class c implements ReplyCallback<a> {
    final /* synthetic */ FontCallback a;
    final /* synthetic */ Handler b;

    c(FontCallback fontCallback, Handler handler) {
        this.a = fontCallback;
        this.b = handler;
    }

    public void onReply(a aVar) {
        if (aVar.b == 0) {
            this.a.callbackSuccessAsync(aVar.a, this.b);
        } else {
            this.a.callbackFailAsync(aVar.b, this.b);
        }
    }
}
