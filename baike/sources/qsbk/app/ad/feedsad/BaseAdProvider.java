package qsbk.app.ad.feedsad;

import android.os.SystemClock;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import java.util.LinkedList;
import java.util.List;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.TimeDelta;

public abstract class BaseAdProvider implements AdProvider {
    private static final long DEFAULT_TIME_LIMIT = 1800000;
    private static final long REFRESH_LIMIT = 1000;
    protected SparseBooleanArray adShowSign = new SparseBooleanArray();
    protected SparseArray<Long> adTimeSign = new SparseArray();
    protected List<AdItemData> addedToListAdItems = new LinkedList();
    protected boolean isFetchingAd = false;
    protected List<AdItemData> mAdItems = new LinkedList();
    protected int ratio;
    private TimeDelta timeDelta = null;

    public void tryFetchAd() {
        if (this.isFetchingAd) {
            LogUtil.d("is fetching ad and return:" + this.isFetchingAd);
        } else {
            fetchAd();
        }
    }

    public int getAdCount() {
        return this.mAdItems.size();
    }

    public AdItemData popAd() {
        AdItemData adItemData = null;
        if (this.mAdItems.size() > 0) {
            adItemData = (AdItemData) this.mAdItems.remove(0);
            if (this.mAdItems.size() == 0) {
                tryFetchAd();
            }
        }
        return adItemData;
    }

    protected long getTimeLimit() {
        return 1800000;
    }

    protected boolean isOutOfDateAd(int i) {
        return SystemClock.elapsedRealtime() - ((Long) this.adTimeSign.get(i, Long.valueOf(0))).longValue() > getTimeLimit();
    }

    protected void setAdUnShow(int i) {
        this.adShowSign.put(i, false);
    }

    protected void setAdFetchTime(int i) {
        this.adTimeSign.put(i, Long.valueOf(SystemClock.elapsedRealtime()));
    }

    protected AdItemData popAdAndSigned() {
        AdItemData adItemData;
        if (this.timeDelta != null && this.timeDelta.getDelta() > REFRESH_LIMIT) {
            this.mAdItems.addAll(this.addedToListAdItems);
            this.addedToListAdItems.clear();
            int i = 0;
            while (i < this.mAdItems.size()) {
                adItemData = (AdItemData) this.mAdItems.get(i);
                int hashCode = adItemData.hashCode();
                if (this.adShowSign.get(hashCode, true) || isOutOfDateAd(hashCode)) {
                    this.mAdItems.remove(adItemData);
                    this.adShowSign.delete(hashCode);
                    this.adTimeSign.delete(hashCode);
                    i--;
                }
                i++;
            }
            this.timeDelta.renew();
        } else if (this.timeDelta == null) {
            this.timeDelta = new TimeDelta();
        }
        if (this.mAdItems.size() > 0) {
            adItemData = (AdItemData) this.mAdItems.remove(0);
            if (this.mAdItems.size() == 0) {
                tryFetchAd();
            }
        } else {
            tryFetchAd();
            adItemData = null;
        }
        if (adItemData != null) {
            this.addedToListAdItems.add(adItemData);
        }
        return adItemData;
    }

    public void adShowed(AdItemData adItemData) {
        if (adItemData == null) {
            return;
        }
        if (this.adTimeSign.indexOfKey(adItemData.hashCode()) >= 0) {
            this.adShowSign.put(adItemData.hashCode(), true);
        } else {
            LogUtil.e("test 此广告已经展示过");
        }
    }

    public int getRatio() {
        return this.ratio;
    }
}
