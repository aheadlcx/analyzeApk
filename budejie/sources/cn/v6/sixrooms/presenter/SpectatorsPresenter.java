package cn.v6.sixrooms.presenter;

import android.text.TextUtils;
import android.util.SparseArray;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.engine.RefreshChatListEngine;
import cn.v6.sixrooms.engine.RefreshChatListEngine.CallBack;
import cn.v6.sixrooms.room.presenter.InroomPresenter;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.view.interfaces.SpectatorsViewable;
import cn.v6.sixrooms.view.interfaces.UpdateSpectatorsNumable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SpectatorsPresenter {
    private static final String a = SpectatorsPresenter.class.getSimpleName();
    private static volatile SpectatorsPresenter b;
    private RefreshChatListEngine c;
    private SpectatorsViewable d;
    private WrapUserInfo e;
    private CopyOnWriteArrayList<UpdateSpectatorsNumable> f = new CopyOnWriteArrayList();
    private int g = 0;
    private final int h = 0;
    private final int i = 1;
    private final int j = 2;
    private ArrayList<UserInfoBean> k = new ArrayList();
    private ArrayList<UserInfoBean> l = new ArrayList();
    private ArrayList<UserInfoBean> m = new ArrayList();
    private ArrayList<UserInfoBean> n = new ArrayList();
    private boolean o;
    private SparseArray<CallBack> p = new SparseArray();

    private class a implements CallBack {
        final /* synthetic */ SpectatorsPresenter a;

        private a(SpectatorsPresenter spectatorsPresenter) {
            this.a = spectatorsPresenter;
        }

        public final void resultInfo(WrapUserInfo wrapUserInfo) {
            LogUtils.e(SpectatorsPresenter.a, "http-result");
            this.a.updateSpectator(wrapUserInfo);
            for (int i = 0; i < this.a.p.size(); i++) {
                ((CallBack) this.a.p.valueAt(i)).resultInfo(wrapUserInfo);
            }
        }

        public final void error(int i) {
            LogUtils.e(SpectatorsPresenter.a, "http---updata---error");
            Iterator it = this.a.f.iterator();
            while (it.hasNext()) {
                ((UpdateSpectatorsNumable) it.next()).updateError(i);
            }
            if (this.a.d != null) {
                this.a.d.updateError(i);
                for (int i2 = 0; i2 < this.a.p.size(); i2++) {
                    ((CallBack) this.a.p.valueAt(i2)).error(i);
                }
            }
        }
    }

    public ArrayList<UserInfoBean> getCommonList() {
        return this.k;
    }

    public SpectatorsPresenter() {
        if (this.c == null) {
            this.c = new RefreshChatListEngine(new a());
        }
    }

    public void setIsSuperGirlRoom(boolean z) {
        this.o = z;
    }

    public void setSpectatorsViewable(SpectatorsViewable spectatorsViewable) {
        this.d = spectatorsViewable;
    }

    public static SpectatorsPresenter getInstance() {
        if (b == null) {
            synchronized (SpectatorsPresenter.class) {
                if (b == null) {
                    b = new SpectatorsPresenter();
                }
            }
        }
        return b;
    }

    public void getWrapUserInfo(String str, String str2) {
        this.c.getRoomList(str, str2);
    }

    public boolean getAllRoomList() {
        if (this.n == null || this.n.size() <= 50) {
            return false;
        }
        LogUtils.e(a, "407---send");
        this.d.sendLoadAllRoomList();
        return true;
    }

    public List<UserInfoBean> getChatList() {
        return this.k;
    }

    public void addChatListCallBack(CallBack callBack) {
        if (callBack != null) {
            this.p.put(callBack.hashCode(), callBack);
        }
    }

    public void removeChatListCallBack(CallBack callBack) {
        if (callBack != null) {
            for (int i = 0; i < this.p.size(); i++) {
                int keyAt = this.p.keyAt(i);
                if (callBack.hashCode() == keyAt) {
                    this.p.remove(keyAt);
                }
            }
        }
    }

    public void register(UpdateSpectatorsNumable updateSpectatorsNumable) {
        if (!this.f.contains(updateSpectatorsNumable)) {
            this.f.add(updateSpectatorsNumable);
        }
    }

    public void unregister(UpdateSpectatorsNumable updateSpectatorsNumable) {
        this.f.remove(updateSpectatorsNumable);
    }

    public void onDestroy() {
        this.f.clear();
        this.d = null;
        b = null;
    }

    public void initSpectators(WrapUserInfo wrapUserInfo) {
        this.e = wrapUserInfo;
        this.l.clear();
        this.m.clear();
        this.n.clear();
        this.l.addAll(b());
        this.m.addAll(this.e.getAllAdmList());
        this.n.addAll(a(this.e.getAllList()));
        this.k.addAll(this.l);
        this.g = 0;
        this.d.updateSpectatorsView(this.k, this.o ? wrapUserInfo.getNtvsn() : this.l.size(), this.m.size(), this.e.getNum());
    }

    private ArrayList<UserInfoBean> b() {
        return this.o ? this.e.getFansCardList() : this.e.getSafeList();
    }

    private static ArrayList<UserInfoBean> a(ArrayList<UserInfoBean> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            int size = arrayList.size() - 1;
            String uid = ((UserInfoBean) arrayList.get(size)).getUid();
            if (!TextUtils.isEmpty(uid) && uid.length() > 8 && uid.equals("1000000000")) {
                arrayList.remove(size);
            }
        }
        return arrayList;
    }

    public void openGuard() {
        this.d.dismiss();
        if (LoginUtils.isLogin()) {
            this.d.showOpenGuard();
        } else {
            this.d.showLoginDialog();
        }
    }

    public void changeToGuardList() {
        this.k.clear();
        this.k.addAll(this.l);
        this.g = 0;
    }

    public void changeToManagerList() {
        this.k.clear();
        this.k.addAll(this.m);
        this.g = 1;
    }

    public void changeToSpectatorList() {
        this.k.clear();
        this.k.addAll(this.n);
        this.g = 2;
    }

    public void updateSelectedType() {
        this.d.updateSelectedType(this.g);
    }

    public void updateSpectator(WrapUserInfo wrapUserInfo) {
        if (wrapUserInfo != null) {
            this.e = wrapUserInfo;
            WrapRoomInfo localRoomInfo = InroomPresenter.getInstance().getLocalRoomInfo();
            if (localRoomInfo != null) {
                localRoomInfo.setWrapUserInfo(wrapUserInfo);
            }
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                UpdateSpectatorsNumable updateSpectatorsNumable = (UpdateSpectatorsNumable) it.next();
                updateSpectatorsNumable.updataSpectatorSize(wrapUserInfo.getNum());
                if (this.o) {
                    updateSpectatorsNumable.updataGuardSize(wrapUserInfo.getNtvsn());
                } else if (wrapUserInfo.getSafeList() != null) {
                    updateSpectatorsNumable.updataGuardSize(wrapUserInfo.getSafeList().size());
                }
            }
            if (this.d != null) {
                this.k.clear();
                Collection allAdmList = wrapUserInfo.getAllAdmList();
                if (allAdmList != null) {
                    this.m.clear();
                    this.m.addAll(allAdmList);
                }
                ArrayList allList = wrapUserInfo.getAllList();
                if (allList != null) {
                    this.n.clear();
                    this.n.addAll(a(allList));
                }
                Collection b = b();
                if (b != null) {
                    this.l.clear();
                    this.l.addAll(b);
                }
                if (this.g == 1) {
                    if (allAdmList == null) {
                        this.d.pullToRefreshComplete();
                        return;
                    } else {
                        this.k.addAll(this.m);
                        LogUtils.e(a, "updata---manager");
                    }
                } else if (this.g == 2) {
                    if (allList == null) {
                        this.d.pullToRefreshComplete();
                        return;
                    } else {
                        this.k.addAll(this.n);
                        LogUtils.e(a, "updata---spectator");
                    }
                } else if (this.g == 0) {
                    if (b == null) {
                        this.d.pullToRefreshComplete();
                        return;
                    } else {
                        this.k.addAll(this.l);
                        LogUtils.e(a, "updata---guard");
                    }
                }
                this.d.updateSpectatorsView(this.k, this.o ? wrapUserInfo.getNtvsn() : this.l.size(), this.m.size(), wrapUserInfo.getNum());
            }
        }
    }
}
