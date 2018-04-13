package qsbk.app.ad.feedsad.qbad;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qiushibaike.statsdk.StatSDK;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.SimpleWebActivity;
import qsbk.app.ad.feedsad.AdItemData;
import qsbk.app.ad.feedsad.FeedsAdUtils;
import qsbk.app.game.GamePlugin;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.CircleArticle;
import qsbk.app.push.PushMessageShow;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.comm.ArrayUtils;
import qsbk.app.utils.json.JSONAble;
import qsbk.app.widget.RatingView;

public class QbAdItem extends JSONAble implements AdItemData {
    public static final int APP_ARG_ERROR = 0;
    public static final int APP_DOWNLOADED = 2;
    public static final int APP_INSTALLED = 3;
    public static final int APP_UNDOWNLOAD = 1;
    private static final String KEY_QB_AD_ACTION_PREFIX = "qb_ad_action_";
    private static final String KEY_QB_AD_LAST_SHOW_PREFIX = "qb_ad_last_show_";
    private static final String KEY_QB_AD_VIEW_PREFIX = "qb_ad_view_";
    public static final String QB_APP = "qbapp";
    public static final String QB_STORE = "qbstore";
    public static final String QB_WEB = "qbweb";
    private static final String[] SUPPORT_TYPES = new String[]{QB_APP, QB_WEB, QB_STORE};
    public static final int TYPE_CHICKEN = 1;
    public static final int TYPE_QB_AD = 0;
    private static final SimpleDateFormat sFormatter = new SimpleDateFormat("yyyyMMdd");
    public String action_args;
    public int action_limit;
    public String action_type;
    public String ad_source;
    public int at_top;
    public String banner;
    public int banner_h;
    public int banner_w;
    public String btn_txt;
    private Integer cacheAdActionCount;
    private Long cacheAdLastShowTimeStamp;
    private Integer cacheAdViewCount;
    public String description;
    public int id;
    public int ratting = 0;
    public int show_interval;
    private int type = 0;
    public String type_icon;
    public String type_icon_night;
    public String user_badge;
    public String user_badge_night;
    public String user_icon;
    public String user_name;
    public int view_limit;

    class a {
        final /* synthetic */ QbAdItem a;
        private SimpleDraweeView b;
        private TextView c;
        public RelativeLayout contentLayout;
        private TextView d;
        private TextView e;
        private TextView f;
        private SimpleDraweeView g;
        private TextView h;
        private ImageView i;

        a(QbAdItem qbAdItem) {
            this.a = qbAdItem;
        }
    }

    class b {
        RelativeLayout a;
        ImageView b;
        TextView c;
        ImageView d;
        ImageView e;
        TextView f;
        ImageView g;
        TextView h;
        RatingView i;
        TextView j;
        ProgressBar k;
        View l;
        TextView m;
        final /* synthetic */ QbAdItem n;

        b(QbAdItem qbAdItem) {
            this.n = qbAdItem;
        }
    }

    public boolean isSupportAdType() {
        return ArrayUtils.find(SUPPORT_TYPES, this.action_type) >= 0;
    }

    public HashMap<String, Object> getQbAdType(String str) {
        HashMap<String, Object> hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("type") == 1) {
                JSONObject optJSONObject = jSONObject.optJSONObject("content");
                if (optJSONObject != null) {
                    hashMap.put("article", CircleArticle.parseAsCircleArticle(optJSONObject));
                }
                hashMap.put("type", Integer.valueOf(1));
                hashMap.put("id", jSONObject.optString("contact"));
                return hashMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        hashMap.put("type", Integer.valueOf(0));
        return hashMap;
    }

