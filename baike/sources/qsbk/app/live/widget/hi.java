package qsbk.app.live.widget;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.qiushibaike.statsdk.StatSDK;
import java.io.Serializable;
import java.util.List;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.ui.LivePullActivity;

class hi implements OnItemClickListener {
    final /* synthetic */ List a;
    final /* synthetic */ LivePullEndDialog b;

    hi(LivePullEndDialog livePullEndDialog, List list) {
        this.b = livePullEndDialog;
        this.a = list;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i < this.a.size() && !AppUtils.isFastDoubleClick()) {
            CommonVideo commonVideo = (CommonVideo) this.a.get(i);
            if (commonVideo.author != null) {
                StatSDK.onEvent(this.b.a, "live_recommend_click", commonVideo.author.getPlatformId() + "");
            }
            Intent intent = new Intent(this.b.a, LivePullActivity.class);
            intent.putExtra("live", (Serializable) this.a.get(i));
            this.b.a.startActivity(intent);
            ((LivePullActivity) this.b.a).doConfirm(false);
        }
    }
}
