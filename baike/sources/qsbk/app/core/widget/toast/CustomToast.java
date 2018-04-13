package qsbk.app.core.widget.toast;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import qsbk.app.core.R;

@Deprecated
public class CustomToast extends AbstractToast {
    final Context a;
    final CustomToast$a b = new CustomToast$a();
    int c;
    View d;

    public CustomToast(Context context) {
        this.a = context;
        try {
            this.b.d = context.getResources().getDimensionPixelSize(a("dimen", "toast_y_offset"));
        } catch (NotFoundException e) {
            this.b.d = 0;
        }
        try {
            this.b.b = context.getResources().getInteger(a("integer", "config_toastDefaultGravity"));
        } catch (NotFoundException e2) {
            this.b.b = 17;
        }
    }

    public static CustomToast makeText(Context context, CharSequence charSequence, int i) {
        View inflate;
        CustomToast customToast = new CustomToast(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        try {
            inflate = layoutInflater.inflate(a("layout", "transient_notification"), null);
        } catch (NotFoundException e) {
            inflate = layoutInflater.inflate(R.layout.core_transient_notification, null);
        }
        ((TextView) inflate.findViewById(a("id", "message"))).setText(charSequence);
        customToast.d = inflate;
        customToast.c = i;
        return customToast;
    }

    public static CustomToast makeText(Context context, int i, int i2) throws NotFoundException {
        return makeText(context, context.getResources().getText(i), i2);
    }

    private static int a(String str, String str2) {
        return Resources.getSystem().getIdentifier(str2, str, "android");
    }

    public void show() {
        if (this.d == null) {
            throw new RuntimeException("setView must have been called");
        }
        CustomToast$a customToast$a = this.b;
        customToast$a.i = this.d;
        customToast$a.g = this.c == 0 ? 2000 : 3500;
        customToast$a.show();
    }

    public void cancel() {
        this.b.hide();
    }

    public View getView() {
        return this.d;
    }

    public void setView(View view) {
        this.d = view;
    }

    public int getDuration() {
        return this.c;
    }

    public void setDuration(int i) {
        this.c = i;
    }

    public void setMargin(float f, float f2) {
        this.b.e = f;
        this.b.f = f2;
    }

    public float getHorizontalMargin() {
        return this.b.e;
    }

    public float getVerticalMargin() {
        return this.b.f;
    }

    public void setGravity(int i, int i2, int i3) {
        this.b.b = i;
        this.b.c = i2;
        this.b.d = i3;
    }

    public int getGravity() {
        return this.b.b;
    }

    public int getXOffset() {
        return this.b.c;
    }

    public int getYOffset() {
        return this.b.d;
    }

    public void setText(int i) {
        setText(this.a.getText(i));
    }

    public void setText(CharSequence charSequence) {
        if (this.d == null) {
            throw new RuntimeException("This Toast was not created with Toast.makeText()");
        }
        TextView textView = (TextView) this.d.findViewById(a("id", "message"));
        if (textView == null) {
            throw new RuntimeException("This Toast was not created with Toast.makeText()");
        }
        textView.setText(charSequence);
    }
}
