package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import cn.v6.sixrooms.R;

public class MsgVerifyDialog extends VerifyDialog {
    private EditText a;
    private TextView b;
    private TextView c;
    private TextView d;
    private OnConfirmClickListener e;
    private Context f;
    private Handler g = new v(this);

    public interface OnConfirmClickListener {
        void onClick(String str);
    }

    public MsgVerifyDialog(Context context) {
        super(context);
        this.f = context;
        setCanceledOnTouchOutside(false);
        requestWindowFeature(1);
        getWindow().setSoftInputMode(4);
        getWindow().setBackgroundDrawable(new ColorDrawable());
        setContentView(R.layout.phone_dialog_code_verify);
        findViewById(R.id.cancel).setOnClickListener(new w(this));
        this.d = (TextView) findViewById(R.id.ok);
        this.d.setOnClickListener(new x(this));
        this.d.setClickable(false);
        this.d.setEnabled(false);
        this.a = (EditText) findViewById(R.id.content);
        this.a.requestFocus();
        this.a.addTextChangedListener(new y(this));
        this.b = (TextView) findViewById(R.id.tv_times_tip);
        this.c = (TextView) findViewById(R.id.tv_getcode_btn);
        switchCodeBtnClickable(false);
    }

    private void a(int i) {
        this.g.removeMessages(1);
        String string = getContext().getResources().getString(R.string.phone_get_verify_time);
        this.c.setText(String.format(string, new Object[]{Integer.valueOf(i)}));
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.arg1 = i;
        this.g.sendMessageDelayed(obtain, 1000);
    }

    public void notifyRecieveCode(String str) {
        a(60);
        CharSequence spannableString = new SpannableString(String.format(getContext().getResources().getString(R.string.micro_listpage_item_rid_format), new Object[]{str}));
        spannableString.setSpan(new ForegroundColorSpan(-16777216), 3, 4, 17);
        spannableString.setSpan(new StyleSpan(1), 3, 4, 17);
        spannableString.setSpan(new AbsoluteSizeSpan(19, true), 3, 4, 17);
        this.b.setText(spannableString);
    }

    public void setCodeBtnOnclickListener(OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
    }

    public void switchCodeBtnClickable(boolean z) {
        this.c.setClickable(z);
        this.c.setEnabled(z);
    }

    public void setComfirmOnclickListener(OnConfirmClickListener onConfirmClickListener) {
        this.e = onConfirmClickListener;
    }
}
