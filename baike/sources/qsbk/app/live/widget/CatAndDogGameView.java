package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.connect.common.Constants;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.GameRole;
import qsbk.app.live.model.LiveGameBetMessage;
import qsbk.app.live.model.LiveGameDataMessage;
import qsbk.app.live.model.LiveGameMessage;

public class CatAndDogGameView extends GameView {
    private PokerGroup J;
    private PokerGroup K;

    public CatAndDogGameView(Context context) {
        super(context);
    }

    public CatAndDogGameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CatAndDogGameView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected int getLayoutId() {
        return R.layout.live_game_view_catanddog;
    }

    protected void a(AttributeSet attributeSet, int i) {
        super.a(attributeSet, i);
        this.J = (PokerGroup) this.G.findViewById(R.id.poker_group_1);
        this.K = (PokerGroup) this.G.findViewById(R.id.poker_group_3);
    }

    public void setGameId(long j) {
        super.setGameId(j);
        i();
    }

    private void i() {
        Object gameExplainText = ConfigInfoUtil.instance().getGameExplainText(getGameId() + "");
        String str = "2.2";
        String str2 = Constants.VIA_SHARE_TYPE_INFO;
        if (!TextUtils.isEmpty(gameExplainText)) {
            String[] split = gameExplainText.split("\\|");
            if (split != null && split.length == 2) {
                str = split[0];
                str2 = split[1];
            }
        }
        this.w.setText("妙妙×" + str);
        this.x.setText("平局×" + str2);
        this.y.setText("旺旺×" + str);
    }

    protected void a(List<GameRole> list) {
        super.a((List) list);
        this.J.reset();
        this.K.reset();
    }

    protected void a() {
        a(this.n, -WindowUtils.dp2Px(50), 0.76f);
        a(this.r, WindowUtils.dp2Px(50), 0.76f);
        a(this.o, this.w, this.J);
        a(this.s, this.y, this.K);
        postDelayed(new i(this), 200);
    }

    protected void b() {
        super.b();
        d(this.J);
        d(this.K);
        this.J.cancelPokeGroupAnim();
        this.K.cancelPokeGroupAnim();
    }

    protected void c() {
        super.c();
        b(this.t, -WindowUtils.dp2Px(50), 1.31f);
        b(this.v, WindowUtils.dp2Px(50), 1.31f);
        b(this.J);
        b(this.K);
    }

    private PokerGroup c(long j) {
        if (j == this.w.getRoleId()) {
            return this.J;
        }
        if (j == this.y.getRoleId()) {
            return this.K;
        }
        return null;
    }

    protected void a(LiveGameDataMessage liveGameDataMessage) {
        long j = 0;
        List gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        if (isValidRolesData(gameRoleBetData)) {
            Collections.sort(gameRoleBetData, this.C);
            GameRole gameRole = (GameRole) gameRoleBetData.get(0);
            Collections.sort(gameRoleBetData, this.B);
            int i = 0;
            while (i < gameRoleBetData.size()) {
                this.m.postDelayed(new j(this, (GameRole) gameRoleBetData.get(i)), i == 0 ? 0 : 2000);
                i += 2;
            }
            this.m.postDelayed(new k(this, gameRole), 4000);
        }
        long countDownDuration = liveGameDataMessage.getCountDownDuration() - 2;
        if (countDownDuration > 9) {
            countDownDuration = 9;
        }
        if (countDownDuration >= 0) {
            j = countDownDuration;
        }
        this.m.removeCallbacks(this.H);
        this.m.postDelayed(this.H, 1000 * j);
    }

    public String getRoleName(long j) {
        if (j == this.w.getRoleId()) {
            return "妙妙";
        }
        if (j == this.y.getRoleId()) {
            return "旺旺";
        }
        return null;
    }

    protected void b(List<GameRole> list) {
        super.b((List) list);
        for (int i = 0; i < list.size(); i++) {
            GameRole gameRole = (GameRole) list.get(i);
            PokerGroup c = c(gameRole.getRoleId());
            if (c != null) {
                c.loadPokers(gameRole.getGameResult());
            }
        }
        Collections.sort(list, this.C);
        b(((GameRole) list.get(0)).getRoleId());
    }

