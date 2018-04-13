package qsbk.app.im;

import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;

class gr implements Runnable {
    final /* synthetic */ IMMessageListFragment a;

    gr(IMMessageListFragment iMMessageListFragment) {
        this.a = iMMessageListFragment;
    }

    public void run() {
        try {
            String str = Constants.REL_GET_FANS + "&clr_count=1";
            r1 = new Object[2];
            QsbkApp.getInstance();
            r1[0] = QsbkApp.currentUser.userId;
            r1[1] = Integer.valueOf(1);
            HttpClient.getIntentce().get(String.format(str, r1));
        } catch (QiushibaikeException e) {
            e.printStackTrace();
        }
    }
}
