package qsbk.app.live.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import qsbk.app.live.R;
import qsbk.app.live.model.GameRole;

public class GuessBetView extends LinearLayout {
    public static final int BET_BIG = 2;
    public static final int BET_SMALL = 1;
    protected TextView a;
    protected TextView b;
    public GameRole bigRole;
    private ImageView c;
    private ImageView d;
    public ClickListenner mListenner;
    public List<GameRole> roles;
    public GameRole smallRole;
    public boolean useable;

    public interface ClickListenner {
        void clickListenner(boolean z, long j);
    }

    public void setmListenner(ClickListenner clickListenner) {
        this.mListenner = clickListenner;
    }

    public GuessBetView(Context context) {
        super(context);
        a(null, 0);
    }

    public GuessBetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    public GuessBetView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet, i);
    }

    protected void a(AttributeSet attributeSet, int i) {
        setOrientation(0);
        View inflate = View.inflate(getContext(), R.layout.live_guess_bet_view, this);
        this.c = (ImageView) inflate.findViewById(R.id.iv_big);
        this.a = (TextView) inflate.findViewById(R.id.tv_bet_big_total);
        this.d = (ImageView) inflate.findViewById(R.id.iv_small);
        this.b = (TextView) inflate.findViewById(R.id.tv_bet_small_total);
        this.c.setOnClickListener(new en(this));
        this.d.setOnClickListener(new eo(this));
    }

    public void setChoice(int i) {
        if (i == 2) {
            this.c.setSelected(true);
            this.d.setSelected(false);
            this.c.setEnabled(false);
            this.d.setEnabled(false);
        } else if (i == 1) {
            this.c.setSelected(false);
            this.d.setSelected(true);
            this.c.setEnabled(false);
            this.d.setEnabled(false);
        }
    }

    public void setUseable(boolean z) {
        this.useable = z;
    }

    public void initData(List<GameRole> list) {
        this.roles = list;
        this.smallRole = (GameRole) list.get(0);
        this.bigRole = (GameRole) list.get(1);
        reset();
    }

    public void setData(List<GameRole> list) {
        for (int i = 0; i < list.size(); i++) {
            GameRole gameRole = (GameRole) list.get(i);
            if (gameRole.getRoleId() == getBigRoleId()) {
                this.a.setText("" + gameRole.getTotalBet());
                if (gameRole.getTotalBet() > 0) {
                    this.a.setVisibility(0);
                } else {
                    this.a.setVisibility(4);
                }
            } else if (gameRole.getRoleId() == getSmallRoleId()) {
                this.b.setText("" + gameRole.getTotalBet());
                if (gameRole.getTotalBet() > 0) {
                    this.b.setVisibility(0);
                } else {
                    this.b.setVisibility(4);
                }
            }
        }
    }

    public void setData(GameRole gameRole) {
        if (gameRole.getRoleId() == getBigRoleId()) {
            this.a.setText("" + gameRole.getTotalBet());
        } else if (gameRole.getRoleId() == getSmallRoleId()) {
            this.b.setText("" + gameRole.getTotalBet());
        }
    }

    public void reset() {
        this.d.setSelected(false);
        this.c.setSelected(false);
        this.d.setEnabled(true);
        this.c.setEnabled(true);
    }

    public void setNumToZero() {
        this.a.setText("0");
        this.b.setText("0");
        this.a.setVisibility(4);
        this.b.setVisibility(4);
    }

    public long getBigRoleId() {
        return this.bigRole != null ? this.bigRole.getRoleId() : 2;
    }

    public long getSmallRoleId() {
        return this.smallRole != null ? this.smallRole.getRoleId() : 1;
    }
}
