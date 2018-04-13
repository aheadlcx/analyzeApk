package cn.xiaochuankeji.tieba.ui.topic.weight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import c.a.d.a.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.net.a.b;
import cn.xiaochuankeji.tieba.background.post.p;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.recommend.VoteInfoBean;
import cn.xiaochuankeji.tieba.json.recommend.VoteInfoBean.VoteItem;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.utils.e;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class PostVoteView extends LinearLayout {
    private static final int[] a = new int[]{a.a().d(R.drawable.bg_vote_item_yellow1), a.a().d(R.drawable.bg_vote_item_yellow2), a.a().d(R.drawable.bg_vote_item_yellow3), a.a().d(R.drawable.bg_vote_item_yellow4)};
    private static float k;
    private OnLongClickListener b;
    private OnClickListener c;
    private List<TextView> d;
    private List<TextView> e;
    private List<View> f;
    private List<View> g;
    private VoteInfoBean h;
    private String i;
    private long j;

    public PostVoteView(Context context) {
        super(context);
        a();
    }

    public PostVoteView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public PostVoteView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_post_vote, this);
        this.d = new LinkedList();
        this.e = new LinkedList();
        this.f = new LinkedList();
        this.g = new LinkedList();
        post(new Runnable(this) {
            final /* synthetic */ PostVoteView a;

            {
                this.a = r1;
            }

            public void run() {
                PostVoteView.k = (float) this.a.getWidth();
            }
        });
    }

    public void a(VoteInfoBean voteInfoBean, long j, String str, int i) {
        if (i > 0) {
            k = (float) i;
        }
        if (voteInfoBean != null && voteInfoBean.voteItems != null && !voteInfoBean.voteItems.isEmpty()) {
            this.h = voteInfoBean;
            this.j = j;
            this.i = str;
            b();
            c();
            setOnLongClickListener(this.b);
            setOnClickListener(this.c);
            if (k <= 0.0f) {
                k = (float) (e.b() - e.a(14.0f));
            }
            d();
        }
    }

    private void b() {
        this.d.clear();
        this.e.clear();
        this.d.add((TextView) findViewById(R.id.vote_percent_1));
        this.d.add((TextView) findViewById(R.id.vote_percent_2));
        this.d.add((TextView) findViewById(R.id.vote_percent_3));
        this.d.add((TextView) findViewById(R.id.vote_percent_4));
        this.e.add((TextView) findViewById(R.id.vote_result_1));
        this.e.add((TextView) findViewById(R.id.vote_result_2));
        this.e.add((TextView) findViewById(R.id.vote_result_3));
        this.e.add((TextView) findViewById(R.id.vote_result_4));
    }

    private void c() {
        this.f.clear();
        this.g.clear();
        this.f.add(findViewById(R.id.vote_item_1));
        this.f.add(findViewById(R.id.vote_item_2));
        this.f.add(findViewById(R.id.vote_item_3));
        this.f.add(findViewById(R.id.vote_item_4));
        this.g.add(findViewById(R.id.vote_bg_1));
        this.g.add(findViewById(R.id.vote_bg_2));
        this.g.add(findViewById(R.id.vote_bg_3));
        this.g.add(findViewById(R.id.vote_bg_4));
    }

    private void d() {
        int i = 4;
        for (int i2 = 0; i2 < 4; i2++) {
            ((View) this.f.get(i2)).setVisibility(0);
        }
        while (i > this.h.voteItems.size()) {
            ((View) this.f.get(i - 1)).setVisibility(8);
            this.d.remove(i - 1);
            this.e.remove(i - 1);
            this.f.remove(i - 1);
            this.g.remove(i - 1);
            i--;
        }
        if (this.h.voteItem == null) {
            e();
        } else {
            f();
        }
    }

    private void e() {
        for (int i = 0; i < this.h.voteItems.size(); i++) {
            ((TextView) this.e.get(i)).setTypeface(Typeface.defaultFromStyle(0));
            ((TextView) this.e.get(i)).setText(((VoteItem) this.h.voteItems.get(i)).voteName);
            ((TextView) this.e.get(i)).setGravity(17);
            ((TextView) this.d.get(i)).setVisibility(8);
            ((View) this.g.get(i)).setVisibility(8);
            final String str = ((VoteItem) this.h.voteItems.get(i)).voteId;
            ((View) this.f.get(i)).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ PostVoteView b;

                public void onClick(View view) {
                    this.b.a(str);
                }
            });
            ((View) this.f.get(i)).setOnLongClickListener(this.b);
        }
    }

    private void a(String str) {
        new p(this.j, this.h.id, str, this.i, null, new b<JSONObject>(this) {
            final /* synthetic */ PostVoteView a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                try {
                    VoteInfoBean parse = VoteInfoBean.parse(jSONObject.getJSONObject("vote"));
                    if (parse == null || parse.voteItem == null) {
                        g.a("投票失败");
                        return;
                    }
                    this.a.h.voteItems = parse.voteItems;
                    this.a.h.voteItem = parse.voteItem;
                    this.a.f();
                } catch (JSONException e) {
                    g.a("投票失败");
                    e.printStackTrace();
                }
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ PostVoteView a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a("投票失败");
            }
        }).execute();
    }

    @SuppressLint({"SetTextI18n"})
    private void f() {
        int i = 0;
        int[] iArr = new int[this.h.voteItems.size()];
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.h.voteItems.size()) {
            ((TextView) this.e.get(i2)).setText(((VoteItem) this.h.voteItems.get(i2)).voteName);
            ((TextView) this.e.get(i2)).setGravity(GravityCompat.START);
            ((TextView) this.d.get(i2)).setText(d.b(((VoteItem) this.h.voteItems.get(i2)).voteCount) + "／票");
            ((TextView) this.d.get(i2)).setVisibility(0);
            if (((VoteItem) this.h.voteItems.get(i2)).voteId.equals(this.h.voteItem)) {
                ((TextView) this.e.get(i2)).setTypeface(Typeface.defaultFromStyle(1));
                ((TextView) this.d.get(i2)).setTypeface(Typeface.defaultFromStyle(1));
            } else {
                ((TextView) this.e.get(i2)).setTypeface(Typeface.defaultFromStyle(0));
                ((TextView) this.d.get(i2)).setTypeface(Typeface.defaultFromStyle(0));
            }
            ((View) this.f.get(i2)).setOnLongClickListener(this.b);
            ((View) this.f.get(i2)).setOnClickListener(this.c);
            int i4 = i3 + ((VoteItem) this.h.voteItems.get(i2)).voteCount;
            iArr[i2] = i2;
            i2++;
            i3 = i4;
        }
        while (i < this.h.voteItems.size()) {
            i4 = i;
            while (i4 > 0 && ((VoteItem) this.h.voteItems.get(iArr[i4])).voteCount > ((VoteItem) this.h.voteItems.get(iArr[i4 - 1])).voteCount) {
                iArr[i4] = iArr[i4] + iArr[i4 - 1];
                iArr[i4 - 1] = iArr[i4] - iArr[i4 - 1];
                iArr[i4] = iArr[i4] - iArr[i4 - 1];
                i4--;
            }
            i++;
        }
        a(iArr, i3);
    }

    private void a(int[] iArr, int i) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f = ((float) ((VoteItem) this.h.voteItems.get(iArr[i2])).voteCount) / ((float) i);
            View view = (View) this.g.get(iArr[i2]);
            if (((double) f) > 0.0d) {
                LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.width = (int) (f * k);
                view.setLayoutParams(layoutParams);
                view.setBackgroundResource(a[i2]);
                view.setVisibility(0);
            } else {
                view.setVisibility(8);
            }
        }
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.b = onLongClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.c = onClickListener;
    }
}
