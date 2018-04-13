package cn.xiaochuankeji.tieba.ui.post;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.htjyb.b.a.d;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView.EmptyPaddingStyle;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.i;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class a extends i implements OnItemClickListener {
    private QueryListView b;
    private c c;
    private d<Member> d;

    protected void b() {
        this.b.h();
    }

    protected QueryListView c() {
        this.b = new QueryListView(getActivity());
        this.b.m().setPadding(0, e.a(9.0f), 0, e.a(9.0f));
        this.b.m().setClipToPadding(false);
        this.b.a("空空如也~", (int) R.drawable.ic_post_empty, EmptyPaddingStyle.GoldenSection);
        return this.b;
    }

    protected void d() {
        this.d = ((LikedUsersActivity) getActivity()).j();
        this.c = new c(getActivity(), this.d);
        this.b.a(this.d, this.c);
        this.b.m().setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - this.b.m().getHeaderViewsCount();
        if (headerViewsCount < this.d.itemCount()) {
            Member member = (Member) this.d.itemAt(headerViewsCount);
            if (member.isRegistered()) {
                MemberDetailActivity.a(getActivity(), member.getId());
            } else {
                g.a(getResources().getString(R.string.unregister_tip));
            }
        }
    }
}
