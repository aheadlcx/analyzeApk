package cn.xiaochuankeji.tieba.ui.videomaker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import cn.xiaochuankeji.tieba.R;

public class VideoRecordOptionPanel extends FrameLayout {
    private View a;
    private View b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private View h;

    public VideoRecordOptionPanel(Context context) {
        super(context);
        a(context);
    }

    public VideoRecordOptionPanel(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public VideoRecordOptionPanel(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.video_record_option_panel, this);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordOptionPanel a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.setVisibility(4);
            }
        });
        findViewById(R.id.option_container).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordOptionPanel a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
            }
        });
        this.a = findViewById(R.id.btn_speed_lowest);
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordOptionPanel a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.f();
                view.setSelected(true);
            }
        });
        this.b = findViewById(R.id.btn_speed_low);
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordOptionPanel a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.f();
                view.setSelected(true);
            }
        });
        this.c = findViewById(R.id.btn_speed_normal);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordOptionPanel a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.f();
                view.setSelected(true);
            }
        });
        this.c.setSelected(true);
        this.d = findViewById(R.id.btn_speed_fast);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordOptionPanel a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.f();
                view.setSelected(true);
            }
        });
        this.e = findViewById(R.id.btn_speed_fastest);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordOptionPanel a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.f();
                view.setSelected(true);
            }
        });
        this.f = findViewById(R.id.btn_countdown_off);
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordOptionPanel a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.g();
                view.setSelected(true);
            }
        });
        this.f.setSelected(true);
        this.g = findViewById(R.id.btn_countdown_three);
        this.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordOptionPanel a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.g();
                view.setSelected(true);
            }
        });
        this.h = findViewById(R.id.btn_countdown_six);
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordOptionPanel a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.g();
                view.setSelected(true);
            }
        });
    }

    public void a() {
        setVisibility(0);
    }

    public boolean isShown() {
        return getVisibility() == 0;
    }

    public void b() {
        setVisibility(4);
    }

    private void f() {
        this.a.setSelected(false);
        this.b.setSelected(false);
        this.c.setSelected(false);
        this.d.setSelected(false);
        this.e.setSelected(false);
    }

    private void g() {
        this.f.setSelected(false);
        this.g.setSelected(false);
        this.h.setSelected(false);
    }

    public int c() {
        if (this.f.isSelected()) {
            return 0;
        }
        if (this.g.isSelected()) {
            return 3;
        }
        if (this.h.isSelected()) {
            return 6;
        }
        return 0;
    }

    public Pair<Integer, Integer> d() {
        if (this.a.isSelected()) {
            return new Pair(Integer.valueOf(1), Integer.valueOf(3));
        }
        if (this.b.isSelected()) {
            return new Pair(Integer.valueOf(1), Integer.valueOf(2));
        }
        if (this.c.isSelected()) {
            return new Pair(Integer.valueOf(1), Integer.valueOf(1));
        }
        if (this.d.isSelected()) {
            return new Pair(Integer.valueOf(2), Integer.valueOf(1));
        }
        if (this.e.isSelected()) {
            return new Pair(Integer.valueOf(3), Integer.valueOf(1));
        }
        return new Pair(Integer.valueOf(1), Integer.valueOf(1));
    }

    public void e() {
        g();
        this.f.setSelected(true);
    }
}
