package qsbk.app.live.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
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

public class RollTableGameView extends GameView {
    private RollTableView J;
    private TextView K;
    private TextView L;
    private TextView M;
    private TextView N;
    private TextView O;
    private TextView P;
    private ImageView Q;
    private ImageView R;
    private ImageView S;
    private ImageView T;
    private ImageView U;
    private ImageView V;
    private FrameLayout W;
    private FrameLayout aa;
    private FrameLayout ab;
    private FrameLayout ac;
    private FrameLayout ad;
    private FrameLayout ae;
    private TextView af;
    private TextView ag;
    private TextView ah;
    private TextView ai;
    private TextView aj;
    private TextView ak;

    public RollTableGameView(Context context) {
        super(context, null);
    }

    public RollTableGameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public RollTableGameView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected int getLayoutId() {
        return R.layout.game_rolltable;
    }

    protected void a(AttributeSet attributeSet, int i) {
        this.mLiveBaseActivity = (LiveBaseActivity) getContext();
        this.D = this.mLiveBaseActivity.mDialog;
        this.m = new Handler();
        this.G = View.inflate(getContext(), getLayoutId(), this);
        this.b = this.G.findViewById(R.id.game_container);
        this.J = (RollTableView) this.G.findViewById(R.id.roll_table);
        this.K = (TextView) this.G.findViewById(R.id.tv_bet2);
        this.L = (TextView) this.G.findViewById(R.id.tv_bet4);
        this.M = (TextView) this.G.findViewById(R.id.tv_bet7);
        this.N = (TextView) this.G.findViewById(R.id.tv_bet12);
        this.O = (TextView) this.G.findViewById(R.id.tv_bet_wing);
        this.P = (TextView) this.G.findViewById(R.id.tv_bet_diamond);
        this.Q = (ImageView) this.G.findViewById(R.id.iv_bet2);
        this.R = (ImageView) this.G.findViewById(R.id.iv_bet4);
        this.S = (ImageView) this.G.findViewById(R.id.iv_bet7);
        this.T = (ImageView) this.G.findViewById(R.id.iv_bet12);
        this.U = (ImageView) this.G.findViewById(R.id.iv_bet_wing);
        this.V = (ImageView) this.G.findViewById(R.id.iv_bet_diamond);
        this.Q.setOnClickListener(this);
        this.R.setOnClickListener(this);
        this.S.setOnClickListener(this);
        this.T.setOnClickListener(this);
        this.U.setOnClickListener(this);
        this.V.setOnClickListener(this);
        this.W = (FrameLayout) this.G.findViewById(R.id.fl_bet_2);
        this.aa = (FrameLayout) this.G.findViewById(R.id.fl_bet_4);
        this.ab = (FrameLayout) this.G.findViewById(R.id.fl_bet_7);
        this.ac = (FrameLayout) this.G.findViewById(R.id.fl_bet_12);
        this.ad = (FrameLayout) this.G.findViewById(R.id.fl_bet_wing);
        this.ae = (FrameLayout) this.G.findViewById(R.id.fl_bet_diamond);
        this.af = (TextView) this.G.findViewById(R.id.btn_bet_num2);
        this.ag = (TextView) this.G.findViewById(R.id.btn_bet_num4);
        this.ah = (TextView) this.G.findViewById(R.id.btn_bet_num7);
        this.ai = (TextView) this.G.findViewById(R.id.btn_bet_num12);
        this.aj = (TextView) this.G.findViewById(R.id.btn_bet_num_wing);
        this.ak = (TextView) this.G.findViewById(R.id.btn_bet_num_diamond);
        this.W.setVisibility(4);
        this.aa.setVisibility(4);
        this.ab.setVisibility(4);
        this.ac.setVisibility(4);
        this.ad.setVisibility(4);
        this.ae.setVisibility(4);
        this.f = (GameBetButton) this.G.findViewById(R.id.bet_btn);
        this.e = (TextView) this.G.findViewById(R.id.tv_game_balance);
        d();
        this.g = this.G.findViewById(R.id.btn_more);
        this.h = this.G.findViewById(R.id.btn_help);
        this.i = this.G.findViewById(R.id.btn_history);
        this.j = this.G.findViewById(R.id.btn_mvp);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        findViewById(R.id.ll_game_balance).setOnClickListener(this);
        this.z = (TextView) findViewById(R.id.tv_tips);
    }

    public void loadGameData(LiveGameMessage liveGameMessage) {
        if (!GameView.isSupportedGame(liveGameMessage.getGameId())) {
            setVisibility(4);
        } else if (this.mLiveBaseActivity != null) {
            int messageType = liveGameMessage.getMessageType();
            if (messageType != 44 && messageType != 49 && this.a == null) {
                return;
            }
            if (messageType == 44) {
                this.a = liveGameMessage;
                e(liveGameMessage);
            } else if (messageType == 49) {
                this.a = liveGameMessage;
                f(liveGameMessage);
            } else if (messageType == 46) {
                a((LiveGameBetMessage) liveGameMessage);
            } else if (messageType == 42) {
                LiveGameDataMessage liveGameDataMessage = (LiveGameDataMessage) liveGameMessage;
                this.mLiveBaseActivity.updateBalance(liveGameDataMessage.getBalance());
                e(liveGameDataMessage);
            } else if (messageType == 45) {
                b(liveGameMessage);
            } else if (messageType == 47) {
                c(liveGameMessage);
            } else if (messageType == 43 && isContentVisible()) {
                showBestBetResult(liveGameMessage);
            }
        }
    }

