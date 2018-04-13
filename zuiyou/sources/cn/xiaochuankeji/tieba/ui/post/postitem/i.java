package cn.xiaochuankeji.tieba.ui.post.postitem;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.data.post.Post.PostVote;
import cn.xiaochuankeji.tieba.background.data.post.Post.PostVoteItem;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.post.p;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.utils.d;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.json.JSONException;
import org.json.JSONObject;

public class i implements OnClickListener {
    public int a;
    private Context b;
    private View c;
    private View d;
    private RelativeLayout e;
    private RelativeLayout f;
    private RelativeLayout g;
    private RelativeLayout h;
    private View i;
    private View j;
    private View k;
    private View l;
    private Post m;
    private int n;
    private int o;
    private String p;
    private ArrayList<j> q = new ArrayList();
    private b r;
    private a s;

    public interface b {
        void a(PostVote postVote);
    }

    public interface a {
        void a();
    }

    public i(Context context, View view, Post post, String str) {
        this.p = str;
        this.a = -1;
        this.b = context;
        this.c = view;
        this.d = view;
        this.e = (RelativeLayout) view.findViewById(R.id.vote1);
        this.f = (RelativeLayout) view.findViewById(R.id.vote2);
        this.g = (RelativeLayout) view.findViewById(R.id.vote3);
        this.h = (RelativeLayout) view.findViewById(R.id.vote4);
        this.i = view.findViewById(R.id.viewBg1);
        this.j = view.findViewById(R.id.viewBg2);
        this.k = view.findViewById(R.id.viewBg3);
        this.l = view.findViewById(R.id.viewBg4);
        c();
        this.m = post;
        this.n = this.m.postVote.getVoteItems().size();
        for (int i = this.n - 1; i > 0; i--) {
            if (((PostVoteItem) this.m.postVote.getVoteItems().get(i)).getPollCount() > 0) {
                this.o = i;
                return;
            }
        }
    }

    public void a() {
        d();
        e();
        g();
    }

    public void b() {
        d();
        e();
    }

    private void c() {
        this.c.findViewById(R.id.viewBg1).setOnClickListener(this);
        this.c.findViewById(R.id.viewBg2).setOnClickListener(this);
        this.c.findViewById(R.id.viewBg3).setOnClickListener(this);
        this.c.findViewById(R.id.viewBg4).setOnClickListener(this);
        this.c.findViewById(R.id.tvItemValue1).setOnClickListener(this);
        this.c.findViewById(R.id.tvItemValue2).setOnClickListener(this);
        this.c.findViewById(R.id.tvItemValue3).setOnClickListener(this);
        this.c.findViewById(R.id.tvItemValue4).setOnClickListener(this);
    }

    private void d() {
        for (int i = 0; i < this.n; i++) {
            c(i).setText(((PostVoteItem) this.m.postVote.getVoteItems().get(i)).getName());
            c(i).setTextColor(c.a.d.a.a.a().a((int) R.color.CT_2));
        }
        for (int i2 = 0; i2 < this.n; i2++) {
            d(i2).setVisibility(f() ? 0 : 8);
        }
    }

    private void e() {
        if (f()) {
            h();
            return;
        }
        for (int i = 0; i < this.n; i++) {
            f(i).setBackgroundColor(0);
            TextView c = c(i);
            c.setGravity(17);
            c.setTextColor(c.a.d.a.a.a().a((int) R.color.CT_2));
            c.getPaint().setFakeBoldText(false);
            d(i).getPaint().setFakeBoldText(false);
        }
    }

    private boolean f() {
        return this.m.postVote.getVoteMids().contains(Long.valueOf(cn.xiaochuankeji.tieba.background.a.g().c()));
    }

    private void g() {
        int i;
        if (!f()) {
            for (i = 0; i < this.n; i++) {
                b(i).setVisibility(0);
                if (i == this.n - 1) {
                    e(i).setVisibility(8);
                } else {
                    e(i).setVisibility(0);
                }
            }
        }
        for (i = 0; i < this.n; i++) {
            b(i).setVisibility(0);
        }
        for (i = this.n; i < 4; i++) {
            b(i).setVisibility(8);
        }
    }

    private View b(int i) {
        switch (i) {
            case 0:
                return this.e;
            case 1:
                return this.f;
            case 2:
                return this.g;
            case 3:
                return this.h;
            default:
                return this.e;
        }
    }

    private TextView c(int i) {
        switch (i) {
            case 0:
                return (TextView) this.c.findViewById(R.id.tvItemValue1);
            case 1:
                return (TextView) this.c.findViewById(R.id.tvItemValue2);
            case 2:
                return (TextView) this.c.findViewById(R.id.tvItemValue3);
            case 3:
                return (TextView) this.c.findViewById(R.id.tvItemValue4);
            default:
                return (TextView) this.c.findViewById(R.id.tvItemValue1);
        }
    }

    private TextView d(int i) {
        switch (i) {
            case 0:
                return (TextView) this.c.findViewById(R.id.tvVoteCount1);
            case 1:
                return (TextView) this.c.findViewById(R.id.tvVoteCount2);
            case 2:
                return (TextView) this.c.findViewById(R.id.tvVoteCount3);
            case 3:
                return (TextView) this.c.findViewById(R.id.tvVoteCount4);
            default:
                return (TextView) this.c.findViewById(R.id.tvVoteCount1);
        }
    }

    private View e(int i) {
        switch (i) {
            case 0:
                return this.c.findViewById(R.id.ivSeparator1);
            case 1:
                return this.c.findViewById(R.id.ivSeparator2);
            case 2:
                return this.c.findViewById(R.id.ivSeparator3);
            case 3:
                return this.c.findViewById(R.id.ivSeparator4);
            default:
                return this.c.findViewById(R.id.ivSeparator1);
        }
    }

