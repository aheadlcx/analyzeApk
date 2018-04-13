package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class js implements OnClickListener {
    final /* synthetic */ CreateNewGroupActivity a;

    js(CreateNewGroupActivity createNewGroupActivity) {
        this.a = createNewGroupActivity;
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this.a.getApplicationContext(), FillGroupInfoActivity.class);
        this.a.startActivity(intent);
    }
}
