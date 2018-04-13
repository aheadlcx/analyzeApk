package qsbk.app.ad.feedsad;

import android.app.Activity;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.ad.feedsad.baiduad.BaiduAd;
import qsbk.app.ad.feedsad.gdtad.GdtAd;
import qsbk.app.ad.feedsad.gdtad.GdtAdItemData;
import qsbk.app.ad.feedsad.gdtad.GdtVideoAd;
import qsbk.app.ad.feedsad.qbad.QbAd;
import qsbk.app.ad.feedsad.qbad.QbAdItem;
import qsbk.app.ad.feedsad.qhad.QhAd;
import qsbk.app.model.NewsAdControl;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.LogUtil;

public class FeedsAd implements IFeedsAdLoaded {
    private static final int AD_QIUSHI = 1;
    private static final int AD_QIUYOUCIRCLE = 2;
    public static int FEEDSAD_SHOW_TIMES = 9999;
    private static final byte[] LOCK = new byte[0];
    private static final String TAG = FeedsAd.class.getSimpleName();
    private static volatile FeedsAd mInstance;
    private static boolean publishGdtVideoAd = true;
    private static volatile FeedsAd sQiuyouCircleFeedsAdInstance;
    private List<IFeedsAdLoaded> mAdLoadedListeners = new ArrayList();
    private boolean mFeedsAdSwitcher = true;
    private int[] mInsertRelativePosition = new int[]{8, 8, 8};
    private int mWhichAd = 1;
    private boolean notifiedAdLoaded = false;
    private List<AdProvider> providers = new ArrayList();
    private Random r = new Random();

    private FeedsAd() {
        FEEDSAD_SHOW_TIMES = FeedsAdUtils.getMaxFeedAdShowTime();
        initialImageLoader();
    }

    public static FeedsAd getInstance() {
        if (mInstance == null) {
            synchronized (FeedsAd.class) {
                if (mInstance == null) {
                    mInstance = new FeedsAd();
                    mInstance.initAdConfig();
                    mInstance.initAdProvider();
                }
            }
        }
        return mInstance;
    }

    public static FeedsAd getQiuyouCircleInstance() {
        if (sQiuyouCircleFeedsAdInstance == null) {
            synchronized (LOCK) {
                if (sQiuyouCircleFeedsAdInstance == null) {
                    sQiuyouCircleFeedsAdInstance = new FeedsAd();
                    sQiuyouCircleFeedsAdInstance.initQiuyouCircleAdConfig();
                    sQiuyouCircleFeedsAdInstance.initCircleAdProvider();
                }
            }
        }
        return sQiuyouCircleFeedsAdInstance;
    }

    private void initAdProvider() {
        JSONObject jSONObject;
        if (QsbkApp.indexConfig == null) {
            jSONObject = new JSONObject();
        } else {
            jSONObject = QsbkApp.indexConfig.optJSONObject("FeedAdConfig");
        }
        if (publishGdtVideoAd) {
            this.providers.add(new GdtVideoAd(jSONObject.optInt("gdt_ad_ratio", 3)));
        }
        this.providers.add(new GdtAd(jSONObject.optInt("gdt_ad_ratio", 3)));
        this.providers.add(new BaiduAd(jSONObject.optInt("baidu_ad_ratio", 0)));
        this.providers.add(new QbAd(jSONObject.optInt("qb_ad_ratio", 0)));
        this.providers.add(new QhAd(jSONObject.optInt("qh_ad_ratio", 0), true));
    }

    private boolean isQiushiAd() {
        return this.mWhichAd == 1;
    }

