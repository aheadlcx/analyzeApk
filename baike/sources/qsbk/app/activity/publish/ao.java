package qsbk.app.activity.publish;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

class ao implements OnCheckedChangeListener {
    final /* synthetic */ PublishActivity a;

    ao(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.a.a(z);
    }
}
