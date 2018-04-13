package qsbk.app.provider;

import android.text.TextUtils;
import java.util.LinkedList;
import qsbk.app.QsbkApp;
import qsbk.app.utils.AuditQueue;
import qsbk.app.utils.DataParse.StampContent;
import qsbk.app.utils.HttpUtils;

@Deprecated
public class ContentLoadAndUpdate {
    public static final AuditQueue auditQueue = new AuditQueue();
    private int a = 0;
    private LinkedList<DataUnit> b = new LinkedList();

    public void fillData(int i, DataUnit dataUnit) {
        this.b.addLast(dataUnit);
    }

    public int getIndex() {
        return this.a;
    }

    public void setIndex(int i) {
        this.a = i;
    }

    public int getRemoteDataSize() {
        return this.b.size();
    }

    public DataUnit loadData(int i) {
        if (i < 0 || i > getRemoteDataSize()) {
            return null;
        }
        return (DataUnit) this.b.get(i - 1);
    }

    public DataUnit getCurrentData() {
        return loadData(this.a);
    }

    public boolean updateData(int i, StampContent stampContent) {
        if (i < 0 || i > getRemoteDataSize()) {
            return false;
        }
        ((DataUnit) this.b.get(i - 1)).setEvaluate(stampContent);
        auditQueue.push((DataUnit) this.b.get(i - 1));
        return true;
    }

    public DataUnit loadPrevious() {
        this.a--;
        return loadData(this.a);
    }

    public DataUnit loadNext() {
        this.a++;
        DataUnit loadData = loadData(this.a);
        if (HttpUtils.isWifi(QsbkApp.mContext) || TextUtils.isEmpty(loadData.getImage())) {
            return loadData;
        }
        return loadNext();
    }

    public boolean udapteDataCurrent(StampContent stampContent) {
        return updateData(this.a, stampContent);
    }

    public boolean hasNext() {
        if (this.a + 1 >= getRemoteDataSize()) {
            return false;
        }
        return true;
    }

    public boolean isNeedLoadMore() {
        if (getRemoteDataSize() - this.a < 3) {
            return true;
        }
        return false;
    }

    public boolean hasPrevious() {
        if (this.a <= 1) {
            return false;
        }
        return true;
    }
}
