package qsbk.app.live.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.adapter.FamilyAllMemberAdapter.ItemViewHolder;
import qsbk.app.live.model.FamilyMemberData;

class g implements OnClickListener {
    final /* synthetic */ ItemViewHolder a;
    final /* synthetic */ FamilyMemberData b;
    final /* synthetic */ FamilyAllMemberAdapter c;

    g(FamilyAllMemberAdapter familyAllMemberAdapter, ItemViewHolder itemViewHolder, FamilyMemberData familyMemberData) {
        this.c = familyAllMemberAdapter;
        this.a = itemViewHolder;
        this.b = familyMemberData;
    }

    public void onClick(View view) {
        this.c.a(this.a.ivMore, this.b);
    }
}
