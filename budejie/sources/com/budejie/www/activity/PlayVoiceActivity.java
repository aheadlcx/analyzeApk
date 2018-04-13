package com.budejie.www.activity;

import android.content.Context;
import android.os.Bundle;
import com.budejie.www.R;
import com.budejie.www.activity.view.AudioLayout;
import com.budejie.www.bean.CommentItemForNews;
import com.budejie.www.util.ac;

public class PlayVoiceActivity extends OauthWeiboBaseAct {
    private CommentItemForNews a;
    private AudioLayout b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_play_voice);
        this.a = (CommentItemForNews) getIntent().getSerializableExtra("voice_comment");
        this.b = (AudioLayout) findViewById(R.id.audio_layout);
        this.b.setVisibility(0);
        this.b.c();
        this.b.setPlayPath((String) this.a.audio.audio.get(0));
        this.b.setAudioTime("" + this.a.audio.duration);
        if (!ac.a((Context) this).c()) {
            this.b.c();
        } else if (ac.a((Context) this).m().equals(this.a.audio.audio.get(0))) {
            this.b.d();
        } else {
            this.b.c();
        }
    }

    protected void onPause() {
        super.onPause();
        try {
            ac.a((Context) this).g();
            ac.a((Context) this).h();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
