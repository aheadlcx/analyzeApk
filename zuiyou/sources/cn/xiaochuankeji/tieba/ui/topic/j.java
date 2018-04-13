package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import cn.xiaochuankeji.tieba.json.topic.TopicMemberInfoBean;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import java.util.ArrayList;
import java.util.List;

public class j extends Adapter<c> {
    boolean a = false;
    boolean b = false;
    private final Context c;
    private List<a> d;
    private final LayoutInflater e;
    private long f;
    private String g;
    private long h;
    private String i;
    private List<TopicMemberInfoBean> j;
    private List<TopicMemberInfoBean> k;
    private List<TopicMemberInfoBean> l;
    private List<TopicMemberInfoBean> m;

    private static class a {
        public int a;
        public MemberInfoBean b;

        public a(int i, MemberInfoBean memberInfoBean) {
            this.a = i;
            this.b = memberInfoBean;
        }
    }

    public class c extends ViewHolder {
        TextView i;
        View j;
        TextView k;
        WebImageView l;
        TextView m;
        ImageView n;
        ImageView o;
        int p;
        final /* synthetic */ j q;

        public c(j jVar, View view, int i) {
            this.q = jVar;
            super(view);
            this.p = i;
            if (i == 0) {
                this.l = (WebImageView) view.findViewById(R.id.iv_avatar_member);
                this.m = (TextView) view.findViewById(R.id.tv_memeber_name);
            } else if (i == 4) {
                this.k = (TextView) view.findViewById(R.id.btn_apply_admin);
            } else if (i == 5) {
                this.l = (WebImageView) view.findViewById(R.id.iv_avatar_member);
                this.m = (TextView) view.findViewById(R.id.tv_memeber_name);
                this.n = (ImageView) view.findViewById(R.id.iv_member_icon);
                this.i = (TextView) view.findViewById(R.id.tv_topic_num);
            } else if (i <= 3 || i == 7) {
                this.o = (ImageView) view.findViewById(R.id.tip_link_role);
                this.j = view.findViewById(R.id.tip_link_role_text);
            }
            if (!jVar.a && this.k != null) {
                this.k.setVisibility(8);
            }
        }

        public c(j jVar, View view) {
            this.q = jVar;
            super(view);
        }
    }

    public class b extends c {
        WebImageView a;
        WebImageView b;
        WebImageView c;
        WebImageView d;
        WebImageView e;
        LinearLayout f;
        TextView g;
        final /* synthetic */ j h;

        public b(j jVar, View view) {
            this.h = jVar;
            super(jVar, view);
            this.a = (WebImageView) view.findViewById(R.id.escort_1);
            this.b = (WebImageView) view.findViewById(R.id.escort_2);
            this.c = (WebImageView) view.findViewById(R.id.escort_3);
            this.d = (WebImageView) view.findViewById(R.id.escort_4);
            this.e = (WebImageView) view.findViewById(R.id.escort_5);
            this.g = (TextView) view.findViewById(R.id.escort_size_text);
            this.k = (TextView) view.findViewById(R.id.btn_apply_escort);
            this.f = (LinearLayout) view.findViewById(R.id.escort_list);
            this.f.setVisibility(8);
        }

        public void a(final List<TopicMemberInfoBean> list) {
            if (list != null && list.size() > 0) {
                final MemberInfoBean memberInfoBean;
                this.f.setVisibility(0);
                if (list.size() >= 1) {
                    memberInfoBean = (MemberInfoBean) list.get(0);
                    this.a.setVisibility(0);
                    this.a.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(memberInfoBean.getId(), memberInfoBean.getAvatarId()));
                    this.a.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ b b;

                        public void onClick(View view) {
                            MemberDetailActivity.a(this.b.h.c, memberInfoBean.getId());
                        }
                    });
                }
                if (list.size() >= 2) {
                    memberInfoBean = (MemberInfoBean) list.get(1);
                    this.b.setVisibility(0);
                    this.b.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(memberInfoBean.getId(), memberInfoBean.getAvatarId()));
                    this.b.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ b b;