    protected void b(LiveGameMessage liveGameMessage) {
        a((LiveGameDataMessage) liveGameMessage, false);
        this.m.postDelayed(new ih(this), 10000);
    }

    protected void c(LiveGameMessage liveGameMessage) {
        LiveGameBetMessage liveGameBetMessage = (LiveGameBetMessage) liveGameMessage;
        if (this.mLiveBaseActivity.isAnchor(liveGameMessage)) {
            a(liveGameBetMessage.getAnchorResult(), true);
        } else if (liveGameBetMessage.isWin()) {
            long winNum = liveGameBetMessage.getWinNum();
            if (this.D != null && this.D.isShowing()) {
                this.D.dismiss();
            }
            this.D = new GameWinDialog(this.mLiveBaseActivity, getGameId(), winNum, this.E);
            this.D.show();
        } else if (this.mLiveBaseActivity.isAnchor(liveGameMessage)) {
            a(R.string.game_fail_anchor, true);
        } else {
            a(R.string.game_fail_player, true);
        }
        if (liveGameBetMessage.isWin()) {
            this.mLiveBaseActivity.updateBalance(liveGameBetMessage.getBalance());
        }
    }

    public void showBestBetResult(LiveGameMessage liveGameMessage) {
        List bestBetResult = ((LiveGameDataMessage) liveGameMessage).getBestBetResult();
        if (bestBetResult != null && bestBetResult.size() > 0) {
            if (this.D != null && this.D.isShowing()) {
                this.D.dismiss();
            }
            this.D = new GameResultDialog(this.mLiveBaseActivity, bestBetResult, (int) getGameId());
            this.D.show();
            this.D.setOnDismissListener(new ii(this));
            this.mLiveBaseActivity.dim();
        }
    }

    protected void e(LiveGameDataMessage liveGameDataMessage) {
        List gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        int i = 0;
        while (gameRoleBetData != null && i < gameRoleBetData.size()) {
            GameRole gameRole = (GameRole) gameRoleBetData.get(i);
            View roleView = getRoleView(gameRole.getRoleId());
            if (roleView != null) {
                roleView.setVisibility(0);
                c(gameRole.getRoleId()).setText(FormatUtils.formatCoupon(gameRole.getMeBet()));
            }
            i++;
        }
    }

    private void e(LiveGameMessage liveGameMessage) {
        a(liveGameMessage, false);
    }

    private void a(LiveGameMessage liveGameMessage, boolean z) {
        this.J.doCountDown(((LiveGameDataMessage) liveGameMessage).getCountDownDuration() - 2);
        this.W.setVisibility(4);
        this.aa.setVisibility(4);
        this.ab.setVisibility(4);
        this.ac.setVisibility(4);
        this.ad.setVisibility(4);
        this.ae.setVisibility(4);
        this.K.setText("");
        this.L.setText("");
        this.M.setText("");
        this.N.setText("");
        this.P.setText("");
        this.O.setText("");
        a(z);
    }

    protected void d() {
    }

    private void a(boolean z) {
        if (z) {
            a(R.string.live_game_ready, true);
        } else {
            a(R.string.live_game_rolltable_start, true);
        }
    }

    private void f(LiveGameMessage liveGameMessage) {
        LiveGameDataMessage liveGameDataMessage = (LiveGameDataMessage) liveGameMessage;
        int gameStatus = liveGameDataMessage.getGameStatus();
        if (gameStatus == 0) {
            c(liveGameDataMessage);
        } else if (gameStatus == 1) {
            this.J.doCountDown(liveGameDataMessage.getCountDownDuration() - 2);
            b(liveGameDataMessage);
        } else if (gameStatus == 2) {
            b(liveGameDataMessage);
            a(liveGameDataMessage, true);
        }
    }

    protected void c(LiveGameDataMessage liveGameDataMessage) {
        a((LiveGameMessage) liveGameDataMessage, true);
    }

    protected void b(LiveGameDataMessage liveGameDataMessage) {
        List gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        int i = 0;
        while (gameRoleBetData != null && i < gameRoleBetData.size()) {
            GameRole gameRole = (GameRole) gameRoleBetData.get(i);
            if (gameRole != null) {
                int roleId = (int) gameRole.getRoleId();
                a(roleId, gameRole.getTotalBet());
                if (gameRole.getMeBet() > 0) {
                    getRoleView((long) roleId).setVisibility(0);
                    c((long) roleId).setText(FormatUtils.formatCoupon(gameRole.getMeBet()));
                }
            }
            i++;
        }
    }

