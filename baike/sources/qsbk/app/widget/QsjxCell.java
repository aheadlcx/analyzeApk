package qsbk.app.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import qsbk.app.R;
import qsbk.app.model.Qsjx;
import qsbk.app.utils.UIHelper;

public class QsjxCell extends BaseCell {
    private static final SimpleDateFormat a = new SimpleDateFormat("- yyyy.MM.dd 期 -", Locale.CHINA);
    private ImageView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private View f;

    public void onCreate() {
        setCellView((int) R.layout.high_light_qiushi_item);
        this.b = (ImageView) findViewById(R.id.backgroud_image);
        this.c = (TextView) findViewById(R.id.title);
        this.d = (TextView) findViewById(R.id.date);
        this.e = (TextView) findViewById(R.id.btn);
        this.f = findViewById(R.id.divider);
    }

    public void onUpdate() {
        int i;
        int i2 = -9802626;
        Qsjx qsjx = (Qsjx) getItem();
        this.f.setVisibility(this.q == 0 ? 0 : 4);
        displayImage(this.b, qsjx.picUrl);
        if (!UIHelper.isNightTheme()) {
            this.b.setColorFilter(855638016);
        }
        TextView textView = this.c;
        if (UIHelper.isNightTheme()) {
            i = -9802626;
        } else {
            i = -1;
        }
        textView.setTextColor(i);
        textView = this.d;
        if (UIHelper.isNightTheme()) {
            i = -9802626;
        } else {
            i = -1;
        }
        textView.setTextColor(i);
        TextView textView2 = this.e;
        if (!UIHelper.isNightTheme()) {
            i2 = -1;
        }
        textView2.setTextColor(i2);
        this.c.setText(qsjx.title);
        this.d.setText(a.format(new Date(qsjx.date * 1000)));
        this.e.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.qiuyoucircle_topic_btn_night : R.drawable.qiuyoucircle_topic_btn);
        getCellView().setOnClickListener(new eg(this, qsjx));
    }
}
