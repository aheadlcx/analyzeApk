package qsbk.app.im;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.activity.LaiseeSendActivity;
import qsbk.app.utils.ToastAndDialog;

class ez implements OnItemClickListener {
    final /* synthetic */ GroupConversationActivity a;

    ez(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        switch (i) {
            case 0:
                this.a.startImagePicker(this.a.Y);
                return;
            case 1:
                if (this.a.au != null) {
                    LaiseeSendActivity.launchTribe(this.a, this.a.getToId(), this.a.au.memberNum, 12);
                    return;
                } else {
                    ToastAndDialog.makeText(this.a, "正在加载群信息，请稍后再试").show();
                    return;
                }
            default:
                return;
        }
    }
}
