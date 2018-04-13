package cn.xiaochuankeji.tieba.ui.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.j;

public class h extends j implements OnClickListener {
    private EditText a;
    private ImageView b;
    private ImageView c;
    private a d;
    private LinearLayout e;

    public interface a {
        void a(String str);

        void j();
    }

    public h(Context context) {
        super(context);
    }

    protected View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.layout_search_header, null);
    }

    protected void a(View view) {
        this.e = (LinearLayout) view.findViewById(R.id.ll_root_container);
        this.a = (EditText) view.findViewById(R.id.etSearchKey);
        this.b = (ImageView) view.findViewById(R.id.ivClear);
        this.c = (ImageView) view.findViewById(R.id.ivCancel);
        c();
    }

    public void a(String str, a aVar) {
        this.a.requestFocus();
        this.a.setHint(str);
        this.d = aVar;
    }

    private void c() {
        this.a.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                CharSequence trim = this.a.a.getText().toString().trim();
                this.a.d.a(trim);
                if (TextUtils.isEmpty(trim)) {
                    this.a.b.setVisibility(4);
                } else {
                    this.a.b.setVisibility(0);
                }
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivClear:
                this.a.setText("");
                return;
            case R.id.ivCancel:
                this.d.j();
                return;
            default:
                return;
        }
    }
}
