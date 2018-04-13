package com.budejie.www.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.VideoView;
import com.budejie.www.R;
import com.budejie.www.goddubbing.c.d;
import com.budejie.www.goddubbing.c.g;
import com.budejie.www.goddubbing.c.g.a;
import com.budejie.www.goddubbing.wediget.SeekBar;
import com.budejie.www.goddubbing.wediget.VideoLayout;
import java.util.List;

public class DubbingPreviewActivity extends Activity implements a, SeekBar.a {
    private VideoView a;
    private ImageView b;
    private String c;
    private List<String> d;
    private int e;
    private int f;
    private int g = 0;
    private SeekBar h;
    private String i;

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_dubbing_preview);
        this.h = (SeekBar) findViewById(R.id.seek_bar);
        VideoLayout videoLayout = (VideoLayout) findViewById(R.id.video_layout);
        videoLayout.getPreviewImageView().setVisibility(8);
        this.a = videoLayout.getVideoView();
        this.b = (ImageView) findViewById(R.id.back_image_view);
        c();
        Intent intent = getIntent();
        if (intent != null) {
            this.c = intent.getStringExtra("VideoPathTag");
            this.d = (List) intent.getSerializableExtra("RecorderPathTag");
            this.e = intent.getIntExtra("VideoCutStartTime", 0);
            this.f = intent.getIntExtra("VideoCutEndTime", 0);
            this.i = intent.getStringExtra("ImagePathTag");
        }
        if (d.d(this.i)) {
            videoLayout.getDefaultImageView().setVisibility(0);
            videoLayout.getDefaultImageView().setImageURI(Uri.parse(this.i));
            if (!com.budejie.www.goddubbing.c.a.a(this.d)) {
                e();
            } else {
                return;
            }
        }
        videoLayout.getDefaultImageView().setVisibility(8);
        d();
        this.h.a(this.f - this.e);
        this.h.setPlayCallBack(this);
        g.a().a((a) this);
    }

    private void c() {
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DubbingPreviewActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.a != null) {
                    this.a.a.stopPlayback();
                }
                g.a().b();
                this.a.finish();
            }
        });
    }

    private void d() {
        if (d.d(this.c)) {
            this.a.setVideoURI(Uri.parse(this.c));
            this.a.setOnPreparedListener(new OnPreparedListener(this) {
                final /* synthetic */ DubbingPreviewActivity a;

                {
                    this.a = r1;
                }

                public void onPrepared(MediaPlayer mediaPlayer) {
                    this.a.a.seekTo(this.a.e);
                    mediaPlayer.setOnSeekCompleteListener(new DubbingPreviewActivity$2$1(this));
                    if (!com.budejie.www.goddubbing.c.a.a(this.a.d)) {
                        mediaPlayer.setVolume(0.0f, 0.0f);
                        this.a.e();
                    }
                }
            });
        }
    }

    private void e() {
        Object obj = (com.budejie.www.goddubbing.c.a.a(this.d) || this.g >= this.d.size()) ? null : 1;
        if (obj != null) {
            String str = (String) this.d.get(this.g);
            if (d.d(str)) {
                g.a().b(str);
                this.g++;
            }
        }
    }

    protected void onPause() {
        super.onPause();
        finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.a != null) {
            this.a.stopPlayback();
            this.a = null;
        }
        if (this.h != null) {
            this.h.e();
            this.h.f();
        }
        g.a().c();
    }

    public void a() {
        e();
    }

    public void b() {
        if (this.a != null) {
            this.a.stopPlayback();
            this.a = null;
        }
        finish();
    }
}
