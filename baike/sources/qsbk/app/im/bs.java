package qsbk.app.im;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.LaiseeSendActivity;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.ToastAndDialog;

class bs implements OnItemClickListener {
    final /* synthetic */ ConversationActivity a;

    bs(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        switch (i) {
            case 0:
                if ((this.a.getUserType() == 1 && this.a.getToId().equals("32879940")) || this.a.mRelationship == Relationship.FRIEND || this.a.isTemporary) {
                    this.a.startImagePicker(this.a.Y);
                    return;
                } else {
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "互粉后才可以发图...", Integer.valueOf(0)).show();
                    return;
                }
            case 1:
                LaiseeSendActivity.launchP2P(this.a, this.a.getToId(), 102);
                return;
            default:
                return;
        }
    }
}
