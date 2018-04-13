package qsbk.app.live.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.List;
import qsbk.app.live.R;

public class GameDiceView extends LinearLayout {
    private ImageView[] a;
    private int[] b;

    public GameDiceView(Context context) {
        this(context, null);
    }

    public GameDiceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GameDiceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new ImageView[3];
        this.b = new int[]{R.drawable.live_game_ypdx_dice_1, R.drawable.live_game_ypdx_dice_2, R.drawable.live_game_ypdx_dice_3, R.drawable.live_game_ypdx_dice_4, R.drawable.live_game_ypdx_dice_5, R.drawable.live_game_ypdx_dice_6};
        a();
    }

    private void a() {
        setOrientation(0);
        View inflate = View.inflate(getContext(), R.layout.live_game_dice_view, this);
        this.a[0] = (ImageView) inflate.findViewById(R.id.iv_dice_1);
        this.a[1] = (ImageView) inflate.findViewById(R.id.iv_dice_2);
        this.a[2] = (ImageView) inflate.findViewById(R.id.iv_dice_3);
    }

    public void setDiceValue(List<Integer> list) {
        if (list != null && list.size() >= 3) {
            int i = 0;
            while (i < 3) {
                if (((Integer) list.get(i)).intValue() <= 6 && ((Integer) list.get(i)).intValue() > 0) {
                    this.a[i].setImageResource(this.b[((Integer) list.get(i)).intValue() - 1]);
                }
                i++;
            }
        }
    }
}
