package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.GameResult;
import qsbk.app.live.model.GameRole;
import qsbk.app.live.model.LiveGameBetMessage;
import qsbk.app.live.model.LiveGameDataMessage;
import qsbk.app.live.model.LiveGameMessage;

public class YPDXGameView extends GameView {
    private View J;
    private View K;
    private View L;
    private ImageView M;
    private View N;
    private TextView O;
    private ImageView P;
    private ImageView Q;
    private ImageView R;
    private ImageView S;
    private ImageView T;
    private ImageView U;
    private ImageView V;
    private GameDiceView W;
    private FrameLayout aa;
    private int[] ab = new int[]{R.drawable.live_game_ypdx_flag_dada, R.drawable.live_game_ypdx_flag_baobao, R.drawable.live_game_ypdx_flag_xiaoxiao};

    public YPDXGameView(Context context) {
        super(context);
    }

    public YPDXGameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public YPDXGameView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected int getLayoutId() {
        return R.layout.live_game_view_ypdx;
    }

    protected void a(AttributeSet attributeSet, int i) {
        super.a(attributeSet, i);
        this.J = this.G.findViewById(R.id.fl_win_bubble_1);
        this.K = this.G.findViewById(R.id.fl_win_bubble_2);
        this.L = this.G.findViewById(R.id.fl_win_bubble_3);
        this.M = (ImageView) this.G.findViewById(R.id.iv_win_bubble_2);
        this.N = this.G.findViewById(R.id.live_game_dice_bg);
        this.O = (TextView) this.G.findViewById(R.id.tv_dice_value);
        this.P = (ImageView) this.G.findViewById(R.id.live_game_ypdx_light);
        this.Q = (ImageView) this.G.findViewById(R.id.live_game_ypdx_flag);
        this.R = (ImageView) this.G.findViewById(R.id.live_game_ypdx_flag_left);
        this.S = (ImageView) this.G.findViewById(R.id.live_game_ypdx_flag_right);
        this.T = (ImageView) this.G.findViewById(R.id.live_game_ypdx_cup_pan);
        this.U = (ImageView) this.G.findViewById(R.id.live_game_ypdx_pan);
        this.V = (ImageView) this.G.findViewById(R.id.live_game_ypdx_cup);
        this.W = (GameDiceView) this.G.findViewById(R.id.live_game_ypdx_dice);
        this.aa = (FrameLayout) this.G.findViewById(R.id.fl_dice);
        String gameExplainText = ConfigInfoUtil.instance().getGameExplainText("2");
        if (TextUtils.isEmpty(gameExplainText)) {
            gameExplainText = "4-18";
        }
        this.x.setText("顺宝x" + gameExplainText);
    }

    protected void a() {
        a(this.n, -WindowUtils.dp2Px(40), 0.75f);
        a(this.p, -WindowUtils.dp2Px(40), 0.75f);
        a(this.r, -WindowUtils.dp2Px(40), 0.75f);
        a(this.o, this.w, null);
        a(this.q, this.x, null);
        a(this.s, this.y, null);
    }

    protected void a(LiveGameMessage liveGameMessage) {
        LiveGameDataMessage liveGameDataMessage = (LiveGameDataMessage) liveGameMessage;
        a(liveGameDataMessage.getGameRoleBetData());
        this.A = liveGameDataMessage.getCountDownDuration() - 4;
        i();
        b();
        this.c.setVisibility(0);
        this.d.setVisibility(4);
        a(true);
    }

    protected void c() {
        i();
        super.c();
        b(this.t, -WindowUtils.dp2Px(30), 1.33f);
        b(this.u, -WindowUtils.dp2Px(30), 1.33f);
        b(this.v, -WindowUtils.dp2Px(30), 1.33f);
    }

    private void i() {
        this.J.setVisibility(8);
        this.K.setVisibility(8);
        this.L.setVisibility(8);
        this.V.setVisibility(8);
        this.U.setVisibility(8);
        this.W.setVisibility(8);
        this.N.setAlpha(0.0f);
        ViewCompat.setScaleX(this.W, 1.0f);
        ViewCompat.setScaleY(this.W, 1.0f);
        ViewCompat.setTranslationY(this.W, 0.0f);
        ViewCompat.setTranslationX(this.V, 0.0f);
        ViewCompat.setTranslationY(this.V, 0.0f);
        ViewCompat.setRotation(this.V, 0.0f);
        ViewCompat.setTranslationY(this.U, 0.0f);
    }

