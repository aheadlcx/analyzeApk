package cn.v6.sixrooms.ui.phone.input;

import android.app.Dialog;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.BaseRoomActivity.WindowColor;

public abstract class AbRoomInputDialog extends Dialog {
    private WindowColor a;
    protected BaseRoomActivity mActivity;
    protected InputMethodManager mInputManager = ((InputMethodManager) this.mActivity.getSystemService("input_method"));

    public abstract View initContentView();

    public abstract void initListener();

    public abstract void initView();

    public AbRoomInputDialog(BaseRoomActivity baseRoomActivity) {
        super(baseRoomActivity, R.style.inputDialogStyle);
        this.mActivity = baseRoomActivity;
        getWindow().setSoftInputMode(20);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        getWindow().setAttributes(attributes);
        setContentView(initContentView());
        initView();
        initListener();
    }

    public void show() {
        this.a = this.mActivity.mWindowColor;
        this.mActivity.setWindow(WindowColor.WHITE);
        super.show();
    }

    public void dismiss() {
        this.mActivity.setWindow(this.a);
        super.dismiss();
    }
}
