package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.StatService;
import qsbk.app.model.FoundFragementItem.Game;

class gs implements OnClickListener {
    final /* synthetic */ Game a;
    final /* synthetic */ c b;

    gs(c cVar, Game game) {
        this.b = cVar;
        this.a = game;
    }

    public void onClick(View view) {
        StatService.onEvent(view.getContext(), "found_click", "found_item_game_" + this.a.pos + "_click");
        MyProfileFragment.d(this.b.a.b.getActivity(), this.a.link, this.a.name);
    }
}
