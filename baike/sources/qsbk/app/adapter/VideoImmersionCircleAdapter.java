package qsbk.app.adapter;

import android.app.Activity;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.e.ads.nativ.MediaView;
import com.qq.e.ads.nativ.NativeMediaADData;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.R;
import qsbk.app.activity.VideoFullScreenActivity;
import qsbk.app.activity.VideoFullScreenActivity$SimpleVideoInfo;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.CircleArticle;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.video.CircleVideoPlayerView;
import qsbk.app.video.LightActuator;
import qsbk.app.widget.BaseCell;
import qsbk.app.widget.RatingView;
import qsbk.app.widget.qiuyoucircle.NormalCell;

public class VideoImmersionCircleAdapter extends BaseImageAdapter implements OnScrollListener {
    public static VideoImmersionCell lastSelect;
    public static GdtVideoImmersionCell lastSelectGdt;
    private int a;
    private int b;
    private ShareUtils$OnCircleShareStartListener c;
    private int e;

    public class GdtVideoImmersionCell extends BaseCell {
        ProgressBar a;
        ImageView b;
        MediaView c;
        TextView d;
        TextView e;
        TextView f;
        ImageView g;
        ImageView h;
        RatingView i;
        View j;
        TextView k;
        View l;
        View m;
        View n;
        View o;
        final /* synthetic */ VideoImmersionCircleAdapter p;
        private LightActuator t;
        private LightActuator u;
        private NativeMediaADData v;

        public GdtVideoImmersionCell(VideoImmersionCircleAdapter videoImmersionCircleAdapter) {
            this.p = videoImmersionCircleAdapter;
        }

        public MediaView getMediaView() {
            return this.c;
        }

        public NativeMediaADData getRef() {
            return this.v;
        }

        public void onCreate() {
            setCellView((int) R.layout.feeds_gdt_immersion);
            this.a = (ProgressBar) findViewById(R.id.progress);
            this.b = (ImageView) findViewById(R.id.image);
            this.c = (MediaView) findViewById(R.id.video);
            this.d = (TextView) findViewById(R.id.userName);
            this.f = (TextView) findViewById(R.id.content);
            this.e = (TextView) findViewById(R.id.players);
            this.i = (RatingView) findViewById(R.id.ratting);
            this.g = (ImageView) findViewById(R.id.feedsAdSpread);
            this.j = findViewById(R.id.mainLayout);
            this.k = (TextView) findViewById(R.id.downbt);
            this.h = (ImageView) findViewById(R.id.userIcon);
            this.l = findViewById(R.id.immersion_mask1);
            this.m = findViewById(R.id.immersion_mask2);
            this.n = findViewById(R.id.immersion_mask3);
            this.o = findViewById(R.id.layerMask);
            this.s = TileBackground.getBackgroud(this.b.getContext(), BgImageType.AD);
            this.t = new eo(this, 10, 50);
            this.u = new ep(this, 10, 50);
        }

        public void onUpdate() {
            this.v = (NativeMediaADData) getItem();
            VideoImmersionCircleAdapter.lastSelectGdt = this;
            FrescoImageloader.displayAvatar(this.h, this.v.getIconUrl());
            this.d.setText(this.v.getTitle());
            this.f.setText(this.v.getDesc());
            FrescoImageloader.displayImage(this.b, this.v.getImgUrl(), this.s);
            if (this.v.isAPP()) {
                this.e.setText("本广告由腾讯广点通提供");
                this.i.setVisibility(8);
                a(this.v.getAPPStatus());
            } else {
                this.e.setText("本广告由腾讯广点通提供");
                this.i.setVisibility(8);
                a("查看");
            }
            this.j.setOnClickListener(new eq(this));
            this.v.onExposured(getCellView());
            if (this.v.isVideoAD() && this.v.isVideoLoaded()) {
                this.b.setVisibility(8);
                this.c.setVisibility(0);
                this.v.bindView(this.c, false);
                this.v.setVolumeOn(true);
                this.v.setMediaListener(new et(this));
                return;
            }
            this.b.setVisibility(0);
            this.c.setVisibility(8);
        }

