package cn.xiaochuankeji.tieba.ui.mediabrowse.component;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.htjyb.b.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl;
import cn.xiaochuankeji.tieba.background.utils.a.e;
import cn.xiaochuankeji.tieba.ui.widget.bigImage.BigImageView;
import cn.xiaochuankeji.tieba.ui.widget.bigImage.h;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.tencent.open.SocialConstants;

public class b extends c {
    private BigImageView a;
    private WebImageView b;
    private RoundProgressBar c;
    private ImageView d;
    private int e;
    private a f;
    private a g;
    private boolean h = false;
    private AsyncTask i;
    private boolean j;
    private String k;
    private boolean l = false;
    private int m;
    private cn.xiaochuankeji.tieba.background.utils.a.b n = new cn.xiaochuankeji.tieba.background.utils.a.b();

    public static b a(int i, boolean z, long j, a aVar, a aVar2) {
        Bundle b = c.b(i, z, j, aVar, aVar2);
        b bVar = new b();
        bVar.setArguments(b);
        return bVar;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.e = arguments.getInt(RequestParameters.POSITION);
        this.j = false;
        this.f = PictureImpl.buildPictureBy(arguments.getBundle(SocialConstants.PARAM_AVATAR_URI));
        arguments = arguments.getBundle("thumbpic");
        if (arguments != null) {
            this.g = PictureImpl.buildPictureBy(arguments);
        }
        this.k = this.f.cachePath();
    }

    public void a() {
    }

    public void b() {
    }

    public int c() {
        return this.e;
    }

    public a d() {
        return this.f;
    }

    public void e() {
        this.j = true;
        if (this.f != null) {
            this.f.cancelDownload();
        }
        if (this.i != null) {
            this.i.cancel(true);
            this.i = null;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_media_browse_image, viewGroup, false);
        a(inflate);
        return inflate;
    }

    private void a(View view) {
        this.a = (BigImageView) view.findViewById(R.id.zoomImageView);
        this.a.setInitScaleType(3);
        this.a.getImageView().setZoomEnabled(true);
        this.a.getImageView().setMinimumScaleType(1);
        this.a.setOptimizeDisplay(true);
        this.a.setProgressIndicator(new h(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public View a(BigImageView bigImageView) {
                return null;
            }

            public void a() {
                this.a.c.setVisibility(0);
                this.a.d.setVisibility(0);
            }

            public void a(int i) {
                this.a.c.setProgress(i);
            }

            public void b() {
                this.a.c.setVisibility(8);
                this.a.d.setVisibility(8);
            }
        });
        this.b = (WebImageView) view.findViewById(R.id.pvThumbImg);
        this.d = (ImageView) view.findViewById(R.id.ivProgressBg);
        this.c = (RoundProgressBar) view.findViewById(R.id.roundPBar);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        g();
    }

    public void onResume() {
        super.onResume();
        if (this.h) {
            this.h = false;
            return;
        }
        f();
        if (true == this.l) {
            this.n.b();
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.l = z;
        if (this.l) {
            this.n.b();
        } else if (this.n.c()) {
            this.n.d();
            e.a().a(this.f.getPictureID(), (long) this.n.e());
        }
    }

    private void f() {
        com.izuiyou.a.a.b.e("url:" + this.f.webpDownloadUrl());
        if (this.g != null) {
            this.a.a(Uri.parse(this.g.webpDownloadUrl()), Uri.parse(this.f.webpDownloadUrl()));
        } else if (this.f.hasLocalFile()) {
            this.a.a(Uri.parse("file://" + this.f.getUrl()));
        } else {
            this.a.a(Uri.parse(this.f.webpDownloadUrl()));
        }
    }

    public void onPause() {
        super.onPause();
        this.h = true;
        if (this.n.c()) {
            this.n.d();
            e.a().a(this.f.getPictureID(), (long) this.n.e());
        }
    }

    private void g() {
        this.m = ViewConfiguration.get(getActivity()).getScaledTouchSlop();
        this.a.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public boolean onLongClick(View view) {
                this.a.a(this.a.f);
                return true;
            }
        });
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.isAdded()) {
                    this.a.getActivity().finish();
                }
            }
        });
    }
}
