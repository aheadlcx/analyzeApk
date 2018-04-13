package qsbk.app.live.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import qsbk.app.core.utils.FormatUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.GameRole;

public class GameBetView extends LinearLayout {
    protected TextView a;
    protected TextView b;
    protected TextView c;
    private ImageView d;
    private LinearLayout e;
    private LinearLayout f;
    private GameRole g;

    public GameBetView(Context context) {
        super(context);
        a(null, 0);
    }

    public GameBetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    public GameBetView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet, i);
    }

    protected void a(AttributeSet attributeSet, int i) {
        setOrientation(1);
        int attributeResourceValue = attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "src", 0);
        CharSequence attributeValue = attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "text");
        View inflate = View.inflate(getContext(), R.layout.live_game_bet_view, this);
        this.d = (ImageView) findViewById(R.id.iv_role_name);
        this.a = (TextView) findViewById(R.id.tv_role_name);
        if (attributeResourceValue > 0) {
            Drawable drawable = getResources().getDrawable(attributeResourceValue);
            drawable.setCallback(this);
            this.d.setImageDrawable(drawable);
            this.d.setVisibility(0);
        } else if (!TextUtils.isEmpty(attributeValue)) {
            this.a.setText(attributeValue);
            this.a.setVisibility(0);
        }
        this.e = (LinearLayout) inflate.findViewById(R.id.ll_bet_total);
        this.f = (LinearLayout) inflate.findViewById(R.id.ll_bet_me);
        this.b = (TextView) inflate.findViewById(R.id.tv_bet_total);
        this.c = (TextView) inflate.findViewById(R.id.tv_bet_me);
    }

    public void initData(GameRole gameRole) {
        this.g = gameRole;
        setBetTotal(0);
        setMeTotal(0);
    }

    public void loadData(GameRole gameRole) {
        if (gameRole != null) {
            setBetTotal(gameRole.getTotalBet());
        }
    }

    public void loadMeData(GameRole gameRole) {
        if (gameRole != null) {
            setBetTotal(gameRole.getTotalBet());
            setMeTotal(gameRole.getMeBet());
        }
    }

    private void setBetTotal(long j) {
        this.b.setText(FormatUtils.formatCoupon(j));
        this.e.setVisibility(j > 0 ? 0 : 4);
    }

    private void setMeTotal(long j) {
        this.c.setText(FormatUtils.formatCoupon(j));
        this.f.setVisibility(j > 0 ? 0 : 4);
    }

    public long getRoleId() {
        return this.g != null ? this.g.getRoleId() : -1;
    }

    public void setText(String str) {
        this.a.setText(str);
    }
}
