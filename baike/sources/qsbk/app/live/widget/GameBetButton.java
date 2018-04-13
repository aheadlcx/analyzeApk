package qsbk.app.live.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.PreferenceUtils;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;

public class GameBetButton extends LinearLayout {
    private List<View> a;
    private View b;
    private long c;

    public GameBetButton(Context context) {
        this(context, null);
    }

    public GameBetButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GameBetButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new ArrayList();
        this.c = -1;
        a();
    }

    private void a() {
        Map map;
        Map gameButtonMap = ConfigInfoUtil.instance().getGameButtonMap("1");
        if (gameButtonMap == null || gameButtonMap.isEmpty()) {
            gameButtonMap = new HashMap();
            gameButtonMap.put(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, "1");
            gameButtonMap.put("100", "2");
            gameButtonMap.put(Constants.DEFAULT_UIN, "3");
            gameButtonMap.put("10000", "4");
            map = gameButtonMap;
        } else {
            map = gameButtonMap;
        }
        Map treeMap = new TreeMap(new bo(this));
        for (String str : map.keySet()) {
            treeMap.put(str, map.get(str));
        }
        this.c = PreferenceUtils.instance().getLong("bet_option_selected_id", -1);
        for (String str2 : treeMap.keySet()) {
            View inflate = View.inflate(getContext(), R.layout.live_game_bet_button, null);
            ((TextView) inflate.findViewById(R.id.btn_bet_num)).setText(str2);
            this.a.add(inflate);
            long longValue = Long.valueOf((String) treeMap.get(str2)).longValue();
            if (this.c == -1) {
                this.b = inflate;
                this.b.setSelected(true);
                this.c = longValue;
            } else if (this.c == longValue) {
                this.b = inflate;
                this.b.setSelected(true);
            }
            inflate.setOnClickListener(new bp(this, longValue));
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.leftMargin = WindowUtils.dp2Px(4);
            inflate.setLayoutParams(layoutParams);
            addView(inflate);
        }
    }

    public void setButtonEnable(boolean z) {
        for (View enabled : this.a) {
            enabled.setEnabled(z);
        }
    }

    public View getSelectedButton() {
        return this.b;
    }

    public long getBetOptionId() {
        return this.c;
    }
}
