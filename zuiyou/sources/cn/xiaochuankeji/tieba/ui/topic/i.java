package cn.xiaochuankeji.tieba.ui.topic;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.topic.b;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.topic.TopicMemberInfoBean;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.deleteList.TopicDeletePostActivity;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import java.util.List;
import rx.j;

public class i extends Adapter<a> {
    private final Context a;
    private List<TopicMemberInfoBean> b;
    private int c;
    private long d;
    private LayoutInflater e;
    private b f = new b();

    public static class a extends ViewHolder {
        WebImageView a;
        TextView b;
        TextView c;
        ImageView d;
        TextView e;
        TextView f;

        public a(View view) {
            super(view);
            this.a = (WebImageView) view.findViewById(R.id.avatar_admin);
            this.b = (TextView) view.findViewById(R.id.name_admin);
            this.c = (TextView) view.findViewById(R.id.tv_describe);
            this.d = (ImageView) view.findViewById(R.id.iv_level_escort_logo);
            this.e = (TextView) view.findViewById(R.id.btn_fire);
            this.f = (TextView) view.findViewById(R.id.btn_check);
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public i(Context context, int i, long j) {
        this.a = context;
        this.c = i;
        this.d = j;
        this.e = LayoutInflater.from(context);
    }

    public void a(List<TopicMemberInfoBean> list) {
        this.b = list;
        notifyDataSetChanged();
    }

    public a a(ViewGroup viewGroup, int i) {
        return new a(this.e.inflate(R.layout.item_escort, viewGroup, false));
    }

    public void a(final a aVar, final int i) {
        if (this.b != null && i >= 0 && i < this.b.size()) {
            final TopicMemberInfoBean topicMemberInfoBean = (TopicMemberInfoBean) this.b.get(i);
            aVar.a.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(topicMemberInfoBean.getId(), topicMemberInfoBean.getAvatarId()));
            aVar.b.setText(d.b(topicMemberInfoBean.getNickName()));
            if (topicMemberInfoBean.getTopicRole() == 8) {
                aVar.d.setVisibility(0);
            } else {
                aVar.d.setVisibility(8);
            }
            aVar.c.setText(topicMemberInfoBean.getEscortDescribe());
            aVar.itemView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ i b;

                public void onClick(View view) {
                    MemberDetailActivity.a(this.b.a, ((TopicMemberInfoBean) this.b.b.get(aVar.getAdapterPosition())).getId());
                }
            });
            if (this.c == 4 && topicMemberInfoBean.getTopicRole() == 2) {
                aVar.e.setVisibility(0);
                aVar.e.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ i c;

                    public void onClick(View view) {
                        new b().a(this.c.d, topicMemberInfoBean.getId(), "fire").a(rx.a.b.a.a()).b(new j<Void>(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public /* synthetic */ void onNext(Object obj) {
                                a((Void) obj);
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

                            public void a(Void voidR) {
                                int adapterPosition = aVar.getAdapterPosition();
                                this.a.c.b.remove(adapterPosition);
                                this.a.c.notifyItemRemoved(adapterPosition);
                            }
                        });
                    }
                });
            }
            aVar.f.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ i b;

                public void onClick(View view) {
                    TopicDeletePostActivity.a(this.b.a, topicMemberInfoBean, this.b.d);
                }
            });
            aVar.e.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ i c;

                public void onClick(View view) {
                    f.a("提示", "确定辞退吗?", (Activity) this.c.a, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void a(boolean z) {
                            if (z) {
                                this.a.c.f.b(this.a.c.d, topicMemberInfoBean.getId(), "fire").a(rx.a.b.a.a()).b(new j<EmptyJson>(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

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
                                        g.a("辞退成功");
                                        this.a.a.c.b.remove(topicMemberInfoBean);
                                        this.a.a.c.notifyItemRemoved(i);
                                    }
                                });
                            }
                        }
                    });
                }
            });
        }
    }

    public int getItemCount() {
        if (this.b == null) {
            return 0;
        }
        return this.b.size();
    }
}
