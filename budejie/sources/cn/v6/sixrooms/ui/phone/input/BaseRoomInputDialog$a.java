package cn.v6.sixrooms.ui.phone.input;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import cn.v6.sixrooms.utils.LogUtils;

class BaseRoomInputDialog$a implements OnGlobalLayoutListener {
    View a = this.b.mActivity.getWindow().getDecorView();
    final /* synthetic */ BaseRoomInputDialog b;

    BaseRoomInputDialog$a(BaseRoomInputDialog baseRoomInputDialog) {
        this.b = baseRoomInputDialog;
    }

    public final void onGlobalLayout() {
        if (BaseRoomInputDialog.g(this.b) != null) {
            this.a.getWindowVisibleDisplayFrame(new Rect());
            int height = this.a.getHeight() - this.b.getWindow().getDecorView().getHeight();
            BaseRoomInputDialog.g(this.b).setVisibility(0);
            LogUtils.d(BaseRoomInputDialog.b(this.b), "mExpressionKeyboard-11111--InputGlobalLayoutListener--");
            if (height > 200) {
                if (!BaseRoomInputDialog.h(this.b)) {
                    if (this.b.mExpressionKeyboard.getVisibility() == 0) {
                        BaseRoomInputDialog.e(this.b);
                        this.b.mExpressionKeyboard.setVisibility(8);
                        this.b.mExpressionBtn.setBackgroundResource(this.b.mInputLayoutFactory.getExpressionImg());
                    }
                    if (BaseRoomInputDialog.i(this.b) != null) {
                        BaseRoomInputDialog.i(this.b).changeState(KeyboardState.TEXT_KEYBOARD);
                    }
                }
                BaseRoomInputDialog.a(this.b, true);
                return;
            }
            if (BaseRoomInputDialog.h(this.b) && this.b.isShowing() && !this.b.mExpressionKeyboard.isShown() && !BaseRoomInputDialog.j(this.b)) {
                BaseRoomInputDialog.a(this.b);
                this.b.dismiss();
                BaseRoomInputDialog.k(this.b).sendEmptyMessageDelayed(17, 1000);
            }
            if (this.b.isShowing() && BaseRoomInputDialog.l(this.b)) {
                BaseRoomInputDialog.k(this.b).sendEmptyMessageDelayed(9, 0);
            }
            BaseRoomInputDialog.a(this.b, false);
        }
    }
}
