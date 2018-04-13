package cn.v6.sixrooms.ui.phone.input;

import android.os.Handler;
import android.os.Message;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.ui.phone.input.BaseRoomInputDialog.OnKeyBoardLister;
import cn.v6.sixrooms.utils.LogUtils;
import java.util.Iterator;

final class a extends Handler {
    final /* synthetic */ BaseRoomInputDialog a;

    a(BaseRoomInputDialog baseRoomInputDialog) {
        this.a = baseRoomInputDialog;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 6:
                this.a.mActivity.isCanSpeak = true;
                return;
            case 9:
                BaseRoomInputDialog.a(this.a);
                this.a.mExpressionKeyboard.setVisibility(0);
                this.a.mExpressionBtn.setOnClickListener(this.a);
                this.a.mExpressionBtn.setBackgroundResource(this.a.mInputLayoutFactory.getKeyboardImg());
                LogUtils.d(BaseRoomInputDialog.b(this.a), "mExpressionKeyboard-11111--mExpressionKeyboard.getVisibility()--" + this.a.mExpressionKeyboard.getVisibility() + "---" + this.a.isShowing());
                if (BaseRoomInputDialog.c(this.a) != null && BaseRoomInputDialog.c(this.a).size() > 0) {
                    Iterator it;
                    if (this.a.isShowing()) {
                        it = BaseRoomInputDialog.c(this.a).iterator();
                        while (it.hasNext()) {
                            ((OnKeyBoardLister) it.next()).OnKeyBoardChange(true, ((int) this.a.mActivity.getResources().getDimension(R.dimen.phone_expression_key_height)) + ((int) this.a.mActivity.getResources().getDimension(R.dimen.input_chat_up_height_extend)));
                        }
                        return;
                    }
                    it = BaseRoomInputDialog.c(this.a).iterator();
                    while (it.hasNext()) {
                        ((OnKeyBoardLister) it.next()).OnKeyBoardChange(false, 0);
                    }
                    return;
                }
                return;
            case 17:
                if (this.a.mActivity != null) {
                    this.a.mActivity.refreshChat();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
