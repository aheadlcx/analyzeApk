package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.topic.b;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.topic.TopicMemberInfoBean;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.MultipleLineEllipsisTextView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import java.util.List;
import rx.j;

public class a extends Adapter<a> {
    private final Context a;
    private final long b;
    private List<TopicMemberInfoBean> c;
    private b d = new b();
    private LayoutInflater e;

    public class a extends ViewHolder {
        WebImageView a;
        TextView b;
        TextView c;
        TextView d;
        Button e;
        Button f;
        MultipleLineEllipsisTextView g;
        final /* synthetic */ a h;
        private LinearLayout i;
        private View j;

        public a(a aVar, View view) {
            this.h = aVar;
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
                this.g.a("申请理由：" + str, null, -1, -6710887, 5);
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
                    final /* synthetic */ a c;

                    public void onClick(final View view) {
                        String str = "";
                        if (view.getId() == R.id.btn_agree) {
                            str = "agree";
                        } else if (view.getId() == R.id.btn_deny) {
                            str = "refuse";
                        }
                        this.c.h.d.a(this.c.h.b, topicMemberInfoBean.getId(), str).a(rx.a.b.a.a()).b(new j<Void>(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public /* synthetic */ void onNext(Object obj) {
                                a((Void) obj);
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

                            public void a(Void voidR) {
                                if (view.getId() == R.id.btn_agree) {
                                    topicMemberInfoBean.applyAgreed = true;
                                } else if (view.getId() == R.id.btn_deny) {
                                    topicMemberInfoBean.applyDenied = true;
                                }
                                this.b.c.h.notifyItemChanged(i);
                            }
                        });
                    }
                };
                this.f.setOnClickListener(anonymousClass1);
                this.e.setOnClickListener(anonymousClass1);
                this.a.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a b;

                    public void onClick(View view) {
                        MemberDetailActivity.a(this.b.h.a, topicMemberInfoBean.getId());
                    }
                });
            }
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public a(Context context, long j) {
        this.a = context;
        this.b = j;
        this.e = LayoutInflater.from(context);
    }

    public void a(List<TopicMemberInfoBean> list) {
        this.c = list;
        notifyDataSetChanged();
    }

    public void b(List<TopicMemberInfoBean> list) {
        this.c.addAll(list);
        notifyItemRangeInserted(this.c.size() - list.size(), this.c.size() - 1);
    }

    public a a(ViewGroup viewGroup, int i) {
        return new a(this, this.e.inflate(R.layout.item_apply_escort, viewGroup, false));
    }

    public void a(a aVar, int i) {
        if (i >= 0 || i < this.c.size()) {
            aVar.a((TopicMemberInfoBean) this.c.get(i), i);
        }
    }

    public int getItemCount() {
        if (this.c == null) {
            return 0;
        }
        return this.c.size();
    }
}
