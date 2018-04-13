package cn.v6.sixrooms.room.dialog;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.utils.DensityUtil;

public abstract class BaseDialog extends Dialog {
    private static final String a = BaseDialog.class.getSimpleName();
    private View b;
    private FrameLayout c;
    protected BaseRoomActivity mBaseRoomActivity;
    protected WrapRoomInfo mWrapRoomInfo;

    protected abstract View getDialogContentView();

    public BaseDialog(BaseRoomActivity baseRoomActivity, WrapRoomInfo wrapRoomInfo) {
        this(baseRoomActivity);
        this.mWrapRoomInfo = wrapRoomInfo;
    }

    public BaseDialog(BaseRoomActivity baseRoomActivity) {
        super(baseRoomActivity, R.style.Transparent_OutClose_NoTitle);
        this.mBaseRoomActivity = baseRoomActivity;
        this.b = View.inflate(this.mBaseRoomActivity, R.layout.dialog_base, null);
        this.c = (FrameLayout) this.b.findViewById(R.id.fl_content);
        this.c.addView(getDialogContentView());
        setContentView(this.b);
    }

    public void setLayout(int i) {
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        if (i == 0) {
            window.setBackgroundDrawableResource(R.drawable.room_chat_common_backgroud);
            attributes.gravity = 80;
            attributes.width = -1;
            attributes.height = DensityUtil.dip2px(312.0f);
        } else if (i == 4) {
            window.setBackgroundDrawableResource(R.color.dialog_land_bg);
            attributes.gravity = 80;
            attributes.width = -1;
            attributes.height = DensityUtil.dip2px(312.0f);
        } else if (i == 1) {
            window.setBackgroundDrawableResource(R.drawable.room_chat_common_backgroud);
            attributes.gravity = 80;
            attributes.width = -1;
            attributes.height = DensityUtil.dip2px(312.0f);
        } else if (i == 2 || i == 3) {
            window.setBackgroundDrawableResource(R.color.dialog_land_bg);
            getWindow().addFlags(1024);
            attributes.gravity = 5;
            attributes.width = DensityUtil.dip2px(252.0f);
            attributes.height = -1;
        }
        window.setAttributes(attributes);
    }
}
