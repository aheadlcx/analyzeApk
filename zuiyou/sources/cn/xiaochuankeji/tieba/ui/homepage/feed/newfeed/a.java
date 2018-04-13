package cn.xiaochuankeji.tieba.ui.homepage.feed.newfeed;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import java.util.List;
import org.json.JSONObject;

public class a extends Adapter {
    static boolean a = true;
    private List<MemberInfoBean> b;
    private Activity c;
    private d d;

    interface d {
        void a();
    }

    private class a extends ViewHolder {
        final /* synthetic */ a a;

        a(final a aVar, View view) {
            this.a = aVar;
            super(view);
            view.findViewById(R.id.reload).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    if (this.b.a.d != null) {
                        this.b.a.d.a();
                    }
                }
            });
        }

        void a() {
        }
    }

    private class b extends ViewHolder {
        final /* synthetic */ a a;

        b(a aVar, View view) {
            this.a = aVar;
            super(view);
        }

        void a() {
        }
    }

    private class c extends ViewHolder {
        final /* synthetic */ a a;
        private AppCompatTextView b;
        private AppCompatTextView c;
        private WebImageView d;

        c(a aVar, View view) {
            this.a = aVar;
            super(view);
            this.b = (AppCompatTextView) view.findViewById(R.id.follow);
            this.c = (AppCompatTextView) view.findViewById(R.id.nickname);
            this.d = (WebImageView) view.findViewById(R.id.avatar);
        }

        void a(final MemberInfoBean memberInfoBean) {
            this.d.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(memberInfoBean.getId(), memberInfoBean.getAvatarId()));
            this.c.setText(memberInfoBean.getNickName());
            this.d.setOnClickListener(a(memberInfoBean.getId()));
            this.c.setOnClickListener(a(memberInfoBean.getId()));
            boolean z = memberInfoBean.getFollowStatus() > 0;
            this.b.setSelected(z);
            this.b.setText(z ? "已关注" : "关注");
            this.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ c b;

                public void onClick(View view) {
                    if (memberInfoBean.getFollowStatus() > 0) {
                        this.b.c(memberInfoBean);
                    } else {
                        this.b.b(memberInfoBean);
                    }
                }
            });
        }

        private OnClickListener a(final long j) {
            return new OnClickListener(this) {
                final /* synthetic */ c b;

                public void onClick(View view) {
                    MemberDetailActivity.a(this.b.a.c, j);
                }
            };
        }

        private void b(final MemberInfoBean memberInfoBean) {
            new cn.xiaochuankeji.tieba.background.e.b(memberInfoBean.getId(), "follow_suggest_page", null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ c b;

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    if (a.a) {
                        g.a("下拉刷新获取关注内容");
                        a.a = false;
                    }
                    memberInfoBean.setFollowStatus(1);
                    this.b.b.setText("已关注");
                    this.b.b.setSelected(true);
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    g.a(xCError.getMessage());
                }
            }).execute();
        }

        private void c(final MemberInfoBean memberInfoBean) {
            new cn.xiaochuankeji.tieba.background.e.a(memberInfoBean.getId(), null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ c b;

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    memberInfoBean.setFollowStatus(0);
                    this.b.b.setText("关注");
                    this.b.b.setSelected(false);
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    g.a(xCError.getMessage());
                }
            }).execute();
        }
    }

    a(Activity activity) {
        this.c = activity;
    }

    void a(List<MemberInfoBean> list) {
        this.b = list;
    }

    boolean a(int i) {
        return this.b == null || i == this.b.size() + 1;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                return new b(this, LayoutInflater.from(this.c).inflate(R.layout.item_feed_header, viewGroup, false));
            case 2:
                return new a(this, LayoutInflater.from(this.c).inflate(R.layout.item_feed_footer, viewGroup, false));
            default:
                return new c(this, LayoutInflater.from(this.c).inflate(R.layout.item_feed_member, viewGroup, false));
        }
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case 0:
                ((b) viewHolder).a();
                return;
            case 2:
                ((a) viewHolder).a();
                return;
            default:
                ((c) viewHolder).a((MemberInfoBean) this.b.get(i - 1));
                return;
        }
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == this.b.size() + 1) {
            return 2;
        }
        return 1;
    }

    public int getItemCount() {
        return this.b == null ? 0 : this.b.size() + 2;
    }

    void a(d dVar) {
        this.d = dVar;
    }
}
