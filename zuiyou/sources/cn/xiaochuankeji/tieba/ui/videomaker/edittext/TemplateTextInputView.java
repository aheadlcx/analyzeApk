package cn.xiaochuankeji.tieba.ui.videomaker.edittext;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.videomaker.edittext.e.b;

public class TemplateTextInputView extends FrameLayout {
    private AutoResizeEditText a;
    private ImageView b;
    private b c;

    public TemplateTextInputView(@NonNull Context context) {
        this(context, null);
    }

    public TemplateTextInputView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TemplateTextInputView(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        b();
    }

    private void b() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_template_text_input, this);
        this.a = (AutoResizeEditText) findViewById(R.id.resizeEditText);
        this.b = (ImageView) findViewById(R.id.ivTextBg);
        this.a.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ TemplateTextInputView a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                Spannable text = this.a.a.getText();
                d.a(text, b.class, c.class);
                for (int i = 0; i < text.length(); i++) {
                    if (e.a(text.charAt(i))) {
                        boolean z;
                        int i2 = this.a.c.i;
                        int i3 = this.a.c.j;
                        if (this.a.c.j != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        text.setSpan(new b(i2, i3, z, this.a.c.k), i, i + 1, 18);
                    }
                }
            }
        });
    }

    public void a() {
        this.a.setText(null);
        this.a.clearFocus();
        setVisibility(8);
    }

    public String getCurrStr() {
        return this.a.getText().toString();
    }

    public EditText getEditText() {
        return this.a;
    }

    public void a(b bVar, final String str) {
        setVisibility(0);
        this.c = bVar;
        this.a.requestFocus();
        LayoutParams layoutParams = new FrameLayout.LayoutParams(bVar.d + 40, bVar.e + 40);
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.b.getLayoutParams();
        if (bVar.f != null) {
            layoutParams2.setMargins(bVar.f.left + 20, bVar.f.top + 20, bVar.f.right + 20, bVar.f.bottom + 20);
        } else {
            layoutParams2.setMargins(20, 20, 20, 20);
        }
        this.b.setLayoutParams(layoutParams2);
        if (bVar.c != 0) {
            this.b.setImageResource(bVar.c);
        } else {
            this.b.setImageDrawable(null);
        }
        layoutParams2 = (FrameLayout.LayoutParams) this.a.getLayoutParams();
        if (bVar.g != null) {
            layoutParams2.setMargins(bVar.g.left, bVar.g.top, bVar.g.right, bVar.g.bottom);
        } else {
            layoutParams2.setMargins(0, 0, 0, 0);
        }
        this.a.setLayoutParams(layoutParams2);
        this.a.setPadding(20, 20, 20, 20);
        e.a(this.a, 20.0f);
        this.a.setMaxTextLine(bVar.l);
        if (bVar.h == 0) {
            this.a.getPaint().setFakeBoldText(true);
            this.a.setTypeface(null);
        } else {
            this.a.getPaint().setFakeBoldText(false);
            Typeface a = a.a(getContext(), bVar.h);
            if (a != null) {
                this.a.setTypeface(a);
            }
        }
        this.a.post(new Runnable(this) {
            final /* synthetic */ TemplateTextInputView b;

            public void run() {
                if (TextUtils.isEmpty(str)) {
                    this.b.a.setText("");
                    return;
                }
                this.b.a.setText(str);
                this.b.a.setSelection(this.b.a.length());
            }
        });
    }
}
