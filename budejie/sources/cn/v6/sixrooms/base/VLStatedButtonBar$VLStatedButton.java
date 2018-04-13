package cn.v6.sixrooms.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class VLStatedButtonBar$VLStatedButton extends FrameLayout {
    private VLButtonState a;
    private int b;
    private VLStatedButtonDelegate c;

    public enum VLButtonState {
        StateNormal,
        StateHoverd,
        StatePressed,
        StateChecked
    }

    public interface VLStatedButtonDelegate {
        void onStatedButtonChanged(VLStatedButtonBar$VLStatedButton vLStatedButtonBar$VLStatedButton, VLButtonState vLButtonState, int i);

        void onStatedButtonCreated(VLStatedButtonBar$VLStatedButton vLStatedButtonBar$VLStatedButton, LayoutInflater layoutInflater);
    }

    public VLStatedButtonBar$VLStatedButton(Context context) {
        this(context, null, 0);
    }

    public VLStatedButtonBar$VLStatedButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VLStatedButtonBar$VLStatedButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = VLButtonState.StateNormal;
        this.b = 0;
    }

    public void setButtonState(VLButtonState vLButtonState) {
        if (this.a != vLButtonState) {
            this.a = vLButtonState;
            notifyStateChanged();
        }
    }

    public void setUserState(int i) {
        if (this.b != i) {
            this.b = i;
            notifyStateChanged();
        }
    }

    public void notifyStateChanged() {
        if (this.c != null) {
            this.c.onStatedButtonChanged(this, this.a, this.b);
        }
    }

    public void setStatedButtonDelegate(VLStatedButtonDelegate vLStatedButtonDelegate) {
        this.c = vLStatedButtonDelegate;
        LayoutInflater from = LayoutInflater.from(getContext());
        if (this.c != null) {
            this.c.onStatedButtonCreated(this, from);
            this.c.onStatedButtonChanged(this, this.a, this.b);
        }
    }
}
