package cn.xiaochuankeji.tieba.ui.mediabrowse.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.htjyb.b.a.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl;
import cn.xiaochuankeji.tieba.background.utils.a.b;
import cn.xiaochuankeji.tieba.background.utils.a.e;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.widget.VideoView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.tencent.open.SocialConstants;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnPreparedListener;

public class i extends c implements a, cn.htjyb.netlib.a.a {
    private VideoView a;
    private WebImageView b;
    private RoundProgressBar c;
    private ImageView d;
    private int e;
    private cn.htjyb.b.a f;
    private cn.htjyb.b.a g;
    private boolean h = false;
    private boolean i = false;
    private boolean j;
    private String k;
    private boolean l = false;
    private b m = new b();

    public static i a(int i, boolean z, long j, cn.htjyb.b.a aVar, cn.htjyb.b.a aVar2) {
        Bundle b = c.b(i, z, j, aVar, aVar2);
        i iVar = new i();
        iVar.setArguments(b);
        return iVar;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.e = arguments.getInt(RequestParameters.POSITION);
        this.i = arguments.getBoolean("show");
        this.j = this.i;
        this.f = PictureImpl.buildPictureBy(arguments.getBundle(SocialConstants.PARAM_AVATAR_URI));
        arguments = arguments.getBundle("thumbpic");
        if (arguments != null) {
            this.g = PictureImpl.buildPictureBy(arguments);
        }
        this.k = this.f.cachePath();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_video_browse_download, viewGroup, false);
        a(inflate);
        return inflate;
    }

    private void a(View view) {
        this.a = (VideoView) view.findViewById(R.id.videoView);
        this.b = (WebImageView) view.findViewById(R.id.pvThumbImg);
        this.d = (ImageView) view.findViewById(R.id.ivProgressBg);
        this.c = (RoundProgressBar) view.findViewById(R.id.roundPBar);
    }

    public void onResume() {
        super.onResume();
        if (this.h) {
            this.h = false;
            b();
            return;
        }
        if (this.i) {
            b();
        } else {
            a();
        }
        if (true == this.l) {
            this.m.b();
        }
    }

    public void onPause() {
        super.onPause();
        this.h = true;
        if (this.m.c()) {
            this.m.d();
            e.a().a(this.f.getPictureID(), (long) this.m.e());
        }
    }

    public void a() {
        b(false);
        this.j = false;
    }

    public void b() {
        b(true);
        this.j = true;
    }

    private void b(boolean z) {
        if (this.g != null) {
            this.b.setImageURI(this.g.webpDownloadUrl());
        }
        if (!z) {
            g();
            if (!this.f.hasLocalFile() && !this.f.isDownloading()) {
                this.f.registerPictureDownloadListener(this);
                this.f.download(true, this);
            }
        } else if (this.f.hasLocalFile()) {
            f();
        } else if (!this.f.isDownloading()) {
            g();
            this.f.registerPictureDownloadListener(this);
            this.f.download(true, this);
        }
    }

    private void f() {
        this.b.setVisibility(8);
        this.c.setVisibility(8);
        this.d.setVisibility(8);
        this.a.setVisibility(0);
        this.a.setVideoPath(this.k);
        this.a.start();
        this.a.setOnPreparedListener(new IMediaPlayer$OnPreparedListener(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void onPrepared(IMediaPlayer iMediaPlayer) {
                iMediaPlayer.setLooping(true);
            }
        });
    }

    private void g() {
        this.b.setVisibility(0);
        this.c.setVisibility(0);
        this.d.setVisibility(0);
        this.a.setVisibility(8);
    }

    public void a(cn.htjyb.b.a aVar, boolean z, int i, String str) {
        aVar.unregisterPictureDownloadListener(this);
        if (z) {
            if (this.j) {
                f();
            } else {
                g();
            }
        } else if (str == null) {
        } else {
            if (str.trim().toLowerCase().contains("filenotfound")) {
                g.a("sorry:fileNotFound");
            } else {
                g.a(str);
            }
        }
    }

    public void a(int i, int i2) {
        this.c.setProgress((int) ((((float) i2) / ((float) i)) * 100.0f));
    }

    public int c() {
        return this.e;
    }

    public cn.htjyb.b.a d() {
        return this.f;
    }

    public void e() {
        if (this.f != null) {
            this.f.unregisterPictureDownloadListener(this);
            this.f.cancelDownload();
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.l = z;
        if (this.l) {
            this.m.b();
        } else if (this.m.c()) {
            this.m.d();
            e.a().a(this.f.getPictureID(), (long) this.m.e());
        }
    }
}
