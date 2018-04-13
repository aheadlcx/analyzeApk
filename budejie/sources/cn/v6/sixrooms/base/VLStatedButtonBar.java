package cn.v6.sixrooms.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import cn.v6.sixrooms.base.VLStatedButtonBar.VLStatedButton.VLButtonState;
import java.util.ArrayList;
import java.util.List;

public class VLStatedButtonBar extends LinearLayout implements OnClickListener {
    private List<VLStatedButtonBar$VLStatedButton> a;
    private VLStatedButtonBarDelegate b;
    private int c;

    public interface VLStatedButtonBarDelegate {
        void onStatedButtonBarChanged(VLStatedButtonBar vLStatedButtonBar, int i);

        void onStatedButtonBarCreated(VLStatedButtonBar vLStatedButtonBar);
    }

    public VLStatedButtonBar$VLStatedButton getButton(int i) {
        return (VLStatedButtonBar$VLStatedButton) this.a.get(i);
    }

    public VLStatedButtonBar(Context context) {
        this(context, null, 0);
    }

    public VLStatedButtonBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @TargetApi(11)
    public VLStatedButtonBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOrientation(0);
        this.a = new ArrayList();
        this.b = null;
        this.c = -1;
    }

    public void addStatedButton(VLStatedButtonBar$VLStatedButton vLStatedButtonBar$VLStatedButton) {
        addStatedButton(vLStatedButtonBar$VLStatedButton, SixRoomsUtils.paramsLinear(0, -1, 1.0f));
    }

    public void addStatedButton(VLStatedButtonBar$VLStatedButton vLStatedButtonBar$VLStatedButton, LayoutParams layoutParams) {
        vLStatedButtonBar$VLStatedButton.setLayoutParams(layoutParams);
        vLStatedButtonBar$VLStatedButton.setOnClickListener(this);
        this.a.add(vLStatedButtonBar$VLStatedButton);
        addView(vLStatedButtonBar$VLStatedButton);
    }

    public void addStatedView(View view, float f) {
        view.setLayoutParams(SixRoomsUtils.paramsLinear(0, -1, f));
        addView(view);
    }

    public void addStatedView(View view, LayoutParams layoutParams) {
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    public void onClick(View view) {
        for (int i = 0; i < this.a.size(); i++) {
            if (this.a.get(i) == view) {
                setChecked(i);
                return;
            }
        }
    }

    public void setChecked(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i >= this.a.size()) {
            i %= this.a.size();
        }
        if (this.c != i) {
            for (int i2 = 0; i2 < this.a.size(); i2++) {
                VLStatedButtonBar$VLStatedButton vLStatedButtonBar$VLStatedButton = (VLStatedButtonBar$VLStatedButton) this.a.get(i2);
                if (i2 == i) {
                    vLStatedButtonBar$VLStatedButton.setButtonState(VLButtonState.StateChecked);
                    this.c = i2;
                } else {
                    vLStatedButtonBar$VLStatedButton.setButtonState(VLButtonState.StateNormal);
                }
            }
            if (this.b != null) {
                this.b.onStatedButtonBarChanged(this, this.c);
            }
        }
    }

    public void setStatedButtonBarDelegate(VLStatedButtonBarDelegate vLStatedButtonBarDelegate) {
        this.a.clear();
        removeAllViews();
        this.c = -1;
        this.b = vLStatedButtonBarDelegate;
        this.b.onStatedButtonBarCreated(this);
    }

    public void update() {
        int i = this.c;
        this.a.clear();
        removeAllViews();
        this.c = -1;
        if (this.b != null) {
            this.b.onStatedButtonBarCreated(this);
        }
        if (i >= this.a.size()) {
            i = this.a.size() - 1;
        }
        if (i >= 0) {
            setChecked(i);
        }
    }

    public void setRedRound(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i >= this.a.size()) {
            i = this.a.size() - 1;
        }
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            VLStatedButtonBar$VLStatedButton vLStatedButtonBar$VLStatedButton = (VLStatedButtonBar$VLStatedButton) this.a.get(i2);
            if (i2 == i) {
                vLStatedButtonBar$VLStatedButton.setUserState(1);
            } else {
                vLStatedButtonBar$VLStatedButton.setUserState(2);
            }
        }
    }

    public void setRedImRound(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i >= this.a.size()) {
            i = this.a.size() - 1;
        }
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            VLStatedButtonBar$VLStatedButton vLStatedButtonBar$VLStatedButton = (VLStatedButtonBar$VLStatedButton) this.a.get(i2);
            if (i2 == i) {
                vLStatedButtonBar$VLStatedButton.setUserState(3);
            } else {
                vLStatedButtonBar$VLStatedButton.setUserState(4);
            }
        }
    }

    public void cancelRedRound(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i >= this.a.size()) {
            i = this.a.size() - 1;
        }
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            VLStatedButtonBar$VLStatedButton vLStatedButtonBar$VLStatedButton = (VLStatedButtonBar$VLStatedButton) this.a.get(i2);
            if (i2 == i) {
                vLStatedButtonBar$VLStatedButton.setUserState(2);
            }
        }
    }

    public void cancelRedIMRound(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i >= this.a.size()) {
            i = this.a.size() - 1;
        }
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            VLStatedButtonBar$VLStatedButton vLStatedButtonBar$VLStatedButton = (VLStatedButtonBar$VLStatedButton) this.a.get(i2);
            if (i2 == i) {
                vLStatedButtonBar$VLStatedButton.setUserState(4);
            }
        }
    }
}
