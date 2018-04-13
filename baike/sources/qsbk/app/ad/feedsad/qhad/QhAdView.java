package qsbk.app.ad.feedsad.qhad;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.ak.android.engine.nav.NativeAd;
import com.ak.android.engine.navbase.NativeAdListener;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;
import qsbk.app.R;
import qsbk.app.ad.feedsad.FeedsAdStat;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.UIHelper;

public class QhAdView implements NativeAdListener {
    private String mBtnText;
    private Context mContext;
    private String mDesc;
    private int mImageHeight;
    private String mImageUrl;
    private int mImageWidth;
    private LayoutInflater mInflater;
    private String mLogoUrl;
    private int mMaxHeight;
    private NativeAd mNativeAd;
    private int mRequestWidth;
    private double mStarLevel;
    private int mStaus = 5;
    private String mTitle;

    class a {
        RelativeLayout a;
        ImageView b;
        TextView c;
        TextView d;
        ImageView e;
        TextView f;
        TextView g;
        ProgressBar h;
        TextView i;
        final /* synthetic */ QhAdView j;

        a(QhAdView qhAdView) {
            this.j = qhAdView;
        }
    }

    public QhAdView(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void onAlertChange(int i) {
    }

    public void onLandingPageChange(int i) {
    }

    public void onLeftApplication() {
    }

    public NativeAd getmNativeAd() {
        return this.mNativeAd;
    }

    public void setmNativeAd(NativeAd nativeAd) {
        this.mNativeAd = nativeAd;
    }

    public void init(NativeAd nativeAd) {
        this.mNativeAd = nativeAd;
        this.mNativeAd.setAdListener(this);
        JSONObject content = this.mNativeAd.getContent();
        this.mTitle = content.optString("title");
        this.mDesc = content.optString(SocialConstants.PARAM_APP_DESC);
        this.mImageUrl = content.optString("contentimg");
        this.mLogoUrl = content.optString("logo");
        this.mBtnText = content.optString("btntext");
        initAdSize();
    }

    private void initAdSize() {
        Resources resources = this.mContext.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        this.mRequestWidth = displayMetrics.widthPixels - (resources.getDimensionPixelSize(R.dimen.profile_item_margin) * 2);
        this.mMaxHeight = (int) (((double) displayMetrics.heightPixels) * 1.5d);
        this.mImageWidth = this.mRequestWidth;
        this.mImageHeight = (int) (((float) this.mImageWidth) / 1.6f);
    }

    @TargetApi(11)
    private void setImageViewLayoutParam(ImageView imageView) {
        if (imageView != null) {
            int[] iArr = new int[2];
            caculateImageSize(iArr, this.mImageWidth, this.mImageHeight);
            imageView.setLayoutParams(new LayoutParams(iArr[0], iArr[1]));
        }
    }

    private void caculateImageSize(int[] iArr, int i, int i2) {
        if (iArr == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        int i3 = (int) ((((float) this.mRequestWidth) / ((float) i)) * ((float) i2));
        if (this.mMaxHeight != -1 && i3 > this.mMaxHeight) {
            i3 = this.mMaxHeight;
        }
        iArr[0] = this.mRequestWidth;
        iArr[1] = i3;
    }

    public View getView(View view) {
        a aVar;
        DebugUtil.debug("FeedsAd", "getView");
        if (view == null || !(view.getTag() instanceof a)) {
            a aVar2 = new a(this);
            view = this.mInflater.inflate(R.layout.feeds_qh, null);
            aVar2.e = (ImageView) view.findViewById(R.id.image);
            aVar2.d = (TextView) view.findViewById(R.id.content);
            aVar2.g = (TextView) view.findViewById(R.id.downbt);
            aVar2.b = (ImageView) view.findViewById(R.id.userIcon);
            aVar2.f = (TextView) view.findViewById(R.id.players);
            aVar2.c = (TextView) view.findViewById(R.id.userName);
            aVar2.a = (RelativeLayout) view.findViewById(R.id.mainLayout);
            aVar2.h = (ProgressBar) view.findViewById(R.id.progress);
            aVar2.i = (TextView) view.findViewById(R.id.downbt);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        if (!(aVar == null || this.mContext == null)) {
            if (UIHelper.isNightTheme()) {
                aVar.a.setBackgroundColor(UIHelper.getColor(R.color.main_bg_night));
                aVar.i.setBackgroundColor(UIHelper.getColor(R.color.downbt_background_night));
                aVar.i.setTextColor(UIHelper.getColor(R.color.downbt_text_color_night));
            } else {
                aVar.a.setBackgroundColor(UIHelper.getColor(R.color.white));
                aVar.i.setBackgroundColor(UIHelper.getColor(R.color.downbt_background));
                aVar.i.setTextColor(UIHelper.getColor(R.color.downbt_text_color));
            }
        }
        aVar.d.setText(this.mDesc);
        aVar.g.setText(this.mBtnText);
        aVar.c.setText(this.mTitle);
        aVar.f.setText("此广告由360提供");
        aVar.h.setTag(this.mImageUrl);
        aVar.h.setVisibility(8);
        aVar.e.setTag(aVar.h);
        FrescoImageloader.displayAvatar(aVar.b, this.mLogoUrl);
        FrescoImageloader.displayImage(aVar.e, this.mImageUrl, TileBackground.getBackgroud(aVar.e.getContext(), BgImageType.AD));
        setImageViewLayoutParam(aVar.e);
        aVar.a.setOnClickListener(new a(this));
        this.mNativeAd.onAdShowed(view);
        FeedsAdStat.stat(view, "qh");
        return view;
    }
}