                        public void onClick(View view) {
                            MemberDetailActivity.a(this.b.h.c, memberInfoBean.getId());
                        }
                    });
                }
                if (list.size() >= 3) {
                    this.c.setVisibility(0);
                    memberInfoBean = (MemberInfoBean) list.get(2);
                    this.c.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(memberInfoBean.getId(), memberInfoBean.getAvatarId()));
                    this.c.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ b b;

                        public void onClick(View view) {
                            MemberDetailActivity.a(this.b.h.c, memberInfoBean.getId());
                        }
                    });
                }
                if (list.size() >= 4) {
                    this.d.setVisibility(0);
                    memberInfoBean = (MemberInfoBean) list.get(3);
                    this.d.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(memberInfoBean.getId(), memberInfoBean.getAvatarId()));
                    this.d.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ b b;

                        public void onClick(View view) {
                            MemberDetailActivity.a(this.b.h.c, memberInfoBean.getId());
                        }
                    });
                }
                if (list.size() >= 5) {
                    this.e.setVisibility(0);
                    memberInfoBean = (MemberInfoBean) list.get(4);
                    this.e.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(memberInfoBean.getId(), memberInfoBean.getAvatarId()));
                    if (list.size() > 5) {
                        this.g.setText(list.size() + "+");
                        this.e.setColorFilter(Color.parseColor("#4c000000"));
                        this.e.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ b b;

                            public void onClick(View view) {
                                TopicEscortListActivity.a(this.b.itemView.getContext(), list);
                            }
                        });
                    } else {
                        this.e.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ b b;

                            public void onClick(View view) {
                                MemberDetailActivity.a(this.b.h.c, memberInfoBean.getId());
                            }
                        });
                    }
                }
                if (!this.h.b && this.k != null) {
                    this.k.setVisibility(8);
                }
            }
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((c) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public j(Context context, long j, String str, long j2) {
        this.c = context;
        this.e = LayoutInflater.from(context);
        this.f = j;
        this.g = str;
        this.h = j2;
    }

    public void a(List<TopicMemberInfoBean> list, List<TopicMemberInfoBean> list2, List<TopicMemberInfoBean> list3) {
        this.j = list;
        this.k = list2;
        this.l = list3;
        a();
        notifyDataSetChanged();
    }

    public void a(List<TopicMemberInfoBean> list) {
        int size = this.d == null ? 0 : this.d.size();
        if (this.m == null) {
            this.m = list;
        } else {
            this.m.addAll(list);
        }
        a();
        notifyItemRangeInserted(size + 1, this.d.size() - size);
    }

    public c a(ViewGroup viewGroup, int i) {
        View inflate;
        String str = "";
        switch (i) {
            case 0:
                inflate = this.e.inflate(R.layout.item_holder, viewGroup, false);
                break;
            case 1:
                inflate = this.e.inflate(R.layout.layout_topic_member_title, viewGroup, false);
                ((TextView) inflate.findViewById(R.id.title_type_text)).setText("话事人");
                break;
            case 2:
                CharSequence charSequence;
                CharSequence charSequence2 = this.i;
                if (TextUtils.isEmpty(charSequence2)) {
                    charSequence = "话题达人";
                } else {
                    charSequence = charSequence2;
                }
                View inflate2 = this.e.inflate(R.layout.layout_topic_member_title, viewGroup, false);
                ((TextView) inflate2.findViewById(R.id.title_type_text)).setText(charSequence);
                inflate = inflate2;
                break;
            case 3:
                inflate = this.e.inflate(R.layout.layout_topic_member_title, viewGroup, false);
                ((TextView) inflate.findViewById(R.id.title_type_text)).setText("成员");
                break;
            case 4:
                inflate = this.e.inflate(R.layout.layout_topic_apply_admin, viewGroup, false);
                break;
            case 5:
                inflate = this.e.inflate(R.layout.layout_topic_member_item, viewGroup, false);
                break;
            case 6:
                return new b(this, this.e.inflate(R.layout.layout_escort_item, viewGroup, false));
            case 7:
                inflate = this.e.inflate(R.layout.layout_topic_member_title, viewGroup, false);
                ((TextView) inflate.findViewById(R.id.title_type_text)).setText("护卫队");
                break;
            default:
                inflate = null;
                break;
        }
        if (inflate != null) {
            return new c(this, inflate, i);
        }
        com.izuiyou.a.a.b.b("viewHolder is null, with type:" + i);
        return null;
    }

    public void a(final c cVar, final int i) {
        if (cVar.k != null) {
            cVar.k.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ j b;

                public void onClick(View view) {
                    if (cn.xiaochuankeji.tieba.ui.auth.a.a((FragmentActivity) this.b.c, "topic_member", 1000)) {
                        String str;
                        String str2;
                        if (cVar instanceof b) {
                            str = "https://$$/help/topic_manage/team_apply/";
                            str2 = "申请护卫队";
                        } else {
                            str = "https://$$/help/topic_manage/manage_apply/";
                            str2 = "申请话事人";
                        }
                        WebActivity.a(this.b.c, cn.xiaochuan.jsbridge.b.a(str2, cn.xiaochuankeji.tieba.background.utils.d.a.d(str) + this.b.f));
                    }
                }
            });
        }
        if (cVar.o != null) {
            cVar.o.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ j b;

                public void onClick(View view) {
                    Object obj = "";
                    String str = "";
                    if (cVar.p == 1) {
                        obj = cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/topic_manage/intro_manage");
                        str = "话事人";
                    } else if (cVar.p == 7) {
                        obj = cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/topic_manage/intro_team");
                        str = "护卫队";
                    }
                    if (!TextUtils.isEmpty(obj)) {
                        WebActivity.a(this.b.c, cn.xiaochuan.jsbridge.b.a(str, obj));
                    }
                }
            });
            cVar.j.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ j a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Object d = cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/kol/describe");
                    if (!TextUtils.isEmpty(d)) {
                        WebActivity.a(this.a.c, cn.xiaochuan.jsbridge.b.a(this.a.i, d));
                    }
                }
            });
            if (cVar.p == 3) {
                cVar.o.setVisibility(8);
                cVar.j.setVisibility(8);
            } else if (cVar.p == 2) {
                cVar.o.setVisibility(8);
                cVar.j.setVisibility(0);
            }
        }
        if (((a) this.d.get(i)).a == 5) {
            MemberInfoBean memberInfoBean = ((a) this.d.get(i)).b;
            cVar.l.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(memberInfoBean.getId(), memberInfoBean.getAvatarId()));
            cVar.m.setText(d.b(memberInfoBean.getNickName()));
            cVar.n.setVisibility(0);
            if (memberInfoBean.getTopicRole() == 4) {
                cVar.n.setImageResource(R.drawable.topic_holder_big_icon);
            } else if (memberInfoBean.getTopicRole() == 2) {
                cVar.n.setImageResource(R.drawable.topic_admin_big_icon);
            } else if (memberInfoBean.getTopicRole() == 1) {
                cVar.n.setImageResource(R.drawable.topic_talent_big_icon);
            } else {
                cVar.n.setVisibility(8);
            }
            cVar.itemView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ j b;

                public void onClick(View view) {
                    MemberInfoBean memberInfoBean = ((a) this.b.d.get(i)).b;
                    if (memberInfoBean != null) {
                        MemberDetailActivity.a(this.b.c, memberInfoBean.getId());
                    }
                }
            });
            if (memberInfoBean.getRecPostCount() > 0) {
                cVar.i.setVisibility(0);
                cVar.i.setText("上周推荐帖" + memberInfoBean.getRecPostCount());
                return;
            }
            cVar.i.setVisibility(8);
        } else if (((a) this.d.get(i)).a == 6 && this.k != null && this.k.size() > 0) {
            ((b) cVar).a(this.k);
        }
    }

    public int getItemCount() {
        return this.d == null ? 0 : this.d.size();
    }

    public int getItemViewType(int i) {
        if (this.d == null || this.d.size() == 0) {
            return 0;
        }
        return ((a) this.d.get(i)).a;
    }

    public void a(String str) {
        this.i = str;
    }

    public void a(int i, int i2) {
        boolean z;
        boolean z2 = true;
        if (i == 1) {
            z = true;
        } else {
            z = false;
        }
        this.a = z;
        if (i2 != 1) {
            z2 = false;
        }
        this.b = z2;
    }

    private void a() {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        this.d.clear();
        this.d.add(new a(1, null));
        if (this.j != null && this.j.size() > 0) {
            for (MemberInfoBean memberInfoBean : this.j) {
                if (memberInfoBean.getTopicRole() == 4) {
                    this.d.add(1, new a(5, memberInfoBean));
                } else {
                    this.d.add(new a(5, memberInfoBean));
                }
            }
        }
        if (this.a) {
            this.d.add(new a(4, null));
        }
        if (this.b || (this.k != null && this.k.size() > 0)) {
            this.d.add(new a(7, null));
            this.d.add(new a(6, null));
        }
        if (this.l != null && this.l.size() > 0) {
            this.d.add(new a(2, null));
            for (MemberInfoBean memberInfoBean2 : this.l) {
                this.d.add(new a(5, memberInfoBean2));
            }
        }
        if (this.m != null && this.m.size() > 0) {
            this.d.add(new a(3, null));
            for (MemberInfoBean memberInfoBean22 : this.m) {
                this.d.add(new a(5, memberInfoBean22));
            }
        }
    }
}
