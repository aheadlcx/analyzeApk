package qsbk.app.widget.qiuyoucircle;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ak.android.engine.nav.NativeAd;
import com.baidu.mobad.feeds.NativeResponse;
import com.qq.e.ads.nativ.MediaView;
import com.qq.e.ads.nativ.NativeMediaADData;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.ad.feedsad.FeedsAd;
import qsbk.app.ad.feedsad.FeedsAdStat;
import qsbk.app.ad.feedsad.FeedsAdUtils;
import qsbk.app.ad.feedsad.baiduad.BaiduAdItemData;
import qsbk.app.ad.feedsad.gdtad.GdtAdItemData;
import qsbk.app.ad.feedsad.qbad.QbAdItem;
import qsbk.app.ad.feedsad.qhad.QhAdItemData;
import qsbk.app.core.utils.NetworkUtils;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.BaseCell;
import qsbk.app.widget.RatingView;

public abstract class BaseAdCell extends BaseCell {
    protected ImageView a;
    protected TextView b;
    protected TextView c;
    protected ImageView d;
    protected ProgressBar e;
    protected RatingView f;
    protected TextView g;
    protected TextView h;
    protected MediaView i;
    protected ImageView j;

    public static class BaiduAdCell extends BaseAdCell {
        private int k;

        private String a(int i) {
            switch (i) {
                case 0:
                    return getContext().getResources().getString(R.string.try_agin);
                case 1:
                    return getContext().getResources().getString(R.string.download);
                case 2:
                    return getContext().getResources().getString(R.string.downloading);
                case 3:
                    return getContext().getResources().getString(R.string.click_to_install);
                case 4:
                    return getContext().getResources().getString(R.string.click_to_start);
                default:
                    return null;
            }
        }

        public void updateStatus(int i) {
            this.k = i;
            if (this.h != null) {
                this.h.setText(a(i));
            }
        }

        public void onClick() {
            super.onClick();
            NativeResponse ref = ((BaiduAdItemData) getItem()).getView().getRef();
            View cellView = getCellView();
            Context context = cellView.getContext();
            if (ref.isDownloadApp()) {
                String network = HttpUtils.getNetwork(context);
                if (FeedsAdUtils.needShowConfirm(network)) {
                    new Builder(context).setTitle("温馨提示").setCancelable(true).setMessage("当前为" + network + "网络，开始下载应用？").setNegativeButton("取消", new b(this)).setPositiveButton("确认", new a(this, ref, cellView)).create().show();
                } else {
                    Object optString = QsbkApp.indexConfig.optString("ad_click_toast");
                    if (!TextUtils.isEmpty(optString)) {
                        ToastAndDialog.makeNeutralToast(QsbkApp.mContext, optString, Integer.valueOf(0)).show();
                    }
                    ref.handleClick(cellView);
                    ReportAdForMedalUtil.report(AD_PROVIDER.BAIDU, AD_TYPE.QIUYOUQUANLIST);
                }
            } else {
                ref.handleClick(cellView);
                ReportAdForMedalUtil.report(AD_PROVIDER.BAIDU, AD_TYPE.QIUYOUQUANLIST);
            }
            FeedsAdStat.onClick(cellView.getContext(), "baidu");
        }

        public void onUpdate() {
            NativeResponse ref = ((BaiduAdItemData) getItem()).getView().getRef();
            this.g.setText("本广告由百度广告提供");
            this.e.setTag(ref.getImageUrl());
            this.d.setTag(this.e);
            if (ref.isDownloadApp()) {
                this.h.setText(a(this.k));
            } else {
                this.h.setText("查看");
            }
            this.c.setText(ref.getDesc());
            this.b.setText(ref.getTitle());
            displayAvatar(this.a, ref.getIconUrl());
            displayImage(this.d, ref.getImageUrl(), this.s);
            ref.recordImpression(this.h);
        }
    }

    public static class GDTAdCell extends BaseAdCell {
        private NativeMediaADData k;

        public NativeMediaADData getRef() {
            return this.k;
        }

        public MediaView getMediaView() {
            return this.i;
        }

        public void onClick() {
            super.onClick();
            NativeMediaADData ref = ((GdtAdItemData) getItem()).getView().getRef();
            int aPPStatus = ref.getAPPStatus();
            View cellView = getCellView();
            if (aPPStatus == 1 || aPPStatus == 4 || aPPStatus == 8) {
                ref.onClicked(cellView);
                FeedsAdStat.onClick(cellView.getContext(), "gdt_native");
                ReportAdForMedalUtil.report(AD_PROVIDER.GDT, AD_TYPE.QIUYOUQUANLIST);
                return;
            }
            Context context = cellView.getContext();
            String network = HttpUtils.getNetwork(context);
            if (FeedsAdUtils.needShowConfirm(network)) {
                new Builder(context).setTitle("温馨提示").setCancelable(true).setMessage("当前为" + network + "网络，开始下载应用？").setNegativeButton("取消", new d(this)).setPositiveButton("确认", new c(this, ref, cellView, context)).create().show();
                return;
            }
            ref.onClicked(cellView);
            FeedsAdStat.onClick(cellView.getContext(), "gdt_native");
            ReportAdForMedalUtil.report(AD_PROVIDER.GDT, AD_TYPE.QIUYOUQUANLIST);
        }

