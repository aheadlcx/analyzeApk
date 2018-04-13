package cn.v6.sixrooms.hall;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.base.VLStatedButtonBar$VLStatedButton;
import cn.v6.sixrooms.base.VLStatedButtonBar.VLStatedButton.VLButtonState;
import cn.v6.sixrooms.base.VLStatedButtonBar.VLStatedButton.VLStatedButtonDelegate;
import cn.v6.sixrooms.hall.HallActivity.a;

class HallActivity$a$a implements VLStatedButtonDelegate {
    final /* synthetic */ a a;
    private ImageView b;
    private TextView c;
    private String d;
    private int e;
    private int f;

    HallActivity$a$a(a aVar, String str, int i, int i2) {
        this.a = aVar;
        this.d = str;
        this.e = i;
        this.f = i2;
    }

    public final void onStatedButtonCreated(VLStatedButtonBar$VLStatedButton vLStatedButtonBar$VLStatedButton, LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.phone_main_bottom_bar_button, vLStatedButtonBar$VLStatedButton);
        this.b = (ImageView) inflate.findViewById(R.id.mainBottomBarButtonImage);
        this.c = (TextView) inflate.findViewById(R.id.mainBottomBarButtonText);
        this.c.setTextSize(11.0f);
        this.c.setText(this.d);
    }

    public final void onStatedButtonChanged(VLStatedButtonBar$VLStatedButton vLStatedButtonBar$VLStatedButton, VLButtonState vLButtonState, int i) {
        if (vLButtonState == VLButtonState.StateChecked) {
            this.b.setImageResource(this.f);
            this.c.setTextColor(-1354682);
            return;
        }
        this.b.setImageResource(this.e);
        this.c.setTextColor(-10066330);
    }
}
