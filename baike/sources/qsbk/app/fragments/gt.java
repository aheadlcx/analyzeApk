package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.StatService;
import qsbk.app.model.FoundFragementItem.FoundGame;

class gt implements OnClickListener {
    final /* synthetic */ FoundGame a;
    final /* synthetic */ d b;

    gt(d dVar, FoundGame foundGame) {
        this.b = dVar;
        this.a = foundGame;
    }

    public void onClick(View view) {
        StatService.onEvent(view.getContext(), "found_click", "found_item_game_head");
        MyProfileFragment.d(this.b.a.b.getActivity(), this.a.link, "糗百游戏中心");
    }
}
