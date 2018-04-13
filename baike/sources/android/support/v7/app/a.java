package android.support.v7.app;

import android.view.View;
import android.view.View.OnClickListener;

class a implements OnClickListener {
    final /* synthetic */ ActionBarDrawerToggle a;

    a(ActionBarDrawerToggle actionBarDrawerToggle) {
        this.a = actionBarDrawerToggle;
    }

    public void onClick(View view) {
        if (this.a.a) {
            this.a.a();
        } else if (this.a.b != null) {
            this.a.b.onClick(view);
        }
    }
}
