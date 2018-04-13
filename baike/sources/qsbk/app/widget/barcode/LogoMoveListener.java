package qsbk.app.widget.barcode;

import android.view.View;
import qsbk.app.widget.barcode.SimpleZoomListener.ControlType;

public class LogoMoveListener extends SimpleZoomListener {
    protected void a(View view, float f, float f2) {
        setControlType(ControlType.PAN);
    }

    protected float a() {
        return 5.0f;
    }
}
