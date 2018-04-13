package qsbk.app.utils;

import android.app.Activity;
import android.content.Intent;
import android.util.SparseArray;

public class ResultActivityAdaptor {
    private SparseArray<ResultActivityListener> a = new SparseArray();
    private Activity b;
    private int c = 20000;

    public ResultActivityAdaptor(Activity activity) {
        this.b = activity;
    }

    public static boolean isResultActivityAdapterRequest(int i) {
        return i >= 20000 && i < 65536;
    }

    public void addReqeustCodeListener(int i, ResultActivityListener resultActivityListener) {
        this.a.append(i, resultActivityListener);
    }

    public int startActivityForResult(Intent intent, ResultActivityListener resultActivityListener) {
        this.c++;
        if (this.c >= 65536) {
            this.c = (this.c % 65536) + 20000;
        }
        this.a.put(this.c, resultActivityListener);
        this.b.startActivityForResult(intent, this.c);
        return this.c;
    }

    public boolean onResult(int i, int i2, Intent intent) {
        ResultActivityListener resultActivityListener = (ResultActivityListener) this.a.get(i);
        if (resultActivityListener == null) {
            return false;
        }
        resultActivityListener.onResult(i, i2, intent);
        this.a.remove(i);
        return true;
    }
}
