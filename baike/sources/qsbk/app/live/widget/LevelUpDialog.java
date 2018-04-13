package qsbk.app.live.widget;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveUser;
import qsbk.app.live.ui.helper.LevelHelper;

public class LevelUpDialog extends BaseDialog {
    private ImageView c;
    private TextView d;
    private LiveUser e;

    public LevelUpDialog(Context context, LiveUser liveUser) {
        super(context);
        this.e = liveUser;
        getWindow().setFlags(8, 8);
    }

    protected int c() {
        return R.layout.level_up_dialog;
    }

    protected void d() {
        this.c = (ImageView) a(R.id.iv_avatar);
        this.d = (TextView) a(R.id.tv_notice_content);
    }

    protected void e() {
        AppUtils.getInstance().getImageProvider().loadAvatar(this.c, this.e.av, true);
        String str = this.e.n;
        if (str != null && str.length() > 8) {
            str = str.substring(0, 4) + "..." + str.substring(str.length() - 3, str.length());
        }
        this.d.setText(str + "\n" + AppUtils.getInstance().getAppContext().getString(R.string.level_update_to) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + LevelHelper.getLevelText(this.e.l));
    }

    protected int a() {
        return 17;
    }
}
