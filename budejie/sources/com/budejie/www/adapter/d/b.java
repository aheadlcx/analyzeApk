package com.budejie.www.adapter.d;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bdj.picture.edit.util.c;
import com.budejie.www.R;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.a;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.VoteData;
import com.budejie.www.util.aa;
import com.budejie.www.util.ax;
import com.budejie.www.widget.VoteView;
import com.budejie.www.widget.parsetagview.NewParseTagEditText;

public class b extends a implements OnClickListener, com.budejie.www.activity.video.f.b {
    protected final ListItemObject a;
    protected final Activity b;
    protected final LayoutInflater c;
    protected final com.budejie.www.adapter.e.a d;
    protected final int e;
    private final int f = 7;

    public /* synthetic */ Object d() {
        return a();
    }

    public b(Activity activity, com.budejie.www.adapter.e.a aVar, ListItemObject listItemObject, int i) {
        this.a = listItemObject;
        this.b = activity;
        this.d = aVar;
        this.c = LayoutInflater.from(activity);
        this.e = i;
    }

    public View b() {
        b$a b_a = new b$a(this);
        ViewGroup viewGroup = (ViewGroup) this.c.inflate(R.layout.post_detail_head_content_layout, null);
        this.a.mcollapsibleState = 0;
        b$a.a(b_a, (NewParseTagEditText) viewGroup.findViewById(R.id.post_detail_head_content));
        b$a.a(b_a, (TextView) viewGroup.findViewById(R.id.post_detail_head_content_open_or_close));
        b$a.b(b_a, (TextView) viewGroup.findViewById(R.id.post_detail_head_content_play_times));
        b$a.a(b_a, (VoteView) viewGroup.findViewById(R.id.vote_view));
        viewGroup.setTag(b_a);
        return viewGroup;
    }

    public int c() {
        return RowType.POST_DETAIL_HEAD_ROW.ordinal();
    }

    public void a(com.budejie.www.adapter.b bVar) {
        b$a b_a = (b$a) bVar;
        CharSequence charSequence = "";
        if (!TextUtils.isEmpty(this.a.getPlaycount())) {
            charSequence = this.a.getPlaycount() + "次播放·";
        }
        if (this.a.getLove() != 0) {
            charSequence = charSequence + Integer.toString(this.a.getLove()) + "赞·";
        }
        String addtime = this.a.getAddtime();
        if (!TextUtils.isEmpty(addtime)) {
            try {
                if (addtime.length() > 19) {
                    addtime = addtime.substring(addtime.length() - 19);
                }
                charSequence = charSequence + c.b(c.a(addtime));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!TextUtils.isEmpty(charSequence)) {
            b$a.a(b_a).setText(charSequence);
            b$a.a(b_a).setVisibility(0);
        }
        b$a.b(b_a).setText(this.a.getContent());
        if (this.a.mcollapsibleState == 0) {
            b$a.b(b_a).post(new b$1(this, b_a));
        } else {
            a(b_a);
        }
        b$a.c(b_a).setTag(b_a);
        b$a.c(b_a).setOnClickListener(this);
        try {
            VoteData voteData = this.a.getVoteData();
            ax.a(voteData, this.b);
            if (voteData == null || voteData.votes == null || voteData.votes.size() <= 0) {
                b$a.d(b_a).setVisibility(8);
                return;
            }
            b$a.d(b_a).a();
            b$a.d(b_a).setVisibility(0);
            b$a.d(b_a).setVoteData(voteData);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a(b$a b_a) {
        if (b_a != null) {
            aa.b("postcontentView", "setTextViewCollapsible()");
            if (2 == this.a.mcollapsibleState) {
                aa.b("postcontentView", "setTextViewCollapsible() COLLAPSIBLE_STATE_SHRINKUP");
                b$a.c(b_a).setVisibility(0);
                b$a.c(b_a).setText("全文");
                b$a.c(b_a).setPadding(0, 0, 0, 0);
                b$a.b(b_a).setMaxLines(7);
            } else if (3 == this.a.mcollapsibleState) {
                aa.b("postcontentView", "setTextViewCollapsible() COLLAPSIBLE_STATE_SPREAD");
                b$a.c(b_a).setVisibility(0);
                b$a.c(b_a).setText("收起");
                b$a.c(b_a).setPadding(0, 12, 0, 0);
                b$a.b(b_a).setMaxLines(Integer.MAX_VALUE);
            } else if (1 == this.a.mcollapsibleState) {
                aa.b("postcontentView", "setTextViewCollapsible() COLLAPSIBLE_STATE_NONE");
                b$a.c(b_a).setVisibility(8);
                b$a.b(b_a).setMaxLines(Integer.MAX_VALUE);
            } else {
                aa.b("postcontentView", "setTextViewCollapsible() no");
            }
        }
    }

    public ListItemObject a() {
        return this.a;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.post_detail_head_content_open_or_close:
                if (3 == this.a.mcollapsibleState) {
                    this.a.mcollapsibleState = 2;
                } else if (2 == this.a.mcollapsibleState) {
                    this.a.mcollapsibleState = 3;
                }
                a((b$a) view.getTag());
                return;
            default:
                return;
        }
    }

    public void b_() {
    }
}
