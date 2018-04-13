package cn.v6.sixrooms.room.presenter;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.FansBean;
import cn.v6.sixrooms.bean.GiftListItemBean;
import cn.v6.sixrooms.engine.FansListEngine;
import cn.v6.sixrooms.engine.FansListEngine.CallBack;
import cn.v6.sixrooms.room.dialog.FansDialog;
import cn.v6.sixrooms.room.interfaces.FansViewable;
import cn.v6.sixrooms.utils.LogUtils;
import java.util.ArrayList;
import java.util.List;

public class FansPresenter {
    private static final String a = FansDialog.class.getSimpleName();
    private static volatile FansPresenter b;
    private FansListEngine c;
    private FansViewable d;
    private FirstFansCallBack e;
    private int f = 0;
    private final int g = 0;
    private final int h = 1;
    private final int i = 2;
    private final int j = 3;
    private boolean k = false;
    private boolean l = false;
    private List<FansBean> m = new ArrayList();
    private List<FansBean> n = new ArrayList();
    private List<FansBean> o = new ArrayList();
    private List<FansBean> p = new ArrayList();
    private List<FansBean> q = new ArrayList();
    private List<FansBean> r = new ArrayList();
    private List<GiftListItemBean> s;

    public interface FirstFansCallBack {
        void firstFans(FansBean fansBean);

        void noFans();
    }

    private class a implements CallBack {
        final /* synthetic */ FansPresenter a;

        private a(FansPresenter fansPresenter) {
            this.a = fansPresenter;
        }

        public final void fansList(List<FansBean> list, List<FansBean> list2, List<FansBean> list3) {
            this.a.k = true;
            this.a.o.clear();
            this.a.o.addAll(list3);
            this.a.p.clear();
            this.a.p.addAll(list);
            this.a.q.clear();
            this.a.q.addAll(list2);
            if (this.a.f != 0) {
                this.a.m.clear();
                switch (this.a.f) {
                    case 1:
                        this.a.m.addAll(this.a.o);
                        break;
                    case 2:
                        this.a.m.addAll(this.a.p);
                        break;
                    case 3:
                        this.a.m.addAll(this.a.q);
                        break;
                }
                LogUtils.e(FansPresenter.a, "fansList3");
                this.a.updateFansUI(this.a.m);
            }
        }

        public final void fansList(List<FansBean> list, List<FansBean> list2) {
            if (!(this.a.e == null || list == null)) {
                if (list.size() == 0) {
                    this.a.e.noFans();
                } else {
                    LogUtils.e(FansPresenter.a, "this-firstFans");
                    this.a.e.firstFans((FansBean) list.get(0));
                }
            }
            this.a.n.clear();
            this.a.n.addAll(list);
            this.a.r.clear();
            this.a.r.addAll(list2);
            if (this.a.f == 0) {
                LogUtils.e(FansPresenter.a, "this_tab");
                this.a.m.clear();
                if (this.a.l) {
                    this.a.m.addAll(this.a.r);
                } else {
                    this.a.m.addAll(this.a.n);
                }
                this.a.updateFansUI(this.a.m);
            }
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

    public List<GiftListItemBean> getGiftsBeans() {
        return this.s;
    }

    public List<FansBean> getFansBeans() {
        return this.m;
    }

    public static FansPresenter getInstance() {
        if (b == null) {
            synchronized (FansPresenter.class) {
                if (b == null) {
                    b = new FansPresenter();
                }
            }
        }
        return b;
    }

    public FansPresenter() {
        if (this.c == null) {
            this.c = new FansListEngine(new a());
        }
    }

    public void setFansViewable(FansViewable fansViewable) {
        this.d = fansViewable;
    }

    public void updateNowFans(String str, String str2) {
        this.c.getNowFansList(str, str2);
    }

    public void getSupperSortFansList(String str) {
        this.c.getSupperSortFansList(str);
    }

    public void sendGiftListSocket() {
        if (this.d != null) {
            this.d.sendGiftListSocket();
        }
    }

    public void initGiftList(List<GiftListItemBean> list) {
        if (this.s == null) {
            this.s = new ArrayList();
        }
        this.s.clear();
        this.s.addAll(list);
        updateGiftsUI(this.s);
    }

    public void onDestroy() {
        this.e = null;
        this.d = null;
        b = null;
    }

    public void changeToFansList(int i, String str) {
        if (i == R.id.this_tab) {
            this.f = 0;
        } else if (i == R.id.seven_tab) {
            this.f = 1;
        } else if (i == R.id.thirty_tab) {
            this.f = 2;
        } else if (i == R.id.super_tab) {
            this.f = 3;
        }
        if (this.d != null) {
            this.d.updateSelectedType(this.f);
        }
        if (this.f == 0 || this.k) {
            this.m.clear();
            if (i == R.id.this_tab) {
                this.m.addAll(this.n);
            } else if (i == R.id.seven_tab) {
                this.m.addAll(this.o);
            } else if (i == R.id.thirty_tab) {
                this.m.addAll(this.p);
            } else if (i == R.id.super_tab) {
                this.m.addAll(this.q);
            }
            LogUtils.e(a, "changeToFansList");
            updateFansUI(this.m);
            return;
        }
        getSupperSortFansList(str);
        LogUtils.e(a, "getSupperSortFansList");
    }

    public void changeToFansList(boolean z) {
        this.l = z;
        this.m.clear();
        if (z) {
            this.m.addAll(this.r);
        } else {
            this.m.addAll(this.n);
        }
        updateFansUI(this.m);
    }

    public void updateFansUI(List<FansBean> list) {
        if (this.d != null && list != null) {
            this.d.updateFansView(list);
        }
    }

    public void updateGiftsUI(List<GiftListItemBean> list) {
        if (this.d != null && list != null) {
            this.d.updateGiftsView(list);
        }
    }

    public void setFirstFansCallBack(FirstFansCallBack firstFansCallBack) {
        this.e = firstFansCallBack;
    }
}
