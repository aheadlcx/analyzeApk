package qsbk.app.live.widget;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.ui.LevelGiftPayActivity;

class go implements OnClickListener {
    final /* synthetic */ LevelGiftDialog a;

    go(LevelGiftDialog levelGiftDialog) {
        this.a = levelGiftDialog;
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("boxid", this.a.j.boxid);
        intent.setClass(this.a.i, LevelGiftPayActivity.class);
        this.a.i.startActivity(intent);
        this.a.dismiss();
    }
}
