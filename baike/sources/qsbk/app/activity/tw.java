package qsbk.app.activity;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

class tw implements OnCheckedChangeListener {
    final /* synthetic */ MemberTitleActivity a;

    tw(MemberTitleActivity memberTitleActivity) {
        this.a = memberTitleActivity;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.a.e.setVisibility(z ? 0 : 8);
    }
}
