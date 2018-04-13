package qsbk.app.live;

import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.utils.PreferenceUtils;

public class DataHelper {
    private static DataHelper a;
    private long b = PreferenceUtils.instance().getLong("balance", -1);
    private long c = PreferenceUtils.instance().getLong("certificate", -1);

    private DataHelper() {
    }

    public static DataHelper getInstance() {
        if (a == null) {
            a = new DataHelper();
        }
        return a;
    }

    public long getBalance() {
        return this.b > 0 ? this.b : 0;
    }

    public void setBalance(long j) {
        this.b = j;
        PreferenceUtils.instance().putLong("balance", j);
    }

    public long getCertificate() {
        return this.c > 0 ? this.c : 0;
    }

    public void setCertificate(long j) {
        this.c = j;
        PreferenceUtils.instance().putLong("certificate", this.b);
    }

    public void clearAccount() {
        LogUtils.d("push", "clear account and push data");
        setBalance(0);
        setCertificate(0);
    }
}
