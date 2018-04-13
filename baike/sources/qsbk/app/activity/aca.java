package qsbk.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.io.Serializable;
import qsbk.app.im.ConversationActivity;
import qsbk.app.model.relationship.Relationship;

class aca extends BroadcastReceiver {
    final /* synthetic */ SearchQiuYouActivity a;

    aca(SearchQiuYouActivity searchQiuYouActivity) {
        this.a = searchQiuYouActivity;
    }

    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("userId");
        Serializable serializableExtra = intent.getSerializableExtra(ConversationActivity.RELATIONSHIP);
        if (serializableExtra != null) {
            this.a.k.updateRelationShip((Relationship) serializableExtra, stringExtra);
        }
    }
}
