package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.v6.sixrooms.base.SixRoomsUtils;
import cn.v6.sixrooms.pojo.HistroyWatch;

final class bd implements OnItemClickListener {
    final /* synthetic */ HistoryActivity a;

    bd(HistoryActivity historyActivity) {
        this.a = historyActivity;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        HistroyWatch item = this.a.c.getItem(i);
        if (item != null) {
            SixRoomsUtils.gotoRoomActivity(this.a, item.getRid(), item.getUid());
        }
    }
}
