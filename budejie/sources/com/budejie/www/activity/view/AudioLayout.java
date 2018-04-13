package com.budejie.www.activity.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.f.b;
import com.budejie.www.util.ac;
import com.lt.a;

public class AudioLayout extends RelativeLayout implements b {
    Handler a = new AudioLayout$1(this);
    private Context b;
    private LinearLayout c;
    private TrumpetImageView d;
    private TextView e;
    private ProgressBar f;
    private ImageView g;
    private ac h;
    private String i;
    private boolean j = false;

    public AudioLayout(Context context) {
        super(context);
        this.b = context;
        e();
    }

    public AudioLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        e();
    }

    private void e() {
        this.h = ac.a(this.b);
        addView(LayoutInflater.from(this.b).inflate(R.layout.audio_laba_view, null));
        this.c = (LinearLayout) findViewById(R.id.audioLayout);
        this.d = (TrumpetImageView) findViewById(R.id.trumpetImageView);
        this.e = (TextView) findViewById(R.id.audioTime);
        this.f = (ProgressBar) findViewById(R.id.audioProgressBar);
        this.f.setVisibility(8);
        this.g = (ImageView) findViewById(R.id.audioPlayIv);
        this.c.setOnClickListener(new AudioLayout$2(this));
    }

    public TextView getAudioTime() {
        return this.e;
    }

    public void setAudioTime(String str) {
        this.e.setText(str + "''");
    }

    public void setPlayPath(String str) {
        this.i = str;
    }

    public String getPlayPath() {
        return a.a(this.b).a(this.i);
    }

    public void a() {
        this.a.sendEmptyMessage(1);
    }

    public void a(int i) {
        c();
    }

    public void b() {
        c();
    }

    public void c() {
        this.f.setVisibility(8);
        this.g.setVisibility(0);
        this.d.setVisibility(8);
        this.d.b(true);
    }

    public void d() {
        this.h.c(getPlayPath());
        this.h.a((b) this);
        this.a.sendEmptyMessage(1);
    }

    public void setBg(int i) {
        this.c.setBackgroundResource(i);
    }
}
