package com.budejie.www.activity.video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.budejie.www.R;
import com.budejie.www.activity.HomeGroup;
import com.budejie.www.util.aa;
import com.umeng.analytics.MobclickAgent;

public class AdVideoActivity extends Activity {
    OnClickListener a = new OnClickListener(this) {
        final /* synthetic */ AdVideoActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.b.setBackgroundResource(R.drawable.ad_button_pressed);
            MobclickAgent.onEvent(this.a, "E01-A06", "跳过次数");
            this.a.d();
        }
    };
    private Button b;
    private SurfaceView c;
    private MediaPlayer d;
    private RelativeLayout e;
    private boolean f = false;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_advideo);
        b();
        a();
    }

    private void a() {
        this.b.setOnClickListener(this.a);
        this.e.setOnClickListener(this.a);
        this.c.getHolder().setType(3);
        this.c.getHolder().addCallback(new Callback(this) {
            final /* synthetic */ AdVideoActivity a;

            {
                this.a = r1;
            }

            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            }

            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                try {
                    this.a.c();
                } catch (Exception e) {
                }
            }

            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                System.out.println("");
            }
        });
        this.d.setOnCompletionListener(new OnCompletionListener(this) {
            final /* synthetic */ AdVideoActivity a;

            {
                this.a = r1;
            }

            public void onCompletion(MediaPlayer mediaPlayer) {
                MobclickAgent.onEvent(this.a, "E01-A06", "视频播放完成次数");
                this.a.d();
            }
        });
    }

    private void b() {
        this.b = (Button) findViewById(R.id.JumpButton);
        this.c = (SurfaceView) findViewById(R.id.AdSurfaceView);
        this.e = (RelativeLayout) findViewById(R.id.advideo_layout);
        this.d = new MediaPlayer();
    }

    private void c() {
        try {
            AssetFileDescriptor openFd = getAssets().openFd("advideo.mp4");
            this.d.reset();
            this.d.setAudioStreamType(3);
            this.d.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
            this.d.setDisplay(this.c.getHolder());
            this.d.prepare();
            this.d.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void d() {
        if (this.d.isPlaying()) {
            this.d.stop();
        }
        Intent intent = new Intent(this, HomeGroup.class);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String string = extras.getString("jumpUrl");
            aa.a("jump", "AdVideoActivity  HomeGroup:" + string);
            extras.putString("jumpUrl", string);
            intent.putExtras(extras);
        }
        startActivity(intent);
        try {
            finish();
        } catch (Exception e) {
        }
    }

    protected void onResume() {
        try {
            if (!this.f) {
                c();
            }
        } catch (Exception e) {
        }
        this.f = false;
        super.onResume();
    }

    protected void onPause() {
        if (this.d.isPlaying()) {
            this.d.stop();
        }
        super.onPause();
    }

    protected void onStop() {
        this.f = true;
        super.onStop();
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 4) {
            return false;
        }
        MobclickAgent.onEvent((Context) this, "E01-A06", "返回键跳过次数");
        d();
        finish();
        return true;
    }

    protected void onDestroy() {
        this.d.release();
        this.d = null;
        super.onDestroy();
    }
}
