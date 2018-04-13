package cn.v6.sixrooms.room.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.utils.AppSclickManager;
import cn.v6.sixrooms.ui.phone.EventActivity;

public class HeadLineRuleDialog extends Dialog implements OnClickListener {
    private TextView a;
    private ImageView b;
    private FrameLayout c;
    private String d = "http://v.6.cn/coop/mobile/event/headline/index.php";
    private BaseRoomActivity e;

    public HeadLineRuleDialog(BaseRoomActivity baseRoomActivity) {
        super(baseRoomActivity, R.style.ImprovedDialog);
        this.e = baseRoomActivity;
        if (getContext().getResources().getConfiguration().orientation == 2) {
            getWindow().addFlags(1024);
        } else {
            getWindow().addFlags(2048);
        }
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_head_line_rule);
        this.a = (TextView) findViewById(R.id.tv_rule);
        this.b = (ImageView) findViewById(R.id.iv_close);
        this.c = (FrameLayout) findViewById(R.id.fl_root);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_rule) {
            dismiss();
            String str = this.d;
            Intent intent = new Intent(this.e, EventActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("eventurl", str);
            bundle.putBoolean(AppSclickManager.KEY, true);
            intent.putExtras(bundle);
            this.e.startActivity(intent);
        } else if (id == R.id.iv_close || id == R.id.fl_root) {
            dismiss();
        }
    }
}
