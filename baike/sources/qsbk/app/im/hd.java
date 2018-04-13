package qsbk.app.im;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.activity.GroupNoticeActivity;
import qsbk.app.activity.NewFansActivity;
import qsbk.app.live.ui.family.FamilyMessageActivity;

class hd implements OnItemClickListener {
    final /* synthetic */ IMMessageListFragment a;

    hd(IMMessageListFragment iMMessageListFragment) {
        this.a = iMMessageListFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (j <= ((long) (this.a.i.size() - 1))) {
            ContactListItem contactListItem = (ContactListItem) this.a.i.get((int) j);
            Intent intent;
            if (contactListItem.type == 2 || contactListItem.id.equalsIgnoreCase(NewFansActivity.NEWFANS_ID)) {
                intent = new Intent(this.a.getActivity(), NewFansActivity.class);
                NewFansActivity.NEWFANS_ID = contactListItem.id;
                this.a.startActivity(intent);
            } else if (contactListItem.isGroupNotice()) {
                this.a.m.markAllMessagesToRead(contactListItem.unreadCount);
                GroupNoticeActivity.launch(this.a.getActivity());
            } else if (contactListItem.type == 7) {
                intent = new Intent(this.a.getActivity(), FamilyMessageActivity.class);
                intent.putExtra("unreadCount", contactListItem.unreadCount);
                this.a.startActivity(intent);
                this.a.c(contactListItem);
                contactListItem.unreadCount = 0;
                this.a.q.notifyDataSetChanged();
            } else {
                Intent intent2 = new Intent(this.a.getActivity(), contactListItem.type == 3 ? GroupConversationActivity.class : ConversationActivity.class);
                intent2.putExtra(IMChatBaseActivity.USER_TYPE, contactListItem.type);
                intent2.putExtra("user_id", this.a.j);
                intent2.putExtra("to_id", contactListItem.id);
                intent2.putExtra(IMChatBaseActivity.TO_ICON, contactListItem.icon);
                intent2.putExtra(IMChatBaseActivity.TO_NICK, contactListItem.name);
                if (contactListItem.atMsgId != 0) {
                    intent2.putExtra("at_id", contactListItem.atMsgId);
                    intent2.putExtra("at_type", contactListItem.atType);
                }
                intent2.putExtra("unreadCount", contactListItem.unreadCount);
                this.a.startActivity(intent2);
                if (contactListItem.atMsgId != 0) {
                    contactListItem.atMsgId = 0;
                    this.a.n.update(contactListItem);
                }
                contactListItem.unreadCount = 0;
                this.a.q.notifyDataSetChanged();
            }
        }
    }
}
