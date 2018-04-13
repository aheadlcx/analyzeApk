package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.nearby.ui.ShakeDialogFragment;

class bp implements OnClickListener {
    final /* synthetic */ ConversationActivity a;

    bp(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public void onClick(View view) {
        if (this.a.mRelationship == Relationship.NO_REL_CHATED) {
            ShakeDialogFragment newInstance = ShakeDialogFragment.newInstance(this.a.getToNick());
            newInstance.setOnShakedListener(new bq(this));
            newInstance.show(this.a.getSupportFragmentManager(), ShakeDialogFragment.class.getSimpleName());
            newInstance.setCancelable(false);
            return;
        }
        this.a.a(0, 0);
    }
}
