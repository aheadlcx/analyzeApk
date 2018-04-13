package cn.xiaochuankeji.tieba.ui.discovery.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import cn.htjyb.b.a.d;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView.EmptyPaddingStyle;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.c.a;
import cn.xiaochuankeji.tieba.background.c.c;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.topic.TopicHistoryRecordManager;
import cn.xiaochuankeji.tieba.background.topic.TopicHistoryRecordManager.Type;
import cn.xiaochuankeji.tieba.background.topic.TopicSearcher;
import cn.xiaochuankeji.tieba.background.utils.a.h;
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.PostQueryListView;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.o;
import cn.xiaochuankeji.tieba.ui.topic.p;

public class b extends f implements OnItemClickListener {
    public static int a = 0;
    public static int b = 1;
    public static int c = 2;
    private String d;
    private int e = a;
    private TopicSearcher f;
    private h g;
    private c h;
    private cn.xiaochuankeji.tieba.background.c.b i;
    private TopicHistoryRecordManager j;
    private a k;
    private QueryListView l;
    private PostQueryListView m;
    private QueryListView n;
    private ListView o;
    private ListView p;
    private View q;
    private View r;
    private FrameLayout s;

    public static b b() {
        return new b();
    }

    public void a(int i) {
        i().clear();
        this.e = i;
        if (this.d != null) {
            f();
            e();
            h();
            return;
        }
        d();
        g();
    }

    private void d() {
        this.l.setVisibility(8);
        this.m.setVisibility(8);
        this.n.setVisibility(8);
    }

    public void a(String str) {
        this.d = str;
        f();
        e();
        h();
    }

    public void c() {
        this.d = null;
        i().clear();
        d();
        g();
    }

    private void e() {
        if (a == this.e) {
            this.l.setVisibility(0);
            this.m.setVisibility(8);
            this.n.setVisibility(8);
        } else if (b == this.e) {
            this.l.setVisibility(8);
            this.m.setVisibility(0);
            this.n.setVisibility(8);
        } else if (c == this.e) {
            this.l.setVisibility(8);
            this.m.setVisibility(8);
            this.n.setVisibility(0);
        }
    }

    private void f() {
        if (a == this.e) {
            this.f.clear();
            this.f.setSearchKey(this.d);
            this.g.a(this.d);
            this.f.refresh();
        } else if (b == this.e) {
            this.h.clear();
            this.h.a(this.d);
            this.h.refresh();
        } else if (c == this.e) {
            this.i.clear();
            this.i.a(this.d);
            this.i.refresh();
        }
    }

    private void g() {
        if (this.e == a) {
            this.o.setVisibility(0);
            this.p.setVisibility(8);
        } else if (this.e == c) {
            this.p.setVisibility(0);
            this.o.setVisibility(8);
        } else {
            this.o.setVisibility(8);
            this.p.setVisibility(8);
        }
    }

    private void h() {
        this.o.setVisibility(8);
        this.p.setVisibility(8);
    }

    private d i() {
        if (a == this.e) {
            return this.f;
        }
        if (b == this.e) {
            return this.h;
        }
        if (c == this.e) {
            return this.i;
        }
        return null;
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_ugcvideo_recommend, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.l = new QueryListView(getActivity());
        this.m = new PostQueryListView(getActivity());
        this.n = new QueryListView(getActivity());
        this.o = new ListView(getActivity());
        this.p = new ListView(getActivity());
        this.s = (FrameLayout) view.findViewById(R.id.rootView);
        this.s.addView(this.l);
        this.s.addView(this.m);
        this.s.addView(this.n);
        this.s.addView(this.o);
        this.s.addView(this.p);
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        this.l.setVisibility(8);
        this.o.setVisibility(0);
        this.p.setVisibility(8);
        a(this.n.m());
        a(this.l.m());
        a(this.o);
        a(this.p);
    }

