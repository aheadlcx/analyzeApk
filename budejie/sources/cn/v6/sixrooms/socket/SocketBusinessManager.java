package cn.v6.sixrooms.socket;

import android.text.TextUtils;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.bean.ActivityBean;
import cn.v6.sixrooms.bean.AnchorPrompt;
import cn.v6.sixrooms.bean.AuthKeyBean;
import cn.v6.sixrooms.bean.BroadcastBean;
import cn.v6.sixrooms.bean.ChangzhanBeginBean;
import cn.v6.sixrooms.bean.ChangzhanFinalUsersBean;
import cn.v6.sixrooms.bean.ChangzhanFinishBean;
import cn.v6.sixrooms.bean.ChangzhanStatusBean;
import cn.v6.sixrooms.bean.ChangzhanTimeBean;
import cn.v6.sixrooms.bean.ChatPermissionBean;
import cn.v6.sixrooms.bean.ErrorBean;
import cn.v6.sixrooms.bean.FansListTmBean;
import cn.v6.sixrooms.bean.FireworkFailBean;
import cn.v6.sixrooms.bean.FireworkSuccessBean;
import cn.v6.sixrooms.bean.FlagBean;
import cn.v6.sixrooms.bean.FlyTextBean;
import cn.v6.sixrooms.bean.FreeVoteMsgBean;
import cn.v6.sixrooms.bean.GiftBean;
import cn.v6.sixrooms.bean.GiftListBean;
import cn.v6.sixrooms.bean.GuardStausBean;
import cn.v6.sixrooms.bean.LiveBroadcastBean;
import cn.v6.sixrooms.bean.LiveMessage;
import cn.v6.sixrooms.bean.LiveStateBean;
import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.bean.MiniGameIdBean;
import cn.v6.sixrooms.bean.NoticeTmBean;
import cn.v6.sixrooms.bean.PrivateChatBean;
import cn.v6.sixrooms.bean.PublicChatBean;
import cn.v6.sixrooms.bean.RoomUpgradeMsg;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.bean.SocketRoomMessageSofaBean;
import cn.v6.sixrooms.bean.SongLiveListBean;
import cn.v6.sixrooms.bean.SubRedBean;
import cn.v6.sixrooms.bean.SysNotificationBean;
import cn.v6.sixrooms.bean.UpdateCoinWealthBean;
import cn.v6.sixrooms.bean.UpdateGiftNumBean;
import cn.v6.sixrooms.bean.UserListTmBean;
import cn.v6.sixrooms.bean.VoteBean;
import cn.v6.sixrooms.bean.WelcomeBean;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.constants.GiftIdStrs;
import cn.v6.sixrooms.listener.RedPackgeLisener;
import cn.v6.sixrooms.room.BecomeGodBean;
import cn.v6.sixrooms.room.InitHeadLineBean;
import cn.v6.sixrooms.room.PublicNoticeBean;
import cn.v6.sixrooms.room.SmallFlyTextBean;
import cn.v6.sixrooms.room.StarlightCount;
import cn.v6.sixrooms.room.SuperFireworksBean;
import cn.v6.sixrooms.room.bean.OnHeadlineBeans;
import cn.v6.sixrooms.room.game.GameLuckIndianaInitBean;
import cn.v6.sixrooms.room.game.GameLuckIndianaResultBean;
import cn.v6.sixrooms.room.game.GamePlaneEndBean;
import cn.v6.sixrooms.room.game.GamePlaneStartBean;
import cn.v6.sixrooms.room.game.MiniGameBean;
import cn.v6.sixrooms.room.game.PigPkYellowDuckBean;
import cn.v6.sixrooms.room.gift.BoxingBean;
import cn.v6.sixrooms.room.gift.BoxingBoxerVotes;
import cn.v6.sixrooms.room.gift.BoxingCloseBean;
import cn.v6.sixrooms.room.gift.BoxingListener;
import cn.v6.sixrooms.room.gift.BoxingVoteBean;
import cn.v6.sixrooms.room.gift.BoxingWinVictoryBean;
import cn.v6.sixrooms.room.gift.BoxingWinningBean;
import cn.v6.sixrooms.room.gift.Gift;
import cn.v6.sixrooms.room.gift.HeadLineListener;
import cn.v6.sixrooms.room.gift.InitTopGiftBean;
import cn.v6.sixrooms.room.gift.MiniGameListener;
import cn.v6.sixrooms.room.gift.MiniGameLiveListener;
import cn.v6.sixrooms.room.gift.ReadGiftEngine;
import cn.v6.sixrooms.socket.beanmanager.ActivityBeanManager;
import cn.v6.sixrooms.socket.beanmanager.AnchorPromptManager;
import cn.v6.sixrooms.socket.beanmanager.AuthKeyBeanManager;
import cn.v6.sixrooms.socket.beanmanager.ChangzhanBeginBeanManager;
import cn.v6.sixrooms.socket.beanmanager.ChangzhanFinalManager;
import cn.v6.sixrooms.socket.beanmanager.ChangzhanFinishManager;
import cn.v6.sixrooms.socket.beanmanager.ChangzhanStatusBeanManager;
import cn.v6.sixrooms.socket.beanmanager.ChangzhanTimeBeanManager;
import cn.v6.sixrooms.socket.beanmanager.ChatPermissionBeanManager;
import cn.v6.sixrooms.socket.beanmanager.CommonEventStatusBeanManager;
import cn.v6.sixrooms.socket.beanmanager.FansListTmBeanManager;
import cn.v6.sixrooms.socket.beanmanager.FlyTextBeanManager;
import cn.v6.sixrooms.socket.beanmanager.FreeVoteMsgBeanManager;
import cn.v6.sixrooms.socket.beanmanager.GameLuckIndianaInitManager;
import cn.v6.sixrooms.socket.beanmanager.GamePlaneEndBeanManager;
import cn.v6.sixrooms.socket.beanmanager.GamePlaneStartBeanManager;
import cn.v6.sixrooms.socket.beanmanager.GiftBeanManager;
import cn.v6.sixrooms.socket.beanmanager.GirlSystemGiftManager;
import cn.v6.sixrooms.socket.beanmanager.GuardStausBeanManager;
import cn.v6.sixrooms.socket.beanmanager.HeadlineBeanManager;
import cn.v6.sixrooms.socket.beanmanager.LiveBroadcastBeanManager;
import cn.v6.sixrooms.socket.beanmanager.LiveBroadcastChangeManager;
import cn.v6.sixrooms.socket.beanmanager.LiveMessageManager;
import cn.v6.sixrooms.socket.beanmanager.LiveStateBeanManager;
import cn.v6.sixrooms.socket.beanmanager.NoticeTmBeanManager;
import cn.v6.sixrooms.socket.beanmanager.PigPkYellowDuckBeanManager;
import cn.v6.sixrooms.socket.beanmanager.PrivateChatBeanManager;
import cn.v6.sixrooms.socket.beanmanager.PublicChatBeanManager;
import cn.v6.sixrooms.socket.beanmanager.ResponseBeanManager;
import cn.v6.sixrooms.socket.beanmanager.RoomUpgradeMsgManager;
import cn.v6.sixrooms.socket.beanmanager.SmallFlyTextBeanManager;
import cn.v6.sixrooms.socket.beanmanager.SofaBeanManager;
import cn.v6.sixrooms.socket.beanmanager.SongLiveListBeanManager;
import cn.v6.sixrooms.socket.beanmanager.SpeakManager;
import cn.v6.sixrooms.socket.beanmanager.SubRedBeanManager;
import cn.v6.sixrooms.socket.beanmanager.SysNotificationBeanManager;
import cn.v6.sixrooms.socket.beanmanager.UpdateCoinWealthBeanManager;
import cn.v6.sixrooms.socket.beanmanager.UpdateGiftNumBeanManager;
import cn.v6.sixrooms.socket.beanmanager.UserListTmBeanManager;
import cn.v6.sixrooms.socket.beanmanager.VoteBeanManager;
import cn.v6.sixrooms.socket.beanmanager.WelcomeBeanManager;
import cn.v6.sixrooms.socket.beanmanager.WrapUserInfoManager;
import cn.v6.sixrooms.socket.chat.ChangzhanSocketCallBack;
import cn.v6.sixrooms.socket.chat.ChatMsgSocketCallBack;
import cn.v6.sixrooms.socket.chat.CommonEventStatusBean;
import cn.v6.sixrooms.socket.chat.CommonEventVoteMsgCallBack;
import cn.v6.sixrooms.socket.chat.GameLuckIndianaListener;
import cn.v6.sixrooms.socket.chat.GamePlaneListener;
import cn.v6.sixrooms.socket.chat.PigPkYellowDuckSocketCallBack;
import cn.v6.sixrooms.socket.chat.SuperGMsgCallBack;
import cn.v6.sixrooms.socket.chat.XiyouGameListener;
import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.SuperFireworksTextUtils;
import cn.v6.sixrooms.utils.ChatStyleUtils;
import cn.v6.sixrooms.utils.JsonParseUtils;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.RoommsgBeanFormatUtils;
import cn.v6.sixrooms.utils.SharedPreferencesUtils;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.google.gson.Gson;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class SocketBusinessManager {
    private ChangzhanSocketCallBack a;
    private CommonEventVoteMsgCallBack b;
    private PigPkYellowDuckSocketCallBack c;
    protected ChatMsgSocketCallBack callBack;
    private ReadGiftEngine d;
    private BoxingListener e;
    private HeadLineListener f;
    private MiniGameListener g;
    private MiniGameLiveListener h;
    private GamePlaneListener i;
    private GameLuckIndianaListener j;
    private RedPackgeLisener k;
    private SuperGMsgCallBack l;
    private XiyouGameListener m;
    private SocketBusinessManager$SocketBusinessListener n;

    public SocketBusinessManager(ChatMsgSocketCallBack chatMsgSocketCallBack, SocketBusinessManager$SocketBusinessListener socketBusinessManager$SocketBusinessListener) {
        this.callBack = chatMsgSocketCallBack;
        this.n = socketBusinessManager$SocketBusinessListener;
    }

    public void setRedPackgeLisener(RedPackgeLisener redPackgeLisener) {
        this.k = redPackgeLisener;
    }

    public void addGameListener(XiyouGameListener xiyouGameListener) {
        this.m = xiyouGameListener;
    }

    public void setGamePlaneListener(GamePlaneListener gamePlaneListener) {
        this.i = gamePlaneListener;
    }

    public void setGameLuckIndianaListener(GameLuckIndianaListener gameLuckIndianaListener) {
        this.j = gameLuckIndianaListener;
    }

    public void setSuperGMsgListener(SuperGMsgCallBack superGMsgCallBack) {
        this.l = superGMsgCallBack;
    }

    public void setCommonEventListener(CommonEventVoteMsgCallBack commonEventVoteMsgCallBack) {
        this.b = commonEventVoteMsgCallBack;
    }

    public void setChangzhanListener(ChangzhanSocketCallBack changzhanSocketCallBack) {
        this.a = changzhanSocketCallBack;
    }

    public void setPigPkYellowDuckListener(PigPkYellowDuckSocketCallBack pigPkYellowDuckSocketCallBack) {
        this.c = pigPkYellowDuckSocketCallBack;
    }

    public void setBoxingListener(BoxingListener boxingListener) {
        this.e = boxingListener;
    }

    public void setHeadLineListener(HeadLineListener headLineListener) {
        this.f = headLineListener;
    }

    public void setMiniGameListener(MiniGameListener miniGameListener) {
        this.g = miniGameListener;
    }

    public void setMiniGameLiveListener(MiniGameLiveListener miniGameLiveListener) {
        this.h = miniGameLiveListener;
    }

    public void onReceiveSuccess(JSONObject jSONObject, String str) {
        int i = 0;
        try {
            LogUtils.e("test", "socket下发信息:  " + jSONObject.toString());
            int i2 = jSONObject.getInt("typeID");
            if (i2 == 124) {
                this.callBack.handlerUpgradeMessage((RoomUpgradeMsg) new RoomUpgradeMsgManager().progressMessageBean(jSONObject, str, i2));
            } else if (i2 == 161) {
                onLiveWarningMessage((LiveMessage) new LiveMessageManager().progressMessageBean(jSONObject, str, i2));
            } else if (i2 == 163) {
                onReceiveAnchorPrompt((AnchorPrompt) new AnchorPromptManager().progressMessageBean(jSONObject, str, i2));
            } else if (i2 == 301) {
                onShowMainLive((LiveBroadcastBean) new LiveBroadcastBeanManager().progressMessageBean(jSONObject, str, i2));
            } else if (i2 == 305) {
            } else {
                if (i2 == 306) {
                    onShowMainLive((LiveBroadcastBean) new LiveBroadcastChangeManager().progressMessageBean(jSONObject, str, i2));
                } else if (i2 == 134) {
                    this.callBack.updateHeadLineMsg((InitHeadLineBean) JsonParseUtils.json2Obj(jSONObject.toString(), InitHeadLineBean.class));
                } else if (i2 == 135) {
                    onHeadLineMessage((OnHeadlineBeans) new HeadlineBeanManager().progressMessageBean(jSONObject, str, i2));
                } else if (i2 == 101) {
                    this.callBack.onNotifyPublicDataSetChanged(ChatStyleUtils.chatStyleHandle(RoommsgBeanFormatUtils.formatFromPublicChatBean((PublicChatBean) new PublicChatBeanManager().progressMessageBean(jSONObject, str, i2))), false);
                } else if (i2 == 123) {
                    onWelcomeReceive((WelcomeBean) new WelcomeBeanManager().progressMessageBean(jSONObject, str, i2));
                } else if (i2 == 107) {
                    this.callBack.onNotifyPrivateDataSetChanged(RoommsgBeanFormatUtils.formatFromPrivateChatBean((PrivateChatBean) new PrivateChatBeanManager().progressMessageBean(jSONObject, str, i2)));
                } else if (i2 == 109) {
                    this.callBack.onSofaUpdated(((SocketRoomMessageSofaBean) new SofaBeanManager().progressMessageBean(jSONObject, str, i2)).getContent());
                } else if (i2 == 1350) {
                    CommonEventStatusBean commonEventStatusBean = (CommonEventStatusBean) new CommonEventStatusBeanManager().progressMessageBean(jSONObject, str, i2);
                    if (this.b != null) {
                        this.b.onCommonEventStatusUpdate(commonEventStatusBean);
                    }
                } else if (i2 == 113) {
                    ChangzhanBeginBean changzhanBeginBean = (ChangzhanBeginBean) new ChangzhanBeginBeanManager().progressMessageBean(jSONObject, str, i2);
                    if (this.a != null) {
                        this.a.onChangzhanBegin(changzhanBeginBean);
                    }
                } else if (i2 == 117) {
                    ChangzhanFinishBean changzhanFinishBean = (ChangzhanFinishBean) new ChangzhanFinishManager().progressMessageBean(jSONObject, str, i2);
                    if (this.a != null) {
                        this.a.onChangzhanFinish(changzhanFinishBean);
                    }
                } else if (i2 == 119) {
                    ChangzhanTimeBean changzhanTimeBean = (ChangzhanTimeBean) new ChangzhanTimeBeanManager().progressMessageBean(jSONObject, str, i2);
                    if (this.a != null) {
                        this.a.onChangzhanTimeChange(changzhanTimeBean);
                    }
                } else if (i2 == 129) {
                    ChangzhanFinalUsersBean changzhanFinalUsersBean = (ChangzhanFinalUsersBean) new ChangzhanFinalManager().progressMessageBean(jSONObject, str, i2);
                    if (this.a != null) {
                        this.a.onChangzhanFinalUsersChange(changzhanFinalUsersBean);
                    }
                } else if (i2 == SocketUtil.TYPEID_1413) {
                    r2 = jSONObject.getJSONArray("content");
                    if (r2 != null) {
                        while (i < r2.length()) {
                            onReceiveSuccess(r2.getJSONObject(i), str);
                            i++;
                        }
                    }
                } else if (i2 == 201) {
                    a((GiftBean) new GiftBeanManager().progressMessageBean(jSONObject, str, i2));
                } else if (i2 == 102) {
                    this.callBack.onNotifyPublicDataSetChanged(ChatStyleUtils.chatStyleHandle(RoommsgBeanFormatUtils.formatFromSysNotificationBean((SysNotificationBean) new SysNotificationBeanManager().progressMessageBean(jSONObject, str, i2))), false);
                } else if (i2 == 408) {
                    r1 = (AuthKeyBean) new AuthKeyBeanManager().progressMessageBean(jSONObject, str, i2);
                    if (r1 != null && this.n != null) {
                        this.n.onAuthChange(r1.getAuthKey());
                        r2 = r1.getPriv();
                        r3 = r1.getEventDefend();
                        MiniGameBean miniGameBean = r1.getMiniGameBean();
                        BoxingBean boxingBean = r1.getBoxingBean();
                        InitTopGiftBean initTopGift = r1.getInitTopGift();
                        if (!TextUtils.isEmpty(r2)) {
                            this.callBack.onReceiveSpeakState(r1);
                        }
                        if (!(initTopGift == null || this.f == null)) {
                            this.f.onHeadLine(initTopGift);
                        }
                        if (!(boxingBean == null || this.e == null)) {
                            this.e.handleBoxingInitialization(boxingBean);
                        }
                        if (r3 == 1 && this.n != null) {
                            this.n.onPigPkDuckInit();
                        }
                        if (miniGameBean != null && this.g != null) {
                            this.g.onMiniGame(miniGameBean);
                        }
                    }
                } else if (i2 == 151) {
                    r1 = (SongLiveListBean) new SongLiveListBeanManager().progressMessageBean(jSONObject, str, i2);
                    if (SocketUtil.T_SONG_SHOW_LIVE_LIST.equals(r1.getType())) {
                        this.callBack.onShowSongMenuList(r1.getLiveList());
                    } else if (SocketUtil.T_SONG_SHOW_CALLED_LIST.equals(r1.getType())) {
                        this.callBack.onShowSongQueueList(r1.getLiveList());
                    } else {
                        this.callBack.onShowSongUpdataList(r1.getLiveList());
                    }
                } else if (i2 == 701) {
                    MessageBean progressMessageBean = new ResponseBeanManager().progressMessageBean(jSONObject, str, i2);
                    if (progressMessageBean instanceof MiniGameIdBean) {
                        MiniGameIdBean miniGameIdBean = (MiniGameIdBean) progressMessageBean;
                        if (SocketUtil.T_ROOM_MINIGAME_OPEN.equals(miniGameIdBean.getType())) {
                            if (this.h != null) {
                                this.h.onMiniGameOpen(miniGameIdBean.getGameId());
                            }
                        } else if (SocketUtil.T_ROOM_MINIGAME_CLOSE.equals(miniGameIdBean.getType()) && this.h != null) {
                            this.h.onMiniGameClose(miniGameIdBean.getGameId());
                        }
                    } else if (progressMessageBean instanceof ErrorBean) {
                        this.callBack.onReceiveError((ErrorBean) progressMessageBean);
                    } else if (progressMessageBean instanceof SongLiveListBean) {
                        r1 = (SongLiveListBean) progressMessageBean;
                        if (SocketUtil.T_SONG_SHOW_LIVE_LIST.equals(r1.getType())) {
                            this.callBack.onShowSongMenuList(r1.getLiveList());
                        } else if (SocketUtil.T_SONG_SHOW_CALLED_LIST.equals(r1.getType())) {
                            this.callBack.onShowSongQueueList(r1.getLiveList());
                        } else {
                            this.callBack.onShowSongUpdataList(r1.getLiveList());
                        }
                    } else if (progressMessageBean instanceof AuthKeyBean) {
                        this.n.onAuthChange(((AuthKeyBean) progressMessageBean).getAuthKey());
                    } else if (progressMessageBean instanceof FireworkFailBean) {
                        if (this.k != null) {
                            this.k.onGetFailResult(((FireworkFailBean) progressMessageBean).getRedid(), ((FireworkFailBean) progressMessageBean).getState(), ((FireworkFailBean) progressMessageBean).getMsg());
                        }
                    } else if (progressMessageBean instanceof FireworkSuccessBean) {
                        if (this.k != null) {
                            this.k.onGetSuccResult(((FireworkSuccessBean) progressMessageBean).getRedid(), ((FireworkSuccessBean) progressMessageBean).getMsg());
                        }
                    } else if (progressMessageBean instanceof GiftListBean) {
                        this.callBack.onGiftList((GiftListBean) progressMessageBean);
                    }
                } else if (i2 == 105) {
                    this.callBack.onLiveStateReceive((LiveStateBean) new LiveStateBeanManager().progressMessageBean(jSONObject, str, i2));
                } else if (i2 == 404) {
                    r1 = (AuthKeyBean) new SpeakManager().progressMessageBean(jSONObject, str, i2);
                    if (r1 != null) {
                        r2 = r1.getPriv();
                        r3 = r1.getEventDefend();
                        BoxingBean boxingBean2 = r1.getBoxingBean();
                        InitTopGiftBean initTopGift2 = r1.getInitTopGift();
                        if (!TextUtils.isEmpty(r2)) {
                            this.callBack.onReceiveSpeakState(r1);
                        }
                        if (!(initTopGift2 == null || this.f == null)) {
                            this.f.onHeadLine(initTopGift2);
                        }
                        if (!(boxingBean2 == null || this.e == null)) {
                            this.e.handleBoxingInitialization(boxingBean2);
                        }
                        if (r3 == 1) {
                            this.n.onPigPkDuckInit();
                        }
                    }
                } else if (i2 == 413) {
                    r1 = ((UserListTmBean) new UserListTmBeanManager().progressMessageBean(jSONObject, str, i2)).getTm();
                    if (this.n != null) {
                        this.n.onReceiveUserListTm(r1);
                    }
                    this.callBack.onReceiveChatList(r1);
                } else if (i2 == 407) {
                    WrapUserInfo wrapUserInfo = (WrapUserInfo) new WrapUserInfoManager().progressMessageBean(jSONObject, str, i2);
                    if (wrapUserInfo != null) {
                        this.callBack.onReceiveAllChatList(wrapUserInfo);
                    }
                } else if (i2 == 414) {
                    this.callBack.onReceiveFansTm(((FansListTmBean) new FansListTmBeanManager().progressMessageBean(jSONObject, str, i2)).getTm());
                } else if (i2 == 415) {
                } else {
                    if (i2 == 416) {
                        NoticeTmBean noticeTmBean = (NoticeTmBean) new NoticeTmBeanManager().progressMessageBean(jSONObject, str, i2);
                        if (noticeTmBean != null) {
                            this.callBack.onReceiveNoticeTm(noticeTmBean);
                        }
                    } else if (i2 == 114) {
                        ChatPermissionBean chatPermissionBean = (ChatPermissionBean) new ChatPermissionBeanManager().progressMessageBean(jSONObject, str, i2);
                        if (chatPermissionBean != null) {
                            this.callBack.onReceiveChatPermission(chatPermissionBean);
                        }
                    } else if (i2 == SocketUtil.TYPEID_201001) {
                        this.callBack.onUpdateCoinWealth((UpdateCoinWealthBean) new UpdateCoinWealthBeanManager().progressMessageBean(jSONObject, str, i2));
                    } else if (i2 == 1201) {
                        this.callBack.onUpdateGiftNum((UpdateGiftNumBean) new UpdateGiftNumBeanManager().progressMessageBean(jSONObject, str, i2));
                    } else if (i2 == SocketUtil.TYPEID_810) {
                        this.callBack.onVoteReceive((VoteBean) new VoteBeanManager().progressMessageBean(jSONObject, str, i2));
                    } else if (i2 == SocketUtil.TYPEID_1304) {
                        SubRedBean subRedBean = (SubRedBean) new SubRedBeanManager().progressMessageBean(jSONObject, str, i2);
                        if (subRedBean != null) {
                            r2 = RoommsgBeanFormatUtils.formatFromSubRedBean(subRedBean);
                            r2.setTypeID(subRedBean.getTypeId());
                            this.callBack.onNotifyPublicDataSetChanged(ChatStyleUtils.chatStyleHandle(r2), true);
                        }
                    } else if (i2 == SocketUtil.TYPEID_1305) {
                        FreeVoteMsgBean freeVoteMsgBean = (FreeVoteMsgBean) new FreeVoteMsgBeanManager().progressMessageBean(jSONObject, str, i2);
                        if (freeVoteMsgBean != null) {
                            this.callBack.onNotifyPublicDataSetChanged(RoommsgBeanFormatUtils.formatFromFreeVote(freeVoteMsgBean), false);
                        }
                    } else if (i2 == 1306) {
                        ChangzhanStatusBean changzhanStatusBean = (ChangzhanStatusBean) new ChangzhanStatusBeanManager().progressMessageBean(jSONObject, str, i2);
                        if (this.a != null) {
                            this.a.onChangzhanStatusUpdate(changzhanStatusBean);
                        }
                    } else if (i2 != 509 && i2 != 510 && i2 != 511 && i2 != 512 && i2 != 513) {
                        if (i2 == SocketUtil.TYPEID_518) {
                            GuardStausBean guardStausBean = (GuardStausBean) new GuardStausBeanManager().progressMessageBean(jSONObject, str, i2);
                            if (this.callBack != null) {
                                this.callBack.onRececiveGuardShow(guardStausBean);
                            }
                        } else if (i2 == 108) {
                            this.callBack.onReceiveFlyText((FlyTextBean) new FlyTextBeanManager().progressMessageBean(jSONObject, str, i2));
                        } else if (i2 == 139) {
                            this.callBack.onReceiveSmallFlyText((SmallFlyTextBean) new SmallFlyTextBeanManager().progressMessageBean(jSONObject, str, i2));
                        } else if (i2 == 403) {
                        } else {
                            if (i2 == 1514) {
                                if (this.c != null) {
                                    this.c.onPigPkYellowDuckChange((PigPkYellowDuckBean) new PigPkYellowDuckBeanManager().progressMessageBean(jSONObject, str, i2));
                                }
                            } else if (i2 == SocketUtil.TYPEID_1519) {
                                if (this.i != null) {
                                    this.i.onNotifyGameStart((GamePlaneStartBean) new GamePlaneStartBeanManager().progressMessageBean(jSONObject, str, i2));
                                }
                            } else if (i2 == SocketUtil.TYPEID_1520) {
                                if (this.i != null) {
                                    this.i.onNotifyGameEnd((GamePlaneEndBean) new GamePlaneEndBeanManager().progressMessageBean(jSONObject, str, i2));
                                }
                            } else if (i2 == SocketUtil.TYPEID_1521) {
                                r1 = jSONObject.getJSONObject("content").getString("money");
                                if (this.i != null) {
                                    this.i.onNotifySapphireAward(r1);
                                }
                            } else if (i2 == SocketUtil.TYPEID_1523) {
                                if (this.j != null) {
                                    this.j.onNotifyGameStart((GameLuckIndianaInitBean) new GameLuckIndianaInitManager().progressMessageBean(jSONObject, str, i2));
                                }
                            } else if (i2 == SocketUtil.TYPEID_1524) {
                                GameLuckIndianaResultBean gameLuckIndianaResultBean = (GameLuckIndianaResultBean) JsonParseUtils.json2Obj(jSONObject.getString("content"), GameLuckIndianaResultBean.class);
                                if (this.j != null) {
                                    this.j.onNotifyGameEnd(gameLuckIndianaResultBean);
                                }
                            } else if (i2 == SocketUtil.TYPEID_1525) {
                                Map map = (Map) new Gson().fromJson(jSONObject.getString("content"), new a(this).getType());
                                if (this.j != null) {
                                    this.j.onNotifyGameBuyNum(map);
                                }
                            } else if (i2 == 1605) {
                                if (this.k != null) {
                                    r2 = jSONObject.getJSONArray("content");
                                    r3 = r2.length();
                                    r4 = new String[r3];
                                    while (i < r3) {
                                        r4[i] = r2.getString(i);
                                        i++;
                                    }
                                    this.k.onReceiveBigFireworks(r4);
                                }
                            } else if (i2 == 1606) {
                                if (this.k != null) {
                                    this.k.onRedPackageNone(jSONObject.getJSONObject("content").getString("redid"));
                                }
                            } else if (i2 == SocketUtil.TYPEID_1607) {
                                if (this.k != null) {
                                    r2 = jSONObject.getJSONArray("content");
                                    r3 = r2.length();
                                    r4 = new String[r3];
                                    r5 = new String[r3];
                                    while (i < r3) {
                                        r6 = r2.getJSONObject(i);
                                        r4[i] = r6.getString("id");
                                        r5[i] = r6.getString("second");
                                        i++;
                                    }
                                    this.k.onReceiveSuperFireworks(r4, r5);
                                }
                            } else if (i2 == SocketUtil.TYPEID_1608) {
                                if (this.k != null) {
                                    r2 = jSONObject.getJSONArray("content");
                                    r3 = r2.length();
                                    r4 = new String[r3];
                                    r5 = new String[r3];
                                    while (i < r3) {
                                        r6 = r2.getJSONObject(i);
                                        r4[i] = r6.getString("id");
                                        r5[i] = r6.getString("redid");
                                        i++;
                                    }
                                    this.k.onReceiveFireworksTimeEnd(r4, r5);
                                }
                            } else if (i2 == SocketUtil.TYPEID_1501) {
                                this.callBack.onMiniGameStart((MiniGameBean) JsonParseUtils.json2Obj(jSONObject.getString("content"), MiniGameBean.class));
                            } else if (i2 == SocketUtil.TYPEID_1502) {
                                this.callBack.onMiniGameEnd((MiniGameBean) JsonParseUtils.json2Obj(jSONObject.getString("content"), MiniGameBean.class));
                            } else if (i2 == SocketUtil.TYPEID_1503) {
                                this.callBack.onMiniGameUpdate(jSONObject.toString());
                            } else if (i2 != 503 && i2 != 504) {
                                if (i2 == 533) {
                                    if (this.e != null) {
                                        BoxingBean boxingBean3 = (BoxingBean) JsonParseUtils.json2Obj(jSONObject.getString("content"), BoxingBean.class);
                                        if (boxingBean3 != null) {
                                            this.e.handleBoxingStart(boxingBean3);
                                        }
                                    }
                                } else if (i2 == 534) {
                                    BoxingVoteBean boxingVoteBean = (BoxingVoteBean) JsonParseUtils.json2Obj(jSONObject.getString("content"), BoxingVoteBean.class);
                                    if (boxingVoteBean != null && this.e != null) {
                                        this.e.handleBoxingVote(boxingVoteBean);
                                    }
                                } else if (i2 == 535) {
                                    BoxingWinningBean boxingWinningBean = (BoxingWinningBean) JsonParseUtils.json2Obj(jSONObject.getString("content"), BoxingWinningBean.class);
                                    if (boxingWinningBean != null && this.e != null) {
                                        this.e.handleBoxingWinning(boxingWinningBean);
                                    }
                                } else if (i2 == 536) {
                                    BoxingBoxerVotes boxingBoxerVotes = (BoxingBoxerVotes) JsonParseUtils.json2Obj(jSONObject.getString("content"), BoxingBoxerVotes.class);
                                    if (boxingBoxerVotes != null && this.e != null) {
                                        this.e.handleBoxingFlushVotes(boxingBoxerVotes);
                                    }
                                } else if (i2 == 537) {
                                    BoxingWinVictoryBean boxingWinVictoryBean = (BoxingWinVictoryBean) JsonParseUtils.json2Obj(jSONObject.getString("content"), BoxingWinVictoryBean.class);
                                    if (boxingWinVictoryBean != null && this.e != null) {
                                        this.e.handleBoxingWinVictory(boxingWinVictoryBean);
                                    }
                                } else if (i2 == 538) {
                                    BoxingCloseBean boxingCloseBean = (BoxingCloseBean) JsonParseUtils.json2Obj(jSONObject.getString("content"), BoxingCloseBean.class);
                                    if (boxingCloseBean != null && this.e != null) {
                                        this.e.handleBoxingClose(boxingCloseBean);
                                    }
                                } else if (i2 == 409) {
                                    a((GiftBean) new GirlSystemGiftManager().progressMessageBean(jSONObject, str, i2));
                                } else if (i2 == SocketUtil.TYPEID_201002) {
                                    if (jSONObject.has("content")) {
                                        r1 = jSONObject.getString("content");
                                        StarlightCount starlightCount = JsonParseUtils.isJson(r1) ? (StarlightCount) JsonParseUtils.json2Obj(r1, StarlightCount.class) : null;
                                        if (starlightCount != null && this.l != null) {
                                            this.l.receiveStartlightCount(starlightCount);
                                        }
                                    }
                                } else if (i2 == 138) {
                                    BroadcastBean broadcastBean = (BroadcastBean) JsonParseUtils.json2Obj(jSONObject.getString("content"), BroadcastBean.class);
                                    broadcastBean.setFrom(jSONObject.getString(UserTrackerConstants.FROM));
                                    broadcastBean.setTo(jSONObject.getString("to"));
                                    this.callBack.onRececiveBroadcast(broadcastBean);
                                } else if (i2 == SocketUtil.TYPEID_430) {
                                    this.callBack.onRececiveBecomeGod((BecomeGodBean) JsonParseUtils.json2Obj(jSONObject.getString("content"), BecomeGodBean.class));
                                } else if (i2 == SocketUtil.TYPEID_431) {
                                    this.callBack.onRececiveSuperFireworks((SuperFireworksBean) JsonParseUtils.json2Obj(jSONObject.getString("content"), SuperFireworksBean.class));
                                } else if (i2 == SocketUtil.TYPEID_1705) {
                                    this.callBack.onRececivePublicNotice((PublicNoticeBean) JsonParseUtils.json2Obj(jSONObject.getString("content"), PublicNoticeBean.class));
                                } else if (i2 == SocketUtil.TYPEID_1309) {
                                    ActivityBean activityBean = (ActivityBean) new ActivityBeanManager().progressMessageBean(jSONObject, str, i2);
                                    r2 = new RoommsgBean();
                                    r2.setTypeID(activityBean.getTypeId());
                                    r2.setEid(activityBean.getContent().getEid());
                                    r2.setFid(activityBean.getFid());
                                    r2.setFrid(activityBean.getFrid());
                                    r2.setFrom(activityBean.getFrom());
                                    r2.setTo(activityBean.getTo());
                                    r2.setToid(activityBean.getTid());
                                    r2.setTorid(activityBean.getTrid());
                                    r2.setContent(new StringBuilder(SuperFireworksTextUtils.s1).append(activityBean.getContent().getNum()).append("张").append(activityBean.getContent().getEname()).append("人气票").toString());
                                    this.callBack.onNotifyPublicDataSetChanged(ChatStyleUtils.chatStyleHandle(r2), false);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public void onReceiveFail(JSONObject jSONObject, String str) {
        if (SocketUtil.FLAG_ON_RECONNECT.equals(str)) {
            new FlagBean().setFlag(SocketUtil.FLAG_ON_RECONNECT);
            this.callBack.onReconnectChatSocket();
            return;
        }
        ErrorBean errorBean = new ErrorBean();
        errorBean.setFlag(str);
        if (SocketUtil.FLAG_ON_KICK_OUT.equals(str)) {
            errorBean.setContent("您已经被禁止进入本房间！");
        } else if (SocketUtil.FLAG_ON_FULL.equals(str)) {
            errorBean.setContent("房间人数已满！");
        } else if (SocketUtil.FLAG_ON_MISTAKE_OUT.equals(str)) {
            try {
                errorBean.setContent(jSONObject.getString("content"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.callBack.onReceiveError(errorBean);
    }

    public void onLiveWarningMessage(LiveMessage liveMessage) {
        this.callBack.handlerLiveWarning(liveMessage);
    }

    public void onReceiveAnchorPrompt(AnchorPrompt anchorPrompt) {
        this.callBack.handlerAnchorPrompt(anchorPrompt);
    }

    public void onShowMainLive(LiveBroadcastBean liveBroadcastBean) {
        this.callBack.onShowMainLive(liveBroadcastBean);
    }

    public void onHeadLineMessage(OnHeadlineBeans onHeadlineBeans) {
        this.callBack.onHeadLineMessage(onHeadlineBeans);
        RoommsgBean roommsgBean = new RoommsgBean();
        if (onHeadlineBeans.getTop8() != null && onHeadlineBeans.getTop8().size() > 3) {
            roommsgBean.setHeadlineBeans(onHeadlineBeans);
            roommsgBean.setTypeID(onHeadlineBeans.getTypeId());
            roommsgBean.setTm(onHeadlineBeans.getTm());
            this.callBack.onNotifyPublicDataSetChanged(ChatStyleUtils.chatStyleHandle(roommsgBean), false);
        }
    }

    public void onWelcomeReceive(WelcomeBean welcomeBean) {
        if (SharedPreferencesUtils.getBooleanSettings(V6Coop.getInstance().getContext(), "welcome_switch")) {
            this.callBack.onRececiveWelcome(welcomeBean);
            this.callBack.onNotifyPublicDataSetChanged(ChatStyleUtils.chatStyleHandle(RoommsgBeanFormatUtils.formatFromWelcomeBean(welcomeBean)), false);
        }
    }

    private void a(GiftBean giftBean) {
        if (giftBean != null) {
            if (this.d == null) {
                this.d = new ReadGiftEngine();
            }
            Gift giftBeanById = this.d.getGiftBeanById(giftBean.getItem());
            if (giftBeanById == null) {
                if (giftBean.getItem() == GiftIdStrs.GOLD_GUARD || giftBean.getItem() == GiftIdStrs.SILVER_GUARD) {
                    giftBeanById = new Gift();
                } else {
                    return;
                }
            }
            giftBeanById.setFid(giftBean.getFid());
            giftBeanById.setFrid(giftBean.getFrid());
            giftBeanById.setFrom(giftBean.getFrom());
            giftBeanById.setItem(giftBean.getItem());
            giftBeanById.setMsg(giftBean.getMsg());
            giftBeanById.setNum(giftBean.getNum());
            giftBeanById.setTid(giftBean.getTid());
            giftBeanById.setTo(giftBean.getTo());
            giftBeanById.setTrid(giftBean.getTrid());
            if (giftBean.getFrid() != 0) {
                if (giftBean.getItem() == GiftIdStrs.GOLD_GUARD || giftBean.getItem() == GiftIdStrs.SILVER_GUARD) {
                    giftBeanById = new Gift();
                    giftBeanById.setFrid(giftBean.getFrid());
                    giftBeanById.setItem(giftBean.getItem());
                } else {
                    this.callBack.onReceiveGift(giftBeanById);
                }
            } else if (giftBean.isSystem()) {
                this.callBack.onReceiveGift(giftBeanById);
            }
            this.callBack.onNotifyPublicDataSetChanged(ChatStyleUtils.chatStyleHandle(RoommsgBeanFormatUtils.formatFromGift(giftBean, giftBeanById)), false);
        }
    }
}
