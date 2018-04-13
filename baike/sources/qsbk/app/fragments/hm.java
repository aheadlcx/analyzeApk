package qsbk.app.fragments;

import qsbk.app.http.SimpleCallBack;
import qsbk.app.http.SimpleHttpTask;

class hm extends SimpleHttpTask {
    final /* synthetic */ NearbyUsersFragment a;

    hm(NearbyUsersFragment nearbyUsersFragment, String str, SimpleCallBack simpleCallBack) {
        this.a = nearbyUsersFragment;
        super(str, simpleCallBack);
    }

    protected String a(Void... voidArr) {
        if (this.a.t == 1) {
            this.a.g();
        }
        return super.a(voidArr);
    }
}
