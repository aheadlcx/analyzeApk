package qsbk.app.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.activity.ClockedRankingActivity.ClockedRanking;
import qsbk.app.im.IMChatMsgSource;

class jf implements OnItemClickListener {
    final /* synthetic */ ClockedRankingActivity a;

    jf(ClockedRankingActivity clockedRankingActivity) {
        this.a = clockedRankingActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ClockedRanking clockedRanking = (ClockedRanking) this.a.e.get(i);
        MyInfoActivity.launch(this.a, clockedRanking.userId, MyInfoActivity.FANS_ORIGINS[1], new IMChatMsgSource(8, clockedRanking.userId, "来自糗友圈"));
    }
}
