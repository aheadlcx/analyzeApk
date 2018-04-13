package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import cn.xiaochuankeji.tieba.json.topic.TopicMemberInfoBean;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import java.io.Serializable;
import java.util.List;

public class TopicEscortListActivity extends h {
    private List<MemberInfoBean> d;
    private a e;
    private View f;
    private RecyclerView g;

    private class a extends Adapter<b> {
        final /* synthetic */ TopicEscortListActivity a;

        private a(TopicEscortListActivity topicEscortListActivity) {
            this.a = topicEscortListActivity;
        }

        public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
            a((b) viewHolder, i);
        }

        public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return a(viewGroup, i);
        }

        public b a(ViewGroup viewGroup, int i) {
            return new b(LayoutInflater.from(this.a).inflate(R.layout.layout_topic_member_item, viewGroup, false));
        }

        public void a(b bVar, int i) {
            if (i < this.a.d.size() && i >= 0) {
                final MemberInfoBean memberInfoBean = (MemberInfoBean) this.a.d.get(i);
                if (memberInfoBean != null) {
                    bVar.b.setText(memberInfoBean.getNickName());
                    bVar.c.setImageResource(R.drawable.topic_guard_big_icon);
                    bVar.a.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(memberInfoBean.getId(), memberInfoBean.getAvatarId()));
                    bVar.itemView.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ a b;

                        public void onClick(View view) {
                            MemberDetailActivity.a(this.b.a, memberInfoBean.getId());
                        }
                    });
                }
            }
        }

        public int getItemCount() {
            if (this.a.d == null) {
                return 0;
            }
            return this.a.d.size();
        }
    }

    private static class b extends ViewHolder {
        WebImageView a;
        TextView b;
        ImageView c;

        public b(View view) {
            super(view);
            this.c = (ImageView) view.findViewById(R.id.iv_member_icon);
            this.a = (WebImageView) view.findViewById(R.id.iv_avatar_member);
            this.b = (TextView) view.findViewById(R.id.tv_memeber_name);
        }
    }

    public static void a(Context context, List<TopicMemberInfoBean> list) {
        Intent intent = new Intent(context, TopicEscortListActivity.class);
        intent.putExtra("escortList", (Serializable) list);
        context.startActivity(intent);
    }

    protected boolean a(Bundle bundle) {
        this.d = (List) getIntent().getSerializableExtra("escortList");
        return true;
    }

    protected int a() {
        return R.layout.activity_topic_escort_list;
    }

    protected void i_() {
        this.g = (RecyclerView) findViewById(R.id.escort_list);
        this.g.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.e = new a();
        this.g.setAdapter(this.e);
        this.f = findViewById(R.id.tip_link_role);
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ TopicEscortListActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Object d = cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/topic_manage/intro_team");
                if (!TextUtils.isEmpty(d)) {
                    WebActivity.a(this.a, cn.xiaochuan.jsbridge.b.a("护卫队", d));
                }
            }
        });
    }
}
