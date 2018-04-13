package com.budejie.www.adapter.g.b;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.budejie.www.R;
import com.budejie.www.adapter.g.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.VoteData;
import com.budejie.www.busevent.VoteEvent;
import com.budejie.www.util.aa;
import com.budejie.www.widget.VoteView;
import de.greenrobot.event.EventBus;

public class v extends a<ListItemObject> {
    private VoteView e;

    public v(Context context, b bVar) {
        super(context, bVar);
        EventBus.getDefault().register(this);
    }

    public View a(ViewGroup viewGroup) {
        View inflate = View.inflate(this.a, R.layout.new_new_list_item_vote_layout, viewGroup);
        this.e = (VoteView) inflate.findViewById(R.id.post_item_vote_view);
        return inflate;
    }

    public void c() {
        try {
            VoteData voteData = ((ListItemObject) this.c).getVoteData();
            if (voteData == null || voteData.votes == null || voteData.votes.size() <= 0) {
                this.e.setVisibility(8);
                return;
            }
            this.e.a();
            this.e.setVisibility(0);
            this.e.setVoteData(voteData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
    }

    public void onEventMainThread(VoteEvent voteEvent) {
        if (this.c != null && ((ListItemObject) this.c).getVoteData() != null && voteEvent.a != null && !TextUtils.isEmpty(((ListItemObject) this.c).getVoteData().pid) && ((ListItemObject) this.c).getVoteData().pid.equals(voteEvent.a.pid)) {
            ((ListItemObject) this.c).setVoteData(voteEvent.a);
            aa.b("PostVoteView", "onEventMainThread voteData = " + ((ListItemObject) this.c).getVoteData().pid);
            try {
                c();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
