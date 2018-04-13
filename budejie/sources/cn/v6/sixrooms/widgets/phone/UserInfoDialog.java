package cn.v6.sixrooms.widgets.phone;

import android.app.Dialog;
import android.content.Intent;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.event.EventManager;
import cn.v6.sixrooms.event.GiftBoxEvent;
import cn.v6.sixrooms.presenter.UserInfoPresenter;
import cn.v6.sixrooms.presenter.runnable.UserInfoDsplayable;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.ReportActivity;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.room.statistic.StatisticManager;
import cn.v6.sixrooms.room.statistic.StatisticValue;
import cn.v6.sixrooms.room.utils.WealthRankImageUtils;
import cn.v6.sixrooms.room.view.DraweeSpan.Builder;
import cn.v6.sixrooms.room.view.DraweeTextView;
import cn.v6.sixrooms.ui.view.UserInfoProgressBar;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.DrawableResourceUtils;
import cn.v6.sixrooms.utils.PropParseUtil;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.facebook.drawee.view.SimpleDraweeView;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class UserInfoDialog extends Dialog implements OnClickListener, UserInfoDsplayable {
    private UserInfoProgressBar A;
    private SimpleDraweeView B;
    private SimpleDraweeView C;
    private TextView D;
    private DecimalFormat E;
    private View F;
    private View G;
    private TextView H;
    private TextView I;
    private View J;
    private View K;
    private View L;
    private View M;
    private TextView N;
    private TextView O;
    private ImageView P;
    private ImageView Q;
    private View R;
    private TextView S;
    private View T;
    private TextView U;
    private View V;
    private String W;
    private String X;
    private UserInfoBean Y;
    private BaseRoomActivity a;
    private SimpleDraweeView b;
    private ImageView c;
    private ImageView d;
    private DraweeTextView e;
    private View f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private View n;
    private View o;
    private View p;
    private View q;
    private View r;
    private UserInfoBean s;
    private UserInfoPresenter t = new UserInfoPresenter();
    private View u;
    private View v;
    private UserInfoProgressBar w;
    private ImageView x;
    private ImageView y;
    private TextView z;

    public UserInfoDialog(BaseRoomActivity baseRoomActivity) {
        super(baseRoomActivity, R.style.ImprovedDialog);
        this.a = baseRoomActivity;
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_user_info);
        this.t.setUserInfoDsplayable(this);
        this.r = findViewById(R.id.rl_root);
        this.p = findViewById(R.id.rl_content);
        this.q = findViewById(R.id.progressBar);
        this.b = (SimpleDraweeView) findViewById(R.id.iv_header);
        this.c = (ImageView) findViewById(R.id.iv_vip);
        this.d = (ImageView) findViewById(R.id.iv_card);
        this.e = (DraweeTextView) findViewById(R.id.tv_badge);
        this.f = findViewById(R.id.iv_close_btn);
        this.g = (TextView) findViewById(R.id.report);
        this.h = (TextView) findViewById(R.id.tv_user_name);
        this.i = (TextView) findViewById(R.id.tv_user_rid);
        this.k = (TextView) findViewById(R.id.tv_public_chat);
        this.j = (TextView) findViewById(R.id.tv_follow);
        this.u = findViewById(R.id.ll_bottom);
        this.v = findViewById(R.id.ll_bottom3);
        this.w = (UserInfoProgressBar) findViewById(R.id.user_level_progressBar);
        this.x = (ImageView) findViewById(R.id.iv_level);
        this.y = (ImageView) findViewById(R.id.iv_level_next);
        this.z = (TextView) findViewById(R.id.tv_level_desc);
        this.A = (UserInfoProgressBar) findViewById(R.id.user_coin_level_progressBar);
        this.B = (SimpleDraweeView) findViewById(R.id.iv_coin_level);
        this.C = (SimpleDraweeView) findViewById(R.id.iv_coin_level_next);
        this.D = (TextView) findViewById(R.id.tv_coin_level_desc);
        this.F = findViewById(R.id.rl_star_level_info);
        this.G = findViewById(R.id.rl_coin_level_info);
        this.E = new DecimalFormat("###,###");
        this.K = findViewById(R.id.ll_bottom_2);
        this.H = (TextView) findViewById(R.id.tv_upgrade_manager);
        this.I = (TextView) findViewById(R.id.tv_upgrade_admin);
        this.J = findViewById(R.id.div3_2);
        this.l = (TextView) findViewById(R.id.tv_mute);
        this.m = (TextView) findViewById(R.id.tv_popup);
        this.n = findViewById(R.id.div1);
        this.o = findViewById(R.id.div2);
        this.N = (TextView) findViewById(R.id.tv_mute_2);
        this.O = (TextView) findViewById(R.id.tv_popup_2);
        this.L = findViewById(R.id.div4_2);
        this.M = findViewById(R.id.div5_2);
        this.S = (TextView) findViewById(R.id.tv_gift);
        this.T = findViewById(R.id.div7_2);
        this.U = (TextView) findViewById(R.id.tv_private_chat);
        this.V = findViewById(R.id.div8_2);
        this.P = (ImageView) findViewById(R.id.ll_bottom_line);
        this.Q = (ImageView) findViewById(R.id.ll_bottom_line3);
        this.R = findViewById(R.id.ll_bottom2_line);
        this.g.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.H.setOnClickListener(this);
        this.I.setOnClickListener(this);
        this.S.setOnClickListener(this);
        this.U.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_gift) {
            if (this.Y != null) {
                String uid = this.Y.getUid();
                String uname = this.Y.getUname();
                GiftBoxEvent giftBoxEvent = new GiftBoxEvent();
                giftBoxEvent.setUid(uid);
                giftBoxEvent.setUname(uname);
                EventManager.getDefault().nodifyObservers(giftBoxEvent, "0");
            }
            dismiss();
            StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_SENDGIFT);
            StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_SENDGIFT);
            StatisticValue.getInstance().setRechargePageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_SENDGIFT);
        } else if (id == R.id.tv_private_chat) {
            this.a.showPrivateChatView(this.Y);
            dismiss();
            if (this.X.equals(this.W)) {
                StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_CHAT);
                return;
            }
            StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_PCHAT);
            StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_PCHAT);
        } else if (id == R.id.iv_close_btn || id == R.id.rl_root) {
            dismiss();
        } else if (id == R.id.tv_follow) {
            this.t.requestFollow(!this.s.getIsfollow().equals("1"));
            dismiss();
            if (this.X.equals(this.W)) {
                StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FPRO_FOLLOW);
                StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FPRO_FOLLOW);
                return;
            }
            StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_FOLLOW);
            StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_FOLLOW);
        } else if (id == R.id.tv_public_chat) {
            this.a.showPublicChatView(this.Y);
            dismiss();
        } else if (id == R.id.tv_mute_2 || id == R.id.tv_mute) {
            if (this.Y == null || this.Y.getSpeakState() == 0) {
                this.t.requestUnMute();
            } else {
                this.t.requestMute();
            }
            dismiss();
        } else if (id == R.id.tv_popup) {
            this.t.requestPopup();
            dismiss();
        } else if (id == R.id.tv_upgrade_manager) {
            if (this.Y == null || this.Y.getUserIdentity() == 10) {
                this.t.reqeuestRevokeManager();
            } else {
                this.t.reqeuestAddManager();
            }
            dismiss();
        } else if (id == R.id.tv_upgrade_admin) {
            if (this.Y == null || this.Y.getUserIdentity() == 7) {
                this.t.reqeuestRevokeAdmin();
            } else {
                this.t.reqeuestAddAdmin();
            }
            dismiss();
        } else if (id == R.id.report) {
            Intent intent = new Intent(this.a, ReportActivity.class);
            intent.putExtra(HistoryOpenHelper.COLUMN_UID, this.X);
            this.a.startActivity(intent);
        }
    }

    public void show(String str, String str2, boolean z, int i) {
        this.W = str2;
        this.X = str;
        this.t.initData();
        this.t.updateBean(str, str2, z, i);
        b(false);
        c(false);
        a(false);
        showBottomView();
        showBottom2View();
        showBottomView3();
        hideStarLevelView();
        showCoinLevelView();
        if (!this.a.isSuperGirlRoom().booleanValue()) {
            showGiftView();
        }
        super.show();
    }

    @Deprecated
    public void show() {
    }

    public void updateView(UserInfoBean userInfoBean) {
        this.s = userInfoBean;
        String vipLevel = userInfoBean.getVipLevel();
        String cardLevel = userInfoBean.getCardLevel();
        if (UrlStrs.UNKNOW_PORTRAIT.equals(this.s.getUserpic())) {
            this.b.setImageResource(R.drawable.rooms_third_common_head_portrait);
        } else {
            this.b.setImageURI(this.s.getUserpic());
        }
        this.h.setText(this.s.getUname());
        if ("7104".equals(vipLevel)) {
            this.c.setImageResource(R.drawable.iv_vip_purple);
            this.c.setVisibility(0);
        } else if ("7105".equals(vipLevel)) {
            this.c.setImageResource(R.drawable.iv_vip_yellow);
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
        if ("7559".equals(cardLevel)) {
            this.d.setImageResource(R.drawable.iv_card_green);
            this.d.setVisibility(0);
        } else if ("7859".equals(cardLevel)) {
            this.d.setImageResource(R.drawable.iv_card_yellow);
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
        List<String> parsePropImgUrlList = PropParseUtil.parsePropImgUrlList(Arrays.asList(this.Y.getProp().split(",")));
        if (parsePropImgUrlList == null || parsePropImgUrlList.size() <= 0) {
            this.e.setVisibility(8);
        } else {
            CharSequence spannableStringBuilder = new SpannableStringBuilder("");
            for (String vipLevel2 : parsePropImgUrlList) {
                spannableStringBuilder.insert(0, "* ");
                spannableStringBuilder.setSpan(new Builder(vipLevel2).setLayout(DensityUtil.dip2px(17.0f), DensityUtil.dip2px(17.0f)).setPlaceHolderImage(this.a.getResources().getDrawable(R.drawable.badge_default)).build(), 0, 1, 33);
            }
            this.e.setText(spannableStringBuilder);
            this.e.setVisibility(0);
        }
        this.i.setText("房间号：" + this.s.getUrid());
        this.x.setImageResource(DrawableResourceUtils.getStarLevelImageResource(userInfoBean.getAnchorLevel()));
        this.y.setImageResource(DrawableResourceUtils.getStarLevelImageResource(userInfoBean.getAnchorLevel() + 1));
        this.z.setText("还差" + this.E.format(userInfoBean.getWealtlate()) + "六豆");
        if (userInfoBean.getWealthstep() != 0) {
            this.w.setProgress((int) (((userInfoBean.getWealthstep() - userInfoBean.getWealtlate()) * ((long) this.w.getMax())) / userInfoBean.getWealthstep()));
        }
        WealthRankImageUtils.setWealthImageView(userInfoBean.getUid(), userInfoBean.getWealthLevel(), this.B, userInfoBean.getIsGodPic() == 1);
        if (userInfoBean.getIsGodPic() == 1) {
            this.C.setVisibility(4);
        } else {
            this.C.setVisibility(0);
            WealthRankImageUtils.setNextWealthImageView(userInfoBean.getUid(), userInfoBean.getWealthLevel(), this.C);
        }
        this.D.setText("还差" + this.E.format(userInfoBean.getCoin6late()) + "六币");
        if (userInfoBean.getCoinstep() != 0) {
            this.A.setProgress((int) (((userInfoBean.getCoinstep() - userInfoBean.getCoin6late()) * ((long) this.A.getMax())) / userInfoBean.getCoinstep()));
        }
    }

    public void hideContentView() {
        this.p.setVisibility(4);
    }

    public void showLoadingView() {
        this.q.setVisibility(0);
    }

    public void showFollowState(boolean z) {
        this.j.setVisibility(0);
        if (z) {
            this.j.setSelected(true);
            this.j.setText("已关注");
            this.j.setCompoundDrawables(null, null, null, null);
            return;
        }
        this.j.setSelected(false);
        this.j.setText("关注");
    }

    public void showOperateBtn(boolean z) {
        if (z) {
            this.l.setText("禁言");
            this.N.setText("禁言");
        } else {
            this.l.setText("解除禁言");
            this.N.setText("解除禁言");
        }
        this.n.setVisibility(0);
        this.o.setVisibility(0);
        this.l.setVisibility(0);
        this.m.setVisibility(0);
    }

    public void showContentView() {
        this.p.setVisibility(0);
    }

    public void hideLoadingView() {
        this.q.setVisibility(8);
    }

    public void showBottomView() {
        this.u.setVisibility(0);
        this.P.setVisibility(0);
    }

    public void hideBottomView() {
        this.u.setVisibility(8);
        this.P.setVisibility(8);
    }

    public void showErrorDialog(String str, String str2) {
        this.a.handleErrorResult(str, str2, this.a);
    }

    public void showLoginDialog() {
        this.a.showLoginDialog();
    }

    public void showErrorToast(int i) {
        this.a.showErrorToast(i);
    }

    public void requestStopMsg(String str) {
        this.a.stopMessage(str);
    }

    public void requestKickRoom(String str) {
        this.a.kickRoom(str);
    }

    public void requestAddAdmin(String str) {
        this.a.addAdmin(str);
    }

    public void requestRevokeAdmin(String str) {
        this.a.revokeAdmin(str);
    }

    public void requestAddManager(String str) {
        this.a.addManager(str);
    }

    public void requestRevokeManager(String str) {
        this.a.revokeManager(str);
    }

    public void dismiss() {
        super.dismiss();
        this.t.relese();
    }

    public void requestRecoverMsg(String str) {
        this.a.recoverMessage(str);
    }

    public void showStarLevelView() {
        this.F.setVisibility(0);
    }

    public void hideStarLevelView() {
        this.F.setVisibility(8);
    }

    public void showCoinLevelView() {
        this.G.setVisibility(0);
    }

    public void hideCoinLevelView() {
        this.G.setVisibility(8);
    }

    public void showBottomView3() {
        this.v.setVisibility(0);
        this.Q.setVisibility(0);
    }

    public void hideBottomView3() {
        this.v.setVisibility(8);
        this.Q.setVisibility(8);
    }

    public void showBottom2View() {
        this.K.setVisibility(0);
        this.R.setVisibility(0);
        if (this.a.isSuperGirlRoom().booleanValue()) {
            hideGiftView();
            hidePrivateChatView();
        }
    }

    public void hideBottom2View() {
        this.K.setVisibility(8);
        this.R.setVisibility(8);
    }

    public void hideAdminView() {
        showAdminView(3);
    }

    public void showAdminView(int i) {
        if (i == 1 || i == 2) {
            this.I.setVisibility(0);
        } else {
            this.I.setVisibility(8);
        }
    }

    public void hideManagerView() {
        this.J.setVisibility(8);
        this.H.setVisibility(8);
    }

    public void showGiftView() {
        this.S.setVisibility(0);
        this.T.setVisibility(0);
    }

    public void hideGiftView() {
        this.S.setVisibility(8);
        this.T.setVisibility(8);
    }

    public void hidePrivateChatView() {
        this.U.setVisibility(8);
        this.V.setVisibility(8);
    }

    public void showMutePopupView(int i) {
        if (i == 1) {
            this.N.setVisibility(8);
            this.O.setVisibility(8);
            this.L.setVisibility(8);
            this.M.setVisibility(8);
            this.l.setVisibility(0);
            this.m.setVisibility(0);
            this.n.setVisibility(0);
            this.o.setVisibility(0);
        } else if (i == 2) {
            this.N.setVisibility(0);
            this.O.setVisibility(0);
            this.L.setVisibility(0);
            this.M.setVisibility(0);
            this.l.setVisibility(8);
            this.m.setVisibility(8);
            this.n.setVisibility(8);
            this.o.setVisibility(8);
        } else {
            this.N.setVisibility(8);
            this.O.setVisibility(8);
            this.L.setVisibility(8);
            this.M.setVisibility(8);
            this.l.setVisibility(8);
            this.m.setVisibility(8);
            this.n.setVisibility(8);
            this.o.setVisibility(8);
        }
    }

    public void hideMutePopupView() {
        showMutePopupView(3);
    }

    public void showManagerView() {
        this.J.setVisibility(0);
        this.H.setVisibility(0);
    }

    public void setLeftBtmBg(View view) {
        view.setBackgroundResource(R.drawable.userinfo_dialog_btn_left_selecter);
    }

    public void setRightBtmBg(View view) {
        view.setBackgroundResource(R.drawable.userinfo_dialog_btn_right_selecter);
    }

    public void setMiddleBtmBg(View view) {
        view.setBackgroundResource(R.drawable.userinfo_dialog_btn_middle_selecter);
    }

    public void showMyself() {
        hideBottomView();
        hideBottom2View();
        hideBottomView3();
        hideLoadingView();
        hideStarLevelView();
        showContentView();
    }

    public void showAnchor(UserInfoBean userInfoBean) {
        hideBottomView();
        showBottom2View();
        showFollowState(userInfoBean.getIsfollow().equals("1"));
        hideManagerView();
        hideMutePopupView();
        hideAdminView();
        hideCoinLevelView();
        showStarLevelView();
        hideGiftView();
    }

    public void showEveryoneByGeneral(UserInfoBean userInfoBean) {
        hideBottomView();
        showBottom2View();
        showFollowState(userInfoBean.getIsfollow().equals("1"));
        hideManagerView();
    }

    public void showEveryoneByManager(UserInfoBean userInfoBean) {
        showBottomView();
        showBottom2View();
        showFollowState(userInfoBean.getIsfollow().equals("1"));
        showAdminView(2);
        showMutePopupView(1);
        hideManagerView();
        c(userInfoBean.isAdmin());
        b(userInfoBean.isGag());
    }

    public void showEveryoneByAdmin(UserInfoBean userInfoBean) {
        showBottomView();
        showBottom2View();
        showFollowState(userInfoBean.getIsfollow().equals("1"));
        showMutePopupView(1);
        hideManagerView();
        hideAdminView();
        b(userInfoBean.isGag());
    }

    public void showManagerByAnchor(UserInfoBean userInfoBean) {
        showFollowState(userInfoBean.getIsfollow().equals("1"));
        showManagerView();
        hideAdminView();
        showMutePopupView(1);
        a(userInfoBean.isManager());
        b(userInfoBean.isGag());
    }

    public void showEveryoneByAnchor(UserInfoBean userInfoBean) {
        showFollowState(userInfoBean.getIsfollow().equals("1"));
        showManagerView();
        showAdminView(1);
        showMutePopupView(1);
        c(userInfoBean.isAdmin());
        b(userInfoBean.isGag());
    }

    public void showManagerByManager(UserInfoBean userInfoBean) {
        showBottomView();
        showBottom2View();
        showFollowState(userInfoBean.getIsfollow().equals("1"));
        showMutePopupView(1);
        hideManagerView();
        hideAdminView();
        b(userInfoBean.isGag());
    }

    public void showAnchorYourself(UserInfoBean userInfoBean) {
        hideBottomView();
        hideBottom2View();
        hideLoadingView();
        hideCoinLevelView();
        showStarLevelView();
        showContentView();
    }

    public void setUserinfo(UserInfoBean userInfoBean) {
        this.Y = userInfoBean;
    }

    private void a(boolean z) {
        if (z) {
            this.H.setText("撤总管");
        } else {
            this.H.setText("升总管");
        }
    }

    private void b(boolean z) {
        if (z) {
            this.l.setText("解除禁言");
            this.N.setText("解除禁言");
            return;
        }
        this.l.setText("禁言");
        this.N.setText("禁言");
    }

    private void c(boolean z) {
        if (z) {
            this.I.setText("撤管理");
        } else {
            this.I.setText("升管理");
        }
    }
}
