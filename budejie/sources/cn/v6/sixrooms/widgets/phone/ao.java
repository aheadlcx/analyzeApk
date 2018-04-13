package cn.v6.sixrooms.widgets.phone;

import android.widget.Toast;
import cn.v6.sixrooms.utils.ImprovedDialogForSofa.ImprovedDialogListener;

final class ao implements ImprovedDialogListener {
    final /* synthetic */ SofaView a;

    ao(SofaView sofaView) {
        this.a = sofaView;
    }

    public final boolean OnOkClick(String str) {
        if (this.a.o == null) {
            Toast.makeText(this.a.a, "你没有在room里设置监听", 0).show();
            return false;
        }
        try {
            int parseInt = Integer.parseInt(str);
            Long.parseLong(str);
            this.a.o.kickSofa(this.a.n, parseInt);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this.a.a, "请输入数字", 0).show();
            return false;
        }
    }

    public final void OnCancelClick() {
    }

    public final boolean OnInputNull() {
        Toast.makeText(this.a.a, "请输入沙发数量", 0).show();
        return false;
    }

    public final void showDialog() {
        if (this.a.o != null) {
            this.a.o.onShowDialog();
        }
    }

    public final void dismisDialog() {
        if (this.a.o != null) {
            this.a.o.onDismisDialog();
        }
    }
}