    protected void a(View view) {
        a((PokerGroup) view);
    }

    private void a(PokerGroup pokerGroup) {
        if (pokerGroup != null) {
            pokerGroup.setVisibility(0);
            pokerGroup.startPokeGroupAnim();
        }
    }

    private void b(PokerGroup pokerGroup) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(pokerGroup, View.SCALE_X, new float[]{1.0f, 0.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(pokerGroup, View.SCALE_Y, new float[]{1.0f, 0.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setDuration(320);
        animatorSet.start();
    }

    protected void a(LiveGameMessage liveGameMessage) {
        super.a(liveGameMessage);
        g((LiveGameDataMessage) liveGameMessage);
    }

    private void g(LiveGameDataMessage liveGameDataMessage) {
        List<GameRole> gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        if (gameRoleBetData != null) {
            for (GameRole gameRole : gameRoleBetData) {
                long roleId = gameRole.getRoleId();
                List resultGroup = gameRole.getResultGroup();
                if (resultGroup != null && resultGroup.size() > 0) {
                    if (roleId == 1) {
                        this.J.loadPokers(Arrays.asList(new Integer[]{(Integer) resultGroup.get(0)}));
                    } else if (roleId == 3) {
                        this.K.loadPokers(Arrays.asList(new Integer[]{(Integer) resultGroup.get(0)}));
                    }
                }
            }
        }
    }

    protected void b(View view) {
        a(view, -WindowUtils.dp2Px(38));
    }

    protected void b(LiveGameDataMessage liveGameDataMessage) {
        super.b(liveGameDataMessage);
        g(liveGameDataMessage);
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
            roleId = iArr[0] + ((WindowUtils.dp2Px(66) - WindowUtils.dp2Px(25)) / 2);
            if (gameBetView == this.w) {
                layoutParams.leftMargin = roleId - WindowUtils.dp2Px(60);
            } else if (gameBetView == this.y) {
                layoutParams.leftMargin = roleId + WindowUtils.dp2Px(66);
            } else {
                layoutParams.leftMargin = roleId;
            }
            layoutParams.topMargin = i;
            simpleDraweeView.setLayoutParams(layoutParams);
            AppUtils.getInstance().getImageProvider().loadAvatar(simpleDraweeView, userAvatar);
            addView(simpleDraweeView);
            ImageView imageView = new ImageView(getContext());
            LayoutParams layoutParams2 = new FrameLayout.LayoutParams(WindowUtils.dp2Px(11), WindowUtils.dp2Px(9));
            layoutParams2.leftMargin = layoutParams.leftMargin + WindowUtils.dp2Px(7);
            layoutParams2.topMargin = i - WindowUtils.dp2Px(50);
            imageView.setLayoutParams(layoutParams2);
            imageView.setImageResource(R.drawable.live_game_bet_love);
            imageView.setScaleType(ScaleType.FIT_XY);
            int i2 = -WindowUtils.dp2Px(50);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(simpleDraweeView, View.TRANSLATION_Y, new float[]{0.0f, (float) i2});
            ofFloat.setDuration(500);
            ofFloat.setInterpolator(new DecelerateInterpolator());
            ObjectAnimator.ofFloat(simpleDraweeView, View.ALPHA, new float[]{1.0f, 0.0f}).setDuration(500);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(new Animator[]{ofFloat, r3});
            animatorSet.addListener(new l(this, simpleDraweeView));
            animatorSet.start();
            if (gameBetView == this.w) {
                i2 = WindowUtils.dp2Px(40);
            } else if (gameBetView == this.y) {
                i2 = -WindowUtils.dp2Px(83);
            } else {
                i2 = -WindowUtils.dp2Px(24);
            }
            ofFloat = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, new float[]{0.0f, (float) i2});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, new float[]{0.0f, (float) WindowUtils.dp2Px(23)});
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.setInterpolator(new DecelerateInterpolator());
            animatorSet2.setDuration(250);
            animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet2.addListener(new m(this, imageView));
            this.m.postDelayed(new n(this, imageView, animatorSet2), 500);
        }
    }
}
