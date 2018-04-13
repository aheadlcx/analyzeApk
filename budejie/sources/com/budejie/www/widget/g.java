package com.budejie.www.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.budejie.www.R;
import com.budejie.www.bean.VoteData;
import com.budejie.www.util.ax;

public class g extends Dialog {
    private VoteData a;
    private Context b;
    private ViewGroup c;
    private VoteView d;

    public g(Context context, int i, VoteData voteData) {
        super(context, i);
        this.b = context;
        this.a = voteData;
        ax.a(this.a, this.b);
        a();
    }

    private void a() {
        this.c = (ViewGroup) LayoutInflater.from(this.b).inflate(R.layout.vote_dialog_layout, null);
        this.d = (VoteView) this.c.findViewById(R.id.vote_view);
        this.d.setVoteData(this.a);
        setContentView(this.c);
    }
}
