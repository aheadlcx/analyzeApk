package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;

class ee implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ QiuyouCircleTab b;

    ee(QiuyouCircleTab qiuyouCircleTab, int i) {
        this.b = qiuyouCircleTab;
        this.a = i;
    }

    public void onClick(View view) {
        if (QiuyouCircleTab.a(this.b) != null) {
            QiuyouCircleTab.a(this.b).onTabClickListener(this.a);
        }
        this.b.setSelectedTab(this.a);
    }
}
