package qsbk.app.activity;

import android.content.Intent;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.widget.EditText;
import android.widget.TextView;
import java.io.Serializable;
import qsbk.app.R;
import qsbk.app.activity.EditInfoBaseActivity.REQUEST_KEY;

public abstract class SingleEditTextBaseActivity extends EditInfoBaseActivity {
    protected TextView c;
    protected TextView d;
    protected EditText e;
    protected TextView f;
    private String g;

    public abstract String getDefaultEditTextTips();

    public abstract String getDefaultInputTips();

    public abstract String getDescription();

    public int getLayout() {
        return R.layout.layout_edit_with_single_edittext;
    }

    public void init() {
        getWindow().setSoftInputMode(21);
        this.c = (TextView) findViewById(R.id.tips);
        this.e = (EditText) findViewById(R.id.edittext);
        this.d = (TextView) findViewById(R.id.left_count);
        this.f = (TextView) findViewById(R.id.title);
        if (maxLength() > 0) {
            this.e.setFilters(new InputFilter[]{new LengthFilter(maxLength())});
        }
        this.e.addTextChangedListener(new act(this));
    }

    public void handleIntent(Intent intent) {
        Serializable serializableExtra = intent.getSerializableExtra(REQUEST_KEY.KEY_DEFAULT_VALUE);
        this.g = serializableExtra != null ? (String) serializableExtra : "";
        this.e.setText(this.g);
        this.e.setSelection(this.e.length());
        this.c.setText(getDefaultInputTips());
        this.c.setVisibility(0);
        this.f.setText(getDescription());
    }

    public int maxLength() {
        return 0;
    }
}
