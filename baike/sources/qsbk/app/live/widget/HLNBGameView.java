package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import java.util.Collections;
import java.util.List;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.GameRole;
import qsbk.app.live.model.LiveGameDataMessage;

public class HLNBGameView extends GameView {
    private PokerGroup J;
    private PokerGroup K;
    private PokerGroup L;

    public HLNBGameView(Context context) {
        super(context);
    }

    public HLNBGameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HLNBGameView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected int getLayoutId() {
        return R.layout.live_game_view_hlnb;
    }

    protected void a(AttributeSet attributeSet, int i) {
        super.a(attributeSet, i);
        this.J = (PokerGroup) this.G.findViewById(R.id.poker_group_1);
        this.K = (PokerGroup) this.G.findViewById(R.id.poker_group_2);
        this.L = (PokerGroup) this.G.findViewById(R.id.poker_group_3);
    }

    protected void a(List<GameRole> list) {
        super.a((List) list);
        this.J.reset();
        this.K.reset();
        this.L.reset();
    }

    protected void a() {
        a(this.n, -WindowUtils.dp2Px(50), 0.76f);
        a(this.p, 0, 0.76f);
        a(this.r, WindowUtils.dp2Px(50), 0.76f);
        a(this.o, this.w, this.J);
        a(this.q, this.x, this.K);
        a(this.s, this.y, this.L);
    }

    protected void b() {
        super.b();
        d(this.J);
        d(this.K);
        d(this.L);
        this.J.cancelPokeGroupAnim();
        this.K.cancelPokeGroupAnim();
        this.L.cancelPokeGroupAnim();
    }

    protected void c() {
        super.c();
        b(this.t, -WindowUtils.dp2Px(50), 1.31f);
        b(this.u, 0, 1.31f);
        b(this.v, WindowUtils.dp2Px(50), 1.31f);
        b(this.J);
        b(this.K);
        b(this.L);
    }

    private PokerGroup c(long j) {
        if (j == this.w.getRoleId()) {
            return this.J;
        }
        if (j == this.x.getRoleId()) {
            return this.K;
        }
        if (j == this.y.getRoleId()) {
            return this.L;
        }
        return null;
    }

    protected void a(LiveGameDataMessage liveGameDataMessage) {
        List gameRoleBetData = liveGameDataMessage.getGameRoleBetData();
        if (isValidRolesData(gameRoleBetData)) {
            Collections.sort(gameRoleBetData, this.C);
            GameRole gameRole = (GameRole) gameRoleBetData.get(0);
            Collections.sort(gameRoleBetData, this.B);
            for (int i = 0; i < gameRoleBetData.size(); i++) {
                this.m.postDelayed(new fd(this, (GameRole) gameRoleBetData.get(i)), (long) (i * 2000));
            }
            this.m.postDelayed(new fe(this, gameRole), 6000);
        }
        long countDownDuration = liveGameDataMessage.getCountDownDuration() - 2;
        if (countDownDuration > 11) {
            countDownDuration = 11;
        }
        if (countDownDuration < 0) {
            countDownDuration = 0;
        }
        this.m.removeCallbacks(this.H);
        this.m.postDelayed(this.H, countDownDuration * 1000);
    }

    public String getRoleName(long j) {
        if (j == this.w.getRoleId()) {
            return "热热";
        }
        if (j == this.x.getRoleId()) {
            return "猫猫";
        }
        if (j == this.y.getRoleId()) {
            return "宝宝";
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
}
