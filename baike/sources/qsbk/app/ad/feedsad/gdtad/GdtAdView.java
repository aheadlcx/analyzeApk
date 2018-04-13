package qsbk.app.ad.feedsad.gdtad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.qq.e.ads.nativ.MediaView;
import com.qq.e.ads.nativ.NativeMediaADData;
import qsbk.app.R;
import qsbk.app.ad.feedsad.FeedsAdStat;
import qsbk.app.ad.feedsad.FeedsAdUtils;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.RatingView;

public class GdtAdView {
    LayoutInflater _mInflater;
    Context mContext;
    private NativeMediaADData ref;

    public class ViewHolder {
        TextView downbt;
        ImageView feedsAdSpread;
        ImageView icon;
        ImageView mAdIV;
        MediaView mAdVideo;
        View mainLayout;
        ImageView playVideo;
        ProgressBar progressBar;
        RatingView rating;
        NativeMediaADData ref;
        TextView tvContent;
        TextView tvSource;
        TextView tvTitle;

        public NativeMediaADData getRef() {
            return this.ref;
        }

        public MediaView getMediaView() {
            return this.mAdVideo;
        }
    }

    public GdtAdView(Context context) {
        this.mContext = context;
        this._mInflater = LayoutInflater.from(context);
    }

    public void init(NativeMediaADData nativeMediaADData) {
        this.ref = nativeMediaADData;
    }

    public NativeMediaADData getRef() {
        return this.ref;
    }

    public AdView getView(View view) {
        ViewHolder viewHolder;
        if (view == null || !(view instanceof AdView)) {
            View view2 = (AdView) this._mInflater.inflate(R.layout.feeds_gdt, null);
            ViewHolder viewHolder2 = new ViewHolder();
            viewHolder2.ref = this.ref;
            viewHolder2.progressBar = (ProgressBar) view2.findViewById(R.id.progress);
            viewHolder2.mAdIV = (ImageView) view2.findViewById(R.id.image);
            viewHolder2.mAdVideo = (MediaView) view2.findViewById(R.id.video);
            viewHolder2.tvTitle = (TextView) view2.findViewById(R.id.userName);
            viewHolder2.tvContent = (TextView) view2.findViewById(R.id.content);
            viewHolder2.tvSource = (TextView) view2.findViewById(R.id.players);
            viewHolder2.rating = (RatingView) view2.findViewById(R.id.ratting);
            viewHolder2.feedsAdSpread = (ImageView) view2.findViewById(R.id.feedsAdSpread);
            viewHolder2.mainLayout = view2.findViewById(R.id.mainLayout);
            viewHolder2.downbt = (TextView) view2.findViewById(R.id.downbt);
            viewHolder2.icon = (ImageView) view2.findViewById(R.id.userIcon);
            viewHolder2.playVideo = (ImageView) view2.findViewById(R.id.play_video);
            FeedsAdUtils.setImageViewLayoutParam(viewHolder2.mAdIV, 1280, 720);
            view2.setTag(viewHolder2);
            view = view2;
            viewHolder = viewHolder2;
        } else {
            view = (AdView) view;
            viewHolder = (ViewHolder) view.getTag();
            view.setTag(viewHolder);
        }
        if (!(viewHolder == null || this.mContext == null)) {
            if (UIHelper.isNightTheme()) {
                viewHolder.mainLayout.setBackgroundColor(UIHelper.getColor(R.color.main_bg_night));
                viewHolder.downbt.setBackgroundColor(UIHelper.getColor(R.color.downbt_background_night));
                viewHolder.downbt.setTextColor(UIHelper.getColor(R.color.downbt_text_color_night));
            } else {
                viewHolder.mainLayout.setBackgroundColor(UIHelper.getColor(R.color.white));
                viewHolder.downbt.setBackgroundColor(UIHelper.getColor(R.color.downbt_background));
                viewHolder.downbt.setTextColor(UIHelper.getColor(R.color.downbt_text_color));
            }
        }
        viewHolder.progressBar.setTag(this.ref.getImgUrl());
        viewHolder.progressBar.setVisibility(8);
        viewHolder.mAdIV.setTag(viewHolder.progressBar);
        LogUtil.d("ref.getImgUrl():" + this.ref.getImgUrl());
        FrescoImageloader.displayAvatar(viewHolder.icon, this.ref.getIconUrl());
        FrescoImageloader.displayImage(viewHolder.mAdIV, this.ref.getImgUrl(), TileBackground.getBackgroud(viewHolder.mAdIV.getContext(), BgImageType.AD));
        viewHolder.tvTitle.setText(this.ref.getTitle());
        viewHolder.tvContent.setText(this.ref.getDesc());
        viewHolder.feedsAdSpread.setImageResource(R.drawable.feedsadspread);
        viewHolder.tvTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        viewHolder.rating.setVisibility(0);
        if (this.ref.isAPP()) {
            viewHolder.tvSource.setText("本广告由腾讯广点通提供");
            viewHolder.rating.setVisibility(8);
            view.updateButtonText(this.ref.getAPPStatus());
        } else {
            viewHolder.tvSource.setText("本广告由腾讯广点通提供");
            viewHolder.rating.setVisibility(8);
            view.updateButtonText("查看");
        }
        view.bindPkg("");
        viewHolder.mainLayout.setOnClickListener(new b(this, view));
        this.ref.onExposured(view);
        if (this.ref.isVideoAD() && this.ref.isVideoLoaded()) {
            viewHolder.playVideo.setVisibility(0);
            viewHolder.playVideo.setOnClickListener(new e(this, view));
        } else {
            viewHolder.playVideo.setVisibility(8);
        }
        FeedsAdStat.stat(view, "gdt");
        return view;
    }
}
