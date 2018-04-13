package cn.xiaochuankeji.tieba.ui.post;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.post.Post.PostVote;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.net.a.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import cn.xiaochuankeji.tieba.background.post.q;
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.g;
import org.json.JSONException;
import org.json.JSONObject;

public class h extends f {
    protected ListView a;
    private long b;
    private long c;
    private int d;

    public static h a(long j, long j2, int i) {
        h hVar = new h();
        Bundle bundle = new Bundle();
        bundle.putLong("kPostIdKey", j);
        bundle.putLong("kVoteIdKey", j2);
        bundle.putInt("kPositionKey", i);
        hVar.setArguments(bundle);
        return hVar;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = getArguments().getLong("kPostIdKey");
        this.c = getArguments().getLong("kVoteIdKey");
        this.d = getArguments().getInt("kPositionKey");
        b();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_vote_detail, viewGroup, false);
        this.a = (ListView) inflate.findViewById(R.id.list);
        TextView textView = (TextView) inflate.findViewById(R.id.tvEmpty);
        textView.setText("");
        e.a(textView, (int) R.drawable.icon_common_empty);
        this.a.setEmptyView(textView);
        return inflate;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void onPause() {
        super.onPause();
    }

    private void b() {
        g.a(getActivity());
        new q(this.b, this.c, null, new b<JSONObject>(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                try {
                    this.a.a.setAdapter(new g(this.a.getActivity(), new PostVote(jSONObject.getJSONObject("vote")), this.a.d));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                g.c(this.a.getActivity());
            }
        }, new a(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.c(this.a.getActivity());
            }
        }).execute();
    }
}
