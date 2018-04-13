package qsbk.app.activity;

import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.view.View;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

class aad implements OnTabSelectedListener {
    final /* synthetic */ QiuYouActivity a;

    aad(QiuYouActivity qiuYouActivity) {
        this.a = qiuYouActivity;
    }

    public void onTabSelected(Tab tab) {
        View customView = tab.getCustomView();
        if (customView != null) {
            ((TextView) customView.findViewById(16908308)).setTextColor(UIHelper.isNightTheme() ? -4424933 : -17664);
            ((TextView) customView.findViewById(R.id.num)).setTextColor(-17899);
        }
    }

    public void onTabUnselected(Tab tab) {
        View customView = tab.getCustomView();
        if (customView != null) {
            ((TextView) customView.findViewById(16908308)).setTextColor(UIHelper.isNightTheme() ? -12171438 : -4671304);
            ((TextView) customView.findViewById(R.id.num)).setTextColor(-1761625579);
        }
    }

    public void onTabReselected(Tab tab) {
    }
}
