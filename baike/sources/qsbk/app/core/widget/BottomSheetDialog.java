package qsbk.app.core.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import qsbk.app.core.R;

public class BottomSheetDialog extends Dialog {
    private Context a;

    public BottomSheetDialog(Context context, int i) {
        super(context, i);
        this.a = context;
    }

    public BottomSheetDialog(Context context) {
        super(context, R.style.BottomSheetDialog);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.height = -2;
        attributes.width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
        attributes.gravity = 80;
        getWindow().setAttributes(attributes);
    }

    public BottomSheetDialog contentView(int i) {
        return i == 0 ? this : contentView(LayoutInflater.from(getContext()).inflate(i, null));
    }

    public BottomSheetDialog contentView(View view) {
        setContentView(view);
        return this;
    }

    public BottomSheetDialog dimAmount(float f) {
        Window window = getWindow();
        if (f > 0.0f) {
            window.addFlags(2);
            LayoutParams attributes = window.getAttributes();
            attributes.dimAmount = f;
            window.setAttributes(attributes);
        } else {
            window.clearFlags(2);
        }
        return this;
    }

    public void setBackground(Drawable drawable) {
        if (drawable != null) {
            getWindow().setBackgroundDrawable(drawable);
        }
    }
}
