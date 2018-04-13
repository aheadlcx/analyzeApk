package qsbk.app.live.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.GameResult;

public class PokerGroup extends View {
    private static final String a = PokerGroup.class.getSimpleName();
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private RectF H = new RectF();
    private int b = -1;
    private int c = -7829368;
    private int d = 0;
    private float e = 0.0f;
    private int f = 0;
    private Drawable g;
    private int h = -65536;
    private int i;
    private Drawable j;
    private int k;
    private TextPaint l;
    private TextPaint m;
    private TextPaint n;
    private List<Integer> o = new ArrayList();
    private String p;
    private Runnable q;
    private int r;
    private int s;
    private int t = 200;
    private int u = 20;
    private int v = (this.t / this.u);
    private boolean w = false;
    private int x;
    private int y;
    private int z;

    public PokerGroup(Context context) {
        super(context);
        a(null, 0);
    }

    public PokerGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    public PokerGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet, i);
    }

    private void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.PokerGroup, i, 0);
        this.b = obtainStyledAttributes.getColor(R.styleable.PokerGroup_pokerColor, this.b);
        this.c = obtainStyledAttributes.getColor(R.styleable.PokerGroup_pokerBorderColor, this.c);
        this.h = obtainStyledAttributes.getColor(R.styleable.PokerGroup_pokerResultColor, this.h);
        this.e = obtainStyledAttributes.getDimension(R.styleable.PokerGroup_pokerCorner, this.e);
        this.f = (int) obtainStyledAttributes.getDimension(R.styleable.PokerGroup_pokerInnerPadding, (float) this.f);
        this.i = (int) obtainStyledAttributes.getDimension(R.styleable.PokerGroup_pokerResultTextSize, (float) this.i);
        this.d = (int) obtainStyledAttributes.getDimension(R.styleable.PokerGroup_pokerBorderWidth, (float) this.d);
        this.k = obtainStyledAttributes.getInt(R.styleable.PokerGroup_pokerCount, 1);
        if (obtainStyledAttributes.hasValue(R.styleable.PokerGroup_pokerBackImage)) {
            this.g = obtainStyledAttributes.getDrawable(R.styleable.PokerGroup_pokerBackImage);
            this.g.setCallback(this);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.PokerGroup_pokerResultImage)) {
            this.j = obtainStyledAttributes.getDrawable(R.styleable.PokerGroup_pokerResultImage);
            this.j.setCallback(this);
        }
        obtainStyledAttributes.recycle();
        this.l = new TextPaint();
        this.l.setFlags(1);
        this.l.setTextAlign(Align.LEFT);
        this.l.setStyle(Style.FILL);
        this.m = new TextPaint();
        this.m.setFlags(1);
        this.m.setTextAlign(Align.LEFT);
        this.m.setStyle(Style.STROKE);
        this.n = new TextPaint();
        this.n.setFlags(1);
        this.n.setTextAlign(Align.LEFT);
        this.n.setStyle(Style.STROKE);
        a();
    }

    private void a() {
        this.l.setColor(this.b);
        this.n.setColor(this.h);
        this.n.setTextSize((float) this.i);
        this.m.setColor(this.c);
        this.m.setStrokeWidth((float) this.d);
    }

    protected void onVisibilityChanged(@NonNull View view, int i) {
        if (i == 0) {
            this.x = getWidth();
            this.y = getHeight();
            this.z = getPaddingLeft();
            this.B = getPaddingTop();
            this.A = getPaddingRight();
            this.C = getPaddingBottom();
            this.D = (this.x - this.z) - this.A;
            this.E = (this.y - this.B) - this.C;
            this.F = this.g.getIntrinsicWidth();
            this.G = this.g.getIntrinsicHeight();
            this.F = (this.F * this.E) / this.G;
            if (this.F > this.D) {
                this.F = this.D;
            }
            this.G = this.E;
            if (this.k > 1 && this.s == 0) {
                int i2 = (this.D - this.F) / (this.k - 1);
                this.s = i2;
                this.r = i2;
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        for (i = 0; i < this.k; i++) {
            int intValue;
            if (i < this.o.size()) {
                intValue = ((Integer) this.o.get(i)).intValue();
            } else {
                intValue = -1;
            }
            int i2 = this.z + (this.r * i);
            int i3 = this.B;
            if (intValue != -1) {
                Drawable pokerDrawable = getPokerDrawable(intValue);
                pokerDrawable.setBounds(i2, i3, this.F + i2, this.G + i3);
                pokerDrawable.draw(canvas);
            } else if (this.g != null) {
                this.g.setBounds(i2, i3, this.F + i2, this.G + i3);
                this.g.draw(canvas);
            } else {
                this.H.set((float) i2, (float) i3, (float) (i2 + this.F), (float) (i3 + this.G));
                canvas.drawRoundRect(this.H, this.e, this.e, this.l);
                canvas.drawRoundRect(this.H, this.e, this.e, this.m);
            }
        }
        if (this.w) {
            this.r += this.s / this.v;
        }
        if (!TextUtils.isEmpty(this.p) && this.j != null) {
            pokerDrawable = this.j;
            i = this.D;
            int intrinsicHeight = (pokerDrawable.getIntrinsicHeight() * i) / pokerDrawable.getIntrinsicWidth();
            pokerDrawable.setBounds(this.z, (this.B + this.E) - intrinsicHeight, i + this.z, this.B + this.E);
            pokerDrawable.draw(canvas);
            float f = (float) this.z;
            canvas.drawText(this.p, ((((float) this.D) - this.n.measureText(this.p)) / 2.0f) + f, (float) ((this.B + (this.E - (intrinsicHeight / 2))) + WindowUtils.dp2Px(4)), this.n);
        }
    }

    public static Drawable getPokerDrawable(int i) {
        int i2 = ((i % 4) * 13) + ((i / 4) + 1);
        int i3 = R.drawable.live_game_poker_back_popbull;
        if (1 == i2) {
            i3 = R.drawable.live_game_poker_1;
        } else if (2 == i2) {
            i3 = R.drawable.live_game_poker_2;
        } else if (3 == i2) {
            i3 = R.drawable.live_game_poker_3;
        } else if (4 == i2) {
            i3 = R.drawable.live_game_poker_4;
        } else if (5 == i2) {
            i3 = R.drawable.live_game_poker_5;
        } else if (6 == i2) {
            i3 = R.drawable.live_game_poker_6;
        } else if (7 == i2) {
            i3 = R.drawable.live_game_poker_7;
        } else if (8 == i2) {
            i3 = R.drawable.live_game_poker_8;
        } else if (9 == i2) {
            i3 = R.drawable.live_game_poker_9;
        } else if (10 == i2) {
            i3 = R.drawable.live_game_poker_10;
        } else if (11 == i2) {
            i3 = R.drawable.live_game_poker_11;
        } else if (12 == i2) {
            i3 = R.drawable.live_game_poker_12;
        } else if (13 == i2) {
            i3 = R.drawable.live_game_poker_13;
        } else if (14 == i2) {
            i3 = R.drawable.live_game_poker_14;
        } else if (15 == i2) {
            i3 = R.drawable.live_game_poker_15;
        } else if (16 == i2) {
            i3 = R.drawable.live_game_poker_16;
        } else if (17 == i2) {
            i3 = R.drawable.live_game_poker_17;
        } else if (18 == i2) {
            i3 = R.drawable.live_game_poker_18;
        } else if (19 == i2) {
            i3 = R.drawable.live_game_poker_19;
        } else if (20 == i2) {
            i3 = R.drawable.live_game_poker_20;
        } else if (21 == i2) {
            i3 = R.drawable.live_game_poker_21;
        } else if (22 == i2) {
            i3 = R.drawable.live_game_poker_22;
        } else if (23 == i2) {
            i3 = R.drawable.live_game_poker_23;
        } else if (24 == i2) {
            i3 = R.drawable.live_game_poker_24;
        } else if (25 == i2) {
            i3 = R.drawable.live_game_poker_25;
        } else if (26 == i2) {
            i3 = R.drawable.live_game_poker_26;
        } else if (27 == i2) {
            i3 = R.drawable.live_game_poker_27;
        } else if (28 == i2) {
            i3 = R.drawable.live_game_poker_28;
        } else if (29 == i2) {
            i3 = R.drawable.live_game_poker_29;
        } else if (30 == i2) {
            i3 = R.drawable.live_game_poker_30;
        } else if (31 == i2) {
            i3 = R.drawable.live_game_poker_31;
        } else if (32 == i2) {
            i3 = R.drawable.live_game_poker_32;
        } else if (33 == i2) {
            i3 = R.drawable.live_game_poker_33;
        } else if (34 == i2) {
            i3 = R.drawable.live_game_poker_34;
        } else if (35 == i2) {
            i3 = R.drawable.live_game_poker_35;
        } else if (36 == i2) {
            i3 = R.drawable.live_game_poker_36;
        } else if (37 == i2) {
            i3 = R.drawable.live_game_poker_37;
        } else if (38 == i2) {
            i3 = R.drawable.live_game_poker_38;
        } else if (39 == i2) {
            i3 = R.drawable.live_game_poker_39;
        } else if (40 == i2) {
            i3 = R.drawable.live_game_poker_40;
        } else if (41 == i2) {
            i3 = R.drawable.live_game_poker_41;
        } else if (42 == i2) {
            i3 = R.drawable.live_game_poker_42;
        } else if (43 == i2) {
            i3 = R.drawable.live_game_poker_43;
        } else if (44 == i2) {
            i3 = R.drawable.live_game_poker_44;
        } else if (45 == i2) {
            i3 = R.drawable.live_game_poker_45;
        } else if (46 == i2) {
            i3 = R.drawable.live_game_poker_46;
        } else if (47 == i2) {
            i3 = R.drawable.live_game_poker_47;
        } else if (48 == i2) {
            i3 = R.drawable.live_game_poker_48;
        } else if (49 == i2) {
            i3 = R.drawable.live_game_poker_49;
        } else if (50 == i2) {
            i3 = R.drawable.live_game_poker_50;
        } else if (51 == i2) {
            i3 = R.drawable.live_game_poker_51;
        } else if (52 == i2) {
            i3 = R.drawable.live_game_poker_52;
        } else {
            LogUtils.e(a, "wrong poker " + i);
        }
        return AppUtils.getInstance().getAppContext().getResources().getDrawable(i3);
    }

    public void reset() {
        loadPokers((GameResult) null);
    }

    public void loadPokers(GameResult gameResult) {
        this.o.clear();
        if (gameResult != null) {
            Collection resultGroup = gameResult.getResultGroup();
            if (resultGroup != null) {
                this.o.addAll(resultGroup);
            }
        }
        setResult(gameResult);
        invalidate();
    }

    public void setResult(GameResult gameResult) {
        String str = null;
        if (!(gameResult == null || gameResult.getResultGroup() == null)) {
            String str2 = "牛";
            switch (gameResult.getResult()) {
                case 0:
                    str = "没" + str2;
                    break;
                case 1:
                    str = str2 + "一";
                    break;
                case 2:
                    str = str2 + "二";
                    break;
                case 3:
                    str = str2 + "三";
                    break;
                case 4:
                    str = str2 + "四";
                    break;
                case 5:
                    str = str2 + "五";
                    break;
                case 6:
                    str = str2 + "六";
                    break;
                case 7:
                    str = str2 + "七";
                    break;
                case 8:
                    str = str2 + "八";
                    break;
                case 9:
                    str = str2 + "九";
                    break;
                case 10:
                    str = str2 + "牛";
                    break;
            }
        }
        this.p = str;
    }

    public int getPokerColor() {
        return this.b;
    }

    public void setPokerColor(int i) {
        this.b = i;
        a();
    }

    public float getPokerCorner() {
        return this.e;
    }

    public void setPokerCorner(float f) {
        this.e = f;
        a();
    }

    public Drawable getPokerBackImage() {
        return this.g;
    }

    public void setPokerBackImage(Drawable drawable) {
        this.g = drawable;
    }

    public void startPokeGroupAnim() {
        this.r = 0;
        this.w = true;
        if (this.q == null) {
            this.q = new hp(this);
        }
        post(this.q);
    }

    public void cancelPokeGroupAnim() {
        if (this.r != this.s || this.w) {
            this.r = this.s;
            this.w = false;
            invalidate();
        }
    }

    public void loadPokers(List<Integer> list) {
        this.o.clear();
        this.o.addAll(list);
        invalidate();
    }
}
