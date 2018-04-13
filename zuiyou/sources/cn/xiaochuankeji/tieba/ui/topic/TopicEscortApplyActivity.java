package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.topic.TopicMemberInfoBean;
import cn.xiaochuankeji.tieba.json.topic.TopicRoleApplyListJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.MultipleLineEllipsisTextView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import java.util.List;
import rx.j;

public class TopicEscortApplyActivity extends h {
    private RecyclerView d;
    private a e;
    private cn.xiaochuankeji.tieba.api.topic.b f;
    private long g;
    private long h = 0;
    private int i = 1;
    private List<TopicMemberInfoBean> j;
    private int k = 0;
    private RelativeLayout l;

    private class a extends Adapter<b> {
        final /* synthetic */ TopicEscortApplyActivity a;

        private a(TopicEscortApplyActivity topicEscortApplyActivity) {
            this.a = topicEscortApplyActivity;
        }

        public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
            a((b) viewHolder, i);
        }

        public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return a(viewGroup, i);
        }

        public b a(ViewGroup viewGroup, int i) {
            return new b(this.a, LayoutInflater.from(this.a).inflate(R.layout.item_apply_escort, viewGroup, false));
        }

        public void a(b bVar, int i) {
            if (i < this.a.j.size()) {
                bVar.a((TopicMemberInfoBean) this.a.j.get(i), i);
            }
        }

        public int getItemCount() {
            if (this.a.j == null) {
                return 0;
            }
            return this.a.j.size();
        }
    }

    private class b extends ViewHolder {
        WebImageView a;
        TextView b;
        TextView c;
        TextView d;
        Button e;
        Button f;
        MultipleLineEllipsisTextView g;
        final /* synthetic */ TopicEscortApplyActivity h;
        private LinearLayout i;
        private View j;

        public b(TopicEscortApplyActivity topicEscortApplyActivity, View view) {
            this.h = topicEscortApplyActivity;
            super(view);
            this.a = (WebImageView) view.findViewById(R.id.iv_avatar);
            this.b = (TextView) view.findViewById(R.id.tv_name);
            this.c = (TextView) view.findViewById(R.id.tv_agreed);
            this.d = (TextView) view.findViewById(R.id.tv_denied);
            this.e = (Button) view.findViewById(R.id.btn_agree);
            this.f = (Button) view.findViewById(R.id.btn_deny);
            this.g = (MultipleLineEllipsisTextView) view.findViewById(R.id.tv_reason);
            this.g.setEndDesc("更多");
            this.g.setEndDescColor(-12733185);
            this.i = (LinearLayout) view.findViewById(R.id.ll_tool);
            this.j = view.findViewById(R.id.split_line);
        }

        public void a(final TopicMemberInfoBean topicMemberInfoBean, final int i) {
            if (topicMemberInfoBean != null) {
                String str;
                this.a.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(topicMemberInfoBean.getId(), topicMemberInfoBean.getAvatarId()));
                this.b.setText(topicMemberInfoBean.getNickName());
                if (TextUtils.isEmpty(topicMemberInfoBean.getApplyReason())) {
                    str = "暂无理由";
                } else {
                    str = topicMemberInfoBean.getApplyReason();
                }
                this.g.setText("申请理由：" + str);
                this.i.setVisibility(8);
                this.c.setVisibility(8);
                this.d.setVisibility(8);
                if (topicMemberInfoBean.applyAgreed) {
                    this.c.setVisibility(0);
                } else if (topicMemberInfoBean.applyDenied) {
                    this.d.setVisibility(0);
                } else {
                    this.i.setVisibility(0);
                }
                OnClickListener anonymousClass1 = new OnClickListener(this) {
                    final /* synthetic */ b c;

                    public void onClick(final View view) {
                        String str = "";
                        if (view.getId() == R.id.btn_agree) {
                            str = "agree";
                        } else if (view.getId() == R.id.btn_deny) {
                            str = "refuse";
                        }
                        this.c.h.f.b(this.c.h.g, topicMemberInfoBean.getId(), str).a(rx.a.b.a.a()).b(new j<EmptyJson>(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public /* synthetic */ void onNext(Object obj) {
                                a((EmptyJson) obj);
                            }

                            public void onCompleted() {
                            }

                            public void onError(Throwable th) {
                                if (th instanceof ClientErrorException) {
                                    g.a(th.getMessage());
                                } else {
                                    g.a("网络错误");
                                }
                            }

                            public void a(EmptyJson emptyJson) {
                                if (view.getId() == R.id.btn_agree) {
                                    topicMemberInfoBean.applyAgreed = true;
                                } else if (view.getId() == R.id.btn_deny) {
                                    topicMemberInfoBean.applyDenied = true;
                                }
                                this.b.c.h.e.notifyItemChanged(i);
                            }
                        });
                    }
                };
                this.f.setOnClickListener(anonymousClass1);
                this.e.setOnClickListener(anonymousClass1);
                this.a.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ b b;

                    public void onClick(View view) {
                        MemberDetailActivity.a(this.b.h, topicMemberInfoBean.getId());
                    }
                });
            }
        }
    }

    public static void a(Context context, long j) {
        Intent intent = new Intent(context, TopicEscortApplyActivity.class);
        intent.putExtra("topicId", j);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.layout_topic_escort_list;
    }

    protected void i_() {
        this.l = (RelativeLayout) findViewById(R.id.rootView);
        this.d = (RecyclerView) findViewById(R.id.escort_list);
        final LayoutManager linearLayoutManager = new LinearLayoutManager(this, 1, false);
        this.d.setLayoutManager(linearLayoutManager);
        this.e = new a();
        this.d.setAdapter(this.e);
        this.d.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ TopicEscortApplyActivity b;

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 0 && this.b.k + 1 == this.b.e.getItemCount() && this.b.i > 0) {
                    this.b.j();
                }
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                this.b.k = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    protected boolean a(Bundle bundle) {
        if (this.j != null) {
            this.j.clear();
            this.j = null;
        }
        this.g = getIntent().getLongExtra("topicId", 0);
        this.f = new cn.xiaochuankeji.tieba.api.topic.b();
        j();
        return true;
    }

    private void j() {
        if (this.i != 0) {
            this.f.b(this.g, this.h).a(rx.a.b.a.a()).b(new j<TopicRoleApplyListJson>(this) {
                final /* synthetic */ TopicEscortApplyActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((TopicRoleApplyListJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    if (th instanceof ClientErrorException) {
                        g.a(th.getMessage());
                    } else {
                        g.a("网络异常");
                    }
                }

                public void a(TopicRoleApplyListJson topicRoleApplyListJson) {
                    if (topicRoleApplyListJson != null) {
                        this.a.i = topicRoleApplyListJson.hasMore;
                        this.a.h = topicRoleApplyListJson.lastTimestamp;
                        if (topicRoleApplyListJson.applyList != null) {
                            if (this.a.j == null) {
                                this.a.j = topicRoleApplyListJson.applyList;
                            } else {
                                this.a.j.addAll(topicRoleApplyListJson.applyList);
                            }
                            this.a.e.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
    }
}