        public void onUpdate() {
            this.k = ((GdtAdItemData) getItem()).getView().getRef();
            this.e.setTag(this.k.getImgUrl());
            this.d.setTag(this.e);
            displayAvatar(this.a, this.k.getIconUrl());
            displayImage(this.d, this.k.getImgUrl(), this.s);
            this.b.setText(this.k.getTitle());
            this.c.setText(this.k.getDesc());
            this.c.setTextSize(QsbkApp.getInstance().getCurrentContentTextSize());
            this.g.setText("本广告由腾讯广点通提供");
            this.f.setVisibility(8);
            this.h.setText(a(this.k.getAPPStatus()));
            FeedsAdStat.stat(getCellView(), "gdt");
            this.k.onExposured(getCellView());
            if (this.k.isVideoAD() && this.k.isVideoLoaded()) {
                this.j.setVisibility(0);
                this.j.setOnClickListener(new e(this));
                return;
            }
            this.j.setVisibility(8);
        }

        private String a(int i) {
            String str = "";
            switch (i) {
                case 1:
                    return "点击启动";
                case 4:
                    return "下载中";
                case 8:
                    return "点击安装";
                case 16:
                    return "点击重试";
                default:
                    return "下载";
            }
        }
    }

    public static class QHAdCell extends BaseAdCell {
        public void onClick() {
            super.onClick();
            NativeAd nativeAd = ((QhAdItemData) getItem()).getAdView().getmNativeAd();
            View cellView = getCellView();
            Context context = cellView.getContext();
            if (NetworkUtils.getInstance().isWifiAvailable()) {
                if (context instanceof Activity) {
                    nativeAd.onAdClick((Activity) context, cellView);
                    FeedsAdStat.onClick(context, "qh_native");
                }
                ReportAdForMedalUtil.report(AD_PROVIDER.QH, AD_TYPE.QIUSHILIST);
                return;
            }
            new Builder(context).setTitle("温馨提示").setCancelable(true).setMessage("当前为" + HttpUtils.getNetwork(getContext()) + "网络，开始下载应用？").setNegativeButton("取消", new g(this)).setPositiveButton("确认", new f(this, nativeAd, context, cellView)).create().show();
        }

        public void onUpdate() {
            QhAdItemData qhAdItemData = (QhAdItemData) getItem();
            NativeAd nativeAd = qhAdItemData.getAdView().getmNativeAd();
            this.g.setText("本广告由360提供");
            JSONObject content = nativeAd.getContent();
            CharSequence optString = content.optString("title");
            CharSequence optString2 = content.optString(SocialConstants.PARAM_APP_DESC);
            String optString3 = content.optString("contentimg");
            String optString4 = content.optString("logo");
            CharSequence optString5 = content.optString("btntext");
            this.e.setTag(optString3);
            this.d.setTag(this.e);
            this.h.setText(optString5);
            this.c.setText(optString2);
            this.b.setText(optString);
            displayAvatar(this.a, optString4);
            displayImage(this.d, optString3, this.s);
            FeedsAdStat.stat(getCellView(), "qh");
            FeedsAd.getQiuyouCircleInstance().setAdShowed(qhAdItemData, AD_PROVIDER.QH);
            nativeAd.onAdShowed(getCellView());
        }
    }

    public static class QbAdCell extends BaseAdCell {
        protected ImageView k;
        protected ImageView l;