    protected void a(LiveGameDataMessage liveGameDataMessage, boolean z) {
        List gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        if (gameRoleBetData != null && gameRoleBetData.size() > 0 && ((GameRole) gameRoleBetData.get(0)).getGameResult().getResultGroup() != null) {
            int intValue = ((Integer) ((GameRole) gameRoleBetData.get(0)).getGameResult().getResultGroup().get(0)).intValue();
            if (z) {
                this.J.startRoll(intValue, 1000);
            } else {
                this.J.startRoll(intValue, 5000);
            }
        }
    }

    private void a(LiveGameBetMessage liveGameBetMessage) {
        GameRole gameRole = liveGameBetMessage.getGameRole();
        View roleView = getRoleView(gameRole.getRoleId());
        if (roleView != null) {
            if (this.mLiveBaseActivity.isMe(liveGameBetMessage)) {
                int i;
                roleView.getLocationOnScreen(new int[2]);
                if (gameRole.getRoleId() == 1 || gameRole.getRoleId() == 2 || gameRole.getRoleId() == 3) {
                    i = -WindowUtils.dp2Px(164);
                } else {
                    i = -WindowUtils.dp2Px(94);
                }
                a(roleView, i);
            }
            if (isContentVisible()) {
                a((int) liveGameBetMessage.getRoleId(), liveGameBetMessage.getGameRoleBetTotal());
            }
        }
    }

    private TextView c(long j) {
        switch ((int) j) {
            case 1:
                return this.af;
            case 2:
                return this.ag;
            case 3:
                return this.ah;
            case 4:
                return this.ai;
            case 5:
                return this.aj;
            case 6:
                return this.ak;
            default:
                return this.af;
        }
    }

    public View getRoleView(long j) {
        switch ((int) j) {
            case 1:
                return this.W;
            case 2:
                return this.aa;
            case 3:
                return this.ab;
            case 4:
                return this.ac;
            case 5:
                return this.ad;
            case 6:
                return this.ae;
            default:
                return this.W;
        }
    }

    private void a(int i, long j) {
        if (j > 0) {
            switch (i) {
                case 1:
                    this.K.setText(FormatUtils.formatCoupon(j));
                    return;
                case 2:
                    this.L.setText(FormatUtils.formatCoupon(j));
                    return;
                case 3:
                    this.M.setText(FormatUtils.formatCoupon(j));
                    return;
                case 4:
                    this.N.setText(FormatUtils.formatCoupon(j));
                    return;
                case 5:
                    this.O.setText(FormatUtils.formatCoupon(j));
                    return;
                case 6:
                    this.P.setText(FormatUtils.formatCoupon(j));
                    return;
                default:
                    return;
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.iv_bet2 || view.getId() == R.id.iv_bet4 || view.getId() == R.id.iv_bet7 || view.getId() == R.id.iv_bet12 || view.getId() == R.id.iv_bet_wing || view.getId() == R.id.iv_bet_diamond) {
            c(view);
        } else if (view.getId() == R.id.btn_more) {
            e();
        } else if (view.getId() == R.id.btn_history) {
            if (this.D != null && this.D.isShowing()) {
                this.D.dismiss();
            }
            this.D = new GameHistoryDialog(this.mLiveBaseActivity, getGameId(), this.mLiveBaseActivity.getRoomId());
            this.D.show();
        } else if (view.getId() == R.id.btn_help) {
            if (this.D != null && this.D.isShowing()) {
                this.D.dismiss();
            }
            this.D = new GameHelpDialog(this.mLiveBaseActivity, getGameId());
            this.D.show();
        } else if (view.getId() == R.id.btn_mvp) {
            if (this.D != null && this.D.isShowing()) {
                this.D.dismiss();
            }
            this.D = new GameMVPDialog(this.mLiveBaseActivity, getGameId());
            this.D.show();
        } else if (view.getId() == R.id.ll_game_balance) {
            AppUtils.getInstance().getUserInfoProvider().toPay(this.mLiveBaseActivity, 103);
        }
    }

    protected void c(View view) {
        long betOptionId = this.f.getBetOptionId();
        long j = 0;
        if (view.getId() == R.id.iv_bet2) {
            j = 1;
        } else if (view.getId() == R.id.iv_bet4) {
            j = 2;
        } else if (view.getId() == R.id.iv_bet7) {
            j = 3;
        } else if (view.getId() == R.id.iv_bet12) {
            j = 4;
        } else if (view.getId() == R.id.iv_bet_wing) {
            j = 5;
        } else if (view.getId() == R.id.iv_bet_diamond) {
            j = 6;
        }
        if (this.a != null) {
            this.mLiveBaseActivity.sendLiveMessageAndRefreshUI(LiveMessage.createGameBetMessage(AppUtils.getInstance().getUserInfoProvider().getUserId(), this.a.getGameId(), this.a.getGameRoundId(), j, betOptionId));
        }
    }

    public void release() {
        this.mLiveBaseActivity = null;
        this.m.removeCallbacksAndMessages(null);
        removeAllViews();
        this.D = null;
    }
}
