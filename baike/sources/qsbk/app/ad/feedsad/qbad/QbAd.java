package qsbk.app.ad.feedsad.qbad;

import android.app.Activity;
import android.content.Context;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import qsbk.app.ConfigManager;
import qsbk.app.Constants;
import qsbk.app.ad.feedsad.AdItemData;
import qsbk.app.ad.feedsad.BaseAdProvider;
import qsbk.app.ad.feedsad.IFeedsAdLoaded;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.utils.LogUtil;

public class QbAd extends BaseAdProvider {
    public static final int FIX_FIRST = 2;
    public static final int FIX_SECOND = 3;
    public static final int FIX_TOP = 1;
    public static final String QB_ADS_KEY = "qb_ads";
    private List<QbAdItem> gAllQbAds = new LinkedList();
    private List<QbAdItem> gValidNormalAds = new LinkedList();
    private List<QbAdItem> gValidTopAds = new LinkedList();
    private long lastFetchTime = 0;
    private long lastFindValid = 0;
    private boolean loadDataSucc = false;
    @Deprecated
    private Context mContext;
    private IFeedsAdLoaded mListener;
    protected int ratio;

    public QbAd(int i) {
        this.ratio = i;
    }

    private static boolean isValid2Show(QbAdItem qbAdItem) {
        return (qbAdItem == null || !qbAdItem.isSupportAdType() || qbAdItem.isQbAdExceedActionLimit() || qbAdItem.isQbAdExceedViewLimit() || qbAdItem.isQbAdInShowInterval()) ? false : true;
    }

    public void exit() {
    }

    public void init(Activity activity, IFeedsAdLoaded iFeedsAdLoaded) {
        this.mContext = activity;
        this.mListener = iFeedsAdLoaded;
        LogUtil.d("init qbad");
        tryFetchAd();
    }

    public boolean isLoadDataSucc() {
        return this.loadDataSucc;
    }

    public void fetchAd() {
        String str = Constants.QBBANNER_URL;
        if (ConfigManager.getInstance().isInTestMode()) {
            str = str + "?is_publish=0";
        }
        this.isFetchingAd = true;
        new a(this).execute(str);
    }

    public boolean fetchAdInLessThan5Min() {
        return System.currentTimeMillis() - this.lastFetchTime < ListViewHelper.DEFAULT_TIPS_TO_REFRESH_INTERVAL;
    }

    public void tryFetchAd() {
        if (!this.isFetchingAd) {
            if (!fetchAdInLessThan5Min() || ConfigManager.getInstance().isInTestMode()) {
                fetchAd();
            }
        }
    }

    public boolean findValidAdsAndTopAds() {
        LogUtil.d("find valid ads and top ads:");
        List<QbAdItem> linkedList = new LinkedList();
        List<QbAdItem> linkedList2 = new LinkedList();
        synchronized (this.gAllQbAds) {
            for (int i = 0; i < this.gAllQbAds.size(); i++) {
                List list;
                QbAdItem qbAdItem = (QbAdItem) this.gAllQbAds.get(i);
                if (qbAdItem.at_top > 0) {
                    list = linkedList;
                } else {
                    list = linkedList2;
                }
                if (isValid2Show(qbAdItem)) {
                    list.add(qbAdItem);
                }
            }
        }
        LogUtil.d("validNormal:" + linkedList2.size() + " local:" + this.gValidNormalAds.size());
        LogUtil.d("validTopAds:" + linkedList.size() + " local:" + this.gValidTopAds.size());
        if (linkedList2.size() > 0) {
            synchronized (this.gValidNormalAds) {
                for (QbAdItem qbAdItem2 : linkedList2) {
                    if (!this.gValidNormalAds.contains(qbAdItem2)) {
                        this.gValidNormalAds.add(qbAdItem2);
                    }
                }
            }
        }
        if (linkedList.size() > 0) {
            synchronized (this.gValidTopAds) {
                for (QbAdItem qbAdItem22 : linkedList) {
                    if (!this.gValidTopAds.contains(qbAdItem22)) {
                        this.gValidTopAds.add(qbAdItem22);
                    }
                }
            }
        }
        return linkedList2.size() == 0;
    }

    public int getAdCount() {
        return this.gValidNormalAds.size();
    }

    private void limitFindValidAds() {
        if (System.currentTimeMillis() - this.lastFindValid > 10000) {
            boolean findValidAdsAndTopAds = findValidAdsAndTopAds();
            this.lastFindValid = System.currentTimeMillis();
            if (findValidAdsAndTopAds) {
                tryFetchAd();
            }
        }
    }

    public AdItemData popAd() {
        AdItemData adItemData = null;
        LogUtil.d("pop ads");
        synchronized (this.gValidNormalAds) {
            if (this.gValidNormalAds.size() > 0) {
                adItemData = (QbAdItem) this.gValidNormalAds.remove(0);
                if (isValid2Show(adItemData)) {
                    this.gValidNormalAds.add(adItemData);
                }
                limitFindValidAds();
            }
        }
        return adItemData;
    }

    public int getRatio() {
        return this.ratio;
    }

    public void setRatio(int i) {
        this.ratio = i;
    }

    public QbAdItem getValidTopQbAdItem() {
        if (this.gValidTopAds.size() > 0) {
            return (QbAdItem) this.gValidTopAds.remove(0);
        }
        return null;
    }

    public QbAdItem getTopItemWithAd(int i) {
        QbAdItem qbAdItem;
        Collection arrayList = new ArrayList();
        synchronized (this.gValidTopAds) {
            int i2 = 0;
            qbAdItem = null;
            while (i2 < this.gValidTopAds.size()) {
                qbAdItem = (QbAdItem) this.gValidTopAds.get(i2);
                if (qbAdItem.at_top == i) {
                    this.gValidTopAds.remove(qbAdItem);
                    i2--;
                    if (isValid2Show(qbAdItem)) {
                        arrayList.add(qbAdItem);
                        break;
                    }
                }
                i2++;
            }
            this.gValidTopAds.addAll(arrayList);
        }
        if (this.gValidTopAds.size() == 0) {
            LogUtil.d("limitFindValidAds");
            limitFindValidAds();
        }
        if (!(qbAdItem == null || qbAdItem.at_top == i)) {
            qbAdItem = null;
        }
        LogUtil.d("get top item with ad:" + i + " item:" + qbAdItem);
        return qbAdItem;
    }

    public String toString() {
        return "QbAd{loadDataSucc=" + this.loadDataSucc + ", lastFetchTime=" + this.lastFetchTime + ", lastFindValid=" + this.lastFindValid + ", ratio=" + this.ratio + '}';
    }
}