    public Pair<String, String> getUrlAndPackageNameFromArsg(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new Pair(jSONObject.optString("url"), jSONObject.optString("package_name"));
        } catch (Exception e) {
            return null;
        }
    }

    public int getApkStatus(Pair<String, String> pair) {
        if (pair == null) {
            return 0;
        }
        LogUtil.d("packageName:" + ((String) pair.second));
        if (QbAdApkDownloader.instance().isPackageInstalled((String) pair.second)) {
            return 3;
        }
        if (QbAdApkDownloader.instance().isDownloadApkExist((String) pair.first)) {
            return 2;
        }
        return 1;
    }

    public String getBtnText() {
        if (QB_APP.equals(this.action_type)) {
            int apkStatus = getApkStatus(getUrlAndPackageNameFromArsg(this.action_args));
            if (apkStatus == 1) {
                return "点击下载";
            }
            if (apkStatus == 2) {
                return "点击安装";
            }
            if (apkStatus == 3) {
                return "点击打开";
            }
        }
        return this.btn_txt;
    }

    private void downloadApp(Context context, Pair<String, String> pair, String str) {
        QbAdApkDownloader.instance().downloadFile(context, (String) pair.first, str);
    }

    private void openApp(Pair<String, String> pair) {
        GamePlugin.openAndroidAppByPackage((String) pair.second);
    }

    private void installApp(Pair<String, String> pair) {
        GamePlugin.installApp(QbAdApkDownloader.instance().getDownloadedFileByUrl((String) pair.first).getAbsolutePath());
    }

    public View getView(LayoutInflater layoutInflater, View view, ViewGroup viewGroup, int i) {
        int i2 = 0;
        HashMap qbAdType = getQbAdType(this.action_args);
        int i3;
        if (((Integer) qbAdType.get("type")).intValue() == 1) {
            a aVar;
            CircleArticle circleArticle = (CircleArticle) qbAdType.get("article");
            if (view == null || !(view.getTag() instanceof a)) {
                view = layoutInflater.inflate(R.layout.qiushi_chicken_ad, null);
                aVar = new a(this);
                aVar.b = (SimpleDraweeView) view.findViewById(R.id.userIcon);
                aVar.c = (TextView) view.findViewById(R.id.userName);
                aVar.d = (TextView) view.findViewById(R.id.type);
                aVar.e = (TextView) view.findViewById(R.id.content);
                aVar.h = (TextView) view.findViewById(R.id.more_video);
                aVar.contentLayout = (RelativeLayout) view.findViewById(R.id.imageLayout);
                view.findViewById(R.id.layerMask).setVisibility(8);
                aVar.g = (SimpleDraweeView) view.findViewById(R.id.image);
                aVar.f = (TextView) view.findViewById(R.id.duration);
                aVar.i = (ImageView) view.findViewById(R.id.play_video);
                view.setTag(view);
            } else {
                aVar = (a) view.getTag();
            }
            aVar.c.setText(this.user_name);
            aVar.d.setText("糗友圈");
            aVar.d.setCompoundDrawablesWithIntrinsicBounds(aVar.h.getContext().getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.qiushi_circle_video_night : R.drawable.qiushi_circle_video), null, null, null);
            aVar.d.setOnClickListener(new g(this));
            aVar.h.setText("查看更多视频");
            aVar.h.setCompoundDrawablesWithIntrinsicBounds(aVar.h.getContext().getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.live_arrow_night : R.drawable.live_arrow), null, null, null);
            aVar.h.setOnClickListener(new h(this, circleArticle));
            FrescoImageloader.displayAvatar(aVar.b, this.user_icon);
            FeedsAdUtils.setImageViewLayoutParam(aVar.g, this.banner_w, this.banner_h);
            FrescoImageloader.displayImage(aVar.g, this.banner, TileBackground.getBackgroud(aVar.g.getContext(), BgImageType.AD));
            aVar.i.setVisibility(0);
            aVar.b.setOnClickListener(new i(this, qbAdType));
            if (circleArticle.video != null) {
                aVar.f.setVisibility(0);
                i3 = circleArticle.video.duration;
                aVar.f.setText(String.format("%d:%02d", new Object[]{Integer.valueOf(i3 / 60), Integer.valueOf(i3 % 60)}));
            } else {
                aVar.f.setVisibility(8);
            }
            if (circleArticle.topic != null || (circleArticle.atInfoTexts != null && circleArticle.atInfoTexts.size() > 0)) {
                CharSequence spannableString = new SpannableString(circleArticle.content);
                if (circleArticle.topic != null) {
                    i3 = circleArticle.content.indexOf(circleArticle.topic.content);
                    if (i3 != -1) {
                        int length = circleArticle.topic.content.length() + i3;
                        spannableString.setSpan(new ForegroundColorSpan(UIHelper.getTopicLinkColor()), i3, length, 33);
                        if (!(aVar.e.getContext() instanceof MainActivity)) {
                            spannableString.setSpan(new j(this, circleArticle), i3, length, 33);
                            aVar.e.setMovementMethod(LinkMovementMethod.getInstance());
                        }
                    }
                }
                if (circleArticle.atInfoTexts != null && circleArticle.atInfoTexts.size() > 0) {
                    while (i2 < circleArticle.atInfoTexts.size()) {
                        AtInfo atInfo = (AtInfo) circleArticle.atInfoTexts.get(i2);
                        try {
                            Matcher matcher = Pattern.compile("@" + atInfo.name).matcher(circleArticle.content);
                            while (matcher.find()) {
                                int start = matcher.start(0);
                                int end = matcher.end(0);
                                spannableString.setSpan(new ForegroundColorSpan(UIHelper.getTopicLinkColor()), start, end, 33);
                                if (!(aVar.e.getContext() instanceof MainActivity)) {
                                    spannableString.setSpan(new k(this, atInfo, circleArticle), start, end, 33);
                                    aVar.e.setMovementMethod(LinkMovementMethod.getInstance());
                                }
                            }
                        } catch (PatternSyntaxException e) {
                        }
                        i2++;
                    }
                }
                aVar.e.setText(spannableString);
            } else {
                aVar.e.setMovementMethod(null);
                aVar.e.setText(circleArticle.content);
            }
            aVar.e.setOnClickListener(new l(this, circleArticle));
            aVar.g.setOnClickListener(new m(this, circleArticle));
            addAdViewCount();
            setAdLastShowTimeStamp();
        } else {
            b bVar;
            if (view == null || !(view.getTag() instanceof b)) {
                view = layoutInflater.inflate(R.layout.feeds_qb_ad, null);
                b bVar2 = new b(this);
                bVar2.a = (RelativeLayout) view.findViewById(R.id.mainLayout);
                bVar2.k = (ProgressBar) view.findViewById(R.id.progress);
                bVar2.g = (ImageView) view.findViewById(R.id.image);
                bVar2.d = (ImageView) view.findViewById(R.id.userBadge);
                bVar2.b = (ImageView) view.findViewById(R.id.userIcon);
                bVar2.f = (TextView) view.findViewById(R.id.content);
                bVar2.c = (TextView) view.findViewById(R.id.userName);
                bVar2.i = (RatingView) view.findViewById(R.id.ratting);
                bVar2.h = (TextView) view.findViewById(R.id.players);
                bVar2.j = (TextView) view.findViewById(R.id.downbt);
                bVar2.e = (ImageView) view.findViewById(R.id.feedsAdSpread);
                bVar2.l = view.findViewById(R.id.divider);
                bVar2.m = (TextView) view.findViewById(R.id.downbt);
                view.setTag(bVar2);
                bVar = bVar2;
            } else {
                bVar = (b) view.getTag();
            }
            if (UIHelper.isNightTheme()) {
                bVar.a.setBackgroundColor(UIHelper.getColor(R.color.main_bg_night));
                bVar.m.setBackgroundColor(UIHelper.getColor(R.color.downbt_background_night));
                bVar.m.setTextColor(UIHelper.getColor(R.color.downbt_text_color_night));
            } else {
                bVar.a.setBackgroundColor(UIHelper.getColor(R.color.white));
                bVar.m.setBackgroundColor(UIHelper.getColor(R.color.downbt_background));
                bVar.m.setTextColor(UIHelper.getColor(R.color.downbt_text_color));
            }
            if (bVar.l != null) {
                View view2 = bVar.l;
                if (i == 0) {
                    i3 = 8;
                } else {
                    i3 = 0;
                }
                view2.setVisibility(i3);
            }
            bVar.k.setTag(this.banner);
            bVar.k.setVisibility(8);
            bVar.g.setTag(bVar.k);
            FeedsAdUtils.setImageViewLayoutParam(bVar.g, this.banner_w, this.banner_h);
            bVar.b.setImageDrawable(null);
            FrescoImageloader.displayAvatar(bVar.b, this.user_icon);
            FrescoImageloader.displayImage(bVar.g, this.banner, TileBackground.getBackgroud(bVar.g.getContext(), BgImageType.AD));
            bVar.c.setText(this.user_name);
            bVar.f.setText(this.description);
            if (TextUtils.isEmpty(this.ad_source)) {
                bVar.h.setVisibility(8);
            } else {
                bVar.h.setVisibility(0);
                bVar.h.setText("此广告由糗事百科提供");
            }
            if (this.ratting > 0) {
                bVar.i.setRating(this.ratting);
                bVar.i.setVisibility(8);
            } else {
                bVar.i.setVisibility(8);
            }
            if (TextUtils.isEmpty(this.type_icon)) {
                bVar.e.setVisibility(8);
            } else {
                bVar.e.setImageDrawable(null);
                if (!UIHelper.isNightTheme() || TextUtils.isEmpty(this.type_icon_night)) {
                    FrescoImageloader.displayImage(bVar.e, this.type_icon);
                } else {
                    FrescoImageloader.displayImage(bVar.e, this.type_icon_night);
                }
                bVar.e.setVisibility(8);
            }
            if (TextUtils.isEmpty(this.user_badge)) {
                bVar.d.setVisibility(8);
            } else {
                bVar.d.setImageDrawable(null);
                if (TextUtils.isEmpty(this.user_badge_night) || !UIHelper.isNightTheme()) {
                    FrescoImageloader.displayImage(bVar.d, this.user_badge);
                } else {
                    FrescoImageloader.displayImage(bVar.d, this.user_badge_night);
                }
                bVar.d.setVisibility(0);
            }
            if (TextUtils.isEmpty(this.btn_txt)) {
                bVar.j.setVisibility(8);
            } else {
                bVar.j.setVisibility(0);
                bVar.j.setText(getBtnText());
            }
            bVar.a.setOnClickListener(new n(this));
            addAdViewCount();
            setAdLastShowTimeStamp();
        }
        return view;
    }

    public void onAppClick(View view) {
        if (QB_APP.equals(this.action_type)) {
            Pair urlAndPackageNameFromArsg = getUrlAndPackageNameFromArsg(this.action_args);
            int apkStatus = getApkStatus(urlAndPackageNameFromArsg);
            if (apkStatus == 1) {
                String str = "";
                try {
                    str = new JSONObject(this.action_args).optString("app_name", "");
                } catch (Exception e) {
                }
                downloadApp(view.getContext(), urlAndPackageNameFromArsg, str);
            } else if (apkStatus == 3) {
                openApp(urlAndPackageNameFromArsg);
            } else if (apkStatus == 2) {
                installApp(urlAndPackageNameFromArsg);
            }
            ReportAdForMedalUtil.report(AD_PROVIDER.QIUBAI, AD_TYPE.QIUSHILIST);
        } else if (QB_WEB.equals(this.action_type)) {
            try {
                JSONObject jSONObject = new JSONObject(this.action_args);
                openQbVideo(view.getContext(), jSONObject.optString("url"), jSONObject.optString("title"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            ReportAdForMedalUtil.report(AD_PROVIDER.QIUBAI, AD_TYPE.QIUSHILIST);
        } else if (QB_STORE.equals(this.action_type)) {
            try {
                openQbStore(view.getContext(), new JSONObject(this.action_args).optString("url"), "" + this.id);
            } catch (Exception e22) {
                e22.printStackTrace();
            }
        }
        addAdActionCount();
    }

    public void openQbStore(Context context, String str, String str2) {
        SimpleWebActivity.launch(context, str, "糗百货");
    }

    public void openQbVideo(Context context, String str, String str2) {
        Intent intent = new Intent(context, PushMessageShow.class);
        intent.putExtra("id", PushMessageShow.setOpenURL(str));
        intent.putExtra("title", str2);
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        context.startActivity(intent);
    }

    public int getAdViewCount() {
        String todayStr = getTodayStr();
        if (this.cacheAdViewCount == null) {
            this.cacheAdViewCount = Integer.valueOf(SharePreferenceUtils.getSharePreferencesIntValue(KEY_QB_AD_VIEW_PREFIX + todayStr + "_" + this.id));
        }
        return this.cacheAdViewCount.intValue();
    }

    public int getAdActionCount() {
        if (this.cacheAdActionCount == null) {
            this.cacheAdActionCount = Integer.valueOf(SharePreferenceUtils.getSharePreferencesIntValue(KEY_QB_AD_ACTION_PREFIX + this.id));
        }
        return this.cacheAdActionCount.intValue();
    }

    public long getAdLastShowTimeStamp() {
        if (this.cacheAdLastShowTimeStamp == null) {
            this.cacheAdLastShowTimeStamp = Long.valueOf(SharePreferenceUtils.getSharePreferencesLongValue(KEY_QB_AD_LAST_SHOW_PREFIX + this.id));
        }
        return this.cacheAdLastShowTimeStamp.longValue();
    }

    public void setAdLastShowTimeStamp() {
        this.cacheAdLastShowTimeStamp = Long.valueOf(System.currentTimeMillis());
        SharePreferenceUtils.setSharePreferencesValue(KEY_QB_AD_LAST_SHOW_PREFIX + this.id, this.cacheAdLastShowTimeStamp.longValue());
    }

    public String getTodayStr() {
        return sFormatter.format(new Date(System.currentTimeMillis()));
    }

    public void addAdViewCount() {
        String todayStr = getTodayStr();
        if (this.cacheAdViewCount == null) {
            this.cacheAdViewCount = Integer.valueOf(SharePreferenceUtils.getSharePreferencesIntValue(KEY_QB_AD_VIEW_PREFIX + todayStr + "_" + this.id));
        }
        this.cacheAdViewCount = Integer.valueOf(this.cacheAdViewCount.intValue() + 1);
        StatSDK.onEvent(QsbkApp.mContext, "qbad_view_" + HttpUtils.getNetwork(QsbkApp.mContext), "" + this.id);
        SharePreferenceUtils.setSharePreferencesValue(KEY_QB_AD_VIEW_PREFIX + todayStr + "_" + this.id, this.cacheAdViewCount.intValue());
    }

    public void addAdActionCount() {
        StatSDK.onEvent(QsbkApp.mContext, "qbad_action_" + HttpUtils.getNetwork(QsbkApp.mContext), "" + this.id);
        if (this.cacheAdActionCount == null) {
            this.cacheAdActionCount = Integer.valueOf(SharePreferenceUtils.getSharePreferencesIntValue(KEY_QB_AD_ACTION_PREFIX + this.id));
        }
        this.cacheAdActionCount = Integer.valueOf(this.cacheAdActionCount.intValue() + 1);
        SharePreferenceUtils.setSharePreferencesValue(KEY_QB_AD_ACTION_PREFIX + this.id, this.cacheAdActionCount.intValue());
    }

    public boolean isQbAdExceedViewLimit() {
        return getAdViewCount() > this.view_limit;
    }

    public boolean isQbAdExceedActionLimit() {
        return getAdActionCount() > this.action_limit;
    }

    public boolean isQbAdInShowInterval() {
        return System.currentTimeMillis() - getAdLastShowTimeStamp() < ((long) (this.show_interval * 1000));
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof QbAdItem) && ((QbAdItem) obj).id == this.id) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.id + "").hashCode();
    }
}
