package qsbk.app.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import qsbk.app.R;

public class NotificationSettingItem extends LinearLayout {
    private final int[] a = new int[]{16842901, 16842904, 16843087};
    private TextView b;
    private String c;
    private ColorStateList d;
    private int e;
    private Switch f;

    public interface OnCheckedChange {
        void onCheckedChanged(NotificationSettingItem notificationSettingItem, boolean z);
    }

    private static final class a implements OnCheckedChangeListener {
        OnCheckedChange a;
        NotificationSettingItem b;

        public a(OnCheckedChange onCheckedChange, NotificationSettingItem notificationSettingItem) {
            this.a = onCheckedChange;
            this.b = notificationSettingItem;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (this.a != null) {
                this.a.onCheckedChanged(this.b, z);
            }
        }
    }

    public NotificationSettingItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.msg_reminder_item, this, true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, this.a);
        this.e = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        this.d = obtainStyledAttributes.getColorStateList(1);
        this.c = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.b = (TextView) findViewById(R.id.description);
        this.f = (Switch) findViewById(R.id.toggle);
        if (this.d != null) {
            this.b.setTextColor(this.d);
        }
        if (this.e > 0) {
            this.b.setTextSize((float) this.e);
        }
        if (!TextUtils.isEmpty(this.c)) {
            this.b.setText(this.c);
        }
    }

    public void setTitle(String str) {
        this.b.setText(str);
    }

    public boolean isChecked() {
        return this.f.isChecked();
    }

    public void setChecked(boolean z) {
        this.f.setChecked(z);
    }

    public void setCheckedEnable(boolean z) {
        this.f.setEnabled(z);
    }

    public void setOnCheckedChangeListener(OnCheckedChange onCheckedChange) {
        this.f.setOnCheckedChangeListener(new a(onCheckedChange, this));
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.f.setEnabled(z);
    }
}
