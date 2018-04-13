package cn.xiaochuankeji.tieba.ui.topic;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.a;
import com.iflytek.cloud.SpeechConstant;

public class TopicInvolvedUsersActivity extends a implements OnClickListener {
    private TextView a;
    private TextView b;
    private TextView c;
    private ImageView d;
    private m e;

    protected int a() {
        return R.layout.activity_topic_involved_users;
    }

    protected void c() {
        super.c();
        this.a = (TextView) findViewById(R.id.tvWeek);
        this.b = (TextView) findViewById(R.id.tvMonth);
        this.c = (TextView) findViewById(R.id.tvAll);
        this.d = (ImageView) findViewById(R.id.viewBack);
        this.a.setSelected(true);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
    }

    protected void i_() {
        this.e = m.a(getIntent().getExtras().getLong("TOPIC_ID"));
        a(this.e);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.viewBack:
                finish();
                return;
            case R.id.tvWeek:
                if (!this.a.isSelected()) {
                    this.a.setSelected(true);
                    this.b.setSelected(false);
                    this.c.setSelected(false);
                    this.e.a("weekly");
                    return;
                }
                return;
            case R.id.tvMonth:
                if (!this.b.isSelected()) {
                    this.b.setSelected(true);
                    this.a.setSelected(false);
                    this.c.setSelected(false);
                    this.e.a("monthly");
                    return;
                }
                return;
            case R.id.tvAll:
                if (!this.c.isSelected()) {
                    this.b.setSelected(false);
                    this.a.setSelected(false);
                    this.c.setSelected(true);
                    this.e.a(SpeechConstant.PLUS_LOCAL_ALL);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
