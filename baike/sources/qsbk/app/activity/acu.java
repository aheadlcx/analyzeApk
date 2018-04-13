package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class acu implements OnClickListener {
    final /* synthetic */ SmallPaperSettingActivity a;

    acu(SmallPaperSettingActivity smallPaperSettingActivity) {
        this.a = smallPaperSettingActivity;
    }

    public void onClick(View view) {
        this.a.startActivity(new Intent(this.a, TemporaryNoteActivity.class));
    }
}
