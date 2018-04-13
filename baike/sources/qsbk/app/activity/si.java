package qsbk.app.activity;

import android.text.TextUtils;
import qsbk.app.R;
import qsbk.app.widget.TabPanel.OnTabClickListener;

class si implements OnTabClickListener {
    final /* synthetic */ MainActivity a;

    si(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onTabClick(String str) {
        if (!TextUtils.equals(MainActivity.i(this.a), str)) {
            MainActivity.a(this.a, str, R.id.container);
        }
        if (MainActivity.j(this.a) != null) {
            MainActivity.a(this.a, MainActivity.j(this.a).findFragmentByTag(str));
        }
        MainActivity.a(this.a, str, MainActivity.k(this.a));
        MainActivity.a(this.a, str);
    }
}
