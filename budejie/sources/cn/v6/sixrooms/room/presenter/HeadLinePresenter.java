package cn.v6.sixrooms.room.presenter;

import android.os.Handler;
import cn.v6.sixrooms.room.InitHeadLineBean;
import cn.v6.sixrooms.room.bean.OnHeadlineBean;
import cn.v6.sixrooms.room.dialog.HeadLineDialog;
import cn.v6.sixrooms.room.engine.InitHeadLineEngine;
import cn.v6.sixrooms.room.engine.InitHeadLineEngine.CallBack;
import cn.v6.sixrooms.room.interfaces.HeadLineViewable;
import cn.v6.sixrooms.utils.LogUtils;
import java.util.ArrayList;
import java.util.List;

public class HeadLinePresenter {
    private static final String a = HeadLineDialog.class.getSimpleName();
    private static volatile HeadLinePresenter b;
    private InitHeadLineEngine c;
    private HeadLineViewable d;
    private int e = 0;
    private final int f = 0;
    private final int g = 1;
    private boolean h = false;
    private boolean i = false;
    private List<OnHeadlineBean> j = new ArrayList();
    private List<OnHeadlineBean> k = new ArrayList();
    private List<OnHeadlineBean> l = new ArrayList();
    private long m;
    private Handler n = new c(this);

    private class a implements CallBack {
        final /* synthetic */ HeadLinePresenter a;

        private a(HeadLinePresenter headLinePresenter) {
            this.a = headLinePresenter;
        }

        public final void result(InitHeadLineBean initHeadLineBean) {
            if (this.a.e == 0) {
                this.a.h = true;
            } else if (this.a.e == 1) {
                this.a.i = true;
            }
            this.a.updateTop8Info(initHeadLineBean.getContent(), false, false);
        }

        public final void handleErrorInfo(String str, String str2) {
            if (this.a.d != null) {
                this.a.d.showErrorDialog(str, str2);
            }
        }

        public final void error(int i) {
            if (this.a.d != null) {
                this.a.d.showErrorToast(i);
            }
        }
    }

    public List<OnHeadlineBean> getCommonList() {
        return this.j;
    }

    public boolean ismIsInitLastList() {
        return this.i;
    }

    public boolean ismIsInitThisList() {
        return this.h;
    }

    public HeadLinePresenter() {
        if (this.c == null) {
            this.c = new InitHeadLineEngine(new a());
        }
    }

    public void setHeadLineViewable(HeadLineViewable headLineViewable) {
        this.d = headLineViewable;
    }

    public static HeadLinePresenter getInstance() {
        if (b == null) {
            synchronized (HeadLinePresenter.class) {
                if (b == null) {
                    b = new HeadLinePresenter();
                }
            }
        }
        return b;
    }

    public void getTop8Info(String str, String str2, String str3) {
        switch (Integer.parseInt(str)) {
            case 0:
                this.e = 0;
                break;
            case 1:
                this.e = 1;
                break;
        }
        this.c.getInitHeadLine(str, str2, str3);
    }

    public void initCountDownTime(String str) {
        this.n.removeCallbacksAndMessages(null);
        this.m = Long.parseLong(str);
        a();
    }

    private void a() {
        this.n.sendMessageDelayed(this.n.obtainMessage(), 1000);
    }

    public void onDestroy() {
        this.n.removeCallbacksAndMessages(null);
        this.d = null;
        b = null;
    }

    public void showHeadLineDetail() {
        this.d.dismiss();
        this.d.showHeadLineDetail();
    }

    public void changeToThisList() {
        LogUtils.e(a, "changeToThisList");
        this.j.clear();
        this.j.addAll(this.k);
        this.e = 0;
    }

    public void changeToLastList() {
        LogUtils.e(a, "changeToLastList");
        this.j.clear();
        this.j.addAll(this.l);
        this.e = 1;
    }

    public void updateTop8Info(List<OnHeadlineBean> list, boolean z, boolean z2) {
        if (this.d != null && list != null && list.size() != 0) {
            switch (this.e) {
                case 0:
                    LogUtils.e(a, "update---this");
                    this.k.clear();
                    if (z2) {
                        LogUtils.e(a, "update---135");
                        this.l.clear();
                        this.l.addAll(list);
                    } else {
                        LogUtils.e(a, "update---http/134");
                        this.k.addAll(list);
                    }
                    this.j.clear();
                    this.j.addAll(this.k);
                    break;
                case 1:
                    LogUtils.e(a, "update---last");
                    if (!z) {
                        LogUtils.e(a, "update---http/135");
                        if (z2) {
                            this.k.clear();
                        }
                        this.l.clear();
                        this.l.addAll(list);
                        this.j.clear();
                        this.j.addAll(this.l);
                        break;
                    }
                    LogUtils.e(a, "update---134");
                    this.k.clear();
                    this.k.addAll(list);
                    return;
            }
            this.d.updateTop8View(this.j);
        }
    }
}
