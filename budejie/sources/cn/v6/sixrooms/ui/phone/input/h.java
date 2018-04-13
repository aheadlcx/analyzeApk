package cn.v6.sixrooms.ui.phone.input;

import android.text.Editable;
import android.text.TextUtils;
import cn.v6.sixrooms.bean.SmileyVo;
import cn.v6.sixrooms.utils.phone.PhoneSmileyParser;
import cn.v6.sixrooms.widgets.phone.ExpressionKeyboard.OnOperateListener;

final class h implements OnOperateListener {
    final /* synthetic */ BaseRoomInputDialog a;

    h(BaseRoomInputDialog baseRoomInputDialog) {
        this.a = baseRoomInputDialog;
    }

    public final void selectedSmileyVo(SmileyVo smileyVo) {
        if (!TextUtils.isEmpty(smileyVo.getFaceName())) {
            this.a.mInputEditText.append(smileyVo.getFaceName());
        }
    }

    public final void openKeyboard() {
    }

    public final void deleteSmileyVo(PhoneSmileyParser phoneSmileyParser) {
        Editable text = this.a.mInputEditText.getText();
        boolean z = true;
        if (text.length() > 0) {
            String obj = text.toString();
            int lastIndexOf = obj.lastIndexOf("/");
            if (lastIndexOf != -1) {
                z = phoneSmileyParser.parserText(obj.substring(lastIndexOf));
            }
            if (z) {
                text.delete(obj.length() - 1, obj.length());
            } else {
                text.delete(lastIndexOf, obj.length());
            }
        }
    }

    public final void closeKeyboard() {
        BaseRoomInputDialog.e(this.a);
        this.a.mExpressionBtn.setBackgroundResource(this.a.mInputLayoutFactory.getExpressionImg());
        this.a.dismiss();
    }

    public final void sendChatInfo() {
    }
}
