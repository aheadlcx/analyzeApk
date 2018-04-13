package qsbk.app.im;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class gx implements OnClickListener {
    final /* synthetic */ ContactListItem a;
    final /* synthetic */ IMMessageListFragment b;

    gx(IMMessageListFragment iMMessageListFragment, ContactListItem contactListItem) {
        this.b = iMMessageListFragment;
        this.a = contactListItem;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.b.a(this.a);
    }
}
