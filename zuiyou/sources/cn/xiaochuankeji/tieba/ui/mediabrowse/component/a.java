package cn.xiaochuankeji.tieba.ui.mediabrowse.component;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl;
import cn.xiaochuankeji.tieba.background.utils.a.b;
import cn.xiaochuankeji.tieba.background.utils.a.e;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.facebook.drawee.a.a.c;
import com.facebook.drawee.drawable.n$b;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.open.SocialConstants;

public class a extends c implements cn.htjyb.b.a.a, cn.htjyb.netlib.a.a {
    private FrameLayout a;
    private WebImageView b;
    private RoundProgressBar c;
    private ImageView d;
    private SimpleDraweeView e;
    private int f;
    private cn.htjyb.b.a g;
    private cn.htjyb.b.a h;
    private boolean i = false;
    private String j;
    private boolean k = false;
    private b l = new b();

    public static a a(int i, boolean z, long j, cn.htjyb.b.a aVar, cn.htjyb.b.a aVar2) {
        Bundle b = c.b(i, z, j, aVar, aVar2);
        a aVar3 = new a();
        aVar3.setArguments(b);
        return aVar3;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.f = arguments.getInt(RequestParameters.POSITION);
        this.g = PictureImpl.buildPictureBy(arguments.getBundle(SocialConstants.PARAM_AVATAR_URI));
        arguments = arguments.getBundle("thumbpic");
        if (arguments != null) {
            this.h = PictureImpl.buildPictureBy(arguments);
        }
        this.j = this.g.cachePath();
        com.izuiyou.a.a.b.e("picture url:" + this.g.downloadUrl());
    }

    public void onResume() {
        super.onResume();
        if (this.i) {
            this.i = false;
            return;
        }
        if (this.g.hasLocalFile()) {
            f();
        } else {
            if (this.h != null) {
                this.b.setImageURI(this.h.webpDownloadUrl());
            }
            this.c.setVisibility(0);
            this.d.setVisibility(0);
            this.c.setProgress(0);
            if (!this.g.isDownloading()) {
                this.g.registerPictureDownloadListener(this);
                this.g.download(true, this);
            }
        }
        if (true == this.k) {
            this.l.b();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.l.c()) {
            this.l.d();
            e.a().a(this.g.getPictureID(), (long) this.l.e());
        }
    }

    public void a() {
    }

    public void b() {
    }

    private void f() {
        this.e.setController(((com.facebook.drawee.a.a.e) c.a().a(Uri.parse("file://" + this.j)).b(true)).k());
        this.c.setVisibility(8);
        this.d.setVisibility(8);
        this.b.setVisibility(8);
    }

    public int c() {
        return this.f;
    }

    public cn.htjyb.b.a d() {
        return this.g;
    }

    public void e() {
        if (this.g != null) {
            this.g.unregisterPictureDownloadListener(this);
            this.g.cancelDownload();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_media_browse_gif, viewGroup, false);
        a(inflate);
        return inflate;
    }

    private void a(View view) {
        this.a = (FrameLayout) view.findViewById(R.id.rootView);
        this.b = (WebImageView) view.findViewById(R.id.pvThumbImg);
        this.d = (ImageView) view.findViewById(R.id.ivProgressBg);
        this.c = (RoundProgressBar) view.findViewById(R.id.roundPBar);
        if (this.e == null) {
            this.e = new SimpleDraweeView(getActivity());
            LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 17;
            this.e.setLayoutParams(layoutParams);
            this.e.setHierarchy(new com.facebook.drawee.generic.b(getResources()).a(300).e(n$b.c).t());
            this.a.addView(this.e, 1);
        }
    }

    public void a(cn.htjyb.b.a aVar, boolean z, int i, String str) {
        aVar.unregisterPictureDownloadListener(this);
        if (z) {
            f();
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

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.k = z;
        if (this.k) {
            this.l.b();
        } else if (this.l.c()) {
            this.l.d();
            e.a().a(this.g.getPictureID(), (long) this.l.e());
        }
    }
}