    private void a(ListView listView) {
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f = new TopicSearcher();
        this.g = new h();
        this.l.a(this.f, new p(getActivity(), this.f));
        this.l.m().setOnItemClickListener(this);
        this.h = new c();
        this.m.f();
        this.m.a(this.h);
        this.i = new cn.xiaochuankeji.tieba.background.c.b();
        this.n.a(this.i, new c(getActivity(), this.i));
        this.n.f();
        this.n.m().setOnItemClickListener(this);
        j();
        l();
        this.l.a("哎呦，什么都没有找到", (int) R.drawable.ic_post_empty, EmptyPaddingStyle.GoldenSection);
        this.m.a("哎呦，什么都没有找到", (int) R.drawable.ic_post_empty, EmptyPaddingStyle.GoldenSection);
        this.n.a("哎呦，什么都没有找到", (int) R.drawable.ic_post_empty, EmptyPaddingStyle.GoldenSection);
    }

    private void j() {
        this.j = TopicHistoryRecordManager.getInstance(Type.kSearch);
        final Object dVar = new cn.xiaochuankeji.tieba.ui.topic.d(this.j, getActivity());
        this.q = LayoutInflater.from(getActivity()).inflate(R.layout.view_footer_lv_history_record, null);
        this.o.setDividerHeight(0);
        this.o.setCacheColorHint(Color.parseColor("#00000000"));
        this.o.addFooterView(this.q);
        this.o.setAdapter(dVar);
        this.q.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
            }
        });
        this.q.findViewById(R.id.bnClearHistory).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                cn.xiaochuankeji.tieba.ui.widget.f.a("提醒", "确认清空历史记录？", this.a.getActivity(), new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z) {
                        if (z) {
                            this.a.a.j.clear();
                        }
                    }
                });
            }
        });
        this.j.registerOnListUpdateListener(new cn.htjyb.b.a.a.a(this) {
            final /* synthetic */ b b;

            public void a() {
                dVar.notifyDataSetChanged();
                this.b.k();
            }
        });
        this.o.setOnItemClickListener(this);
        k();
    }

    private void k() {
        if (this.j.itemCount() > 0) {
            this.q.setVisibility(0);
        } else {
            this.q.setVisibility(8);
        }
    }

    private void l() {
        this.k = new a();
        final Object cVar = new c(getActivity(), this.k);
        this.r = LayoutInflater.from(getActivity()).inflate(R.layout.view_footer_lv_history_record, null);
        this.p.setDividerHeight(0);
        this.p.setCacheColorHint(Color.parseColor("#00000000"));
        this.p.addFooterView(this.r);
        this.p.setAdapter(cVar);
        this.r.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
            }
        });
        this.r.findViewById(R.id.bnClearHistory).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                cn.xiaochuankeji.tieba.ui.widget.f.a("提醒", "确认清空历史记录？", this.a.getActivity(), new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z) {
                        if (z) {
                            this.a.a.k.b();
                        }
                    }
                });
            }
        });
        this.k.registerOnListUpdateListener(new cn.htjyb.b.a.a.a(this) {
            final /* synthetic */ b b;

            public void a() {
                cVar.notifyDataSetChanged();
                this.b.m();
            }
        });
        this.p.setOnItemClickListener(this);
        m();
    }

    private void m() {
        if (this.k.itemCount() > 0) {
            this.r.setVisibility(0);
        } else {
            this.r.setVisibility(8);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (adapterView == this.o) {
            Topic itemAt = this.j.itemAt(i);
            if (itemAt != null) {
                TopicDetailActivity.a(getActivity(), itemAt, "search_history");
            }
        } else if (adapterView == this.l.m()) {
            if (view.getTag() != null && (view.getTag() instanceof o)) {
                int headerViewsCount = i - this.l.m().getHeaderViewsCount();
                if (headerViewsCount >= 0 && headerViewsCount < this.f.itemCount()) {
                    Topic topic = (Topic) this.f.itemAt(headerViewsCount);
                    if (topic != null) {
                        this.g.a("topicsug", topic._topicID, "search", headerViewsCount);
                        TopicDetailActivity.a(getActivity(), topic, "search");
                        this.j.insert(topic);
                    }
                }
            }
        } else if (adapterView == this.p) {
            MemberDetailActivity.a(getActivity(), this.k.a(i).getId());
        } else if (adapterView == this.n.m()) {
            Member member = (Member) this.i.itemAt(i - 1);
            MemberDetailActivity.a(getActivity(), member.getId());
            this.k.a(member);
        }
    }
}
