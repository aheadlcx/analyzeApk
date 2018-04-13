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
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.topic.TopicMemberInfoBean;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.deleteList.TopicDeletePostActivity;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.izuiyou.a.a.b;
import java.util.List;
import rx.j;

public class e extends Adapter<a> {
    List<TopicMemberInfoBean> a;
    private final Context b;
    private int c;
    private long d;
    private LayoutInflater e;

    public static class a extends ViewHolder {
        WebImageView a;
        TextView b;
        TextView c;
        ImageView d;
        ImageView e;
        TextView f;
        TextView g;

        public a(View view) {
            super(view);
            this.a = (WebImageView) view.findViewById(R.id.avatar_admin);
            this.b = (TextView) view.findViewById(R.id.name_admin);
            this.c = (TextView) view.findViewById(R.id.tv_describe);
            this.d = (ImageView) view.findViewById(R.id.iv_level_holder_logo);
            this.e = (ImageView) view.findViewById(R.id.iv_level_admin_logo);
            this.f = (TextView) view.findViewById(R.id.btn_fire);
            this.g = (TextView) view.findViewById(R.id.btn_check);
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public e(Context context, int i, long j) {
        this.b = context;
        this.c = i;
        this.d = j;
        this.e = LayoutInflater.from(context);
    }

    public void a(List<TopicMemberInfoBean> list) {
        this.a = list;
        notifyDataSetChanged();
    }

    public a a(ViewGroup viewGroup, int i) {
        return new a(this.e.inflate(R.layout.item_admin, viewGroup, false));
    }

    public void a(final a aVar, int i) {
        b.b("position:" + i);
        if (this.a != null && i >= 0 && i < this.a.size()) {
            final TopicMemberInfoBean topicMemberInfoBean = (TopicMemberInfoBean) this.a.get(i);
            aVar.a.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(topicMemberInfoBean.getId(), topicMemberInfoBean.getAvatarId()));
            aVar.b.setText(d.b(topicMemberInfoBean.getNickName()));
            if (topicMemberInfoBean.getTopicRole() == 4) {
                aVar.d.setVisibility(0);
                aVar.e.setVisibility(8);
            } else {
                aVar.e.setVisibility(0);
                aVar.d.setVisibility(8);
            }
            aVar.itemView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ e b;

                public void onClick(View view) {
                    MemberDetailActivity.a(this.b.b, ((TopicMemberInfoBean) this.b.a.get(aVar.getAdapterPosition())).getId());
                }
            });
            aVar.g.setVisibility(8);
            aVar.c.setVisibility(8);
            if (this.c != 4 || topicMemberInfoBean.getTopicRole() < 2) {
                aVar.f.setVisibility(8);
                return;
            }
            aVar.f.setVisibility(0);
            if (topicMemberInfoBean.getTopicRole() == 2) {
                aVar.f.setText("辞退");
                aVar.f.setWidth(cn.xiaochuankeji.tieba.ui.utils.e.a(48.0f));
                aVar.c.setVisibility(0);
                aVar.c.setText(topicMemberInfoBean.getEscortDescribe());
                aVar.g.setVisibility(0);
                aVar.g.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ e b;

                    public void onClick(View view) {
                        TopicDeletePostActivity.a(this.b.b, topicMemberInfoBean, this.b.d);
                    }
                });
            } else {
                aVar.f.setText("申请辞职");
                aVar.f.setWidth(cn.xiaochuankeji.tieba.ui.utils.e.a(69.0f));
            }
            aVar.f.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ e c;

                public void onClick(View view) {
                    final cn.xiaochuankeji.tieba.api.topic.b bVar = new cn.xiaochuankeji.tieba.api.topic.b();
                    if (topicMemberInfoBean.getTopicRole() == 2) {
                        f.a("提示", "确定辞退吗?", (Activity) this.c.b, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                            final /* synthetic */ AnonymousClass3 b;

                            public void a(boolean z) {
                                if (z) {
                                    bVar.a(this.b.c.d, topicMemberInfoBean.getId(), "fire").a(rx.a.b.a.a()).b(new j<Void>(this) {
                                        final /* synthetic */ AnonymousClass1 a;

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
                                            this.a.b.c.a.remove(adapterPosition);
                                            this.a.b.c.notifyItemRemoved(adapterPosition);
                                        }
                                    });
                                }
                            }
                        });
                    } else if (topicMemberInfoBean.getTopicRole() == 4) {
                        f.a("提示", "确定辞退吗?", (Activity) this.c.b, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                            final /* synthetic */ AnonymousClass3 b;

                            public void a(boolean z) {
                                if (z) {
                                    bVar.a(this.b.c.d, "retire").a(rx.a.b.a.a()).b(new j<EmptyJson>(this) {
                                        final /* synthetic */ AnonymousClass2 a;

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
                                                g.a("网络异常");
                                            }
                                        }

                                        public void a(EmptyJson emptyJson) {
                                            g.a("已申请成功，正在等待审核");
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    public int getItemCount() {
        if (this.a != null) {
            return this.a.size();
        }
        return 0;
    }
}
