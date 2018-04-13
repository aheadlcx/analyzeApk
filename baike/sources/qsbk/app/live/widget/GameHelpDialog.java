package qsbk.app.live.widget;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.connect.common.Constants;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.live.R;

public class GameHelpDialog extends BaseDialog {
    protected TextView c;
    protected long d;
    private String e;
    private ImageView f;

    public GameHelpDialog(Context context) {
        super(context);
    }

    public GameHelpDialog(Context context, int i) {
        super(context, i);
    }

    public GameHelpDialog(Context context, long j) {
        super(context);
        this.d = j;
    }

    protected int c() {
        switch ((int) this.d) {
            case 1:
                return R.layout.activity_game_hlnb_help_dialog;
            case 2:
                return R.layout.activity_game_ypdx_help_dialog;
            case 3:
                return R.layout.activity_game_catanddog_help_dialog;
            case 4:
                return R.layout.activity_game_fanfanle_help_dialog;
            case 5:
                return R.layout.activity_game_rolltable_help_dialog;
            case 6:
                return R.layout.activity_game_guess_help_dialog;
            case GameView.GAME_GUESS_CON_HELP /*1997*/:
                return R.layout.activity_game_guess_over_dialog;
            case GameView.GAME_GUESS_OVER_HELP /*1998*/:
                return R.layout.activity_game_guess_over_dialog;
            case GameView.GAME_GUESS_FHK_HELP /*1999*/:
                return R.layout.activity_game_guess_fhk_help_dialog;
            default:
                return 0;
        }
    }

    protected void d() {
        i();
        setCanceledOnTouchOutside(false);
        this.c = (TextView) a(R.id.game_help_rule);
        this.f = (ImageView) a(R.id.iv_close);
    }

    protected void e() {
        this.e = ConfigInfoUtil.instance().getGameExplainText(this.d + "");
        if (this.d == 1 || this.d == 2) {
            if (TextUtils.isEmpty(this.e)) {
                this.e = this.d == 1 ? "2.5~2.9" : "4-18";
            }
            this.c.setText(String.format(this.c.getText().toString(), new Object[]{this.e}));
        } else if (this.d == 3) {
            String str = "2.2";
            String str2 = Constants.VIA_SHARE_TYPE_INFO;
            if (!TextUtils.isEmpty(this.e)) {
                String[] split = this.e.split("\\|");
                if (split != null && split.length == 2) {
                    str = split[0];
                    str2 = split[1];
                }
            }
            this.c.setText(String.format(this.c.getText().toString(), new Object[]{str, str2}));
        }
        if (this.f != null) {
            this.f.setOnClickListener(new bx(this));
        }
    }

    protected int a() {
        return 48;
    }
}
