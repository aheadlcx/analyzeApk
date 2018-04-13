package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.GameResult;
import qsbk.app.live.model.GameRole;
import qsbk.app.live.model.LiveGameBetMessage;
import qsbk.app.live.model.LiveGameDataMessage;
import qsbk.app.live.model.LiveGameMessage;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.ui.LiveBaseActivity;

public class GuessBigOrSmallGameView extends GameView {
    private int J = -1;
    private boolean K = true;
    private int L = -1;
    private DonutProgress M;
    private GuessBetView N;
    private GuessResultView O;
    private ImageView P;
    private TextView Q;
    private ImageView R;
    private ImageView S;
    private ImageView T;
    private ImageView U;
    private ImageView V;
    private ImageView W;
    private GameDiceView aa;
    private FrameLayout ab;
    private View ac;
    private TextView ad;
    private int[] ae = new int[]{R.drawable.live_game_guess_open_small, R.drawable.live_game_guess_open_big};

    public GuessBigOrSmallGameView(Context context) {
        super(context);
    }

    public GuessBigOrSmallGameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GuessBigOrSmallGameView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected int getLayoutId() {
        return R.layout.live_game_view_guess;
    }

    protected void a(AttributeSet attributeSet, int i) {
        this.mLiveBaseActivity = (LiveBaseActivity) getContext();
        this.D = this.mLiveBaseActivity.mDialog;
        this.m = new Handler();
        this.G = View.inflate(getContext(), getLayoutId(), this);
        this.b = this.G.findViewById(R.id.game_container);
        this.M = (DonutProgress) this.G.findViewById(R.id.progress_view);
        this.N = (GuessBetView) this.G.findViewById(R.id.guess_bet_view);
        this.O = (GuessResultView) this.G.findViewById(R.id.guess_result_view);
        this.P = (ImageView) this.G.findViewById(R.id.iv_help);
        this.ac = this.G.findViewById(R.id.live_game_dice_bg);
        this.z = (TextView) this.G.findViewById(R.id.tv_tips);
        this.Q = (TextView) this.G.findViewById(R.id.tv_dice_value);
        this.R = (ImageView) this.G.findViewById(R.id.live_game_ypdx_light);
        this.S = (ImageView) this.G.findViewById(R.id.live_game_ypdx_flag);
        this.T = (ImageView) this.G.findViewById(R.id.live_game_ypdx_cup_pan);
        this.U = (ImageView) this.G.findViewById(R.id.live_game_ypdx_cup_pan_top);
        this.V = (ImageView) this.G.findViewById(R.id.live_game_ypdx_pan);
        this.W = (ImageView) this.G.findViewById(R.id.live_game_ypdx_cup);
        this.aa = (GameDiceView) this.G.findViewById(R.id.live_game_ypdx_dice);
        this.ab = (FrameLayout) this.G.findViewById(R.id.fl_dice);
        this.ad = (TextView) this.G.findViewById(R.id.result_tips);
        this.P.setOnClickListener(this);
        if (!AppUtils.getInstance().getUserInfoProvider().isLogin()) {
            d();
        }
        this.B = new ep(this);
        this.C = new et(this);
    }

    protected void a() {
    }

    protected void a(LiveGameMessage liveGameMessage) {
        LiveGameDataMessage liveGameDataMessage = (LiveGameDataMessage) liveGameMessage;
        a(liveGameDataMessage.getGameRoleBetData());
        this.A = liveGameDataMessage.getCountDownDuration() - 4;
        i();
        b();
        a(true);
    }

    protected void c() {
    }

    private void i() {
        this.U.setVisibility(0);
        this.W.setVisibility(8);
        this.V.setVisibility(8);
        this.aa.setVisibility(8);
        this.ac.setAlpha(0.0f);
        ViewCompat.setScaleX(this.aa, 1.0f);
        ViewCompat.setScaleY(this.aa, 1.0f);
        ViewCompat.setTranslationY(this.aa, 0.0f);
        ViewCompat.setTranslationX(this.W, 0.0f);
        ViewCompat.setTranslationY(this.W, 0.0f);
        ViewCompat.setRotation(this.W, 0.0f);
        ViewCompat.setTranslationY(this.V, 0.0f);
        ViewCompat.setAlpha(this.S, 0.0f);
    }

