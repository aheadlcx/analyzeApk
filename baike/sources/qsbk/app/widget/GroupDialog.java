package qsbk.app.widget;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import qsbk.app.R;

public class GroupDialog extends BaseGroupDialog {
    private CharSequence a;
    private CharSequence b;
    private CharSequence c;
    private OnClickListener d;
    private OnClickListener e;

    public static class Builder {
        private Context a;
        private CharSequence b;
        private CharSequence c;
        private CharSequence d;
        private OnClickListener e;
        private OnClickListener f;

        public Builder(Context context) {
            this.a = context;
        }

        public Builder setMessage(CharSequence charSequence) {
            this.b = charSequence;
            return this;
        }

        public Builder hideCancelButton() {
            this.c = null;
            return this;
        }

        public Builder setPositiveButton(CharSequence charSequence, OnClickListener onClickListener) {
            this.d = charSequence;
            this.f = onClickListener;
            return this;
        }

        public Builder setNegativeButton(CharSequence charSequence, OnClickListener onClickListener) {
            this.c = charSequence;
            this.e = onClickListener;
            return this;
        }

        public GroupDialog create() {
            return new GroupDialog(this.a, this.b, this.d, this.f, this.c, this.e);
        }

        public GroupDialog show() {
            GroupDialog create = create();
            create.show();
            return create;
        }
    }

    public GroupDialog(Context context, CharSequence charSequence, CharSequence charSequence2, OnClickListener onClickListener, CharSequence charSequence3, OnClickListener onClickListener2) {
        super(context);
        this.a = charSequence;
        this.b = charSequence3;
        this.c = charSequence2;
        this.d = onClickListener2;
        this.e = onClickListener;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_group_base);
        TextView textView = (TextView) findViewById(R.id.msg);
        if (this.a != null) {
            textView.setText(this.a);
        }
        textView = (TextView) findViewById(R.id.cancel);
        View findViewById = findViewById(R.id.button_divider);
        if (this.b != null) {
            textView.setText(this.b);
        } else {
            textView.setVisibility(8);
            findViewById.setVisibility(8);
        }
        textView.setOnClickListener(new bt(this));
        textView = (TextView) findViewById(R.id.submit);
        if (this.c != null) {
            textView.setText(this.c);
        }
        textView.setOnClickListener(new bu(this));
    }
}
