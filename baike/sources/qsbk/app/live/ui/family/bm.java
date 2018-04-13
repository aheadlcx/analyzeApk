package qsbk.app.live.ui.family;

import android.content.Intent;
import android.support.v7.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.R;

class bm implements OnClickListener {
    final /* synthetic */ Message a;
    final /* synthetic */ MessageAdapter b;

    bm(MessageAdapter messageAdapter, Message message) {
        this.b = messageAdapter;
        this.a = message;
    }

    public void onClick(View view) {
        if (this.a.getStatus() == 0) {
            new Builder(this.b.b, R.style.Theme_AppCompat_Light_Dialog_Alert).setTitle(this.b.b.getString(R.string.family_agree_member, new Object[]{this.a.getUserName()})).setItems(new String[]{this.b.b.getString(R.string.family_agree), this.b.b.getString(R.string.family_deny), this.b.b.getString(R.string.family_ignore)}, new bn(this)).setNegativeButton(this.b.b.getString(R.string.setting_cancel), null).create().show();
            return;
        }
        Intent intent = new Intent(this.b.b, FamilyDetailActivity.class);
        intent.putExtra("familyId", this.a.family_id);
        this.b.b.startActivity(intent);
    }
}