        public void onItemChange(Object obj) {
            this.t.reset();
            this.u.reset();
        }

        private void a(int i) {
            String str = "";
            switch (i) {
                case 1:
                    str = "点击启动";
                    break;
                case 4:
                    str = "下载中";
                    break;
                case 8:
                    str = "点击安装";
                    break;
                case 16:
                    str = "点击重试";
                    break;
                default:
                    str = "下载";
                    break;
            }
            a(str);
        }

        private void a(String str) {
            ((TextView) findViewById(R.id.downbt)).setText(str);
        }
    }

    public class VideoImmersionCell extends NormalCell {
        final /* synthetic */ VideoImmersionCircleAdapter a;
        private LightActuator b;
        private LightActuator c;
        private TextView d;
        private View e;
        private View f;
        private View g;
        private TextView h;
        private ImageView i;

        public VideoImmersionCell(VideoImmersionCircleAdapter videoImmersionCircleAdapter, ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener) {
            this.a = videoImmersionCircleAdapter;
            super(shareUtils$OnCircleShareStartListener);
        }

        public void setCellView(int i) {
            super.setCellView(R.layout.cell_qiuyoucircle_video_immersion);
        }

        private void a() {
            int size = this.a.m.size() - this.q;
            List arrayList = new ArrayList();
            for (int i = 0; i < size; i++) {
                VideoFullScreenActivity$SimpleVideoInfo videoFullScreenActivity$SimpleVideoInfo = new VideoFullScreenActivity$SimpleVideoInfo();
                Object obj = this.a.m.get(this.q + i);
                if (obj instanceof CircleArticle) {
                    CircleArticle circleArticle = (CircleArticle) obj;
                    videoFullScreenActivity$SimpleVideoInfo.url = circleArticle.getVideoUrl();
                    videoFullScreenActivity$SimpleVideoInfo.width = circleArticle.getVideoWidth();
                    videoFullScreenActivity$SimpleVideoInfo.height = circleArticle.getVideoHeight();
                    videoFullScreenActivity$SimpleVideoInfo.extraType = VideoFullScreenActivity$SimpleVideoInfo.TYPE_CircleArticle;
                    videoFullScreenActivity$SimpleVideoInfo.extra = CircleArticle.toJSONObject(circleArticle).toString();
                    if (i == 0) {
                        videoFullScreenActivity$SimpleVideoInfo.currentTime = this.playerView.getCurrentTime();
                    }
                    arrayList.add(videoFullScreenActivity$SimpleVideoInfo);
                }
            }
            VideoFullScreenActivity.launch(this.a.k, (VideoFullScreenActivity$SimpleVideoInfo[]) arrayList.toArray(new VideoFullScreenActivity$SimpleVideoInfo[arrayList.size()]), new eu(this, this.q));
        }

        public void onCreate() {
            super.onCreate();
            this.playerView.setLoop(false);
            ((CircleVideoPlayerView) this.playerView).setOnControlBarListener(new ev(this));
            this.playerView.setOnVideoEventListener(new ew(this));
            this.playerView.setOnVideoStateListener(new ex(this));
            ((CircleVideoPlayerView) this.playerView).setOnFullScreenClick(new ey(this));
            this.d = (TextView) findViewById(R.id.share_count);
            this.d.setOnClickListener(new ez(this));
            this.e = findViewById(R.id.immersion_mask1);
            this.f = findViewById(R.id.immersion_mask2);
            this.g = findViewById(R.id.immersion_mask3);
            this.h = (TextView) findViewById(R.id.hot_comment);
            this.i = (ImageView) findViewById(R.id.hot_comment_label);
            OnTouchListener faVar = new fa(this);
            this.e.setOnTouchListener(faVar);
            this.f.setOnTouchListener(faVar);
            this.g.setOnTouchListener(faVar);
            this.b = new fb(this, 10, 50);
            this.c = new fc(this, 10, 100);
            this.s = TileBackground.getBackgroud(getContext(), BgImageType.ARTICLE);
            this.actionView.setVisibility(8);
        }

