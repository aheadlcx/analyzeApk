package com.baidu.mobad.feeds;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.webkit.WebView;
import com.baidu.mobad.feeds.NativeResponse.MaterialType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdInstanceInfo$CreativeType;
import com.baidu.mobads.interfaces.feeds.IXAdFeedsRequestParameters;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.vo.XAdInstanceInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

public class XAdNativeResponse implements NativeResponse {
    private IXAdInstanceInfo a;
    private BaiduNative b;
    private boolean c = false;
    private IXAdFeedsRequestParameters d;
    private IXAdContainer e;

    public XAdNativeResponse(IXAdInstanceInfo iXAdInstanceInfo, BaiduNative baiduNative, IXAdFeedsRequestParameters iXAdFeedsRequestParameters, IXAdContainer iXAdContainer) {
        this.a = iXAdInstanceInfo;
        this.b = baiduNative;
        this.e = iXAdContainer;
        if (this.a.getActionType() == XAdSDKFoundationFacade.getInstance().getAdConstants().getActTypeDownload()) {
            this.c = true;
        }
        this.d = iXAdFeedsRequestParameters;
    }

    public String getAdLogoUrl() {
        return "https://cpro.baidustatic.com/cpro/ui/noexpire/css/2.1.4/img/mob-adIcon_2x.png";
    }

    public String getBaiduLogoUrl() {
        return "https://cpro.baidustatic.com/cpro/ui/noexpire/css/2.1.4/img/mob-logo_2x.png";
    }

    public String getTitle() {
        return this.a.getTitle();
    }

    public String getDesc() {
        return this.a.getDescription();
    }

    public String getIconUrl() {
        String iconUrl = this.a.getIconUrl();
        if (iconUrl == null || iconUrl.equals("")) {
            return this.a.getMainPictureUrl();
        }
        return iconUrl;
    }

    public String getImageUrl() {
        return this.a.getMainPictureUrl();
    }

    public boolean isDownloadApp() {
        return this.c;
    }

    public void setIsDownloadApp(boolean z) {
        this.c = z;
    }

    public boolean isAdAvailable(Context context) {
        return this.b.isAdAvailable(context, this.a, this.d);
    }

    public long getAppSize() {
        return this.a.getAppSize();
    }

    public String getAppPackage() {
        return this.a.getAppPackageName();
    }

    public List<String> getMultiPicUrls() {
        try {
            JSONArray optJSONArray = this.a.getOriginJsonObject().optJSONArray("morepics");
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return null;
            }
            List<String> arrayList = new ArrayList();
            int i = 0;
            while (i < optJSONArray.length()) {
                try {
                    arrayList.add(optJSONArray.getString(i));
                    i++;
                } catch (Exception e) {
                    return arrayList;
                }
            }
            return arrayList;
        } catch (Exception e2) {
            return null;
        }
    }

    public Map<String, String> getExtras() {
        return null;
    }

    public void recordImpression(View view) {
        this.b.recordImpression(view, this.a, this.d);
    }

    public void handleClick(View view) {
        handleClick(view, -1);
    }

    public void handleClick(View view, int i) {
        a(view, i, this.a);
    }

    private void a(View view, int i, IXAdInstanceInfo iXAdInstanceInfo) {
        if (isDownloadApp()) {
            Context context = view.getContext();
            if (this.d.getAPPConfirmPolicy() == 3) {
                iXAdInstanceInfo.setActionOnlyWifi(false);
                this.b.handleClick(view, iXAdInstanceInfo, i, this.d);
                return;
            } else if (this.d.getAPPConfirmPolicy() == 4) {
                a(context);
                this.b.handleClick(view, iXAdInstanceInfo, i, this.d);
                return;
            } else if (this.d.getAPPConfirmPolicy() == 2) {
                a(view, i);
                return;
            } else if (this.d.getAPPConfirmPolicy() != 1) {
                return;
            } else {
                if (XAdSDKFoundationFacade.getInstance().getSystemUtils().is3GConnected(context).booleanValue()) {
                    a(view, i);
                    return;
                }
                a(context);
                this.b.handleClick(view, iXAdInstanceInfo, i, this.d);
                return;
            }
        }
        this.b.handleClick(view, this.a, i, this.d);
    }

    private void a(Context context) {
        if (XAdSDKFoundationFacade.getInstance().getSystemUtils().is3GConnected(context).booleanValue()) {
            this.a.setActionOnlyWifi(false);
        } else {
            this.a.setActionOnlyWifi(true);
        }
    }

    private void a(final View view, final int i) {
        final Context context = view.getContext();
        Builder builder = new Builder(context);
        builder.setMessage("确认下载\"" + getTitle() + "\"?");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new OnClickListener(this) {
            final /* synthetic */ XAdNativeResponse d;

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                this.d.a(context);
                this.d.b.handleClick(view, this.d.a, i, this.d.d);
            }
        });
        builder.setNegativeButton("取消", new OnClickListener(this) {
            final /* synthetic */ XAdNativeResponse a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    public void onStart(Context context) {
        this.b.handleOnStart(context, this.a, this.d);
    }

    public void onError(Context context, int i, int i2) {
        this.b.handleOnError(context, i, i2, this.a);
    }

    public void onComplete(Context context) {
        this.b.handleOnComplete(context, this.a, this.d);
    }

    public void onClose(Context context, int i) {
        this.b.handleOnClose(context, i, this.a, this.d);
    }

    public void onFullScreen(Context context, int i) {
        this.b.handleOnFullScreen(context, i, this.a, this.d);
    }

    public String getVideoUrl() {
        return this.a.getVideoUrl();
    }

    public int getDuration() {
        return this.a.getVideoDuration();
    }

    public MaterialType getMaterialType() {
        if (this.a.getCreativeType() == IXAdInstanceInfo$CreativeType.VIDEO) {
            return MaterialType.VIDEO;
        }
        if (this.a.getCreativeType() == IXAdInstanceInfo$CreativeType.HTML) {
            return MaterialType.HTML;
        }
        return MaterialType.NORMAL;
    }

    public String getHtmlSnippet() {
        return this.a.getHtmlSnippet();
    }

    public WebView getWebView() {
        return (WebView) this.e.getAdView();
    }

    public void onClickAd(Context context) {
        this.b.handleOnClickAd(context, this.a, this.d);
    }

    public int getMainPicWidth() {
        return this.a.getMainMaterialWidth();
    }

    public int getMainPicHeight() {
        return this.a.getMainMaterialHeight();
    }

    public String getBrandName() {
        return this.a.getAppName();
    }

    protected boolean supportDownloadDirect() {
        return this.a.getAction().equals("video") && this.a.getActionType() == XAdSDKFoundationFacade.getInstance().getAdConstants().getActTypeDownload() && this.a.getCreativeType() == IXAdInstanceInfo$CreativeType.VIDEO;
    }

    protected void handleClickDownloadDirect(View view) {
        if (supportDownloadDirect()) {
            try {
                XAdInstanceInfo xAdInstanceInfo = (XAdInstanceInfo) ((XAdInstanceInfo) this.a).clone();
                xAdInstanceInfo.setAction("");
                a(view, -1, xAdInstanceInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
