package qsbk.app.live.ui.share;

public class ShareCallbackHelper {
    private static ShareCallbackHelper a;
    private ShareCallback b;

    public interface ShareCallback {
        void onShareSuccess(String str);
    }

    private ShareCallbackHelper() {
    }

    public static synchronized ShareCallbackHelper getInstance() {
        ShareCallbackHelper shareCallbackHelper;
        synchronized (ShareCallbackHelper.class) {
            if (a == null) {
                a = new ShareCallbackHelper();
            }
            shareCallbackHelper = a;
        }
        return shareCallbackHelper;
    }

    public void notifyShareSuccess(String str) {
        if (this.b != null) {
            this.b.onShareSuccess(str);
        }
    }

    public void setShareCallback(ShareCallback shareCallback) {
        this.b = shareCallback;
    }
}
