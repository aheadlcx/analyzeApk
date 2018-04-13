package cn.xiaochuankeji.tieba.ui.topic;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.htjyb.b.a.b.b;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.xiaochuankeji.tieba.background.c.d;
import cn.xiaochuankeji.tieba.background.topic.TopicInvolvedRankMemberList;
import cn.xiaochuankeji.tieba.ui.base.i;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class m extends i implements OnItemClickListener, b {
    private n b;
    private TopicInvolvedRankMemberList c;
    private l d;
    private long e;

    public static m a(long j) {
        m mVar = new m();
        Bundle bundle = new Bundle();
        bundle.putLong("TOPIC_ID", j);
        mVar.setArguments(bundle);
        return mVar;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e = getArguments().getLong("TOPIC_ID");
    }

    public void a(String str) {
        this.c.setRequestTag(str);
        this.c.refresh();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.c = new TopicInvolvedRankMemberList(this.e);
        this.c.registerOnQueryFinishListener(this);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    protected void b() {
        this.b.h();
    }

    protected QueryListView c() {
        this.b = new n(getActivity());
        this.b.m().setPadding(0, e.a(9.0f), 0, e.a(9.0f));
        this.b.m().setClipToPadding(false);
        return this.b;
    }

    protected void d() {
        this.d = new l(getActivity(), this.c);
        this.b.a(this.c, this.d);
        this.b.m().setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (view.getTag() instanceof a) {
            MemberDetailActivity.a(getActivity(), ((d) this.c.itemAt(i - 1)).a);
        }
    }

    public void a(boolean z, boolean z2, String str) {
        if (z && z2) {
            this.b.m().setSelection(1);
        }
    }
}
