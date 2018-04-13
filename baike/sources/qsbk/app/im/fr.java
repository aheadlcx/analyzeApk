package qsbk.app.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

class fr extends BroadcastReceiver {
    final /* synthetic */ IMChatBaseActivityEx a;

    fr(IMChatBaseActivityEx iMChatBaseActivityEx) {
        this.a = iMChatBaseActivityEx;
    }

    public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(intent.getStringExtra("conversationId"), this.a.getToId())) {
            ChatMsgVoiceData chatMsgVoiceData = new ChatMsgVoiceData(intent.getStringExtra("voice_data"), (int) intent.getLongExtra("voice_duration", 0));
            ContactListItem newContactItem = this.a.newContactItem();
            this.a.g.appendItem(this.a.z.newVoiceChatMsg(newContactItem, chatMsgVoiceData, this.a.s, newContactItem.isGroupMsg() ? 3 : 0));
            this.a.g.notifyDataSetChanged();
            this.a.y.postDelayed(new fs(this), 100);
        }
    }
}
