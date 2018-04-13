package cn.v6.sixrooms.hall;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.base.SixRoomsUtils;
import cn.v6.sixrooms.utils.DensityUtil;

class ao extends Dialog implements OnClickListener {
    static final /* synthetic */ boolean a = (!ao.class.desiredAssertionStatus());
    private Context b;

    ao(@NonNull Context context) {
        super(context);
        this.b = context;
        requestWindowFeature(1);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        if (a || window != null) {
            LayoutParams attributes = window.getAttributes();
            attributes.gravity = 80;
            window.setAttributes(attributes);
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setLayout(-1, -2);
            window.setBackgroundDrawable(new ColorDrawable(0));
            View inflate = LayoutInflater.from(context).inflate(R.layout.phone_dialog_login, null);
            TextView textView = (TextView) inflate.findViewById(R.id.loginTv);
            TextView textView2 = (TextView) inflate.findViewById(R.id.registerTv);
            if ("1".equals(V6Coop.COOP_LOGIN_TYPE)) {
                textView2.setVisibility(8);
            } else {
                Drawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setStroke(DensityUtil.dip2px(0.5f), -6710887);
                textView2.setBackgroundDrawable(gradientDrawable);
                textView2.setOnClickListener(this);
            }
            Drawable gradientDrawable2 = new GradientDrawable();
            gradientDrawable2.setCornerRadius((float) SixRoomsUtils.dip2px(5.0f));
            gradientDrawable2.setColor(-52429);
            textView.setBackgroundDrawable(gradientDrawable2);
            textView.setOnClickListener(this);
            setContentView(inflate);
            return;
        }
        throw new AssertionError();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.loginTv) {
            SixRoomsUtils.gotoLogin((Activity) this.b);
        } else if (view.getId() == R.id.registerTv) {
            SixRoomsUtils.gotoRegister((Activity) this.b, true);
        }
        dismiss();
    }
}
