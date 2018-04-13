package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.List;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.FormatUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.GameResult;
import qsbk.app.live.model.GameRole;
import qsbk.app.live.model.LiveGameAnchorSelectMessage;
import qsbk.app.live.model.LiveGameBetMessage;
import qsbk.app.live.model.LiveGameDataMessage;
import qsbk.app.live.model.LiveGameMessage;
import qsbk.app.live.model.LiveGameStepMessage;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.ui.LiveBaseActivity;
import qsbk.app.widget.RefreshTipView;

public class FanfanleGameView extends GameView {
    private GameFanfanleProgressView J;
    private ImageView K;
    private ImageView L;
    private ImageView M;
    private ImageView N;
    private TextView O;
    private FrameLayout P;
    private FrameLayout Q;
    private FrameLayout R;
    private FrameLayout S;
    private FrameLayout T;
    private int U = -1;
    private int V = 1;
    private int W;
    private ImageView aa;
    private LinearLayout ab;
    private ImageView ac;
    private ImageView ad;
    private ImageView ae;
    private ImageView af;
    private ImageView ag;
    private ImageView ah;
    private ImageView ai;
    private ImageView aj;
    private TextView ak;
    private TextView al;
    private TextView am;
    private TextView an;
    private TextView ao;
    private int ap = -1;
    private long aq = 0;
    private boolean ar = false;
    private boolean as = false;
    private ImageView at;
    private ImageView au;
    private ImageView av;

    public FanfanleGameView(Context context) {
        super(context, null);
    }

    public FanfanleGameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public FanfanleGameView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected int getLayoutId() {
        return R.layout.game_fanfanle;
    }

