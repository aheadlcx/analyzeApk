package cn.xiaochuankeji.tieba.ui.homepage.ugc;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.json.UgcEventSummaryJson.UgcEventJsonInRecommend;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.webview.WebActivity;

public class d extends FrameLayout {
    private LayoutInflater a;
    private WebImageView b;
    private int c = 0;
    private Object d = null;

    public d(Context context) {
        int a;
        super(context);
        this.a = LayoutInflater.from(context);
        this.a.inflate(R.layout.view_ugcevent_in_recommend, this);
        this.b = (WebImageView) findViewById(R.id.picEvent);
        if (VERSION.SDK_INT >= 21) {
            a = e.a(10.0f);
        } else {
            a = e.a(5.0f);
        }
        this.c = a + ((int) (((float) (e.b(getContext()) - e.a(20.0f))) / 2.55f));
        LayoutParams layoutParams = new StaggeredGridLayoutManager.LayoutParams(-1, this.c);
        layoutParams.setFullSpan(true);
        setLayoutParams(layoutParams);
    }

    public void setData(final UgcEventJsonInRecommend ugcEventJsonInRecommend) {
        this.d = ugcEventJsonInRecommend;
        this.b.setWebImage(b.b((long) ugcEventJsonInRecommend.img.id));
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d b;

            public void onClick(View view) {
                if (ugcEventJsonInRecommend.type.equals("h5")) {
                    WebActivity.a(this.b.getContext(), new cn.xiaochuan.jsbridge.b("跟拍活动", ugcEventJsonInRecommend.url));
                } else if (ugcEventJsonInRecommend.type.equals("post")) {
                    UgcVideoActivity.a(this.b.getContext(), ugcEventJsonInRecommend.post, ugcEventJsonInRecommend.post.pid != 0, "index-ugcvideo-banner", null);
                }
            }
        });
        setTag(this.d);
    }

    public Object getTag() {
        return this.d;
    }

    public int getThisHeight() {
        return this.c;
    }
}
