package cn.xiaochuankeji.tieba.ui.a;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView.EmptyPaddingStyle;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.e.e;
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.post.PostQueryListView;

public class b extends f {
    private e a;
    private PostQueryListView b;

    public static b b() {
        Bundle bundle = new Bundle();
        b bVar = new b();
        bVar.setArguments(bundle);
        return bVar;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.a = new e();
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.b = c();
        this.b.a(this.a);
        if (this.a.itemCount() == 0) {
            this.a.refresh();
        }
        return this.b;
    }

    private PostQueryListView c() {
        PostQueryListView postQueryListView = new PostQueryListView(getActivity());
        postQueryListView.a("还没有哦,多去关注几个人吧", (int) R.drawable.ic_post_empty, EmptyPaddingStyle.PADDING20);
        return postQueryListView;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
    }

    public void a(boolean z) {
    }
}