    private View f(int i) {
        switch (i) {
            case 0:
                return this.i;
            case 1:
                return this.j;
            case 2:
                return this.k;
            case 3:
                return this.l;
            default:
                return this.i;
        }
    }

    public void a(b bVar) {
        this.r = bVar;
    }

    public void a(a aVar) {
        this.s = aVar;
    }

    public void onClick(View view) {
        int i;
        switch (view.getId()) {
            case R.id.viewBg1:
            case R.id.tvItemValue1:
                i = 0;
                break;
            case R.id.viewBg2:
            case R.id.tvItemValue2:
                i = 1;
                break;
            case R.id.viewBg3:
            case R.id.tvItemValue3:
                i = 2;
                break;
            case R.id.viewBg4:
            case R.id.tvItemValue4:
                i = 3;
                break;
            default:
                i = 0;
                break;
        }
        if (!cn.xiaochuankeji.tieba.ui.auth.a.a((AppCompatActivity) this.b, this.b instanceof PostDetailActivity ? "post_detail" : "post_list", f() ? 31 : 5)) {
            return;
        }
        if (f()) {
            com.izuiyou.a.a.b.e("hasVoted open post detail");
            if (this.s != null) {
                this.s.a();
                return;
            }
            return;
        }
        this.a = i;
        a(i);
    }

    public void a(final int i) {
        new p(this.m._ID, (long) this.m.postVote.getId(), ((PostVoteItem) this.m.postVote.getVoteItems().get(i)).getId(), this.p, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ i b;

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                try {
                    PostVote postVote = new PostVote(jSONObject.getJSONObject("vote"));
                    postVote.addVoteMids(this.b.m.postVote.getVoteMids());
                    postVote.setVotedItem(((PostVoteItem) postVote.getVoteItems().get(i)).getId());
                    this.b.m.postVote = postVote;
                    this.b.n = this.b.m.postVote.getVoteItems().size();
                    this.b.m.postVote.addVoteMid(cn.xiaochuankeji.tieba.background.a.g().c());
                    if (this.b.r != null) {
                        this.b.r.a(postVote);
                    }
                    this.b.b();
                } catch (JSONException e) {
                    g.a("投票失败");
                    e.printStackTrace();
                }
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a("投票失败");
            }
        }).execute();
    }

    private void h() {
        String votedItem = this.m.postVote.getVotedItem();
        for (int i = 0; i < this.n; i++) {
            e(i).setVisibility(8);
            PostVoteItem postVoteItem = (PostVoteItem) this.m.postVote.getVoteItems().get(i);
            TextView d = d(i);
            d.setText(d.b(postVoteItem.getPollCount()) + "/票");
            TextView c = c(i);
            c.setGravity(19);
            c.setTextColor(c.a.d.a.a.a().a((int) R.color.CT_2));
            TextPaint paint = d.getPaint();
            TextPaint paint2 = c.getPaint();
            if (postVoteItem.getId().equals(votedItem)) {
                paint.setFakeBoldText(true);
                paint2.setFakeBoldText(true);
            } else {
                paint.setFakeBoldText(false);
                paint2.setFakeBoldText(false);
            }
        }
        i();
    }

    private double g(int i) {
        PostVoteItem postVoteItem = (PostVoteItem) this.m.postVote.getVoteItems().get(i);
        int voteCount = this.m.postVote.getVoteCount();
        if (voteCount > 0) {
            return new BigDecimal((double) (((float) postVoteItem.getPollCount()) / ((float) voteCount))).setScale(2, 4).doubleValue();
        }
        return 0.0d;
    }

    private void i() {
        this.c.post(new Runnable(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.j();
                for (int i = 0; i < this.a.n; i++) {
                    View b = this.a.f(i);
                    LayoutParams layoutParams = (LayoutParams) b.getLayoutParams();
                    layoutParams.width = (int) (this.a.g(i) * ((double) this.a.d.getWidth()));
                    b.setLayoutParams(layoutParams);
                    b.requestLayout();
                    b.setBackgroundResource(this.a.h(i));
                }
            }
        });
    }

    private void j() {
        this.q.clear();
        for (int i = 0; i < this.n; i++) {
            PostVoteItem postVoteItem = (PostVoteItem) this.m.postVote.getVoteItems().get(i);
            j jVar = new j();
            jVar.a = i;
            jVar.b = postVoteItem.getPollCount();
            this.q.add(jVar);
        }
        Collections.sort(this.q, new Comparator<j>(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((j) obj, (j) obj2);
            }

            public int a(j jVar, j jVar2) {
                if (jVar.b < jVar2.b) {
                    return 1;
                }
                if (jVar.b > jVar2.b) {
                    return -1;
                }
                return 0;
            }
        });
    }

    private int h(int i) {
        int i2 = 0;
        while (i2 < this.q.size()) {
            if (((j) this.q.get(i2)).a == i) {
                break;
            }
            i2++;
        }
        i2 = 0;
        switch (i2) {
            case 0:
                return c.a.d.a.a.a().d(R.drawable.bg_vote_item_yellow1);
            case 1:
                return c.a.d.a.a.a().d(R.drawable.bg_vote_item_yellow2);
            case 2:
                return c.a.d.a.a.a().d(R.drawable.bg_vote_item_yellow3);
            case 3:
                return c.a.d.a.a.a().d(R.drawable.bg_vote_item_yellow4);
            default:
                return c.a.d.a.a.a().d(R.drawable.bg_vote_item_yellow1);
        }
    }
}
