package com.budejie.www.adapter.g.b;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.BudejieApplication.Status;
import com.budejie.www.activity.view.MediaPlayView;
import com.budejie.www.activity.view.MediaPlayView$a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.g.c;
import com.budejie.www.http.i;
import com.budejie.www.http.j;
import com.budejie.www.util.ac;

public class u extends j implements MediaPlayView$a {
    private TextView h;
    private TextView i;
    private MediaPlayView j;
    private int k;

    public u(Context context, b<ListItemObject> bVar) {
        super(context, bVar);
    }

    public View a(ViewGroup viewGroup) {
        View a = super.a(viewGroup);
        this.h = (TextView) a.findViewById(R.id.itemPlayCount);
        this.i = (TextView) a.findViewById(R.id.playTimeLength);
        this.j = (MediaPlayView) a.findViewById(R.id.mMPview);
        return a;
    }

    protected int e() {
        return R.layout.post_voice;
    }

    public void c() {
        super.c();
        this.j.setOnMediaPlayerStateListener(new c(this.j));
        this.j.setPlayPath(((ListItemObject) this.c).getVoiceUri());
        this.j.setServerTime(Integer.parseInt(((ListItemObject) this.c).getVoicetime()) * 1000);
        this.j.setDataId(((ListItemObject) this.c).getWid());
        Object playcount = ((ListItemObject) this.c).getPlaycount();
        if (TextUtils.isEmpty(playcount)) {
            this.k = 0;
        } else {
            this.k = Integer.parseInt(playcount);
        }
        this.j.setPlayingListener(this);
        ac a = ac.a(this.a);
        Object m = a.m();
        if (a.c() || a.a()) {
            if (TextUtils.isEmpty(m) || !m.equals(((ListItemObject) this.c).getVoiceUri())) {
                this.j.e();
            } else {
                this.j.a();
            }
        } else if (TextUtils.isEmpty(m) || !m.equals(((ListItemObject) this.c).getVoiceUri()) || a.b()) {
            this.j.e();
        } else {
            this.j.b();
        }
        this.i.setVisibility(0);
        this.i.setText(ac.a((long) (Integer.parseInt(((ListItemObject) this.c).getVoicetime()) * 1000)));
        if (this.k != 0) {
            this.h.setText(this.k + "次播放");
        } else {
            this.h.setText("");
        }
    }

    public void a() {
        this.i.setVisibility(8);
        this.k++;
        this.h.setText(this.k + "次播放");
        ((ListItemObject) this.c).setPlaycount(String.valueOf(this.k));
        ((BudejieApplication) this.a.getApplicationContext()).a((ListItemObject) this.c);
        i.a(this.a.getString(R.string.track_event_play_voice), j.a((ListItemObject) this.c), j.b(this.a, (ListItemObject) this.c));
    }

    public void b() {
        this.i.setVisibility(0);
        ((BudejieApplication) this.a.getApplicationContext()).a(Status.end);
    }
}
