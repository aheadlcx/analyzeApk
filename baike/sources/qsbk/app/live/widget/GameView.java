package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.FormatUtils;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.GameRole;
import qsbk.app.live.model.LiveGameBetMessage;
import qsbk.app.live.model.LiveGameDataMessage;
import qsbk.app.live.model.LiveGameMessage;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.ui.LiveBaseActivity;

public abstract class GameView extends FrameLayout implements OnClickListener {
    public static final int GAME_CATANDDOG = 3;
    public static final int GAME_FANFANLE = 4;
    public static final int GAME_GUESS = 6;
    public static final int GAME_GUESS_CON_HELP = 1997;
    public static final int GAME_GUESS_FHK_HELP = 1999;
    public static final int GAME_GUESS_OVER_HELP = 1998;
    public static final int GAME_HLNB = 1;
    public static final int GAME_ROLLTABLE = 5;
    public static final int GAME_YPDX = 2;
    public static final int TIPS_SHOW_MILLIS = 2000;
    public static final int UNKOWN_GAME = 0;
    protected long A;
    protected Comparator<GameRole> B;
    protected Comparator<GameRole> C;
    protected Dialog D;
    protected List<Integer> E = new ArrayList();
    protected List<Integer> F;
    protected View G;
    protected Runnable H = new de(this);
    protected ArrayList<Long> I = new ck(this);
    private FrameLayout J;
    private FrameLayout K;
    private FrameLayout L;
    private View M;
    private View N;
    private View O;
    private View P;
    private View Q;
    private Runnable R;
    private Runnable S;
    private long T;
    protected LiveGameMessage a;
    protected View b;
    protected View c;
    protected View d;
    protected TextView e;
    protected GameBetButton f;
    protected View g;
    protected View h;
    protected View i;
    protected View j;
    protected TextView k;
    protected CountDownTimer l;
    protected Handler m;
    public LiveBaseActivity mLiveBaseActivity;
    protected ImageView n;
    protected ImageView o;
    protected ImageView p;
    protected ImageView q;
    protected ImageView r;
    protected ImageView s;
    protected ImageView t;
    protected ImageView u;
    protected ImageView v;
    protected GameBetView w;
    protected GameBetView x;
    protected GameBetView y;
    protected TextView z;

    public interface State {
        public static final int BET = 1;
        public static final int INIT = 0;
        public static final int RESULT = 2;
    }

    protected abstract int getLayoutId();

    public void setGuessHistoryList(List<Integer> list) {
        this.F = list;
    }

    public void setGameId(long j) {
        this.T = j;
    }

    public long getGameId() {
        return this.T;
    }

    public GameView(Context context) {
        super(context);
        a(null, 0);
    }

