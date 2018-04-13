package cn.v6.sixrooms.hall;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import cn.v6.sixrooms.R;
import java.util.List;

class ag extends Dialog implements OnClickListener {
    static final /* synthetic */ boolean a = (!ag.class.desiredAssertionStatus());
    private List<String> b;
    private a c;

    ag(@NonNull Context context, List<String> list, a aVar) {
        super(context);
        this.b = list;
        this.c = aVar;
        requestWindowFeature(1);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        if (a || window != null) {
            LayoutParams attributes = window.getAttributes();
            attributes.gravity = 48;
            attributes.dimAmount = 0.0f;
            window.setAttributes(attributes);
            window.setWindowAnimations(R.style.dialog_hosts_type_anim);
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setLayout(-1, -2);
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.addFlags(2);
            View inflate = LayoutInflater.from(context).inflate(R.layout.phone_dialog_hosts_category, null);
            GridView gridView = (GridView) inflate.findViewById(R.id.gridView);
            ((ImageView) inflate.findViewById(R.id.closeTv)).setOnClickListener(this);
            gridView.setAdapter(new af(context, this.b));
            gridView.setOnItemClickListener(new ah(this));
            setContentView(inflate);
            return;
        }
        throw new AssertionError();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.closeTv) {
            dismiss();
        }
    }
}
