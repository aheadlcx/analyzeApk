package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.GroupInfoActivity;
import qsbk.app.utils.SharePreferenceUtils;

class ey implements OnClickListener {
    final /* synthetic */ GroupConversationActivity a;

    ey(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void onClick(View view) {
        this.a.aF = true;
        if (!GroupConversationActivity.groupSets.contains(Integer.valueOf(this.a.au.id))) {
            GroupConversationActivity.groupSets.add(String.valueOf(this.a.au.id));
            SharePreferenceUtils.setClickGroupRemind(GroupConversationActivity.groupSets);
        }
        if (this.a.at) {
            this.a.finish();
        } else {
            GroupInfoActivity.launch(this.a, Integer.parseInt(this.a.getToId()), this.a.getToNick(), true);
        }
    }
}
