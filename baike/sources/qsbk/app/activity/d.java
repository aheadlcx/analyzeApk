package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class d implements OnClickListener {
    final /* synthetic */ AboutSettingActivity a;

    d(AboutSettingActivity aboutSettingActivity) {
        this.a = aboutSettingActivity;
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this.a, About.class);
        intent.putExtra("targetPage", "about");
        this.a.startActivity(intent);
    }
}
