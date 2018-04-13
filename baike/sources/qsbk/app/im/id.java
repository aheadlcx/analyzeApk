package qsbk.app.im;

import qsbk.app.utils.image.issue.Logger;

class id implements IOnConnectHostResp {
    final /* synthetic */ String a;
    final /* synthetic */ IMPreConnector b;

    id(IMPreConnector iMPreConnector, String str) {
        this.b = iMPreConnector;
        this.a = str;
    }

    public void onHostCallback(String str) {
        Logger.getInstance().debug("IMPreConnector", this.a, "Try connect to server on onPushNotify .");
        this.b.b.destroyConnectHost(this);
    }
}