    private void initCircleAdProvider() {
        JSONObject jSONObject;
        if (QsbkApp.indexConfig == null) {
            jSONObject = new JSONObject();
        } else {
            jSONObject = QsbkApp.indexConfig.optJSONObject("QBFFeedAdConfig");
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        jSONObject = jSONObject.optJSONObject("feedAdRatio");
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (publishGdtVideoAd) {
            this.providers.add(new GdtVideoAd(jSONObject.optInt("gdt_ad_ratio", 3)));
        }
        this.providers.add(new GdtAd(jSONObject.optInt("GDTFeed", 3), GdtAd.GDT_AD_POSITION_ID_CIRCLE));
        this.providers.add(new BaiduAd(jSONObject.optInt("BaiduMobAD", 0)));
        this.providers.add(new QbAd(jSONObject.optInt("banner", 0)));
        this.providers.add(new QhAd(jSONObject.optInt("QhAD", 0), false));
    }

    public void registerListener(IFeedsAdLoaded iFeedsAdLoaded) {
        if (!this.mAdLoadedListeners.contains(iFeedsAdLoaded)) {
            this.mAdLoadedListeners.add(iFeedsAdLoaded);
        }
    }

    public void unRegisterListener(IFeedsAdLoaded iFeedsAdLoaded) {
        this.mAdLoadedListeners.remove(iFeedsAdLoaded);
    }

    public void init(Activity activity) {
        for (AdProvider init : this.providers) {
            init.init(activity, this);
        }
    }

    public void exit() {
        mInstance = null;
    }

    private void initAdConfig() {
        this.mWhichAd = 1;
        if (QsbkApp.indexConfig != null) {
            try {
                JSONObject optJSONObject = QsbkApp.indexConfig.optJSONObject("FeedAdConfig");
                if (optJSONObject.has("isShowAd")) {
                    setFeedsAdSwitcherState(optJSONObject.getBoolean("isShowAd"));
                }
                if (optJSONObject.has("adPosition")) {
                    JSONArray optJSONArray = optJSONObject.optJSONArray("adPosition");
                    int length = optJSONArray.length();
                    this.mInsertRelativePosition = new int[length];
                    for (int i = 0; i < length; i++) {
                        this.mInsertRelativePosition[i] = optJSONArray.getInt(i);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initQiuyouCircleAdConfig() {
        this.mWhichAd = 2;
        if (QsbkApp.indexConfig != null) {
            JSONObject optJSONObject = QsbkApp.indexConfig.optJSONObject("QBFFeedAdConfig");
            if (optJSONObject == null) {
                optJSONObject = new JSONObject();
            }
            if (optJSONObject.has("isShowAd")) {
                setFeedsAdSwitcherState(optJSONObject.optBoolean("isShowAd"));
            }
            if (optJSONObject.has("qbfAdPosition")) {
                JSONArray optJSONArray = optJSONObject.optJSONArray("qbfAdPosition");
                int length = optJSONArray.length();
                this.mInsertRelativePosition = new int[length];
                for (int i = 0; i < length; i++) {
                    this.mInsertRelativePosition[i] = optJSONArray.optInt(i);
                }
            }
        }
    }

    public synchronized void insertFeedAd(int i, ArrayList<Object> arrayList, boolean z) {
        if (!(FeedsAdUtils.isOver(getDateString(), FEEDSAD_SHOW_TIMES) || arrayList.size() == 0 || !getFeedsAdSwitcherState())) {
            Object obj = i < 6 ? 1 : null;
            DebugUtil.debug(TAG, "insertFeedAd  curPosition:" + i + " needAddFixed" + z);
            for (int i2 = 0; i2 < this.mInsertRelativePosition.length; i2++) {
                i += this.mInsertRelativePosition[i2];
                if (i > 0 && !arrayList.isEmpty() && i < arrayList.size() - 1) {
                    if ((arrayList.get(i - 1) instanceof AdItemData) || (arrayList.get(i) instanceof AdItemData)) {
                        LogUtil.d("has item data and break");
                    } else {
                        QbAd qbAd;
                        for (AdProvider adProvider : this.providers) {
                            if (adProvider instanceof QbAd) {
                                qbAd = (QbAd) adProvider;
                                break;
                            }
                        }
                        qbAd = null;
                        if (obj == null || !z || qbAd == null || qbAd.isLoadDataSucc()) {
                            Object topItemWithAd;
                            if (obj != null && z && isQiushiAd()) {
                                for (AdProvider adProvider2 : this.providers) {
                                    if (adProvider2 instanceof QbAd) {
                                        topItemWithAd = ((QbAd) adProvider2).getTopItemWithAd(i2 + 2);
                                        break;
                                    }
                                }
                            }
                            topItemWithAd = null;
                            if (topItemWithAd == null) {
                                try {
                                    topItemWithAd = getAdItemData();
                                    LogUtil.d("get ad item data:" + topItemWithAd);
                                } catch (Exception e) {
                                    LogUtil.d("get ad item data erro:" + e.toString());
                                }
                            }
                            LogUtil.d("add position " + i + ", contains " + arrayList.contains(topItemWithAd));
                            if (!(topItemWithAd == null || arrayList.contains(topItemWithAd))) {
                                arrayList.add(i, topItemWithAd);
                                FeedsAdUtils.addMain_condition(getDateString());
                            }
                        } else {
                            LogUtil.d("load qbad failed " + qbAd);
                        }
                    }
                }
            }
        }
    }

    public synchronized void insertNewsFeedAd(int i, ArrayList<Object> arrayList, NewsAdControl newsAdControl) {
        if (!FeedsAdUtils.isOver(getDateString(), FEEDSAD_SHOW_TIMES) && arrayList.size() != 0 && getFeedsAdSwitcherState() && i < arrayList.size()) {
            int i2 = i == 0 ? newsAdControl.position : newsAdControl.interval + i;
            while (i2 < arrayList.size()) {
                if ((i2 <= 0 || !(arrayList.get(i2 - 1) instanceof AdItemData)) && !(arrayList.get(i2) instanceof AdItemData)) {
                    AdItemData adItemData = getAdItemData(newsAdControl);
                    if (!(adItemData == null || arrayList.contains(adItemData))) {
                        arrayList.add(i2, adItemData);
                        FeedsAdUtils.addMain_condition(getDateString());
                    }
                    i2 += newsAdControl.interval + 1;
                } else {
                    i2 += newsAdControl.interval;
                }
            }
        }
    }

    public synchronized void insertCircleFeedAd(int i, ArrayList<Object> arrayList, boolean z) {
        Exception e;
        if (!(FeedsAdUtils.isOver(getDateString(), FEEDSAD_SHOW_TIMES) || arrayList.size() == 0 || !getFeedsAdSwitcherState())) {
            Object obj;
            if (i < 6) {
                obj = 1;
            } else {
                obj = null;
            }
            DebugUtil.debug(TAG, "insertFeedAd  curPosition:" + i + " needAddFixed" + z);
            for (int i2 = 0; i2 < this.mInsertRelativePosition.length; i2++) {
                i += this.mInsertRelativePosition[i2];
                if (i > 0 && !arrayList.isEmpty() && i < arrayList.size() - 1) {
                    Object adItemData;
                    if (publishGdtVideoAd && QsbkApp.isInVideoList) {
                        try {
                            adItemData = getAdItemData();
                            try {
                                LogUtil.d("get ad item data:" + adItemData);
                            } catch (Exception e2) {
                                e = e2;
                                LogUtil.d("get ad item data erro:" + e.toString());
                                LogUtil.d("add position " + i + ", contains " + arrayList.contains(adItemData));
                                arrayList.add(i, adItemData);
                                FeedsAdUtils.addMain_condition(getDateString());
                            }
                        } catch (Exception e3) {
                            e = e3;
                            adItemData = null;
                            LogUtil.d("get ad item data erro:" + e.toString());
                            LogUtil.d("add position " + i + ", contains " + arrayList.contains(adItemData));
                            arrayList.add(i, adItemData);
                            FeedsAdUtils.addMain_condition(getDateString());
                        }
                        LogUtil.d("add position " + i + ", contains " + arrayList.contains(adItemData));
                        if (!(adItemData == null || arrayList.contains(adItemData))) {
                            arrayList.add(i, adItemData);
                            FeedsAdUtils.addMain_condition(getDateString());
                        }
                    } else if ((arrayList.get(i - 1) instanceof AdItemData) || (arrayList.get(i) instanceof AdItemData)) {
                        LogUtil.d("has item data and break");
                    } else {
                        QbAd qbAd;
                        for (AdProvider adProvider : this.providers) {
                            if (adProvider instanceof QbAd) {
                                qbAd = (QbAd) adProvider;
                                break;
                            }
                        }
                        qbAd = null;
                        if (obj == null || !z || qbAd == null || qbAd.isLoadDataSucc()) {
                            if (obj != null && z && isQiushiAd()) {
                                for (AdProvider adProvider2 : this.providers) {
                                    if (adProvider2 instanceof QbAd) {
                                        adItemData = ((QbAd) adProvider2).getTopItemWithAd(i2 + 2);
                                        break;
                                    }
                                }
                            }
                            adItemData = null;
                            if (adItemData == null) {
                                try {
                                    adItemData = getAdItemData();
                                    LogUtil.d("get ad item data:" + adItemData);
                                } catch (Exception e4) {
                                    LogUtil.d("get ad item data erro:" + e4.toString());
                                }
                            }
                            LogUtil.d("add position " + i + ", contains " + arrayList.contains(adItemData));
                            if (!(adItemData == null || arrayList.contains(adItemData))) {
                                arrayList.add(i, adItemData);
                                FeedsAdUtils.addMain_condition(getDateString());
                            }
                        } else {
                            LogUtil.d("load qbad failed " + qbAd);
                        }
                    }
                }
            }
        }
    }

    public synchronized void insertImmersionGdtVideoAd(int i, ArrayList<Object> arrayList) {
        if (publishGdtVideoAd) {
            if (!(FeedsAdUtils.isOver(getDateString(), FEEDSAD_SHOW_TIMES) || arrayList.size() == 0 || !getFeedsAdSwitcherState())) {
                GdtVideoAd gdtVideoAd;
                DebugUtil.debug(TAG, "insertVideoFeedAd  curPosition:" + i);
                for (AdProvider adProvider : this.providers) {
                    if (adProvider instanceof GdtVideoAd) {
                        gdtVideoAd = (GdtVideoAd) adProvider;
                        break;
                    }
                }
                gdtVideoAd = null;
                if (gdtVideoAd != null) {
                    for (int i2 : this.mInsertRelativePosition) {
                        i += i2;
                        if (i > 0 && !arrayList.isEmpty() && i < arrayList.size() - 1) {
                            AdItemData popVideoAd = gdtVideoAd.popVideoAd();
                            LogUtil.d("add position " + i + ", contains " + arrayList.contains(popVideoAd));
                            if (popVideoAd instanceof GdtAdItemData) {
                                arrayList.add(i, ((GdtAdItemData) popVideoAd).getView().getRef());
                                FeedsAdUtils.addMain_condition(getDateString());
                            }
                        }
                    }
                }
            }
        }
    }

    public void refreshThem() {
        initialImageLoader();
    }

    public boolean getFeedsAdSwitcherState() {
        return this.mFeedsAdSwitcher;
    }

    private void setFeedsAdSwitcherState(boolean z) {
        DebugUtil.debug(TAG, "switcher:" + z);
        this.mFeedsAdSwitcher = z;
    }

    private String getDateString() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    private void initialImageLoader() {
    }

    private AdItemData getAdItemData() {
        int i;
        AdProvider adProvider;
        int i2 = 0;
        if (publishGdtVideoAd && QsbkApp.isInVideoList) {
            for (i = 0; i < this.providers.size(); i++) {
                adProvider = (AdProvider) this.providers.get(i);
                if (adProvider instanceof GdtVideoAd) {
                    GdtVideoAd gdtVideoAd = (GdtVideoAd) adProvider;
                    AdItemData popVideoAd = gdtVideoAd.popVideoAd();
                    if (popVideoAd != null) {
                        return popVideoAd;
                    }
                    AdItemData popAd = gdtVideoAd.popAd();
                    if (popAd != null) {
                        return popAd;
                    }
                }
            }
        }
        List linkedList = new LinkedList();
        List linkedList2 = new LinkedList();
        int i3 = 0;
        for (i = 0; i < this.providers.size(); i++) {
            adProvider = (AdProvider) this.providers.get(i);
            if (!(adProvider instanceof GdtVideoAd)) {
                if (adProvider.getAdCount() > 0 && adProvider.getRatio() > 0) {
                    linkedList.add(adProvider);
                    LogUtil.d("valid providers:" + i + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + adProvider.getClass().toString());
                    i3 += adProvider.getRatio();
                    linkedList2.add(Integer.valueOf(i3));
                    LogUtil.d("add total ratio:" + i3);
                } else if (adProvider.getRatio() > 0) {
                    adProvider.tryFetchAd();
                    LogUtil.d("try fetch ad for provider: " + adProvider.getClass().toString());
                }
            }
        }
        LogUtil.d("provider:" + linkedList.size());
        LogUtil.d("validProviderMax:" + linkedList2.size());
        i = i3 > 0 ? this.r.nextInt(i3) : 0;
        LogUtil.d("random ratio:" + i);
        while (i2 < linkedList.size()) {
            if (i < ((Integer) linkedList2.get(i2)).intValue()) {
                break;
            }
            i2++;
        }
        i2 = -1;
        if (i2 < 0) {
            return null;
        }
        LogUtil.d("index not zero invalid:" + i2);
        return ((AdProvider) linkedList.get(i2)).popAd();
    }

    private AdItemData getAdItemData(NewsAdControl newsAdControl) {
        int i;
        int i2 = 0;
        List linkedList = new LinkedList();
        List linkedList2 = new LinkedList();
        int i3 = 0;
        for (i = 0; i < this.providers.size(); i++) {
            AdProvider adProvider = (AdProvider) this.providers.get(i);
            if (!(adProvider instanceof GdtVideoAd)) {
                int ratio = ((adProvider instanceof GdtAd) && newsAdControl.showGdtAd) ? newsAdControl.isUseQsRatio ? adProvider.getRatio() : newsAdControl.gdtRatio : ((adProvider instanceof QbAd) && newsAdControl.showQbAd) ? newsAdControl.isUseQsRatio ? adProvider.getRatio() : newsAdControl.qbRatio : ((adProvider instanceof QhAd) && newsAdControl.showQhAd) ? newsAdControl.isUseQsRatio ? adProvider.getRatio() : newsAdControl.qhRatio : ((adProvider instanceof BaiduAd) && newsAdControl.showBdAd) ? newsAdControl.isUseQsRatio ? adProvider.getRatio() : newsAdControl.bdRatio : 0;
                if (ratio > 0) {
                    if (adProvider.getAdCount() > 0) {
                        linkedList.add(adProvider);
                        i3 += ratio;
                        linkedList2.add(Integer.valueOf(i3));
                    } else {
                        try {
                            adProvider.tryFetchAd();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        LogUtil.d("provider:" + linkedList.size());
        LogUtil.d("validProviderMax:" + linkedList2.size());
        i = i3 > 0 ? this.r.nextInt(i3) : 0;
        LogUtil.d("random ratio:" + i);
        while (i2 < linkedList.size()) {
            if (i < ((Integer) linkedList2.get(i2)).intValue()) {
                break;
            }
            i2++;
        }
        i2 = -1;
        if (i2 < 0) {
            return null;
        }
        LogUtil.d("index not zero invalid:" + i2);
        return ((AdProvider) linkedList.get(i2)).popAd();
    }

    public void onFeedsAdLoaded() {
        if (!this.notifiedAdLoaded) {
            LogUtil.d("on feeds ad loaded");
            this.notifiedAdLoaded = true;
            for (IFeedsAdLoaded onFeedsAdLoaded : this.mAdLoadedListeners) {
                onFeedsAdLoaded.onFeedsAdLoaded();
            }
        }
    }

    public QbAdItem getTopItemWithAd(int i) {
        for (AdProvider adProvider : this.providers) {
            if (adProvider instanceof QbAd) {
                return ((QbAd) adProvider).getTopItemWithAd(i);
            }
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setAdShowed(qsbk.app.ad.feedsad.AdItemData r5, qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER r6) {
        /*
        r4 = this;
        r1 = 0;
        r0 = qsbk.app.ad.feedsad.a.a;
        r2 = r6.ordinal();
        r0 = r0[r2];
        switch(r0) {
            case 1: goto L_0x0015;
            case 2: goto L_0x002c;
            case 3: goto L_0x0043;
            case 4: goto L_0x005a;
            default: goto L_0x000c;
        };
    L_0x000c:
        r0 = r1;
    L_0x000d:
        if (r0 == 0) goto L_0x0014;
    L_0x000f:
        r0 = (qsbk.app.ad.feedsad.BaseAdProvider) r0;
        r0.adShowed(r5);
    L_0x0014:
        return;
    L_0x0015:
        r0 = r4.providers;
        r2 = r0.iterator();
    L_0x001b:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x000c;
    L_0x0021:
        r0 = r2.next();
        r0 = (qsbk.app.ad.feedsad.AdProvider) r0;
        r3 = r0 instanceof qsbk.app.ad.feedsad.gdtad.GdtAd;
        if (r3 == 0) goto L_0x001b;
    L_0x002b:
        goto L_0x000d;
    L_0x002c:
        r0 = r4.providers;
        r2 = r0.iterator();
    L_0x0032:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x000c;
    L_0x0038:
        r0 = r2.next();
        r0 = (qsbk.app.ad.feedsad.AdProvider) r0;
        r3 = r0 instanceof qsbk.app.ad.feedsad.baiduad.BaiduAd;
        if (r3 == 0) goto L_0x0032;
    L_0x0042:
        goto L_0x000d;
    L_0x0043:
        r0 = r4.providers;
        r2 = r0.iterator();
    L_0x0049:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x000c;
    L_0x004f:
        r0 = r2.next();
        r0 = (qsbk.app.ad.feedsad.AdProvider) r0;
        r3 = r0 instanceof qsbk.app.ad.feedsad.qbad.QbAd;
        if (r3 == 0) goto L_0x0049;
    L_0x0059:
        goto L_0x000d;
    L_0x005a:
        r0 = r4.providers;
        r2 = r0.iterator();
    L_0x0060:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x000c;
    L_0x0066:
        r0 = r2.next();
        r0 = (qsbk.app.ad.feedsad.AdProvider) r0;
        r3 = r0 instanceof qsbk.app.ad.feedsad.qhad.QhAd;
        if (r3 == 0) goto L_0x0060;
    L_0x0070:
        goto L_0x000d;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.ad.feedsad.FeedsAd.setAdShowed(qsbk.app.ad.feedsad.AdItemData, qsbk.app.utils.ReportAdForMedalUtil$AD_PROVIDER):void");
    }
}
