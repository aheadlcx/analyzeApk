package qsbk.app.activity;

import qsbk.app.utils.ToastAndDialog;
import qsbk.app.wxapi.WXAuthHelper.OnWXAuthListener;
import qsbk.app.wxapi.WXAuthHelper.WXAuthException;
import qsbk.app.wxapi.WXAuthHelper.WXAuthToken;

class ActionBarLoginActivity$c implements OnWXAuthListener {
    final /* synthetic */ ActionBarLoginActivity a;

    private ActionBarLoginActivity$c(ActionBarLoginActivity actionBarLoginActivity) {
        this.a = actionBarLoginActivity;
    }

    public void onStart() {
        ActionBarLoginActivity.c(this.a, true);
    }

    public void onComplete(WXAuthToken wXAuthToken) {
        ActionBarLoginActivity.b(this.a);
        if (!ActionBarLoginActivity.p(this.a) && wXAuthToken != null && wXAuthToken.isValid()) {
            ActionBarLoginActivity.b(this.a, wXAuthToken.token, wXAuthToken.expiresIn + "", wXAuthToken.openId);
            ActionBarLoginActivity.b(this.a, wXAuthToken.token);
            ActionBarLoginActivity.c(this.a, wXAuthToken.expiresIn + "");
            ActionBarLoginActivity.d(this.a, wXAuthToken.openId);
        }
    }

    public void onError(WXAuthException wXAuthException) {
        ActionBarLoginActivity.b(this.a);
        if (wXAuthException != null) {
            String message = wXAuthException.getMessage();
            if (message != null) {
                ToastAndDialog.makeNegativeToast(this.a, message).show();
            }
        }
    }
}
