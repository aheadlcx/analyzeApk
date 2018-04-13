package cn.xiaochuankeji.tieba.ui.post.postitem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.ad.AdvertismentBean;
import cn.xiaochuankeji.tieba.background.ad.AdvertismentBean.AdMultiMedia;
import cn.xiaochuankeji.tieba.background.ad.PostAdExtraInfo;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.d.h;
import cn.xiaochuankeji.tieba.network.filedownload.e;
import cn.xiaochuankeji.tieba.push.proto.Push.Packet;
import cn.xiaochuankeji.tieba.ui.mediabrowse.local.LocalVideoPlayActivity;
import cn.xiaochuankeji.tieba.ui.post.a.b;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.ui.widget.k;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qq.e.ads.nativ.MediaListener;
import com.qq.e.ads.nativ.MediaView;
import com.qq.e.ads.nativ.NativeMediaADData;
import com.qq.e.comm.util.AdError;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.c;

public class AdsItemHolder extends ViewHolder {
    NativeMediaADData a;
    @BindView
    View adContainer;
    @BindView
    TextView adsDur;
    private Context b;
    @BindView
    TextView btnDownload;
    @BindView
    ImageView btnPlay;
    @BindView
    SimpleDraweeView imageCover;
    @BindView
    TextView label;
    @BindView
    MediaView mediaView;
    @BindView
    SimpleDraweeView memberAvatar;
    @BindView
    TextView memberName;
    @BindView
    RelativeLayout memberView;
    @BindView
    ImageView tediumAds;
    @BindView
    TextView titleText;
    @BindView
    TextView tvDescribe;
    @BindView
    TextView tvTitle;

    public AdsItemHolder(Context context, ViewGroup viewGroup) {
        super(LayoutInflater.from(context).inflate(R.layout.view_item_ads, viewGroup, false));
        this.b = context;
        ButterKnife.a(this, this.itemView);
    }

    public void a(AdvertismentBean advertismentBean) {
        this.memberView.setVisibility(0);
        b(advertismentBean);
        c(advertismentBean);
        d(advertismentBean);
        a.d().b(advertismentBean.attachCbuRL);
        cn.xiaochuankeji.tieba.ui.post.a.a.a(advertismentBean, 1004);
    }

