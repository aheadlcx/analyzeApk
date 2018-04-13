package cn.xiaochuankeji.tieba.ui.member;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView.EmptyPaddingStyle;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.comment.c;

public class a extends Fragment {
    private cn.xiaochuankeji.tieba.background.member.a a;
    private c b;

    public static a a(long j) {
        Bundle bundle = new Bundle();
        a aVar = new a();
        bundle.putLong("key_user_id", j);
        aVar.setArguments(bundle);
        return aVar;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.a = new cn.xiaochuankeji.tieba.background.member.a(getArguments().getLong("key_user_id"));
        this.b = new c(getActivity(), this.a);
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View a = a();
        a.a(this.a, this.b);
        if (this.a.itemCount() == 0) {
            this.a.refresh();
        }
        return a;
    }

    private QueryListView a() {
        QueryListView queryListView = new QueryListView(getActivity());
        queryListView.f();
        queryListView.m().setId(R.id.id_stickynavlayout_innerscrollview);
        queryListView.a("空空如也~", (int) R.drawable.ic_post_empty, EmptyPaddingStyle.PADDING20);
        return queryListView;
    }
}