    public GameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    public GameView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet, i);
    }

    protected void a(AttributeSet attributeSet, int i) {
        this.mLiveBaseActivity = (LiveBaseActivity) getContext();
        this.D = this.mLiveBaseActivity.mDialog;
        this.m = new Handler();
        this.G = View.inflate(getContext(), getLayoutId(), this);
        this.b = this.G.findViewById(R.id.game_container);
        this.G.findViewById(R.id.ll_game_balance).setOnClickListener(this);
        this.e = (TextView) this.G.findViewById(R.id.tv_game_balance);
        this.c = this.G.findViewById(R.id.ll_game_init);
        this.n = (ImageView) this.G.findViewById(R.id.iv_role_1);
        this.o = (ImageView) this.G.findViewById(R.id.iv_role_name_1);
        this.p = (ImageView) this.G.findViewById(R.id.iv_role_2);
        this.q = (ImageView) this.G.findViewById(R.id.iv_role_name_2);
        this.r = (ImageView) this.G.findViewById(R.id.iv_role_3);
        this.s = (ImageView) this.G.findViewById(R.id.iv_role_name_3);
        this.t = (ImageView) this.G.findViewById(R.id.bet_role_1);
        this.u = (ImageView) this.G.findViewById(R.id.bet_role_2);
        this.v = (ImageView) this.G.findViewById(R.id.bet_role_3);
        this.w = (GameBetView) this.G.findViewById(R.id.bet_view_1);
        this.x = (GameBetView) this.G.findViewById(R.id.bet_view_2);
        this.y = (GameBetView) this.G.findViewById(R.id.bet_view_3);
        this.w.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.y.setOnClickListener(this);
        this.d = this.G.findViewById(R.id.ll_game_bet);
        this.J = (FrameLayout) this.G.findViewById(R.id.fl_group_1);
        this.K = (FrameLayout) this.G.findViewById(R.id.fl_group_2);
        this.L = (FrameLayout) this.G.findViewById(R.id.fl_group_3);
        this.M = this.G.findViewById(R.id.mask_group_1);
        this.N = this.G.findViewById(R.id.mask_group_2);
        this.O = this.G.findViewById(R.id.mask_group_3);
        this.P = this.G.findViewById(R.id.divider);
        this.Q = this.G.findViewById(R.id.divider_bottom);
        this.f = (GameBetButton) this.G.findViewById(R.id.bet_btn);
        this.g = this.G.findViewById(R.id.btn_more);
        this.h = this.G.findViewById(R.id.btn_help);
        this.j = this.G.findViewById(R.id.btn_mvp);
        this.i = this.G.findViewById(R.id.btn_history);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        if (this.j != null) {
            this.j.setOnClickListener(this);
        }
        this.i.setOnClickListener(this);
        this.k = (TextView) this.G.findViewById(R.id.tv_count_down);
        this.z = (TextView) this.G.findViewById(R.id.tv_tips);
        this.c.setVisibility(4);
        this.d.setVisibility(4);
        this.k.setVisibility(4);
        this.z.setVisibility(4);
        if (!AppUtils.getInstance().getUserInfoProvider().isLogin() || this.mLiveBaseActivity.isAnchor()) {
            d();
        }
        this.B = new cj(this);
        this.C = new cv(this);
    }

    public void loadGameData(LiveGameMessage liveGameMessage) {
        if (!isSupportedGame(liveGameMessage.getGameId())) {
            setVisibility(4);
        } else if (this.mLiveBaseActivity != null) {
            int messageType = liveGameMessage.getMessageType();
            if (messageType != 44 && messageType != 49 && this.a == null) {
                return;
            }
            if (messageType == 44) {
                this.a = liveGameMessage;
                a(liveGameMessage);
            } else if (messageType == 49) {
                this.a = liveGameMessage;
                r5 = (LiveGameDataMessage) liveGameMessage;
                if (isValidRolesData(r5.getGameRoleBetData())) {
                    f(r5);
                    b();
                    messageType = r5.getGameStatus();
                    if (messageType == 0) {
                        c(r5);
                    } else if (messageType == 1) {
                        b(r5);
                    } else if (messageType == 2) {
                        d(r5);
                    }
                }
            } else if (messageType == 46) {
                updateBetData((LiveGameBetMessage) liveGameMessage);
            } else if (messageType == 42) {
                r5 = (LiveGameDataMessage) liveGameMessage;
                this.mLiveBaseActivity.updateBalance(r5.getBalance());
                e(r5);
            } else if (messageType == 45) {
                b(liveGameMessage);
            } else if (messageType == 47) {
                c(liveGameMessage);
            } else if (messageType == 43 && isContentVisible()) {
                showBestBetResult(liveGameMessage);
            }
        }
    }

    protected void d(LiveGameDataMessage liveGameDataMessage) {
        if (c(liveGameDataMessage.getGameRoleBetData())) {
            setBetEnable(false);
            d((LiveGameMessage) liveGameDataMessage);
            h();
            return;
        }
        d();
    }

    protected boolean c(List<GameRole> list) {
        return ((GameRole) list.get(0)).getResultGroup() != null && ((GameRole) list.get(0)).getResultGroup().size() > 0;
    }

    protected void b(LiveGameDataMessage liveGameDataMessage) {
        this.c.setVisibility(4);
        this.d.setVisibility(0);
        h();
        long countDownDuration = liveGameDataMessage.getCountDownDuration();
        if (countDownDuration >= 2) {
            a(R.string.live_game_start);
        } else {
            this.z.setVisibility(4);
        }
        if (countDownDuration > 0) {
            setBetEnable(true);
            a(countDownDuration);
        } else {
            setBetEnable(false);
            this.k.setVisibility(4);
            a(R.string.live_game_result, false);
        }
        e(liveGameDataMessage);
    }

    protected void c(LiveGameDataMessage liveGameDataMessage) {
        if (this.mLiveBaseActivity.isAnchor()) {
            if (this.c.getVisibility() != 0) {
                d();
            }
            a(liveGameDataMessage.getCountDownDuration());
            return;
        }
        d();
    }

    public boolean isValidRolesData(List<GameRole> list) {
        return list != null && list.size() == 3;
    }

    public void updateBetData(LiveGameBetMessage liveGameBetMessage) {
        GameRole gameRole = liveGameBetMessage.getGameRole();
        GameBetView roleBetView = getRoleBetView(gameRole.getRoleId());
        if (roleBetView != null) {
            roleBetView.loadData(gameRole);
            if (this.mLiveBaseActivity.isMe(liveGameBetMessage)) {
                b((View) roleBetView);
            } else if (!this.mLiveBaseActivity.isMessageOverloadOrLowDevice() && isContentVisible()) {
                a(liveGameBetMessage, roleBetView);
            }
        }
    }

    protected void b(long j) {
        a(j, false);
    }

    protected void a(long j, boolean z) {
        int color = getResources().getColor(R.color.black_30_percent_transparent);
        b(this.M, 4);
        b(this.N, 4);
        b(this.O, 4);
        c(this.J, 0);
        c(this.K, 0);
        c(this.L, 0);
        a(this.t, true);
        a(this.u, true);
        a(this.v, true);
        if (j == this.w.getRoleId()) {
            b(this.t, true);
            c(this.J, color);
            b(this.N, 0);
            b(this.O, 0);
        } else if (j == this.y.getRoleId()) {
            b(this.v, true);
            c(this.L, color);
            b(this.M, 0);
            b(this.N, 0);
        } else if (j == this.x.getRoleId()) {
            b(this.u, true);
            c(this.K, color);
            b(this.M, 0);
            b(this.O, 0);
        }
        if (z) {
            b(this.u, true);
            c(this.K, color);
            b(this.N, 4);
        }
        b(this.P, 0);
        b(this.Q, 0);
    }

    public void updateBalance(long j) {
        this.e.setText(FormatUtils.formatBalance(j));
    }

    protected void d() {
        this.c.setVisibility(0);
        this.d.setVisibility(4);
        k();
        this.k.setVisibility(4);
        a(R.string.live_game_ready, true);
        setBetEnable(false);
    }

    protected void c(LiveGameMessage liveGameMessage) {
        LiveGameBetMessage liveGameBetMessage = (LiveGameBetMessage) liveGameMessage;
        if (this.mLiveBaseActivity.isAnchor(liveGameMessage)) {
            a(liveGameBetMessage.getAnchorResult());
        } else if (liveGameBetMessage.isWin()) {
            long winNum = liveGameBetMessage.getWinNum();
            this.z.setVisibility(4);
            if (this.D != null && this.D.isShowing()) {
                this.D.dismiss();
            }
            this.D = new GameWinDialog(this.mLiveBaseActivity, getGameId(), winNum, this.E);
            this.D.show();
        } else {
            a(liveGameBetMessage.getResult());
        }
        if (liveGameBetMessage.isWin()) {
            this.mLiveBaseActivity.updateBalance(liveGameBetMessage.getBalance());
        }
    }

    protected void a(String str) {
        a(str, null, 3000);
    }

    protected void f(LiveGameDataMessage liveGameDataMessage) {
        a(liveGameDataMessage.getGameRoleBetData());
    }

    protected void a(List<GameRole> list) {
        for (int i = 0; i < list.size(); i++) {
            GameRole gameRole = (GameRole) list.get(i);
            long roleId = gameRole.getRoleId();
            if (roleId == 1) {
                this.w.initData(gameRole);
            } else if (roleId == 2) {
                this.x.initData(gameRole);
            } else if (roleId == 3) {
                this.y.initData(gameRole);
            }
        }
    }

    public String getRoleName(long j) {
        return null;
    }

    public GameBetView getRoleBetView(long j) {
        if (j == this.w.getRoleId()) {
            return this.w;
        }
        if (j == this.x.getRoleId()) {
            return this.x;
        }
        if (j == this.y.getRoleId()) {
            return this.y;
        }
        return null;
    }

    protected void e(LiveGameDataMessage liveGameDataMessage) {
        List gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        int i = 0;
        while (gameRoleBetData != null && i < gameRoleBetData.size()) {
            GameRole gameRole = (GameRole) gameRoleBetData.get(i);
            GameBetView roleBetView = getRoleBetView(gameRole.getRoleId());
            if (roleBetView != null) {
                roleBetView.loadMeData(gameRole);
            }
            i++;
        }
    }

    protected void d(LiveGameMessage liveGameMessage) {
        long j = 3;
        cancelCountDownTimer();
        this.c.setVisibility(4);
        this.d.setVisibility(0);
        this.k.setVisibility(4);
        setBetEnable(false);
        LiveGameDataMessage liveGameDataMessage = (LiveGameDataMessage) liveGameMessage;
        List gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        if (isValidRolesData(gameRoleBetData)) {
            b(gameRoleBetData);
        }
        long countDownDuration = liveGameDataMessage.getCountDownDuration();
        if (countDownDuration <= 3) {
            if (countDownDuration < 0) {
                j = 0;
            } else {
                j = countDownDuration;
            }
        }
        this.m.removeCallbacks(this.H);
        this.m.postDelayed(this.H, j * 1000);
    }

    protected void b(List<GameRole> list) {
        Collections.sort(list, this.B);
        for (int i = 0; i < list.size(); i++) {
            GameRole gameRole = (GameRole) list.get(i);
            GameBetView roleBetView = getRoleBetView(gameRole.getRoleId());
            if (roleBetView != null) {
                roleBetView.loadMeData(gameRole);
            }
        }
    }

    protected void b(LiveGameMessage liveGameMessage) {
        cancelCountDownTimer();
        this.c.setVisibility(4);
        this.d.setVisibility(0);
        if (this.k.getVisibility() == 0) {
            j();
        }
        setBetEnable(false);
        Runnable dcVar = new dc(this, (LiveGameDataMessage) liveGameMessage);
        if (this.z.isEnabled()) {
            a(R.string.live_game_result, dcVar);
        } else {
            a(dcVar);
        }
    }

    protected void a(LiveGameDataMessage liveGameDataMessage) {
    }

    protected void setBetEnable(boolean z) {
        this.w.setEnabled(z);
        this.x.setEnabled(z);
        this.y.setEnabled(z);
        this.f.setButtonEnable(z);
    }

    public static boolean isSupportedGame(long j) {
        return j == 1 || j == 2 || j == 3 || j == 5 || j == 4 || j == 6;
    }

    protected void a(long j) {
        this.l = new dd(this, (1000 * j) + 500, 500);
        this.l.start();
    }

    public void onClick(View view) {
        if (this.mLiveBaseActivity != null && !this.mLiveBaseActivity.forwardIfNotLogin()) {
            this.mLiveBaseActivity.disShowBottomFollowTipsDialog();
            if (view.getId() == R.id.btn_more) {
                e();
            } else if (view.getId() == R.id.btn_help) {
                if (this.D != null && this.D.isShowing()) {
                    this.D.dismiss();
                }
                this.D = new GameHelpDialog(this.mLiveBaseActivity, getGameId());
                this.D.show();
            } else if (view.getId() == R.id.btn_history) {
                if (this.D != null && this.D.isShowing()) {
                    this.D.dismiss();
                }
                this.D = new GameHistoryDialog(this.mLiveBaseActivity, getGameId(), this.mLiveBaseActivity.getRoomId());
                this.D.show();
            } else if (view.getId() == R.id.btn_mvp) {
                if (this.D != null && this.D.isShowing()) {
                    this.D.dismiss();
                }
                this.D = new GameMVPDialog(this.mLiveBaseActivity, getGameId());
                this.D.show();
            } else if (view.getId() == R.id.bet_view_1 || view.getId() == R.id.bet_view_2 || view.getId() == R.id.bet_view_3) {
                c(view);
            } else if (view.getId() == R.id.ll_game_balance) {
                AppUtils.getInstance().getUserInfoProvider().toPay(this.mLiveBaseActivity, 103);
            } else if (view.getId() == R.id.iv_help) {
                if (this.D != null && this.D.isShowing()) {
                    this.D.dismiss();
                }
                this.D = new GameHelpDialog(this.mLiveBaseActivity, getGameId());
                this.D.show();
            }
        }
    }

    protected void e() {
        if (this.i.getVisibility() == 0) {
            this.h.setVisibility(8);
            this.i.setVisibility(8);
            if (this.j != null) {
                this.j.setVisibility(8);
            }
            this.g.setSelected(false);
            return;
        }
        this.g.setSelected(true);
        this.i.setVisibility(0);
        e(this.i);
        this.m.postDelayed(new df(this), 100);
        this.m.postDelayed(new dg(this), 200);
    }

    private void e(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) WindowUtils.dp2Px(50), (float) (-WindowUtils.dp2Px(3))});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) (-WindowUtils.dp2Px(3)), (float) WindowUtils.dp2Px(2)});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) WindowUtils.dp2Px(2), 0.0f});
        ofFloat.setDuration(70);
        ofFloat2.setDuration(15);
        ofFloat3.setDuration(15);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(new Animator[]{ofFloat, ofFloat2, ofFloat3});
        animatorSet.start();
    }

    public void showBestBetResult(LiveGameMessage liveGameMessage) {
        List bestBetResult = ((LiveGameDataMessage) liveGameMessage).getBestBetResult();
        if (bestBetResult != null && bestBetResult.size() > 0) {
            if (this.D != null && this.D.isShowing()) {
                this.D.dismiss();
            }
            this.D = new GameResultDialog(this.mLiveBaseActivity, bestBetResult, (int) getGameId());
            this.D.show();
            this.D.setOnDismissListener(new dh(this));
            this.mLiveBaseActivity.dim();
        }
    }

    protected void c(View view) {
        long betOptionId = this.f.getBetOptionId();
        long roleId = ((GameBetView) view).getRoleId();
        if (this.a != null) {
            this.mLiveBaseActivity.sendLiveMessageAndRefreshUI(LiveMessage.createGameBetMessage(AppUtils.getInstance().getUserInfoProvider().getUserId(), this.a.getGameId(), this.a.getGameRoundId(), roleId, betOptionId));
        }
    }

    protected void b(View view) {
        a(view, null);
    }

    protected void a(View view, Runnable runnable) {
        a(view, -WindowUtils.dp2Px(52), runnable);
    }

    protected void a(View view, int i) {
        a(view, i, null);
    }

    protected void a(View view, int i, Runnable runnable) {
        a(view, i, -WindowUtils.dp2Px(8), runnable);
    }

    protected void a(View view, int i, int i2, Runnable runnable) {
        View selectedButton = this.f.getSelectedButton();
        if (selectedButton != null) {
            int[] iArr = new int[2];
            selectedButton.getLocationOnScreen(iArr);
            selectedButton = new ImageView(getContext());
            selectedButton.setScaleType(ScaleType.FIT_XY);
            selectedButton.setImageResource(R.drawable.live_game_bet_love);
            LayoutParams layoutParams = new FrameLayout.LayoutParams(WindowUtils.dp2Px(17), WindowUtils.dp2Px(14));
            layoutParams.leftMargin = iArr[0] + WindowUtils.dp2Px(11);
            layoutParams.bottomMargin = WindowUtils.dp2Px(19);
            layoutParams.gravity = 80;
            selectedButton.setLayoutParams(layoutParams);
            addView(selectedButton);
            view.getLocationOnScreen(new int[2]);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(selectedButton, View.TRANSLATION_Y, new float[]{0.0f, (float) (-WindowUtils.dp2Px(15))});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(selectedButton, View.TRANSLATION_Y, new float[]{(float) (-WindowUtils.dp2Px(15)), (float) i});
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(selectedButton, View.TRANSLATION_X, new float[]{0.0f, (float) ((r2[0] - iArr[0]) + i2)});
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(selectedButton, View.SCALE_X, new float[]{1.0f, 0.6f});
            ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(selectedButton, View.SCALE_Y, new float[]{1.0f, 0.6f});
            new AnimatorSet().playTogether(new Animator[]{ofFloat2, ofFloat3});
            new AnimatorSet().playTogether(new Animator[]{ofFloat4, ofFloat5});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(new Animator[]{ofFloat, r6, r1});
            animatorSet.setDuration(250);
            animatorSet.setInterpolator(new AccelerateInterpolator());
            animatorSet.addListener(new di(this, selectedButton, runnable));
            animatorSet.start();
        }
    }

    protected void a(LiveGameBetMessage liveGameBetMessage, GameBetView gameBetView) {
        a(liveGameBetMessage, gameBetView, WindowUtils.dp2Px(128));
    }

    protected void a(LiveGameBetMessage liveGameBetMessage, GameBetView gameBetView, int i) {
        int roleId = ((int) gameBetView.getRoleId()) - 1;
        if (roleId >= 0 && roleId < this.I.size() && System.currentTimeMillis() - ((Long) this.I.get(roleId)).longValue() >= 125) {
            this.I.set(roleId, Long.valueOf(System.currentTimeMillis()));
            String userAvatar = liveGameBetMessage.getUserAvatar();
            int[] iArr = new int[2];
            gameBetView.getLocationOnScreen(iArr);
            View simpleDraweeView = new SimpleDraweeView(getContext());
            LayoutParams layoutParams = new FrameLayout.LayoutParams(WindowUtils.dp2Px(25), WindowUtils.dp2Px(25));
            roleId = iArr[0] - WindowUtils.dp2Px(35);
            layoutParams.leftMargin = roleId;
            layoutParams.topMargin = i;
            simpleDraweeView.setLayoutParams(layoutParams);
            AppUtils.getInstance().getImageProvider().loadAvatar(simpleDraweeView, userAvatar);
            addView(simpleDraweeView);
            ImageView imageView = new ImageView(getContext());
            layoutParams = new FrameLayout.LayoutParams(WindowUtils.dp2Px(11), WindowUtils.dp2Px(9));
            layoutParams.leftMargin = roleId + WindowUtils.dp2Px(7);
            layoutParams.topMargin = i - WindowUtils.dp2Px(50);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(R.drawable.live_game_bet_love);
            imageView.setScaleType(ScaleType.FIT_XY);
            roleId = -WindowUtils.dp2Px(60);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(simpleDraweeView, View.TRANSLATION_Y, new float[]{0.0f, (float) roleId});
            ofFloat.setDuration(500);
            ofFloat.setInterpolator(new DecelerateInterpolator());
            ObjectAnimator.ofFloat(simpleDraweeView, View.ALPHA, new float[]{1.0f, 0.0f}).setDuration(500);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(new Animator[]{ofFloat, r3});
            animatorSet.addListener(new cl(this, simpleDraweeView));
            animatorSet.start();
            ofFloat = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, new float[]{0.0f, (float) WindowUtils.dp2Px(34)});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, new float[]{0.0f, (float) WindowUtils.dp2Px(11)});
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.setInterpolator(new DecelerateInterpolator());
            animatorSet2.setDuration(250);
            animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet2.addListener(new cm(this, imageView));
            this.m.postDelayed(new cn(this, imageView, animatorSet2), 500);
        }
    }

    public boolean isVisible() {
        return getVisibility() == 0;
    }

    public void showContent() {
        this.b.setVisibility(0);
    }

    public void hideContent() {
        this.b.setVisibility(8);
    }

    public boolean isContentVisible() {
        return this.b.getVisibility() == 0;
    }

    protected void a(LiveGameMessage liveGameMessage) {
        LiveGameDataMessage liveGameDataMessage = (LiveGameDataMessage) liveGameMessage;
        f(liveGameDataMessage);
        this.A = liveGameDataMessage.getCountDownDuration() - 2;
        b();
        this.c.setVisibility(0);
        this.d.setVisibility(4);
        f();
    }

    protected void f() {
        a();
        this.m.postDelayed(new co(this), 320);
        this.m.postDelayed(new cp(this), 400);
    }

    protected void a() {
    }

    protected void b() {
        cancelCountDownTimer();
        this.m.removeCallbacks(this.H);
        l();
        a(this.t, false);
        a(this.u, false);
        a(this.v, false);
        b(this.t, false);
        b(this.u, false);
        b(this.v, false);
        d(this.n);
        d(this.p);
        d(this.r);
        d(this.o);
        d(this.q);
        d(this.s);
        d(this.t);
        d(this.u);
        d(this.v);
        d(this.w);
        d(this.x);
        d(this.y);
        d(this.k);
        b(this.M, 4);
        b(this.N, 4);
        b(this.O, 4);
        c(this.J, 0);
        c(this.K, 0);
        c(this.L, 0);
        b(this.P, 4);
        b(this.Q, 4);
    }

    private void a(ImageView imageView, boolean z) {
        if (imageView != null) {
            imageView.setActivated(z);
        }
    }

    private void b(ImageView imageView, boolean z) {
        if (imageView != null) {
            imageView.setSelected(z);
        }
    }

    private void b(View view, int i) {
        if (view != null) {
            view.setVisibility(i);
        }
    }

    private void c(View view, int i) {
        if (view != null) {
            view.setBackgroundColor(i);
        }
    }

    protected void d(View view) {
        if (view != null) {
            view.setPivotX((float) (view.getWidth() / 2));
            view.setPivotY((float) (view.getHeight() / 2));
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
        }
    }

    protected void a(ImageView imageView, int i, float f) {
        if (imageView != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, View.SCALE_X, new float[]{1.0f, f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, new float[]{1.0f, f});
            AnimatorSet animatorSet = new AnimatorSet();
            imageView.setPivotX((float) i);
            imageView.setPivotY((float) imageView.getHeight());
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet.setDuration(320);
            animatorSet.start();
        }
    }

    protected void a(ImageView imageView, GameBetView gameBetView, View view) {
        if (imageView != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, View.SCALE_X, new float[]{1.0f, 0.0f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, new float[]{1.0f, 0.0f});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet.setDuration(200);
            animatorSet.addListener(new cr(this, gameBetView, view));
            animatorSet.start();
        }
    }

    protected void a(GameBetView gameBetView) {
        if (gameBetView != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(gameBetView, View.SCALE_X, new float[]{0.0f, 1.0f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(gameBetView, View.SCALE_Y, new float[]{0.0f, 1.0f});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet.setDuration(120);
            animatorSet.start();
        }
    }

    protected void a(View view) {
    }

    protected void a(int i) {
        a(i, null);
    }

    protected void a(int i, boolean z) {
        a(i, null, z);
    }

    protected void a(String str, boolean z) {
        a(str, null, z);
    }

    protected void a(int i, Runnable runnable) {
        a(i, runnable, true);
    }

    protected void a(int i, Runnable runnable, long j) {
        a(i, runnable, true, j);
    }

    private void a(int i, Runnable runnable, boolean z) {
        a(i, runnable, z, 2000);
    }

    protected void a(int i, Runnable runnable, boolean z, long j) {
        a(getResources().getString(i), runnable, z, j);
    }

    protected void a(String str, Runnable runnable) {
        a(str, runnable, true);
    }

    protected void a(String str, Runnable runnable, long j) {
        a(str, runnable, true, j);
    }

    protected void a(String str, Runnable runnable, boolean z) {
        a(str, runnable, z, 2000);
    }

    protected void a(String str, Runnable runnable, boolean z, long j) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.z, View.SCALE_X, new float[]{0.0f, 1.1f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.z, View.SCALE_Y, new float[]{0.0f, 1.1f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setDuration(200);
        ofFloat = ObjectAnimator.ofFloat(this.z, View.SCALE_X, new float[]{1.1f, 0.9f});
        ofFloat2 = ObjectAnimator.ofFloat(this.z, View.SCALE_Y, new float[]{1.1f, 0.9f});
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet2.setDuration(200);
        ofFloat = ObjectAnimator.ofFloat(this.z, View.SCALE_X, new float[]{0.9f, 1.0f});
        ofFloat2 = ObjectAnimator.ofFloat(this.z, View.SCALE_Y, new float[]{0.9f, 1.0f});
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet3.setDuration(200);
        long j2 = j - 600;
        if (z) {
            j2 -= 200;
        }
        ObjectAnimator.ofFloat(this.z, View.SCALE_X, new float[]{1.0f, 1.0f}).setDuration(j2);
        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.playSequentially(new Animator[]{animatorSet, animatorSet2, animatorSet3, r5});
        animatorSet4.addListener(new cs(this, z, runnable));
        animatorSet4.start();
        this.z.setText(str);
        this.z.setVisibility(0);
        this.z.setEnabled(false);
    }

    private void i() {
        a(null);
    }

    private void a(Runnable runnable) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.z, View.SCALE_X, new float[]{1.0f, 0.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.z, View.SCALE_Y, new float[]{1.0f, 0.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setDuration(200);
        animatorSet.addListener(new ct(this, runnable));
        animatorSet.start();
    }

    protected void g() {
        this.k.setVisibility(0);
        this.k.setText(String.valueOf(this.A));
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.k, View.SCALE_X, new float[]{0.0f, 1.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.k, View.SCALE_Y, new float[]{0.0f, 1.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setDuration(200);
        animatorSet.addListener(new cu(this));
        animatorSet.start();
    }

    private void j() {
        if (this.k.isEnabled()) {
            this.k.setEnabled(false);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.k, View.SCALE_X, new float[]{1.0f, 0.0f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.k, View.SCALE_Y, new float[]{1.0f, 0.0f});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet.setDuration(200);
            animatorSet.addListener(new cw(this));
            animatorSet.start();
        }
    }

    public void release() {
        this.D = null;
        this.mLiveBaseActivity = null;
        cancelCountDownTimer();
        this.m.removeCallbacksAndMessages(null);
        removeAllViews();
    }

    public void cancelCountDownTimer() {
        if (this.l != null) {
            this.l.cancel();
            this.l = null;
        }
        if (this.k != null) {
            this.k.setVisibility(4);
        }
    }

    protected void c() {
        l();
        a(this.w, this.o);
        a(this.x, this.q);
        a(this.y, this.s);
        this.m.postDelayed(new cx(this), 320);
    }

    private void a(GameBetView gameBetView, ImageView imageView) {
        if (gameBetView != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(gameBetView, View.SCALE_X, new float[]{1.0f, 0.0f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(gameBetView, View.SCALE_Y, new float[]{1.0f, 0.0f});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet.setDuration(200);
            animatorSet.addListener(new cy(this, imageView));
            animatorSet.start();
        }
    }

    protected void b(ImageView imageView, int i, float f) {
        if (imageView != null) {
            imageView.setPivotX((float) i);
            imageView.setPivotY((float) imageView.getHeight());
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, View.SCALE_X, new float[]{1.0f, f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, new float[]{1.0f, f});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet.setDuration(320);
            animatorSet.addListener(new cz(this));
            animatorSet.start();
        }
    }

    private void a(ImageView imageView) {
        if (imageView != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, View.SCALE_X, new float[]{0.0f, 1.0f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, new float[]{0.0f, 1.0f});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet.setDuration(120);
            animatorSet.start();
        }
    }

    private void k() {
        if (this.mLiveBaseActivity == null || !this.mLiveBaseActivity.isMessageOverloadOrLowDevice()) {
            if (this.R == null) {
                this.R = new da(this);
            }
            this.m.post(this.R);
        }
    }

    protected void h() {
        if (this.mLiveBaseActivity == null || !this.mLiveBaseActivity.isMessageOverloadOrLowDevice()) {
            if (this.S == null) {
                this.S = new db(this);
            }
            this.m.post(this.S);
        }
    }

    private void b(ImageView imageView) {
        if (imageView != null) {
            imageView.setPivotY((float) imageView.getHeight());
            imageView.setPivotX((float) (imageView.getWidth() / 2));
            c(imageView);
        }
    }

    private void c(ImageView imageView) {
        if (imageView != null) {
            ObjectAnimator.ofFloat(imageView, View.SCALE_Y, new float[]{1.0f, 1.02f}).setDuration(800);
            ObjectAnimator.ofFloat(imageView, View.SCALE_Y, new float[]{1.02f, 1.0f}).setDuration(800);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(new Animator[]{r0, r1});
            animatorSet.start();
        }
    }

    private void l() {
        this.m.removeCallbacks(this.R);
        this.m.removeCallbacks(this.S);
    }

    public void reset() {
    }
}