    private void a(boolean z) {
        AnimatorSet a = a(this.ab, z);
        a.addListener(new eu(this, z));
        a.start();
        this.W.setVisibility(0);
        this.V.setVisibility(0);
    }

    private AnimatorSet a(View view, boolean z) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, (float) (-WindowUtils.dp2Px(65))});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.0f, 0.32f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.0f, 0.32f});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.ac, View.ALPHA, new float[]{1.0f, 0.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3, ofFloat4});
        animatorSet.setDuration(320);
        if (!z) {
            return animatorSet;
        }
        ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) WindowUtils.dp2Px(200), 0.0f});
        ofFloat2 = ObjectAnimator.ofFloat(this.ac, View.ALPHA, new float[]{0.0f, 1.0f});
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet2.setDuration(200);
        ofFloat = ObjectAnimator.ofFloat(view, View.ROTATION, new float[]{0.0f, -20.0f});
        ofFloat2 = ObjectAnimator.ofFloat(view, View.ROTATION, new float[]{-20.0f, 20.0f});
        ofFloat4 = ObjectAnimator.ofFloat(view, View.ROTATION, new float[]{20.0f, -20.0f});
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(view, View.ROTATION, new float[]{-20.0f, 20.0f});
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(view, View.ROTATION, new float[]{20.0f, -20.0f});
        ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(view, View.ROTATION, new float[]{-20.0f, 20.0f});
        ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(view, View.ROTATION, new float[]{20.0f, -20.0f});
        ObjectAnimator ofFloat9 = ObjectAnimator.ofFloat(view, View.ROTATION, new float[]{-20.0f, 20.0f});
        ObjectAnimator ofFloat10 = ObjectAnimator.ofFloat(view, View.ROTATION, new float[]{20.0f, -20.0f});
        ObjectAnimator ofFloat11 = ObjectAnimator.ofFloat(view, View.ROTATION, new float[]{-20.0f, 20.0f});
        ObjectAnimator ofFloat12 = ObjectAnimator.ofFloat(view, View.ROTATION, new float[]{20.0f, 0.0f});
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.playSequentially(new Animator[]{ofFloat, ofFloat2, ofFloat4, ofFloat5, ofFloat6, ofFloat7, ofFloat8, ofFloat9, ofFloat10, ofFloat11, ofFloat12});
        animatorSet3.setDuration(60);
        ofFloat2 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, (float) (-WindowUtils.dp2Px(20))});
        ofFloat2.setDuration(160);
        ofFloat4 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) (-WindowUtils.dp2Px(20)), 0.0f});
        ofFloat2.setDuration(80);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, 0.0f}).setDuration(320);
        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.playSequentially(new Animator[]{animatorSet2, animatorSet3, ofFloat2, ofFloat4, ofFloat5, animatorSet});
        return animatorSet4;
    }

    private AnimatorSet e(View view) {
        ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.0f, 0.0f}).setDuration(400);
        ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 1.0f}).setDuration(1300);
        ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 0.0f}).setDuration(200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(new Animator[]{r0, r1, r2});
        return animatorSet;
    }

    private AnimatorSet f(View view) {
        ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.0f, 0.0f}).setDuration(400);
        ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 1.0f}).setDuration(1300);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(new Animator[]{r0, r1});
        return animatorSet;
    }

    public boolean isValidRolesData(List<GameRole> list) {
        return list != null && list.size() == 2;
    }

    protected void setBetEnable(boolean z) {
        i();
        b();
    }

    protected void b(LiveGameMessage liveGameMessage) {
        cancelCountDownTimer();
        this.M.setVisibility(4);
        this.T.setVisibility(4);
        this.U.setVisibility(0);
        a((LiveGameDataMessage) liveGameMessage);
    }

    protected void a(LiveGameDataMessage liveGameDataMessage) {
        List gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        if (isValidRolesData(gameRoleBetData)) {
            GameResult gameResult = ((GameRole) gameRoleBetData.get(0)).getGameResult();
            if (gameResult != null) {
                long result = (long) gameResult.getResult();
                a(gameResult, result, gameResult.isShunZi());
                if (result <= 2 && result > 0) {
                    this.S.setImageResource(this.ae[(int) (result - 1)]);
                }
                a(true, false);
                this.O.setResult(liveGameDataMessage.getStep(), result);
                this.N.setData(gameRoleBetData);
            }
        }
    }

    protected void a(boolean z, boolean z2) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.V, View.TRANSLATION_Y, new float[]{0.0f, (float) WindowUtils.dp2Px(200)});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.aa, View.TRANSLATION_Y, new float[]{0.0f, (float) WindowUtils.dp2Px(200)});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setDuration(1000);
        this.V.setVisibility(0);
        this.aa.setVisibility(0);
        ofFloat = ObjectAnimator.ofFloat(this.ab, View.TRANSLATION_Y, new float[]{(float) (-WindowUtils.dp2Px(60)), 0.0f});
        ofFloat2 = ObjectAnimator.ofFloat(this.ab, View.SCALE_X, new float[]{0.32f, 1.0f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.ab, View.SCALE_Y, new float[]{0.32f, 1.0f});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.ac, View.ALPHA, new float[]{0.0f, 1.0f});
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3, ofFloat4});
        animatorSet2.setDuration(320);
        ofFloat = ObjectAnimator.ofFloat(this.W, View.TRANSLATION_Y, new float[]{0.0f, (float) (-WindowUtils.dp2Px(60))});
        ofFloat2 = ObjectAnimator.ofFloat(this.W, View.ROTATION, new float[]{0.0f, 40.0f});
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet3.setDuration(800);
        ObjectAnimator.ofFloat(this.W, View.TRANSLATION_X, new float[]{0.0f, (float) WindowUtils.dp2Px(300)}).setDuration(300);
        ObjectAnimator.ofFloat(this.V, View.TRANSLATION_X, new float[]{0.0f, 0.0f}).setDuration(800);
        new AnimatorSet().playSequentially(new Animator[]{animatorSet3, ofFloat, ofFloat2});
        ObjectAnimator.ofFloat(this.R, View.ALPHA, new float[]{0.0f, 1.0f}).setDuration(800);
        ObjectAnimator.ofFloat(this.R, View.ALPHA, new float[]{1.0f, 1.0f}).setDuration(800);
        ObjectAnimator.ofFloat(this.R, View.ALPHA, new float[]{1.0f, 0.0f}).setDuration(300);
        new AnimatorSet().playSequentially(new Animator[]{ofFloat, ofFloat2, ofFloat3});
        new AnimatorSet().playTogether(new Animator[]{r4, r6, f(this.S), e(this.Q)});
        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.playSequentially(new Animator[]{animatorSet2, r0, animatorSet});
        animatorSet4.addListener(new ev(this));
        this.W.setVisibility(0);
        this.T.setVisibility(8);
        animatorSet4.start();
    }

    private void a(GameResult gameResult, long j, boolean z) {
        this.aa.setDiceValue(gameResult.getResultGroup());
    }

    protected void a(long j, boolean z) {
    }

    public String getRoleName(long j) {
        return "";
    }

    protected void b(LiveGameDataMessage liveGameDataMessage) {
        long countDownDuration = liveGameDataMessage.getCountDownDuration();
        if (countDownDuration >= 2) {
            a(R.string.live_game_start);
        } else {
            this.z.setVisibility(4);
        }
        if (countDownDuration > 0) {
            setBetEnable(true);
            this.U.setVisibility(8);
            a(countDownDuration);
        } else {
            setBetEnable(false);
            this.M.setVisibility(4);
            this.U.setVisibility(0);
            a(R.string.live_game_result, true);
        }
        List gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        if (gameRoleBetData != null && gameRoleBetData.size() > 0) {
            for (int i = 0; i < gameRoleBetData.size(); i++) {
                GameRole gameRole = (GameRole) gameRoleBetData.get(i);
                if (gameRole.getMeBet() > 0) {
                    this.N.setChoice((int) gameRole.getRoleId());
                    return;
                }
            }
        }
    }

    protected void e(LiveGameDataMessage liveGameDataMessage) {
        List gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        if (gameRoleBetData != null && gameRoleBetData.size() > 0) {
            this.N.setChoice((int) ((GameRole) gameRoleBetData.get(0)).getRoleId());
        }
    }

    protected void b(List<GameRole> list) {
        GameResult gameResult = ((GameRole) list.get(0)).getGameResult();
        if (gameResult != null) {
            a(gameResult, (long) gameResult.getResult(), false);
        }
        this.N.setData((List) list);
    }

    protected void a(View view) {
    }

    protected void b(View view) {
        a(view, -WindowUtils.dp2Px(46));
    }

    protected void a(LiveGameBetMessage liveGameBetMessage, GameBetView gameBetView) {
        a(liveGameBetMessage, gameBetView, WindowUtils.dp2Px(136));
    }

    protected void c(LiveGameDataMessage liveGameDataMessage) {
        if (this.M.getVisibility() == 0) {
            this.U.setVisibility(8);
            a(liveGameDataMessage.getCountDownDuration());
            return;
        }
        d();
    }

    protected void d() {
        this.M.setVisibility(4);
        this.U.setVisibility(0);
        a(R.string.live_game_ready, true);
        setBetEnable(false);
    }

    protected void a(List<GameRole> list) {
        Collections.sort(list, this.B);
        this.N.initData(list);
    }

    protected void d(LiveGameDataMessage liveGameDataMessage) {
        if (c(liveGameDataMessage.getGameRoleBetData())) {
            d((LiveGameMessage) liveGameDataMessage);
            List gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
            if (gameRoleBetData != null && gameRoleBetData.size() > 0) {
                for (int i = 0; i < gameRoleBetData.size(); i++) {
                    GameRole gameRole = (GameRole) gameRoleBetData.get(i);
                    if (gameRole.getMeBet() > 0) {
                        this.N.setChoice((int) gameRole.getRoleId());
                        return;
                    }
                }
                return;
            }
            return;
        }
        d();
    }

    protected void d(LiveGameMessage liveGameMessage) {
        cancelCountDownTimer();
        setBetEnable(false);
        List gameRoleBetData = ((LiveGameDataMessage) liveGameMessage).getGameRoleBetData();
        if (isValidRolesData(gameRoleBetData)) {
            b(gameRoleBetData);
        }
    }

    protected void f(LiveGameDataMessage liveGameDataMessage) {
        a(liveGameDataMessage.getGameRoleBetData());
    }

    public void updateBetData(LiveGameBetMessage liveGameBetMessage) {
        this.N.setData(liveGameBetMessage.getGameRole());
    }

    public void updateBalance(long j) {
    }

    protected void b() {
        cancelCountDownTimer();
        this.M.setVisibility(4);
        this.O.reset();
        this.N.reset();
        this.N.setNumToZero();
        this.N.setUseable(this.K);
        this.N.setmListenner(new ew(this));
    }

    protected void f() {
        this.m.postDelayed(new ey(this), 400);
    }

    protected void g() {
        this.M.setVisibility(0);
        this.M.setMax((int) this.A);
        this.M.setProgress((int) this.A);
        this.T.setVisibility(8);
        a(this.A);
    }

    protected void a(long j) {
        this.l = new fa(this, j * 1000, 1000);
        this.l.start();
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
                this.J = ((LiveGameDataMessage) liveGameMessage).getStep();
                if (this.J == -1) {
                    this.J = 0;
                }
                this.K = true;
                a(liveGameMessage);
            } else if (messageType == 49) {
                this.a = liveGameMessage;
                LiveGameDataMessage liveGameDataMessage = (LiveGameDataMessage) liveGameMessage;
                this.J = liveGameDataMessage.getStep();
                this.L = liveGameDataMessage.getVoteStatus();
                if (this.L == 1) {
                    this.K = true;
                } else {
                    this.K = false;
                }
                List gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
                if (isValidRolesData(gameRoleBetData)) {
                    a(gameRoleBetData);
                    i();
                    b();
                    messageType = liveGameDataMessage.getGameStatus();
                    if (messageType == 0) {
                        c(liveGameDataMessage);
                    } else if (messageType == 1) {
                        b(liveGameDataMessage);
                    } else if (messageType == 2) {
                        d(liveGameDataMessage);
                    }
                }
            } else if (messageType == 46) {
            } else {
                if (messageType == 42) {
                    e((LiveGameDataMessage) liveGameMessage);
                } else if (messageType == 45) {
                    b(liveGameMessage);
                } else if (messageType == 47) {
                    c(liveGameMessage);
                } else if (messageType == 108) {
                    e(liveGameMessage);
                }
            }
        }
    }

    protected void c(LiveGameMessage liveGameMessage) {
        LiveGameBetMessage liveGameBetMessage = (LiveGameBetMessage) liveGameMessage;
        if (liveGameBetMessage.useFhToWin()) {
            this.ad.setVisibility(0);
            this.ad.setText("您猜错啦！已使用复活卡，继续游戏");
            this.m.postDelayed(new fb(this), Config.BPLUS_DELAY_TIME);
            this.mLiveBaseActivity.setFuhuokaCount(liveGameBetMessage.getFhkCount());
        } else if (liveGameBetMessage.isWin()) {
            this.ad.setVisibility(0);
            this.ad.setText("恭喜您答对了");
            this.m.postDelayed(new fc(this), Config.BPLUS_DELAY_TIME);
        } else if (liveGameBetMessage.isLose()) {
            this.ad.setVisibility(0);
            this.ad.setText("很遗憾！您猜错了，游戏结束");
            this.m.postDelayed(new eq(this), Config.BPLUS_DELAY_TIME);
        }
    }

    protected void e(LiveGameMessage liveGameMessage) {
        LiveGameDataMessage liveGameDataMessage = (LiveGameDataMessage) liveGameMessage;
        long gameResult = liveGameDataMessage.getGameResult();
        String gameResultContent = liveGameDataMessage.getGameResultContent();
        if (gameResult == 1) {
            if (this.D != null && this.D.isShowing()) {
                this.D.dismiss();
            }
            this.D = new GameGuessHelpDialog(this.mLiveBaseActivity, 1997, gameResultContent, new er(this), liveGameDataMessage);
            this.D.show();
        } else if (gameResult == 0) {
            if (this.D != null && this.D.isShowing()) {
                this.D.dismiss();
            }
            this.D = new GameGuessHelpDialog(this.mLiveBaseActivity, 1998, gameResultContent, new es(this), liveGameDataMessage);
            this.D.show();
        }
    }

    public void share(int i, LiveMessage liveMessage) {
        if (liveMessage != null && this.mLiveBaseActivity != null) {
            this.mLiveBaseActivity.share(i, liveMessage);
        }
    }

    public void onClick(View view) {
        super.onClick(view);
    }

    public void setGuessHistoryList(List<Integer> list) {
        super.setGuessHistoryList(list);
        if (list != null && list.size() > 0 && this.O != null) {
            this.O.setResult((ArrayList) list);
        }
    }

    public void reset() {
        i();
        b();
        this.O.clear();
    }
}
