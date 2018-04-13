package cn.xiaochuankeji.tieba.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuankeji.tieba.R;

public class PushNotifyPermissionDialog extends Dialog {
    a a;
    @BindView
    ImageView checkBox;
    @BindView
    LinearLayout checkboxPanel;
    @BindView
    TextView confirm;
    @BindView
    TextView dialogContent;
    @BindView
    TextView diaologTitle;

    public interface a {
        void b();

        void c();

        void d();
    }

    public PushNotifyPermissionDialog(Context context, a aVar) {
        super(context);
        requestWindowFeature(1);
        getWindow().setBackgroundDrawableResource(17170445);
        setContentView(View.inflate(getContext(), R.layout.push_permission_container, null));
        ButterKnife.a(this);
        this.a = aVar;
    }

    public void show() {
        super.show();
    }

    public void a(String str) {
        this.diaologTitle.setText(str);
    }

    public void b(String str) {
        this.dialogContent.setText(str);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @OnClick
    public void clickClose() {
        if (this.checkBox.isSelected()) {
            this.a.c();
        } else {
            this.a.b();
        }
        dismiss();
    }

    @OnClick
    public void clickConfirmed() {
        dismiss();
        this.a.d();
    }

    @OnClick
    public void clickCheckBox() {
        if (this.checkBox.isSelected()) {
            this.checkBox.setSelected(false);
        } else {
            this.checkBox.setSelected(true);
        }
    }
}
