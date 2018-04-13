package com.budejie.www.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.DingMeActivity;
import com.budejie.www.bean.Vote;
import com.budejie.www.bean.VoteData;
import com.budejie.www.busevent.VoteEvent;
import com.budejie.www.busevent.VoteEvent.VoteAction;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import com.budejie.www.util.ax;
import de.greenrobot.event.EventBus;
import java.util.Iterator;

public class VoteView extends LinearLayout implements OnClickListener {
    private final String a;
    private String[] b;
    private Context c;
    private LinearLayout d;
    private ImageView e;
    private boolean f;
    private VoteData g;
    private b h;
    private c i;
    private Handler j;

    public interface b {
        void a();
    }

    public interface c {
        void a(VoteData voteData, Vote vote);
    }

    public VoteView(Context context) {
        super(context);
        this.a = "VoteView";
        this.b = new String[]{"A", "B", "C", "D", "E", "F"};
        this.j = new VoteView$2(this);
        a(context);
    }

    public VoteView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = "VoteView";
        this.b = new String[]{"A", "B", "C", "D", "E", "F"};
        this.j = new VoteView$2(this);
        a(context);
    }

    public VoteView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = "VoteView";
        this.b = new String[]{"A", "B", "C", "D", "E", "F"};
        this.j = new VoteView$2(this);
        a(context);
    }

    private void a(Context context) {
        this.c = context;
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.vote_item_container, null);
        this.d = (LinearLayout) inflate.findViewById(R.id.vote_item_container);
        this.e = (ImageView) inflate.findViewById(R.id.vote_container_cancel);
        addView(inflate, new LayoutParams(-1, -2));
    }

    public void setVoteData(VoteData voteData) {
        a(voteData, false);
    }

    public void a(VoteData voteData, boolean z) {
        this.g = voteData;
        if (!(voteData == null || voteData.votes == null)) {
            for (int i = 0; i < voteData.votes.size(); i++) {
                a((Vote) voteData.votes.get(i), i);
            }
        }
        setShowCancelBtn(z);
    }

    public void a() {
        if (this.d != null) {
            this.d.removeAllViews();
        }
        if (this.e != null) {
            this.e.setVisibility(8);
        }
    }

    private void setShowCancelBtn(boolean z) {
        this.f = z;
        if (this.f) {
            ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            int a = an.a(this.c, 15);
            layoutParams.setMargins(a, a, a, a);
            this.d.setLayoutParams(layoutParams);
            this.e.setVisibility(0);
            this.e.setOnClickListener(new VoteView$1(this));
            return;
        }
        this.e.setVisibility(8);
    }

    public void setCancelListener(b bVar) {
        this.h = bVar;
    }

    public void setItemClickListener(c cVar) {
        this.i = cVar;
    }

    private void a(Vote vote, int i) {
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(this.c).inflate(R.layout.item_vote, null);
        if (this.d != null) {
            ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.vote_proportion);
            ImageView imageView2 = (ImageView) relativeLayout.findViewById(R.id.vote_selected);
            ((TextView) relativeLayout.findViewById(R.id.vote_desc)).setText(this.b[i] + " " + vote.name.replace("\n", ""));
            TextView textView = (TextView) relativeLayout.findViewById(R.id.vote_count);
            if (TextUtils.isEmpty(vote.vid)) {
                relativeLayout.setEnabled(false);
            } else {
                relativeLayout.setEnabled(true);
                relativeLayout.setOnClickListener(this);
            }
            textView.setText(vote.getVoteNumStr() + "票");
            textView.setVisibility(0);
            ClipDrawable clipDrawable = (ClipDrawable) imageView.getDrawable();
            if (this.g.getTotalCount() != 0) {
                clipDrawable.setLevel((vote.vote_num * 10000) / this.g.getTotalCount());
            }
            imageView.setVisibility(0);
            if (vote.isVoted) {
                imageView2.setVisibility(0);
            } else {
                imageView2.setVisibility(8);
            }
            relativeLayout.setTag(vote);
            this.d.addView(relativeLayout);
        }
    }

    public void onClick(View view) {
        if (!this.f && !an.a(this.c)) {
            Toast.makeText(this.c, this.c.getString(R.string.nonet), 0).show();
        } else if (this.f) {
            if (this.i != null) {
                this.i.a(null, null);
            }
        } else if (this.g.isVoted()) {
            if (this.g.isVoted()) {
                r0 = (Vote) view.getTag();
                if (this.i != null) {
                    this.i.a(this.g, r0);
                    return;
                }
                Intent intent = new Intent(this.c, DingMeActivity.class);
                intent.putExtra("page_type", 0);
                intent.putExtra("post_id", this.g.pid);
                intent.putExtra("comment_id", this.g.cid);
                intent.putExtra("vote_id", r0.vid);
                this.c.startActivity(intent);
            }
        } else if (an.a(this.c.getSharedPreferences("weiboprefer", 0))) {
            r0 = (Vote) view.getTag();
            Object a = ax.a(this.c, this.g.pid, r0.vid, this.g.cid);
            if (TextUtils.isEmpty(a)) {
                r0.vote_num++;
                r0.isVoted = true;
            } else {
                Iterator it = this.g.votes.iterator();
                while (it.hasNext()) {
                    r0 = (Vote) it.next();
                    if (a.equals(r0.vid)) {
                        r0.isVoted = true;
                    }
                }
            }
            aa.b("VoteView", "EventBus.getDefault().post(VoteAction.VOTE) =" + this.g.pid);
            EventBus.getDefault().post(new VoteEvent(VoteAction.VOTE, this.g));
            b();
        } else {
            an.a((Activity) this.c, 0, null, null, 0);
        }
    }

    public void b() {
        if (this.d != null && this.d.getChildCount() > 0) {
            for (int i = 0; i < this.d.getChildCount(); i++) {
                RelativeLayout relativeLayout = (RelativeLayout) this.d.getChildAt(i);
                Vote vote = (Vote) relativeLayout.getTag();
                ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.vote_selected);
                TextView textView = (TextView) relativeLayout.findViewById(R.id.vote_count);
                ImageView imageView2 = (ImageView) relativeLayout.findViewById(R.id.vote_proportion);
                textView.setText(vote.getVoteNumStr() + "票");
                textView.setVisibility(0);
                ClipDrawable clipDrawable = (ClipDrawable) imageView2.getDrawable();
                imageView2.setVisibility(0);
                if (vote.vote_num > 0) {
                    new VoteView$a(this, "", clipDrawable, (vote.vote_num * 10000) / this.g.getTotalCount()).start();
                }
                if (vote.isVoted) {
                    imageView.setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                }
            }
        }
    }
}
