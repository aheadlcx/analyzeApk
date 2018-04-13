package cn.xiaochuankeji.tieba.ui.post.postitem;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qq.e.ads.nativ.MediaView;

public class AdsItemHolder_ViewBinding implements Unbinder {
    private AdsItemHolder b;

    @UiThread
    public AdsItemHolder_ViewBinding(AdsItemHolder adsItemHolder, View view) {
        this.b = adsItemHolder;
        adsItemHolder.memberView = (RelativeLayout) b.b(view, R.id.llMemberInfo, "field 'memberView'", RelativeLayout.class);
        adsItemHolder.memberAvatar = (SimpleDraweeView) b.b(view, R.id.pvAvatar, "field 'memberAvatar'", SimpleDraweeView.class);
        adsItemHolder.memberName = (TextView) b.b(view, R.id.tvWriterName, "field 'memberName'", TextView.class);
        adsItemHolder.tediumAds = (ImageView) b.b(view, R.id.ivTediumPost, "field 'tediumAds'", ImageView.class);
        adsItemHolder.titleText = (TextView) b.b(view, R.id.ad_title, "field 'titleText'", TextView.class);
        adsItemHolder.imageCover = (SimpleDraweeView) b.b(view, R.id.iv_ads_cover, "field 'imageCover'", SimpleDraweeView.class);
        adsItemHolder.adsDur = (TextView) b.b(view, R.id.tv_ads_dur, "field 'adsDur'", TextView.class);
        adsItemHolder.btnPlay = (ImageView) b.b(view, R.id.iv_video_play, "field 'btnPlay'", ImageView.class);
        adsItemHolder.adContainer = b.a(view, R.id.ad_container, "field 'adContainer'");
        adsItemHolder.tvTitle = (TextView) b.b(view, R.id.link_title, "field 'tvTitle'", TextView.class);
        adsItemHolder.tvDescribe = (TextView) b.b(view, R.id.link_describe, "field 'tvDescribe'", TextView.class);
        adsItemHolder.btnDownload = (TextView) b.b(view, R.id.btn_download, "field 'btnDownload'", TextView.class);
        adsItemHolder.label = (TextView) b.b(view, R.id.tvAdsTip, "field 'label'", TextView.class);
        adsItemHolder.mediaView = (MediaView) b.b(view, R.id.gdt_media_view, "field 'mediaView'", MediaView.class);
    }

    @CallSuper
    public void a() {
        AdsItemHolder adsItemHolder = this.b;
        if (adsItemHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        adsItemHolder.memberView = null;
        adsItemHolder.memberAvatar = null;
        adsItemHolder.memberName = null;
        adsItemHolder.tediumAds = null;
        adsItemHolder.titleText = null;
        adsItemHolder.imageCover = null;
        adsItemHolder.adsDur = null;
        adsItemHolder.btnPlay = null;
        adsItemHolder.adContainer = null;
        adsItemHolder.tvTitle = null;
        adsItemHolder.tvDescribe = null;
        adsItemHolder.btnDownload = null;
        adsItemHolder.label = null;
        adsItemHolder.mediaView = null;
    }
}
