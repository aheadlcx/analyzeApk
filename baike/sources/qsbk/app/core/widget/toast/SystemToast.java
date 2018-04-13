package qsbk.app.core.widget.toast;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Toast;

public class SystemToast extends AbstractToast {
    protected Toast a;

    protected SystemToast() {
    }

    public SystemToast(Context context) {
        this.a = new Toast(context);
    }

    public static SystemToast makeText(Context context, CharSequence charSequence, int i) {
        SystemToast systemToast = new SystemToast();
        systemToast.a = Toast.makeText(context, charSequence, i);
        return systemToast;
    }

    public static SystemToast makeText(Context context, @StringRes int i, int i2) throws NotFoundException {
        return makeText(context, context.getResources().getText(i), i2);
    }

    public void show() {
        this.a.show();
    }

    public void cancel() {
        this.a.cancel();
    }

    public View getView() {
        return this.a.getView();
    }

    public void setView(View view) {
        this.a.setView(view);
    }

    public int getDuration() {
        return this.a.getDuration();
    }

    public void setDuration(int i) {
        this.a.setDuration(i);
    }

    public void setMargin(float f, float f2) {
        this.a.setMargin(f, f2);
    }

    public float getHorizontalMargin() {
        return this.a.getHorizontalMargin();
    }

    public float getVerticalMargin() {
        return this.a.getVerticalMargin();
    }

    public void setGravity(int i, int i2, int i3) {
        this.a.setGravity(i, i2, i3);
    }

    public int getGravity() {
        return this.a.getGravity();
    }

    public int getXOffset() {
        return this.a.getXOffset();
    }

    public int getYOffset() {
        return this.a.getYOffset();
    }

    public void setText(@StringRes int i) {
        this.a.setText(i);
    }

    public void setText(CharSequence charSequence) {
        this.a.setText(charSequence);
    }
}
