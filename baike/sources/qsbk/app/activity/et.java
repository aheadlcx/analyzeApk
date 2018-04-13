package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.Calendar;

class et implements OnClickListener {
    final /* synthetic */ h a;

    et(h hVar) {
        this.a = hVar;
    }

    public void onClick(View view) {
        this.a.a.setCurrentDate(Calendar.getInstance());
    }
}