    private void b(final AdvertismentBean advertismentBean) {
        if (advertismentBean.member != null) {
            this.memberAvatar.setImageURI(advertismentBean.member.avatarUrl);
            this.memberName.setText(advertismentBean.member.memberName);
            this.tediumAds.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AdsItemHolder b;

                public void onClick(View view) {
                    if (advertismentBean == null || advertismentBean.adFilters == null || advertismentBean.adFilters.size() == 0) {
                        c.a().d(new b(advertismentBean));
                        return;
                    }
                    k kVar = new k(this.b.itemView.getContext());
                    kVar.a(advertismentBean.createTediumReason(), new k.b(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void a(ArrayList<String> arrayList, String str) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("type", Integer.valueOf(advertismentBean.adType));
                            jSONObject.put("adid", Long.valueOf(advertismentBean.id));
                            jSONObject.put("c_type", Integer.valueOf(advertismentBean.c_type));
                            List arrayList2 = new ArrayList();
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                try {
                                    arrayList2.add(Integer.valueOf((String) it.next()));
                                } catch (Exception e) {
                                }
                            }
                            jSONObject.put("reasons", arrayList2);
                            new cn.xiaochuankeji.tieba.api.ad.a().a(jSONObject).g();
                            c.a().d(new b(advertismentBean));
                        }
                    });
                    kVar.show();
                }
            });
            this.label.setText(advertismentBean.adLabel);
        }
    }

    private void c(final AdvertismentBean advertismentBean) {
        if (TextUtils.isEmpty(advertismentBean.adTitle)) {
            this.titleText.setVisibility(8);
        } else {
            this.titleText.setVisibility(0);
            this.titleText.setText(advertismentBean.adTitle);
        }
        if (advertismentBean.media != null) {
            final AdMultiMedia adMultiMedia = (AdMultiMedia) advertismentBean.media.get(0);
            final boolean z = (adMultiMedia == null || adMultiMedia.videoUrls == null || adMultiMedia.videoUrls.size() <= 0) ? false : true;
            if (z) {
                this.btnPlay.setVisibility(0);
            } else {
                this.btnPlay.setVisibility(8);
            }
            if (adMultiMedia != null && adMultiMedia.imageUrls != null && adMultiMedia.imageUrls.size() > 0) {
                this.imageCover.setImageURI((String) adMultiMedia.imageUrls.get(0));
            } else if (!(adMultiMedia == null || adMultiMedia.videoUrls == null || adMultiMedia.videoUrls.size() <= 0)) {
                this.imageCover.setImageURI((String) adMultiMedia.videoUrls.get(0));
            }
            this.imageCover.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AdsItemHolder d;

                public void onClick(View view) {
                    if (z) {
                        a.d().b(advertismentBean.videoPlayStartUrl);
                        LocalVideoPlayActivity.a(this.d.itemView.getContext(), (String) adMultiMedia.videoUrls.get(0), advertismentBean);
                        cn.xiaochuankeji.tieba.ui.post.a.a.a(advertismentBean, (int) PointerIconCompat.TYPE_VERTICAL_TEXT);
                        return;
                    }
                    WebActivity.a(this.d.itemView.getContext(), cn.xiaochuan.jsbridge.b.a(advertismentBean.feedExtraInfo.title, advertismentBean.openDetailUrl));
                    a.d().b(advertismentBean.clickCbURL);
                    cn.xiaochuankeji.tieba.ui.post.a.a.a(advertismentBean, 1006);
                }
            });
            if (adMultiMedia == null || adMultiMedia.duration <= 0) {
                this.adsDur.setVisibility(8);
                return;
            }
            this.adsDur.setVisibility(0);
            this.adsDur.setText(h.a((long) (adMultiMedia.duration * 1000)));
        }
    }

    private void d(final AdvertismentBean advertismentBean) {
        if (advertismentBean.feedExtraInfo == null || TextUtils.isEmpty(advertismentBean.feedExtraInfo.title)) {
            this.adContainer.setVisibility(8);
            return;
        }
        this.adContainer.setVisibility(0);
        this.tvTitle.setText(advertismentBean.feedExtraInfo.title);
        this.tvDescribe.setText(advertismentBean.feedExtraInfo.subTitle);
        if (advertismentBean.feedExtraInfo.FeedExtraType == 0) {
            this.btnDownload.setText("打开");
        } else if (b(advertismentBean.appExtraInfo.apkPackageName)) {
            this.btnDownload.setText("打开");
        } else {
            this.btnDownload.setText("下载");
        }
        OnClickListener anonymousClass3 = new OnClickListener(this) {
            final /* synthetic */ AdsItemHolder b;

            public void onClick(View view) {
                a.d().b(advertismentBean.clickCbURL);
                if (advertismentBean.feedExtraInfo.FeedExtraType == 0) {
                    WebActivity.a(this.b.itemView.getContext(), cn.xiaochuan.jsbridge.b.a(advertismentBean.feedExtraInfo.title, advertismentBean.openDetailUrl));
                    cn.xiaochuankeji.tieba.ui.post.a.a.a(advertismentBean, 1006);
                } else if (this.b.b(advertismentBean.appExtraInfo.apkPackageName)) {
                    this.b.itemView.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(advertismentBean.appExtraInfo.openAppUri)));
                    cn.xiaochuankeji.tieba.ui.post.a.a.a(advertismentBean, (int) Packet.CLIENTVER_FIELD_NUMBER);
                } else {
                    f.a("下载", "确认下载应用" + advertismentBean.appName + "?", (Activity) this.b.b, new f.a(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void a(boolean z) {
                            if (z) {
                                e.a(advertismentBean.appExtraInfo.apkDownloadUrl, new cn.xiaochuankeji.tieba.network.filedownload.a(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    protected void b(com.liulishuo.filedownloader.a aVar, int i, int i2) {
                                        super.b(aVar, i, i2);
                                        this.a.a.b.btnDownload.setText(((int) ((((double) i) * 100.0d) / ((double) i2))) + "%");
                                    }

                                    protected void e(com.liulishuo.filedownloader.a aVar) {
                                        super.e(aVar);
                                        g.b("下载完成");
                                        this.a.a.b.btnDownload.setText("打开");
                                        this.a.a.b.adContainer.setEnabled(true);
                                    }
                                });
                                this.a.b.adContainer.setEnabled(false);
                                cn.xiaochuankeji.tieba.ui.post.a.a.a(advertismentBean, (int) PointerIconCompat.TYPE_CROSSHAIR);
                            }
                        }
                    });
                }
            }
        };
        this.adContainer.setOnClickListener(anonymousClass3);
        this.btnDownload.setOnClickListener(anonymousClass3);
    }

    public void a(final NativeMediaADData nativeMediaADData, final PostAdExtraInfo postAdExtraInfo) {
        int i;
        this.a = nativeMediaADData;
        this.memberView.setVisibility(0);
        this.titleText.setVisibility(8);
        this.memberAvatar.setImageURI(nativeMediaADData.getIconUrl());
        if (postAdExtraInfo == null || TextUtils.isEmpty(postAdExtraInfo.name)) {
            this.memberName.setText("最右广告狗");
        } else {
            this.memberName.setText(postAdExtraInfo.name);
        }
        this.label.setText(postAdExtraInfo.label.length > 0 ? postAdExtraInfo.label[0] : "广告");
        if (nativeMediaADData.getAdPatternType() == 2) {
            i = 1;
        } else {
            i = 0;
        }
        if (i == 0) {
            this.btnPlay.setVisibility(8);
        } else {
            this.btnPlay.setVisibility(0);
        }
        this.imageCover.setImageURI(nativeMediaADData.getImgUrl());
        this.tvTitle.setText(nativeMediaADData.getTitle());
        this.tvDescribe.setText(nativeMediaADData.getDesc());
        this.btnDownload.setWidth(cn.xiaochuankeji.tieba.ui.utils.e.a(50.0f));
        if (nativeMediaADData.isAPP()) {
            switch (nativeMediaADData.getAPPStatus()) {
                case 0:
                    this.btnDownload.setText("下载");
                    break;
                case 1:
                    this.btnDownload.setText("启动");
                    break;
                case 2:
                    this.btnDownload.setText("更新");
                    break;
                case 4:
                    this.btnDownload.setText(nativeMediaADData.getProgress() + "%");
                    break;
                case 8:
                    this.btnDownload.setText("安装");
                    break;
                case 16:
                    this.btnDownload.setText("重新下载");
                    this.btnDownload.setWidth(cn.xiaochuankeji.tieba.ui.utils.e.a(65.0f));
                    break;
                default:
                    this.btnDownload.setText("浏览");
                    break;
            }
        }
        this.btnDownload.setText("浏览");
        nativeMediaADData.onExposured(this.itemView);
        cn.xiaochuankeji.tieba.ui.post.a.a.a().a(1004, nativeMediaADData, postAdExtraInfo);
        this.btnDownload.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AdsItemHolder b;

            public void onClick(View view) {
                nativeMediaADData.onClicked(view);
            }
        });
        this.btnPlay.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AdsItemHolder c;

            public void onClick(View view) {
                if (nativeMediaADData.getAdPatternType() != 2 || !nativeMediaADData.isVideoLoaded()) {
                    return;
                }
                if (nativeMediaADData.isPlaying()) {
                    this.c.mediaView.setVisibility(0);
                    this.c.btnPlay.setVisibility(8);
                    return;
                }
                this.c.mediaView.setVisibility(0);
                this.c.btnPlay.setVisibility(8);
                nativeMediaADData.bindView(this.c.mediaView, true);
                nativeMediaADData.play();
                nativeMediaADData.setMediaListener(new MediaListener(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = r1;
                    }

                    public void onVideoReady(long j) {
                        Log.i("AdsItemHolder", "onVideoReady, video duration = " + j);
                    }

                    public void onVideoStart() {
                        Log.i("AdsItemHolder", "onVideoStart");
                        cn.xiaochuankeji.tieba.ui.post.a.a.a().a(PointerIconCompat.TYPE_VERTICAL_TEXT, nativeMediaADData, postAdExtraInfo);
                    }

                    public void onVideoPause() {
                        Log.i("AdsItemHolder", "onVideoPause");
                        cn.xiaochuankeji.tieba.ui.post.a.a.a().a(PointerIconCompat.TYPE_ALIAS, nativeMediaADData, postAdExtraInfo);
                    }

                    public void onVideoComplete() {
                        Log.i("AdsItemHolder", "onVideoComplete");
                        cn.xiaochuankeji.tieba.ui.post.a.a.a().a(PointerIconCompat.TYPE_ALL_SCROLL, nativeMediaADData, postAdExtraInfo);
                    }

                    public void onVideoError(AdError adError) {
                        Log.i("AdsItemHolder", String.format("onVideoError, errorCode: %d, errorMsg: %s", new Object[]{Integer.valueOf(adError.getErrorCode()), adError.getErrorMsg()}));
                    }

                    public void onReplayButtonClicked() {
                        Log.i("AdsItemHolder", "onReplayButtonClicked");
                        cn.xiaochuankeji.tieba.ui.post.a.a.a().a(1011, nativeMediaADData, postAdExtraInfo);
                    }

                    public void onADButtonClicked() {
                        Log.i("AdsItemHolder", "onADButtonClicked");
                    }

                    public void onFullScreenChanged(boolean z) {
                        Log.i("AdsItemHolder", "onFullScreenChanged, inFullScreen = " + z);
                        if (z) {
                            cn.xiaochuankeji.tieba.ui.post.a.a.a().a(PointerIconCompat.TYPE_NO_DROP, nativeMediaADData, postAdExtraInfo);
                            nativeMediaADData.setVolumeOn(true);
                            this.a.c.btnPlay.setVisibility(0);
                            return;
                        }
                        nativeMediaADData.setVolumeOn(false);
                    }
                });
            }
        });
        this.tediumAds.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AdsItemHolder c;

            public void onClick(View view) {
                if (postAdExtraInfo == null || postAdExtraInfo.filterWords == null || postAdExtraInfo.filterWords.size() == 0) {
                    c.a().d(new b(nativeMediaADData));
                    return;
                }
                k kVar = new k(this.c.itemView.getContext());
                kVar.a(postAdExtraInfo.createTediumReason(), new k.b(this) {
                    final /* synthetic */ AnonymousClass6 a;

                    {
                        this.a = r1;
                    }

                    public void a(ArrayList<String> arrayList, String str) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("type", postAdExtraInfo.source.type);
                        jSONObject.put("appid", postAdExtraInfo.source.appId);
                        jSONObject.put("adslot", postAdExtraInfo.source.slotId);
                        jSONObject.put("title", nativeMediaADData.getTitle());
                        jSONObject.put("description", nativeMediaADData.getDesc());
                        jSONObject.put("image_url", nativeMediaADData.getImgUrl());
                        List arrayList2 = new ArrayList();
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            try {
                                arrayList2.add(Integer.valueOf((String) it.next()));
                            } catch (Exception e) {
                            }
                        }
                        jSONObject.put("reasons", arrayList2);
                        new cn.xiaochuankeji.tieba.api.ad.a().a(jSONObject).g();
                        c.a().d(new b(nativeMediaADData));
                    }
                });
                kVar.show();
            }
        });
    }

    public View a() {
        return this.itemView;
    }

    public NativeMediaADData b() {
        return this.a;
    }

    public void a(String str) {
        this.btnDownload.setText(str);
    }

    private boolean b(String str) {
        try {
            BaseApplication.getAppContext().getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
