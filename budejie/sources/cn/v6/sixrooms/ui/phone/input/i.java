package cn.v6.sixrooms.ui.phone.input;

import android.graphics.Rect;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.ui.phone.input.BaseRoomInputDialog.OnKeyBoardLister;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.LogUtils;
import java.util.Iterator;

final class i implements OnGlobalLayoutListener {
    final /* synthetic */ BaseRoomInputDialog a;

    i(BaseRoomInputDialog baseRoomInputDialog) {
        this.a = baseRoomInputDialog;
    }

    public final void onGlobalLayout() {
        Rect rect = new Rect();
        BaseRoomInputDialog.f(this.a).getWindowVisibleDisplayFrame(rect);
        int absoluteScreenHeight = DensityUtil.getAbsoluteScreenHeight(this.a.mActivity) - rect.bottom;
        if (BaseRoomInputDialog.c(this.a) != null && BaseRoomInputDialog.c(this.a).size() > 0) {
            if (absoluteScreenHeight > DensityUtil.dip2px(75.0f)) {
                Iterator it = BaseRoomInputDialog.c(this.a).iterator();
                while (it.hasNext()) {
                    ((OnKeyBoardLister) it.next()).OnKeyBoardChange(true, ((int) this.a.mActivity.getResources().getDimension(R.dimen.input_chat_up_height_extend)) + absoluteScreenHeight);
                }
            } else {
                Iterator it2 = BaseRoomInputDialog.c(this.a).iterator();
                while (it2.hasNext()) {
                    OnKeyBoardLister onKeyBoardLister = (OnKeyBoardLister) it2.next();
                    if (!this.a.isShowing()) {
                        onKeyBoardLister.OnKeyBoardChange(false, 0);
                    }
                }
            }
            LogUtils.d(BaseRoomInputDialog.b(this.a), "DensityUtil.getScreenHeight()---" + DensityUtil.getAbsoluteScreenHeight(this.a.mActivity) + "===rect.bottom==" + rect.bottom);
        }
    }
}
