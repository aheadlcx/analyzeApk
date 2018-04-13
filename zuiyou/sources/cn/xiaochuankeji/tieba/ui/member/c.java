package cn.xiaochuankeji.tieba.ui.member;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView.EmptyPaddingStyle;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.topic.TopicFollowedByUserList;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.k;

public class c extends Fragment implements OnItemClickListener {
    private TopicFollowedByUserList a;

    public static c a(long j) {
        Bundle bundle = new Bundle();
        c cVar = new c();
        bundle.putLong("key_user_id", j);
        cVar.setArguments(bundle);
        return cVar;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.a = new TopicFollowedByUserList(getArguments().getLong("key_user_id"));
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View a = a();
        a.a(this.a, new k(getActivity(), this.a));
        a.m().setOnItemClickListener(this);
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

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        TopicDetailActivity.a(getActivity(), (Topic) this.a.itemAt(i - 1), "member_topic");
    }
}
