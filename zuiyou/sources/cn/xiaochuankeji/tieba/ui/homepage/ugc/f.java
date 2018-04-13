package cn.xiaochuankeji.tieba.ui.homepage.ugc;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.ugcvideo.a;
import cn.xiaochuankeji.tieba.background.d.e;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.background.utils.share.UgcVideoShareStruct;
import cn.xiaochuankeji.tieba.background.utils.share.b;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.json.UgcVideoShareJson;
import cn.xiaochuankeji.tieba.ui.base.j;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.File;
import org.greenrobot.eventbus.c;

public class f extends j implements OnClickListener {
    private UgcVideoInfoBean a;
    private boolean b;

    protected f(Context context, boolean z) {
        super(context);
        this.b = z;
    }

    protected View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.view_item_ugc_video_share, null);
    }

    protected void a(View view) {
        view.setOnClickListener(this);
        view.findViewById(R.id.ivShareWeibo).setOnClickListener(this);
        view.findViewById(R.id.ivShareWechat).setOnClickListener(this);
        view.findViewById(R.id.ivShareWeChatCircle).setOnClickListener(this);
        view.findViewById(R.id.ivShareQQ).setOnClickListener(this);
        view.findViewById(R.id.ivShareQQZone).setOnClickListener(this);
        view.findViewById(R.id.ivDownload).setOnClickListener(this);
    }

    public void a(UgcVideoInfoBean ugcVideoInfoBean) {
        this.a = ugcVideoInfoBean;
    }

    public void onClick(View view) {
        this.a.isCreatedByUserJustNow = false;
        ViewGroup viewGroup = (ViewGroup) f_().getParent();
        viewGroup.removeView(f_());
        viewGroup.findViewById(R.id.wivMemberCover).setVisibility(0);
        if (this.b) {
            viewGroup.findViewById(R.id.tvTime).setVisibility(0);
        } else {
            viewGroup.findViewById(R.id.llVideoInfo).setVisibility(0);
        }
        int id = view.getId();
        switch (id) {
            case R.id.ivShareQQ:
                a(1);
                break;
            case R.id.ivShareWechat:
                a(2);
                break;
            case R.id.ivShareWeChatCircle:
                a(4);
                break;
            case R.id.ivShareQQZone:
                a(5);
                break;
            case R.id.ivShareWeibo:
                a(3);
                break;
            case R.id.ivDownload:
                e eVar = new e();
                eVar.a = this.a.videoInfo.url;
                c.a().d(eVar);
                break;
        }
        if (id == R.id.ivDownload) {
            h.a("zy_event_ugcvideo_tab_follow", "发布成功后下载");
        } else if (id == R.id.ivShareWeibo || id == R.id.ivShareWechat || id == R.id.ivShareWeChatCircle || id == R.id.ivShareQQ || id == R.id.ivShareQQZone) {
            h.a("zy_event_ugcvideo_tab_follow", "发布成功后分享");
        }
    }

    private void a(final int i) {
        new a().b(this.a.id, "other").a(rx.a.b.a.a()).b(new rx.j<UgcVideoShareJson>(this) {
            final /* synthetic */ f b;

            public /* synthetic */ void onNext(Object obj) {
                a((UgcVideoShareJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                g.b(th.getMessage());
            }

            public void a(UgcVideoShareJson ugcVideoShareJson) {
                if (ugcVideoShareJson.shareTxt != null) {
                    Object obj = ugcVideoShareJson.shareTxt.title;
                    Object obj2 = ugcVideoShareJson.shareTxt.desp;
                    if (!TextUtils.isEmpty(obj) || !TextUtils.isEmpty(obj2)) {
                        b.a().a(i, (Activity) this.b.e_(), new UgcVideoShareStruct(obj, obj2, this.b.a(cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", (long) this.b.a.img.id, null)), cn.xiaochuankeji.tieba.background.utils.d.a.b(this.b.a.id, 0)));
                    }
                }
            }
        });
    }

    private String a(String str) {
        File a = cn.xiaochuankeji.tieba.common.c.a.a(ImageRequest.a(str));
        if (a == null || !a.exists()) {
            return null;
        }
        return a.getPath();
    }
}