    private void a(boolean z) {
        AnimatorSet a = a(this.aa, z);
        a.addListener(new jt(this, z));
        a.start();
        this.V.setVisibility(0);
        this.U.setVisibility(0);
    }

    private AnimatorSet a(View view, boolean z) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, (float) (-WindowUtils.dp2Px(65))});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.0f, 0.32f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.0f, 0.32f});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.N, View.ALPHA, new float[]{1.0f, 0.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3, ofFloat4});
        animatorSet.setDuration(320);
        if (!z) {
            return animatorSet;
        }
        ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) WindowUtils.dp2Px(200), 0.0f});
        ofFloat2 = ObjectAnimator.ofFloat(this.N, View.ALPHA, new float[]{0.0f, 1.0f});
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

    private void a(boolean z, boolean z2) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.U, View.TRANSLATION_Y, new float[]{0.0f, (float) WindowUtils.dp2Px(200)});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.W, View.TRANSLATION_Y, new float[]{0.0f, (float) (-WindowUtils.dp2Px(110))});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.W, View.SCALE_X, new float[]{1.0f, 0.6f});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.W, View.SCALE_Y, new float[]{1.0f, 0.6f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3, ofFloat4});
        animatorSet.setDuration(400);
        this.U.setVisibility(0);
        this.W.setVisibility(0);
        if (z) {
            ofFloat = ObjectAnimator.ofFloat(this.aa, View.TRANSLATION_Y, new float[]{(float) (-WindowUtils.dp2Px(60)), 0.0f});
            ofFloat2 = ObjectAnimator.ofFloat(this.aa, View.SCALE_X, new float[]{0.32f, 1.0f});
            ofFloat3 = ObjectAnimator.ofFloat(this.aa, View.SCALE_Y, new float[]{0.32f, 1.0f});
            ofFloat4 = ObjectAnimator.ofFloat(this.N, View.ALPHA, new float[]{0.0f, 1.0f});
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3, ofFloat4});
            animatorSet2.setDuration(320);
            ofFloat = ObjectAnimator.ofFloat(this.V, View.TRANSLATION_Y, new float[]{0.0f, (float) (-WindowUtils.dp2Px(60))});
            ofFloat2 = ObjectAnimator.ofFloat(this.V, View.ROTATION, new float[]{0.0f, 40.0f});
            AnimatorSet animatorSet3 = new AnimatorSet();
            animatorSet3.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet3.setDuration(800);
            ObjectAnimator.ofFloat(this.V, View.TRANSLATION_X, new float[]{0.0f, (float) WindowUtils.dp2Px(300)}).setDuration(300);
            ObjectAnimator.ofFloat(this.U, View.TRANSLATION_X, new float[]{0.0f, 0.0f}).setDuration(800);
            new AnimatorSet().playSequentially(new Animator[]{animatorSet3, ofFloat, ofFloat2});
            ObjectAnimator.ofFloat(this.P, View.ALPHA, new float[]{0.0f, 1.0f}).setDuration(800);
            ObjectAnimator.ofFloat(this.P, View.ALPHA, new float[]{1.0f, 1.0f}).setDuration(800);
            ObjectAnimator.ofFloat(this.P, View.ALPHA, new float[]{1.0f, 0.0f}).setDuration(300);
            new AnimatorSet().playSequentially(new Animator[]{ofFloat, ofFloat2, ofFloat3});
            AnimatorSet animatorSet4 = new AnimatorSet();
            if (z2) {
                animatorSet4.playTogether(new Animator[]{r3, r6, e(this.R), e(this.S), e(this.O)});
            } else {
                animatorSet4.playTogether(new Animator[]{r3, r6, e(this.Q), e(this.O)});
            }
            AnimatorSet animatorSet5 = new AnimatorSet();
            animatorSet5.playSequentially(new Animator[]{animatorSet2, animatorSet4, animatorSet});
            animatorSet5.addListener(new jv(this));
            this.V.setVisibility(0);
            this.T.setVisibility(8);
            animatorSet5.start();
            return;
        }
        animatorSet.addListener(new ju(this));
        animatorSet.start();
    }

    private AnimatorSet e(View view) {
        ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.0f, 0.0f}).setDuration(400);
        ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 1.0f}).setDuration(1300);
        ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 0.0f}).setDuration(200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(new Animator[]{r0, r1, r2});
        return animatorSet;
    }

    protected void a(LiveGameDataMessage liveGameDataMessage) {
        List gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        if (isValidRolesData(gameRoleBetData)) {
            GameResult gameResult = ((GameRole) gameRoleBetData.get(0)).getGameResult();
            if (gameResult != null) {
                long result = (long) gameResult.getResult();
                boolean isShunZi = gameResult.isShunZi();
                a(gameResult, result, isShunZi);
                a(true, isShunZi);
                this.m.postDelayed(new jw(this, result, isShunZi), 3000);
                if (result <= 3 && result > 0) {
                    if (isShunZi) {
                        this.R.setImageResource(this.ab[((int) result) - 1]);
                    } else {
                        this.Q.setImageResource(this.ab[((int) result) - 1]);
                    }
                }
            }
        }
        long countDownDuration = liveGameDataMessage.getCountDownDuration() - 2;
        if (countDownDuration > 8) {
            countDownDuration = 8;
        }
        if (countDownDuration < 0) {
            countDownDuration = 0;
        }
        this.m.removeCallbacks(this.H);
        this.m.postDelayed(this.H, countDownDuration * 1000);
    }

    private void a(GameResult gameResult, long j, boolean z) {
        List<Integer> resultGroup = gameResult.getResultGroup();
        this.W.setDiceValue(resultGroup);
        if (j == 2) {
            this.O.setText("豹子");
            return;
        }
        int i = 0;
        for (Integer intValue : resultGroup) {
            i = intValue.intValue() + i;
        }
        CharSequence charSequence = i + "点";
        if (z) {
            charSequence = charSequence + "、顺子";
        }
        this.O.setText(charSequence);
    }

    protected void a(long j, boolean z) {
        this.N.setAlpha(0.0f);
        super.a(j, z);
        this.J.setVisibility(8);
        this.K.setVisibility(8);
        this.L.setVisibility(8);
        if (j == this.w.getRoleId()) {
            this.J.setVisibility(0);
        } else if (j == this.y.getRoleId()) {
            this.L.setVisibility(0);
        } else if (j == this.x.getRoleId()) {
            this.M.setImageResource(R.drawable.live_game_ypdx_result_baobao);
            this.K.setVisibility(0);
        }
        if (z) {
            this.M.setImageResource(R.drawable.live_game_ypdx_result_shunzi);
            this.K.setVisibility(0);
        }
    }

    public String getRoleName(long j) {
        if (j == this.w.getRoleId()) {
            return "哒哒";
        }
        if (j == this.x.getRoleId()) {
            return "顺宝";
        }
        if (j == this.y.getRoleId()) {
            return "晓晓";
        }
        return null;
    }

    protected void b(LiveGameDataMessage liveGameDataMessage) {
        super.b(liveGameDataMessage);
        a(false);
    }

    protected void b(List<GameRole> list) {
        GameResult gameResult = ((GameRole) list.get(0)).getGameResult();
        if (gameResult != null) {
            long result = (long) gameResult.getResult();
            boolean isShunZi = gameResult.isShunZi();
            a(gameResult, result, isShunZi);
            a(false, isShunZi);
            a(result, isShunZi);
        }
        super.b((List) list);
    }

    protected void a(View view) {
    }

    protected void b(View view) {
        a(view, -WindowUtils.dp2Px(46));
    }

    protected void a(LiveGameBetMessage liveGameBetMessage, GameBetView gameBetView) {
        a(liveGameBetMessage, gameBetView, WindowUtils.dp2Px(136));
    }
}
