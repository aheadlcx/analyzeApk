package qsbk.app.activity;

import java.util.List;
import qsbk.app.im.ContactListItem;
import qsbk.app.im.datastore.Callback;

class qc implements Callback<List<ContactListItem>> {
    final /* synthetic */ InviteQiuYouActivity a;

    qc(InviteQiuYouActivity inviteQiuYouActivity) {
        this.a = inviteQiuYouActivity;
    }

    public void onFinished(List<ContactListItem> list) {
        this.a.a((List) list);
    }

    public void onFailure(Throwable th) {
        this.a.hideLoading();
    }
}
