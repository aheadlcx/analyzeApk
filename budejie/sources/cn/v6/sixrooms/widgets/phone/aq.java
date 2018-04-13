package cn.v6.sixrooms.widgets.phone;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.v6.sixrooms.bean.UserInfoBean;

final class aq implements OnItemClickListener {
    final /* synthetic */ SpectatorPage a;

    aq(SpectatorPage spectatorPage) {
        this.a = spectatorPage;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.showPublicMenu((UserInfoBean) this.a.w.getItem(i));
    }
}
