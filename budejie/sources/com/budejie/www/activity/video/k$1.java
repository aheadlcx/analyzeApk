package com.budejie.www.activity.video;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.text.TextUtils;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.video.f.b;
import com.budejie.www.activity.video.k.a;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.i;
import com.budejie.www.http.j;

class k$1 implements OnErrorListener {
    final /* synthetic */ ListItemObject a;
    final /* synthetic */ int b;
    final /* synthetic */ a c;
    final /* synthetic */ b d;
    final /* synthetic */ f$e e;
    final /* synthetic */ int f;
    final /* synthetic */ k g;

    k$1(k kVar, ListItemObject listItemObject, int i, a aVar, b bVar, f$e f_e, int i2) {
        this.g = kVar;
        this.a = listItemObject;
        this.b = i;
        this.c = aVar;
        this.d = bVar;
        this.e = f_e;
        this.f = i2;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        try {
            i.a(k.a(this.g, 2, this.a.getVideouri()), j.a(this.a), j.b(k.a(this.g), this.a));
            if (TextUtils.isEmpty(this.a.getVideouriBackup()) || this.a.getVideouri().equals(this.a.getVideouriBackup())) {
                this.g.h();
                Toast.makeText(k.a(this.g), k.a(this.g).getString(R.string.loading_video_error_tip) + "(" + i + "," + i2 + ")", 0).show();
                return true;
            }
            this.a.setVideouri(this.a.getVideouriBackup());
            if (this.g.f.m) {
                k.b(this.g).setVideoPath(this.a.getVideouriBackup());
            } else {
                if (this.g.f == null) {
                    k.c(this.g).c.removeAllViews();
                } else {
                    this.g.f.b.removeAllViews();
                }
                this.g.a(this.b, this.a, (a) this.c.clone(), this.d, this.e, k.d(this.g), this.f);
            }
            return true;
        } catch (Exception e) {
            this.g.h();
            Toast.makeText(k.a(this.g), k.a(this.g).getString(R.string.loading_video_error_tip) + "  (" + i + "," + i2 + ")", 0).show();
            e.printStackTrace();
        }
    }
}