        public void onUpdate() {
            QbAdItem qbAdItem = (QbAdItem) getItem();
            this.e.setTag(qbAdItem.banner);
            this.d.setTag(this.e);
            FeedsAdUtils.setImageViewLayoutParam(this.d, qbAdItem.banner_w, qbAdItem.banner_h);
            this.a.setImageDrawable(null);
            displayAvatar(this.a, qbAdItem.user_icon);
            displayImage(this.d, qbAdItem.banner, this.s);
            this.b.setText(qbAdItem.user_name);
            this.c.setText(qbAdItem.description);
            this.c.setTextSize(QsbkApp.getInstance().getCurrentContentTextSize());
            if (TextUtils.isEmpty(qbAdItem.ad_source)) {
                this.g.setVisibility(8);
            } else {
                this.g.setVisibility(0);
                this.g.setText(qbAdItem.ad_source);
            }
            if (qbAdItem.ratting > 0) {
                this.f.setRating(qbAdItem.ratting);
                this.f.setVisibility(0);
            } else {
                this.f.setVisibility(8);
            }
            if (TextUtils.isEmpty(qbAdItem.type_icon)) {
                this.l.setVisibility(8);
            } else {
                this.l.setImageDrawable(null);
                if (!UIHelper.isNightTheme() || TextUtils.isEmpty(qbAdItem.type_icon_night)) {
                    displayImage(this.l, qbAdItem.type_icon, null);
                } else {
                    displayImage(this.l, qbAdItem.type_icon_night, null);
                }
                this.l.setVisibility(0);
            }
            if (TextUtils.isEmpty(qbAdItem.user_badge)) {
                this.k.setVisibility(8);
            } else {
                this.k.setImageDrawable(null);
                if (TextUtils.isEmpty(qbAdItem.user_badge_night) || !UIHelper.isNightTheme()) {
                    displayImage(this.k, qbAdItem.user_badge, null);
                } else {
                    displayImage(this.k, qbAdItem.user_badge_night, null);
                }
                this.k.setVisibility(0);
            }
            if (TextUtils.isEmpty(qbAdItem.btn_txt)) {
                this.h.setVisibility(8);
            } else {
                this.h.setVisibility(0);
                this.h.setText(qbAdItem.getBtnText());
            }
            qbAdItem.addAdViewCount();
            qbAdItem.setAdLastShowTimeStamp();
        }

        public void onClick() {
            super.onClick();
            QbAdItem qbAdItem = (QbAdItem) getItem();
            View cellView = getCellView();
            if (!QbAdItem.QB_APP.equals(qbAdItem.action_type)) {
                qbAdItem.onAppClick(cellView);
                ReportAdForMedalUtil.report(AD_PROVIDER.QIUBAI, AD_TYPE.QIUYOUQUANLIST);
            } else if (qbAdItem.getApkStatus(qbAdItem.getUrlAndPackageNameFromArsg(qbAdItem.action_args)) == 1) {
                Context context = cellView.getContext();
                String network = HttpUtils.getNetwork(context);
                if (FeedsAdUtils.needShowConfirm(network)) {
                    new Builder(context).setTitle("温馨提示").setCancelable(true).setMessage("当前为" + network + "网络，开始下载应用？").setNegativeButton("取消", new i(this)).setPositiveButton("确认", new h(this, qbAdItem, cellView)).create().show();
                    return;
                }
                qbAdItem.onAppClick(cellView);
                ReportAdForMedalUtil.report(AD_PROVIDER.QIUBAI, AD_TYPE.QIUYOUQUANLIST);
            } else {
                qbAdItem.onAppClick(cellView);
                ReportAdForMedalUtil.report(AD_PROVIDER.QIUBAI, AD_TYPE.QIUYOUQUANLIST);
            }
        }

        public void onCreate() {
            setCellView(R.layout.feeds_qb_ad);
            this.a = (ImageView) findViewById(R.id.userIcon);
            this.b = (TextView) findViewById(R.id.userName);
            this.c = (TextView) findViewById(R.id.content);
            this.d = (ImageView) findViewById(R.id.image);
            this.e = (ProgressBar) findViewById(R.id.progress);
            this.e.setVisibility(8);
            this.f = (RatingView) findViewById(R.id.ratting);
            this.g = (TextView) findViewById(R.id.players);
            this.h = (TextView) findViewById(R.id.downbt);
            this.k = (ImageView) findViewById(R.id.userBadge);
            this.l = (ImageView) findViewById(R.id.feedsAdSpread);
        }
    }

    public void onCreate() {
        setCellView(R.layout.cell_qiuyoucircle_ad);
        this.a = (ImageView) findViewById(R.id.avatar);
        this.b = (TextView) findViewById(R.id.nickname);
        this.c = (TextView) findViewById(R.id.content);
        this.c.setTextSize(QsbkApp.getInstance().getCurrentContentTextSize());
        this.d = (ImageView) findViewById(R.id.image);
        this.i = (MediaView) findViewById(R.id.video);
        this.j = (ImageView) findViewById(R.id.play_video);
        this.e = (ProgressBar) findViewById(R.id.progress);
        this.e.setVisibility(8);
        this.f = (RatingView) findViewById(R.id.ratting);
        this.g = (TextView) findViewById(R.id.players);
        this.h = (TextView) findViewById(R.id.downbt);
        this.s = TileBackground.getBackgroud(getContext(), BgImageType.AD);
    }
}
