package qsbk.app.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class hm extends BroadcastReceiver {
    final /* synthetic */ IMMessageListFragment a;

    hm(IMMessageListFragment iMMessageListFragment) {
        this.a = iMMessageListFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (IMMessageListFragment.ACTION_DELETE_CONTACT_ITEM.equalsIgnoreCase(intent.getAction())) {
            String stringExtra = intent.getStringExtra(IMMessageListFragment.ACTION_DELETE_CONTACT_ITEM);
            if (stringExtra != null) {
                ContactListItem a = this.a.a(stringExtra, 1);
                if (a != null) {
                    this.a.a(a);
                }
            }
        }
    }
}