    protected void a(AttributeSet attributeSet, int i) {
        this.mLiveBaseActivity = (LiveBaseActivity) getContext();
        this.D = this.mLiveBaseActivity.mDialog;
        this.m = new Handler();
        this.G = View.inflate(getContext(), getLayoutId(), this);
        this.b = this.G.findViewById(R.id.game_container);
        this.G.findViewById(R.id.ll_game_balance).setOnClickListener(this);
        this.e = (TextView) this.G.findViewById(R.id.tv_game_balance);
        this.J = (GameFanfanleProgressView) this.G.findViewById(R.id.game_fanfanle_progressview);
        this.O = (TextView) this.G.findViewById(R.id.tv_board);
        this.N = (ImageView) this.G.findViewById(R.id.iv_card);
        this.f = (GameBetButton) this.G.findViewById(R.id.bet_btn);
        this.g = this.G.findViewById(R.id.btn_more);
        this.h = this.G.findViewById(R.id.btn_help);
        this.i = this.G.findViewById(R.id.btn_history);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.j = this.G.findViewById(R.id.btn_mvp);
        if (this.j != null) {
            this.j.setOnClickListener(this);
        }
        this.k = (TextView) this.G.findViewById(R.id.tv_countdown);
        ObjectAnimator.ofFloat(this.k, ROTATION, new float[]{-5.0f, 5.0f}).setDuration(1000);
        ObjectAnimator.ofFloat(this.k, ROTATION, new float[]{5.0f, -5.0f}).setDuration(1000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(new Animator[]{r0, r1});
        animatorSet.addListener(new ai(this, animatorSet));
        animatorSet.start();
        this.P = (FrameLayout) this.G.findViewById(R.id.fl_bet1);
        this.Q = (FrameLayout) this.G.findViewById(R.id.fl_bet2);
        this.R = (FrameLayout) this.G.findViewById(R.id.fl_bet3);
        this.S = (FrameLayout) this.G.findViewById(R.id.fl_bet4);
        this.T = (FrameLayout) this.G.findViewById(R.id.fl_bet5);
        this.P.setOnClickListener(this);
        this.Q.setOnClickListener(this);
        this.R.setOnClickListener(this);
        this.S.setOnClickListener(this);
        this.T.setOnClickListener(this);
        this.aa = (ImageView) this.G.findViewById(R.id.iv_result_bg);
        this.ab = (LinearLayout) this.G.findViewById(R.id.ll_pokegroup);
        this.K = (ImageView) this.G.findViewById(R.id.iv_card1);
        this.K.setOnClickListener(this);
        this.L = (ImageView) this.G.findViewById(R.id.iv_card2);
        this.L.setOnClickListener(this);
        this.M = (ImageView) this.G.findViewById(R.id.iv_card3);
        this.M.setOnClickListener(this);
        this.ac = (ImageView) this.G.findViewById(R.id.iv_card1_result);
        this.ad = (ImageView) this.G.findViewById(R.id.iv_card2_result);
        this.ae = (ImageView) this.G.findViewById(R.id.iv_card3_result);
        this.af = (ImageView) this.G.findViewById(R.id.iv_bet1_result);
        this.ag = (ImageView) this.G.findViewById(R.id.iv_bet2_result);
        this.ah = (ImageView) this.G.findViewById(R.id.iv_bet3_result);
        this.ai = (ImageView) this.G.findViewById(R.id.iv_bet4_result);
        this.aj = (ImageView) this.G.findViewById(R.id.iv_bet5_result);
        this.ak = (TextView) this.G.findViewById(R.id.tvRate1);
        this.al = (TextView) this.G.findViewById(R.id.tvRate2);
        this.am = (TextView) this.G.findViewById(R.id.tvRate3);
        this.an = (TextView) this.G.findViewById(R.id.tvRate4);
        this.ao = (TextView) this.G.findViewById(R.id.tvRate5);
        this.z = (TextView) this.G.findViewById(R.id.tv_tips);
        b();
        this.at = (ImageView) this.G.findViewById(R.id.iv_bird);
        this.au = (ImageView) this.G.findViewById(R.id.iv_clond_left);
        this.av = (ImageView) this.G.findViewById(R.id.iv_cloud_right);
        this.m.postDelayed(new av(this), 200);
        this.m.postDelayed(new ax(this), 200);
        this.m.postDelayed(new ay(this), 200);
    }

    private void i() {
        this.at.setImageResource(R.drawable.game_fanfanle_bird_left);
        Animator ofFloat = ObjectAnimator.ofFloat(this.at, TRANSLATION_X, new float[]{0.0f, (float) (WindowUtils.dp2Px(47) + getWidth())});
        ofFloat.setDuration(RefreshTipView.FIRST_REFRESH_INTERVAL);
        ofFloat.addListener(new az(this));
        ofFloat.start();
    }

    private void j() {
        this.at.setImageResource(R.drawable.game_fanfanle_bird_right);
        Animator ofFloat = ObjectAnimator.ofFloat(this.at, TRANSLATION_X, new float[]{(float) (WindowUtils.dp2Px(47) + getWidth()), 0.0f});
        ofFloat.setDuration(RefreshTipView.FIRST_REFRESH_INTERVAL);
        ofFloat.addListener(new bb(this));
        ofFloat.start();
    }

    private void k() {
        Animator ofFloat = ObjectAnimator.ofFloat(this.au, TRANSLATION_X, new float[]{0.0f, (float) (WindowUtils.dp2Px(65) + getWidth())});
        ofFloat.setDuration(36000);
        ofFloat.addListener(new bd(this));
        ofFloat.start();
    }

    private void l() {
        Animator ofFloat = ObjectAnimator.ofFloat(this.au, TRANSLATION_X, new float[]{(float) (WindowUtils.dp2Px(65) + getWidth()), 0.0f});
        ofFloat.setDuration(36000);
        ofFloat.addListener(new bf(this));
        ofFloat.start();
    }

    private void m() {
        Animator ofFloat = ObjectAnimator.ofFloat(this.av, TRANSLATION_X, new float[]{(float) ((-WindowUtils.dp2Px(68)) - getWidth()), 0.0f});
        ofFloat.setDuration(40000);
        ofFloat.addListener(new bh(this));
        ofFloat.start();
    }

    private void n() {
        Animator ofFloat = ObjectAnimator.ofFloat(this.av, TRANSLATION_X, new float[]{0.0f, (float) ((-WindowUtils.dp2Px(68)) - getWidth())});
        ofFloat.setDuration(40000);
        ofFloat.addListener(new aj(this));
        ofFloat.start();
    }

    public void loadGameData(LiveGameMessage liveGameMessage) {
        int i = 4;
        if (!GameView.isSupportedGame(liveGameMessage.getGameId())) {
            setVisibility(4);
        } else if (this.mLiveBaseActivity != null) {
            int messageType = liveGameMessage.getMessageType();
            if (messageType != 44 && messageType != 49 && this.a == null) {
                return;
            }
            GameBetButton gameBetButton;
            if (messageType == 44) {
                this.as = false;
                this.a = liveGameMessage;
                if (this.ap == -1) {
                    this.ap = 0;
                }
                gameBetButton = this.f;
                if (this.ap == 0) {
                    i = 0;
                }
                gameBetButton.setVisibility(i);
                this.ar = true;
                this.U = -1;
                b();
                a(liveGameMessage);
            } else if (messageType == 49) {
                this.a = liveGameMessage;
                r10 = (LiveGameDataMessage) liveGameMessage;
                this.ap = r10.getStep();
                gameBetButton = this.f;
                if (this.ap == 0) {
                    i = 0;
                }
                gameBetButton.setVisibility(i);
                this.J.increase(this.ap, this.aq, j(r10));
                this.O.setText(FormatUtils.formatCoupon(j(r10)));
                h(r10);
                if (r10.getSelectedIndex() != -1) {
                    this.U = r10.getSelectedIndex();
                    i = R.id.iv_card1;
                    switch (this.U) {
                        case 0:
                            i = R.id.iv_card1;
                            break;
                        case 1:
                            i = R.id.iv_card2;
                            break;
                        case 2:
                            i = R.id.iv_card3;
                            break;
                    }
                    d(i);
                }
                i = r10.getGameStatus();
                if (i == 0) {
                    c(r10);
                    this.ar = false;
                } else if (i == 1) {
                    b(r10);
                    this.ar = true;
                } else if (i == 2) {
                    d(r10);
                    this.ar = false;
                }
            } else if (messageType == 46) {
                updateBetData((LiveGameBetMessage) liveGameMessage);
            } else if (messageType == 42) {
                r10 = (LiveGameDataMessage) liveGameMessage;
                this.mLiveBaseActivity.updateBalance(r10.getBalance());
                e(r10);
            } else if (messageType == 45) {
                this.as = true;
                this.ar = false;
                b(liveGameMessage);
            } else if (messageType == 47) {
                c(liveGameMessage);
            } else if (messageType == 43 && isContentVisible()) {
                showBestBetResult(liveGameMessage);
            } else if (messageType == 95) {
                a((LiveGameStepMessage) liveGameMessage);
            } else if (messageType == 93) {
                i = R.id.iv_card1;
                switch (((LiveGameAnchorSelectMessage) liveGameMessage).getPos()) {
                    case 0:
                        i = R.id.iv_card1;
                        break;
                    case 1:
                        i = R.id.iv_card2;
                        break;
                    case 2:
                        i = R.id.iv_card3;
                        break;
                }
                d(i);
            } else if (messageType == 96) {
                ToastUtil.Long("竞猜超时，上轮游戏已自动结算");
                this.O.setText("0");
            }
        }
    }

    private void a(LiveGameStepMessage liveGameStepMessage) {
        this.ap = liveGameStepMessage.getStep();
        this.f.setVisibility(this.ap == 0 ? 0 : 4);
        if (this.ap == 0) {
            this.J.setInitialState(0, 0);
            this.aq = 0;
            this.O.setText("0");
            return;
        }
        this.J.increase(this.ap, this.aq, liveGameStepMessage.getCoupon());
        this.aq = liveGameStepMessage.getCoupon();
        this.O.setText(FormatUtils.formatCoupon(this.aq));
    }

    protected void a(LiveGameMessage liveGameMessage) {
        if (this.ap >= 0) {
            a(R.string.game_fanfanle_start, new al(this, liveGameMessage), 1600);
        }
    }

    private void g(LiveGameDataMessage liveGameDataMessage) {
        List<GameRole> gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        if (gameRoleBetData != null && gameRoleBetData.size() > 0) {
            GameResult gameResult = ((GameRole) gameRoleBetData.get(0)).getGameResult();
            if (gameResult != null) {
                List resultGroup = gameResult.getResultGroup();
                if (resultGroup != null && resultGroup.size() > 0 && this.ap >= 0) {
                    e(((Integer) resultGroup.get(0)).intValue());
                }
            }
            for (GameRole gameRole : gameRoleBetData) {
                TextView b = b((int) gameRole.getRoleId());
                CharSequence format = new DecimalFormat("0.00").format((double) gameRole.getRate());
                if (b != null) {
                    b.setText(format);
                }
                if (gameRole.getRate() == 0.0f) {
                    View roleView = getRoleView((long) ((int) gameRole.getRoleId()));
                    roleView.setClickable(false);
                    roleView.setBackgroundResource(R.drawable.game_fanfanle_bet_invalid);
                }
            }
        }
    }

    private TextView b(int i) {
        switch (i) {
            case 1:
                return this.ak;
            case 2:
                return this.al;
            case 3:
                return this.am;
            case 4:
                return this.an;
            case 5:
                return this.ao;
            default:
                return null;
        }
    }

    protected void a(long j) {
        this.l = new am(this, (1000 * j) + 500, 500);
        this.l.start();
    }

    protected void b() {
        this.K.setTranslationY(0.0f);
        this.L.setTranslationY(0.0f);
        this.M.setTranslationY(0.0f);
        this.K.setVisibility(0);
        this.L.setVisibility(0);
        this.M.setVisibility(0);
        this.ac.setTranslationY(0.0f);
        this.ad.setTranslationY(0.0f);
        this.ae.setTranslationY(0.0f);
        this.K.setSelected(false);
        this.L.setSelected(false);
        this.M.setSelected(false);
        this.K.setImageResource(R.drawable.game_fanfanle_poke_bg);
        this.L.setImageResource(R.drawable.game_fanfanle_poke_bg);
        this.M.setImageResource(R.drawable.game_fanfanle_poke_bg);
        this.K.setAlpha(1.0f);
        this.K.setRotationY(0.0f);
        this.L.setAlpha(1.0f);
        this.L.setRotationY(0.0f);
        this.M.setAlpha(1.0f);
        this.M.setRotationY(0.0f);
        this.ab.setVisibility(4);
        this.aa.setVisibility(8);
        this.ac.setVisibility(4);
        this.ac.setImageDrawable(null);
        this.ac.setBackgroundColor(getResources().getColor(R.color.transparent));
        this.ad.setVisibility(4);
        this.ad.setImageDrawable(null);
        this.ad.setBackgroundColor(getResources().getColor(R.color.transparent));
        this.ae.setVisibility(4);
        this.ae.setImageDrawable(null);
        this.ae.setBackgroundColor(getResources().getColor(R.color.transparent));
        this.af.setVisibility(4);
        this.ag.setVisibility(4);
        this.ah.setVisibility(4);
        this.ai.setVisibility(4);
        this.aj.setVisibility(4);
        this.af.setBackgroundColor(getResources().getColor(R.color.transparent));
        this.ag.setBackgroundColor(getResources().getColor(R.color.transparent));
        this.ah.setBackgroundColor(getResources().getColor(R.color.transparent));
        this.ai.setBackgroundColor(getResources().getColor(R.color.transparent));
        this.aj.setBackgroundColor(getResources().getColor(R.color.transparent));
        this.P.setSelected(true);
        this.P.setClickable(true);
        this.P.setBackgroundResource(R.drawable.game_fanfanle_selector);
        this.Q.setSelected(true);
        this.Q.setClickable(true);
        this.Q.setBackgroundResource(R.drawable.game_fanfanle_selector);
        this.R.setSelected(true);
        this.R.setClickable(true);
        this.R.setBackgroundResource(R.drawable.game_fanfanle_selector);
        this.S.setSelected(true);
        this.S.setClickable(true);
        this.S.setBackgroundResource(R.drawable.game_fanfanle_selector);
        this.T.setSelected(true);
        this.T.setClickable(true);
        this.T.setBackgroundResource(R.drawable.game_fanfanle_selector);
    }

    protected void c(LiveGameDataMessage liveGameDataMessage) {
        a(liveGameDataMessage.getCountDownDuration() - 2);
    }

    protected void b(LiveGameDataMessage liveGameDataMessage) {
        this.N.setVisibility(0);
        List gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        if (gameRoleBetData != null && gameRoleBetData.size() > 0) {
            GameResult gameResult = ((GameRole) gameRoleBetData.get(0)).getGameResult();
            if (gameResult != null) {
                gameRoleBetData = gameResult.getResultGroup();
                if (gameRoleBetData != null && gameRoleBetData.size() > 0) {
                    this.N.setImageDrawable(PokerGroup.getPokerDrawable(((Integer) gameRoleBetData.get(0)).intValue()));
                    a(liveGameDataMessage.getCountDownDuration() - 2);
                }
            }
        }
        this.ab.setVisibility(0);
        this.K.setVisibility(0);
        this.L.setVisibility(0);
        this.M.setVisibility(0);
    }

    private void h(LiveGameDataMessage liveGameDataMessage) {
        List<GameRole> gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        if (gameRoleBetData != null && gameRoleBetData.size() > 0) {
            boolean i = i(liveGameDataMessage);
            for (GameRole gameRole : gameRoleBetData) {
                View roleView = getRoleView(gameRole.getRoleId());
                TextView b = b((int) gameRole.getRoleId());
                CharSequence format = new DecimalFormat("0.00").format((double) gameRole.getRate());
                if (b != null) {
                    b.setText(format);
                }
                if (gameRole.getRate() == 0.0f) {
                    roleView.setBackgroundResource(R.drawable.game_fanfanle_bet_invalid);
                    roleView.setClickable(false);
                } else {
                    roleView.setClickable(true);
                    if (i) {
                        roleView.setBackgroundResource(R.drawable.game_fanfanle_selector);
                        roleView.setSelected(true);
                    } else if (gameRole.getMeBet() > 0) {
                        roleView.setSelected(true);
                    } else {
                        roleView.setSelected(false);
                        if (this.ap == 0) {
                            roleView.setBackgroundResource(R.drawable.game_fanfanle_bet_invalid);
                            roleView.setClickable(false);
                        } else {
                            roleView.setBackgroundResource(R.drawable.game_fanfanle_selector);
                        }
                    }
                }
            }
        }
    }

    private boolean i(LiveGameDataMessage liveGameDataMessage) {
        List<GameRole> gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        if (gameRoleBetData != null && gameRoleBetData.size() > 0) {
            for (GameRole meBet : gameRoleBetData) {
                if (meBet.getMeBet() > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private long j(LiveGameDataMessage liveGameDataMessage) {
        if (this.ap >= 1) {
            this.aq = liveGameDataMessage.getBetNum();
            return this.aq;
        }
        List<GameRole> gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        if (gameRoleBetData != null && gameRoleBetData.size() > 0) {
            for (GameRole gameRole : gameRoleBetData) {
                if (gameRole.getMeBet() > 0) {
                    this.aq = gameRole.getMeBet();
                    return this.aq;
                }
            }
        }
        return 0;
    }

    protected void d(LiveGameDataMessage liveGameDataMessage) {
        b((LiveGameMessage) liveGameDataMessage);
    }

    public void updateBetData(LiveGameBetMessage liveGameBetMessage) {
    }

    public View getRoleView(long j) {
        switch ((int) j) {
            case 1:
                return this.P;
            case 2:
                return this.Q;
            case 3:
                return this.R;
            case 4:
                return this.S;
            case 5:
                return this.T;
            default:
                return null;
        }
    }

    protected void e(LiveGameDataMessage liveGameDataMessage) {
        List gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        int i = 0;
        while (gameRoleBetData != null && i < gameRoleBetData.size()) {
            GameRole gameRole = (GameRole) gameRoleBetData.get(i);
            View roleView = getRoleView(gameRole.getRoleId());
            if (roleView != null) {
                g((int) gameRole.getRoleId());
                if (gameRole.getMeBet() > 0) {
                    if (this.ap == 0) {
                        roleView.getLocationOnScreen(new int[2]);
                        a(roleView, -WindowUtils.dp2Px(50), WindowUtils.dp2Px(6), new an(this, gameRole));
                    }
                    roleView.setSelected(true);
                } else {
                    roleView.setSelected(false);
                }
            }
            i++;
        }
    }

    protected void b(LiveGameMessage liveGameMessage) {
        a(R.string.live_game_result, new ao(this, liveGameMessage));
    }

    protected void a(LiveGameDataMessage liveGameDataMessage) {
        this.U = liveGameDataMessage.getSelectedIndex();
        int i = R.id.iv_card1;
        switch (this.U) {
            case 0:
                i = R.id.iv_card1;
                break;
            case 1:
                i = R.id.iv_card2;
                break;
            case 2:
                i = R.id.iv_card3;
                break;
        }
        d(i);
        List gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        if (gameRoleBetData != null && gameRoleBetData.size() > 0) {
            GameResult gameResult = ((GameRole) gameRoleBetData.get(0)).getGameResult();
            int result = gameResult.getResult();
            if (gameResult.e != null && gameResult.e.length() > 0) {
                int charAt = gameResult.e.charAt(0) - 48;
                gameRoleBetData = gameResult.getResultGroup();
                if (gameRoleBetData != null && gameRoleBetData.size() > 0) {
                    this.P.setSelected(true);
                    this.Q.setSelected(true);
                    this.R.setSelected(true);
                    this.S.setSelected(true);
                    this.T.setSelected(true);
                    this.P.setClickable(false);
                    this.Q.setClickable(false);
                    this.R.setClickable(false);
                    this.S.setClickable(false);
                    this.T.setClickable(false);
                    a(((Integer) gameRoleBetData.get(1)).intValue(), result, charAt);
                }
            }
        }
    }

    private void a(int i, int i2, int i3) {
        View view;
        ImageView imageView;
        ImageView imageView2;
        ImageView imageView3;
        ImageView imageView4;
        ImageView imageView5;
        this.V = i;
        ImageView[] imageViewArr = new ImageView[]{this.af, this.ag, this.ah, this.ai, this.aj};
        if (this.U == 0 || this.U == -1) {
            view = this.ac;
            imageView = this.ad;
            imageView2 = this.ae;
            imageView3 = this.K;
            imageView4 = this.L;
            imageView5 = this.M;
        } else if (this.U == 1) {
            view = this.ad;
            imageView = this.ac;
            imageView2 = this.ae;
            imageView4 = this.K;
            imageView3 = this.L;
            imageView5 = this.M;
        } else {
            view = this.ae;
            imageView = this.ac;
            imageView2 = this.ad;
            imageView4 = this.K;
            imageView5 = this.L;
            imageView3 = this.M;
        }
        view.setImageDrawable(PokerGroup.getPokerDrawable(i));
        view.setVisibility(0);
        a(view, c(this.U), 540);
        this.m.postDelayed(new ap(this, imageView, imageView2, imageView3, imageView4, imageView5, i2, i3, imageViewArr), 270);
        this.m.postDelayed(new ar(this), 5500);
    }

    private ImageView c(int i) {
        ImageView imageView = this.K;
        switch (i) {
            case 0:
                return this.K;
            case 1:
                return this.L;
            case 2:
                return this.M;
            default:
                return imageView;
        }
    }

    protected void c(LiveGameMessage liveGameMessage) {
        LiveGameBetMessage liveGameBetMessage = (LiveGameBetMessage) liveGameMessage;
        if (this.mLiveBaseActivity.isAnchor(AppUtils.getInstance().getUserInfoProvider().getUser())) {
            a(liveGameBetMessage.getAnchorResult(), true);
        } else if (liveGameBetMessage.isWin()) {
            long winNum = liveGameBetMessage.getWinNum();
            if (this.D != null && this.D.isShowing()) {
                this.D.dismiss();
            }
            this.D = new GameWinDialog(this.mLiveBaseActivity, getGameId(), winNum, liveGameBetMessage.getStep(), this.E);
            ((GameWinDialog) this.D).setCloseListener(new as(this));
            ((GameWinDialog) this.D).setNextListener(new at(this));
            this.D.show();
        } else {
            a(liveGameBetMessage.getResult(), null);
            this.aq = 0;
            this.ap = 0;
            this.O.setText("0");
        }
    }

    public void showBestBetResult(LiveGameMessage liveGameMessage) {
        super.showBestBetResult(liveGameMessage);
    }

    public void onClick(View view) {
        if (this.mLiveBaseActivity != null && !this.mLiveBaseActivity.forwardIfNotLogin()) {
            this.mLiveBaseActivity.disShowBottomFollowTipsDialog();
            if (view.getId() == R.id.ll_game_balance) {
                AppUtils.getInstance().getUserInfoProvider().toPay(this.mLiveBaseActivity, 103);
            } else if (view.getId() == R.id.btn_more) {
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
            } else if (view.getId() == R.id.fl_bet1 || view.getId() == R.id.fl_bet2 || view.getId() == R.id.fl_bet3 || view.getId() == R.id.fl_bet4 || view.getId() == R.id.fl_bet5) {
                c(view);
            } else if ((view.getId() == R.id.iv_card1 || view.getId() == R.id.iv_card2 || view.getId() == R.id.iv_card3) && this.mLiveBaseActivity.isAnchor(AppUtils.getInstance().getUserInfoProvider().getUser())) {
                if (view.getId() == R.id.iv_card1) {
                    this.U = 0;
                } else if (view.getId() == R.id.iv_card2) {
                    this.U = 1;
                } else {
                    this.U = 2;
                }
                h(this.U);
            }
        }
    }

    private void d(int i) {
        if (!this.as) {
            if (i == R.id.iv_card1) {
                this.U = 0;
                this.ac.setTranslationY((float) (-WindowUtils.dp2Px(5)));
                this.ad.setTranslationY(0.0f);
                this.ae.setTranslationY(0.0f);
                this.K.setTranslationY((float) (-WindowUtils.dp2Px(5)));
                this.L.setTranslationY((float) (-WindowUtils.dp2Px(0)));
                this.M.setTranslationY((float) (-WindowUtils.dp2Px(0)));
                this.K.setSelected(true);
                this.L.setSelected(false);
                this.M.setSelected(false);
                this.ac.setVisibility(0);
                this.ad.setVisibility(4);
                this.ae.setVisibility(4);
            } else if (i == R.id.iv_card2) {
                this.U = 1;
                this.ad.setTranslationY((float) (-WindowUtils.dp2Px(5)));
                this.ac.setTranslationY((float) (-WindowUtils.dp2Px(0)));
                this.ae.setTranslationY((float) (-WindowUtils.dp2Px(0)));
                this.L.setTranslationY((float) (-WindowUtils.dp2Px(5)));
                this.K.setTranslationY((float) (-WindowUtils.dp2Px(0)));
                this.M.setTranslationY((float) (-WindowUtils.dp2Px(0)));
                this.L.setSelected(true);
                this.K.setSelected(false);
                this.M.setSelected(false);
                this.ad.setVisibility(0);
                this.ac.setVisibility(4);
                this.ae.setVisibility(4);
            } else if (i == R.id.iv_card3) {
                this.U = 2;
                this.ae.setTranslationY((float) (-WindowUtils.dp2Px(5)));
                this.ac.setTranslationY((float) (-WindowUtils.dp2Px(0)));
                this.ad.setTranslationY((float) (-WindowUtils.dp2Px(0)));
                this.M.setTranslationY((float) (-WindowUtils.dp2Px(5)));
                this.K.setTranslationY((float) (-WindowUtils.dp2Px(0)));
                this.L.setTranslationY((float) (-WindowUtils.dp2Px(0)));
                this.M.setSelected(true);
                this.K.setSelected(false);
                this.L.setSelected(false);
                this.ae.setVisibility(0);
                this.ad.setVisibility(4);
                this.ac.setVisibility(4);
            }
        }
    }

    protected void c(View view) {
        long betOptionId = this.f.getBetOptionId();
        long j = 0;
        if (view.getId() == R.id.fl_bet1) {
            j = 1;
        } else if (view.getId() == R.id.fl_bet2) {
            j = 2;
        } else if (view.getId() == R.id.fl_bet3) {
            j = 3;
        } else if (view.getId() == R.id.fl_bet4) {
            j = 4;
        } else if (view.getId() == R.id.fl_bet5) {
            j = 5;
        }
        if (this.a == null) {
            return;
        }
        if (this.ap == 0) {
            this.mLiveBaseActivity.sendLiveMessageAndRefreshUI(LiveMessage.createGameBetMessage(AppUtils.getInstance().getUserInfoProvider().getUserId(), this.a.getGameId(), this.a.getGameRoundId(), j, betOptionId));
            return;
        }
        this.mLiveBaseActivity.sendLiveMessageAndRefreshUI(LiveMessage.createGameFanfanleSelectMessage(AppUtils.getInstance().getUserInfoProvider().getUserId(), this.a.getGameId(), this.a.getGameRoundId(), j, betOptionId));
    }

    public void release() {
        this.mLiveBaseActivity = null;
        this.m.removeCallbacksAndMessages(null);
        removeAllViews();
        this.D = null;
    }

    private void e(int i) {
        this.ab.setVisibility(0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.K, TRANSLATION_X, new float[]{0.0f, 0.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.L, TRANSLATION_X, new float[]{(float) (-WindowUtils.dp2Px(27)), 0.0f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.M, TRANSLATION_X, new float[]{(float) (-WindowUtils.dp2Px(54)), 0.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
        animatorSet.setDuration(440);
        animatorSet.start();
        this.N.setImageDrawable(PokerGroup.getPokerDrawable(i));
        if (this.W == 0) {
            this.W = 1;
            ofFloat = ObjectAnimator.ofFloat(this.N, SCALE_X, new float[]{0.0f, 1.0f});
            ofFloat2 = ObjectAnimator.ofFloat(this.N, SCALE_Y, new float[]{0.0f, 1.0f});
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet2.setDuration(200);
            animatorSet2.start();
            this.N.setVisibility(0);
        }
    }

    private void a(View view, View view2, long j) {
        if (view != null && view2 != null) {
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view2, "rotationY", new float[]{0.0f, 180.0f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view2, "alpha", new float[]{1.0f, 0.0f});
            ofFloat.setDuration(j);
            ofFloat2.setDuration(0);
            ofFloat2.setStartDelay(j / 2);
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
            AnimatorSet animatorSet2 = new AnimatorSet();
            ofFloat2 = ObjectAnimator.ofFloat(view, "alpha", new float[]{1.0f, 0.0f});
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, "rotationY", new float[]{-180.0f, 0.0f});
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, "alpha", new float[]{1.0f, 1.0f});
            ofFloat2.setDuration(0);
            ofFloat3.setDuration(j);
            ofFloat4.setDuration(0);
            ofFloat4.setStartDelay(j / 2);
            animatorSet2.playTogether(new Animator[]{ofFloat2, ofFloat3, ofFloat4});
            float f = ((float) 15000) * view.getContext().getResources().getDisplayMetrics().density;
            view.setCameraDistance(f);
            view2.setCameraDistance(f);
            animatorSet.start();
            animatorSet2.start();
        }
    }

    private void o() {
        int dp2Px;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.K, TRANSLATION_X, new float[]{0.0f, 0.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.L, TRANSLATION_X, new float[]{0.0f, (float) (-WindowUtils.dp2Px(27))});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.M, TRANSLATION_X, new float[]{0.0f, (float) (-WindowUtils.dp2Px(54))});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
        animatorSet.setDuration(280);
        animatorSet.start();
        animatorSet.addListener(new au(this));
        ImageView f = f(this.U);
        if (this.U != -1) {
            dp2Px = WindowUtils.dp2Px(5);
        } else {
            dp2Px = 0;
        }
        ofFloat3 = ObjectAnimator.ofFloat(f, TRANSLATION_X, new float[]{0.0f, (float) (WindowUtils.dp2Px(93) - f.getLeft())});
        ofFloat = ObjectAnimator.ofFloat(f, TRANSLATION_Y, new float[]{(float) dp2Px, 0.0f});
        animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat3, ofFloat});
        animatorSet.setDuration(280);
        animatorSet.start();
        animatorSet.addListener(new aw(this, f));
        this.aa.setVisibility(4);
        this.af.setBackgroundColor(getResources().getColor(R.color.transparent));
        this.ag.setBackgroundColor(getResources().getColor(R.color.transparent));
        this.ah.setBackgroundColor(getResources().getColor(R.color.transparent));
        this.ai.setBackgroundColor(getResources().getColor(R.color.transparent));
        this.aj.setBackgroundColor(getResources().getColor(R.color.transparent));
    }

    private ImageView f(int i) {
        if (i == 0 || i == -1) {
            return this.ac;
        }
        if (i == 1) {
            return this.ad;
        }
        return this.ae;
    }

    private void g(int i) {
        FrameLayout[] frameLayoutArr = new FrameLayout[]{this.P, this.Q, this.R, this.S, this.T};
        for (int i2 = 0; i2 < 5; i2++) {
            if (i2 == i - 1) {
                frameLayoutArr[i2].setBackgroundResource(R.drawable.game_fanfanle_selector);
                frameLayoutArr[i2].setSelected(true);
            } else if (this.ap == 0) {
                frameLayoutArr[i2].setClickable(false);
                frameLayoutArr[i2].setBackgroundResource(R.drawable.game_fanfanle_bet_invalid);
            } else {
                frameLayoutArr[i2].setClickable(true);
                frameLayoutArr[i2].setBackgroundResource(R.drawable.game_fanfanle_selector);
                frameLayoutArr[i2].setSelected(false);
            }
        }
    }

    private void h(int i) {
        this.mLiveBaseActivity.sendLiveMessageAndRefreshUI(LiveMessage.createGameAnchorSelectMessage(AppUtils.getInstance().getUserInfoProvider().getUserId(), this.a.getGameId(), this.a.getGameRoundId(), i));
    }
}
