package cn.xiaochuankeji.tieba.ui.my.block;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.b.c;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.BlockUserJson;
import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import rx.j;

public class BlockUserActivity extends h {
    private RecyclerView d;
    private b e;
    private List<MemberInfoBean> f;
    private LayoutInflater g;
    private int h;
    private int i = -1;
    private int j = 0;
    private int k = 20;
    private cn.xiaochuankeji.tieba.api.topic.a l;
    private CustomEmptyView m;

    private static class a extends ViewHolder {
        WebImageView a;
        TextView b;
        View c;

        public a(View view) {
            super(view);
            this.a = (WebImageView) view.findViewById(R.id.avatar_applier);
            this.b = (TextView) view.findViewById(R.id.tv_name);
            view.findViewById(R.id.btn_agree).setVisibility(8);
            view.findViewById(R.id.btn_deny).setVisibility(8);
            this.c = view.findViewById(R.id.split_line);
        }
    }

    private class b extends Adapter<a> {
        final /* synthetic */ BlockUserActivity a;

        private b(BlockUserActivity blockUserActivity) {
            this.a = blockUserActivity;
        }

        public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
            a((a) viewHolder, i);
        }

        public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return a(viewGroup, i);
        }

        public a a(ViewGroup viewGroup, int i) {
            return new a(this.a.g.inflate(R.layout.item_apply_info, viewGroup, false));
        }

        public void a(a aVar, final int i) {
            final MemberInfoBean memberInfoBean = (MemberInfoBean) this.a.f.get(i);
            aVar.a.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(memberInfoBean.getId(), memberInfoBean.getAvatarId()));
            aVar.b.setText(d.b(memberInfoBean.getNickName()));
            aVar.itemView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b c;

                public void onClick(View view) {
                    f.a("解除拉黑", "将" + memberInfoBean.getNickName() + "从黑名单中移除", this.c.a, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void a(boolean z) {
                            if (z) {
                                new c(memberInfoBean.getId(), 1, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public /* synthetic */ void onResponse(Object obj, Object obj2) {
                                        a((JSONObject) obj, obj2);
                                    }

                                    public void a(JSONObject jSONObject, Object obj) {
                                        g.a("已移出黑名单");
                                        if (this.a.a.c.a.f.size() > i) {
                                            this.a.a.c.a.f.remove(i);
                                            this.a.a.c.a.e.notifyItemRemoved(i);
                                        }
                                    }
                                }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void onErrorResponse(XCError xCError, Object obj) {
                                        g.a(xCError.getMessage());
                                    }
                                }).execute();
                            }
                        }
                    }, true);
                }
            });
        }

        public int getItemCount() {
            return this.a.f.size();
        }
    }

    public static void a(Context context) {
        context.startActivity(new Intent(context, BlockUserActivity.class));
    }

    protected int a() {
        return R.layout.activity_block_users;
    }

    protected void i_() {
        this.d = (RecyclerView) findViewById(R.id.block_list_view);
        this.f = new ArrayList();
        this.g = LayoutInflater.from(this);
        this.e = new b();
        final LayoutManager linearLayoutManager = new LinearLayoutManager(this, 1, false);
        this.d.setLayoutManager(linearLayoutManager);
        this.d.setAdapter(this.e);
        this.d.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ BlockUserActivity b;

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 0 && this.b.h + 1 == this.b.e.getItemCount() && this.b.i > 0) {
                    this.b.j();
                }
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                this.b.h = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
        this.m = (CustomEmptyView) findViewById(R.id.custom_empty_view);
        this.m.a(R.drawable.ic_topic_empty_post, "暂时没有屏蔽的用户");
        j();
    }

    public void j() {
        this.l = new cn.xiaochuankeji.tieba.api.topic.a();
        this.l.a(this.j, this.k).a(rx.a.b.a.a()).b(new j<BlockUserJson>(this) {
            final /* synthetic */ BlockUserActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((BlockUserJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.a.a(this.a.e.getItemCount());
            }

            public void a(BlockUserJson blockUserJson) {
                if (blockUserJson != null) {
                    if (blockUserJson.blockList.size() == 0) {
                        this.a.a(this.a.e.getItemCount());
                        return;
                    }
                    this.a.i = blockUserJson.hasMore;
                    this.a.j = blockUserJson.offset;
                    if (this.a.f != null) {
                        int size = this.a.f.size();
                        this.a.f.addAll(blockUserJson.blockList);
                        this.a.e.notifyItemRangeInserted(size, blockUserJson.blockList.size());
                    }
                    this.a.a(this.a.e.getItemCount());
                }
            }
        });
    }

    private void a(int i) {
        if (i == 0) {
            this.m.b();
        } else {
            this.m.setVisibility(8);
        }
    }
}
