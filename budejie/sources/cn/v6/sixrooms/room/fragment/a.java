package cn.v6.sixrooms.room.fragment;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import cn.v6.sixrooms.bean.GiftListBean;
import cn.v6.sixrooms.bean.GiftListItemBean;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.presenter.SpectatorsPresenter;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.StarlightCount;
import cn.v6.sixrooms.room.bean.OnHeadlineBeans;
import cn.v6.sixrooms.room.game.MiniGameBean;
import cn.v6.sixrooms.room.game.PigPkYellowDuckBean;
import cn.v6.sixrooms.room.gift.Gift;
import cn.v6.sixrooms.room.gift.InitTopGiftBean;
import cn.v6.sixrooms.room.presenter.FansPresenter;
import cn.v6.sixrooms.room.presenter.HeadLinePresenter;
import cn.v6.sixrooms.room.presenter.InroomPresenter;
import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.utils.LogUtils;
import java.util.ArrayList;
import java.util.List;

final class a extends Handler {
    final /* synthetic */ FullScreenRoomFragment a;

    a(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void handleMessage(Message message) {
        Gift gift;
        switch (message.what) {
            case 1:
                RoommsgBean roommsgBean = (RoommsgBean) message.obj;
                if (!(!"1304".equals(roommsgBean.getTypeID()) || FullScreenRoomFragment.h(this.a) || FullScreenRoomFragment.i(this.a))) {
                    FullScreenRoomFragment.l(this.a).setText(FullScreenRoomFragment.k(this.a).format((long) FullScreenRoomFragment.j(this.a)));
                }
                if (FullScreenRoomFragment.h(this.a) || ((FullScreenRoomFragment.g(this.a) != null && FullScreenRoomFragment.g(this.a).getKeyboardStatus() == 2) || FullScreenRoomFragment.m(this.a) || FullScreenRoomFragment.n(this.a))) {
                    if (FullScreenRoomFragment.o(this.a).size() >= 200) {
                        FullScreenRoomFragment.o(this.a).remove(0);
                    }
                    FullScreenRoomFragment.o(this.a).add(roommsgBean);
                    return;
                }
                this.a.refreshPublicAdapter(roommsgBean);
                return;
            case 6:
                FullScreenRoomFragment.f(this.a).isCanSpeak = true;
                return;
            case 11:
                LogUtils.e("FansDialog", "handler-11");
                FansPresenter.getInstance().updateNowFans(FullScreenRoomFragment.p(this.a).getRoominfoBean().getId(), (String) message.obj);
                return;
            case 15:
                if (FullScreenRoomFragment.g(this.a) != null && FullScreenRoomFragment.g(this.a).isShowing()) {
                    FullScreenRoomFragment.g(this.a).updateState();
                    return;
                }
                return;
            case 16:
                FullScreenRoomFragment.f(this.a).mSpeakState = message.arg1;
                if (FullScreenRoomFragment.g(this.a) != null && FullScreenRoomFragment.g(this.a).isShowing()) {
                    FullScreenRoomFragment.g(this.a).updateState();
                    return;
                }
                return;
            case 17:
                this.a.refreshChat();
                return;
            case 18:
                if (FullScreenRoomFragment.e(this.a) != null) {
                    FullScreenRoomFragment.e(this.a).updataMenuList(this.a.menuListBean);
                    return;
                }
                return;
            case 19:
                if (FullScreenRoomFragment.e(this.a) != null) {
                    FullScreenRoomFragment.e(this.a).upDataQueueList(this.a.queueListBean);
                    return;
                }
                return;
            case 135:
                LogUtils.e("HeadLineDialog", "135");
                OnHeadlineBeans onHeadlineBeans = (OnHeadlineBeans) message.obj;
                HeadLinePresenter.getInstance().initCountDownTime(onHeadlineBeans.getCountdown());
                if (FullScreenRoomFragment.t(this.a) != null) {
                    HeadLinePresenter.getInstance().updateTop8Info(onHeadlineBeans.getTop8(), false, true);
                    return;
                }
                return;
            case 201:
                if (FullScreenRoomFragment.q(this.a) != null && FansPresenter.getInstance().getGiftsBeans() != null && this.a.a != null) {
                    gift = (Gift) message.obj;
                    for (Gift id : this.a.a) {
                        if (gift.getId().equals(id.getId())) {
                            GiftListItemBean giftListItemBean = new GiftListItemBean();
                            giftListItemBean.setUname(gift.getFrom());
                            giftListItemBean.setItem(gift.getId());
                            giftListItemBean.setNum(gift.getNum());
                            giftListItemBean.setPicUrl(gift.getSpic().getImg());
                            List giftsBeans = FansPresenter.getInstance().getGiftsBeans();
                            giftsBeans.add(0, giftListItemBean);
                            if (giftsBeans.size() > 50) {
                                giftsBeans.remove(giftsBeans.size() - 1);
                            }
                            FullScreenRoomFragment.q(this.a).updateGiftsView(giftsBeans);
                            return;
                        }
                    }
                    return;
                }
                return;
            case 407:
                WrapUserInfo wrapUserInfo = (WrapUserInfo) message.obj;
                if (FullScreenRoomFragment.r(this.a) != null) {
                    LogUtils.e(FullScreenRoomFragment.a(), "407---receive");
                    this.a.setSpectatorNum(wrapUserInfo.getNum());
                    SpectatorsPresenter.getInstance().updateSpectator(wrapUserInfo);
                    return;
                }
                return;
            case 413:
                if (InroomPresenter.getInstance().getLocalRoomInfo() != null) {
                    LogUtils.e(FullScreenRoomFragment.a(), "413---receive");
                    if (!SpectatorsPresenter.getInstance().getAllRoomList() && FullScreenRoomFragment.p(this.a) != null && FullScreenRoomFragment.p(this.a).getRoominfoBean() != null) {
                        LogUtils.e(FullScreenRoomFragment.a(), "http---send");
                        SpectatorsPresenter.getInstance().getWrapUserInfo(FullScreenRoomFragment.p(this.a).getRoominfoBean().getId(), FullScreenRoomFragment.f(this.a).getLastUpadateTime());
                        return;
                    }
                    return;
                }
                return;
            case 701:
                if (FullScreenRoomFragment.q(this.a) != null && this.a.a != null) {
                    List content = ((GiftListBean) message.obj).getContent();
                    List arrayList = new ArrayList();
                    for (int size = content.size() - 1; size >= 0; size--) {
                        for (Gift gift2 : this.a.a) {
                            if (((GiftListItemBean) content.get(size)).getItem().equals(gift2.getId())) {
                                ((GiftListItemBean) content.get(size)).setPicUrl(gift2.getSpic().getImg());
                                arrayList.add(content.get(size));
                            }
                        }
                    }
                    FansPresenter.getInstance().initGiftList(arrayList);
                    return;
                }
                return;
            case SocketUtil.TYPEID_1501 /*1501*/:
                if (FullScreenRoomFragment.a(this.a)) {
                    FullScreenRoomFragment.a(this.a, (MiniGameBean) message.obj);
                    if (FullScreenRoomFragment.b(this.a) != null) {
                        LogUtils.e("MiniGame", "TYPEID_1501" + FullScreenRoomFragment.b(this.a).getUrl());
                        FullScreenRoomFragment.a(this.a, true);
                        FullScreenRoomFragment.c(this.a).initLoadUrl(FullScreenRoomFragment.b(this.a).getUrl());
                        return;
                    }
                    return;
                }
                return;
            case SocketUtil.TYPEID_1502 /*1502*/:
                if (FullScreenRoomFragment.a(this.a)) {
                    LogUtils.e("MiniGame", "TYPEID_1502");
                    FullScreenRoomFragment.d(this.a).setVisibility(8);
                    FullScreenRoomFragment.a(this.a, null);
                    FullScreenRoomFragment.a(this.a, false);
                    return;
                }
                return;
            case SocketUtil.TYPEID_1503 /*1503*/:
                if (FullScreenRoomFragment.a(this.a)) {
                    String str = (String) message.obj;
                    if (!TextUtils.isEmpty(str)) {
                        LogUtils.e("MiniGame", "TYPEID_1503");
                        FullScreenRoomFragment.c(this.a).loadUrl("javascript:SetMiniGame('" + str + "')");
                        return;
                    }
                    return;
                }
                return;
            case 1514:
                FullScreenRoomFragment.a(this.a, (PigPkYellowDuckBean) message.obj);
                return;
            case BaseRoomActivity.HEAD_LINE /*4081*/:
                FullScreenRoomFragment.a(this.a, (InitTopGiftBean) message.obj);
                LogUtils.e("HeadLineDialog", "408:" + FullScreenRoomFragment.s(this.a).getCountdown());
                HeadLinePresenter.getInstance().initCountDownTime(FullScreenRoomFragment.s(this.a).getCountdown());
                return;
            case BaseRoomActivity.MINI_GAME /*4082*/:
                if (FullScreenRoomFragment.a(this.a)) {
                    FullScreenRoomFragment.a(this.a, (MiniGameBean) message.obj);
                    if (FullScreenRoomFragment.b(this.a) != null) {
                        LogUtils.e("MiniGame", "408-404" + FullScreenRoomFragment.b(this.a).getUrl());
                        FullScreenRoomFragment.a(this.a, true);
                        FullScreenRoomFragment.c(this.a).initLoadUrl(FullScreenRoomFragment.b(this.a).getUrl());
                        return;
                    }
                    return;
                }
                return;
            case SocketUtil.TYPEID_201002 /*201002*/:
                FullScreenRoomFragment.a(this.a, (StarlightCount) message.obj);
                return;
            default:
                return;
        }
    }
}
