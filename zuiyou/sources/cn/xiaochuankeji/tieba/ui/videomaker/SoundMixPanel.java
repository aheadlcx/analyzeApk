package cn.xiaochuankeji.tieba.ui.videomaker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import cn.xiaochuankeji.tieba.R;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;

public class SoundMixPanel extends FrameLayout {
    private a a;
    private View b;
    private SeekBar c;
    private View d;
    private SeekBar e;
    private e f;
    private e g;
    private cn.xiaochuankeji.tieba.common.d.a h;
    private cn.xiaochuankeji.tieba.common.d.a i;
    private IMediaPlayer$OnCompletionListener j = new IMediaPlayer$OnCompletionListener(this) {
        final /* synthetic */ SoundMixPanel a;

        {
            this.a = r1;
        }

        public void onCompletion(IMediaPlayer iMediaPlayer) {
            if (this.a.h != null) {
                this.a.h.seekTo(0);
                this.a.h.start();
            }
            if (this.a.i != null) {
                this.a.i.seekTo(0);
                this.a.i.start();
            }
        }
    };

    interface a {
        void a();
    }

    public SoundMixPanel(Context context) {
        super(context);
        a(context);
    }

    public SoundMixPanel(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public SoundMixPanel(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.sound_mix_panel, this);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SoundMixPanel a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.d();
            }
        });
        findViewById(R.id.layout_container).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SoundMixPanel a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
            }
        });
        this.b = findViewById(R.id.label_record_sound);
        this.c = (SeekBar) findViewById(R.id.record_volume_seekbar);
        this.c.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
            final /* synthetic */ SoundMixPanel a;

            {
                this.a = r1;
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z && this.a.h != null) {
                    this.a.h.a(((float) i) / 100.0f);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.d = findViewById(R.id.label_bgm_sound);
        this.e = (SeekBar) findViewById(R.id.bgm_volume_seekbar);
        this.e.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
            final /* synthetic */ SoundMixPanel a;

            {
                this.a = r1;
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z && this.a.i != null) {
                    this.a.i.a(((float) i) / 100.0f);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void a(e eVar, e eVar2) {
        boolean z = true;
        int i = 0;
        boolean z2 = eVar != null;
        this.b.setEnabled(z2);
        this.c.setEnabled(z2);
        this.f = eVar;
        this.c.setProgress(eVar == null ? 0 : eVar.b);
        if (eVar2 == null) {
            z = false;
        }
        this.d.setEnabled(z);
        this.e.setEnabled(z);
        this.g = eVar2;
        SeekBar seekBar = this.e;
        if (eVar2 != null) {
            i = eVar2.b;
        }
        seekBar.setProgress(i);
    }

    public int getRecordVolume() {
        return this.c.getProgress();
    }

    public int getBgmVolume() {
        return this.e.getProgress();
    }

    public void setListener(a aVar) {
        this.a = aVar;
    }

    public void a() {
        setVisibility(0);
        e();
    }

    public boolean isShown() {
        return getVisibility() == 0;
    }

    public void b() {
        f();
    }

    public void c() {
        if (isShown()) {
            e();
        }
    }

    public void d() {
        setVisibility(4);
        f();
        if (this.a != null) {
            this.a.a();
        }
    }

    private void e() {
        f();
        int progress = this.c.getProgress();
        int progress2 = this.e.getProgress();
        if (this.f != null) {
            this.h = new cn.xiaochuankeji.tieba.common.d.a(getContext());
            this.h.a(this.f.a);
            this.h.start();
            this.h.a(((float) progress) / 100.0f);
        }
        if (this.g != null) {
            this.i = new cn.xiaochuankeji.tieba.common.d.a(getContext());
            this.i.a(this.g.a);
            this.i.start();
            this.i.a(((float) progress2) / 100.0f);
        }
        if (this.h != null) {
            this.h.a(this.j);
        } else if (this.i != null) {
            this.i.a(this.j);
        }
    }

    private void f() {
        if (this.h != null) {
            this.h.f();
            this.h.g();
            this.h = null;
        }
        if (this.i != null) {
            this.i.f();
            this.i.g();
            this.i = null;
        }
    }
}
