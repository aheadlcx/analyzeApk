package qsbk.app.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ListUtil;
import qsbk.app.widget.BaseCell;

class hd implements OnItemClickListener {
    final /* synthetic */ NearbyCircleFragment a;

    hd(NearbyCircleFragment nearbyCircleFragment) {
        this.a = nearbyCircleFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerCount = ListUtil.getHeaderCount(this.a.o);
        if (i >= headerCount) {
            headerCount = i - headerCount;
            if (QsbkApp.currentUser != null) {
                if (view.getTag() instanceof BaseCell) {
                    ((BaseCell) view.getTag()).onClick();
                }
            } else if (view.getTag() instanceof BaseCell) {
                ((BaseCell) view.getTag()).onClick();
            }
        }
    }
}