        public void onUpdate() {
            super.onUpdate();
            this.nameView.setTextColor(-1);
            this.playerView.setOnClickListener(this.playerView);
            CircleArticle circleArticle = (CircleArticle) getItem();
            if (this.h == null) {
                return;
            }
            LayoutParams layoutParams;
            if (circleArticle.hotComment != null) {
                layoutParams = this.g.getLayoutParams();
                if (layoutParams != null || (layoutParams instanceof RelativeLayout.LayoutParams)) {
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                } else {
                    layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                }
                layoutParams.addRule(8, R.id.hot_comment_layout);
                this.g.setLayoutParams(layoutParams);
                return;
            }
            layoutParams = this.g.getLayoutParams();
            if (layoutParams != null || (layoutParams instanceof RelativeLayout.LayoutParams)) {
                layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            } else {
                layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            }
            layoutParams.addRule(8, R.id.distance);
            this.g.setLayoutParams(layoutParams);
        }

        public void onItemChange(Object obj) {
            this.b.reset();
            this.c.reset();
        }

        public void toLight() {
            this.c.light();
            if (!(VideoImmersionCircleAdapter.lastSelect == this || VideoImmersionCircleAdapter.lastSelect == null)) {
                VideoImmersionCircleAdapter.lastSelect.toOffLight(true);
            }
            VideoImmersionCircleAdapter.lastSelect = this;
            this.b.light();
        }

        public void toOffLight(boolean z) {
            this.b.offLight();
            if (z) {
                this.c.offLight();
            }
        }

        public void download() {
            this.playerView.download();
        }
    }

    public VideoImmersionCircleAdapter(ArrayList<Object> arrayList, Activity activity, ListView listView, int i, ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener) {
        super(arrayList, activity);
        this.l = listView;
        this.c = shareUtils$OnCircleShareStartListener;
        this.a = i;
    }

    public void setOffset(int i) {
        this.b = i;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public int getItemViewType(int i) {
        Object item = getItem(i);
        if (!(item instanceof CircleArticle) && (item instanceof NativeMediaADData)) {
            return 1;
        }
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseCell videoImmersionCell;
        switch (getItemViewType(i)) {
            case 0:
                if (view == null || !(view.getTag() instanceof VideoImmersionCell)) {
                    videoImmersionCell = new VideoImmersionCell(this, this.c);
                    videoImmersionCell.performCreate(i, viewGroup, getItem(i));
                    view = videoImmersionCell.getCellView();
                    view.setTag(videoImmersionCell);
                } else {
                    videoImmersionCell = (BaseCell) view.getTag();
                }
                videoImmersionCell.performUpdate(i, viewGroup, getItem(i));
                break;
            case 1:
                if (view == null || !(view.getTag() instanceof GdtVideoImmersionCell)) {
                    videoImmersionCell = new GdtVideoImmersionCell(this);
                    videoImmersionCell.performCreate(i, viewGroup, getItem(i));
                    view = videoImmersionCell.getCellView();
                    view.setTag(videoImmersionCell);
                } else {
                    videoImmersionCell = (BaseCell) view.getTag();
                }
                videoImmersionCell.performUpdate(i, viewGroup, getItem(i));
                break;
        }
        return view;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (lastSelect != null) {
            ((CircleVideoPlayerView) lastSelect.playerView).showControlBar(false, true);
        }
        this.e = i;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (lastSelect != null && this.e == 1) {
            ((CircleVideoPlayerView) lastSelect.playerView).showControlBar(false, true);
        }
    }
}
